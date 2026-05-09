package com.dentalcare.app.di

import android.content.Context
import androidx.room.Room
import com.dentalcare.app.data.local.database.DentalCareDatabase
import com.dentalcare.app.data.remote.api.DentalCareApi
import com.dentalcare.app.data.repository.AppointmentRepositoryImpl
import com.dentalcare.app.data.repository.DentistRepositoryImpl
import com.dentalcare.app.data.repository.MedicalRecordRepositoryImpl
import com.dentalcare.app.data.repository.PatientRepositoryImpl
import com.dentalcare.app.data.repository.PaymentRepositoryImpl
import com.dentalcare.app.data.repository.SettingsRepositoryImpl
import com.dentalcare.app.domain.repository.AppointmentRepository
import com.dentalcare.app.domain.repository.DentistRepository
import com.dentalcare.app.domain.repository.MedicalRecordRepository
import com.dentalcare.app.domain.repository.PatientRepository
import com.dentalcare.app.domain.repository.PaymentRepository
import com.dentalcare.app.domain.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.dentalcare.com/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Provides
    @Singleton
    fun provideDentalCareApi(
        okHttpClient: OkHttpClient,
        json: Json,
    ): DentalCareApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(DentalCareApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDentalCareDatabase(
        @ApplicationContext context: Context,
    ): DentalCareDatabase {
        return Room.databaseBuilder(
            context,
            DentalCareDatabase::class.java,
            "dental_care_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePatientRepository(
        api: DentalCareApi,
        database: DentalCareDatabase,
    ): PatientRepository = PatientRepositoryImpl(api, database.patientDao())

    @Provides
    @Singleton
    fun provideDentistRepository(
        api: DentalCareApi,
        database: DentalCareDatabase,
    ): DentistRepository = DentistRepositoryImpl(api, database.dentistDao())

    @Provides
    @Singleton
    fun provideAppointmentRepository(
        api: DentalCareApi,
        database: DentalCareDatabase,
    ): AppointmentRepository = AppointmentRepositoryImpl(api, database.appointmentDao())

    @Provides
    @Singleton
    fun provideMedicalRecordRepository(
        api: DentalCareApi,
        database: DentalCareDatabase,
    ): MedicalRecordRepository = MedicalRecordRepositoryImpl(api, database.medicalRecordDao())

    @Provides
    @Singleton
    fun providePaymentRepository(
        api: DentalCareApi,
        database: DentalCareDatabase,
    ): PaymentRepository = PaymentRepositoryImpl(api, database.paymentDao())

    @Provides
    @Singleton
    fun provideSettingsRepository(
        @ApplicationContext context: Context,
    ): SettingsRepository = SettingsRepositoryImpl(context)
}
