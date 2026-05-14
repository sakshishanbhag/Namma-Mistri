package com.nammamistri.app.repository

import androidx.lifecycle.LiveData
import com.nammamistri.app.data.dao.SitePhotoDao
import com.nammamistri.app.data.entity.SitePhoto

class PhotoRepository(private val sitePhotoDao: SitePhotoDao) {
    fun getPhotosBySite(siteId: Long): LiveData<List<SitePhoto>> {
        return sitePhotoDao.getPhotosBySite(siteId)
    }

    suspend fun insertPhoto(photo: SitePhoto): Long {
        return sitePhotoDao.insertPhoto(photo)
    }

    suspend fun deletePhoto(photo: SitePhoto) {
        sitePhotoDao.deletePhoto(photo)
    }

    suspend fun getPhotosBySiteSync(siteId: Long): List<SitePhoto> {
        return sitePhotoDao.getPhotosBySiteSync(siteId)
    }

    suspend fun getPhotoById(photoId: Long): SitePhoto? {
        return sitePhotoDao.getPhotoById(photoId)
    }
}
