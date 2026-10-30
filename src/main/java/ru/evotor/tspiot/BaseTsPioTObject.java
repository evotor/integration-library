package ru.evotor.tspiot;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public abstract class BaseTsPioTObject implements Parcelable {

    public void setUseOldParcelling(boolean useOldParcelling) { }

    public boolean isUseOldParcelling() { return false; }

    public void writeToParcel(Parcel parcel, WriteInterface writeInterface) {
        Parcel tmp = Parcel.obtain();
        try {
            writeInterface.write(tmp);

            byte[] bytes = tmp.marshall();
            parcel.writeByteArray(bytes);
        } finally {
            tmp.recycle();
        }
    }

    public Parcel createLocalParcelToRead(Parcel parcel, int version, int standard) {
        if (version >= standard) {
            Parcel tmp = Parcel.obtain();
            byte[] bytes = parcel.createByteArray();
            tmp.unmarshall(Objects.requireNonNull(bytes), 0, bytes.length);
            tmp.setDataPosition(0);
            return tmp;
        } else {
            return parcel;
        }
    }

    public void endLocalParcelRead(Parcel localParcel, int version, int standard) {
        if (version >= standard) {
            localParcel.recycle();
        }
    }

    @FunctionalInterface
    public interface WriteInterface {
        void write(Parcel local);
    }
}
