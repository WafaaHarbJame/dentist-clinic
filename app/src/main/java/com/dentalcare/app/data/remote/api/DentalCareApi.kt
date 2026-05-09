package com.dentalcare.app.data.remote.api

import com.dentalcare.app.data.remote.dto.PatientDto
import com.dentalcare.app.data.remote.dto.DentistDto
import com.dentalcare.app.data.remote.dto.AppointmentDto
import com.dentalcare.app.data.remote.dto.MedicalRecordDto
import com.dentalcare.app.data.remote.dto.PaymentDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface DentalCareApi {

    // Patient endpoints
    @GET("patients/{id}")
    suspend fun getPatientById(@Path("id") patientId: String): PatientDto

    @POST("patients")
    suspend fun createPatient(@Body patient: PatientDto): PatientDto

    @PUT("patients/{id}")
    suspend fun updatePatient(
        @Path("id") patientId: String,
        @Body patient: PatientDto
    ): PatientDto

    // Dentist endpoints
    @GET("dentists")
    suspend fun getAllDentists(): List<DentistDto>

    @GET("dentists/{id}")
    suspend fun getDentistById(@Path("id") dentistId: String): DentistDto

    @GET("dentists")
    suspend fun getDentistsBySpecialty(@Query("specialty") specialty: String): List<DentistDto>

    // Appointment endpoints
    @GET("appointments")
    suspend fun getAllAppointments(): List<AppointmentDto>

    @GET("appointments/upcoming")
    suspend fun getUpcomingAppointments(): List<AppointmentDto>

    @POST("appointments")
    suspend fun createAppointment(@Body appointment: AppointmentDto): AppointmentDto

    @PUT("appointments/{id}")
    suspend fun updateAppointment(
        @Path("id") appointmentId: String,
        @Body appointment: AppointmentDto
    ): AppointmentDto

    @DELETE("appointments/{id}")
    suspend fun deleteAppointment(@Path("id") appointmentId: String)

    // Medical Records endpoints
    @GET("medical-records")
    suspend fun getMedicalRecords(): List<MedicalRecordDto>

    @POST("medical-records")
    suspend fun createMedicalRecord(@Body record: MedicalRecordDto): MedicalRecordDto

    // Payment endpoints
    @GET("payments")
    suspend fun getPayments(): List<PaymentDto>

    @POST("payments")
    suspend fun createPayment(@Body payment: PaymentDto): PaymentDto

    @GET("invoices/total")
    suspend fun getTotalInvoices(): Map<String, Any>
}
