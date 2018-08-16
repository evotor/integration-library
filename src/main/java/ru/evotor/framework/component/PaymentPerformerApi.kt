package ru.evotor.framework.component

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import ru.evotor.framework.core.action.event.receipt.payment.system.event.PaymentSystemEvent
import ru.evotor.framework.payment.PaymentSystem
import ru.evotor.framework.payment.PaymentType

object PaymentPerformerApi {

    private const val METADATA_NAME_APP_UUID = "app_uuid"

    fun getAllPaymentPerformers(context: Context): List<PaymentPerformer> {
        val eventName = PaymentSystemEvent.NAME_ACTION
        val applicationsList = ArrayList<PaymentPerformer>()
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
                val packageInfo = packageManager.getPackageInfo(resolveInfo.serviceInfo.packageName, PackageManager.GET_META_DATA or PackageManager.GET_PERMISSIONS)
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
            val app = PaymentPerformer(
                    paymentSystem,
                    resolveInfo.serviceInfo.packageName,
                    resolveInfo.serviceInfo.name,
                    appUuid,
                    resolveInfo.loadLabel(packageManager).toString()
            )
            applicationsList.add(app)
        }
        return applicationsList
    }


}