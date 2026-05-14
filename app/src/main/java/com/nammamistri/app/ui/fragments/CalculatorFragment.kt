package com.nammamistri.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nammamistri.app.databinding.FragmentCalculatorBinding
import com.nammamistri.app.viewmodel.CalculatorViewModel
import com.nammamistri.app.R

class CalculatorFragment : Fragment() {
    private lateinit var binding: FragmentCalculatorBinding
    private lateinit var viewModel: CalculatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initially hide results
        binding.resultsCard.visibility = View.GONE

        // Update wastage label in real-time
        binding.wastageSlider.addOnChangeListener { _, value, _ ->
            binding.wastageValueText.text = "${value.toInt()}%"
        }

        viewModel.calculationResult.observe(viewLifecycleOwner) { result ->
            binding.bricksResult.text = getString(R.string.result_bricks, result.bricksRequired)
            binding.cementResult.text = getString(R.string.result_cement, result.cementBagsRequired)
            binding.sandResult.text = getString(R.string.result_sand, result.sandLoadsRequired)
            binding.resultsCard.visibility = View.VISIBLE
        }

        binding.calculateButton.setOnClickListener {
            val lengthStr = binding.lengthInput.text.toString()
            val heightStr = binding.heightInput.text.toString()

            if (lengthStr.isEmpty()) {
                binding.lengthInput.error = getString(R.string.error_empty_field)
                return@setOnClickListener
            }
            if (heightStr.isEmpty()) {
                binding.heightInput.error = getString(R.string.error_empty_field)
                return@setOnClickListener
            }

            val length = lengthStr.toDoubleOrNull() ?: return@setOnClickListener
            val height = heightStr.toDoubleOrNull() ?: return@setOnClickListener

            val wallThickness = when (binding.thicknessToggleGroup.checkedButtonId) {
                R.id.btn_4_5 -> "0.5 Brick"
                R.id.btn_9 -> "1 Brick"
                R.id.btn_13_5 -> "1.5 Brick"
                else -> "1 Brick"
            }

            val wastagePercent = binding.wastageSlider.value.toInt()

            viewModel.calculateMaterials(length, height, wallThickness, wastagePercent)
        }

        binding.btnShare.setOnClickListener {
            val bricksText = binding.bricksResult.text.toString()
            val cementText = binding.cementResult.text.toString()
            val sandText = binding.sandResult.text.toString()
            
            val shareMessage = """
                Wall Material Calculator Results
                
                Bricks: $bricksText
                Cement: $cementText
                Sand: $sandText
                
                Calculated using Namma Mistri App
            """.trimIndent()
            
            val shareIntent = android.content.Intent().apply {
                action = android.content.Intent.ACTION_SEND
                putExtra(android.content.Intent.EXTRA_TEXT, shareMessage)
                type = "text/plain"
            }
            
            startActivity(android.content.Intent.createChooser(shareIntent, "Share Results"))
        }
    }
}
