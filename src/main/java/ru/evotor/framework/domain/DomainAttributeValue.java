package ru.evotor.framework.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Значение атрибута
 */
public class DomainAttributeValue implements Parcelable, Serializable {

    /**
     * Уникальный идентификатор атрибута
     */
    private String dictionaryUuid;

    /**
     * Имя атрибута (ex. 'Цвет')
     */
    private String dictionaryName;

    /**
     * Уникальный идентификатор значения атрибута
     */
    private String uuid;

    /**
     * Имя значения атрибута (ex. 'Черный')
     */
    private String name;

    public static final Creator<DomainAttributeValue> CREATOR = new Creator<DomainAttributeValue>() {
        @Override
        public DomainAttributeValue createFromParcel(Parcel in) {
            return new DomainAttributeValue(in);
        }

        @Override
        public DomainAttributeValue[] newArray(int size) {
            return new DomainAttributeValue[size];
        }
    };

    private DomainAttributeValue(Builder builder) {
        this.dictionaryUuid = builder.dictionaryUuid;
        this.dictionaryName = builder.dictionaryName;
        this.uuid = builder.uuid;
        this.name = builder.name;
    }

    protected DomainAttributeValue(Parcel in) {
        dictionaryUuid = in.readString();
        dictionaryName = in.readString();
        uuid = in.readString();
        name = in.readString();
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getDictionaryUuid() {
        return dictionaryUuid;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dictionaryUuid);
        dest.writeString(dictionaryName);
        dest.writeString(uuid);
        dest.writeString(name);
    }

    public static class Builder {

        private String dictionaryUuid;
        private String dictionaryName;
        private String uuid;
        private String name;

        public Builder() {
        }

        public Builder dictionaryUuid(String dictionaryUuid) {
            this.dictionaryUuid = dictionaryUuid;
            return this;
        }

        public Builder dictionaryName(String dictionaryName) {
            this.dictionaryName = dictionaryName;
            return this;
        }

        public Builder uuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public DomainAttributeValue build() {
            return new DomainAttributeValue(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DomainAttributeValue that = (DomainAttributeValue) o;

        if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
