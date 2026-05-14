package com.nammamistri.app.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "attendance",
    foreignKeys = [ForeignKey(
        entity = Worker::class,
        parentColumns = ["workerId"],
        childColumns = ["workerId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Attendance(
    @PrimaryKey(autoGenerate = true)
    val attendanceId: Long = 0,
    val workerId: Long,
    val date: String, // yyyy-MM-dd format
    val status: String // "PRESENT", "ABSENT", "HALF_DAY"
)
