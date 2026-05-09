package com.dentalcare.app.ui.screens.patients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dentalcare.app.domain.repository.PatientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientProfileViewModel @Inject constructor(\n    private val patientRepository: PatientRepository,\n) : ViewModel() {\n    private val _profileState = MutableStateFlow<PatientProfileState>(PatientProfileState.Loading)\n    val profileState: StateFlow<PatientProfileState> = _profileState\n\n    fun loadPatientProfile() {\n        viewModelScope.launch {\n            try {\n                _profileState.value = PatientProfileState.Loading\n                val patient = patientRepository.getCurrentPatient()\n                _profileState.value = PatientProfileState.Success(\n                    profile = PatientProfile(\n                        id = patient.id,\n                        name = patient.firstName + \" \" + patient.lastName,\n                        email = patient.email,\n                        phone = patient.phone,\n                        dateOfBirth = patient.dateOfBirth,\n                        allergies = patient.allergies,\n                        medications = patient.medications,\n                        medicalConditions = patient.medicalConditions,\n                    ),\n                )\n            } catch (e: Exception) {\n                _profileState.value = PatientProfileState.Error(\n                    message = e.message ?: \"Failed to load profile\"\n                )\n            }\n        }\n    }\n}\n\nsealed class PatientProfileState {\n    data object Loading : PatientProfileState()\n    data class Success(val profile: PatientProfile) : PatientProfileState()\n    data class Error(val message: String) : PatientProfileState()\n}\n\ndata class PatientProfile(\n    val id: String,\n    val name: String,\n    val email: String,\n    val phone: String,\n    val dateOfBirth: String,\n    val allergies: String,\n    val medications: String,\n    val medicalConditions: String,\n)\n"