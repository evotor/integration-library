[Главная страница](https://github.com/evotor/integration-library/blob/master/README.md) SDK для принтера Эвотор


# __1.1. SDK для принтера Эвотор__
_Содержание:_  
1.1.1. [Инициализация класса ru.evotor.devices.commons.DeviceServiceConnector.](#1011)  
1.1.2. [Вызов метода `DeviceServiceConnector.getPrinterService()` и взаимодействие с его ответом.](#1012)  
1.1.3. [Передача данных в печать.](#1013)  
1.1.4. [Пример кода печати.](#1014)  


<a name="1011"></a>
### 1.1.1. Инициализация класса `ru.evotor.devices.commons.DeviceServiceConnector`.  
Для того, что бы начать обращаться к принтеру достаточно проинициализировать класс `ru.evotor.devices.commons.DeviceServiceConnector`, который содержится в библиотеке, подключенной на прошлом шаге. Проинициализируйте его в `onCreate` вашего `Application` или старте `activity`:  
```  
DeviceServiceConnector.startInitConnections(getApplicationContext());
```
Инициация класса начнется ассинхронно, что бы не держать "вызывающий" поток в течение всего времени подключения. В случае, если есть необходимость выполнить какой-то код сразу после установки соединения, воспользуйтесь методом `addConnectionWrapper`, чтобы получить событие об успешном подключении.

<a name="1012"></a>
### 1.1.2. Вызов метода `DeviceServiceConnector.getPrinterService()` и взаимодействие с его ответом.  

* В ответ на вызов метода  `DeviceServiceConnector.getPrinterService()` вернется объект `ru.evotor.devices.commons.IPrinterService`.
 * `DeviceServiceConnector.getPrinterService()` никогда не будет `null`  
 * В случае перебоев с подключением к сервису оборудования, библиотека будет пробовать переподключиться, чтобы вернуть объект для работы с сервисом.
 * В случае невозможности подключиться, будет выброшен `ru.evotor.devices.commons.exception.ServiceNotConnectedException`.   
* В свою очередь у объекта `ru.evotor.devices.commons.IPrinterService` вызвать методы:  
  * `int getAllowableSymbolsLineLength(int deviceId)` - возвращает количество печатных символов, которые помещаются на 1 строке;
  * `int getAllowablePixelLineLength(int deviceId)` - возвращает доступную для печати ширину бумаги в пикселях;  
  * `void printDocument(int deviceId, in PrinterDocument printerDocument)` - печатает указанный массив объектов (тест, штрихкоды, картинки)

`deviceId` - первый аргумент каждой функции. Отвечает за указание конкретного устройства, для которого будет вызван метод.

Все методы у `DeviceServiceConnector.getPrinterService()` нужно выполнять **не в главном потоке приложения**, так как все операции с удаленным сервисом могут занимать длительное время.

Все методы в `DeviceServiceConnector.getPrinterService()` могут выбросить исключение - наследник `ru.evotor.devices.commons.exception.DeviceServiceException`.  

> __На данный момент печатать можно только на встроенной в СТ2 ККМ, поэтому вместо номера устройства всегда следует передавать константу `ru.evotor.devices.commons.Constants.DEFAULT_DEVICE_INDEX`.__  

<a name="1013"></a>
### 1.1.3. Передача данных в печать.  
Для печати Вам необходимо воспользоваться методом:
```
printDocument(int deviceId, in PrinterDocument printerDocument);
```
`PrinterDocument` - второй аргумент функции. Должен содержать список печатный элементов `IPrintable`:  
* Тексты - `ru.evotor.devices.commons.printer.printable.PrintableText`;
* Штрихкоды - `ru.evotor.devices.commons.printer.printable.PrintableBarcode`;
* Картинки - `ru.evotor.devices.commons.printer.printable.PrintableImage`.

Пример:
```
Bitmap bitmap1 = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/Pictures/1.png");
DeviceServiceConnector.getPrinterService().printDocument(
  DEFAULT_DEVICE_INDEX_UNSET,
  new PrinterDocument(
            new PrintableText("Первая строка"),
            new PrintableText("Довольно длинный текст, помещающийся лишь на несколько строк"),
            new PrintableBarcode("1234567890", PrintableBarcode.BarcodeType.CODE39),
            new PrintableImage(bitmap1)
    ));
```
<a name="1014"></a>
### 1.1.4. Пример кода печати.  

```
try {
  DeviceServiceConnector.getPrinterService().printDocument(
    DEFAULT_DEVICE_INDEX_UNSET,
    new PrinterDocument(new PrintableText("Первая строка")));
} catch (DeviceServiceException exc) {

}
```

-----

###### Более подробную информацию по разрабатке своих решений для бизнеса на платформе Эвотор, Вы можете найти на нашем сайте для разработчиков: https://developer.evotor.ru/
