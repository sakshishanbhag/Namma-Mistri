package com.nammamistri.app.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.nammamistri.app.data.entity.Worker

@Dao
interface WorkerDao {
    @Query("SELECT * FROM workers WHERE siteId = :siteId ORDER BY workerName ASC")
    fun getWorkersBySite(siteId: Long): LiveData<List<Worker>>

    @Insert
    suspend fun insertWorker(worker: Worker): Long

    @Update
    suspend fun updateWorker(worker: Worker)

    @Delete
    suspend fun deleteWorker(worker: Worker)

    @Query("SELECT * FROM workers WHERE workerId = :workerId")
    suspend fun getWorkerById(workerId: Long): Worker?

    @Query("SELECT * FROM workers WHERE siteId = :siteId")
    suspend fun getWorkersBySiteSync(siteId: Long): List<Worker>
}
