package ru.evotor.tspiot.result.model.offline;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.evotor.tspiot.Utils;
import ru.evotor.tspiot.result.model.VariableExpirations;
import ru.evotor.tspiot.result.model.base.BaseCodeCheck;

public class OfflineCodeCheck extends BaseCodeCheck implements Parcelable {

    /**
     * Версия OfflineCodeCheck
     */
    private final static int VERSION = 1;

    /**
     * Признак, определяющий, что GTIN из КИ находится в «сером списке GTIN»
     */
    @Nullable
    private final Boolean isGreyGtin;

    private OfflineCodeCheck(Parcel parcel) {
        super(parcel);

        Boolean isGreyGtin = null;
        if (parcel.dataAvail() > 0) {
            int version = parcel.readInt();
            if (version >= 1) {
                isGreyGtin = Utils.readBoolean(parcel);
            }
        }
        this.isGreyGtin = isGreyGtin;
    }

    private OfflineCodeCheck(Builder builder) {
        super(
                builder.cis,
                builder.found,
                builder.valid,
                builder.printView,
                builder.gtin,
                null,
                builder.verified,
                builder.realizable,
                builder.utilized,
                builder.variableExpirations,
                builder.isBlocked,
                null,
                builder.sold,
                builder.mrp,
                builder.smp
        );
        this.isGreyGtin = builder.isGreyGtin;
    }

    public OfflineCodeCheck(
            String cis,
            @Nullable Boolean found,
            @Nullable Boolean valid,
            @Nullable String printView,
            @Nullable String gtin,
            @Nullable Boolean verified,
            @Nullable Boolean realizable,
            @Nullable Boolean utilized,
            @Nullable VariableExpirations variableExpirations,
            @Nullable Boolean isBlocked,
            @Nullable Boolean sold,
            @Nullable Integer mrp,
            @Nullable Integer smp
    ) {
        this(new Builder(cis)
                .found(found)
                .valid(valid)
                .printView(printView)
                .gtin(gtin)
                .verified(verified)
                .realizable(realizable)
                .utilized(utilized)
                .variableExpirations(variableExpirations)
                .isBlocked(isBlocked)
                .sold(sold)
                .mrp(mrp)
                .smp(smp));
    }

    public static Builder builder(@NonNull String cis) {
        return new Builder(cis);
    }

    public static Builder builder(@NonNull OfflineCodeCheck source) {
        return new Builder(source);
    }

    @Nullable
    public Boolean isGreyGtin() {
        return isGreyGtin;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        super.write(parcel, flags);
        if (isGreyGtin != null) {
            parcel.writeInt(VERSION);
            parcel.writeValue(isGreyGtin);
        }
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString() + "IsGreyGtin: " + Utils.toString(isGreyGtin) + "\n";
    }

    public final static Creator<OfflineCodeCheck> CREATOR = new Creator<OfflineCodeCheck>() {
        @Override
        public OfflineCodeCheck createFromParcel(Parcel parcel) {
            return new OfflineCodeCheck(parcel);
        }

        @Override
        public OfflineCodeCheck[] newArray(int size) {
            return new OfflineCodeCheck[size];
        }
    };

    public static final class Builder {

        private final String cis;
        @Nullable
        private Boolean found;
        @Nullable
        private Boolean valid;
        @Nullable
        private String printView;
        @Nullable
        private String gtin;
        @Nullable
        private Boolean verified;
        @Nullable
        private Boolean realizable;
        @Nullable
        private Boolean utilized;
        @Nullable
        private VariableExpirations variableExpirations;
        @Nullable
        private Boolean isBlocked;
        @Nullable
        private Boolean sold;
        @Nullable
        private Integer mrp;
        @Nullable
        private Integer smp;
        @Nullable
        private Boolean isGreyGtin;

        public Builder(@NonNull String cis) {
            this.cis = cis;
        }

        private Builder(@NonNull OfflineCodeCheck source) {
            this.cis = source.getCis();
            this.found = source.isFound();
            this.valid = source.isValid();
            this.printView = source.getPrintView();
            this.gtin = source.getGtin();
            this.verified = source.isVerified();
            this.realizable = source.isRealizable();
            this.utilized = source.isUtilized();
            this.variableExpirations = source.getVariableExpirations();
            this.isBlocked = source.getBlocked();
            this.sold = source.isSold();
            this.mrp = source.getMrp();
            this.smp = source.getSmp();
            this.isGreyGtin = source.isGreyGtin();
        }

        public Builder found(@Nullable Boolean found) {
            this.found = found;
            return this;
        }

        public Builder valid(@Nullable Boolean valid) {
            this.valid = valid;
            return this;
        }

        public Builder printView(@Nullable String printView) {
            this.printView = printView;
            return this;
        }

        public Builder gtin(@Nullable String gtin) {
            this.gtin = gtin;
            return this;
        }

        public Builder verified(@Nullable Boolean verified) {
            this.verified = verified;
            return this;
        }

        public Builder realizable(@Nullable Boolean realizable) {
            this.realizable = realizable;
            return this;
        }

        public Builder utilized(@Nullable Boolean utilized) {
            this.utilized = utilized;
            return this;
        }

        public Builder variableExpirations(@Nullable VariableExpirations variableExpirations) {
            this.variableExpirations = variableExpirations;
            return this;
        }

        public Builder isBlocked(@Nullable Boolean isBlocked) {
            this.isBlocked = isBlocked;
            return this;
        }

        public Builder sold(@Nullable Boolean sold) {
            this.sold = sold;
            return this;
        }

        public Builder mrp(@Nullable Integer mrp) {
            this.mrp = mrp;
            return this;
        }

        public Builder smp(@Nullable Integer smp) {
            this.smp = smp;
            return this;
        }

        public Builder isGreyGtin(@Nullable Boolean isGreyGtin) {
            this.isGreyGtin = isGreyGtin;
            return this;
        }

        public OfflineCodeCheck build() {
            return new OfflineCodeCheck(this);
        }
    }
}
