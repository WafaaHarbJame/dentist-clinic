package com.dentalcare.app.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dentalcare.app.data.local.entity.PatientEntity
import com.dentalcare.app.data.local.entity.DentistEntity
import com.dentalcare.app.data.local.entity.AppointmentEntity
import com.dentalcare.app.data.local.entity.MedicalRecordEntity
import com.dentalcare.app.data.local.entity.PaymentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {
    @Query("SELECT * FROM patients WHERE id = :patientId")
    suspend fun getPatientById(patientId: String): PatientEntity?

    @Query("SELECT * FROM patients LIMIT 1")
    suspend fun getCurrentPatient(): PatientEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPatient(patient: PatientEntity)

    @Update
    suspend fun updatePatient(patient: PatientEntity)

    @Delete
    suspend fun deletePatient(patient: PatientEntity)

    @Query("SELECT * FROM patients")
    fun getAllPatientsFlow(): Flow<List<PatientEntity>>
}

@Dao
interface DentistDao {
    @Query("SELECT * FROM dentists WHERE id = :dentistId")
    suspend fun getDentistById(dentistId: String): DentistEntity?

    @Query("SELECT * FROM dentists")
    suspend fun getAllDentists(): List<DentistEntity>

    @Query("SELECT * FROM dentists WHERE specialty = :specialty")
    suspend fun getDentistsBySpecialty(specialty: String): List<DentistEntity>

    @Query("SELECT * FROM dentists")
    fun getAllDentistsFlow(): Flow<List<DentistEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDentist(dentist: DentistEntity)

    @Update
    suspend fun updateDentist(dentist: DentistEntity)

    @Delete
    suspend fun deleteDentist(dentist: DentistEntity)
}

@Dao
interface AppointmentDao {
    @Query("SELECT * FROM appointments WHERE id = :appointmentId")
    suspend fun getAppointmentById(appointmentId: String): AppointmentEntity?

    @Query("SELECT * FROM appointments WHERE patientId = :patientId ORDER BY appointmentDate DESC")
    suspend fun getAppointmentsByPatient(patientId: String): List<AppointmentEntity>

    @Query("SELECT * FROM appointments WHERE status = :status ORDER BY appointmentDate DESC")
    suspend fun getAppointmentsByStatus(status: String): List<AppointmentEntity>

    @Query("SELECT * FROM appointments ORDER BY appointmentDate DESC")
    fun getAllAppointmentsFlow(): Flow<List<AppointmentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppointment(appointment: AppointmentEntity)

    @Update
    suspend fun updateAppointment(appointment: AppointmentEntity)

    @Delete
    suspend fun deleteAppointment(appointment: AppointmentEntity)

    @Query("DELETE FROM appointments WHERE id = :appointmentId")
    suspend fun deleteAppointmentById(appointmentId: String)
}

@Dao
interface MedicalRecordDao {
    @Query("SELECT * FROM medical_records WHERE id = :recordId")
    suspend fun getRecordById(recordId: String): MedicalRecordEntity?

    @Query("SELECT * FROM medical_records WHERE patientId = :patientId ORDER BY recordDate DESC")
    suspend fun getRecordsByPatient(patientId: String): List<MedicalRecordEntity>

    @Query("SELECT * FROM medical_records")
    suspend fun getAllRecords(): List<MedicalRecordEntity>

    @Query("SELECT * FROM medical_records")
    fun getAllRecordsFlow(): Flow<List<MedicalRecordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: MedicalRecordEntity)

    @Update
    suspend fun updateRecord(record: MedicalRecordEntity)

    @Delete
    suspend fun deleteRecord(record: MedicalRecordEntity)
}

@Dao
interface PaymentDao {
    @Query("SELECT * FROM payments WHERE id = :paymentId")
    suspend fun getPaymentById(paymentId: String): PaymentEntity?

    @Query("SELECT * FROM payments WHERE patientId = :patientId ORDER BY paymentDate DESC")
    suspend fun getPaymentsByPatient(patientId: String): List<PaymentEntity>

    @Query("SELECT * FROM payments")
    suspend fun getAllPayments(): List<PaymentEntity>

    @Query("SELECT * FROM payments")
    fun getAllPaymentsFlow(): Flow<List<PaymentEntity>>

    @Query("SELECT SUM(amount) FROM payments WHERE status = 'paid'")
    suspend fun getTotalRevenue(): Double

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPayment(payment: PaymentEntity)

    @Update
    suspend fun updatePayment(payment: PaymentEntity)

    @Delete
    suspend fun deletePayment(payment: PaymentEntity)
}
