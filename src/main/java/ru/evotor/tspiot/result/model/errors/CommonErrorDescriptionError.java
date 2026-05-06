package ru.evotor.tspiot.result.model.errors;

import android.os.Parcel;

public sealed abstract class CommonErrorDescriptionError extends TsPioTErrorsDescription<CommonErrorDescription> {
    protected final CommonErrorDescription errorDescription;

    protected CommonErrorDescriptionError(Parcel parcel) {
        super(parcel);
        this.errorDescription = parcel.readParcelable(CommonErrorDescription.class.getClassLoader());
    }

    public CommonErrorDescriptionError(CommonErrorDescription errorDescription) {
        this.errorDescription = errorDescription;
    }

    @Override
    public CommonErrorDescription getErrorDescription() { return errorDescription; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(errorDescription, flags);
    }

    /** Ошибка на стороне ККМ */
    public static final class KkmError extends CommonErrorDescriptionError {
        private KkmError(Parcel parcel) { super(parcel); }

        public KkmError(CommonErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<KkmError> CREATOR = new BaseCreator<>(KkmError.class);
    }

    /** Ошибка на стороне локального модуля */
    public static final class LocalModuleError extends CommonErrorDescriptionError {
        private LocalModuleError(Parcel parcel) { super(parcel); }

        public LocalModuleError(CommonErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<LocalModuleError> CREATOR = new BaseCreator<>(LocalModuleError.class);
    }

    /** Не удалось получить fnSid (Не удалось установить доверенный канал) */
    public static final class FnSidError extends CommonErrorDescriptionError {
        private FnSidError(Parcel parcel) { super(parcel); }

        public FnSidError(CommonErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<FnSidError> CREATOR = new BaseCreator<>(FnSidError.class);
    }

    /**
     * Аварийный режим. Продажа товара разрешена без проверки,
     *  действуют ограничения аварийного режима
     */
    public static final class EmergencyMode extends CommonErrorDescriptionError {
        private EmergencyMode(Parcel parcel) { super(parcel); }

        public EmergencyMode(CommonErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<EmergencyMode> CREATOR = new BaseCreator<>(EmergencyMode.class);
    }
}
