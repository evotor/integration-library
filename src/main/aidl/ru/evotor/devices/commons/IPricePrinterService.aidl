package ru.evotor.devices.commons;

import ru.evotor.devices.commons.result.ResultVoid;

interface IPricePrinterService {

	/**
	 * Подготавливает принтер ценников к печати группы ценников
	 *
	 * @param deviceId  - номер устройства
	 */
	ResultVoid beforePrintPrices(int deviceId);

	/**
	 * Печатает 1 ценник
	 *
	 * @deviceId    - номер устройства
	 * @name	    - название товара
	 * @price	    - цена
	 * @barcode	    - штрихкод
	 * @code	    - код товара
	 */
	ResultVoid printPrice(int deviceId, String name, String price, String barcode, String code);

	/**
	 * Завершает печать группы ценников
	 *
	 * @param deviceId  - номер устройства
	 */
	ResultVoid afterPrintPrices(int deviceId);
}