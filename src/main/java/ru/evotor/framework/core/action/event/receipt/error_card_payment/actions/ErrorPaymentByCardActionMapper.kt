package ru.evotor.framework.core.action.event.receipt.error_card_payment.actions

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import ru.evotor.framework.Utils

object ErrorPaymentByCardActionMapper {

    private const val KEY_ACTION_TYPE = "type"
    private const val KEY_ACTION = "action"

    fun create(actionsParcelable: Array<Parcelable>?): List<IHandleEventResultAction> {
        val actions = ArrayList<IHandleEventResultAction>()
        actionsParcelable ?: return actions

        return actionsParcelable
            .map { it as Bundle }
            .map {
                val typeString = it.getString(KEY_ACTION_TYPE, null) ?: run {
                    Log.e(ErrorPaymentByCardActionMapper::class.simpleName, "type can not be null")
                    return@map null
                }

                val actionBundle = it.getBundle(KEY_ACTION) ?: run {
                    Log.e(ErrorPaymentByCardActionMapper::class.simpleName, "actionBundle can not be null")
                    return@map null
                }

                val type = Utils.safeValueOf(IHandleEventResultAction.Type::class.java, typeString, null)

                if (type == IHandleEventResultAction.Type.SHOW_ERROR_SCREEN_ACTION) {
                    PaymentByCardErrorAction.from(actionBundle)
                } else {
                    null
                }

            }
            .filterNotNull()
    }

    fun toBundle(action: IHandleEventResultAction): Bundle {
        return Bundle().apply {
            if (action is PaymentByCardErrorAction) {
                putString(KEY_ACTION_TYPE, action.getType().name)
            }
            putBundle(KEY_ACTION, action.toBundle())
        }
    }
}
