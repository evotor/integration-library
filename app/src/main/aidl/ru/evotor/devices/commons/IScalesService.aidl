package ru.evotor.devices.commons;

import ru.evotor.devices.commons.result.ResultWeight;

interface IScalesService {

    /**
    * Получить вес и прочие характеристики
    *
    * @param deviceId  - номер устройства
    */
    ResultWeight getWeight(int deviceId);

}
