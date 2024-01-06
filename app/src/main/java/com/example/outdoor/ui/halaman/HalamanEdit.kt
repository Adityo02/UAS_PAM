package com.example.outdoor.ui.halaman

import com.example.outdoor.R
import com.example.outdoor.navigasi.RouteMap

object RouteEdit : RouteMap {
    override val route = "item_edit"
    override val tittleRes = R.string.edit_pesanan
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}