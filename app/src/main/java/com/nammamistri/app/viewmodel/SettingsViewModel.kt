package com.nammamistri.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.nammamistri.app.utils.PrefsManager

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val prefsManager = PrefsManager(application)

    private val _brickCost = MutableLiveData<Double>()
    val brickCost: MutableLiveData<Double> = _brickCost

    private val _cementCost = MutableLiveData<Double>()
    val cementCost: MutableLiveData<Double> = _cementCost

    private val _sandCost = MutableLiveData<Double>()
    val sandCost: MutableLiveData<Double> = _sandCost

    private val _wastagePercent = MutableLiveData<Int>()
    val wastagePercent: MutableLiveData<Int> = _wastagePercent

    private val _darkMode = MutableLiveData<Boolean>()
    val darkMode: MutableLiveData<Boolean> = _darkMode

    init {
        loadSettings()
    }

    private fun loadSettings() {
        _brickCost.value = prefsManager.getBrickCost()
        _cementCost.value = prefsManager.getCementCost()
        _sandCost.value = prefsManager.getSandCost()
        _wastagePercent.value = prefsManager.getWastagePercent()
        _darkMode.value = prefsManager.isDarkModeEnabled()
    }

    fun saveBrickCost(cost: Double) {
        prefsManager.setBrickCost(cost)
        _brickCost.value = cost
    }

    fun saveCementCost(cost: Double) {
        prefsManager.setCementCost(cost)
        _cementCost.value = cost
    }

    fun saveSandCost(cost: Double) {
        prefsManager.setSandCost(cost)
        _sandCost.value = cost
    }

    fun saveWastagePercent(percent: Int) {
        prefsManager.setWastagePercent(percent)
        _wastagePercent.value = percent
    }

    fun toggleDarkMode(enabled: Boolean) {
        prefsManager.setDarkMode(enabled)
        _darkMode.value = enabled
    }

    fun resetSettings() {
        prefsManager.clearAllPrefs()
        loadSettings()
    }

    fun addNewSite(siteName: String) {
        // Store new site to SharedPreferences
        prefsManager.setCurrentSite(siteName)
    }

    fun archiveCurrentSite() {
        // Archive current site logic
        prefsManager.archiveCurrentSite()
    }

    fun exportSiteData() {
        // Export site data as CSV
        prefsManager.exportSiteDataAsCSV()
    }

    fun getCurrentSite(): String {
        return prefsManager.getCurrentSite()
    }
}
