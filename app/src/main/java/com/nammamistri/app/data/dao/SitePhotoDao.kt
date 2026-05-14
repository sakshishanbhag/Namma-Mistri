package com.nammamistri.app.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nammamistri.app.data.entity.SitePhoto

@Dao
interface SitePhotoDao {
    @Query("SELECT * FROM site_photos WHERE siteId = :siteId ORDER BY capturedDate DESC")
    fun getPhotosBySite(siteId: Long): LiveData<List<SitePhoto>>

    @Insert
    suspend fun insertPhoto(photo: SitePhoto): Long

    @Delete
    suspend fun deletePhoto(photo: SitePhoto)

    @Query("SELECT * FROM site_photos WHERE siteId = :siteId ORDER BY capturedDate DESC")
    suspend fun getPhotosBySiteSync(siteId: Long): List<SitePhoto>

    @Query("SELECT * FROM site_photos WHERE photoId = :photoId")
    suspend fun getPhotoById(photoId: Long): SitePhoto?
}
