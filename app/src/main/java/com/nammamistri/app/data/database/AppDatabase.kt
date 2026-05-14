package com.nammamistri.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nammamistri.app.data.dao.AdvancePaymentDao
import com.nammamistri.app.data.dao.AttendanceDao
import com.nammamistri.app.data.dao.CalculationLogDao
import com.nammamistri.app.data.dao.SiteDao
import com.nammamistri.app.data.dao.SitePhotoDao
import com.nammamistri.app.data.dao.WorkerDao
import com.nammamistri.app.data.entity.AdvancePayment
import com.nammamistri.app.data.entity.Attendance
import com.nammamistri.app.data.entity.CalculationLog
import com.nammamistri.app.data.entity.Site
import com.nammamistri.app.data.entity.SitePhoto
import com.nammamistri.app.data.entity.Worker

@Database(
    entities = [
        Site::class,
        Worker::class,
        Attendance::class,
        AdvancePayment::class,
        CalculationLog::class,
        SitePhoto::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun siteDao(): SiteDao
    abstract fun workerDao(): WorkerDao
    abstract fun attendanceDao(): AttendanceDao
    abstract fun advancePaymentDao(): AdvancePaymentDao
    abstract fun calculationLogDao(): CalculationLogDao
    abstract fun sitePhotoDao(): SitePhotoDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "namma_mistri_db"
                ).build()
                instance = db
                db
            }
        }
    }
}
