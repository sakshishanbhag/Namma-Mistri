package com.nammamistri.app.utils

data class WageSummary(
    val workerId: Long,
    val workerName: String,
    val totalDaysWorked: Int,
    val totalWageAmount: Double,
    val totalAdvanceAmount: Double,
    val netPayable: Double,
    val dailyRate: Double
) {
    val balance: Double get() = totalWageAmount - totalAdvanceAmount
}
