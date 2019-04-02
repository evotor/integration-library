package ru.evotor.framework.component

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.core.action.datamapper.PaymentDelegatorMapper

/**
 * Компонент интеграционного приложения, осуществляющий делегирование платежей другим приложениям
 * a.k.a. приложение комбооплаты
 *
 * @property packageName Название пакета
 * @property componentName Название компонента (сервис, активити и т.п.)
 * @property appUuid Уникальный идентификатора приложения в системе Эвотора
 * @property appName Название приложения
 */
class PaymentDelegator(packageName: String?,
                       componentName: String?,
                       appUuid: String?,
                       appName: String?
) : IntegrationComponent(packageName, componentName, appUuid, appName), IBundlable {
    override fun toBundle(): Bundle {
        return PaymentDelegatorMapper.toBundle(this)
    }
}