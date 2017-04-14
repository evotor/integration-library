package ru.evotor.devices.commons;

import android.graphics.Bitmap;

import ru.evotor.devices.commons.printer.PrinterDocument;
import ru.evotor.devices.commons.result.ResultInt;
import ru.evotor.devices.commons.result.ResultVoid;

interface IPrinterService {

    /**
    * Возвращает количество печатных символов, которые помещаются на 1 строке
    *
    * @param deviceId   - номер устройства
    */
    ResultInt getAllowableSymbolsLineLength(int deviceId);

    /**
    * Возвращает доступную для печати ширину бумаги в пикселях
    *
    * @param deviceId   - номер устройства
    */
    ResultInt getAllowablePixelLineLength(int deviceId);

    /**
    * Печатает указанный массив объектов (тест, штрихкоды, картинки)
    *
    * @param deviceId           - номер устройства
    * @param printedObjects     - объекты для печати
    */
    ResultVoid printDocument(int deviceId, in PrinterDocument printerDocument);

}
