package ru.evotor.framework

import android.database.Cursor


abstract class Cursor<T>(cursor: Cursor) : android.database.Cursor by cursor {

    abstract fun getValue(): T
}