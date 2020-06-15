package ru.evotor.framework.payment;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.math.BigDecimal;

import ru.evotor.framework.component.PaymentPerformer;
import ru.evotor.framework.core.action.datamapper.PaymentPerformerMapper;
import ru.evotor.framework.core.action.event.receipt.payment.PaymentEvent;

/**
 * Платёж, которым покупатель оплачивает чек.
 * Список платежей необходимо возвращать, например, при разделении чека на несколько платежей (см. {@link ru.evotor.framework.core.action.event.receipt.payment.PaymentSelectedEventResult})
 * <p>
 * Содержит сумму и цель оплаты (платёжную систему и аккаунт).
 * <p>
 * Вы можете получить платёж с помощью метода {@link PaymentEvent#getPaymentPurpose()}.
 */
public class PaymentPurpose implements Parcelable {

    /**
     * Идентификатор платежа.
     */
    @Nullable
    private final String identifier;
    /**
     * Идентификатор платёжной системы.
     * @deprecated To define payment paymentSystem use {@link #paymentPerformer} instead
     */
    @Nullable
    @Deprecated
    private final String paymentSystemId;
    /**
     * Компонент (служба, операция и т.д.) приложения, который будет выполнять оплату.
     */
    @NonNull
    private final PaymentPerformer paymentPerformer;
    /**
     * Сумма платежа.
     */
    @NonNull
    private final BigDecimal total;
    /**
     * Идентификатор аккаунта в рамках платёжной системы.
     */
    @Nullable
    private final String accountId;
    /**
     * Сообщение, которое будет показано пользователю в момент оплаты.
     */
    @Nullable
    private final String userMessage;

    public PaymentPurpose(@Nullable String identifier, @Nullable String paymentSystemId, @Nullable PaymentPerformer paymentPerformer, @NonNull BigDecimal total, @Nullable String accountId, @Nullable String userMessage) {
        this.identifier = identifier;
        this.paymentSystemId = paymentSystemId;
        this.paymentPerformer = paymentPerformer;
        this.total = total;
        this.accountId = accountId;
        this.userMessage = userMessage;
    }

    @Nullable
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Идентификатор платёжной системы
     * @deprecated To define payment paymentSystem use {@link #paymentPerformer} instead
     */
    @Nullable
    @Deprecated
    public String getPaymentSystemId() {
        return paymentSystemId;
    }

    @Nullable
    public PaymentPerformer getPaymentPerformer() {
        return paymentPerformer;
    }

    @NonNull
    public BigDecimal getTotal() {
        return total;
    }

    @Nullable
    public String getAccountId() {
        return accountId;
    }

    @Nullable
    public String getUserMessage() {
        return userMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentPurpose that = (PaymentPurpose) o;

        if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null)
            return false;

        String paymentSystemId = paymentPerformer != null ? paymentPerformer.getPaymentSystem() != null ? paymentPerformer.getPaymentSystem().getPaymentSystemId() : null : null;
        String thatPaymentSystemId = that.paymentPerformer != null ? that.paymentPerformer.getPaymentSystem() != null ? that.paymentPerformer.getPaymentSystem().getPaymentSystemId() : null : null;
        if (paymentSystemId != null ? !paymentSystemId.equals(thatPaymentSystemId) : thatPaymentSystemId != null)
            return false;

        String packageName = paymentPerformer != null ? paymentPerformer.getPackageName() : null;
        String thatPackageName = that.paymentPerformer != null ? that.paymentPerformer.getPackageName() : null;
        if (packageName != null ? !packageName.equals(thatPackageName) : thatPackageName != null)
            return false;

        String componentName = paymentPerformer != null ? paymentPerformer.getComponentName() : null;
        String thatComponentName = that.paymentPerformer != null ? that.paymentPerformer.getComponentName() : null;
        if (componentName != null ? !componentName.equals(thatComponentName) : thatComponentName != null)
            return false;

        String appUuid = paymentPerformer != null ? paymentPerformer.getAppUuid() : null;
        String thatAppUuid = that.paymentPerformer != null ? that.paymentPerformer.getAppUuid() : null;
        if (appUuid != null ? !appUuid.equals(thatAppUuid) : thatAppUuid != null)
            return false;

        String appName = paymentPerformer != null ? paymentPerformer.getAppName() : null;
        String thatAppName = that.paymentPerformer != null ? that.paymentPerformer.getAppName() : null;
        if (appName != null ? !appName.equals(thatAppName) : thatAppName != null)
            return false;

        return total.equals(that.total)
                && (accountId != null
                    ? accountId.equals(that.accountId)
                    : that.accountId == null)
                && (userMessage != null
                    ? userMessage.equals(that.userMessage)
                    : that.userMessage == null);

    }

    @Override
    public int hashCode() {
        int result = identifier != null ? identifier.hashCode() : 0;

        String paymentSystemId = paymentPerformer != null ? paymentPerformer.getPaymentSystem() != null ? paymentPerformer.getPaymentSystem().getPaymentSystemId() : null : null;
        result = 31 * result + (paymentSystemId != null ? paymentSystemId.hashCode() : 0);

        String packageName = paymentPerformer != null ? paymentPerformer.getPackageName() : null;
        result = 31 * result + (packageName != null ? packageName.hashCode() : 0);
        String componentName = paymentPerformer != null ? paymentPerformer.getComponentName() : null;
        result = 31 * result + (componentName != null ? componentName.hashCode() : 0);
        String appUuid = paymentPerformer != null ? paymentPerformer.getAppUuid() : null;
        result = 31 * result + (appUuid != null ? appUuid.hashCode() : 0);
        String appName = paymentPerformer != null ? paymentPerformer.getAppName() : null;
        result = 31 * result + (appName != null ? appName.hashCode() : 0);

        result = 31 * result + total.hashCode();
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (userMessage != null ? userMessage.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.identifier);
        dest.writeBundle(PaymentPerformerMapper.INSTANCE.toBundle(this.paymentPerformer));
        dest.writeString(this.total.toPlainString());
        dest.writeString(this.accountId);
        dest.writeString(this.userMessage);
    }

    protected PaymentPurpose(Parcel in) {
        this.identifier = in.readString();
        this.paymentSystemId = in.readString();
        this.paymentPerformer = PaymentPerformerMapper.INSTANCE.fromBundle(in.readBundle());
        this.total = new BigDecimal(in.readString());
        this.accountId = in.readString();
        this.userMessage = in.readString();
    }

    public static final Creator<PaymentPurpose> CREATOR = new Creator<PaymentPurpose>() {
        @Override
        public PaymentPurpose createFromParcel(Parcel source) {
            return new PaymentPurpose(source);
        }

        @Override
        public PaymentPurpose[] newArray(int size) {
            return new PaymentPurpose[size];
        }
    };
}
