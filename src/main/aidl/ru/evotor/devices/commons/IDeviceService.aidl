package ru.evotor.devices.commons;

import ru.evotor.devices.commons.result.ResultDeviceInfo;
import ru.evotor.devices.commons.result.ResultInt;
import ru.evotor.devices.commons.result.ResultIntArray;

interface IDeviceService {

//	ККМ

		ResultIntArray getKKMs();

		ResultInt getDefaultKKMIndex();

//	платёжные системы

		ResultIntArray getPaySystems();

		ResultInt getDefaultPaySystemIndex();

//	принтеры ценников

		ResultIntArray getPricePrinters();

		ResultInt getDefaultPricePrinterIndex();

//	денежные ящики

		ResultIntArray getCashDrawers();

		ResultInt getDefaultCashDrawerIndex();

// весы

        ResultIntArray getScales();

		ResultInt getDefaultScalesIndex();

// сканеры

		ResultIntArray getScanners();

// сервисные методы

		ResultIntArray getAllDevices();

        ResultDeviceInfo getDeviceInfo(int deviceId);

        ResultInt getPermanentIdFromDeviceId(int deviceId);

        ResultInt getDeviceIdFromPermanentId(int permanentId);

// принтеры

        ResultIntArray getPrinters();

        ResultInt getDefaultPrinterIndex();

}
