package com.example.appbusiness.presentation.business.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BusinessDetailScreen(
    navController: NavController,
    viewModel: BusinessDetailViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is BusinessDetailViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        event.message
                    )
                }

                is BusinessDetailViewModel.UiEvent.SaveBusiness -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Crear un nuevo negocio",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(BusinessDetailEvent.SaveBusiness)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save business")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(
                        text = "Nombre",
                        color = Color.DarkGray
                    )
                },
                value = state.name,
                onValueChange = {
                    viewModel.onEvent(
                        BusinessDetailEvent.OnValueChange(
                            it,
                            BusinessDetailViewModel.Companion.Fields.NAME
                        )
                    )
                },
                textStyle = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(
                        text = "Teléfono",
                        color = Color.DarkGray
                    )
                },
                value = state.phone,
                onValueChange = {
                    viewModel.onEvent(
                        BusinessDetailEvent.OnValueChange(
                            it,
                            BusinessDetailViewModel.Companion.Fields.PHONE
                        )
                    )
                },
                textStyle = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(
                        text = "Acerca de",
                        color = Color.DarkGray
                    )
                },
                value = state.about,
                onValueChange = {
                    viewModel.onEvent(
                        BusinessDetailEvent.OnValueChange(
                            it,
                            BusinessDetailViewModel.Companion.Fields.ABOUT
                        )
                    )
                },
                textStyle = MaterialTheme.typography.body1
            )
        }
    }
}
