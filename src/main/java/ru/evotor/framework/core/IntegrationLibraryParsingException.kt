package ru.evotor.framework.core

/**
 * Исключение, возникающее при расхождении протокола взаимодействия между сторонним приложенем и системным приложением СТ.
 *
 * Причиной возникновения, скорее всего, является устаревшая версия integration-library в стороннем приложении.
 * Или неверная передача данных.
 *
 * Если после обновления integration-library исключение не перестало возникать, сверьтесь с тех.документацией на сайте.
 */
internal class IntegrationLibraryParsingException(clazz: Class<*>)
    : IntegrationLibraryException(
        "Field in $clazz is required and must not be null."
    )