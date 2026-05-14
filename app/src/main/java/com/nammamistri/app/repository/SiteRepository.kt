package com.nammamistri.app.repository

import androidx.lifecycle.LiveData
import com.nammamistri.app.data.dao.SiteDao
import com.nammamistri.app.data.entity.Site

class SiteRepository(private val siteDao: SiteDao) {
    val allSites: LiveData<List<Site>> = siteDao.getAllSites()

    suspend fun insertSite(site: Site): Long {
        return siteDao.insertSite(site)
    }

    suspend fun updateSite(site: Site) {
        siteDao.updateSite(site)
    }

    suspend fun deleteSite(site: Site) {
        siteDao.deleteSite(site)
    }

    suspend fun getSiteById(siteId: Long): Site? {
        return siteDao.getSiteById(siteId)
    }

    suspend fun getActiveSite(): Site? {
        return siteDao.getActiveSite()
    }
}
