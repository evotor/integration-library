package ru.evotor.framework.core.action.datamapper

import android.os.Bundle
import ru.evotor.framework.component.IntegrationComponent

object IntegrationComponentMapper {
    private const val KEY_PACKAGE_NAME = "packageName"
    private const val KEY_COMPONENT_NAME = "componentName"
    private const val KEY_APP_UUID = "appUuid"
    private const val KEY_APP_NAME = "appName"

    fun toBundle(integrationComponent: IntegrationComponent): Bundle =
            Bundle().apply {
                putString(KEY_PACKAGE_NAME, integrationComponent.packageName)
                putString(KEY_COMPONENT_NAME, integrationComponent.componentName)
                putString(KEY_APP_UUID, integrationComponent.appUuid)
                putString(KEY_APP_NAME, integrationComponent.appName)
            }

    fun fromBundle(bundle: Bundle?): IntegrationComponent? =
            bundle?.let {
                IntegrationComponent(
                        bundle.getString(KEY_PACKAGE_NAME),
                        bundle.getString(KEY_COMPONENT_NAME),
                        bundle.getString(KEY_APP_UUID),
                        bundle.getString(KEY_APP_NAME)
                )
            }

    fun readPackageName(bundle: Bundle?) = bundle?.getString(KEY_PACKAGE_NAME)

    fun readComponentName(bundle: Bundle?) = bundle?.getString(KEY_COMPONENT_NAME)

    fun readAppUuid(bundle: Bundle?) = bundle?.getString(KEY_APP_UUID)

    fun readAppName(bundle: Bundle?) = bundle?.getString(KEY_APP_NAME)
}