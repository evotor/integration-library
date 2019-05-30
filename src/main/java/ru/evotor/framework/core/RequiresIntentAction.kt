package ru.evotor.framework.core

/**
 * Прикрепляется к обработчикам событий широковещательного приёмника и интеграционного сервиса.
 * Означает, что если вы собираетесь реализовать аннотированный метод, вам нужно зарегистрировать
 * ваш широковещательный приёмник с указанным действием в <code>intent-filter</code>. Пример
 * регистрации широковещательного приёмника с действием
 * <code>evotor.intent.action.receipt.sell.OPENED</code>:
 * <pre>
 * {@code
 * <receiver
 *     android:name=".MyReceiver"
 *     android:enabled="true"
 *     android:exported="false">
 *     <intent-filter>
 *         <action android:name="evotor.intent.action.receipt.sell.OPENED" />
 *         <category android:name="android.intent.category.DEFAULT" />
 *     </intent-filter>
 * </receiver>
 * }
 * </pre>
 */
internal annotation class RequiresIntentAction(
        /**
         * Действие
         */
        val action: String
)
