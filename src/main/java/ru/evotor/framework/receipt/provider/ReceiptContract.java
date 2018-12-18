package ru.evotor.framework.receipt.provider;

import android.net.Uri;

import ru.evotor.framework.provider.FiscalDocumentColumns;
import ru.evotor.framework.provider.IdentifiedEntityColumns;
import ru.evotor.framework.provider.MultiVariationEntityColumns;

public final class ReceiptContract {
    private ReceiptContract() {
    }

    public static final String AUTHORITY = "ru.evotor.framework.receipt";

    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_FISCAL_RECEIPTS = "fiscal_receipts";

    public interface PositionColumns extends IdentifiedEntityColumns {
        String PRODUCT_UUID = "PRODUCT_UUID";
        String TYPE = "TYPE";
        String NAME = "NAME";
        String PRODUCT_CODE = "PRODUCT_CODE";
        String BARCODE = "BARCODE";
        String MARK = "MARK";
        String PRICE = "PRICE";
        String QUANTITY = "QUANTITY";
        String DISCOUNT = "DISCOUNT";
    }

    public interface SettlementMethodColumns extends MultiVariationEntityColumns {
        String VARIATION_ID = "SETTLEMENT_METHOD_VARIATION_ID";

        int VARIATION_ID_FULL_PREPAYMENT = 0;
        int VARIATION_ID_PARTIAL_PREPAYMENT = 1;
        int VARIATION_ID_ADVANCE_PAYMENT = 2;
        int VARIATION_ID_FULL_SETTLEMENT = 3;
        int VARIATION_ID_PARTIAL_SETTLEMENT = 4;
        int VARIATION_ID_LEND = 5;
        int VARIATION_ID_LOAN_PAYMENT = 6;

        String AMOUNT = "SETTLEMENT_METHOD_AMOUNT";
    }

    public interface AgentRequisitesColumns {
        String AGENT_IS_NULL = "AGENT_IS_NULL";
        String AGENT_UUID = "AGENT_UUID";
        String AGENT_TYPE = "AGENT_TYPE";
        String AGENT_COUNTERPARTY_TYPE = "AGENT_COUNTERPARTY_TYPE";
        String AGENT_FULL_NAME = "AGENT_FULL_NAME";
        String AGENT_SHORT_NAME = "AGENT_SHORT_NAME";
        String AGENT_INN = "AGENT_INN";
        String AGENT_KPP = "AGENT_KPP";
        String AGENT_PHONES = "AGENT_PHONES";
        String AGENT_ADDRESSES = "AGENT_ADDRESSES";

        String SUBAGENT_UUID = "SUBAGENT_UUID";
        String SUBAGENT_TYPE = "SUBAGENT_TYPE";
        String SUBAGENT_COUNTERPARTY_TYPE = "SUBAGENT_COUNTERPARTY_TYPE";
        String SUBAGENT_FULL_NAME = "SUBAGENT_FULL_NAME";
        String SUBAGENT_SHORT_NAME = "SUBAGENT_SHORT_NAME";
        String SUBAGENT_INN = "SUBAGENT_INN";
        String SUBAGENT_KPP = "SUBAGENT_KPP";
        String SUBAGENT_PHONES = "SUBAGENT_PHONES";
        String SUBAGENT_ADDRESSES = "SUBAGENT_ADDRESSES";

        String PRINCIPAL_UUID = "PRINCIPAL_UUID";
        String PRINCIPAL_COUNTERPARTY_TYPE = "PRINCIPAL_COUNTERPARTY_TYPE";
        String PRINCIPAL_FULL_NAME = "PRINCIPAL_FULL_NAME";
        String PRINCIPAL_SHORT_NAME = "PRINCIPAL_SHORT_NAME";
        String PRINCIPAL_INN = "PRINCIPAL_INN";
        String PRINCIPAL_KPP = "PRINCIPAL_KPP";
        String PRINCIPAL_PHONES = "PRINCIPAL_PHONES";
        String PRINCIPAL_ADDRESSES = "PRINCIPAL_ADDRESSES";

        String TRANSACTION_OPERATOR_IS_NULL = "TRANSACTION_OPERATOR_IS_NULL";
        String TRANSACTION_OPERATOR_UUID = "TRANSACTION_OPERATOR_UUID";
        String TRANSACTION_OPERATOR_COUNTERPARTY_TYPE = "TRANSACTION_OPERATOR_COUNTERPARTY_TYPE";
        String TRANSACTION_OPERATOR_FULL_NAME = "TRANSACTION_OPERATOR_FULL_NAME";
        String TRANSACTION_OPERATOR_SHORT_NAME = "TRANSACTION_OPERATOR_SHORT_NAME";
        String TRANSACTION_OPERATOR_INN = "TRANSACTION_OPERATOR_INN";
        String TRANSACTION_OPERATOR_KPP = "TRANSACTION_OPERATOR_KPP";
        String TRANSACTION_OPERATOR_PHONES = "TRANSACTION_OPERATOR_PHONES";
        String TRANSACTION_OPERATOR_ADDRESSES = "TRANSACTION_OPERATOR_ADDRESSES";
        String OPERATION_DESCRIPTION = "OPERATION_DESCRIPTION";
    }

    public interface FiscalReceiptColumns extends FiscalDocumentColumns {
        String SETTLEMENT_TYPE = "SETTLEMENT_TYPE";
    }
}