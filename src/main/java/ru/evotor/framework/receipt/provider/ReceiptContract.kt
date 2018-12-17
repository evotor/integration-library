package ru.evotor.framework.receipt.provider

import android.net.Uri
import ru.evotor.framework.provider.FiscalDocumentColumns

object ReceiptContract {
    const val AUTHORITY = "ru.evotor.framework.receipt"

    val BASE_URI: Uri = Uri.parse("content://$AUTHORITY")

    const val PATH_FISCAL_RECEIPTS = "fiscal_receipts"

    object PositionColumns {
        const val UUID = "UUID"
        const val PRODUCT_UUID = "PRODUCT_UUID"
        const val TYPE = "TYPE"
        const val NAME = "NAME"
        const val PRODUCT_CODE = "PRODUCT_CODE"
        const val BARCODE = "BARCODE"
        const val MARK = "MARK"
        const val PRICE = "PRICE"
        const val QUANTITY = "QUANTITY"
        const val DISCOUNT = "DISCOUNT"
    }

    object SettlementMethodColumns {
        const val VARIATION_ID = "SETTLEMENT_METHOD_VARIATION_ID"

        const val VARIATION_ID_FULL_PREPAYMENT = 0
        const val VARIATION_ID_PARTIAL_PREPAYMENT = 1
        const val VARIATION_ID_ADVANCE_PAYMENT = 2
        const val VARIATION_ID_FULL_SETTLEMENT = 3
        const val VARIATION_ID_PARTIAL_SETTLEMENT = 4
        const val VARIATION_ID_LEND = 5
        const val VARIATION_ID_LOAN_PAYMENT = 6

        const val AMOUNT = "SETTLEMENT_METHOD_AMOUNT"
    }

    object AgentRequisitesColumns {
        const val AGENT_IS_NULL = "AGENT_IS_NULL"
        const val AGENT_UUID = "AGENT_UUID"
        const val AGENT_TYPE = "AGENT_TYPE"
        const val AGENT_COUNTERPARTY_TYPE = "AGENT_COUNTERPARTY_TYPE"
        const val AGENT_FULL_NAME = "AGENT_FULL_NAME"
        const val AGENT_SHORT_NAME = "AGENT_SHORT_NAME"
        const val AGENT_INN = "AGENT_INN"
        const val AGENT_KPP = "AGENT_KPP"
        const val AGENT_PHONES = "AGENT_PHONES"
        const val AGENT_ADDRESSES = "AGENT_ADDRESSES"

        const val SUBAGENT_UUID = "SUBAGENT_UUID"
        const val SUBAGENT_TYPE = "SUBAGENT_TYPE"
        const val SUBAGENT_COUNTERPARTY_TYPE = "SUBAGENT_COUNTERPARTY_TYPE"
        const val SUBAGENT_FULL_NAME = "SUBAGENT_FULL_NAME"
        const val SUBAGENT_SHORT_NAME = "SUBAGENT_SHORT_NAME"
        const val SUBAGENT_INN = "SUBAGENT_INN"
        const val SUBAGENT_KPP = "SUBAGENT_KPP"
        const val SUBAGENT_PHONES = "SUBAGENT_PHONES"
        const val SUBAGENT_ADDRESSES = "SUBAGENT_ADDRESSES"

        const val PRINCIPAL_UUID = "PRINCIPAL_UUID"
        const val PRINCIPAL_COUNTERPARTY_TYPE = "PRINCIPAL_COUNTERPARTY_TYPE"
        const val PRINCIPAL_FULL_NAME = "PRINCIPAL_FULL_NAME"
        const val PRINCIPAL_SHORT_NAME = "PRINCIPAL_SHORT_NAME"
        const val PRINCIPAL_INN = "PRINCIPAL_INN"
        const val PRINCIPAL_KPP = "PRINCIPAL_KPP"
        const val PRINCIPAL_PHONES = "PRINCIPAL_PHONES"
        const val PRINCIPAL_ADDRESSES = "PRINCIPAL_ADDRESSES"

        const val TRANSACTION_OPERATOR_IS_NULL = "TRANSACTION_OPERATOR_IS_NULL"
        const val TRANSACTION_OPERATOR_UUID = "TRANSACTION_OPERATOR_UUID"
        const val TRANSACTION_OPERATOR_COUNTERPARTY_TYPE = "TRANSACTION_OPERATOR_COUNTERPARTY_TYPE"
        const val TRANSACTION_OPERATOR_FULL_NAME = "TRANSACTION_OPERATOR_FULL_NAME"
        const val TRANSACTION_OPERATOR_SHORT_NAME = "TRANSACTION_OPERATOR_SHORT_NAME"
        const val TRANSACTION_OPERATOR_INN = "TRANSACTION_OPERATOR_INN"
        const val TRANSACTION_OPERATOR_KPP = "TRANSACTION_OPERATOR_KPP"
        const val TRANSACTION_OPERATOR_PHONES = "TRANSACTION_OPERATOR_PHONES"
        const val TRANSACTION_OPERATOR_ADDRESSES = "TRANSACTION_OPERATOR_ADDRESSES"
        const val OPERATION_DESCRIPTION = "OPERATION_DESCRIPTION"
    }

    object FiscalReceiptColumns : FiscalDocumentColumns{
        const val SETTLEMENT_TYPE = "SETTLEMENT_TYPE"
    }
}