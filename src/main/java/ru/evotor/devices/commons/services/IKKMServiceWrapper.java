package ru.evotor.devices.commons.services;

import ru.evotor.devices.commons.exception.DeviceServiceException;
import ru.evotor.devices.commons.service.FfdNumber;

public interface IKKMServiceWrapper {
    boolean is54fzDevice(int deviceId) throws DeviceServiceException;

    FfdNumber getCachedFfdKKMNumber(int deviceId) throws DeviceServiceException;

    FfdNumber getCachedFfdFnNumber(int deviceId) throws DeviceServiceException;

    String getCachedKkmSerialNumber(int deviceId) throws DeviceServiceException;

    String getCachedKkmInn(int deviceId) throws DeviceServiceException;

    String getCachedEKLZSerialNumber(int deviceId) throws DeviceServiceException;

    int getCachedEKLZFlags(int deviceId) throws DeviceServiceException;

    int getCachedEKLZKPKNumber(int deviceId) throws DeviceServiceException;

    int getCachedEKLZKPK(int deviceId) throws DeviceServiceException;

    String getCachedDeviceDescription(int deviceId) throws DeviceServiceException;

    int getCachedNotSendedDocsCount(int deviceId) throws DeviceServiceException;

    String getCachedOfdAddress(int deviceId) throws DeviceServiceException;

    int getCachedOfdPort(int deviceId) throws DeviceServiceException;

    long getCachedOfdDocumentDate(int deviceId) throws DeviceServiceException;

    long getCachedOfdDocumentTime(int deviceId) throws DeviceServiceException;

    int getCachedNetworkError(int deviceId) throws DeviceServiceException;

    int getCachedFnError(int deviceId) throws DeviceServiceException;

    int getCachedOfdError(int deviceId) throws DeviceServiceException;

    int get_LicenseValid(int deviceId) throws DeviceServiceException;

    String get_LicenseExpiredDate(int deviceId) throws DeviceServiceException;

    String get_Version(int deviceId) throws DeviceServiceException;

    String get_DriverName(int deviceId) throws DeviceServiceException;

    boolean get_DeviceEnabled(int deviceId) throws DeviceServiceException;

    int put_DeviceEnabled(int deviceId, boolean var1) throws DeviceServiceException;

    int get_ResultCode(int deviceId) throws DeviceServiceException;

    String get_ResultDescription(int deviceId) throws DeviceServiceException;

    int get_BadParam(int deviceId) throws DeviceServiceException;

    String get_BadParamDescription(int deviceId) throws DeviceServiceException;

    String get_DeviceSettings(int deviceId) throws DeviceServiceException;

    int put_DeviceSettings(int deviceId, String var1) throws DeviceServiceException;

    String get_DeviceSingleSetting(int deviceId, String var1) throws DeviceServiceException;

    int put_DeviceSingleStringSetting(int deviceId, String var1, String var2) throws DeviceServiceException;

    int put_DeviceSingleIntSetting(int deviceId, String var1, int var2) throws DeviceServiceException;

    int put_DeviceSingleDoubleSetting(int deviceId, String var1, double var2) throws DeviceServiceException;

    String get_DeviceSingleSettingMapping(int deviceId, String var1) throws DeviceServiceException;

    int ShowProperties(int deviceId) throws DeviceServiceException;

    int ApplySingleSettings(int deviceId) throws DeviceServiceException;

    int ResetSingleSettings(int deviceId) throws DeviceServiceException;

    String get_Caption(int deviceId) throws DeviceServiceException;

    int put_Caption(int deviceId, String var1) throws DeviceServiceException;

    int get_CaptionPurpose(int deviceId) throws DeviceServiceException;

    int put_CaptionPurpose(int deviceId, int var1) throws DeviceServiceException;

    boolean get_CaptionIsSupported(int deviceId) throws DeviceServiceException;

    String get_CaptionName(int deviceId) throws DeviceServiceException;

    double get_Value(int deviceId) throws DeviceServiceException;

    int put_Value(int deviceId, double var1) throws DeviceServiceException;

    int get_ValuePurpose(int deviceId) throws DeviceServiceException;

    int put_ValuePurpose(int deviceId, int var1) throws DeviceServiceException;

    boolean get_ValueIsSupported(int deviceId) throws DeviceServiceException;

    String get_ValueName(int deviceId) throws DeviceServiceException;

    String get_ValueMapping(int deviceId) throws DeviceServiceException;

    int get_CharLineLength(int deviceId) throws DeviceServiceException;

    String get_SerialNumber(int deviceId) throws DeviceServiceException;

    int put_SerialNumber(int deviceId, String var1) throws DeviceServiceException;

    long get_Time(int deviceId) throws DeviceServiceException;

    int put_Time(int deviceId, long var1) throws DeviceServiceException;

    long get_Date(int deviceId) throws DeviceServiceException;

    int put_Date(int deviceId, long var1) throws DeviceServiceException;

    boolean get_Fiscal(int deviceId) throws DeviceServiceException;

    boolean get_TestMode(int deviceId) throws DeviceServiceException;

    int put_TestMode(int deviceId, boolean var1) throws DeviceServiceException;

    boolean get_EnableCheckSumm(int deviceId) throws DeviceServiceException;

    int put_EnableCheckSumm(int deviceId, boolean var1) throws DeviceServiceException;

    String get_UserPassword(int deviceId) throws DeviceServiceException;

    int put_UserPassword(int deviceId, String var1) throws DeviceServiceException;

    int get_Mode(int deviceId) throws DeviceServiceException;

    int put_Mode(int deviceId, int var1) throws DeviceServiceException;

    int get_Alignment(int deviceId) throws DeviceServiceException;

    int put_Alignment(int deviceId, int var1) throws DeviceServiceException;

    int get_TextWrap(int deviceId) throws DeviceServiceException;

    int put_TextWrap(int deviceId, int var1) throws DeviceServiceException;

    String get_Barcode(int deviceId) throws DeviceServiceException;

    int put_Barcode(int deviceId, String var1) throws DeviceServiceException;

    int get_BarcodeType(int deviceId) throws DeviceServiceException;

    int put_BarcodeType(int deviceId, int var1) throws DeviceServiceException;

    boolean get_PrintBarcodeText(int deviceId) throws DeviceServiceException;

    int put_PrintBarcodeText(int deviceId, boolean var1) throws DeviceServiceException;

    int get_SlipDocOrientation(int deviceId) throws DeviceServiceException;

    int put_SlipDocOrientation(int deviceId, int var1) throws DeviceServiceException;

    double get_Scale(int deviceId) throws DeviceServiceException;

    int put_Scale(int deviceId, double var1) throws DeviceServiceException;

    int get_Height(int deviceId) throws DeviceServiceException;

    int put_Height(int deviceId, int var1) throws DeviceServiceException;

    int get_TypeClose(int deviceId) throws DeviceServiceException;

    int put_TypeClose(int deviceId, int var1) throws DeviceServiceException;

    double get_Summ(int deviceId) throws DeviceServiceException;

    int put_Summ(int deviceId, double var1) throws DeviceServiceException;

    int get_CheckType(int deviceId) throws DeviceServiceException;

    int get_CheckState(int deviceId) throws DeviceServiceException;

    int put_CheckType(int deviceId, int var1) throws DeviceServiceException;

    int get_CheckNumber(int deviceId) throws DeviceServiceException;

    int put_CheckNumber(int deviceId, int var1) throws DeviceServiceException;

    int get_RegisterNumber(int deviceId) throws DeviceServiceException;

    int put_RegisterNumber(int deviceId, int var1) throws DeviceServiceException;

    int get_DocNumber(int deviceId) throws DeviceServiceException;

    int put_DocNumber(int deviceId, int var1) throws DeviceServiceException;

    boolean get_SessionOpened(int deviceId) throws DeviceServiceException;

    int get_Session(int deviceId) throws DeviceServiceException;

    int put_Session(int deviceId, int var1) throws DeviceServiceException;

    boolean get_CheckPaperPresent(int deviceId) throws DeviceServiceException;

    boolean get_ControlPaperPresent(int deviceId) throws DeviceServiceException;

    int get_PLUNumber(int deviceId) throws DeviceServiceException;

    int put_PLUNumber(int deviceId, int var1) throws DeviceServiceException;

    String get_Name(int deviceId) throws DeviceServiceException;

    int put_Name(int deviceId, String var1) throws DeviceServiceException;

    double get_Price(int deviceId) throws DeviceServiceException;

    int put_Price(int deviceId, double var1) throws DeviceServiceException;

    double get_Quantity(int deviceId) throws DeviceServiceException;

    int put_Quantity(int deviceId, double var1) throws DeviceServiceException;

    int get_Department(int deviceId) throws DeviceServiceException;

    int put_Department(int deviceId, int var1) throws DeviceServiceException;

    int get_DiscountType(int deviceId) throws DeviceServiceException;

    int put_DiscountType(int deviceId, int var1) throws DeviceServiceException;

    int get_ReportType(int deviceId) throws DeviceServiceException;

    int put_ReportType(int deviceId, int var1) throws DeviceServiceException;

    String get_InfoLine(int deviceId) throws DeviceServiceException;

    int get_Model(int deviceId) throws DeviceServiceException;

    boolean get_ClearFlag(int deviceId) throws DeviceServiceException;

    int put_ClearFlag(int deviceId, boolean var1) throws DeviceServiceException;

    String get_FileName(int deviceId) throws DeviceServiceException;

    int put_FileName(int deviceId, String var1) throws DeviceServiceException;

    String get_INN(int deviceId) throws DeviceServiceException;

    int put_INN(int deviceId, String var1) throws DeviceServiceException;

    String get_MachineNumber(int deviceId) throws DeviceServiceException;

    int put_MachineNumber(int deviceId, String var1) throws DeviceServiceException;

    String get_License(int deviceId) throws DeviceServiceException;

    int put_License(int deviceId, String var1) throws DeviceServiceException;

    int get_LicenseNumber(int deviceId) throws DeviceServiceException;

    int put_LicenseNumber(int deviceId, int var1) throws DeviceServiceException;

    int get_Table(int deviceId) throws DeviceServiceException;

    int put_Table(int deviceId, int var1) throws DeviceServiceException;

    int get_Row(int deviceId) throws DeviceServiceException;

    int put_Row(int deviceId, int var1) throws DeviceServiceException;

    int get_Field(int deviceId) throws DeviceServiceException;

    int put_Field(int deviceId, int var1) throws DeviceServiceException;

    int get_FieldType(int deviceId) throws DeviceServiceException;

    int put_FieldType(int deviceId, int var1) throws DeviceServiceException;

    String get_CommandBuffer(int deviceId) throws DeviceServiceException;

    int put_CommandBuffer(int deviceId, String var1) throws DeviceServiceException;

    String get_AnswerBuffer(int deviceId) throws DeviceServiceException;

    long get_DateEnd(int deviceId) throws DeviceServiceException;

    int put_DateEnd(int deviceId, long var1) throws DeviceServiceException;

    int get_SessionEnd(int deviceId) throws DeviceServiceException;

    int put_SessionEnd(int deviceId, int var1) throws DeviceServiceException;

    int get_EKLZFlags(int deviceId) throws DeviceServiceException;

    int get_EKLZKPKNumber(int deviceId) throws DeviceServiceException;

    int put_EKLZKPKNumber(int deviceId, int var1) throws DeviceServiceException;

    int get_UnitType(int deviceId) throws DeviceServiceException;

    int put_UnitType(int deviceId, int var1) throws DeviceServiceException;

    int get_PictureNumber(int deviceId) throws DeviceServiceException;

    int put_PictureNumber(int deviceId, int var1) throws DeviceServiceException;

    int get_LeftMargin(int deviceId) throws DeviceServiceException;

    int put_LeftMargin(int deviceId, int var1) throws DeviceServiceException;

    int get_Memory(int deviceId) throws DeviceServiceException;

    int get_PictureState(int deviceId) throws DeviceServiceException;

    int get_Width(int deviceId) throws DeviceServiceException;

    int put_Width(int deviceId, int var1) throws DeviceServiceException;

    int get_Operator(int deviceId) throws DeviceServiceException;

    int put_Operator(int deviceId, int var1) throws DeviceServiceException;

    int put_FontBold(int deviceId, boolean var1) throws DeviceServiceException;

    boolean get_FontBold(int deviceId) throws DeviceServiceException;

    int put_FontItalic(int deviceId, boolean var1) throws DeviceServiceException;

    boolean get_FontItalic(int deviceId) throws DeviceServiceException;

    int put_FontNegative(int deviceId, boolean var1) throws DeviceServiceException;

    boolean get_FontNegative(int deviceId) throws DeviceServiceException;

    int put_FontUnderline(int deviceId, boolean var1) throws DeviceServiceException;

    boolean get_FontUnderline(int deviceId) throws DeviceServiceException;

    int put_FontDblHeight(int deviceId, boolean var1) throws DeviceServiceException;

    boolean get_FontDblHeight(int deviceId) throws DeviceServiceException;

    int put_FontDblWidth(int deviceId, boolean var1) throws DeviceServiceException;

    boolean get_FontDblWidth(int deviceId) throws DeviceServiceException;

    int put_PrintPurpose(int deviceId, int var1) throws DeviceServiceException;

    int get_PrintPurpose(int deviceId) throws DeviceServiceException;

    int put_ReceiptFont(int deviceId, int var1) throws DeviceServiceException;

    int get_ReceiptFont(int deviceId) throws DeviceServiceException;

    int put_ReceiptFontHeight(int deviceId, int var1) throws DeviceServiceException;

    int get_ReceiptFontHeight(int deviceId) throws DeviceServiceException;

    int put_ReceiptBrightness(int deviceId, int var1) throws DeviceServiceException;

    int get_ReceiptBrightness(int deviceId) throws DeviceServiceException;

    int put_ReceiptLinespacing(int deviceId, int var1) throws DeviceServiceException;

    int get_ReceiptLinespacing(int deviceId) throws DeviceServiceException;

    int put_JournalFont(int deviceId, int var1) throws DeviceServiceException;

    int get_JournalFont(int deviceId) throws DeviceServiceException;

    int put_JournalFontHeight(int deviceId, int var1) throws DeviceServiceException;

    int get_JournalFontHeight(int deviceId) throws DeviceServiceException;

    int put_JournalBrightness(int deviceId, int var1) throws DeviceServiceException;

    int get_JournalBrightness(int deviceId) throws DeviceServiceException;

    int put_JournalLinespacing(int deviceId, int var1) throws DeviceServiceException;

    int get_JournalLinespacing(int deviceId) throws DeviceServiceException;

    int put_SummPointPosition(int deviceId, int var1) throws DeviceServiceException;

    int get_SummPointPosition(int deviceId) throws DeviceServiceException;

    int put_TaxNumber(int deviceId, int var1) throws DeviceServiceException;

    int get_TaxNumber(int deviceId) throws DeviceServiceException;

    int put_BarcodePrintType(int deviceId, int var1) throws DeviceServiceException;

    int get_BarcodePrintType(int deviceId) throws DeviceServiceException;

    int put_BarcodeControlCode(int deviceId, boolean var1) throws DeviceServiceException;

    boolean get_BarcodeControlCode(int deviceId) throws DeviceServiceException;

    int put_BarcodeCorrection(int deviceId, int var1) throws DeviceServiceException;

    int get_BarcodeCorrection(int deviceId) throws DeviceServiceException;

    int put_BarcodeEncoding(int deviceId, int var1) throws DeviceServiceException;

    int get_BarcodeEncoding(int deviceId) throws DeviceServiceException;

    int put_BarcodeEncodingMode(int deviceId, int var1) throws DeviceServiceException;

    int get_BarcodeEncodingMode(int deviceId) throws DeviceServiceException;

    int put_FeedValue(int deviceId, int var1) throws DeviceServiceException;

    int get_FeedValue(int deviceId) throws DeviceServiceException;

    long get_ClsPtr(int deviceId) throws DeviceServiceException;

    int get_PixelLineLength(int deviceId) throws DeviceServiceException;

    int get_RcpPixelLineLength(int deviceId) throws DeviceServiceException;

    int get_JrnPixelLineLength(int deviceId) throws DeviceServiceException;

    int get_SlipPixelLineLength(int deviceId) throws DeviceServiceException;

    int get_RcpCharLineLength(int deviceId) throws DeviceServiceException;

    int get_JrnCharLineLength(int deviceId) throws DeviceServiceException;

    int get_SlipCharLineLength(int deviceId) throws DeviceServiceException;

    int put_Count(int deviceId, int var1) throws DeviceServiceException;

    int get_Count(int deviceId) throws DeviceServiceException;

    int put_SlotNumber(int deviceId, int var1) throws DeviceServiceException;

    int get_SlotNumber(int deviceId) throws DeviceServiceException;

    boolean get_DrawerOpened(int deviceId) throws DeviceServiceException;

    boolean get_CoverOpened(int deviceId) throws DeviceServiceException;

    boolean get_BatteryLow(int deviceId) throws DeviceServiceException;

    String get_VerHi(int deviceId) throws DeviceServiceException;

    String get_VerLo(int deviceId) throws DeviceServiceException;

    String get_Build(int deviceId) throws DeviceServiceException;

    int get_Codepage(int deviceId) throws DeviceServiceException;

    double get_Remainder(int deviceId) throws DeviceServiceException;

    double get_Change(int deviceId) throws DeviceServiceException;

    int get_LogicalNumber(int deviceId) throws DeviceServiceException;

    int put_LogicalNumber(int deviceId, int var1) throws DeviceServiceException;

    int get_OperationType(int deviceId) throws DeviceServiceException;

    int put_OperationType(int deviceId, int var1) throws DeviceServiceException;

    int put_CounterType(int deviceId, int var1) throws DeviceServiceException;

    int get_CounterType(int deviceId) throws DeviceServiceException;

    double get_PowerSupplyValue(int deviceId) throws DeviceServiceException;

    int get_PowerSupplyState(int deviceId) throws DeviceServiceException;

    int put_PowerSupplyType(int deviceId, int var1) throws DeviceServiceException;

    int get_PowerSupplyType(int deviceId) throws DeviceServiceException;

    int put_StepCounterType(int deviceId, int var1) throws DeviceServiceException;

    int get_StepCounterType(int deviceId) throws DeviceServiceException;

    int put_Destination(int deviceId, int var1) throws DeviceServiceException;

    int get_Destination(int deviceId) throws DeviceServiceException;

    int put_BarcodePixelProportions(int deviceId, int var1) throws DeviceServiceException;

    int get_BarcodePixelProportions(int deviceId) throws DeviceServiceException;

    int put_BarcodeProportions(int deviceId, int var1) throws DeviceServiceException;

    int get_BarcodeProportions(int deviceId) throws DeviceServiceException;

    int put_BarcodeColumns(int deviceId, int var1) throws DeviceServiceException;

    int get_BarcodeColumns(int deviceId) throws DeviceServiceException;

    int put_BarcodeRows(int deviceId, int var1) throws DeviceServiceException;

    int get_BarcodeRows(int deviceId) throws DeviceServiceException;

    int put_BarcodePackingMode(int deviceId, int var1) throws DeviceServiceException;

    int get_BarcodePackingMode(int deviceId) throws DeviceServiceException;

    int put_BarcodeUseProportions(int deviceId, boolean var1) throws DeviceServiceException;

    boolean get_BarcodeUseProportions(int deviceId) throws DeviceServiceException;

    int put_BarcodeUseRows(int deviceId, boolean var1) throws DeviceServiceException;

    boolean get_BarcodeUseRows(int deviceId) throws DeviceServiceException;

    int put_BarcodeUseColumns(int deviceId, boolean var1) throws DeviceServiceException;

    boolean get_BarcodeUseColumns(int deviceId) throws DeviceServiceException;

    int put_BarcodeUseCorrection(int deviceId, boolean var1) throws DeviceServiceException;

    boolean get_BarcodeUseCorrection(int deviceId) throws DeviceServiceException;

    int put_BarcodeUseCodeWords(int deviceId, boolean var1) throws DeviceServiceException;

    boolean get_BarcodeUseCodeWords(int deviceId) throws DeviceServiceException;

    int put_BarcodeInvert(int deviceId, boolean var1) throws DeviceServiceException;

    boolean get_BarcodeInvert(int deviceId) throws DeviceServiceException;

    int put_BarcodeDeferredPrint(int deviceId, boolean var1) throws DeviceServiceException;

    boolean get_BarcodeDeferredPrint(int deviceId) throws DeviceServiceException;

    int put_BarcodeNumber(int deviceId, int var1) throws DeviceServiceException;

    int get_BarcodeNumber(int deviceId) throws DeviceServiceException;

    int put_DrawerOnTimeout(int deviceId, int var1) throws DeviceServiceException;

    int get_DrawerOnTimeout(int deviceId) throws DeviceServiceException;

    int put_DrawerOffTimeout(int deviceId, int var1) throws DeviceServiceException;

    int get_DrawerOffTimeout(int deviceId) throws DeviceServiceException;

    int put_DrawerOnQuantity(int deviceId, int var1) throws DeviceServiceException;

    int get_DrawerOnQuantity(int deviceId) throws DeviceServiceException;

    int put_Frequency(int deviceId, int var1) throws DeviceServiceException;

    int get_Frequency(int deviceId) throws DeviceServiceException;

    int put_Duration(int deviceId, int var1) throws DeviceServiceException;

    int get_Duration(int deviceId) throws DeviceServiceException;

    int put_Directory(int deviceId, String var1) throws DeviceServiceException;

    String get_Directory(int deviceId) throws DeviceServiceException;

    int put_FileSize(int deviceId, int var1) throws DeviceServiceException;

    int get_FileSize(int deviceId) throws DeviceServiceException;

    int put_FileOpenType(int deviceId, int var1) throws DeviceServiceException;

    int get_FileOpenType(int deviceId) throws DeviceServiceException;

    int put_FileOpenMode(int deviceId, int var1) throws DeviceServiceException;

    int get_FileOpenMode(int deviceId) throws DeviceServiceException;

    int put_FileOffset(int deviceId, int var1) throws DeviceServiceException;

    int get_FileOffset(int deviceId) throws DeviceServiceException;

    int put_FileReadSize(int deviceId, int var1) throws DeviceServiceException;

    int get_FileReadSize(int deviceId) throws DeviceServiceException;

    int SetMode(int deviceId) throws DeviceServiceException;

    int ResetMode(int deviceId) throws DeviceServiceException;

    int Beep(int deviceId) throws DeviceServiceException;

    int Sound(int deviceId) throws DeviceServiceException;

    int OpenDrawer(int deviceId) throws DeviceServiceException;

    int AdvancedOpenDrawer(int deviceId) throws DeviceServiceException;

    int FullCut(int deviceId) throws DeviceServiceException;

    int PartialCut(int deviceId) throws DeviceServiceException;

    int Feed(int deviceId) throws DeviceServiceException;

    int OpenDirectory(int deviceId) throws DeviceServiceException;

    int ReadDirectory(int deviceId) throws DeviceServiceException;

    int CloseDirectory(int deviceId) throws DeviceServiceException;

    int OpenFile(int deviceId) throws DeviceServiceException;

    int CloseFile(int deviceId) throws DeviceServiceException;

    int DeleteFileFromSD(int deviceId) throws DeviceServiceException;

    int WriteFileToSD(int deviceId) throws DeviceServiceException;

    int ReadFile(int deviceId) throws DeviceServiceException;

    int GetStatus(int deviceId) throws DeviceServiceException;

    int GetRegister(int deviceId) throws DeviceServiceException;

    int GetRange(int deviceId) throws DeviceServiceException;

    int GetSumm(int deviceId) throws DeviceServiceException;

    int OpenSession(int deviceId) throws DeviceServiceException;

    int CashIncome(int deviceId) throws DeviceServiceException;

    int CashOutcome(int deviceId) throws DeviceServiceException;

    int Report(int deviceId) throws DeviceServiceException;

    int NewDocument(int deviceId) throws DeviceServiceException;

    int OpenCheck(int deviceId) throws DeviceServiceException;

    int Registration(int deviceId) throws DeviceServiceException;

    int Annulate(int deviceId) throws DeviceServiceException;

    int Return(int deviceId) throws DeviceServiceException;

    int Buy(int deviceId) throws DeviceServiceException;

    int BuyReturn(int deviceId) throws DeviceServiceException;

    int BuyAnnulate(int deviceId) throws DeviceServiceException;

    int Storno(int deviceId) throws DeviceServiceException;

    int Discount(int deviceId) throws DeviceServiceException;

    int Charge(int deviceId) throws DeviceServiceException;

    int ResetChargeDiscount(int deviceId) throws DeviceServiceException;

    int Payment(int deviceId) throws DeviceServiceException;

    int StornoPayment(int deviceId) throws DeviceServiceException;

    int CancelCheck(int deviceId) throws DeviceServiceException;

    int CloseCheck(int deviceId) throws DeviceServiceException;

    int SummTax(int deviceId) throws DeviceServiceException;

    int StornoTax(int deviceId) throws DeviceServiceException;

    int PrintString(int deviceId) throws DeviceServiceException;

    int AddTextField(int deviceId) throws DeviceServiceException;

    int PrintFormattedText(int deviceId) throws DeviceServiceException;

    int PrintHeader(int deviceId) throws DeviceServiceException;

    int PrintFooter(int deviceId) throws DeviceServiceException;

    int BeginDocument(int deviceId) throws DeviceServiceException;

    int EndDocument(int deviceId) throws DeviceServiceException;

    int PrintLastCheckCopy(int deviceId) throws DeviceServiceException;

    int PrintBarcode(int deviceId) throws DeviceServiceException;

    int PrintPicture(int deviceId) throws DeviceServiceException;

    int GetPictureArrayStatus(int deviceId) throws DeviceServiceException;

    int GetPictureStatus(int deviceId) throws DeviceServiceException;

    int PrintPictureByNumber(int deviceId) throws DeviceServiceException;

    int AddPicture(int deviceId) throws DeviceServiceException;

    int AddPictureFromFile(int deviceId) throws DeviceServiceException;

    int DeleteLastPicture(int deviceId) throws DeviceServiceException;

    int ClearPictureArray(int deviceId) throws DeviceServiceException;

    int GetPicture(int deviceId) throws DeviceServiceException;

    int ClearBarcodeArray(int deviceId) throws DeviceServiceException;

    int GetBarcode(int deviceId) throws DeviceServiceException;

    int PrintBarcodeByNumber(int deviceId) throws DeviceServiceException;

    int BeginReport(int deviceId) throws DeviceServiceException;

    int GetRecord(int deviceId) throws DeviceServiceException;

    int EndReport(int deviceId) throws DeviceServiceException;

    int BeginAdd(int deviceId) throws DeviceServiceException;

    int SetRecord(int deviceId) throws DeviceServiceException;

    int EndAdd(int deviceId) throws DeviceServiceException;

    int SetCaption(int deviceId) throws DeviceServiceException;

    int GetCaption(int deviceId) throws DeviceServiceException;

    int SetValue(int deviceId) throws DeviceServiceException;

    int GetValue(int deviceId) throws DeviceServiceException;

    int SetTableField(int deviceId) throws DeviceServiceException;

    int GetTableField(int deviceId) throws DeviceServiceException;

    int Fiscalization(int deviceId) throws DeviceServiceException;

    int ResetSummary(int deviceId) throws DeviceServiceException;

    int SetDate(int deviceId) throws DeviceServiceException;

    int SetTime(int deviceId) throws DeviceServiceException;

    int GetLicense(int deviceId) throws DeviceServiceException;

    int SetLicense(int deviceId) throws DeviceServiceException;

    int SetPointPosition(int deviceId) throws DeviceServiceException;

    int SetSerialNumber(int deviceId) throws DeviceServiceException;

    int InitTables(int deviceId) throws DeviceServiceException;

    int TechZero(int deviceId) throws DeviceServiceException;

    int RunCommand(int deviceId) throws DeviceServiceException;

    int TestConnector(int deviceId) throws DeviceServiceException;

    int DemoPrint(int deviceId) throws DeviceServiceException;

    int PowerOff(int deviceId) throws DeviceServiceException;

    int ClearOutput(int deviceId) throws DeviceServiceException;

    int WriteData(int deviceId) throws DeviceServiceException;

    int EKLZActivate(int deviceId) throws DeviceServiceException;

    int EKLZCloseArchive(int deviceId) throws DeviceServiceException;

    int EKLZGetStatus(int deviceId) throws DeviceServiceException;

    long get_ScannerPortHandler(int deviceId) throws DeviceServiceException;

    int put_ScannerMode(int deviceId, int var1) throws DeviceServiceException;

    int get_ScannerMode(int deviceId) throws DeviceServiceException;

    int put_PinPadMode(int deviceId, int var1) throws DeviceServiceException;

    int get_PinPadMode(int deviceId) throws DeviceServiceException;

    int PowerOnPinPad(int deviceId) throws DeviceServiceException;

    int PowerOffPinPad(int deviceId) throws DeviceServiceException;

    int WritePinPad(int deviceId) throws DeviceServiceException;

    int ReadPinPad(int deviceId) throws DeviceServiceException;

    long get_PinPadDevice(int deviceId) throws DeviceServiceException;

    int put_ModemMode(int deviceId, int var1) throws DeviceServiceException;

    int get_ModemMode(int deviceId) throws DeviceServiceException;

    int PowerOnModem(int deviceId) throws DeviceServiceException;

    int PowerOffModem(int deviceId) throws DeviceServiceException;

    int WriteModem(int deviceId) throws DeviceServiceException;

    int ReadModem(int deviceId) throws DeviceServiceException;

    long get_ModemDevice(int deviceId) throws DeviceServiceException;

    int put_ReadSize(int deviceId, int var1) throws DeviceServiceException;

    int get_ReadSize(int deviceId) throws DeviceServiceException;

    int put_NeedResultFlag(int deviceId, boolean var1) throws DeviceServiceException;

    boolean get_NeedResultFlag(int deviceId) throws DeviceServiceException;

    int OpenPinPad(int deviceId) throws DeviceServiceException;

    int ClosePinPad(int deviceId) throws DeviceServiceException;

    int OpenModem(int deviceId) throws DeviceServiceException;

    int CloseModem(int deviceId) throws DeviceServiceException;

    int put_ModemConnectionType(int deviceId, int var1) throws DeviceServiceException;

    int get_ModemConnectionType(int deviceId) throws DeviceServiceException;

    int put_ModemAddress(int deviceId, String var1) throws DeviceServiceException;

    String get_ModemAddress(int deviceId) throws DeviceServiceException;

    int put_ModemPort(int deviceId, int var1) throws DeviceServiceException;

    int get_ModemPort(int deviceId) throws DeviceServiceException;

    int GetModemStatus(int deviceId) throws DeviceServiceException;

    int GetPinPadStatus(int deviceId) throws DeviceServiceException;

    int get_WriteSize(int deviceId) throws DeviceServiceException;

    int get_ModemStatus(int deviceId) throws DeviceServiceException;

    int get_ModemSignal(int deviceId) throws DeviceServiceException;

    String get_ModemOperator(int deviceId) throws DeviceServiceException;

    String get_ModemError(int deviceId) throws DeviceServiceException;

    int GetDeviceMetrics(int deviceId) throws DeviceServiceException;

    String get_DeviceDescription(int deviceId) throws DeviceServiceException;

    int GetCurrentMode(int deviceId) throws DeviceServiceException;

    boolean get_OutOfPaper(int deviceId) throws DeviceServiceException;

    boolean get_PrinterConnectionFailed(int deviceId) throws DeviceServiceException;

    boolean get_PrinterMechanismError(int deviceId) throws DeviceServiceException;

    boolean get_PrinterCutMechanismError(int deviceId) throws DeviceServiceException;

    boolean get_PrinterOverheatError(int deviceId) throws DeviceServiceException;

    int GetCurrentStatus(int deviceId) throws DeviceServiceException;

    int GetLastSummary(int deviceId) throws DeviceServiceException;

    int get_AdvancedMode(int deviceId) throws DeviceServiceException;

    int put_BottomMargin(int deviceId, int var1) throws DeviceServiceException;

    int get_BottomMargin(int deviceId) throws DeviceServiceException;

    int EKLZGetKPK(int deviceId) throws DeviceServiceException;

    int get_EKLZKPK(int deviceId) throws DeviceServiceException;

    int put_BarcodeVersion(int deviceId, int var1) throws DeviceServiceException;

    int get_BarcodeVersion(int deviceId) throws DeviceServiceException;

    int put_TaxPassword(int deviceId, String var1) throws DeviceServiceException;

    String get_TaxPassword(int deviceId) throws DeviceServiceException;

    int put_Classifier(int deviceId, String var1) throws DeviceServiceException;

    String get_Classifier(int deviceId) throws DeviceServiceException;

    int put_FiscalPropertyNumber(int deviceId, int var1) throws DeviceServiceException;

    int get_FiscalPropertyNumber(int deviceId) throws DeviceServiceException;

    int put_FiscalPropertyValue(int deviceId, String var1) throws DeviceServiceException;

    String get_FiscalPropertyValue(int deviceId) throws DeviceServiceException;

    int put_FiscalPropertyType(int deviceId, int var1) throws DeviceServiceException;

    int get_FiscalPropertyType(int deviceId) throws DeviceServiceException;

    int put_FiscalPropertyPrint(int deviceId, boolean var1) throws DeviceServiceException;

    boolean get_FiscalPropertyPrint(int deviceId) throws DeviceServiceException;

    int WriteFiscalProperty(int deviceId) throws DeviceServiceException;

    int ReadFiscalProperty(int deviceId) throws DeviceServiceException;

    boolean get_HasNotSendedDocs(int deviceId) throws DeviceServiceException;

    int RunFNCommand(int deviceId) throws DeviceServiceException;

    int get_CounterDimension(int deviceId) throws DeviceServiceException;

    int put_CounterDimension(int deviceId, int var1) throws DeviceServiceException;

    int put_DiscountNumber(int deviceId, int i) throws DeviceServiceException;

    int get_DiscountNumber(int deviceId) throws DeviceServiceException;

    int DeleteLastBarcode(int deviceId) throws DeviceServiceException;

    double get_DiscountInSession(int deviceId) throws DeviceServiceException;

    double get_ChargeInSession(int deviceId) throws DeviceServiceException;

    int get_NetworkError(int deviceId) throws DeviceServiceException;

    int get_OFDError(int deviceId) throws DeviceServiceException;

    int get_FNError(int deviceId) throws DeviceServiceException;

    int get_TimeoutACK(int deviceId) throws DeviceServiceException;

    int put_TimeoutACK(int deviceId, int i) throws DeviceServiceException;

    int get_TimeoutENQ(int deviceId) throws DeviceServiceException;

    int put_TimeoutENQ(int deviceId, int i) throws DeviceServiceException;

    int AddBarcode(int deviceId) throws DeviceServiceException;

    int GetBarcodeArrayStatus(int deviceId) throws DeviceServiceException;

    int Correction(int deviceId) throws DeviceServiceException;

    int ReturnCorrection(int deviceId) throws DeviceServiceException;

    int BuyCorrection(int deviceId) throws DeviceServiceException;

    int BuyReturnCorrection(int deviceId) throws DeviceServiceException;

    int put_PrintCheck(int deviceId, boolean b) throws DeviceServiceException;

    boolean get_PrintCheck(int deviceId) throws DeviceServiceException;

    String getCachedFnRegNum(int deviceId) throws DeviceServiceException;

    String getCachedKktNumber(int deviceId) throws DeviceServiceException;

    boolean isCachedAutonomous(int deviceId) throws DeviceServiceException;

    boolean isCachedService(int deviceId) throws DeviceServiceException;

    boolean isCachedCrypt(int deviceId) throws DeviceServiceException;

    boolean isCachedInternetPay(int deviceId) throws DeviceServiceException;

    String getCachedFnLifePhase(int deviceId) throws DeviceServiceException;

    String getCachedTaxSystems(int deviceId) throws DeviceServiceException;

    String getCachedDefaultTaxSystem(int deviceId) throws DeviceServiceException;

    String getCachedOrgInfoInn(int deviceId) throws DeviceServiceException;

    String getCachedOrgInfoAddress(int deviceId) throws DeviceServiceException;

    String getCachedOrgInfoName(int deviceId) throws DeviceServiceException;

    String getCachedOfdInfoInn(int deviceId) throws DeviceServiceException;

    int put_DiscountSumm(int deviceId, double v) throws DeviceServiceException;

    int put_TaxSumm(int deviceId, double v) throws DeviceServiceException;

    int put_FeatureOfCalculation(int deviceId, int i) throws DeviceServiceException;

    int put_MethodOfCalculation(int deviceId, int i) throws DeviceServiceException;

    int get_FNState(int deviceId) throws DeviceServiceException;

    void refreshCachedValues(int deviceId) throws DeviceServiceException;

    boolean hasNewSoftwareForFlashing(int deviceId) throws DeviceServiceException;

    void flashAllIfAvailable(int deviceId) throws DeviceServiceException;
}
