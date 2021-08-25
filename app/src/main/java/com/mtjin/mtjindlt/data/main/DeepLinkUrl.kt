package com.mtjin.mtjindlt.data.main

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deepLinkUrl")
data class DeepLinkUrl(
    @PrimaryKey val url: String, val timestamp: Long
)