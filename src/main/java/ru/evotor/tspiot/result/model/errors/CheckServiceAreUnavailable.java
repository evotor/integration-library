package ru.evotor.tspiot.result.model.errors;

import android.os.Parcel;

/**
 * ТС ПИоТ не смог получить ответ ни от ГИС МТ, ни от ЛМ ЧЗ.
 * Сервисы ЧЗ недоступны, имеет смысл повторить запрос позже.
 */
public final class CheckServiceAreUnavailable extends TsPioTErrorsDescriptionWrapper<CodesCheckErrorDescription> {
    private final CodesCheckErrorDescription errorDescription;

    public CheckServiceAreUnavailable(CodesCheckErrorDescription errorDescription) {
        this.errorDescription = errorDescription;
    }

    private CheckServiceAreUnavailable(Parcel parcel) {
        super(parcel);
        this.errorDescription = parcel.readParcelable(CodesCheckErrorDescription.class.getClassLoader());
    }

    @Override
    public CodesCheckErrorDescription getErrorDescription() { return errorDescription; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(errorDescription, flags);
    }

    public static final Creator<CheckServiceAreUnavailable> CREATOR = new BaseCreator<>(CheckServiceAreUnavailable.class);
}