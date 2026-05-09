package com.dentalcare.app.domain.model

data class Patient(
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

data class Dentist(
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

data class Appointment(
    val id: String,
    val patientId: String,
    val dentistId: String,
    val appointmentDate: String,
    val appointmentTime: String,
    val status: String,
    val notes: String,
    val createdAt: String,
)

data class MedicalRecord(
    val id: String,
    val patientId: String,
    val title: String,
    val recordDate: String,
    val recordType: String,
    val description: String,
    val documentUrl: String,
    val createdAt: String,
)

data class Payment(
    val id: String,
    val patientId: String,
    val appointmentId: String,
    val invoiceNumber: String,
    val amount: Double,
    val paymentDate: String,
    val status: String,
    val method: String,
)
