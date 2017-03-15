package ru.evotor.integrations

import android.net.Uri
import java.math.BigDecimal

/**
 * Created by nixan on 06.03.17.
 */

val INVENTORY_URI: Uri = Uri.parse("content://ru.evotor.evotorpos.inventory/Commodity")

data class Product(val uuid: String, val productName: String, val price: BigDecimal, val quantity: BigDecimal, val description: String?)