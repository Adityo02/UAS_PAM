package com.example.outdoor.ui.halaman

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.outdoor.R
import com.example.outdoor.data.Penyewa
import com.example.outdoor.model.DetailViewModel
import com.example.outdoor.model.ItemDetailUiState
import com.example.outdoor.model.PenyediaViewModel
import com.example.outdoor.model.toPenyewa
import com.example.outdoor.navigasi.AdventureAppBar
import com.example.outdoor.navigasi.RouteMap
import kotlinx.coroutines.launch

object RouteDetail : RouteMap {
    override val route = "item_details"
    override val tittleRes = R.string.data_pesanan
    const val sewaIdArg = "itemId"
    val routeWithArgs = "$route/{$sewaIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navigateToEditItem: (Int) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            AdventureAppBar(
                title = stringResource(RouteDetail.tittleRes),
                bisaNavigasiBack = true,
                navigasiUp = navigateBack
            )
        }, modifier = modifier
    ) { innerPadding ->
        ItemDetailsBody(
            itemDetailUiState = uiState.value,
            onDelete = {
                coroutineScope.launch {
                    viewModel.deleteItem()
                    navigateBack()
                }
            },
            navigateToItemEdit = { navigateToEditItem(uiState.value.penyewaBio.id) },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(
                    rememberScrollState()
                ),

            )
    }
}

@Composable
private fun ItemDetailsBody(
    itemDetailUiState: ItemDetailUiState,
    onDelete: () -> Unit,
    navigateToItemEdit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
        ItemDetails(
            penyewa = itemDetailUiState.penyewaBio.toPenyewa(),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = navigateToItemEdit, shape = MaterialTheme.shapes.small

            ) {
                Text(text = "Update")
            }
            OutlinedButton(
                onClick = { deleteConfirmationRequired = true },
                shape = MaterialTheme.shapes.small

            ) {
                Text("Hapus")
            }
            if (deleteConfirmationRequired) {
                DeleteDialog(
                    onDeleteConfirm = {
                        deleteConfirmationRequired = false
                        onDelete()
                    },
                    onDeleteCancel = { deleteConfirmationRequired = false },
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
private fun ItemDetails(
    penyewa: Penyewa,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier, colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ItemDetailRow(
                labelResID = R.string.nama_pemesan,
                itemDetail = penyewa.nama,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            ItemDetailRow(
                labelResID = R.string.nohp,
                itemDetail = penyewa.nohp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            ItemDetailRow(
                labelResID = R.string.alamat,
                itemDetail = penyewa.alamat,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            ItemDetailRow(
                labelResID = R.string.pesanan,
                itemDetail = penyewa.sewa,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

    }
}

@Composable
private fun ItemDetailRow(
    @StringRes labelResID: Int, itemDetail: String, modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(text = stringResource(labelResID))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = itemDetail, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun DeleteDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        title = { Text(text = "Perhatian!") },
        text = { Text(text = "Yakin ingin hapus data?") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "Tidak")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Iya")
            }
        }
    )
}