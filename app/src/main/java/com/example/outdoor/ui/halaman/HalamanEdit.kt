package com.example.outdoor.ui.halaman

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.outdoor.R
import com.example.outdoor.model.EditViewModel
import com.example.outdoor.model.PenyediaViewModel
import com.example.outdoor.navigasi.AdventureAppBar
import com.example.outdoor.navigasi.RouteMap
import kotlinx.coroutines.launch

object RouteEdit : RouteMap {
    override val route = "item_edit"
    override val tittleRes = R.string.edit_pesanan
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            AdventureAppBar(
                title = stringResource(RouteEdit.tittleRes),
                bisaNavigasiBack = true,
                navigasiUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryPesananBody(
            uiStatePenyewa = viewModel.sewaUiState,
            onPenyewaValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updatePenyewa()
                    navigateBack()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )

    }
}