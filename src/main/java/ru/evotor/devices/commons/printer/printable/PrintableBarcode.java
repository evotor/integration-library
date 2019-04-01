package ru.evotor.devices.commons.printer.printable;

import android.os.Parcel;

public class PrintableBarcode implements IPrintable {

    /**
     * Значение штрихкода
     */
    private final String barcodeValue;

    /**
     * Тип штрихкода
     */
    private final BarcodeType barcodeType;

    public PrintableBarcode(String barcodeValue, BarcodeType barcodeType) {
        this.barcodeValue = barcodeValue;
        this.barcodeType = barcodeType;
    }

    private PrintableBarcode(Parcel parcel) {
        barcodeValue = parcel.readString();
        int barcodeTypeNumber = parcel.readInt();
        barcodeType = BarcodeType.values()[barcodeTypeNumber < BarcodeType.values().length ? barcodeTypeNumber : 0];
    }

    public String getBarcodeValue() {
        return barcodeValue;
    }

    public BarcodeType getBarcodeType() {
        return barcodeType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(barcodeValue);
        parcel.writeInt(barcodeType.ordinal());
    }

    public static final Creator<PrintableBarcode> CREATOR = new Creator<PrintableBarcode>() {

        public PrintableBarcode createFromParcel(Parcel in) {
            return new PrintableBarcode(in);
        }

        public PrintableBarcode[] newArray(int size) {
            return new PrintableBarcode[size];
        }
    };

    public enum BarcodeType {
        EAN8,
        UPCA,
        EAN13,
        CODE39,
        QR_CODE
    }
}
