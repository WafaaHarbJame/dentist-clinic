package com.dentalcare.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patients")
data class PatientEntity(
    @PrimaryKey
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val dateOfBirth: String,
    val gender: String,
    val address: String,
    val allergies: String,
    val medications: String,
    val medicalConditions: String,
    val createdAt: String,
)

@Entity(tableName = "dentists")
data class DentistEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val specialty: String,
    val experience: Int,
    val rating: Float,
    val reviews: Int,
    val imageUrl: String,
    val bio: String,
    val availableHours: String,
)

@Entity(tableName = "appointments")
data class AppointmentEntity(
    @PrimaryKey
    val id: String,
    val patientId: String,
    val dentistId: String,
    val appointmentDate: String,
    val appointmentTime: String,
    val status: String,
    val notes: String,
    val createdAt: String,
)

@Entity(tableName = "medical_records")
data class MedicalRecordEntity(
    @PrimaryKey
    val id: String,
    val patientId: String,
    val title: String,
    val recordDate: String,
    val recordType: String,
    val description: String,
    val documentUrl: String,
    val createdAt: String,
)

@Entity(tableName = "payments")
data class PaymentEntity(
    @PrimaryKey
    val id: String,
    val patientId: String,
    val appointmentId: String,
    val invoiceNumber: String,
    val amount: Double,
    val paymentDate: String,
    val status: String,
    val method: String,
)
