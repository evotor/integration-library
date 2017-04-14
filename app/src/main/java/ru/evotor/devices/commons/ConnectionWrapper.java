package ru.evotor.devices.commons;

public interface ConnectionWrapper {
    void onPrinterServiceConnected(IPrinterService printerService);

    void onPrinterServiceDisconnected();
}
