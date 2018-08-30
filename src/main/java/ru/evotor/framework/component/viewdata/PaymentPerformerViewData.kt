package ru.evotor.framework.component.viewdata

import android.graphics.drawable.Drawable
import ru.evotor.framework.component.PaymentPerformer

class PaymentPerformerViewData(
        val paymentPerformer: PaymentPerformer,
        icon: Drawable?,
        backgroundColor: Int?,
        textColor: Int?
) : IntegrationComponentViewData(icon, backgroundColor, textColor)