package ru.evotor.devices.commons;

import ru.evotor.devices.commons.result.ResultVoid;

interface ICashDrawerService {

        /**
        * Открывает денежный ящик
        *
        * @param deviceId         - номер устройства
        */
		ResultVoid openCashDrawer(int deviceId);

        /**
        * Открывает все подключенные денежные ящики
        */
		ResultVoid openAllCashDrawers();

}
