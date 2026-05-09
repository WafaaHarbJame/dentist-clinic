package com.dentalcare.app.ui.screens.appointments

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dentalcare.app.ui.theme.Dimension
import com.dentalcare.app.ui.components.LoadingState
import com.dentalcare.app.ui.components.ErrorState
import com.dentalcare.app.ui.components.EmptyState

@Composable
fun AppointmentsScreen(\n    navController: NavController,\n    viewModel: AppointmentsViewModel = hiltViewModel(),\n) {\n    val state by viewModel.appointmentsState.collectAsState()\n\n    LaunchedEffect(Unit) {\n        viewModel.loadAppointments()\n    }\n\n    Box(modifier = Modifier.fillMaxSize()) {\n        when (state) {\n            is AppointmentsState.Loading -> LoadingState()\n            is AppointmentsState.Error -> ErrorState(\n                message = (state as AppointmentsState.Error).message,\n                onRetry = { viewModel.loadAppointments() },\n            )\n            is AppointmentsState.Success -> {\n                val appointments = (state as AppointmentsState.Success).appointments\n                if (appointments.isEmpty()) {\n                    EmptyState(\n                        title = \"No Appointments\",\n                        message = \"You have no scheduled appointments\",\n                        onAction = { navController.navigate(\"book_appointment\") },\n                        actionLabel = \"Book Now\",\n                    )\n                } else {\n                    AppointmentsList(appointments = appointments)\n                }\n            }\n        }\n    }\n}\n\n@Composable\nprivate fun AppointmentsList(\n    appointments: List<AppointmentUI>,\n) {\n    LazyColumn(\n        modifier = Modifier.padding(Dimension.paddingMedium),\n        contentPadding = PaddingValues(bottom = Dimension.paddingXLarge),\n    ) {\n        item {\n            Text(\n                text = \"My Appointments\",\n                style = MaterialTheme.typography.headlineMedium,\n                fontWeight = FontWeight.Bold,\n                modifier = Modifier.padding(bottom = Dimension.paddingMedium),\n            )\n        }\n        items(appointments) { appointment ->\n            AppointmentItemCard(appointment = appointment)\n        }\n    }\n}\n\n@Composable\nprivate fun AppointmentItemCard(\n    appointment: AppointmentUI,\n) {\n    Card(\n        modifier = Modifier\n            .fillMaxWidth()\n            .padding(vertical = Dimension.paddingSmall),\n        colors = CardDefaults.cardColors(\n            containerColor = MaterialTheme.colorScheme.surface,\n        ),\n    ) {\n        Column(\n            modifier = Modifier.padding(Dimension.paddingMedium),\n        ) {\n            Text(\n                text = appointment.dentistName,\n                style = MaterialTheme.typography.titleMedium,\n                fontWeight = FontWeight.Bold,\n            )\n            Text(\n                text = appointment.type,\n                style = MaterialTheme.typography.bodySmall,\n                color = MaterialTheme.colorScheme.onSurfaceVariant,\n                modifier = Modifier.padding(top = Dimension.paddingSmall),\n            )\n            Text(\n                text = \"${appointment.date} at ${appointment.time}\",\n                style = MaterialTheme.typography.bodyMedium,\n                color = MaterialTheme.colorScheme.primary,\n                modifier = Modifier.padding(top = Dimension.paddingSmall),\n            )\n            Text(\n                text = \"Status: ${appointment.status}\",\n                style = MaterialTheme.typography.labelSmall,\n                color = getStatusColor(appointment.status),\n                modifier = Modifier.padding(top = Dimension.paddingSmall),\n            )\n            Button(\n                onClick = {},\n                modifier = Modifier\n                    .fillMaxWidth()\n                    .padding(top = Dimension.paddingMedium),\n            ) {\n                Text(\"Reschedule\")\n            }\n        }\n    }\n}\n\n@Composable\nprivate fun getStatusColor(status: String) = when (status) {\n    \"UPCOMING\" -> MaterialTheme.colorScheme.primary\n    \"COMPLETED\" -> MaterialTheme.colorScheme.secondary\n    \"CANCELLED\" -> MaterialTheme.colorScheme.error\n    else -> MaterialTheme.colorScheme.onSurfaceVariant\n}\n"