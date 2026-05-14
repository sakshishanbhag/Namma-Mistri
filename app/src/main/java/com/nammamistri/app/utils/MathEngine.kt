package com.nammamistri.app.utils

import java.util.Locale
import kotlin.math.roundToInt

object MathEngine {
    // Wall calculation - brick work formula
    // Standard brick size: 9" x 4.5" x 3" (225mm x 112.5mm x 75mm)
    // Mortar joints account for additional 0.5" on each dimension

    data class BrickCalculation(
        val bricksRequired: Int,
        val cementBagsRequired: Int,
        val sandLoadsRequired: Double
    )

    fun calculateMaterials(
        lengthFeet: Double,
        heightFeet: Double,
        wallThickness: String = "1 Brick",
        wastagePercent: Int = 5
    ): BrickCalculation {
        // Convert feet to inches
        val lengthInches = lengthFeet * 12
        val heightInches = heightFeet * 12

        // Wall area in sq. inches
        val wallArea = lengthInches * heightInches

        // Bricks per square foot = 7.29 (approximately)
        // For 1 brick wall = 7.29 bricks per sq.ft
        // For 1.5 brick wall = 10.94 bricks per sq.ft
        // For 2 brick wall = 14.58 bricks per sq.ft

        val wallAreaSqFt = wallArea / 144.0
        val bricksPerSqFt = when (wallThickness) {
            "0.5 Brick" -> 3.65
            "1 Brick" -> 7.29
            "1.5 Brick" -> 10.94
            "2 Brick" -> 14.58
            else -> 7.29
        }

        var bricksRequired = (wallAreaSqFt * bricksPerSqFt).toInt()
        bricksRequired += (bricksRequired * wastagePercent.toDouble() / 100.0).roundToInt()

        // Cement required: 1 bag per 350 bricks approximately
        val cementBagsRequired = (bricksRequired / 350) + if (bricksRequired % 350 > 0) 1 else 0

        // Sand required: 1 load (1.5 cubic feet) per 500 bricks approximately
        val sandLoads = bricksRequired / 500.0
        val sandLoadsRequired = String.format(Locale.US, "%.2f", sandLoads).toDouble()

        return BrickCalculation(
            bricksRequired = bricksRequired,
            cementBagsRequired = cementBagsRequired,
            sandLoadsRequired = sandLoadsRequired
        )
    }

    fun calculateCost(
        bricksRequired: Int,
        cementBagsRequired: Int,
        sandLoadsRequired: Double,
        brickCostPerPiece: Double = 10.0,
        cementCostPerBag: Double = 250.0,
        sandCostPerLoad: Double = 500.0
    ): Double {
        val brickCost = bricksRequired * brickCostPerPiece
        val cementCost = cementBagsRequired * cementCostPerBag
        val sandCost = sandLoadsRequired * sandCostPerLoad
        return brickCost + cementCost + sandCost
    }
}
