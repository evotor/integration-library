package ru.evotor.framework.kkt.event

import android.os.Bundle
import java.math.BigDecimal

/**
 * Событие внесения наличности в кассу.
 *
 * Происходит при снятии кассового отчёта о внесении наличности в кассу.
 *
 * Обрабатывать это событие можно с помощью широковещательного приёмника событий кассы:
 * [ru.evotor.framework.kkt.event.handler.receiver.KktBroadcastReceiver]
 *
 * @param documentUuid uuid снятого отчета
 * @param total сумма внесённой наличности
 */
class CashInsertedEvent(documentUuid: String, total: BigDecimal) : CashOperationEvent(documentUuid, total) {
    companion object {
        fun from(bundle: Bundle?): CashInsertedEvent? = bundle?.let {
            CashInsertedEvent(
                    getDocumentUuid(it) ?: return null,
                    getTotal(it) ?: return null
            )
        }
    }
}