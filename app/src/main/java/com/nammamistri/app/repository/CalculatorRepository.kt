package com.nammamistri.app.repository

import androidx.lifecycle.LiveData
import com.nammamistri.app.data.dao.AdvancePaymentDao
import com.nammamistri.app.data.dao.AttendanceDao
import com.nammamistri.app.data.dao.CalculationLogDao
import com.nammamistri.app.data.entity.AdvancePayment
import com.nammamistri.app.data.entity.Attendance
import com.nammamistri.app.data.entity.CalculationLog

class CalculatorRepository(
    private val calculationLogDao: CalculationLogDao,
    private val attendanceDao: AttendanceDao,
    private val advancePaymentDao: AdvancePaymentDao
) {
    fun getLogsBySite(siteId: Long): LiveData<List<CalculationLog>> {
        return calculationLogDao.getLogsBySite(siteId)
    }

    suspend fun insertLog(log: CalculationLog): Long {
        return calculationLogDao.insertLog(log)
    }

    suspend fun deleteLog(log: CalculationLog) {
        calculationLogDao.deleteLog(log)
    }

    suspend fun getLatestLog(siteId: Long): CalculationLog? {
        return calculationLogDao.getLatestLog(siteId)
    }

    suspend fun insertAttendance(attendance: Attendance) {
        attendanceDao.insertOrReplaceAttendance(attendance)
    }

    suspend fun getMonthlyPresentDays(workerId: Long, month: String): Int {
        return attendanceDao.getMonthlyPresentDays(workerId, "$month%")
    }

    suspend fun getTotalAdvances(workerId: Long): Double {
        return advancePaymentDao.getTotalAdvancesByWorker(workerId)
    }
}
