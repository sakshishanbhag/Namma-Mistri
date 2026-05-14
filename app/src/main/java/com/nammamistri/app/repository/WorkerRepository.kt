package com.nammamistri.app.repository

import androidx.lifecycle.LiveData
import com.nammamistri.app.data.dao.WorkerDao
import com.nammamistri.app.data.entity.Worker

class WorkerRepository(private val workerDao: WorkerDao) {
    fun getWorkersBySite(siteId: Long): LiveData<List<Worker>> {
        return workerDao.getWorkersBySite(siteId)
    }

    suspend fun insertWorker(worker: Worker): Long {
        return workerDao.insertWorker(worker)
    }

    suspend fun updateWorker(worker: Worker) {
        workerDao.updateWorker(worker)
    }

    suspend fun deleteWorker(worker: Worker) {
        workerDao.deleteWorker(worker)
    }

    suspend fun getWorkerById(workerId: Long): Worker? {
        return workerDao.getWorkerById(workerId)
    }

    suspend fun getWorkersBySiteSync(siteId: Long): List<Worker> {
        return workerDao.getWorkersBySiteSync(siteId)
    }
}
