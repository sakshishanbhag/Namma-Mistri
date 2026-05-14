package com.nammamistri.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nammamistri.app.databinding.FragmentResultBinding
import com.nammamistri.app.viewmodel.CalculatorViewModel

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private lateinit var viewModel: CalculatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(CalculatorViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.calculationResult.observe(viewLifecycleOwner) { result ->
            binding.resultDetails.text = """
                Bricks Required: ${result.bricksRequired}
                Cement Bags: ${result.cementBagsRequired}
                Sand Loads: ${result.sandLoadsRequired}
            """.trimIndent()
        }

        viewModel.estimatedCost.observe(viewLifecycleOwner) { cost ->
            binding.costDetails.text = "Estimated Cost: ₹$cost"
        }

        binding.saveButton.setOnClickListener {
            // Implement save functionality
        }

        binding.shareButton.setOnClickListener {
            // Implement share functionality
        }
    }
}
