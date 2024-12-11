package ru.evotor

import java.util.regex.Pattern

object UuidValidationUtils {

    private val UUID_REGEX: Pattern = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")

    @JvmStatic
    @Throws(IncorrectUuidException::class)
    fun checkUuid(uuid: String) {
        if (!UUID_REGEX.matcher(uuid).matches()) {
            throw IncorrectUuidException(uuid)
        }
    }
}

class IncorrectUuidException(value: String?)
    : IllegalArgumentException("Invalid UUID String $value : UUID has to be represented by standard 36-char representation")
