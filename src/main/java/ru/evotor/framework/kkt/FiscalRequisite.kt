package ru.evotor.framework.kkt

/**
 * Означает, что значение анотированной переменной было записано (если она была получена при
 * соответствующем обращении к БД СТ), либо будет записано (при передаче её в соответствующий метод
 * АПИ) в кассу в качестве одного или нескольких (в некоторых случаях при повторения аннотации)
 * фискальных реквизитов.
 */
@Repeatable
@Retention(AnnotationRetention.SOURCE)
internal annotation class FiscalRequisite(
        /**
         * Фискальный тег
         */
        val tag: Int,

        /**
         * Флаги
         */
        val flags: Int = NO_FLAGS
) {
    companion object {
        private const val NO_FLAGS = 0

        /**
         * Означает, что фискальный реквизит может иметь несколько значений.
         */
        internal const val FLAG_MULTIPLE_VALUES = 2
    }
}