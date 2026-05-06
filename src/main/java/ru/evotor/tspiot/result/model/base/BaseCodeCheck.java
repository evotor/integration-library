package ru.evotor.tspiot.result.model.base;

import android.os.Parcel;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ru.evotor.tspiot.Utils;
import ru.evotor.tspiot.result.model.VariableExpirations;

public abstract class BaseCodeCheck {

    /** Версия BaseCodeCheck */
    private final static int VERSION = 1;

    /** КИ / КиЗ из запроса */
    protected final String cis;

    /** Признак наличия кода в ГИС МТ */
    protected final boolean found;

    /** Результат проверки валидности структуры КИ / КиЗ */
    protected final boolean valid;

    /** КИ без крипто-подписи / КиЗ */
    protected final String printView;

    /** Код товара */
    protected final String gtin;

    /** Массив идентификаторов товарных групп */
    protected final int[] groupIds;

    /** Результат проверки крипто-подписи КМ */
    protected final boolean verified;

    /** Признак возможности реализации КИ / КиЗ */
    protected final boolean realizable;

    /** Признак нанесения КИ / КиЗ на упаковку */
    protected final boolean utilized;

    /** Информация о вариативном сроке годности */
    @Nullable
    protected final VariableExpirations variableExpirations;

    /** Признак того, что розничная продажа продукции заблокирована по решению ОГВ */
    @Nullable protected final Boolean isBlocked;

    /** Органы государственной власти, установившие блокировку на КИ */
    protected final String[] ogvs;

    /** Признак продажи товара */
    protected final boolean sold;

    /** Максимальная розничная цена */
    @Nullable protected final Integer mrp;

    /** Единая минимальная цена (ЕМЦ) */
    @Nullable protected final Integer smp;

    protected BaseCodeCheck(Parcel parcel) {
        int version = parcel.readInt();
        this.cis = parcel.readString();
        this.found = parcel.readInt() == 1;
        this.valid = parcel.readInt() == 1;
        this.printView = parcel.readString();
        this.gtin = parcel.readString();
        this.groupIds = parcel.createIntArray();
        this.verified = parcel.readInt() == 1;
        this.realizable = parcel.readInt() == 1;
        this.utilized = parcel.readInt() == 1;
        this.variableExpirations = parcel.readTypedObject(VariableExpirations.CREATOR);
        this.isBlocked = Utils.readBoolean(parcel);
        this.ogvs = parcel.createStringArray();
        this.sold = parcel.readInt() == 1;
        this.mrp = Utils.readInteger(parcel);
        this.smp = Utils.readInteger(parcel);
    }

    public BaseCodeCheck(
            String cis,
            boolean found,
            boolean valid,
            String printView,
            String gtin,
            int[] groupIds,
            boolean verified,
            boolean realizable,
            boolean utilized,
            @Nullable VariableExpirations variableExpirations,
            @Nullable Boolean isBlocked,
            String[] ogvs,
            boolean sold,
            @Nullable Integer mrp,
            @Nullable Integer smp
    ) {
        this.cis = cis;
        this.found = found;
        this.valid = valid;
        this.printView = printView;
        this.gtin = gtin;
        this.groupIds = groupIds;
        this.verified = verified;
        this.realizable = realizable;
        this.utilized = utilized;
        this.variableExpirations = variableExpirations;
        this.isBlocked = isBlocked;
        this.ogvs = ogvs;
        this.sold = sold;
        this.mrp = mrp;
        this.smp = smp;
    }

    protected void write(Parcel parcel, int flags) {
        parcel.writeInt(VERSION);
        parcel.writeString(cis);
        parcel.writeInt(found ? 1 : 0);
        parcel.writeInt(valid ? 1 : 0);
        parcel.writeString(printView);
        parcel.writeString(gtin);
        parcel.writeIntArray(groupIds);
        parcel.writeInt(verified ? 1 : 0);
        parcel.writeInt(realizable ? 1 : 0);
        parcel.writeInt(utilized ? 1 : 0);
        parcel.writeTypedObject(this.variableExpirations, flags);
        parcel.writeValue(isBlocked);
        parcel.writeStringArray(ogvs);
        parcel.writeInt(sold ? 1 : 0);
        parcel.writeValue(mrp);
        parcel.writeValue(smp);
    }

    @Nullable
    public Boolean getBlocked() { return isBlocked; }

    public boolean isFound() { return found; }

    public boolean isRealizable() { return realizable; }

    public boolean isSold() { return sold; }

    public boolean isUtilized() { return utilized; }

    public boolean isValid() { return valid; }

    public boolean isVerified() { return verified; }

    public int[] getGroupIds() { return groupIds; }

    @Nullable
    public Integer getMrp() { return mrp; }

    @Nullable
    public Integer getSmp() { return smp; }

    public String getCis() { return cis; }

    public String getGtin() { return gtin; }

    public String getPrintView() { return printView; }

    public String[] getOgvs() { return ogvs; }

    @Nullable
    public VariableExpirations getVariableExpirations() { return variableExpirations; }

    @NonNull
    @Override
    public String toString() {
        return "Cis: " + Utils.toString(cis) + "\n" +
                "IsFound: " + Utils.toString(found) + "\n" +
                "IsValid: " + Utils.toString(valid) + "\n" +
                "PrintView" + Utils.toString(printView) + "\n" +
                "Gtin: " + Utils.toString(gtin) + "\n" +
                "GroupIds: " + Utils.toString(groupIds) + "\n" +
                "IsVerified: " + Utils.toString(verified) + "\n" +
                "IsRealizable: " + Utils.toString(realizable) + "\n" +
                "IsUtilized: " + Utils.toString(utilized) + "\n" +
                "VariableExpirations: " + Utils.toString(variableExpirations) + "\n" +
                "IsBlocked: " + Utils.toString(isBlocked) + "\n" +
                "Ogvs: " + Utils.toString(ogvs) + "\n" +
                "IsSold: " + Utils.toString(sold) + "\n" +
                "Mrp: " + Utils.toString(mrp) + "\n" +
                "Smp: " + Utils.toString(smp) + "\n";
    }
}
