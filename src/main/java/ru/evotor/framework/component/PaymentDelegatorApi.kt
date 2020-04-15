package ru.evotor.framework.component

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import ru.evotor.framework.core.action.event.receipt.payment.combined.event.PaymentDelegatorEvent

/**
 * Класс для получения делегаторов платежей, установленных на смарт-терминале.
 */
object PaymentDelegatorApi {

    private const val METADATA_NAME_APP_UUID = "app_uuid"

    /**
     * Возвращает список всех установленных на смарт-терминале приложений, способных делегировать платежи другим приложениям.
     * @param packageManager экземпляр класса PackageManager, необходимого, для получения информации об установленных приложениях.
     * @return applicationsList список приложений, способных делегировать платежи.
     * @see PaymentDelegator
     */
    fun getAllPaymentDelegators(packageManager: PackageManager): List<PaymentDelegator> {
        val eventName = PaymentDelegatorEvent.NAME_ACTION
        val applicationsList = ArrayList<PaymentDelegator>()
        val intent = Intent(eventName)
        val applicationsInfo = packageManager.queryIntentServices(intent, PackageManager.GET_META_DATA)
        for (resolveInfo in applicationsInfo) {
            generatePaymentDelegator(packageManager, resolveInfo)?.let {
                applicationsList.add(it)
            }
        }
        return applicationsList
    }

    private fun generatePaymentDelegator(packageManager: PackageManager, resolveInfo: ResolveInfo): PaymentDelegator? {
        if (resolveInfo.serviceInfo == null || resolveInfo.serviceInfo.metaData == null) {
            return null
        }
        val appUuid: String?
        try {
            val packageInfo = packageManager.getPackageInfo(resolveInfo.serviceInfo.packageName, PackageManager.GET_META_DATA or PackageManager.GET_PERMISSIONS)
            if (!hasPermission(packageInfo)) return null
            appUuid = getAppUuid(packageInfo)
        } catch (exc: PackageManager.NameNotFoundException) {
            return null
        }
        appUuid ?: return null

        return PaymentDelegator(
                resolveInfo.serviceInfo.packageName,
                resolveInfo.serviceInfo.name,
                appUuid,
                resolveInfo.loadLabel(packageManager).toString()
        )
    }

    private fun hasPermission(packageInfo: PackageInfo) = packageInfo.requestedPermissions.contains(PaymentDelegatorEvent.NAME_PERMISSION)

    private fun getAppUuid(packageInfo: PackageInfo) =
            if (packageInfo.applicationInfo.metaData != null)
                packageInfo.applicationInfo.metaData.getString(METADATA_NAME_APP_UUID, null)
            else null
}