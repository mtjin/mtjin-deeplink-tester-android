package com.mtjin.mtjindlt.data.main.source

import com.mtjin.mtjindlt.data.main.DeepLinkUrl
import com.mtjin.mtjindlt.data.main.source.local.MainLocalDataSource
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val local: MainLocalDataSource) :
    MainRepository {
    override fun insertDeepLinkUrl(deepLinkUrl: DeepLinkUrl): Completable {
        return local.insertDeepLinkUrl(deepLinkUrl)
    }

    override fun getDeepLinkUrls(): Single<List<DeepLinkUrl>> {
        return local.getDeepLinkUrls()
    }

    override fun deleteDeepLinkUrl(deepLinkUrl: DeepLinkUrl): Completable {
        return local.deleteDeepLinkUrl(deepLinkUrl)
    }

}