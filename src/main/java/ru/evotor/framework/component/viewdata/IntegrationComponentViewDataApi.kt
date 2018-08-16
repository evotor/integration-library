package ru.evotor.framework.component.viewdata

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import ru.evotor.framework.component.PaymentPerformer
import ru.evotor.framework.core.action.event.receipt.payment.system.event.PaymentSystemEvent
import ru.evotor.framework.payment.PaymentSystem
import ru.evotor.framework.payment.PaymentType
import ru.evotor.integrations.R

object IntegrationComponentViewDataApi {

    private const val METADATA_NAME_APP_UUID = "app_uuid"
    private const val BACKGROUND_COLOR_KEY = "ru.evotor.sales_screen.BACKGROUND_COLOR"

    fun getAllPaymentPerformerViewData(context: Context): List<PaymentPerformerViewData> {
        val eventName = PaymentSystemEvent.NAME_ACTION
        val applicationsList = ArrayList<PaymentPerformerViewData>()
        val packageManager = context.packageManager
        val intent = Intent(eventName)
        val applicationsInfo = packageManager.queryIntentServices(intent, PackageManager.GET_META_DATA)
        for (resolveInfo in applicationsInfo) {
            if (resolveInfo.serviceInfo == null || resolveInfo.serviceInfo.metaData == null) {
                continue
            }
            val metaData = resolveInfo.serviceInfo.metaData
            val psId = metaData.getString(PaymentSystemEvent.META_NAME_PAYMENT_SYSTEM_ID, null)
                    ?: continue
            var hasPermission: Boolean
            var appUuid: String? = null
            try {
                val packageInfo = packageManager.getPackageInfo(
                        resolveInfo.serviceInfo.packageName,
                        PackageManager.GET_META_DATA or PackageManager.GET_PERMISSIONS
                )
                hasPermission = packageInfo.requestedPermissions.contains(PaymentSystemEvent.NAME_PERMISSION)
                if (packageInfo.applicationInfo.metaData != null) {
                    appUuid = packageInfo.applicationInfo.metaData.getString(METADATA_NAME_APP_UUID, null)
                }
            } catch (exc: PackageManager.NameNotFoundException) {
                hasPermission = false
            }
            if (!hasPermission || appUuid == null) {
                continue
            }
            val paymentSystem = PaymentSystem(
                    PaymentType.ELECTRON,
                    resolveInfo.loadLabel(packageManager).toString(),
                    psId
            )
            val paymentPerformer = PaymentPerformer(
                    paymentSystem,
                    resolveInfo.serviceInfo.packageName,
                    resolveInfo.serviceInfo.name,
                    appUuid,
                    resolveInfo.loadLabel(packageManager).toString()
            )
            val app = PaymentPerformerViewData(
                    paymentPerformer,
                    resolveInfo.loadIcon(packageManager),
                    if (metaData.containsKey(BACKGROUND_COLOR_KEY))
                        metaData.getInt(BACKGROUND_COLOR_KEY)
                    else
                        context.resources.getColor(R.color.white),
                    context.resources.getColor(R.color.text_black)
            )
            applicationsList.add(app)
        }
        return applicationsList
    }

}