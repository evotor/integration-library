package ru.evotor.framework.organisation.mapper

import android.os.Bundle
import ru.evotor.framework.organisation.Organisation
import java.lang.Exception
import java.util.*

internal object OrganisationMapper {

    private const val KEY_UUID = "uuid"
    private const val KEY_FULL_NAME = "fullName"
    private const val KEY_SHORT_NAME = "shortName"
    private const val KEY_INN = "inn"
    private const val KEY_KPP = "kpp"
    private const val KEY_CONTACTS = "contacts"
    private const val KEY_PHONE = "phone"
    private const val KEY_ADDRESS = "address"

    fun read(bundle: Bundle?) = bundle?.let {
        try {
            Organisation(
                    uuid = UUID.fromString(it.getString(KEY_UUID)),
                    fullName = it.getString(KEY_FULL_NAME),
                    shortName = it.getString(KEY_SHORT_NAME),
                    inn = it.getString(KEY_INN),
                    kpp = it.getString(KEY_KPP),
                    contacts = readContacts(it.getBundle(KEY_CONTACTS))
            )
        } catch (e: Exception) {
            null
        }
    }

    private fun readContacts(bundle: Bundle?) = bundle?.let {
        Organisation.Contacts(
                phone = it.getString(KEY_PHONE),
                address = it.getString(KEY_ADDRESS)
        )
    }

    fun write(organisation: Organisation) = Bundle().apply {
        this.putString(KEY_UUID, organisation.uuid.toString())
        this.putString(KEY_FULL_NAME, organisation.fullName)
        this.putString(KEY_SHORT_NAME, organisation.shortName)
        this.putString(KEY_INN, organisation.inn)
        this.putString(KEY_KPP, organisation.kpp)
        this.putString(KEY_UUID, organisation.kpp)
        this.putBundle(KEY_CONTACTS, organisation.contacts?.let { writeContacts(it) })
    }

    private fun writeContacts(contacts: Organisation.Contacts) = Bundle().apply {
        this.putString(KEY_PHONE, contacts.phone)
        this.putString(KEY_ADDRESS, contacts.address)
    }

}