package ru.evotor.framework.core.action.datamapper

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import ru.evotor.framework.core.action.event.receipt.changes.IChange
import ru.evotor.framework.core.action.event.receipt.changes.UnknownChange
import ru.evotor.framework.core.action.event.receipt.changes.position.*
import ru.evotor.framework.core.action.event.receipt.changes.receipt.print_extra.SetPrintExtra
import ru.evotor.framework.safeValueOf

object ChangesMapper {
    private const val TAG = "ChangesMapper"

    private const val KEY_CHANGE_TYPE = "type"
    private const val KEY_CHANGE = "change"

    private data class ChangeInBundle(val typeName: String, val bundle: Bundle)

    fun create(changesParcelable: Array<Parcelable>?): List<IChange> {
        val changes = ArrayList <IChange>()
        changesParcelable ?: return changes

        return changesParcelable
                .map { it as Bundle }
                .map {
                    val typeString = it.getString(KEY_CHANGE_TYPE, null)

                    val changeBundle = it.getBundle(KEY_CHANGE)
                    if (typeString == null) {
                        Log.e(TAG, "type can not be null")
                        return@map null
                    }

                    if (changeBundle == null) {
                        Log.e(TAG, "changeBundle can not be null")
                        return@map null
                    }

                    ChangeInBundle(typeString, changeBundle)
                }
                .filterNotNull()
                .map {
                    val change = fromBundle(it.typeName, it.bundle)
                    if (change == null) {
                        Log.e(TAG, "change can not be null")
                        return@map null
                    }

                    return@map change
                }
                .filterNotNull()
    }

    fun toBundle(change: IChange): Bundle {
        return Bundle().apply {
            if (change is UnknownChange) {
                putString(KEY_CHANGE_TYPE, change.typeName)
            } else {
                putString(KEY_CHANGE_TYPE, change.getType().name)
            }
            putBundle(KEY_CHANGE, change.toBundle())
        }
    }

    private fun fromBundle(typeName: String, bundle: Bundle): IChange? {
        val type = safeValueOf<IChange.Type>(typeName)

        return when (type) {
            IChange.Type.POSITION_REMOVE -> PositionRemove.from(bundle)
            IChange.Type.POSITION_ADD -> PositionAdd.from(bundle)
            IChange.Type.POSITION_EDIT -> PositionEdit.from(bundle)
            IChange.Type.SET_EXTRA -> SetExtra.from(bundle)
            IChange.Type.SET_POSITION_PRINT_GROUP -> SetPrintGroup.from(bundle)
            IChange.Type.SET_PAYMENT_PURPOSE_PRINT_GROUP -> SetPrintGroup.from(bundle)
            IChange.Type.SET_PRINT_EXTRA -> SetPrintExtra.from(bundle)
            null, IChange.Type.UNKNOWN -> UnknownChange.from(typeName, bundle)
        }
    }

}