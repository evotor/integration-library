package ru.evotor.framework.receipt.print_extras

/**
 * Тип места в чеке, куда будет добавлена дополнительная печатная информация
 */
enum class PrintExtraPlaceType {
    PRINT_GROUP_TOP,
    PRINT_GROUP_HEADER,
    PRINT_GROUP_SUMMARY,
    POSITION_FOOTER,
    POSITION_ALL_SUBPOSITIONS_FOOTER
}