package ru.evotor.framework.receipt.provider

import android.net.Uri
import ru.evotor.framework.provider.FiscalDocumentContract

internal object FiscalReceiptContract {
    private const val PATH = "receipt"

    val URI: Uri = Uri.withAppendedPath(FiscalDocumentContract.URI, PATH)

    const val COLUMN_SETTLEMENT_TYPE = "SETTLEMENT_TYPE"
}