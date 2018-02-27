package ru.evotor.devices.commons.printer.printable;

import android.os.Parcel;

public class PrintableText implements IPrintable {

    /**
     * текст для печати
     */
    private final String text;

    public PrintableText(String text) {
        this.text = text;
    }

    private PrintableText(Parcel parcel) {
        text = parcel.readString();
    }

    public String getText() {
        return text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(text);
    }

    public static final Creator<PrintableText> CREATOR = new Creator<PrintableText>() {

        public PrintableText createFromParcel(Parcel in) {
            return new PrintableText(in);
        }

        public PrintableText[] newArray(int size) {
            return new PrintableText[size];
        }
    };

}
