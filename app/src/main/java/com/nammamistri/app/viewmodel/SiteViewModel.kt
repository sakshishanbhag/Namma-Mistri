package com.nammamistri.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nammamistri.app.data.database.AppDatabase
import com.nammamistri.app.data.entity.Site
import com.nammamistri.app.repository.SiteRepository
import kotlinx.coroutines.launch

class SiteViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    private val siteRepository = SiteRepository(database.siteDao())

    val allSites: LiveData<List<Site>> = siteRepository.allSites

    fun addSite(site: Site) {
        viewModelScope.launch {
            siteRepository.insertSite(site)
        }
    }

    fun updateSite(site: Site) {
        viewModelScope.launch {
            siteRepository.updateSite(site)
        }
    }

    fun deleteSite(site: Site) {
        viewModelScope.launch {
            siteRepository.deleteSite(site)
        }
    }

    suspend fun getActiveSite(): Site? {
        return siteRepository.getActiveSite()
    }
}
