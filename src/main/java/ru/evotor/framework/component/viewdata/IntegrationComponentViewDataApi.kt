package ru.evotor.framework.component.viewdata

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.support.v4.content.ContextCompat
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
            generatePaymentPerformerViewData(context, resolveInfo)?.let {
                applicationsList.add(it)
            }
        }
        return applicationsList
    }

    private fun generatePaymentPerformerViewData(context: Context, resolveInfo: ResolveInfo): PaymentPerformerViewData? {
        if (resolveInfo.serviceInfo == null || resolveInfo.serviceInfo.metaData == null) {
            return null
        }
        val metaData = resolveInfo.serviceInfo.metaData
        val paymentSystemId = getPaymentSystemId(metaData) ?: return null
        val packageManager = context.packageManager
        var appUuid: String? = null
        try {
            val packageInfo = packageManager.getPackageInfo(resolveInfo.serviceInfo.packageName, PackageManager.GET_META_DATA or PackageManager.GET_PERMISSIONS)
            if (!hasPermission(packageInfo)) {
                return null
            }
            if (packageInfo.applicationInfo.metaData != null) {
                appUuid = packageInfo.applicationInfo.metaData.getString(METADATA_NAME_APP_UUID, null)
            }
        } catch (exc: PackageManager.NameNotFoundException) {
            return null
        }
        if (appUuid == null) {
            return null
        }
        val paymentSystem = PaymentSystem(
                PaymentType.ELECTRON,
                resolveInfo.loadLabel(packageManager).toString(),
                paymentSystemId
        )
        val paymentPerformer = PaymentPerformer(
                paymentSystem,
                resolveInfo.serviceInfo.packageName,
                resolveInfo.serviceInfo.name,
                appUuid,
                resolveInfo.loadLabel(packageManager).toString()
        )
        return PaymentPerformerViewData(
                paymentPerformer,
                resolveInfo.loadIcon(packageManager),
                if (metaData.containsKey(BACKGROUND_COLOR_KEY))
                    metaData.getInt(BACKGROUND_COLOR_KEY)
                else
                    ContextCompat.getColor(context, R.color.white),
                ContextCompat.getColor(context, R.color.text_black)
        )
    }

    private fun hasPermission(packageInfo: PackageInfo) = packageInfo.requestedPermissions.contains(PaymentSystemEvent.NAME_PERMISSION)

    private fun getPaymentSystemId(metaData: Bundle) = metaData.getString(PaymentSystemEvent.META_NAME_PAYMENT_SYSTEM_ID, null)
}