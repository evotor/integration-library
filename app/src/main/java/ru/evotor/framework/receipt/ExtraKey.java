package ru.evotor.framework.receipt;

/**
 * Created by a.kuznetsov on 02/05/2017.
 */

public class ExtraKey {
    private final String identity;
    private final String appId;
    private final String description;

    public ExtraKey(String identity, String appId, String description) {
        this.identity = identity;
        this.appId = appId;
        this.description = description;
    }

    public String getIdentity() {
        return identity;
    }

    public String getAppId() {
        return appId;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExtraKey extraKey = (ExtraKey) o;

        if (identity != null ? !identity.equals(extraKey.identity) : extraKey.identity != null)
            return false;
        if (appId != null ? !appId.equals(extraKey.appId) : extraKey.appId != null) return false;
        return description != null ? description.equals(extraKey.description) : extraKey.description == null;

    }

    @Override
    public int hashCode() {
        int result = identity != null ? identity.hashCode() : 0;
        result = 31 * result + (appId != null ? appId.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
