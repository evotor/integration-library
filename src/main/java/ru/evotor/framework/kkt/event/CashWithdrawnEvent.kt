package ru.evotor.framework.kkt.event

import android.os.Bundle
import java.math.BigDecimal

/**
 * Событие изъятия наличности из кассы.
 *
 * Происходит при снятии кассового отчёта об изъятии наличности из кассы.
 *
 * Обрабатывать это событие можно с помощью широковещательного приёмника событий кассы:
 * [ru.evotor.framework.kkt.event.handler.receiver.KktBroadcastReceiver]
 *
 * @param documentUuid uuid снятого отчета
 * @param total сумма изъятой наличности
 */
class CashWithdrawnEvent(documentUuid: String, total: BigDecimal) : CashOperationEvent(documentUuid, total) {
    companion object {
        fun from(bundle: Bundle?): CashWithdrawnEvent? = bundle?.let {
            CashWithdrawnEvent(
                    getDocumentUuid(it) ?: return null,
                    getTotal(it) ?: return null
            )
        }
    }
}