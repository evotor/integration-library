package ru.evotor.tspiot.result.model.errors;

import android.os.Parcel;

public sealed abstract class CodeMessageErrorDescriptionWrapper extends TsPioTErrorsDescriptionWrapper<CodeMessageErrorDescription> {
    protected final CodeMessageErrorDescription errorDescription;

    protected CodeMessageErrorDescriptionWrapper(Parcel parcel) {
        super(parcel);
        this.errorDescription = parcel.readParcelable(CodeMessageErrorDescription.class.getClassLoader());
    }

    public CodeMessageErrorDescriptionWrapper(CodeMessageErrorDescription errorDescription) {
        this.errorDescription = errorDescription;
    }

    @Override
    public CodeMessageErrorDescription getErrorDescription() { return errorDescription; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(errorDescription, flags);
    }

    /** Ошибка на стороне ККМ */
    public static final class KkmError extends CodeMessageErrorDescriptionWrapper {
        private KkmError(Parcel parcel) { super(parcel); }

        public KkmError(CodeMessageErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<KkmError> CREATOR = new BaseCreator<>(KkmError.class);
    }

    /** Ошибка на стороне локального модуля */
    public static final class LocalModuleError extends CodeMessageErrorDescriptionWrapper {
        private LocalModuleError(Parcel parcel) { super(parcel); }

        public LocalModuleError(CodeMessageErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<LocalModuleError> CREATOR = new BaseCreator<>(LocalModuleError.class);
    }

    /** Не удалось получить fnSid (Не удалось установить доверенный канал) */
    public static final class FnSidError extends CodeMessageErrorDescriptionWrapper {
        private FnSidError(Parcel parcel) { super(parcel); }

        public FnSidError(CodeMessageErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<FnSidError> CREATOR = new BaseCreator<>(FnSidError.class);
    }

    /**
     * Аварийный режим. Продажа товара разрешена без проверки,
     *  действуют ограничения аварийного режима
     */
    public static final class EmergencyMode extends CodeMessageErrorDescriptionWrapper {
        private EmergencyMode(Parcel parcel) { super(parcel); }

        public EmergencyMode(CodeMessageErrorDescription errorDescription) {
            super(errorDescription);
        }

        public static final Creator<EmergencyMode> CREATOR = new BaseCreator<>(EmergencyMode.class);
    }
}
