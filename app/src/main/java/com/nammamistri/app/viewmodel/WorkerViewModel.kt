package com.nammamistri.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nammamistri.app.data.database.AppDatabase
import com.nammamistri.app.data.entity.Worker
import com.nammamistri.app.repository.WorkerRepository
import kotlinx.coroutines.launch

class WorkerViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    private val workerRepository = WorkerRepository(database.workerDao())

    fun getWorkersBySite(siteId: Long): LiveData<List<Worker>> {
        return workerRepository.getWorkersBySite(siteId)
    }

    fun addWorker(worker: Worker) {
        viewModelScope.launch {
            workerRepository.insertWorker(worker)
        }
    }

    fun updateWorker(worker: Worker) {
        viewModelScope.launch {
            workerRepository.updateWorker(worker)
        }
    }

    fun deleteWorker(worker: Worker) {
        viewModelScope.launch {
            workerRepository.deleteWorker(worker)
        }
    }

    suspend fun getWorkersBySiteSync(siteId: Long): List<Worker> {
        return workerRepository.getWorkersBySiteSync(siteId)
    }
}
