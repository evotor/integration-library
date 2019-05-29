package ru.evotor.framework.core.action.event.receipt.print_extra

import android.os.Bundle
import ru.evotor.IBundlable

/**
 * Событие, которое возникает при печати в чеке произвольных данных.
 *
 * Константы события указывают тип чека, в котором будут напечатанны данные.
 *
 * Чтобы приложение получало событие, значение константы необходимо указать в элементе <code><action></code> intent-фильтра соотвествующей службы.
 */
class PrintExtraRequiredEvent() : IBundlable {

    override fun toBundle(): Bundle {
        return Bundle()
    }

    companion object {
        /**
         * Разрешение, которое необходимо указать в манифесте приложения.
         */
        const val NAME_PERMISSION = "ru.evotor.permission.receipt.printExtra.SET"
        /**
         * Данные будут напечатанны в чеке продажи.
         *
         * Значение константы: <code>evo.v2.receipt.sell.printExtra.REQUIRED</code>.
         */
        const val NAME_SELL_RECEIPT = "evo.v2.receipt.sell.printExtra.REQUIRED"
        /**
         * Данные будут напечатанны в чеке возврата.
         *
         * Значение константы: <code>evo.v2.receipt.payback.printExtra.REQUIRED</code>.
         */
        const val NAME_PAYBACK_RECEIPT = "evo.v2.receipt.payback.printExtra.REQUIRED"
        /**
         * Данные будут напечатанны в чеке покупки.
         *
         * Значение константы: <code>evo.v2.receipt.buy.printExtra.REQUIRED</code>.
         */
        const val NAME_BUY_RECEIPT = "evo.v2.receipt.buy.printExtra.REQUIRED"
        /**
         * Данные будут напечатанны в чеке возврата покупки.
         *
         * Значение константы: <code>evo.v2.receipt.buyback.printExtra.REQUIRED</code>.
         */
        const val NAME_BUYBACK_RECEIPT = "evo.v2.receipt.buyback.printExtra.REQUIRED"

        fun create(bundle: Bundle?): PrintExtraRequiredEvent? {
            if (bundle == null) {
                return null
            }

            return PrintExtraRequiredEvent()
        }
    }
}