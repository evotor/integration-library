package ru.evotor.framework

/**
 * Означает, что значение анотированной переменной было записано (если она была получена при
 * обращении к БД СТ), либо будет записано (при передаче её в соответствующий метод АПИ) в кассу в
 * качестве одного или нескольких (в случае повторения аннотации) фискальных реквизитов.
 */
@Repeatable
@Retention(AnnotationRetention.SOURCE)
internal annotation class FiscalRequisite(
        val tag: Int,
        val isPrinted: Boolean,
        val isSentToOfd: Boolean
)