package ru.evotor.framework.core.action.event.receipt.changes

import android.os.Bundle

/**
 * Created by a.kuznetsov on 23/05/2017.
 */

class UnknownChange(val typeName: String, val bundle: Bundle) : IChange {

    override fun getType(): IChange.Type {
        return IChange.Type.UNKNOWN
    }

    override fun toBundle(): Bundle {
        return bundle
    }

    companion object {
        @JvmStatic
        fun from(typeName: String?, bundle: Bundle?): UnknownChange? {
            typeName ?: return null
            bundle ?: return null

            return UnknownChange(
                    typeName,
                    bundle
            )
        }
    }


}
