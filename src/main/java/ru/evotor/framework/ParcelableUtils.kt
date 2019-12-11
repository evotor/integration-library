package ru.evotor.framework

import android.os.Parcel

object ParcelableUtils {

    /**
     * Magic number для идентификации использования версионирования объекта
     */
    private const val MAGIC_NUMBER = 8800

    @JvmStatic
    fun writeExpand(parcel: Parcel, version: Int, writer: (Parcel) -> Unit) = with(parcel) {
        writeInt(MAGIC_NUMBER)
        writeInt(version)
        // Determine position in parcel for writing data size
        val dataSizePosition = dataPosition()
        // Use integer placeholder for additional data size
        writeInt(0)
        //Determine position of data start
        val startDataPosition = dataPosition()

        //Write additional data
        writer(this)

        // Calculate additional data size
        val dataSize = dataPosition() - startDataPosition
        // Save position at the end of data
        val endOfDataPosition = dataPosition()
        //Set position to start to write additional data size
        setDataPosition(dataSizePosition)
        writeInt(dataSize)
        // Go back to the end of parcel
        setDataPosition(endOfDataPosition)
    }


    @JvmStatic
    fun readExpand(parcel: Parcel, version: Int, reader: (Parcel, Int) -> Unit) = with(parcel) {

        val startReadingPosition = dataPosition()

        // Check if available data size is more than integer size and versioning is supported
        if (dataAvail() <= 4 || readInt() != MAGIC_NUMBER) {
            // Versioning is not supported return pointer to start position and end reading
            setDataPosition(startReadingPosition)
            return
        }
        //Read object version
        val currentVersion = readInt()
        val dataSize = readInt()
        val startDataPosition = dataPosition()

        reader(this, currentVersion)
        if (currentVersion > version) {
            setDataPosition(startDataPosition + dataSize)
        }

    }


}