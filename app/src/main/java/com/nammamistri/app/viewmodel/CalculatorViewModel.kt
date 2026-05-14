package com.nammamistri.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nammamistri.app.data.database.AppDatabase
import com.nammamistri.app.data.entity.CalculationLog
import com.nammamistri.app.repository.CalculatorRepository
import com.nammamistri.app.utils.MathEngine
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CalculatorViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    private val calculatorRepository = CalculatorRepository(
        database.calculationLogDao(),
        database.attendanceDao(),
        database.advancePaymentDao()
    )

    private val _calculationResult = MutableLiveData<MathEngine.BrickCalculation>()
    val calculationResult: LiveData<MathEngine.BrickCalculation> = _calculationResult

    private val _estimatedCost = MutableLiveData<Double>()
    val estimatedCost: LiveData<Double> = _estimatedCost

    fun calculateMaterials(
        length: Double,
        height: Double,
        wallThickness: String,
        wastagePercent: Int
    ) {
        val result = MathEngine.calculateMaterials(length, height, wallThickness, wastagePercent)
        _calculationResult.value = result
    }

    fun estimateCost(
        bricksRequired: Int,
        cementBagsRequired: Int,
        sandLoadsRequired: Double,
        brickCost: Double,
        cementCost: Double,
        sandCost: Double
    ) {
        val cost = MathEngine.calculateCost(
            bricksRequired,
            cementBagsRequired,
            sandLoadsRequired,
            brickCost,
            cementCost,
            sandCost
        )
        _estimatedCost.value = cost
    }

    fun saveCalculation(
        siteId: Long,
        length: Double,
        height: Double,
        wallThickness: String,
        bricksRequired: Int,
        cementBagsRequired: Int,
        sandLoadsRequired: Double,
        wastagePercent: Int,
        estimatedCost: Double
    ) {
        viewModelScope.launch {
            val log = CalculationLog(
                siteId = siteId,
                timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                length = length,
                height = height,
                wallThickness = wallThickness,
                bricksRequired = bricksRequired,
                cementBagsRequired = cementBagsRequired,
                sandLoadsRequired = sandLoadsRequired,
                wastagePercent = wastagePercent,
                estimatedCost = estimatedCost
            )
            calculatorRepository.insertLog(log)
        }
    }

    fun getLogsBySite(siteId: Long): LiveData<List<CalculationLog>> {
        return calculatorRepository.getLogsBySite(siteId)
    }
}
