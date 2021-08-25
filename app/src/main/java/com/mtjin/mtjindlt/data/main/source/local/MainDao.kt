package com.mtjin.mtjindlt.data.main.source.local

import androidx.room.*
import com.mtjin.mtjindlt.data.main.DeepLinkUrl
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface MainDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDeepLinkUrl(deepLinkUrl: DeepLinkUrl): Completable

    @Query("SELECT * FROM deepLinkUrl  ORDER BY `timestamp`")
    fun getDeepLinkUrls(): Single<List<DeepLinkUrl>>

    @Delete
    fun deleteDeepLinkUrl(deepLinkUrl: DeepLinkUrl): Completable
}