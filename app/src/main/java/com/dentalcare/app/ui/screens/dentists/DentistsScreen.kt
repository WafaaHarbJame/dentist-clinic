package com.dentalcare.app.ui.screens.dentists

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dentalcare.app.ui.theme.Dimension
import com.dentalcare.app.ui.components.LoadingState
import com.dentalcare.app.ui.components.ErrorState
import com.dentalcare.app.ui.components.EmptyState

@Composable
fun DentistsScreen(\n    navController: NavController,\n    viewModel: DentistsViewModel = hiltViewModel(),\n) {\n    val state by viewModel.dentistsState.collectAsState()\n\n    LaunchedEffect(Unit) {\n        viewModel.loadDentists()\n    }\n\n    Box(modifier = Modifier.fillMaxSize()) {\n        when (state) {\n            is DentistsState.Loading -> LoadingState()\n            is DentistsState.Error -> ErrorState(\n                message = (state as DentistsState.Error).message,\n                onRetry = { viewModel.loadDentists() },\n            )\n            is DentistsState.Success -> {\n                val dentists = (state as DentistsState.Success).dentists\n                if (dentists.isEmpty()) {\n                    EmptyState(\n                        title = \"No Dentists\",\n                        message = \"No dentists available right now\",\n                    )\n                } else {\n                    DentistsList(dentists = dentists)\n                }\n            }\n        }\n    }\n}\n\n@Composable\nprivate fun DentistsList(\n    dentists: List<DentistUI>,\n) {\n    LazyColumn(\n        modifier = Modifier.padding(Dimension.paddingMedium),\n        contentPadding = PaddingValues(bottom = Dimension.paddingXLarge),\n    ) {\n        item {\n            Text(\n                text = \"Our Dentists\",\n                style = MaterialTheme.typography.headlineMedium,\n                fontWeight = FontWeight.Bold,\n                modifier = Modifier.padding(bottom = Dimension.paddingMedium),\n            )\n        }\n        items(dentists) { dentist ->\n            DentistCard(dentist = dentist)\n        }\n    }\n}\n\n@Composable\nprivate fun DentistCard(\n    dentist: DentistUI,\n) {\n    Card(\n        modifier = Modifier\n            .fillMaxWidth()\n            .padding(vertical = Dimension.paddingSmall),\n        colors = CardDefaults.cardColors(\n            containerColor = MaterialTheme.colorScheme.surface,\n        ),\n    ) {\n        Row(\n            modifier = Modifier\n                .fillMaxWidth()\n                .padding(Dimension.paddingMedium),\n            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(\n                Dimension.paddingMedium\n            ),\n            verticalAlignment = Alignment.Top,\n        ) {\n            Card(\n                modifier = Modifier\n                    .size(Dimension.paddingXLarge + Dimension.paddingLarge)\n                    .clip(CircleShape),\n                colors = CardDefaults.cardColors(\n                    containerColor = MaterialTheme.colorScheme.secondary,\n                ),\n            ) {\n                Box(\n                    modifier = Modifier.fillMaxSize(),\n                    contentAlignment = Alignment.Center,\n                ) {\n                    Text(\n                        text = dentist.initials,\n                        style = MaterialTheme.typography.titleLarge,\n                        fontWeight = FontWeight.Bold,\n                        color = MaterialTheme.colorScheme.onSecondary,\n                    )\n                }\n            }\n            Column(modifier = Modifier.weight(1f)) {\n                Text(\n                    text = dentist.name,\n                    style = MaterialTheme.typography.titleMedium,\n                    fontWeight = FontWeight.Bold,\n                )\n                Text(\n                    text = dentist.specialty,\n                    style = MaterialTheme.typography.bodySmall,\n                    color = MaterialTheme.colorScheme.onSurfaceVariant,\n                    modifier = Modifier.padding(top = Dimension.paddingSmall),\n                )\n                Row(\n                    modifier = Modifier.padding(top = Dimension.paddingSmall),\n                    verticalAlignment = Alignment.CenterVertically,\n                ) {\n                    Icon(\n                        Icons.Default.Star,\n                        contentDescription = null,\n                        tint = MaterialTheme.colorScheme.secondary,\n                        modifier = Modifier.size(Dimension.iconSmall),\n                    )\n                    Text(\n                        text = \"${dentist.rating} (${dentist.reviews} reviews)\",\n                        style = MaterialTheme.typography.labelSmall,\n                        modifier = Modifier.padding(start = Dimension.paddingSmall),\n                    )\n                }\n            }\n        }\n    }\n}\n"