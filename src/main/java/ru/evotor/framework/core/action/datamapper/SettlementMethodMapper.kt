package ru.evotor.framework.core.action.datamapper

import android.os.Bundle
import ru.evotor.framework.receipt.position.SettlementMethod

object SettlementMethodMapper {

    private const val KEY_SETTLEMENT_METHOD = "settlementMethod"

    @JvmStatic
    fun toBundle(settlementMethod: SettlementMethod): Bundle =
            Bundle().apply {
                putParcelable(KEY_SETTLEMENT_METHOD, settlementMethod)
            }

    @JvmStatic
    fun fromBundle(bundle: Bundle?): SettlementMethod {
        val defaultSettlementMethod = SettlementMethod.FullSettlement()

        val settlementMethod = bundle?.let {
            it.classLoader = SettlementMethod::class.java.classLoader
            it.getParcelable<SettlementMethod>(KEY_SETTLEMENT_METHOD)
        }

        return settlementMethod ?: defaultSettlementMethod
    }
}
