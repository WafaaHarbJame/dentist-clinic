package com.dentalcare.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dentalcare.app.data.local.dao.PatientDao
import com.dentalcare.app.data.local.dao.DentistDao
import com.dentalcare.app.data.local.dao.AppointmentDao
import com.dentalcare.app.data.local.dao.MedicalRecordDao
import com.dentalcare.app.data.local.dao.PaymentDao
import com.dentalcare.app.data.local.entity.PatientEntity
import com.dentalcare.app.data.local.entity.DentistEntity
import com.dentalcare.app.data.local.entity.AppointmentEntity
import com.dentalcare.app.data.local.entity.MedicalRecordEntity
import com.dentalcare.app.data.local.entity.PaymentEntity

@Database(
    entities = [
        PatientEntity::class,
        DentistEntity::class,
        AppointmentEntity::class,
        MedicalRecordEntity::class,
        PaymentEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class DentalCareDatabase : RoomDatabase() {
    abstract fun patientDao(): PatientDao
    abstract fun dentistDao(): DentistDao
    abstract fun appointmentDao(): AppointmentDao
    abstract fun medicalRecordDao(): MedicalRecordDao
    abstract fun paymentDao(): PaymentDao
}
