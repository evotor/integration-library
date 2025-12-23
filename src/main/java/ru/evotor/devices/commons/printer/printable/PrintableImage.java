package ru.evotor.devices.commons.printer.printable;

import android.graphics.Bitmap;
import android.os.Parcel;

import ru.evotor.ParcelablesKt;

public class PrintableImage implements IPrintable {

    /**
     * картинка для печати
     */
    private final Bitmap bitmap;

    public PrintableImage(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    private PrintableImage(Parcel parcel) {
        bitmap = ParcelablesKt.readAliased(parcel, Bitmap.CREATOR);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        ParcelablesKt.writeAliased(parcel, bitmap, 0);
    }

    public static final Creator<PrintableImage> CREATOR = new Creator<PrintableImage>() {

        public PrintableImage createFromParcel(Parcel in) {
            return new PrintableImage(in);
        }

        public PrintableImage[] newArray(int size) {
            return new PrintableImage[size];
        }
    };

}
