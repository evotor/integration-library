package ru.evotor.framework.core.action.command.print_receipt_command;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.IBundlable;

public class PrintReceiptCommandResult implements IBundlable {

    private static final String KEY_RECEIPT_UUID = "receiptUuid";
    private static final String KEY_RECEIPT_NUMBER = "receiptNumber";

    /**
     * Нужна синхронизация даты/времени ККМ и терминала
     */
    public static final int ERROR_CODE_DATETIME_SYNC_REQUIRED = -1;
    /**
     * Время сессии превысило 24 часа
     */
    public static final int ERROR_CODE_SESSION_TIME_EXPIRED = -2;
    /**
     * В интерет чеках поля 'эл.почта' и/или 'телефон' клиента должны быть заполнены
     */
    public static final int ERROR_CODE_EMAIL_AND_PHONE_ARE_NULL = -3;
    /**
     * ККМ в данный момент выполняет другую операцию
     */
    public static final int ERROR_CODE_KKM_IS_BUSY = -4;
    /**
     * Нет авторизованного пользователя на терминале
     */
    public static final int ERROR_CODE_NO_AUTHENTICATED_USER = -5;
    /**
     * Ошибка создания документа для печати
     */
    public static final int ERROR_CODE_PRINT_DOCUMENT_CREATION_FAILED = -6;
    /**
     * У приложения нет необходимого разрешения (permission)
     */
    public static final int ERROR_CODE_NO_PERMISSION = -7;
    /**
     * Нет позиций в чеке
     */
    public static final int ERROR_CODE_NO_POSITIONS = -8;
    /**
     * Нет платежей в чеке
     */
    public static final int ERROR_CODE_NO_PAYMENTS = -9;

    @Nullable
    public static PrintReceiptCommandResult create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }

        String receiptUuid = bundle.getString(KEY_RECEIPT_UUID);
        String receiptNumber = bundle.getString(KEY_RECEIPT_NUMBER);
        if (receiptUuid == null || receiptNumber == null) {
            return null;
        }
        return new PrintReceiptCommandResult(
                receiptUuid,
                receiptNumber
        );
    }

    @NonNull
    private final String receiptUuid;
    @NonNull
    private final String receiptNumber;

    public PrintReceiptCommandResult(
            @NonNull String receiptUuid,
            @NonNull String receiptNumber
    ) {
        this.receiptUuid = receiptUuid;
        this.receiptNumber = receiptNumber;
    }

    @Override
    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_RECEIPT_UUID, receiptUuid);
        bundle.putString(KEY_RECEIPT_NUMBER, receiptNumber);
        return bundle;
    }

    @NonNull
    public String getReceiptUuid() {
        return receiptUuid;
    }

    @NonNull
    public String getReceiptNumber() {
        return receiptNumber;
    }
}
