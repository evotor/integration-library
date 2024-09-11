package ru.evotor.framework

import java.util.regex.Pattern
import kotlin.jvm.Throws

object Validation{
    private val UUID_REGEX: Pattern = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")

    @JvmStatic
    @Throws(ExceptionUUID::class)
    fun checkUuid(uuid: String?){
        if(!(uuid == null || UUID_REGEX.matcher(uuid).matches())) {
            throw ExceptionUUID(uuid)
        }
    }

    @JvmStatic
    fun isUuidCorrect(uuid: String?): Boolean{
        return uuid == null || UUID_REGEX.matcher(uuid).matches()
    }
}

class ExceptionUUID(value: String?)
    : IllegalArgumentException("Invalid UUID String $value : UUID has to be represented by standard 36-char representation")
