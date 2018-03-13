package ru.evotor.devices.commons;

import ru.evotor.devices.commons.paysystem.PayInfo;

import ru.evotor.devices.commons.result.ResultPayResult;
import ru.evotor.devices.commons.result.ResultVoid;
import ru.evotor.devices.commons.result.ResultBoolean;
import ru.evotor.devices.commons.result.ResultString;
import ru.evotor.devices.commons.result.ResultInt;

interface IPaySystemService {

	/**
	 * Производит оплату на указанную сумму
	 *
	 * @param deviceId  - номер устройства
	 * @param payinfo   - информация об оплате (сумма)
	 * @return          - параметры успешного завершения результата
	 * @throws          - RuntimeException в случае неуспешного завершения
	 */
	ResultPayResult payment(int deviceId, in PayInfo payinfo);

	/**
	 * Производит отмену оплаты на указанную сумму
	 *
	 * @param deviceId  - номер устройства
	 * @param payinfo   - информация об оплате (сумма)
	 * @param rrn       - РРН отменяемой операции
	 * @return          - параметры успешного завершения результата
	 * @throws          - RuntimeException в случае неуспешного завершения
	 */
	ResultPayResult cancelPayment(int deviceId, in PayInfo payinfo, String rrn);

	/**
	 * Производит возврат на указанную сумму
	 *
	 * @param deviceId  - номер устройства
	 * @param payinfo   - информация об оплате (сумма)
	 * @param rrn       - РРН операции оплаты
	 * @return          - параметры успешного завершения результата
	 * @throws          - RuntimeException в случае неуспешного завершения
	 */
	ResultPayResult payback(int deviceId, in PayInfo payinfo, String rrn);

	/**
	 * Производит отмену возврата на указанную сумму
	 *
	 * @param deviceId  - номер устройства
	 * @param payinfo   - информация об оплате (сумма)
	 * @param rrn       - РРН отменяемой операции
	 * @return          - параметры успешного завершения результата
	 * @throws          - RuntimeException в случае неуспешного завершения
	 */
	ResultPayResult cancelPayback(int deviceId, in PayInfo payinfo, String rrn);

	/**
	 * Производит закрытие банковской смены
	 *
	 * @param deviceId  - номер экземпляра драйвера
	 * @return          - параметры успешного завершения результата
	 * @throws          - RuntimeException в случае неуспешного завершения
	 */
	ResultPayResult closeSession(int deviceId);

	/**
	 * Открывает на терминале сервисное меню
	 *
	 * @param deviceId  - номер экземпляра драйвера
	 * @throws          - RuntimeException в случае неуспешного завершения
	 */
	ResultVoid openServiceMenu(int deviceId);

    /**
	 * запрос свойств пинпада
	 *
	 * @param deviceId  - номер устройства
	 */
	ResultString getBankName(int deviceId);
	ResultInt getTerminalNumber(int deviceId);
	ResultString getTerminalID(int deviceId);
	ResultString getMerchNumber(int deviceId);
	ResultString getMerchCategoryCode(int deviceId);
	ResultString getMerchEngName(int deviceId);
	ResultString getCashier(int deviceId);
	ResultString getServerIP(int deviceId);

    /**
	 * банковскому терминалу не требуется РРН для возврата/отмены операции
	 *
	 * @param instanceId    - номер экземпляра драйвера
	 * @return  true    -   РРН не требуется
	 *          false   -   РРН требуется
	 */
    ResultBoolean isNotNeedRRN(int deviceId);
}
