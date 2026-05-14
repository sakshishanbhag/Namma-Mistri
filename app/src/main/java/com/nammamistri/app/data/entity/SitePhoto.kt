package com.nammamistri.app.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "site_photos",
    foreignKeys = [ForeignKey(
        entity = Site::class,
        parentColumns = ["siteId"],
        childColumns = ["siteId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class SitePhoto(
    @PrimaryKey(autoGenerate = true)
    val photoId: Long = 0,
    val siteId: Long,
    val filePath: String,
    val capturedDate: String,
    val capturedTime: String
)
