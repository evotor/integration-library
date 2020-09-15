package ru.evotor.framework.receipt

object PrintGroupSubTable {
    const val COLUMN_IDENTIFIER = "PRINT_GROUP_IDENTIFIER"
    const val COLUMN_TYPE = "PRINT_GROUP_TYPE"
    const val COLUMN_ORG_NAME = "PRINT_GROUP_ORG_NAME"
    const val COLUMN_ORG_INN = "PRINT_GROUP_ORG_INN"
    const val COLUMN_ORG_ADDRESS = "PRINT_GROUP_ORG_ADDRESS"
    const val COLUMN_TAXATION_SYSTEM = "PRINT_GROUP_TAXATION_SYSTEM"
    const val COLUMN_SHOULD_PRINT_RECEIPT = "PRINT_GROUP_SHOULD_PRINT_RECEIPT"
    const val COLUMN_PURCHASER_NAME = "PURCHASER_NAME"
    const val COLUMN_PURCHASER_DOCUMENT_NUMBER = "PURCHASER_DOCUMENT_NUMBER"
    const val COLUMN_PURCHASER_TYPE = "PURCHASER_TYPE"
    @Deprecated(
            level = DeprecationLevel.WARNING,
            message = "Будет удалено из PrintGroupSubTable и перенесено в MedicineAttributeSubTable",
            replaceWith = ReplaceWith(expression = "MedicineAttributeSubTable.COLUMN_SUBJECT_ID",
                    imports = ["ru.evotor.framework.receipt.MedicineAttributeSubTable"]))
    const val COLUMN_SUBJECT_ID = "SUBJECT_ID"
}