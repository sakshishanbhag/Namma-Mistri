package com.nammamistri.app.utils

import android.content.Context

class PrefsManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences(
        "namma_mistri_prefs",
        Context.MODE_PRIVATE
    )

    // Site preferences
    fun setCurrentSiteId(siteId: Long) {
        sharedPreferences.edit().putLong("current_site_id", siteId).apply()
    }

    fun getCurrentSiteId(): Long {
        return sharedPreferences.getLong("current_site_id", -1L)
    }

    // Material cost preferences
    fun setBrickCost(cost: Double) {
        sharedPreferences.edit().putFloat("brick_cost", cost.toFloat()).apply()
    }

    fun getBrickCost(): Double {
        return sharedPreferences.getFloat("brick_cost", 10f).toDouble()
    }

    fun setCementCost(cost: Double) {
        sharedPreferences.edit().putFloat("cement_cost", cost.toFloat()).apply()
    }

    fun getCementCost(): Double {
        return sharedPreferences.getFloat("cement_cost", 250f).toDouble()
    }

    fun setSandCost(cost: Double) {
        sharedPreferences.edit().putFloat("sand_cost", cost.toFloat()).apply()
    }

    fun getSandCost(): Double {
        return sharedPreferences.getFloat("sand_cost", 500f).toDouble()
    }

    // User preferences
    fun setUserLanguage(language: String) {
        sharedPreferences.edit().putString("language", language).apply()
    }

    fun getUserLanguage(): String {
        return sharedPreferences.getString("language", "en") ?: "en"
    }

    fun setDarkMode(enabled: Boolean) {
        sharedPreferences.edit().putBoolean("dark_mode", enabled).apply()
    }

    fun isDarkModeEnabled(): Boolean {
        return sharedPreferences.getBoolean("dark_mode", false)
    }

    // Default wastage percentage
    fun setWastagePercent(percent: Int) {
        sharedPreferences.edit().putInt("wastage_percent", percent).apply()
    }

    fun getWastagePercent(): Int {
        return sharedPreferences.getInt("wastage_percent", 5)
    }

    fun clearAllPrefs() {
        sharedPreferences.edit().clear().apply()
    }

    fun setCurrentSite(siteName: String) {
        sharedPreferences.edit().putString("current_site_name", siteName).apply()
    }

    fun getCurrentSite(): String {
        return sharedPreferences.getString("current_site_name", "Active Site") ?: "Active Site"
    }

    fun archiveCurrentSite() {
        // Mark the current site as archived
        val currentSiteId = getCurrentSiteId()
        sharedPreferences.edit().putBoolean("site_${currentSiteId}_archived", true).apply()
    }

    fun exportSiteDataAsCSV() {
        // Export logic would go here
        // This would typically involve reading data from the database and creating a CSV file
    }
}
