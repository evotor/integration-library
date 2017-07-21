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

class PositionsMerge(val before: List<Position>, val after: Position) : Parcelable {

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeList(before)
        parcel.writeParcelable(after, Parcelable.PARCELABLE_WRITE_RETURN_VALUE)
    }

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<PositionsMerge> = object : Parcelable.Creator<PositionsMerge> {
            override fun createFromParcel(parcel: Parcel): PositionsMerge {
                val before = ArrayList<Position>()
                parcel.readList(before, Position::class.java.classLoader)
                val after = parcel.readParcelable<Position>(Position::class.java.classLoader)
                return PositionsMerge(before, after)
            }

            override fun newArray(size: Int): Array<PositionsMerge?> {
                return arrayOfNulls(size)
            }
        }
    }

    override fun describeContents(): Int {
        return 0
    }
}
