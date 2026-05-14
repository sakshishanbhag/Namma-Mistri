package com.nammamistri.app.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "advance_payments",
    foreignKeys = [ForeignKey(
        entity = Worker::class,
        parentColumns = ["workerId"],
        childColumns = ["workerId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class AdvancePayment(
    @PrimaryKey(autoGenerate = true)
    val advanceId: Long = 0,
    val workerId: Long,
    val amount: Double,
    val date: String,
    val note: String = ""
)
