package ru.evotor.tspiot.result.model.errors;

import android.os.Parcel;

public sealed abstract class MessageErrorDescriptionError extends TsPioTErrorsDescription<MessageErrorDescription> {
    final protected MessageErrorDescription errorDescription;

    protected MessageErrorDescriptionError(Parcel parcel) {
        super(parcel);
        this.errorDescription = parcel.readParcelable(MessageErrorDescription.class.getClassLoader());
    }

    public MessageErrorDescriptionError(MessageErrorDescription errorDescription) {
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
    public static final class CdnNotFoundError extends MessageErrorDescriptionError {
        private CdnNotFoundError(Parcel parcel) { super(parcel); }

        public CdnNotFoundError(MessageErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<CdnNotFoundError> CREATOR = new BaseCreator<>(CdnNotFoundError.class);
    }

    /** ТСПиОТ не зарегистрирован */
    public static final class NotRegistered extends MessageErrorDescriptionError {
        private NotRegistered(Parcel parcel) { super(parcel); }

        public NotRegistered(MessageErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<NotRegistered> CREATOR = new BaseCreator<>(NotRegistered.class);
    }

    /** Не получена конфигурация ТСПиОТ */
    public static final class NotConfigured extends MessageErrorDescriptionError {
        private NotConfigured(Parcel parcel) { super(parcel); }

        public NotConfigured(MessageErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<NotConfigured> CREATOR = new BaseCreator<>(NotConfigured.class);
    }

    /** Отсутствует пермишн PMSR в манифесте приложения */
    public static final class NotFoundPermission extends MessageErrorDescriptionError {
        private NotFoundPermission(Parcel parcel) { super(parcel); }

        public NotFoundPermission(MessageErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<NotFoundPermission> CREATOR = new BaseCreator<>(NotFoundPermission.class);
    }

    /** Отсутствует PMSR_ID в манифесте приложения */
    public static final class NotFoundPmsrId extends MessageErrorDescriptionError {
        private NotFoundPmsrId(Parcel parcel) { super(parcel); }

        public NotFoundPmsrId(MessageErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<NotFoundPmsrId> CREATOR = new BaseCreator<>(NotFoundPmsrId.class);
    }

    /** Отсутствует PMSR_TOKEN в манифесте приложения */
    public static final class NotFoundPmsrToken extends MessageErrorDescriptionError {
        private NotFoundPmsrToken(Parcel parcel) { super(parcel); }

        public NotFoundPmsrToken(MessageErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<NotFoundPmsrToken> CREATOR = new BaseCreator<>(NotFoundPmsrToken.class);
    }

    /** Не удалось получить appName вызывающего приложения */
    public static final class FailedGetAppName extends MessageErrorDescriptionError {
        private FailedGetAppName(Parcel parcel) { super(parcel); }

        public FailedGetAppName(MessageErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<FailedGetAppName> CREATOR = new BaseCreator<>(FailedGetAppName.class);
    }

    /** Не удалось получить версию вызывающего приложения */
    public static final class FailedGetAppVersion extends MessageErrorDescriptionError {
        private FailedGetAppVersion(Parcel parcel) { super(parcel); }

        public FailedGetAppVersion(MessageErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<FailedGetAppVersion> CREATOR = new BaseCreator<>(FailedGetAppVersion.class);
    }

    /** Неизвестная ошибка */
    public static final class UnknownError extends MessageErrorDescriptionError {
        private UnknownError(Parcel parcel) { super(parcel); }

        public UnknownError(MessageErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<UnknownError> CREATOR = new BaseCreator<>(UnknownError.class);
    }
}
