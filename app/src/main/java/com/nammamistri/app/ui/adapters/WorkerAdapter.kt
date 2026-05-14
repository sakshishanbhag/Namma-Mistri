package com.nammamistri.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nammamistri.app.data.entity.Worker
import com.nammamistri.app.databinding.ItemWorkerBinding
import java.util.Locale

class WorkerAdapter : ListAdapter<Worker, WorkerAdapter.WorkerViewHolder>(WorkerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerViewHolder {
        val binding = ItemWorkerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WorkerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkerViewHolder, position: Int) {
        val worker = getItem(position)
        holder.bind(worker)
    }

    inner class WorkerViewHolder(private val binding: ItemWorkerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(worker: Worker) {
            binding.workerName.text = worker.workerName
            binding.dailyRate.text = String.format(Locale.US, "₹%.0f", worker.dailyRate)
            
            // For now, mock the role and balance since they aren't in the DB yet
            binding.workerRole.text = "Worker"
            binding.balanceDue.text = "₹0"
            
            // Click listeners for attendance (logic to be implemented in ViewModel)
            binding.btnPresent.setOnClickListener { /* TODO */ }
            binding.btnHalfDay.setOnClickListener { /* TODO */ }
            binding.btnAbsent.setOnClickListener { /* TODO */ }
        }
    }

    class WorkerDiffCallback : DiffUtil.ItemCallback<Worker>() {
        override fun areItemsTheSame(oldItem: Worker, newItem: Worker) =
            oldItem.workerId == newItem.workerId

        override fun areContentsTheSame(oldItem: Worker, newItem: Worker) =
            oldItem == newItem
    }
}
