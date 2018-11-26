package ru.evotor.framework

internal annotation class FfdTag (
        val number: Int,
        val necessityIndex: NecessityIndex,
        vararg val documentForms: Document.Form
) {
    enum class NecessityIndex {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN
    }
}