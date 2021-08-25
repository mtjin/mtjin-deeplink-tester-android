package com.mtjin.mtjindlt.data.main.source.local

import com.mtjin.mtjindlt.data.main.DeepLinkUrl
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface MainLocalDataSource {
    fun insertDeepLinkUrl(deepLinkUrl: DeepLinkUrl): Completable
    fun getDeepLinkUrls(): Single<List<DeepLinkUrl>>
    fun deleteDeepLinkUrl(deepLinkUrl: DeepLinkUrl) : Completable
}