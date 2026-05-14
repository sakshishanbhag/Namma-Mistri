package com.nammamistri.app.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "workers",
    foreignKeys = [ForeignKey(
        entity = Site::class,
        parentColumns = ["siteId"],
        childColumns = ["siteId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Worker(
    @PrimaryKey(autoGenerate = true)
    val workerId: Long = 0,
    val siteId: Long,
    val workerName: String,
    val dailyRate: Double,
    val phoneNumber: String = ""
)
