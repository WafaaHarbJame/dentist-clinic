package com.dentalcare.app.ui.screens.appointments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dentalcare.app.domain.repository.AppointmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel\nclass AppointmentsViewModel @Inject constructor(\n    private val appointmentRepository: AppointmentRepository,\n) : ViewModel() {\n    private val _appointmentsState = MutableStateFlow<AppointmentsState>(\n        AppointmentsState.Loading\n    )\n    val appointmentsState: StateFlow<AppointmentsState> = _appointmentsState\n\n    fun loadAppointments() {\n        viewModelScope.launch {\n            try {\n                _appointmentsState.value = AppointmentsState.Loading\n                val appointments = appointmentRepository.getAppointments()\n                _appointmentsState.value = AppointmentsState.Success(\n                    appointments = appointments.map {\n                        AppointmentUI(\n                            id = it.id,\n                            dentistName = \"Dr. Smith\",\n                            type = \"General Checkup\",\n                            date = it.appointmentDate,\n                            time = it.appointmentTime,\n                            status = it.status,\n                        )\n                    },\n                )\n            } catch (e: Exception) {\n                _appointmentsState.value = AppointmentsState.Error(\n                    message = e.message ?: \"Failed to load appointments\"\n                )\n            }\n        }\n    }\n}\n\nsealed class AppointmentsState {\n    data object Loading : AppointmentsState()\n    data class Success(val appointments: List<AppointmentUI>) : AppointmentsState()\n    data class Error(val message: String) : AppointmentsState()\n}\n\ndata class AppointmentUI(\n    val id: String,\n    val dentistName: String,\n    val type: String,\n    val date: String,\n    val time: String,\n    val status: String,\n)\n"