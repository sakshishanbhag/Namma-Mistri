package com.nammamistri.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nammamistri.app.databinding.FragmentWorkerDetailBinding
import com.nammamistri.app.viewmodel.WorkerViewModel

class WorkerDetailFragment : Fragment() {
    private lateinit var binding: FragmentWorkerDetailBinding
    private lateinit var viewModel: WorkerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkerDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[WorkerViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val workerId = arguments?.getLong("workerId") ?: -1L
        val siteId = arguments?.getLong("siteId") ?: 1L // Default site ID

        // Load existing worker if editing
        if (workerId != -1L) {
            // This would load existing worker data if needed
        }

        binding.saveButton.setOnClickListener {
            val workerName = binding.workerNameInput.text.toString()
            val dailyRate = binding.dailyRateInput.text.toString().toDoubleOrNull() ?: 0.0
            val phoneNumber = binding.phoneNumberInput.text.toString()

            if (workerName.isEmpty()) {
                binding.workerNameInput.error = "Worker name is required"
                return@setOnClickListener
            }

            if (phoneNumber.isEmpty()) {
                binding.phoneNumberInput.error = "Phone number is required"
                return@setOnClickListener
            }

            val worker = com.nammamistri.app.data.entity.Worker(
                workerId = if (workerId != -1L) workerId else 0,
                siteId = siteId,
                workerName = workerName,
                dailyRate = dailyRate,
                phoneNumber = phoneNumber
            )

            if (workerId != -1L) {
                viewModel.updateWorker(worker)
            } else {
                viewModel.addWorker(worker)
            }

            android.widget.Toast.makeText(
                requireContext(),
                "Worker saved successfully",
                android.widget.Toast.LENGTH_SHORT
            ).show()

            androidx.navigation.fragment.NavHostFragment.findNavController(this).popBackStack()
        }

        binding.deleteButton.setOnClickListener {
            if (workerId == -1L) {
                android.widget.Toast.makeText(
                    requireContext(),
                    "Cannot delete new worker",
                    android.widget.Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            android.app.AlertDialog.Builder(requireContext())
                .setTitle("Delete Worker")
                .setMessage("Are you sure you want to delete this worker?")
                .setPositiveButton("Delete") { _, _ ->
                    val worker = com.nammamistri.app.data.entity.Worker(
                        workerId = workerId,
                        siteId = siteId,
                        workerName = "",
                        dailyRate = 0.0,
                        phoneNumber = ""
                    )
                    viewModel.deleteWorker(worker)
                    android.widget.Toast.makeText(
                        requireContext(),
                        "Worker deleted",
                        android.widget.Toast.LENGTH_SHORT
                    ).show()
                    androidx.navigation.fragment.NavHostFragment.findNavController(this).popBackStack()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }
}
