package ru.evotor.framework.core.action.command.print_unprinted_document

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ru.evotor.IBundlable
import ru.evotor.framework.core.ActivityStarter
import ru.evotor.framework.core.IntegrationManagerCallback
import ru.evotor.framework.core.IntegrationManagerImpl

class PrintUnprintedDocumentCommand : IBundlable {

    fun process(context: Context, callback: IntegrationManagerCallback) {
        val componentNameList = IntegrationManagerImpl.convertImplicitIntentToExplicitIntent(
            NAME,
            context.applicationContext
        )
        if (componentNameList == null || componentNameList.isEmpty()) {
            return
        }
        IntegrationManagerImpl(context.applicationContext)
            .call(
                NAME,
                componentNameList[0],
                this,
                ActivityStarter(context),
                callback,
                Handler(Looper.getMainLooper())
            )
    }

    override fun toBundle(): Bundle {
        return Bundle()
    }

    companion object {

        const val NAME = "evo.v2.unprinted_document.print"

        @JvmStatic
        fun create(bundle: Bundle?): PrintUnprintedDocumentCommand? {
            return bundle?.let {
                PrintUnprintedDocumentCommand()
            }
        }
    }
}
