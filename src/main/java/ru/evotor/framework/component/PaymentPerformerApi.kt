package ru.evotor.framework.component

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Bundle
import ru.evotor.framework.core.action.event.receipt.payment.system.event.PaymentSystemEvent
import ru.evotor.framework.payment.PaymentSystem
import ru.evotor.framework.payment.PaymentType

/**
 * Класс для получения исполнителей платежей, установленных на смарт-терминале.
 */
object PaymentPerformerApi {

    private const val METADATA_NAME_APP_UUID = "app_uuid"

    private const val CASH_PAYMENT_SYSTEM_ID = "ru.evotor.paymentSystem.cash.base"
    private const val CARD_PAYMENT_SYSTEM_ID = "ru.evotor.paymentSystem.cashless.base"
    private const val CASH_PAYMENT_DESCRIPTION = "Наличные"
    private const val CARD_PAYMENT_DESCRIPTION = "Банковская карта"

    /**
     * Возвращает список всех установленных на смарт-терминале приложений, способных выполнить оплату.
     * @param packageManager экземпляр класса PackageManager, необходимого, для получения информации об установленных приложениях.
     * @return applicationsList список приложений, способных выполнить оплату.
     * @see PaymentPerformer
     */
    fun getAllPaymentPerformers(packageManager: PackageManager): List<PaymentPerformer> {
        val eventName = PaymentSystemEvent.NAME_ACTION
        val applicationsList = ArrayList<PaymentPerformer>()
        applicationsList.add(getDefaultCashPaymentPerformer())
        applicationsList.add(getDefaultCardPaymentPerformer())
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

    private fun getDefaultCashPaymentPerformer() = PaymentPerformer(
            PaymentSystem(
                    PaymentType.CASH,
                    CASH_PAYMENT_DESCRIPTION,
                    CASH_PAYMENT_SYSTEM_ID
            ),
            null,
            null,
            null,
            CASH_PAYMENT_DESCRIPTION
    )

    private fun getDefaultCardPaymentPerformer() = PaymentPerformer(
            PaymentSystem(
                    PaymentType.ELECTRON,
                    CARD_PAYMENT_DESCRIPTION,
                    CARD_PAYMENT_SYSTEM_ID
            ),
            null,
            null,
            null,
            CARD_PAYMENT_DESCRIPTION
    )
}