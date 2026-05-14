package com.nammamistri.app.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "calculation_logs",
    foreignKeys = [ForeignKey(
        entity = Site::class,
        parentColumns = ["siteId"],
        childColumns = ["siteId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class CalculationLog(
    @PrimaryKey(autoGenerate = true)
    val logId: Long = 0,
    val siteId: Long,
    val timestamp: String,
    val length: Double,
    val height: Double,
    val wallThickness: String,
    val bricksRequired: Int,
    val cementBagsRequired: Int,
    val sandLoadsRequired: Double,
    val wastagePercent: Int,
    val estimatedCost: Double
)
