package com.mtjin.mtjindlt.data.main.source.local

import com.mtjin.mtjindlt.data.main.DeepLinkUrl
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MainLocalDataSourceImpl @Inject constructor(private val mainDao: MainDao) :
    MainLocalDataSource {
    override fun insertDeepLinkUrl(deepLinkUrl: DeepLinkUrl): Completable {
        return mainDao.insertDeepLinkUrl(deepLinkUrl)
    }

    override fun getDeepLinkUrls(): Single<List<DeepLinkUrl>> {
        return mainDao.getDeepLinkUrls()
    }

    override fun deleteDeepLinkUrl(deepLinkUrl: DeepLinkUrl): Completable {
        return mainDao.deleteDeepLinkUrl(deepLinkUrl)
    }
}