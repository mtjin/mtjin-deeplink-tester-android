package com.mtjin.mtjindlt.data.main.source

import com.mtjin.mtjindlt.data.main.DeepLinkUrl
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface MainRepository {
    fun insertDeepLinkUrl(deepLinkUrl: DeepLinkUrl): Completable
    fun getDeepLinkUrls(): Single<List<DeepLinkUrl>>
    fun deleteDeepLinkUrl(deepLinkUrl: DeepLinkUrl) : Completable
}