package ru.evotor.framework.core.action.processor;


import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.IBundlable;
import ru.evotor.framework.core.IIntegrationManagerResponse;
import ru.evotor.framework.core.IntegrationManager;
import ru.evotor.framework.core.IntegrationResponse;

/**
 * Родительский класс обработчиков событий смарт-терминала.
 */
public abstract class ActionProcessor {

    public void process(String action, IIntegrationManagerResponse response, Bundle bundle) throws RemoteException {
        process(action, bundle, new Callback(response, bundle));
    }

    public abstract void process(@NonNull String action, @Nullable Bundle bundle, @NonNull Callback callback) throws RemoteException;

    /**
     * Функция обратного вызова, которая вызывается после завершения обработки события.
     *
     * Методы функции позволяют возвращать необходимые результаты обработки событий в смарт-терминал, а также вызывать операции и обрабатывать ошибки.
     */
    public final class Callback {
        private IIntegrationManagerResponse response;
        private Bundle sourceData;

        private Callback(IIntegrationManagerResponse response, Bundle sourceData) {
            this.response = response;
            this.sourceData = sourceData;
        }

        /**
         * Запускает операцию.
         * @param intent содержит данные и название операции, которую необходимо запустить.
         * @throws RemoteException
         */
        public final void startActivity(Intent intent) throws RemoteException {
            if (!intent.hasExtra(IntegrationManager.KEY_INTENT_DATA)) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(IntegrationManager.KEY_INTEGRATION_RESPONSE, new IntegrationResponse(response));
                bundle.putParcelable(IntegrationManager.KEY_SOURCE_DATA, sourceData);
                intent.putExtra(IntegrationManager.KEY_INTENT_DATA, bundle);
            }

            final Bundle data = new Bundle();
            data.putParcelable(IntegrationManager.KEY_INTENT, intent);

            response.onResult(data);
        }

        /**
         * Создаёт результат обработки события.
         * @param bundlable экземпляр результата обработки события в формате {@link IBundlable}. Например, если служба обрабатывает событие {@link ru.evotor.framework.core.action.event.receipt.payment.PaymentSelectedEvent},
         *                  в параметр необходимо передать результат {@link ru.evotor.framework.core.action.event.receipt.payment.PaymentSelectedEventResult}.
         * @throws RemoteException
         */
        public final void onResult(IBundlable bundlable) throws RemoteException {
            onResult(bundlable == null ? null : bundlable.toBundle());
        }

        /**
         * Создаёт результат обработки события.
         * @param bundle экземпляр результата обработки события в формате {@link Bundle}. Например, если служба обрабатывает событие {@link ru.evotor.framework.core.action.event.receipt.payment.PaymentSelectedEvent},
         *               в параметр необходимо передать результат {@link ru.evotor.framework.core.action.event.receipt.payment.PaymentSelectedEventResult}.
         * @throws RemoteException
         */
        public final void onResult(Bundle bundle) throws RemoteException {
            Bundle result = new Bundle();
            result.putBundle(IntegrationManager.KEY_DATA, bundle);
            response.onResult(result);
        }

        /**
         * Обработчик ошибок.
         * @param errorCode код ошибки.
         * @param errorMessage текст сообщения об ошибке.
         * @throws RemoteException
         */
        public final void onError(int errorCode, String errorMessage) throws RemoteException {
            onError(errorCode, errorMessage, (Bundle) null);
        }

        /**
         * Обработчик ошибок.
         * @param errorCode код ошибки.
         * @param errorMessage текст сообщения об ошибке.
         * @param data данные ошибки в формате IBundlable.
         * @throws RemoteException
         */
        public final void onError(int errorCode, String errorMessage, IBundlable data) throws RemoteException {
            onError(errorCode, errorMessage, data == null ? null : data.toBundle());
        }

        /**
         * Обработчик ошибок.
         * @param errorCode код ошибки.
         * @param errorMessage текст сообщения об ошибке
         * @param data данные ошибки в формате Bundle.
         * @throws RemoteException
         */
        public final void onError(int errorCode, String errorMessage, Bundle data) throws RemoteException {
            response.onError(errorCode, errorMessage, data);
        }

        /**
         * Пропускает обработку события.
         * @throws RemoteException
         */
        public final void skip() throws RemoteException {
            Bundle result = new Bundle();
            result.putBoolean(IntegrationManager.KEY_SKIP, true);

            response.onResult(result);
        }
    }
}
