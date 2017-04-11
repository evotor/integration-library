package ru.evotor.integrations

import android.os.Bundle

/**
 * Created by nixan on 06.03.17.
 */

abstract class ReceiptOperation {

    open fun toBundle() = Bundle().apply {
        when (this@ReceiptOperation) {
            is AddPosition -> putString("operationType", "addPosition")
            is RemovePosition -> putString("operationType", "removePosition")
            is AddExtraDataToReceipt -> putString("operationType", "addPositionExtra")
            is ApplyReceiptDiscount -> putString("operationType", "applyReceiptDiscount")
            is ApplyPositionDiscount -> putString("operationType", "applyPositionDiscount")
            is CreatePrintGroup -> putString("operationType", "createPrintGroup")
            is AddPrintGroupForPosition -> putString("operationType", "addPrintGroupForPosition")
        }
    }

    companion object {

        @JvmStatic
        fun fromBundle(bundle: Bundle): ReceiptOperation {
            when (bundle.getString("operationType")) {
                "addPosition" -> return AddPosition.fromBundle(bundle)
                "removePosition" -> return RemovePosition.fromBundle(bundle)
                "addPositionExtra" -> return AddExtraDataToReceipt.fromBundle(bundle)
                "applyReceiptDiscount" -> return ApplyReceiptDiscount.fromBundle(bundle)
                "applyPositionDiscount" -> return ApplyPositionDiscount.fromBundle(bundle)
                "createPrintGroup" -> return CreatePrintGroup.fromBundle(bundle)
                "addPrintGroupForPosition" -> return AddPrintGroupForPosition.fromBundle(bundle)
                else -> throw UnknownOperationException()
            }
        }

    }

    class AddPosition(val uuid: String, val code: String) : ReceiptOperation() {

        override fun toBundle() = super.toBundle().apply {
            putString("uuid", uuid)
            putString("code", code)
        }

        companion object {
            @JvmStatic
            fun fromBundle(bundle: Bundle): AddPosition = AddPosition(bundle.getString("uuid"), bundle.getString("code"))
        }
    }

    class RemovePosition(val uuid: String) : ReceiptOperation() {

        override fun toBundle() = super.toBundle().apply {
            putString("uuid", uuid)
        }

        companion object {
            @JvmStatic
            fun fromBundle(bundle: Bundle): RemovePosition = RemovePosition(bundle.getString("uuid"))
        }
    }

    class AddExtraDataToReceipt(val extraData: Bundle) : ReceiptOperation() {

        override fun toBundle() = super.toBundle().apply {
            putBundle("extraData", extraData)
        }

        companion object {
            @JvmStatic
            fun fromBundle(bundle: Bundle): AddExtraDataToReceipt = AddExtraDataToReceipt(bundle.getBundle("extraData"))
        }
    }

    class ApplyReceiptDiscount(val discount: Double) : ReceiptOperation() {

        override fun toBundle() = super.toBundle().apply {
            putDouble("discount", discount)
        }

        companion object {
            @JvmStatic
            fun fromBundle(bundle: Bundle): ApplyReceiptDiscount = ApplyReceiptDiscount(bundle.getDouble("discount"))
        }
    }

    class ApplyPositionDiscount(val uuid: String, val discount: Double) : ReceiptOperation() {


        override fun toBundle() = super.toBundle().apply {
            putString("uuid", uuid)
            putDouble("discount", discount)
        }

        companion object {
            @JvmStatic
            fun fromBundle(bundle: Bundle): ApplyPositionDiscount = ApplyPositionDiscount(bundle.getString("uuid"), bundle.getDouble("discount"))
        }
    }

    class AddExtraDataToPosition(val uuid: String, val extraData: Bundle) : ReceiptOperation() {

        override fun toBundle() = super.toBundle().apply {
            putString("uuid", uuid)
            putBundle("extraData", extraData)
        }

        companion object {
            @JvmStatic
            fun fromBundle(bundle: Bundle): AddExtraDataToPosition = AddExtraDataToPosition(bundle.getString("uuid"), bundle.getBundle("extraData"))
        }
    }

    class CreatePrintGroup(val printGroupApiObject: PrintGroupApiObject) : ReceiptOperation() {

        override fun toBundle() = super.toBundle().apply {
            when (printGroupApiObject) {
                is PrintGroupApiObject.FiscalReceipt -> {
                    putString("printGroupType", "fiscal")
                    putString("printGroupUuid", printGroupApiObject.uuid)
                }
                is PrintGroupApiObject.NonFiscalReceipt -> {
                    putString("printGroupType", "nonFiscal")
                    putString("printGroupUuid", printGroupApiObject.uuid)
                    putString("printGroupOrganizationName", printGroupApiObject.organizationName)
                    putString("printGroupOrganizationVat", printGroupApiObject.organizationVat)
                }
                is PrintGroupApiObject.SimplifiedTaxationSystemReceipt -> {
                    putString("printGroupType", "simplifiedTaxationSystem")
                    putString("printGroupUuid", printGroupApiObject.uuid)
                    putString("printGroupOrganizationName", printGroupApiObject.organizationName)
                    putString("printGroupOrganizationVat", printGroupApiObject.organizationVat)
                }
            }
        }

        companion object {
            @JvmStatic
            fun fromBundle(bundle: Bundle): CreatePrintGroup = CreatePrintGroup(
                    printGroupApiObject = when (bundle.getString("printGroupType")) {
                        "fiscal" -> PrintGroupApiObject.FiscalReceipt(uuid = bundle.getString("printGroupUuid"))
                        "nonFiscal" -> PrintGroupApiObject.NonFiscalReceipt(
                                uuid = bundle.getString("printGroupUuid"),
                                organizationName = bundle.getString("printGroupOrganizationName"),
                                organizationVat = bundle.getString("printGroupOrganizationVat")
                        )
                        "simplifiedTaxationSystem" -> PrintGroupApiObject.SimplifiedTaxationSystemReceipt(
                                uuid = bundle.getString("printGroupUuid"),
                                organizationName = bundle.getString("printGroupOrganizationName"),
                                organizationVat = bundle.getString("printGroupOrganizationVat")
                        )
                        else -> throw UnknownPrintGroupTypeException()
                    })
        }
    }

    class AddPrintGroupForPosition(val uuid: String, val printGroupUuid: String) : ReceiptOperation() {

        override fun toBundle() = super.toBundle().apply {
            putString("positionUuid", uuid)
            putString("printGroupUuid", printGroupUuid)
        }

        companion object {
            @JvmStatic
            fun fromBundle(bundle: Bundle) = AddPrintGroupForPosition(bundle.getString("positionUuid"), bundle.getString("printGroupUuid"))
        }
    }

    class UnknownOperationException : Exception()

    class UnknownPrintGroupTypeException : Exception()

}