package ru.evotor.framework.core.action.event.receipt.jewelry

import android.os.Bundle
import ru.evotor.IBundlable
import java.math.BigDecimal

/**
 * Событие о выбытии ювелирных изделий.
 *
 * Происходит перед вызовом приложений для оплат, в случае, если в чеке есть товары с типом ProductType.JEWELRY_MARKED.
 *
 * @param receiptUuid uuid чека
 * @param jewelryMarkedPositions список ювелирных позиций (УИН + итоговая стоимость)
 * @param sessionNumber фискальный номер смены
 * @param documentNumber фискальный номер последнего закрытого в ФН чека
 * @param paperWidth ширина чековой ленты
 * @param kktSerialNumber заводской номер ККТ
 */
class SendJewelryEvent(
    val receiptUuid: String,
    val jewelryMarkedPositions: Map<String, BigDecimal>,
    val sessionNumber: Long,
    val documentNumber: Long,
    val paperWidth: Int,
    val kktSerialNumber: String,
) : IBundlable {

    override fun toBundle(): Bundle {
        val result = Bundle()
        result.putString(KEY_RECEIPT_UUID, receiptUuid)
        result.putBundle(KEY_JEWELRY_MARKED_POSITIONS, positionsToBundle(jewelryMarkedPositions))
        result.putLong(KEY_SESSION_NUMBER, sessionNumber)
        result.putLong(KEY_DOCUMENT_NUMBER, documentNumber)
        result.putInt(KEY_PAPER_WIDTH, paperWidth)
        result.putString(KEY_KKT_SERIAL_NUMBER, kktSerialNumber)
        return result
    }
    companion object {

        /**
         * Продажа ювелирных изделий.
         *
         * Значение константы: <code>evo.v2.receipt.sell.sendJewelry</code>.
         */
        const val NAME_SELL_RECEIPT = "evo.v2.receipt.sell.sendJewelry"
        /**
         * Отмена продажи ювелирных изделий.
         *
         * Значение константы: <code>evo.v2.receipt.sell.cancel.sendJewelry</code>.
         */
        const val NAME_SELL_CANCEL_RECEIPT = "evo.v2.receipt.sell.cancel.sendJewelry"
        /**
         * Возврат ювелирных изделий.
         *
         * Значение константы: <code>evo.v2.receipt.payback.sendJewelry</code>.
         */
        const val NAME_PAYBACK_RECEIPT = "evo.v2.receipt.payback.sendJewelry"
        /**
         * Отмена возврата ювелирных изделий.
         *
         * Значение константы: <code>evo.v2.receipt.payback.cancel.sendJewelry</code>.
         */
        const val NAME_PAYBACK_CANCEL_RECEIPT = "evo.v2.receipt.payback.cancel.sendJewelry"

        private const val KEY_RECEIPT_UUID = "receiptUuid"
        private const val KEY_JEWELRY_MARKED_POSITIONS = "jewelryMarkedPositions"
        private const val KEY_SESSION_NUMBER = "sessionNumber"
        private const val KEY_DOCUMENT_NUMBER = "documentNumber"
        private const val KEY_PAPER_WIDTH = "paperWidth"
        private const val KEY_KKT_SERIAL_NUMBER = "kktSerialNumber"

        fun from(bundle: Bundle?): SendJewelryEvent? = bundle?.let {
            SendJewelryEvent(
                getReceiptUuid(it) ?: return null,
                getJewelryMarkedPositions(it) ?: return null,
                getSessionNumber(it) ?: return null,
                getDocumentNumber(it) ?: return null,
                getPaperWidth(it) ?: return null,
                getKktSerialNumber(it) ?: return null,
            )
        }

        private fun getReceiptUuid(bundle: Bundle): String? =
            bundle.getString(KEY_RECEIPT_UUID, null)

        private fun getJewelryMarkedPositions(bundle: Bundle): Map<String, BigDecimal>? =
            bundleToPositions(bundle.getBundle(KEY_JEWELRY_MARKED_POSITIONS))

        private fun getSessionNumber(bundle: Bundle): Long? =
            bundle.getLong(KEY_SESSION_NUMBER, -1L).let { value ->
                if (value != -1L) value else null
            }

        private fun getDocumentNumber(bundle: Bundle): Long? =
            bundle.getLong(KEY_DOCUMENT_NUMBER, -1L).let { value ->
                if (value != -1L) value else null
            }

        private fun getPaperWidth(bundle: Bundle): Int? =
            bundle.getInt(KEY_PAPER_WIDTH, -1).let { value ->
                if (value != -1) value else null
            }

        private fun getKktSerialNumber(bundle: Bundle): String? =
            bundle.getString(KEY_KKT_SERIAL_NUMBER, null)

        private fun bundleToPositions(positions: Bundle?): Map<String, BigDecimal>? {
            positions?.let { bundle ->
                bundle.classLoader = BigDecimal::class.java.classLoader
                return HashMap<String, BigDecimal>().apply {
                    positions.keySet().forEach { key ->
                        val value = positions.getSerializable(key) as BigDecimal?
                        value?.let {
                            this[key] = it
                        }
                    }
                }
            } ?: return null
        }

        private fun positionsToBundle(positions: Map<String, BigDecimal>?): Bundle? {
            positions?.let {
                return Bundle().apply {
                    positions.keys.forEach {
                        this.putSerializable(it, positions[it])
                    }
                }
            } ?: return null
        }
    }

}
