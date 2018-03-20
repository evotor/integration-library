package ru.evotor.framework.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Атрибут
 */
public class DomainAttribute implements Parcelable {

    /**
     * Уникальный идентификатор атрибута
     */
    private String uuid;

    /**
     * Имя атрибута (ex. 'Цвет')
     */
    private String name;

    /**
     * Список значений атрибутов
     */
    private List<DomainAttributeValue> attributeValues;

    public static final Creator<DomainAttribute> CREATOR = new Creator<DomainAttribute>() {
        @Override
        public DomainAttribute createFromParcel(Parcel in) {
            return new DomainAttribute(in);
        }

        @Override
        public DomainAttribute[] newArray(int size) {
            return new DomainAttribute[size];
        }
    };

    private DomainAttribute(Parcel in) {
        this.uuid = in.readString();
        this.name = in.readString();
        this.attributeValues = new ArrayList<DomainAttributeValue>();
        in.readArrayList(DomainAttributeValue.class.getClassLoader());
    }

    private DomainAttribute(Builder builder) {
        this.uuid = builder.uuid;
        this.name = builder.name;
        this.attributeValues = builder.attributeValues;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public List<DomainAttributeValue> getAttributeValuesForAdapter() {
        return attributeValues;
    }

    public void setAttributeValues(List<DomainAttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uuid);
        dest.writeString(name);
        dest.writeList(attributeValues);
    }

    public static class Builder {

        private String uuid;
        private String name;
        private List<DomainAttributeValue> attributeValues;

        public Builder() {
        }

        public Builder uuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder attributeValues(List<DomainAttributeValue> attributeValues) {
            this.attributeValues = attributeValues;
            return this;
        }

        public DomainAttribute build() {
            return new DomainAttribute(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DomainAttribute that = (DomainAttribute) o;

        if (!uuid.equals(that.uuid)) return false;
        if (!name.equals(that.name)) return false;
        return attributeValues.equals(that.attributeValues);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + attributeValues.hashCode();
        return result;
    }
}
