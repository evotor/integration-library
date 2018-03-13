/*
 * Copyright (c) 2014 ATOL (http://www.atol.com), all rights reserved
 */
package ru.evotor.framework.receipt;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;


public class Result implements Parcelable, Serializable {

    private static final long serialVersionUID = 6106269076155338045L;

    /**
     * Нет бумаги или открыта крышка (код ошибки ККМ 103, драйвера -3807).
     */
    public static final int ECR_ERROR_NO_PAPER = -3807;
    /**
     * Операция невозможна, недостаточно питания (код ошибки драйвера -3971).
     */
    public static final int ECR_ERROR_NOT_ENOUGH_POWER_SUPPLY = -3971;
    /**
     * Смена превысила 24 часа (код ошибки ККМ 136, драйвера -3822).
     */
    public static final int ECR_ERROR_SESSION_TIME_EXPIRED = -3822;
    /**
     * Недостаточно денег для выплаты (код ошибки ККМ 152, драйвера -3800).
     */
    public static final int ECR_ERROR_NOT_ENOUGH_CASH_IN_CASH_DESK = -3800;
    /**
     * Обнуленная касса, повторное гашение невозможно (код ошибки ККМ 143,
     * драйвера -3828).
     */
    public static final int ECR_ERROR_REPEATED_Z_REPORT = -3828;
    /**
     * Неопознанная ошибка код ошибки драйвера -199.
     */
    public static final int ECR_ERROR_UNKNOWN_ERROR = -199;

    /**
     * Ошибка обмена с ФН на уровне интерфейса I2C (код ошибки ККМ 210,
     * драйвера -3910)
     */
    public static final int ECR_ERROR_FN_IIC_INTERFACE = -3910;

    /**
     * Начисление налога на последнюю операцию невозможно (код ошибки ККМ 224,
     * драйвера -3970)
     */
    public static final int ECR_ERROR_TAXATION_CORRUPTED_ERROR = -3970;

    public static final int ECR_INTERNAL_ERROR = Integer.MIN_VALUE;

    public ResultCode result;
    public int errorCode;
    public String errorDescription;

    /**
     * true для эмулятора. false для нормального ККМ.
     */
    public boolean fakeResult = false;

    public Result() {

    }

    public Result(ResultCode resultCode) {
        this.result = resultCode;
        if (resultCode == ResultCode.PE_ERR_REMOTE_EXCEPTION) {
            errorCode = -1;
        }
    }

    public Result(ResultCode resultCode, String errorDescription) {
        this(resultCode);
        this.errorDescription = errorDescription;
    }

    public Result(Result objectToCopy) {
        this.result = objectToCopy.result;
        this.errorCode = objectToCopy.errorCode;
        this.errorDescription = objectToCopy.errorDescription;
        this.fakeResult = objectToCopy.fakeResult;
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel pc) {
            return new Result(pc);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public Result(Parcel in) {
        int resultOrdinal = in.readInt();
        if (resultOrdinal >= 0) {
            result = ResultCode.values()[resultOrdinal];
        }
        errorCode = in.readInt();
        errorDescription = in.readString();
        fakeResult = in.readInt() == 1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(result == null ? -1 : result.ordinal());
        dest.writeInt(errorCode);
        dest.writeString(errorDescription);
        dest.writeInt(fakeResult ? 1 : 0);
    }
}