package ru.evotor.framework.core

private const val CAUSE = "*"

private const val BASE_MESSAGE = "Found $CAUSE that is not supported in current integration-library. Try to update your integration-library to resolve this error."

/**
 * Исключение, возникающее при расхождении протокола взаимодействия между сторонним приложенем и системным приложением СТ.
 *
 * Причиной возникновения, скорее всего, является устаревшая версия integration-library в стороннем приложении.
 *
 * Если после обновления integration-library исключение не перестало возникать, свяжитесь с техподдержкой Эвотора.
 */
internal class IntegrationLibraryMappingException(cause: String) : IntegrationLibraryException(BASE_MESSAGE.replace(CAUSE, cause))