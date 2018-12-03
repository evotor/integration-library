package ru.evotor.framework.core

import java.lang.Exception

private const val CAUSE = "$"

private const val BASE_MESSAGE = "Found $CAUSE that is not supported in current integration-library. Update your integration-library to resolve this error."

/**
 * Исключение, возникающее при расхождении протокола взаимодействия между сторонним приложенем,
 * которое использует устаревшую версию integration-library, и системным приложением СТ.
 *
 * При обновлении integration-library в стороннем приложении исключение должно перестать возникать.
 */
class OutdatedLibraryException internal constructor(cause: String) : Exception(BASE_MESSAGE.replace(CAUSE, cause))