package ru.evotor.devices.commons;

import ru.evotor.devices.commons.services.IPrinterServiceWrapper;
import ru.evotor.devices.commons.services.IScalesServiceWrapper;

public interface ConnectionWrapper {
    void onPrinterServiceConnected(IPrinterServiceWrapper printerService);

    void onPrinterServiceDisconnected();

    void onScalesServiceConnected(IScalesServiceWrapper scalesService);

    void onScalesServiceDisconnected();

}
