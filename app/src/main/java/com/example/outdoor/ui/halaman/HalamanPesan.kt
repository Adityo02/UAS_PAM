package com.example.outdoor.ui.halaman

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.outdoor.R
import com.example.outdoor.model.EntryViewModel
import com.example.outdoor.model.PenyediaViewModel
import com.example.outdoor.model.PenyewaBio
import com.example.outdoor.model.UIStatePenyewa
import com.example.outdoor.navigasi.AdventureAppBar
import com.example.outdoor.navigasi.RouteMap
import kotlinx.coroutines.launch

object RouteEntry : RouteMap {
    override val route = "item_entry"
    override val tittleRes = R.string.form_pesan
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryPesananScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EntryViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AdventureAppBar(
                title = stringResource(id = RouteEntry.tittleRes),
                bisaNavigasiBack = true,
                navigasiUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryPesananBody(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            uiStatePenyewa = viewModel.uiStatePenyewa,
            onPenyewaValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.savePenyewa()
                    navigateBack()
                }
            }
        )
    }
}

@Composable
fun EntryPesananBody(
    uiStatePenyewa: UIStatePenyewa,
    onPenyewaValueChange: (PenyewaBio) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        FormInputPesanan(
            penyewaBio = uiStatePenyewa.penyewaBio,
            onValueChange = onPenyewaValueChange,
            pilihanSewa = listOf("Jaket", "Tenda", "Sepatu", "Sleeping Bag"),
            onSelectionChanged = { uiStatePenyewa.penyewaBio.sewa }
        )
        Button(
            onClick = onSaveClick,
            enabled = uiStatePenyewa.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "CHECKOUT")
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputPesanan(
    penyewaBio: PenyewaBio,
    modifier: Modifier = Modifier,
    onValueChange: (PenyewaBio) -> Unit = {},
    enabled: Boolean = true,
    pilihanSewa: List<String>,
    onSelectionChanged: (String) -> Unit

) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = modifier.padding(top = 24.dp))
        OutlinedTextField(
            value = penyewaBio.nama,
            onValueChange = { onValueChange(penyewaBio.copy(nama = it)) },
            label = { Text(text = "Nama Pemesan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = penyewaBio.alamat,
            onValueChange = { onValueChange(penyewaBio.copy(alamat = it)) },
            label = { Text(text = "Alamat Pemesan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = penyewaBio.nohp,
            onValueChange = { onValueChange(penyewaBio.copy(nohp = it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(text = "Kontak Pemesan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        Column(modifier.fillMaxWidth()) {
            pilihanSewa.forEach { item ->
                Row(modifier = Modifier.selectable(
                    selected = penyewaBio.sewa == item,
                    onClick = {
                        onValueChange(penyewaBio.copy(sewa = item))
                        onSelectionChanged(item)
                    }
                ),
                    verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = penyewaBio.sewa == item,
                        onClick = {
                            onValueChange(penyewaBio.copy(sewa = item))
                            onSelectionChanged(item)
                        }
                    )
                    Text(item)
                }
            }
        }
    }
}