package ru.evotor.framework.core.action.event.receipt.error_card_payment

import android.os.Bundle
import android.os.Parcelable
import ru.evotor.IBundlable
import ru.evotor.framework.core.action.event.receipt.error_card_payment.actions.IHandleEventResultAction
import ru.evotor.framework.core.action.event.receipt.error_card_payment.actions.ErrorPaymentByCardActionMapper

class ErrorPaymentByCardEventResult(
    val actions: List<IHandleEventResultAction>
) : IBundlable {


    override fun toBundle(): Bundle {
        val bundle = Bundle()
        val actionsParcelable = arrayOfNulls<Parcelable>(actions.size)
        for (i in actionsParcelable.indices) {
            val action: IHandleEventResultAction = actions[i]
            actionsParcelable[i] = ErrorPaymentByCardActionMapper.toBundle(action)
        }
        bundle.putParcelableArray(KEY_ACTIONS, actionsParcelable)
        return bundle
    }

    companion object {
        private const val KEY_ACTIONS = "actions"

        fun create(bundle: Bundle?): ErrorPaymentByCardEventResult? {
            if (bundle == null) {
                return null
            }
            val actionsParcelable =
                bundle.getParcelableArray(KEY_ACTIONS)
            val actions = ErrorPaymentByCardActionMapper.create(actionsParcelable)
            return ErrorPaymentByCardEventResult(actions)
        }
    }
}
