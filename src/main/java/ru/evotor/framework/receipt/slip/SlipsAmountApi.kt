package ru.evotor.framework.receipt.slip

import android.content.Context
import android.net.Uri
import ru.evotor.framework.features.FeaturesApi

object SlipsAmountApi {
    fun getSlipsAmount(context: Context): Int? =
        if (FeaturesApi.isSlipAmountActive(context)) {
            context.contentResolver.query(
                Uri.withAppendedPath(SlipsAmountProviderContract.BASE_URI, SlipsAmountProviderContract.PATH_SLIPS_AMOUNT),
                null,
                null,
                null,
                null
            )?.use { c ->
                c.moveToFirst()

                val ind = c.getColumnIndex(SlipsAmountProviderContract.COLUMN_SLIPS_AMOUNT)
                if (ind > -1) {
                    c.getInt(ind)
                } else {
                    null
                }
            }
        } else {
            null
        }
}
