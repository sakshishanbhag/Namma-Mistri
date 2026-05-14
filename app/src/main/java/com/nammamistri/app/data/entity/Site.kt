package com.nammamistri.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sites")
data class Site(
    @PrimaryKey(autoGenerate = true)
    val siteId: Long = 0,
    val siteName: String,
    val location: String,
    val createdDate: String,
    val isActive: Boolean = false
)
