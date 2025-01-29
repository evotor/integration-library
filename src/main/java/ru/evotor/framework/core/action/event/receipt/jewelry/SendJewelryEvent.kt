package ru.evotor.framework.core.action.event.receipt.jewelry

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.Utils
import ru.evotor.framework.core.action.datamapper.JewelryMarkedPositionsMapper
import ru.evotor.framework.core.action.event.receipt.payment.system.event.PaymentSystemEvent.OperationType
import java.math.BigDecimal

/**
 * Событие о выбытии ювелирных изделий.
 *
 * Происходит перед вызовом приложений для оплат, в случае, если в чеке есть товары с типом ProductType.JEWELRY_MARKED.
 *
 * @param receiptUuid uuid чека
 * @param operationType тип операции (отправка/отмена чека продажи/возврата)
 * @param jewelryMarkedPositions список ювелирных позиций (УИН + итоговая стоимость)
 * @param sessionNumber фискальный номер смены
 * @param documentNumber фискальный номер последнего закрытого в ФН чека
 * @param paperWidth ширина чековой ленты
 * @param kktSerialNumber заводской номер ККТ
 */
class SendJewelryEvent(
    private val receiptUuid: String,
    private val operationType: OperationType,
    private val jewelryMarkedPositions: Map<String, BigDecimal>,
    private val sessionNumber: Long,
    private val documentNumber: Long,
    private val paperWidth: Int,
    private val kktSerialNumber: String,
) : IBundlable {

    override fun toBundle(): Bundle {
        val result = Bundle()
        result.putString(KEY_RECEIPT_UUID, receiptUuid)
        result.putString(KEY_OPERATION_TYPE, operationType.name)
        result.putBundle(KEY_JEWELRY_MARKED_POSITIONS, JewelryMarkedPositionsMapper.toBundle(jewelryMarkedPositions))
        result.putLong(KEY_SESSION_NUMBER, sessionNumber)
        result.putLong(KEY_DOCUMENT_NUMBER, documentNumber)
        result.putInt(KEY_PAPER_WIDTH, paperWidth)
        result.putString(KEY_KKT_SERIAL_NUMBER, kktSerialNumber)
        return result
    }
    companion object {

        /**
         * Отправка чека в УТМ ГИИС ДМДК через стороннее приложение.
         *
         * Значение константы: <code>evo.v2.receipt.sendJewelry</code>.
         */
        const val NAME_ACTION = "evo.v2.receipt.sendJewelry"

        private const val KEY_RECEIPT_UUID = "receiptUuid"
        private const val KEY_OPERATION_TYPE = "operationType"
        private const val KEY_JEWELRY_MARKED_POSITIONS = "jewelryMarkedPositions"
        private const val KEY_SESSION_NUMBER = "sessionNumber"
        private const val KEY_DOCUMENT_NUMBER = "documentNumber"
        private const val KEY_PAPER_WIDTH = "paperWidth"
        private const val KEY_KKT_SERIAL_NUMBER = "kktSerialNumber"

        fun from(bundle: Bundle?): SendJewelryEvent? = bundle?.let {
            SendJewelryEvent(
                getReceiptUuid(it) ?: return null,
                getOperationType(it),
                getJewelryMarkedPositions(it) ?: return null,
                getSessionNumber(it) ?: return null,
                getDocumentNumber(it) ?: return null,
                getPaperWidth(it) ?: return null,
                getKktSerialNumber(it) ?: return null,
            )
        }

        private fun getReceiptUuid(bundle: Bundle): String? =
            bundle.getString(KEY_RECEIPT_UUID, null)

        private fun getOperationType(bundle: Bundle): OperationType =
            Utils.safeValueOf(
                OperationType::class.java,
                bundle.getString(KEY_OPERATION_TYPE, null),
                OperationType.UNKNOWN
            )

        private fun getJewelryMarkedPositions(bundle: Bundle): Map<String, BigDecimal>? =
            JewelryMarkedPositionsMapper.fromBundle(bundle.getBundle(KEY_JEWELRY_MARKED_POSITIONS))

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
    }

}