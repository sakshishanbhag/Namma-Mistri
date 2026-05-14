package com.nammamistri.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nammamistri.app.data.database.AppDatabase
import com.nammamistri.app.data.entity.SitePhoto
import com.nammamistri.app.repository.PhotoRepository
import kotlinx.coroutines.launch

class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    private val photoRepository = PhotoRepository(database.sitePhotoDao())

    fun getPhotosBySite(siteId: Long): LiveData<List<SitePhoto>> {
        return photoRepository.getPhotosBySite(siteId)
    }

    fun addPhoto(photo: SitePhoto) {
        viewModelScope.launch {
            photoRepository.insertPhoto(photo)
        }
    }

    fun deletePhoto(photo: SitePhoto) {
        viewModelScope.launch {
            photoRepository.deletePhoto(photo)
        }
    }

    suspend fun getPhotosBySiteSync(siteId: Long): List<SitePhoto> {
        return photoRepository.getPhotosBySiteSync(siteId)
    }
}
