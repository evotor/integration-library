package ru.evotor.devices.commons;

import android.content.Context;

import ru.evotor.devices.commons.exception.DeviceServiceOperationOnMainThreadException;
import ru.evotor.devices.commons.exception.ServiceNotConnectedException;
import ru.evotor.devices.commons.services.CashDrawerService;
import ru.evotor.devices.commons.services.DeviceService;
import ru.evotor.devices.commons.services.ICashDrawerServiceWrapper;
import ru.evotor.devices.commons.services.IDeviceServiceWrapper;
import ru.evotor.devices.commons.services.IKKMServiceWrapper;
import ru.evotor.devices.commons.services.IPaySystemServiceWrapper;
import ru.evotor.devices.commons.services.IPricePrinterServiceWrapper;
import ru.evotor.devices.commons.services.KKMService;
import ru.evotor.devices.commons.services.PaySystemService;
import ru.evotor.devices.commons.services.PricePrinterService;

public class InternalDeviceServiceConnector extends DeviceServiceConnector {
    private static final String TAG = "InternalDSC";

    protected static final DeviceService deviceService = new DeviceService();
    protected static final PricePrinterService pricePrinterService = new PricePrinterService();
    protected static final KKMService kkmService = new KKMService();
    protected static final CashDrawerService cashDrawerService = new CashDrawerService();
    protected static final PaySystemService paySystemService = new PaySystemService();

    public static IDeviceServiceWrapper getDeviceService() throws ServiceNotConnectedException {
        deviceService.waitInitService(context);
        return deviceService;
    }

    public static IKKMServiceWrapper getKkmService() throws ServiceNotConnectedException {
        kkmService.waitInitService(context);
        return kkmService;
    }

    public static IPricePrinterServiceWrapper getPricePrinterService() throws ServiceNotConnectedException {
        pricePrinterService.waitInitService(context);
        return pricePrinterService;
    }

    public static ICashDrawerServiceWrapper getCashDrawerService() throws ServiceNotConnectedException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        cashDrawerService.waitInitService(context);
        return cashDrawerService;
    }

    public static IPaySystemServiceWrapper getPaySystemService() throws ServiceNotConnectedException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        paySystemService.waitInitService(context);
        return paySystemService;
    }

    public static void startInitConnections(final Context appContext) {
        startInitConnections(appContext, false);
    }

    public static void startInitConnections(final Context appContext, final boolean force) {
        DeviceServiceConnector.startInitConnections(appContext, force);
        if (appContext == null) {
            return;
        }

        deviceService.startInitConnection(context, force);
        pricePrinterService.startInitConnection(context, force);
        kkmService.startInitConnection(context, force);
        cashDrawerService.startInitConnection(context, force);
        paySystemService.startInitConnection(context, force);
    }

    public static void startDeinitConnections() {
        DeviceServiceConnector.startDeinitConnections();
        if (context == null) {
            return;
        }

        deviceService.startDeinitConnection();
        pricePrinterService.startDeinitConnection();
        kkmService.startDeinitConnection();
        cashDrawerService.startDeinitConnection();
        paySystemService.startDeinitConnection();
    }

}