package ru.evotor.framework.inventory

/**
 * Created by a.kuznetsov on 26/03/2017.
 */
data class ProductExtra(
        val uuid: String,
        val name: String?,
        val commodityUUID: String,
        val fieldUUID: String,
        val fieldValue: Any?,
        val data: String?
)