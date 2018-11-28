package ru.evotor.framework

/**
 * Означает, что значение анотированной переменной было записано, либо будет записано (при передаче
 * её в соответствующий метод АПИ) в кассу в качестве одного или нескольких фискальных реквизитов.
 */
@Repeatable
@Retention(AnnotationRetention.SOURCE)
internal annotation class FiscalRequisite(
        val tag: Int,
        val isPrinted: Boolean,
        val isSentToOfd: Boolean
)