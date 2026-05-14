package com.nammamistri.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nammamistri.app.databinding.FragmentSettingsBinding
import com.nammamistri.app.viewmodel.SettingsViewModel
import com.nammamistri.app.R

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[SettingsViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Real-time wastage slider value
        binding.wastageSlider.addOnChangeListener { _, value, _ ->
            binding.wastageValueText.text = "${value.toInt()}%"
        }

        viewModel.brickCost.observe(viewLifecycleOwner) { cost ->
            binding.brickCostInput.setText(cost.toString())
        }

        viewModel.cementCost.observe(viewLifecycleOwner) { cost ->
            binding.cementCostInput.setText(cost.toString())
        }

        viewModel.sandCost.observe(viewLifecycleOwner) { cost ->
            binding.sandCostInput.setText(cost.toString())
        }

        viewModel.wastagePercent.observe(viewLifecycleOwner) { percent ->
            binding.wastageSlider.value = percent.toFloat()
            binding.wastageValueText.text = "$percent%"
        }

        viewModel.darkMode.observe(viewLifecycleOwner) { enabled ->
            val checkedId = if (enabled) R.id.btn_theme_dark else R.id.btn_theme_light
            binding.appearanceToggleGroup.check(checkedId)
        }

        binding.saveSettingsButton.setOnClickListener {
            val brickCost = binding.brickCostInput.text.toString().toDoubleOrNull() ?: 12.0
            val cementCost = binding.cementCostInput.text.toString().toDoubleOrNull() ?: 450.0
            val sandCost = binding.sandCostInput.text.toString().toDoubleOrNull() ?: 3200.0
            val wastagePercent = binding.wastageSlider.value.toInt()

            viewModel.saveBrickCost(brickCost)
            viewModel.saveCementCost(cementCost)
            viewModel.saveSandCost(sandCost)
            viewModel.saveWastagePercent(wastagePercent)
            
            android.widget.Toast.makeText(requireContext(), "Settings saved", android.widget.Toast.LENGTH_SHORT).show()
        }

        binding.appearanceToggleGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                val isDarkMode = checkedId == R.id.btn_theme_dark
                viewModel.toggleDarkMode(isDarkMode)
            }
        }

        binding.btnNewSite.setOnClickListener {
            // Show a dialog to add new site
            showAddSiteDialog()
        }

        binding.btnArchiveSite.setOnClickListener {
            // Show confirmation dialog for archiving site
            showArchiveSiteConfirmation()
        }

        binding.btnExportData.setOnClickListener {
            // Show confirmation dialog for exporting data
            showExportDataConfirmation()
        }

        // Load and display current site
        loadCurrentSite()
    }

    private fun loadCurrentSite() {
        val currentSite = viewModel.getCurrentSite()
        if (!currentSite.isEmpty() && currentSite != "Active Site") {
            binding.activeSiteNameText.text = currentSite
        }
    }

    private fun showAddSiteDialog() {
        val siteNameInput = android.widget.EditText(requireContext()).apply {
            hint = "Enter site name"
            inputType = android.text.InputType.TYPE_CLASS_TEXT
        }
        
        android.app.AlertDialog.Builder(requireContext())
            .setTitle("Add New Site")
            .setView(siteNameInput)
            .setPositiveButton("Add") { _, _ ->
                val siteName = siteNameInput.text.toString()
                if (siteName.isNotEmpty()) {
                    viewModel.addNewSite(siteName)
                    binding.activeSiteNameText.text = siteName
                    android.widget.Toast.makeText(requireContext(), "Site '$siteName' added successfully", android.widget.Toast.LENGTH_SHORT).show()
                    loadCurrentSite()
                } else {
                    android.widget.Toast.makeText(requireContext(), "Please enter a site name", android.widget.Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showArchiveSiteConfirmation() {
        android.app.AlertDialog.Builder(requireContext())
            .setTitle("Archive Site")
            .setMessage("Are you sure you want to archive the current site? This action cannot be undone.")
            .setPositiveButton("Archive") { _, _ ->
                viewModel.archiveCurrentSite()
                android.widget.Toast.makeText(requireContext(), "Site archived", android.widget.Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showExportDataConfirmation() {
        android.app.AlertDialog.Builder(requireContext())
            .setTitle("Export Site Data")
            .setMessage("Export all measurements and rate logs as a CSV file?")
            .setPositiveButton("Export") { _, _ ->
                viewModel.exportSiteData()
                android.widget.Toast.makeText(requireContext(), "Data exported successfully", android.widget.Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
