package com.example.outdoor.ui.halaman

import com.example.outdoor.R
import com.example.outdoor.navigasi.RouteMap

object RouteDetail : RouteMap {
    override val route = "item_details"
    override val tittleRes = R.string.data_pesanan
    const val sewaIdArg = "itemId"
    val routeWithArgs = "$route/{$sewaIdArg}"
}