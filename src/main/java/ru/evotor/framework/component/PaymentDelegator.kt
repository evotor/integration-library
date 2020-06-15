package ru.evotor.framework.component

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.core.action.datamapper.PaymentDelegatorMapper

/**
 * Компонент (служба, операция и т.д.) интеграционного приложения, осуществляющий делегирование платежей другим приложениям. Например, приложение "Комбооплата".
 *
 * @param packageName Название пакета
 * @param componentName Название компонента (служба, операция и т.д.)
 * @param appUuid Уникальный идентификатора приложения в Облаке Эвотор
 * @param appName Название приложения
 */
class PaymentDelegator(packageName: String?,
                       componentName: String?,
                       appUuid: String?,
                       appName: String?
) : IntegrationComponent(packageName, componentName, appUuid, appName), IBundlable {
    override fun toBundle(): Bundle {
        return PaymentDelegatorMapper.toBundle(this)
    }

    companion object {
        fun from(bundle: Bundle?) = PaymentDelegatorMapper.fromBundle(bundle)
    }
}