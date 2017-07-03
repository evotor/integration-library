package ru.evotor.framework

import android.annotation.TargetApi
import android.content.ContentResolver
import android.database.CharArrayBuffer
import android.database.ContentObserver
import android.database.Cursor
import android.database.DataSetObserver
import android.net.Uri
import android.os.Build
import android.os.Bundle


abstract class Cursor<T>(val cursor: Cursor) : android.database.Cursor {

    abstract fun getValue(): T

    override fun setNotificationUri(cr: ContentResolver?, uri: Uri?) {
        cursor.setNotificationUri(cr, uri)
    }

    override fun copyStringToBuffer(columnIndex: Int, buffer: CharArrayBuffer?) {
        cursor.copyStringToBuffer(columnIndex, buffer)
    }

    override fun getExtras(): Bundle {
        return cursor.extras
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun setExtras(extras: Bundle?) {
        cursor.extras = extras
    }

    override fun moveToPosition(position: Int): Boolean {
        return cursor.moveToPosition(position)
    }

    override fun getLong(columnIndex: Int): Long {
        return cursor.getLong(columnIndex)
    }

    override fun moveToFirst(): Boolean {
        return cursor.moveToFirst()
    }

    override fun getFloat(columnIndex: Int): Float {
        return cursor.getFloat(columnIndex)
    }

    override fun moveToPrevious(): Boolean {
        return cursor.moveToPrevious()
    }

    override fun getDouble(columnIndex: Int): Double {
        return cursor.getDouble(columnIndex)
    }

    override fun close() {
        cursor.close()
    }

    override fun isClosed(): Boolean {
        return cursor.isClosed
    }

    override fun getCount(): Int {
        return cursor.count
    }

    override fun isFirst(): Boolean {
        return cursor.isFirst
    }

    override fun isNull(columnIndex: Int): Boolean {
        return cursor.isNull(columnIndex)
    }

    override fun unregisterContentObserver(observer: ContentObserver?) {
        cursor.unregisterContentObserver(observer)
    }

    override fun getColumnIndexOrThrow(columnName: String?): Int {
        return cursor.getColumnIndexOrThrow(columnName)
    }

    override fun requery(): Boolean {
        return cursor.requery()
    }

    override fun getWantsAllOnMoveCalls(): Boolean {
        return cursor.wantsAllOnMoveCalls
    }

    override fun getColumnNames(): Array<String> {
        return cursor.columnNames
    }

    override fun getInt(columnIndex: Int): Int {
        return cursor.getInt(columnIndex)
    }

    override fun isLast(): Boolean {
        return cursor.isLast
    }

    override fun getType(columnIndex: Int): Int {
        return cursor.getType(columnIndex)
    }

    override fun registerDataSetObserver(observer: DataSetObserver?) {
        cursor.registerDataSetObserver(observer)
    }

    override fun moveToNext(): Boolean {
        return cursor.moveToNext()
    }

    override fun getPosition(): Int {
        return cursor.position
    }

    override fun isBeforeFirst(): Boolean {
        return cursor.isBeforeFirst
    }

    override fun registerContentObserver(observer: ContentObserver?) {
        cursor.registerContentObserver(observer)
    }

    override fun moveToLast(): Boolean {
        return cursor.moveToLast()
    }

    override fun deactivate() {
        cursor.deactivate()
    }

    override fun getNotificationUri(): Uri {
        return cursor.notificationUri
    }

    override fun getColumnName(columnIndex: Int): String {
        return cursor.getColumnName(columnIndex)
    }

    override fun getColumnIndex(columnName: String?): Int {
        return cursor.getColumnIndex(columnName)
    }

    override fun getBlob(columnIndex: Int): ByteArray {
        return cursor.getBlob(columnIndex)
    }

    override fun getShort(columnIndex: Int): Short {
        return cursor.getShort(columnIndex)
    }

    override fun getString(columnIndex: Int): String {
        return cursor.getString(columnIndex)
    }

    override fun move(offset: Int): Boolean {
        return cursor.move(offset)
    }

    override fun getColumnCount(): Int {
        return cursor.columnCount
    }

    override fun respond(extras: Bundle?): Bundle {
        return cursor.respond(extras)
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver?) {
        cursor.unregisterDataSetObserver(observer)
    }

    override fun isAfterLast(): Boolean {
        return cursor.isAfterLast
    }
}