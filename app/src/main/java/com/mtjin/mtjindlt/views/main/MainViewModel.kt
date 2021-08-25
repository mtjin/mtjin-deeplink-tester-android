package com.mtjin.mtjindlt.views.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtjin.mtjindlt.base.BaseViewModel
import com.mtjin.mtjindlt.data.main.DeepLinkUrl
import com.mtjin.mtjindlt.data.main.source.MainRepository
import com.mtjin.mtjindlt.extenstions.getTimestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : BaseViewModel() {
    var etSearch = MutableLiveData("")

    private val _onClickSearch = MutableLiveData<Boolean>()
    private val _deepLinkUrlList = MutableLiveData<List<DeepLinkUrl>>()

    val onClickSearch: LiveData<Boolean> get() = _onClickSearch
    val deepLinkUrlList: LiveData<List<DeepLinkUrl>> get() = _deepLinkUrlList

    // 딥링크 검색 버튼 클릭
    fun onClickSearch() {
        if (etSearch.value.toString().isNotBlank()) {
            repository.insertDeepLinkUrl(
                DeepLinkUrl(
                    url = etSearch.value.toString(),
                    timestamp = getTimestamp()
                )
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onComplete = {
                        _onClickSearch.value = true
                        fetchDeepLinkUrlHistories()
                    },
                    onError = {
                        Log.d("TAG", "onClickSearch() onError() -> " + it.localizedMessage)
                        _onClickSearch.value = false
                    }
                ).addTo(compositeDisposable)
        }
    }

    fun fetchDeepLinkUrlHistories() {
        repository.getDeepLinkUrls()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _deepLinkUrlList.value = it
                },
                onError = {
                    Log.d("TAG", "fetchDeepLinkUrlHistories() onError() -> " + it.localizedMessage)
                }
            ).addTo(compositeDisposable)
    }

    fun deleteDeepLinkUrl(deepLinkUrl: DeepLinkUrl) {
        repository.deleteDeepLinkUrl(deepLinkUrl)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showProgress() }
            .doAfterTerminate { hideProgress() }
            .subscribeBy(
                onComplete = {
                    fetchDeepLinkUrlHistories()
                },
                onError = {
                    Log.d("TAG", "deleteDeepLinkUrl() onError() -> " + it.localizedMessage)
                }
            ).addTo(compositeDisposable)
    }
}