package ru.evotor.framework.core.action.datamapper

import android.os.Bundle
import java.math.BigDecimal

internal class CashOperationEventMapper(private val bundle: Bundle) {

    fun getDocumentUuid(): String? = bundle.getString(KEY_DOCUMENT_UUID)

    fun getTotal(): BigDecimal? = bundle.getMoney(KEY_TOTAL)

    companion object {

        private const val KEY_DOCUMENT_UUID = "documentUuid"

        private const val KEY_TOTAL = "total"

    }

}