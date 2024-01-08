package com.example.outdoor.ui.halaman

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.outdoor.R
import com.example.outdoor.data.Penyewa
import com.example.outdoor.model.HomeViewModel
import com.example.outdoor.model.PenyediaViewModel
import com.example.outdoor.navigasi.AdventureAppBar
import com.example.outdoor.navigasi.RouteMap

object RouteHome : RouteMap {
    override val route = "home"
    override val tittleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AdventureAppBar(
                title = stringResource(id = RouteHome.tittleRes),
                bisaNavigasiBack = false,
                navigasiUp = { /*TODO*/ })
        }, floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry, shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.addPesan_Button)
                )
            }
        }) { innerPadding ->
        val uiStateSewa by viewModel.homeUiState.collectAsState()
        HomeBody(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            itemSewa = uiStateSewa.listPenyewa,
            onCustClick = onDetailClick
        )


    }
}

@Composable
fun HomeBody(
    modifier: Modifier,
    itemSewa: List<Penyewa>,
    onCustClick: (Int) -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        if (itemSewa.isEmpty()) {
            Text(text = "Tidak ada data penyewaan", textAlign = TextAlign.Center)
        } else {
            ListPenyewa(
                itemSewa = itemSewa,
                onItemClick = { onCustClick(it.id) },
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }

    }
}

@Composable
fun ListPenyewa(
    itemSewa: List<Penyewa>,
    modifier: Modifier = Modifier,
    onItemClick: (Penyewa) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(items = itemSewa, key = { it.id }) { customer ->
            DataSewa(
                penyewa = customer,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onItemClick(customer) })
        }
    }
}

@Composable
fun DataSewa(penyewa: Penyewa, modifier: Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.nama_pemesan))
                Spacer(modifier = Modifier.weight(1f))
                Text(text = penyewa.nama)
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.nohp))
                Spacer(modifier = Modifier.weight(1f))
                Text(text = penyewa.nohp)
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.alamat))
                Spacer(modifier = Modifier.weight(1f))
                Text(text = penyewa.alamat)
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.pesanan))
                Spacer(modifier = Modifier.weight(1f))
                Text(text = penyewa.sewa)
            }
        }
    }
}