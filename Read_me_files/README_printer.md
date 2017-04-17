[Главная страница](https://github.com/draudr/integration-library/blob/master/README.md) SSDK для принтера Эвотор


# __1.1. SDK для принтера Эвотор__
_Содержание:_  
1.1.1.  [Подключение библиотеки для работы с оборудованием к своему проекту.](#1011)  
1.1.2. [Инициализация класса ru.evotor.devices.commons.DeviceServiceConnector](#1012).  
1.1.3. [Вызов метода `DeviceServiceConnector.getPrinterService()` и взаимодействие с его ответом.](#1013)  
1.1.4. [Передача данных в печать.  ](#1014)  
1.1.5. [Особенности работы оборудования с `DeviceServiceConnector`.](#1015)  


<a name="1011"></a>
### 1.1.1. Подключение библиотеки для работы с оборудованием к своему проекту.

Для этого в build.gradle проекта добавьте ссылку на репозиторий jitpack:

```
allprojects {
    repositories {
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```

в модуле `build.gradle` добавьте зависимость и укажите точную версию (текущая: v1.2.0+):

```
dependencies {
    compile 'com.github.evotor:device-drivers:v1.2.0+'
}
```
<a name="1012"></a>
### 1.1.2. Инициализация класса `ru.evotor.devices.commons.DeviceServiceConnector`  
Для того, что бы начать обращаться к принтеру достаточно проинициализировать класс `ru.evotor.devices.commons.DeviceServiceConnector`, который был подставлен в подключенную на прошлом шаге библиотеку. Проинициализирйте его при запуске приложения или старте `activity`:  
```  
DeviceServiceConnector.initConnections(getApplicationContext());
```
<a name="1013"></a>
### 1.1.3. Вызов метода `DeviceServiceConnector.getPrinterService()` и взаимодействие с его ответом.  
* В ответ на вызов метода  `DeviceServiceConnector.getPrinterService()` вернется объект `ru.evotor.devices.commons.IPrinterService`.  
* В свою очередь у объекта `ru.evotor.devices.commons.IPrinterService` вызвать методы:  
  * `int getAllowableSymbolsLineLength(int deviceId)` - возвращает количество печатных символов, которые помещаются на 1 строке;
  * `int getAllowablePixelLineLength(int deviceId)` - возвращает доступную для печати ширину бумаги в пикселях;  
  * `void printDocument(int deviceId, in PrinterDocument printerDocument)` - печатает указанный массив объектов (тест, штрихкоды, картинки).  

`deviceId` - первый аргумент каждой функции. Отвечает за указание конкретного устройства, для которого будет вызван метод.

При возникновении каких - либо проблем в ходе вызова любого из перечисленных ранее методов - система вернет значение = `Exception`.

> __На данный момент печатать можно только на встроенной в СТ2 ККМ, поэтому вместо номера устройства всегда следует передавать константу `ru.evotor.devices.commons.Constants.DEFAULT_DEVICE_INDEX`.__  

<a name="1014"></a>
### 1.1.4. Передача данных в печать.  
Для печати Вам необходимо воспользоваться методом:
```
printDocument((int deviceId, in PrinterDocument printerDocument);
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
<a name="1015"></a>
### 1.1.5. Особенности работы оборудования с `DeviceServiceConnector`.  
При работе оборудования с `DeviceServiceConnector` следует помнить, что методы, по факту, исполняются в другом приложении, в связи с этим могут возникать различные `Exception`. В связи с этим необходимо отслеживать и перехватывать любые `RuntimeException`, так как они могут быть проброшены сквозь приложения.  

Также при работе с другими методами удаленных сервисов стоит вызывать метод `DeviceServiceConnector.processException(Exception exc)` для логирования ошибок и сервисных операций (например, перезапуск соединения по DeadObjectException).

Пример кода печати:  
```
try {
	DeviceServiceConnector.getPrinterService().printDocument(
	DEFAULT_DEVICE_INDEX_UNSET,
  new PrinterDocument(new PrintableText("Первая строка")));
    } catch (RemoteException | RuntimeException exc)
{
  DeviceServiceConnector.processException(exc);
}
```

-----

###### Более подробную информацию по разрабатке своих решений для бизнеса на платформе Эвотор, Вы можете найти на нашем сайте для разработчиков: https://developer.evotor.ru/
