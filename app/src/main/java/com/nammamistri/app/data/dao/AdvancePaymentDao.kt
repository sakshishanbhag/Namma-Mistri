package com.nammamistri.app.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nammamistri.app.data.entity.AdvancePayment

@Dao
interface AdvancePaymentDao {
    @Query("SELECT * FROM advance_payments WHERE workerId = :workerId ORDER BY date DESC")
    fun getAdvancesByWorker(workerId: Long): LiveData<List<AdvancePayment>>

    @Insert
    suspend fun insertAdvance(advance: AdvancePayment): Long

    @Query("SELECT COALESCE(SUM(amount), 0) FROM advance_payments WHERE workerId = :workerId")
    suspend fun getTotalAdvancesByWorker(workerId: Long): Double

    @Delete
    suspend fun deleteAdvance(advance: AdvancePayment)

    @Query("SELECT * FROM advance_payments WHERE workerId = :workerId ORDER BY date DESC")
    suspend fun getAdvancesByWorkerSync(workerId: Long): List<AdvancePayment>
}
