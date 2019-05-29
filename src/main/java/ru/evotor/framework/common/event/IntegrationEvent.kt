package ru.evotor.framework.common.event

import ru.evotor.IBundlable

abstract class IntegrationEvent internal constructor() : IBundlable {

    abstract class Result internal constructor() : IBundlable
}