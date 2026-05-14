package com.nammamistri.app.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.nammamistri.app.data.entity.Site

@Dao
interface SiteDao {
    @Query("SELECT * FROM sites ORDER BY createdDate DESC")
    fun getAllSites(): LiveData<List<Site>>

    @Insert
    suspend fun insertSite(site: Site): Long

    @Update
    suspend fun updateSite(site: Site)

    @Delete
    suspend fun deleteSite(site: Site)

    @Query("SELECT * FROM sites WHERE siteId = :siteId")
    suspend fun getSiteById(siteId: Long): Site?

    @Query("SELECT * FROM sites WHERE isActive = 1 LIMIT 1")
    suspend fun getActiveSite(): Site?
}
