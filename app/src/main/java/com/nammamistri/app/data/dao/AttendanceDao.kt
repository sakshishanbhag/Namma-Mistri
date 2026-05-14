package com.nammamistri.app.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nammamistri.app.data.entity.Attendance

@Dao
interface AttendanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceAttendance(attendance: Attendance)

    @Query("SELECT * FROM attendance WHERE workerId = :workerId AND date = :date LIMIT 1")
    suspend fun getByWorkerAndDate(workerId: Long, date: String): Attendance?

    @Query("SELECT * FROM attendance WHERE workerId = :workerId ORDER BY date DESC")
    suspend fun getAttendanceByWorker(workerId: Long): List<Attendance>

    @Delete
    suspend fun deleteAttendance(attendance: Attendance)

    @Query("DELETE FROM attendance WHERE workerId = :workerId")
    suspend fun deleteByWorker(workerId: Long)

    @Query("SELECT COUNT(*) FROM attendance WHERE workerId = :workerId AND status = 'PRESENT' AND date LIKE :monthPattern")
    suspend fun getMonthlyPresentDays(workerId: Long, monthPattern: String): Int
}
