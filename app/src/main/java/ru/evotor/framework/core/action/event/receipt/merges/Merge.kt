package ru.evotor.framework.core.action.event.receipt.merges

import android.os.Parcel
import android.os.Parcelable
import ru.evotor.framework.receipt.Position

/**
 * Created by ivan on 26.06.17.
 */

/**
 * Класс представляет собой результат склеивания 2-х и более позиций в чеке для оповещения интеграций
 * before - список позиций, которые будут склеены
 * after - результат склейки
 */

class Merge(val before: List<Position>, val after: Position) : Parcelable {

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeList(before)
        parcel.writeParcelable(after, Parcelable.PARCELABLE_WRITE_RETURN_VALUE)
    }

    companion object {

        val CREATOR: Parcelable.Creator<Merge> = object : Parcelable.Creator<Merge> {
            override fun createFromParcel(parcel: Parcel): Merge {
                val before = ArrayList<Position>()
                parcel.readList(before, Position::class.java.classLoader)
                val after = parcel.readParcelable<Position>(Position::class.java.classLoader)
                return Merge(before, after)
            }

            override fun newArray(size: Int): Array<Merge?> {
                return arrayOfNulls(size)
            }
        }
    }

    override fun describeContents(): Int {
        return 0
    }
}
