package com.nammamistri.app.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nammamistri.app.data.entity.CalculationLog

@Dao
interface CalculationLogDao {
    @Query("SELECT * FROM calculation_logs WHERE siteId = :siteId ORDER BY timestamp DESC")
    fun getLogsBySite(siteId: Long): LiveData<List<CalculationLog>>

    @Insert
    suspend fun insertLog(log: CalculationLog): Long

    @Query("SELECT * FROM calculation_logs WHERE siteId = :siteId ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLatestLog(siteId: Long): CalculationLog?

    @Delete
    suspend fun deleteLog(log: CalculationLog)

    @Query("DELETE FROM calculation_logs WHERE logId = :logId")
    suspend fun deleteLogById(logId: Long)

    @Query("SELECT * FROM calculation_logs WHERE logId = :logId")
    suspend fun getLogById(logId: Long): CalculationLog?
}
