package com.dentalcare.app.ui.screens.dentists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dentalcare.app.domain.repository.DentistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel\nclass DentistsViewModel @Inject constructor(\n    private val dentistRepository: DentistRepository,\n) : ViewModel() {\n    private val _dentistsState = MutableStateFlow<DentistsState>(\n        DentistsState.Loading\n    )\n    val dentistsState: StateFlow<DentistsState> = _dentistsState\n\n    fun loadDentists() {\n        viewModelScope.launch {\n            try {\n                _dentistsState.value = DentistsState.Loading\n                val dentists = dentistRepository.getAllDentists()\n                _dentistsState.value = DentistsState.Success(\n                    dentists = dentists.map {\n                        DentistUI(\n                            id = it.id,\n                            name = it.name,\n                            specialty = it.specialty,\n                            rating = it.rating,\n                            reviews = it.reviews,\n                            initials = it.name.take(1).uppercase(),\n                        )\n                    },\n                )\n            } catch (e: Exception) {\n                _dentistsState.value = DentistsState.Error(\n                    message = e.message ?: \"Failed to load dentists\"\n                )\n            }\n        }\n    }\n}\n\nsealed class DentistsState {\n    data object Loading : DentistsState()\n    data class Success(val dentists: List<DentistUI>) : DentistsState()\n    data class Error(val message: String) : DentistsState()\n}\n\ndata class DentistUI(\n    val id: String,\n    val name: String,\n    val specialty: String,\n    val rating: Double,\n    val reviews: Int,\n    val initials: String,\n)\n"