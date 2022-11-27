package com.example.appbusiness.presentation.business.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.appbusiness.ui.components.AlertDialogRating
import com.example.appbusiness.ui.components.BusinessItem
import kotlinx.coroutines.launch

@Composable
fun BusinessMainScreen(
    navController: NavController,
    viewModel: BusinessMainViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Listado de Negocio",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("detail")
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Business"
                )
            }
        },
        scaffoldState = scaffoldState
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.business) { business ->
                BusinessItem(
                    business = business,
                    onDeleteBusiness = {
                        viewModel.onEvent(BusinessMainEvent.DeleteBusiness(business))
                        scope.launch {
                            val result = scaffoldState.snackbarHostState.showSnackbar(
                                message = "Negocio eliminado",
                                actionLabel = "Deshacer"
                            )
                            if (result == SnackbarResult.ActionPerformed) {
                                viewModel.onEvent(BusinessMainEvent.RestoreBusinessDeleted)
                            }
                        }
                    },
                    onRatingBusiness = {
                        viewModel.onEvent(
                            BusinessMainEvent.OnPopupDismissed(
                                open = true,
                                business = business
                            )
                        )
                    }
                )
            }
        }

        if (state.openDialogRating) {
            AlertDialogRating(
                rating = state.rating,
                onValueChange = {
                    viewModel.onEvent(BusinessMainEvent.OnValueChangeRating(it))
                },
                onPopupDismissed = {
                    viewModel.onEvent(BusinessMainEvent.OnPopupDismissed(false))
                },
                onSaveRating = {
                    viewModel.onEvent(BusinessMainEvent.SaveRating)
                    viewModel.onEvent(BusinessMainEvent.OnPopupDismissed(false))
                }
            )
        }
    }
}