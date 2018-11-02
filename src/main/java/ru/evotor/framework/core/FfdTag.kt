package ru.evotor.framework.core

/**
 * Означает, что при передаче аннотированной переменной в системный процесс Эвотора,
 * она запишется в кассу в качестве одного или нескольких тегов с указанными номерами.
 */
internal annotation class FfdTag(
        /**
         * Номера тегов
         */
        vararg val numbers: Int
)