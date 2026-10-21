package ru.evotor.tspiot.result.model.base;

import android.os.Parcel;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.evotor.tspiot.BaseTsPioTObject;
import ru.evotor.tspiot.Utils;
import ru.evotor.tspiot.result.model.VariableExpirations;

public abstract class BaseCodeCheck extends BaseTsPioTObject {

    /**
     * Версия BaseCodeCheck
     */
    private final static int VERSION = 2;

    protected int baseCodeCheckObjectVersion = 0;

    /**
     * КИ / КиЗ из запроса
     */
    protected final String cis;

    /**
     * Признак наличия кода в ГИС МТ
     */
    @Nullable
    protected final Boolean found;

    /**
     * Результат проверки валидности структуры КИ / КиЗ
     */
    @Nullable
    protected final Boolean valid;

    /**
     * КИ без крипто-подписи / КиЗ
     */
    @Nullable
    protected final String printView;

    /**
     * Код товара
     */
    @Nullable
    protected final String gtin;

    /**
     * Массив идентификаторов товарных групп
     */
    @Nullable
    protected final int[] groupIds;

    /**
     * Результат проверки крипто-подписи КМ
     */
    @Nullable
    protected final Boolean verified;

    /**
     * Признак возможности реализации КИ / КиЗ
     */
    @Nullable
    protected final Boolean realizable;

    /**
     * Признак нанесения КИ / КиЗ на упаковку
     */
    @Nullable
    protected final Boolean utilized;

    /**
     * Информация о вариативном сроке годности
     */
    @Nullable
    protected final VariableExpirations variableExpirations;

    /**
     * Признак того, что розничная продажа продукции заблокирована по решению ОГВ
     */
    @Nullable
    protected final Boolean isBlocked;

    /**
     * Органы государственной власти, установившие блокировку на КИ
     */
    @Nullable
    protected final String[] ogvs;

    /**
     * Признак продажи товара
     */
    @Nullable
    protected final Boolean sold;

    /**
     * Максимальная розничная цена
     */
    @Nullable
    protected final Integer mrp;

    /**
     * Единая минимальная цена (ЕМЦ)
     */
    @Nullable
    protected final Integer smp;

    protected BaseCodeCheck(Parcel parcel) {
        baseCodeCheckObjectVersion = parcel.readInt();
        Parcel local = createLocalParcelToRead(parcel, baseCodeCheckObjectVersion, 2);

        this.cis = local.readString();
        this.found = Utils.readBoolean(local);
        this.valid = Utils.readBoolean(local);
        this.printView = local.readString();
        this.gtin = local.readString();
        this.groupIds = local.createIntArray();
        this.verified = Utils.readBoolean(local);
        this.realizable = Utils.readBoolean(local);
        this.utilized = Utils.readBoolean(local);
        this.variableExpirations = local.readTypedObject(VariableExpirations.CREATOR);
        this.isBlocked = Utils.readBoolean(local);
        this.ogvs = local.createStringArray();
        this.sold = Utils.readBoolean(local);
        this.mrp = Utils.readInteger(local);
        this.smp = Utils.readInteger(local);

        endLocalParcelRead(local, baseCodeCheckObjectVersion, 2);
    }

    public BaseCodeCheck(
            String cis,
            @Nullable Boolean found,
            @Nullable Boolean valid,
            @Nullable String printView,
            @Nullable String gtin,
            @Nullable int[] groupIds,
            @Nullable Boolean verified,
            @Nullable Boolean realizable,
            @Nullable Boolean utilized,
            @Nullable VariableExpirations variableExpirations,
            @Nullable Boolean isBlocked,
            @Nullable String[] ogvs,
            @Nullable Boolean sold,
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
        writeToParcel(parcel, local -> {
            local.writeString(cis);
            local.writeValue(found);
            local.writeValue(valid);
            local.writeString(printView);
            local.writeString(gtin);
            local.writeIntArray(groupIds);
            local.writeValue(verified);
            local.writeValue(realizable);
            local.writeValue(utilized);
            local.writeTypedObject(this.variableExpirations, flags);
            local.writeValue(isBlocked);
            local.writeStringArray(ogvs);
            local.writeValue(sold);
            local.writeValue(mrp);
            local.writeValue(smp);
        });
    }

    @Nullable
    public Boolean getBlocked() {
        return isBlocked;
    }

    @Nullable
    public Boolean isFound() {
        return found;
    }

    @Nullable
    public Boolean isRealizable() {
        return realizable;
    }

    @Nullable
    public Boolean isSold() {
        return sold;
    }

    @Nullable
    public Boolean isUtilized() {
        return utilized;
    }

    @Nullable
    public Boolean isValid() {
        return valid;
    }

    @Nullable
    public Boolean isVerified() {
        return verified;
    }

    @Nullable
    public int[] getGroupIds() {
        return groupIds;
    }

    @Nullable
    public Integer getMrp() {
        return mrp;
    }

    @Nullable
    public Integer getSmp() {
        return smp;
    }

    public String getCis() {
        return cis;
    }

    @Nullable
    public String getGtin() {
        return gtin;
    }

    @Nullable
    public String getPrintView() {
        return printView;
    }

    @Nullable
    public String[] getOgvs() {
        return ogvs;
    }

    @Nullable
    public VariableExpirations getVariableExpirations() {
        return variableExpirations;
    }

    @NonNull
    @Override
    public String toString() {
        return "Cis: " + Utils.toString(cis) + "\n" +
                "IsFound: " + Utils.toString(found) + "\n" +
                "IsValid: " + Utils.toString(valid) + "\n" +
                "PrintView: " + Utils.toString(printView) + "\n" +
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
