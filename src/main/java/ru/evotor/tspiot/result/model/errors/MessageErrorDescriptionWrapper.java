package ru.evotor.tspiot.result.model.errors;

import android.os.Parcel;

public sealed abstract class MessageErrorDescriptionWrapper extends TsPioTErrorsDescriptionWrapper<MessageErrorDescription> {
    final protected MessageErrorDescription errorDescription;

    protected MessageErrorDescriptionWrapper(Parcel parcel) {
        super(parcel);
        this.errorDescription = parcel.readParcelable(MessageErrorDescription.class.getClassLoader());
    }

    public MessageErrorDescriptionWrapper(MessageErrorDescription errorDescription) {
        this.errorDescription = errorDescription;
    }

    @Override
    public MessageErrorDescription getErrorDescription() { return errorDescription; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(errorDescription, flags);
    }

    /** Нет доступных CDN для онлайн проверки */
    public static final class CdnNotFoundError extends MessageErrorDescriptionWrapper {
        private CdnNotFoundError(Parcel parcel) { super(parcel); }

        public CdnNotFoundError(MessageErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<CdnNotFoundError> CREATOR = new BaseCreator<>(CdnNotFoundError.class);
    }

    /** ТСПиОТ не зарегистрирован */
    public static final class NotRegistered extends MessageErrorDescriptionWrapper {
        private NotRegistered(Parcel parcel) { super(parcel); }

        public NotRegistered(MessageErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<NotRegistered> CREATOR = new BaseCreator<>(NotRegistered.class);
    }

    /** Не получена конфигурация ТСПиОТ */
    public static final class NotConfigured extends MessageErrorDescriptionWrapper {
        private NotConfigured(Parcel parcel) { super(parcel); }

        public NotConfigured(MessageErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<NotConfigured> CREATOR = new BaseCreator<>(NotConfigured.class);
    }

    /** Отсутствует пермишн PMSR в манифесте приложения */
    public static final class NotFoundPermission extends MessageErrorDescriptionWrapper {
        private NotFoundPermission(Parcel parcel) { super(parcel); }

        public NotFoundPermission(MessageErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<NotFoundPermission> CREATOR = new BaseCreator<>(NotFoundPermission.class);
    }

    /** Отсутствует PMSR_ID в манифесте приложения */
    public static final class NotFoundPmsrId extends MessageErrorDescriptionWrapper {
        private NotFoundPmsrId(Parcel parcel) { super(parcel); }

        public NotFoundPmsrId(MessageErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<NotFoundPmsrId> CREATOR = new BaseCreator<>(NotFoundPmsrId.class);
    }

    /** Отсутствует PMSR_TOKEN в манифесте приложения */
    public static final class NotFoundPmsrToken extends MessageErrorDescriptionWrapper {
        private NotFoundPmsrToken(Parcel parcel) { super(parcel); }

        public NotFoundPmsrToken(MessageErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<NotFoundPmsrToken> CREATOR = new BaseCreator<>(NotFoundPmsrToken.class);
    }

    /** Не удалось получить appName вызывающего приложения */
    public static final class FailedGetAppName extends MessageErrorDescriptionWrapper {
        private FailedGetAppName(Parcel parcel) { super(parcel); }

        public FailedGetAppName(MessageErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<FailedGetAppName> CREATOR = new BaseCreator<>(FailedGetAppName.class);
    }

    /** Не удалось получить версию вызывающего приложения */
    public static final class FailedGetAppVersion extends MessageErrorDescriptionWrapper {
        private FailedGetAppVersion(Parcel parcel) { super(parcel); }

        public FailedGetAppVersion(MessageErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<FailedGetAppVersion> CREATOR = new BaseCreator<>(FailedGetAppVersion.class);
    }

    /** Неизвестная ошибка */
    public static final class UnknownError extends MessageErrorDescriptionWrapper {
        private UnknownError(Parcel parcel) { super(parcel); }

        public UnknownError(MessageErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<UnknownError> CREATOR = new BaseCreator<>(UnknownError.class);
    }

    /** Закончился срок действия лицензии */
    public static final class LicenseExpired extends MessageErrorDescriptionWrapper {
        private LicenseExpired(Parcel parcel) { super(parcel); }

        public LicenseExpired(MessageErrorDescription errorDescription) { super(errorDescription); }

        public static final Creator<LicenseExpired> CREATOR = new BaseCreator<>(LicenseExpired.class);
    }
}
