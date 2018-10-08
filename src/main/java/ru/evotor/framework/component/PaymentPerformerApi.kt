package ru.evotor.framework.component

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Bundle
import ru.evotor.framework.core.action.event.receipt.payment.system.event.PaymentSystemEvent
import ru.evotor.framework.payment.PaymentSystem
import ru.evotor.framework.payment.PaymentType

object PaymentPerformerApi {

    private const val METADATA_NAME_APP_UUID = "app_uuid"

    fun getAllPaymentPerformers(packageManager: PackageManager): List<PaymentPerformer> {
        val eventName = PaymentSystemEvent.NAME_ACTION
        val applicationsList = ArrayList<PaymentPerformer>()
        val intent = Intent(eventName)
        val applicationsInfo = packageManager.queryIntentServices(intent, PackageManager.GET_META_DATA)
        for (resolveInfo in applicationsInfo) {
            generatePaymentPerformer(packageManager, resolveInfo)?.let {
                applicationsList.add(it)
            }
        }
        return applicationsList
    }

    private fun generatePaymentPerformer(packageManager: PackageManager, resolveInfo: ResolveInfo): PaymentPerformer? {
        if (resolveInfo.serviceInfo == null || resolveInfo.serviceInfo.metaData == null) {
            return null
        }
        val paymentSystemId = getPaymentSystemId(resolveInfo.serviceInfo.metaData) ?: return null
        val appUuid: String?
        try {
            val packageInfo = packageManager.getPackageInfo(resolveInfo.serviceInfo.packageName, PackageManager.GET_META_DATA or PackageManager.GET_PERMISSIONS)
            if (!hasPermission(packageInfo)) return null
            appUuid = getAppUuid(packageInfo)
        } catch (exc: PackageManager.NameNotFoundException) {
            return null
        }
        appUuid ?: return null

        return PaymentPerformer(
                PaymentSystem(
                        PaymentType.ELECTRON,
                        resolveInfo.loadLabel(packageManager).toString(),
                        paymentSystemId
                ),
                resolveInfo.serviceInfo.packageName,
                resolveInfo.serviceInfo.name,
                appUuid,
                resolveInfo.loadLabel(packageManager).toString()
        )
    }

    private fun hasPermission(packageInfo: PackageInfo) = packageInfo.requestedPermissions.contains(PaymentSystemEvent.NAME_PERMISSION)

    private fun getAppUuid(packageInfo: PackageInfo) =
            if (packageInfo.applicationInfo.metaData != null)
                packageInfo.applicationInfo.metaData.getString(METADATA_NAME_APP_UUID, null)
            else null

    private fun getPaymentSystemId(metaData: Bundle) = metaData.getString(PaymentSystemEvent.META_NAME_PAYMENT_SYSTEM_ID, null)
}