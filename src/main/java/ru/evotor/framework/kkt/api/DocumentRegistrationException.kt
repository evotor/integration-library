package ru.evotor.framework.kkt.api

import java.lang.Exception

private class DocumentRegistrationException(
        val code: Int,
        message: String
) : Exception(message) {
    companion object {
        /**
         * Смена превысила 24 часа
         */
        const val CODE_SESSION_TIME_EXPIRED = -3822

        /**
         * Исчерпан ресурс КС ФН
         */
        const val CODE_FISCAL_STORAGE_KS_RESOURCE_EXHAUSTED = -3978

        /**
         * Исчерпан ресурс хранения ФН
         */
        val CODE_FISCAL_STORAGE_CAPACITY_EXHAUSTED = -3979

        /**
         * Архив ФН переполнен
         */
        val CODE_FISCAL_STORAGE_ARCHIVE_EXHAUSTED = -3916
    }
}