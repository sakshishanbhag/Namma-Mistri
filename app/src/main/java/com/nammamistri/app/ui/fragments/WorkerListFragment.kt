package com.nammamistri.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nammamistri.app.databinding.FragmentWorkerListBinding
import com.nammamistri.app.viewmodel.WorkerViewModel
import com.nammamistri.app.ui.adapters.WorkerAdapter
import com.nammamistri.app.R

class WorkerListFragment : Fragment() {
    private lateinit var binding: FragmentWorkerListBinding
    private lateinit var viewModel: WorkerViewModel
    private lateinit var adapter: WorkerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkerListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[WorkerViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = WorkerAdapter()
        binding.workerRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }

        // Observe workers and update summary
        val siteId = arguments?.getLong("siteId") ?: 1L // Default for demo
        viewModel.getWorkersBySite(siteId).observe(viewLifecycleOwner) { workers ->
            adapter.submitList(workers)
            
            // Update summary card
            binding.totalWorkersCount.text = workers.size.toString()
            
            // Mocking net balance for now (since we don't have attendance/payment data yet)
            val mockBalance = -4200.0
            binding.netBalanceDisplay.text = "₹ ${String.format("%.0f", mockBalance)}"
            binding.netBalanceDisplay.setTextColor(
                if (mockBalance < 0) resources.getColor(R.color.colorError, null) 
                else resources.getColor(R.color.colorSuccess, null)
            )
        }

        binding.addWorkerButton.setOnClickListener {
            // Navigate to add worker detail fragment
            val action = androidx.navigation.fragment.NavHostFragment.findNavController(this)
            action.navigate(R.id.workerDetailFragment)
        }

        // Setup tabs
        binding.diaryTabs.addOnTabSelectedListener(object : com.google.android.material.tabs.TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: com.google.android.material.tabs.TabLayout.Tab?) {
                // Filter list based on tab
            }
            override fun onTabUnselected(tab: com.google.android.material.tabs.TabLayout.Tab?) {}
            override fun onTabReselected(tab: com.google.android.material.tabs.TabLayout.Tab?) {}
        })
    }
}
