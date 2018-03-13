package ru.evotor.devices.commons;

import ru.evotor.devices.commons.result.ResultVoid;
import ru.evotor.devices.commons.result.ResultBoolean;
import ru.evotor.devices.commons.result.ResultString;
import ru.evotor.devices.commons.result.ResultInt;
import ru.evotor.devices.commons.result.ResultLong;
import ru.evotor.devices.commons.result.ResultDouble;

interface IKKMService {

	ResultBoolean is54fzDevice(int deviceId);

	ResultInt getCachedFfdKKMNumber(int deviceId);

	ResultInt getCachedFfdFnNumber(int deviceId);

	ResultString getCachedKkmSerialNumber(int deviceId);

	ResultString getCachedKkmInn(int deviceId);

	ResultString getCachedEKLZSerialNumber(int deviceId);

	ResultInt getCachedEKLZFlags(int deviceId);

	ResultInt getCachedEKLZKPKNumber(int deviceId);

	ResultInt getCachedEKLZKPK(int deviceId);

	ResultString getCachedDeviceDescription(int deviceId);

	ResultInt getCachedNotSendedDocsCount(int deviceId);

	ResultString getCachedOfdAddress(int deviceId);

	ResultInt getCachedOfdPort(int deviceId);

	ResultLong getCachedOfdDocumentDate(int deviceId);

	ResultLong getCachedOfdDocumentTime(int deviceId);

	ResultInt getCachedNetworkError(int deviceId);

	ResultInt getCachedFnError(int deviceId);

	ResultInt getCachedOfdError(int deviceId);

	ResultInt get_LicenseValid(int deviceId);

	ResultString get_LicenseExpiredDate(int deviceId);

	ResultString get_Version(int deviceId);

	ResultString get_DriverName(int deviceId);

	ResultBoolean get_DeviceEnabled(int deviceId);

	ResultInt put_DeviceEnabled(int deviceId, boolean var1);

	ResultInt get_ResultCode(int deviceId);

	ResultString get_ResultDescription(int deviceId);

	ResultInt get_BadParam(int deviceId);

	ResultString get_BadParamDescription(int deviceId);

	ResultString get_DeviceSettings(int deviceId);

	ResultInt put_DeviceSettings(int deviceId, String var1);

	ResultString get_DeviceSingleSetting(int deviceId, String var1);

	ResultInt put_DeviceSingleStringSetting(int deviceId, String var1, String var2);

	ResultInt put_DeviceSingleIntSetting(int deviceId, String var1, int var2);

	ResultInt put_DeviceSingleDoubleSetting(int deviceId, String var1, double var2);

	ResultString get_DeviceSingleSettingMapping(int deviceId, String var1);

	ResultInt ShowProperties(int deviceId);

	ResultInt ApplySingleSettings(int deviceId);

	ResultInt ResetSingleSettings(int deviceId);

	ResultString get_Caption(int deviceId);

	ResultInt put_Caption(int deviceId, String var1);

	ResultInt get_CaptionPurpose(int deviceId);

	ResultInt put_CaptionPurpose(int deviceId, int var1);

	ResultBoolean get_CaptionIsSupported(int deviceId);

	ResultString get_CaptionName(int deviceId);

	ResultDouble get_Value(int deviceId);

	ResultInt put_Value(int deviceId, double var1);

	ResultInt get_ValuePurpose(int deviceId);

	ResultInt put_ValuePurpose(int deviceId, int var1);

	ResultBoolean get_ValueIsSupported(int deviceId);

	ResultString get_ValueName(int deviceId);

	ResultString get_ValueMapping(int deviceId);

	ResultInt get_CharLineLength(int deviceId);

	ResultString get_SerialNumber(int deviceId);

	ResultInt put_SerialNumber(int deviceId, String var1);

	ResultLong get_Time(int deviceId);

	ResultInt put_Time(int deviceId, long var1);

	ResultLong get_Date(int deviceId);

	ResultInt put_Date(int deviceId, long var1);

	ResultBoolean get_Fiscal(int deviceId);

	ResultBoolean get_TestMode(int deviceId);

	ResultInt put_TestMode(int deviceId, boolean var1);

	ResultBoolean get_EnableCheckSumm(int deviceId);

	ResultInt put_EnableCheckSumm(int deviceId, boolean var1);

	ResultString get_UserPassword(int deviceId);

	ResultInt put_UserPassword(int deviceId, String var1);

	ResultInt get_Mode(int deviceId);

	ResultInt put_Mode(int deviceId, int var1);

	ResultInt get_Alignment(int deviceId);

	ResultInt put_Alignment(int deviceId, int var1);

	ResultInt get_TextWrap(int deviceId);

	ResultInt put_TextWrap(int deviceId, int var1);

	ResultString get_Barcode(int deviceId);

	ResultInt put_Barcode(int deviceId, String var1);

	ResultInt get_BarcodeType(int deviceId);

	ResultInt put_BarcodeType(int deviceId, int var1);

	ResultBoolean get_PrintBarcodeText(int deviceId);

	ResultInt put_PrintBarcodeText(int deviceId, boolean var1);

	ResultInt get_SlipDocOrientation(int deviceId);

	ResultInt put_SlipDocOrientation(int deviceId, int var1);

	ResultDouble get_Scale(int deviceId);

	ResultInt put_Scale(int deviceId, double var1);

	ResultInt get_Height(int deviceId);

	ResultInt put_Height(int deviceId, int var1);

	ResultInt get_TypeClose(int deviceId);

	ResultInt put_TypeClose(int deviceId, int var1);

	ResultDouble get_Summ(int deviceId);

	ResultInt put_Summ(int deviceId, double var1);

	ResultInt get_CheckType(int deviceId);

	ResultInt get_CheckState(int deviceId);

	ResultInt put_CheckType(int deviceId, int var1);

	ResultInt get_CheckNumber(int deviceId);

	ResultInt put_CheckNumber(int deviceId, int var1);

	ResultInt get_RegisterNumber(int deviceId);

	ResultInt put_RegisterNumber(int deviceId, int var1);

	ResultInt get_DocNumber(int deviceId);

	ResultInt put_DocNumber(int deviceId, int var1);

	ResultBoolean get_SessionOpened(int deviceId);

	ResultInt get_Session(int deviceId);

	ResultInt put_Session(int deviceId, int var1);

	ResultBoolean get_CheckPaperPresent(int deviceId);

	ResultBoolean get_ControlPaperPresent(int deviceId);

	ResultInt get_PLUNumber(int deviceId);

	ResultInt put_PLUNumber(int deviceId, int var1);

	ResultString get_Name(int deviceId);

	ResultInt put_Name(int deviceId, String var1);

	ResultDouble get_Price(int deviceId);

	ResultInt put_Price(int deviceId, double var1);

	ResultDouble get_Quantity(int deviceId);

	ResultInt put_Quantity(int deviceId, double var1);

	ResultInt get_Department(int deviceId);

	ResultInt put_Department(int deviceId, int var1);

	ResultInt get_DiscountType(int deviceId);

	ResultInt put_DiscountType(int deviceId, int var1);

	ResultInt get_ReportType(int deviceId);

	ResultInt put_ReportType(int deviceId, int var1);

	ResultString get_InfoLine(int deviceId);

	ResultInt get_Model(int deviceId);

	ResultBoolean get_ClearFlag(int deviceId);

	ResultInt put_ClearFlag(int deviceId, boolean var1);

	ResultString get_FileName(int deviceId);

	ResultInt put_FileName(int deviceId, String var1);

	ResultString get_INN(int deviceId);

	ResultInt put_INN(int deviceId, String var1);

	ResultString get_MachineNumber(int deviceId);

	ResultInt put_MachineNumber(int deviceId, String var1);

	ResultString get_License(int deviceId);

	ResultInt put_License(int deviceId, String var1);

	ResultInt get_LicenseNumber(int deviceId);

	ResultInt put_LicenseNumber(int deviceId, int var1);

	ResultInt get_Table(int deviceId);

	ResultInt put_Table(int deviceId, int var1);

	ResultInt get_Row(int deviceId);

	ResultInt put_Row(int deviceId, int var1);

	ResultInt get_Field(int deviceId);

	ResultInt put_Field(int deviceId, int var1);

	ResultInt get_FieldType(int deviceId);

	ResultInt put_FieldType(int deviceId, int var1);

	ResultString get_CommandBuffer(int deviceId);

	ResultInt put_CommandBuffer(int deviceId, String var1);

	ResultString get_AnswerBuffer(int deviceId);

	ResultLong get_DateEnd(int deviceId);

	ResultInt put_DateEnd(int deviceId, long var1);

	ResultInt get_SessionEnd(int deviceId);

	ResultInt put_SessionEnd(int deviceId, int var1);

	ResultInt get_EKLZFlags(int deviceId);

	ResultInt get_EKLZKPKNumber(int deviceId);

	ResultInt put_EKLZKPKNumber(int deviceId, int var1);

	ResultInt get_UnitType(int deviceId);

	ResultInt put_UnitType(int deviceId, int var1);

	ResultInt get_PictureNumber(int deviceId);

	ResultInt put_PictureNumber(int deviceId, int var1);

	ResultInt get_LeftMargin(int deviceId);

	ResultInt put_LeftMargin(int deviceId, int var1);

	ResultInt get_Memory(int deviceId);

	ResultInt get_PictureState(int deviceId);

	ResultInt get_Width(int deviceId);

	ResultInt put_Width(int deviceId, int var1);

	ResultInt get_Operator(int deviceId);

	ResultInt put_Operator(int deviceId, int var1);

	ResultInt put_FontBold(int deviceId, boolean var1);

	ResultBoolean get_FontBold(int deviceId);

	ResultInt put_FontItalic(int deviceId, boolean var1);

	ResultBoolean get_FontItalic(int deviceId);

	ResultInt put_FontNegative(int deviceId, boolean var1);

	ResultBoolean get_FontNegative(int deviceId);

	ResultInt put_FontUnderline(int deviceId, boolean var1);

	ResultBoolean get_FontUnderline(int deviceId);

	ResultInt put_FontDblHeight(int deviceId, boolean var1);

	ResultBoolean get_FontDblHeight(int deviceId);

	ResultInt put_FontDblWidth(int deviceId, boolean var1);

	ResultBoolean get_FontDblWidth(int deviceId);

	ResultInt put_PrintPurpose(int deviceId, int var1);

	ResultInt get_PrintPurpose(int deviceId);

	ResultInt put_ReceiptFont(int deviceId, int var1);

	ResultInt get_ReceiptFont(int deviceId);

	ResultInt put_ReceiptFontHeight(int deviceId, int var1);

	ResultInt get_ReceiptFontHeight(int deviceId);

	ResultInt put_ReceiptBrightness(int deviceId, int var1);

	ResultInt get_ReceiptBrightness(int deviceId);

	ResultInt put_ReceiptLinespacing(int deviceId, int var1);

	ResultInt get_ReceiptLinespacing(int deviceId);

	ResultInt put_JournalFont(int deviceId, int var1);

	ResultInt get_JournalFont(int deviceId);

	ResultInt put_JournalFontHeight(int deviceId, int var1);

	ResultInt get_JournalFontHeight(int deviceId);

	ResultInt put_JournalBrightness(int deviceId, int var1);

	ResultInt get_JournalBrightness(int deviceId);

	ResultInt put_JournalLinespacing(int deviceId, int var1);

	ResultInt get_JournalLinespacing(int deviceId);

	ResultInt put_SummPointPosition(int deviceId, int var1);

	ResultInt get_SummPointPosition(int deviceId);

	ResultInt put_TaxNumber(int deviceId, int var1);

	ResultInt get_TaxNumber(int deviceId);

	ResultInt put_BarcodePrintType(int deviceId, int var1);

	ResultInt get_BarcodePrintType(int deviceId);

	ResultInt put_BarcodeControlCode(int deviceId, boolean var1);

	ResultBoolean get_BarcodeControlCode(int deviceId);

	ResultInt put_BarcodeCorrection(int deviceId, int var1);

	ResultInt get_BarcodeCorrection(int deviceId);

	ResultInt put_BarcodeEncoding(int deviceId, int var1);

	ResultInt get_BarcodeEncoding(int deviceId);

	ResultInt put_BarcodeEncodingMode(int deviceId, int var1);

	ResultInt get_BarcodeEncodingMode(int deviceId);

	ResultInt put_FeedValue(int deviceId, int var1);

	ResultInt get_FeedValue(int deviceId);

	ResultLong get_ClsPtr(int deviceId);

	ResultInt get_PixelLineLength(int deviceId);

	ResultInt get_RcpPixelLineLength(int deviceId);

	ResultInt get_JrnPixelLineLength(int deviceId);

	ResultInt get_SlipPixelLineLength(int deviceId);

	ResultInt get_RcpCharLineLength(int deviceId);

	ResultInt get_JrnCharLineLength(int deviceId);

	ResultInt get_SlipCharLineLength(int deviceId);

	ResultInt put_Count(int deviceId, int var1);

	ResultInt get_Count(int deviceId);

	ResultInt put_SlotNumber(int deviceId, int var1);

	ResultInt get_SlotNumber(int deviceId);

	ResultBoolean get_DrawerOpened(int deviceId);

	ResultBoolean get_CoverOpened(int deviceId);

	ResultBoolean get_BatteryLow(int deviceId);

	ResultString get_VerHi(int deviceId);

	ResultString get_VerLo(int deviceId);

	ResultString get_Build(int deviceId);

	ResultInt get_Codepage(int deviceId);

	ResultDouble get_Remainder(int deviceId);

	ResultDouble get_Change(int deviceId);

	ResultInt get_LogicalNumber(int deviceId);

	ResultInt put_LogicalNumber(int deviceId, int var1);

	ResultInt get_OperationType(int deviceId);

	ResultInt put_OperationType(int deviceId, int var1);

	ResultInt put_CounterType(int deviceId, int var1);

	ResultInt get_CounterType(int deviceId);

	ResultDouble get_PowerSupplyValue(int deviceId);

	ResultInt get_PowerSupplyState(int deviceId);

	ResultInt put_PowerSupplyType(int deviceId, int var1);

	ResultInt get_PowerSupplyType(int deviceId);

	ResultInt put_StepCounterType(int deviceId, int var1);

	ResultInt get_StepCounterType(int deviceId);

	ResultInt put_Destination(int deviceId, int var1);

	ResultInt get_Destination(int deviceId);

	ResultInt put_BarcodePixelProportions(int deviceId, int var1);

	ResultInt get_BarcodePixelProportions(int deviceId);

	ResultInt put_BarcodeProportions(int deviceId, int var1);

	ResultInt get_BarcodeProportions(int deviceId);

	ResultInt put_BarcodeColumns(int deviceId, int var1);

	ResultInt get_BarcodeColumns(int deviceId);

	ResultInt put_BarcodeRows(int deviceId, int var1);

	ResultInt get_BarcodeRows(int deviceId);

	ResultInt put_BarcodePackingMode(int deviceId, int var1);

	ResultInt get_BarcodePackingMode(int deviceId);

	ResultInt put_BarcodeUseProportions(int deviceId, boolean var1);

	ResultBoolean get_BarcodeUseProportions(int deviceId);

	ResultInt put_BarcodeUseRows(int deviceId, boolean var1);

	ResultBoolean get_BarcodeUseRows(int deviceId);

	ResultInt put_BarcodeUseColumns(int deviceId, boolean var1);

	ResultBoolean get_BarcodeUseColumns(int deviceId);

	ResultInt put_BarcodeUseCorrection(int deviceId, boolean var1);

	ResultBoolean get_BarcodeUseCorrection(int deviceId);

	ResultInt put_BarcodeUseCodeWords(int deviceId, boolean var1);

	ResultBoolean get_BarcodeUseCodeWords(int deviceId);

	ResultInt put_BarcodeInvert(int deviceId, boolean var1);

	ResultBoolean get_BarcodeInvert(int deviceId);

	ResultInt put_BarcodeDeferredPrint(int deviceId, boolean var1);

	ResultBoolean get_BarcodeDeferredPrint(int deviceId);

	ResultInt put_BarcodeNumber(int deviceId, int var1);

	ResultInt get_BarcodeNumber(int deviceId);

	ResultInt put_DrawerOnTimeout(int deviceId, int var1);

	ResultInt get_DrawerOnTimeout(int deviceId);

	ResultInt put_DrawerOffTimeout(int deviceId, int var1);

	ResultInt get_DrawerOffTimeout(int deviceId);

	ResultInt put_DrawerOnQuantity(int deviceId, int var1);

	ResultInt get_DrawerOnQuantity(int deviceId);

	ResultInt put_Frequency(int deviceId, int var1);

	ResultInt get_Frequency(int deviceId);

	ResultInt put_Duration(int deviceId, int var1);

	ResultInt get_Duration(int deviceId);

	ResultInt put_Directory(int deviceId, String var1);

	ResultString get_Directory(int deviceId);

	ResultInt put_FileSize(int deviceId, int var1);

	ResultInt get_FileSize(int deviceId);

	ResultInt put_FileOpenType(int deviceId, int var1);

	ResultInt get_FileOpenType(int deviceId);

	ResultInt put_FileOpenMode(int deviceId, int var1);

	ResultInt get_FileOpenMode(int deviceId);

	ResultInt put_FileOffset(int deviceId, int var1);

	ResultInt get_FileOffset(int deviceId);

	ResultInt put_FileReadSize(int deviceId, int var1);

	ResultInt get_FileReadSize(int deviceId);

	ResultInt SetMode(int deviceId);

	ResultInt ResetMode(int deviceId);

	ResultInt Beep(int deviceId);

	ResultInt Sound(int deviceId);

	ResultInt OpenDrawer(int deviceId);

	ResultInt AdvancedOpenDrawer(int deviceId);

	ResultInt FullCut(int deviceId);

	ResultInt PartialCut(int deviceId);

	ResultInt Feed(int deviceId);

	ResultInt OpenDirectory(int deviceId);

	ResultInt ReadDirectory(int deviceId);

	ResultInt CloseDirectory(int deviceId);

	ResultInt OpenFile(int deviceId);

	ResultInt CloseFile(int deviceId);

	ResultInt DeleteFileFromSD(int deviceId);

	ResultInt WriteFileToSD(int deviceId);

	ResultInt ReadFile(int deviceId);

	ResultInt GetStatus(int deviceId);

	ResultInt GetRegister(int deviceId);

	ResultInt GetRange(int deviceId);

	ResultInt GetSumm(int deviceId);

	ResultInt OpenSession(int deviceId);

	ResultInt CashIncome(int deviceId);

	ResultInt CashOutcome(int deviceId);

	ResultInt Report(int deviceId);

	ResultInt NewDocument(int deviceId);

	ResultInt OpenCheck(int deviceId);

	ResultInt Registration(int deviceId);

	ResultInt Annulate(int deviceId);

	ResultInt Return(int deviceId);

	ResultInt Buy(int deviceId);

	ResultInt BuyReturn(int deviceId);

	ResultInt BuyAnnulate(int deviceId);

	ResultInt Storno(int deviceId);

	ResultInt Discount(int deviceId);

	ResultInt Charge(int deviceId);

	ResultInt ResetChargeDiscount(int deviceId);

	ResultInt Payment(int deviceId);

	ResultInt StornoPayment(int deviceId);

	ResultInt CancelCheck(int deviceId);

	ResultInt CloseCheck(int deviceId);

	ResultInt SummTax(int deviceId);

	ResultInt StornoTax(int deviceId);

	ResultInt PrintString(int deviceId);

	ResultInt AddTextField(int deviceId);

	ResultInt PrintFormattedText(int deviceId);

	ResultInt PrintHeader(int deviceId);

	ResultInt PrintFooter(int deviceId);

	ResultInt BeginDocument(int deviceId);

	ResultInt EndDocument(int deviceId);

	ResultInt PrintLastCheckCopy(int deviceId);

	ResultInt PrintBarcode(int deviceId);

	ResultInt PrintPicture(int deviceId);

	ResultInt GetPictureArrayStatus(int deviceId);

	ResultInt GetPictureStatus(int deviceId);

	ResultInt PrintPictureByNumber(int deviceId);

	ResultInt AddPicture(int deviceId);

	ResultInt AddPictureFromFile(int deviceId);

	ResultInt DeleteLastPicture(int deviceId);

	ResultInt ClearPictureArray(int deviceId);

	ResultInt GetPicture(int deviceId);

	ResultInt ClearBarcodeArray(int deviceId);

	ResultInt GetBarcode(int deviceId);

	ResultInt PrintBarcodeByNumber(int deviceId);

	ResultInt BeginReport(int deviceId);

	ResultInt GetRecord(int deviceId);

	ResultInt EndReport(int deviceId);

	ResultInt BeginAdd(int deviceId);

	ResultInt SetRecord(int deviceId);

	ResultInt EndAdd(int deviceId);

	ResultInt SetCaption(int deviceId);

	ResultInt GetCaption(int deviceId);

	ResultInt SetValue(int deviceId);

	ResultInt GetValue(int deviceId);

	ResultInt SetTableField(int deviceId);

	ResultInt GetTableField(int deviceId);

	ResultInt Fiscalization(int deviceId);

	ResultInt ResetSummary(int deviceId);

	ResultInt SetDate(int deviceId);

	ResultInt SetTime(int deviceId);

	ResultInt GetLicense(int deviceId);

	ResultInt SetLicense(int deviceId);

	ResultInt SetPointPosition(int deviceId);

	ResultInt SetSerialNumber(int deviceId);

	ResultInt InitTables(int deviceId);

	ResultInt TechZero(int deviceId);

	ResultInt RunCommand(int deviceId);

	ResultInt TestConnector(int deviceId);

	ResultInt DemoPrint(int deviceId);

	ResultInt PowerOff(int deviceId);

	ResultInt ClearOutput(int deviceId);

	ResultInt WriteData(int deviceId);

	ResultInt EKLZActivate(int deviceId);

	ResultInt EKLZCloseArchive(int deviceId);

	ResultInt EKLZGetStatus(int deviceId);

	ResultLong get_ScannerPortHandler(int deviceId);

	ResultInt put_ScannerMode(int deviceId, int var1);

	ResultInt get_ScannerMode(int deviceId);

	ResultInt put_PinPadMode(int deviceId, int var1);

	ResultInt get_PinPadMode(int deviceId);

	ResultInt PowerOnPinPad(int deviceId);

	ResultInt PowerOffPinPad(int deviceId);

	ResultInt WritePinPad(int deviceId);

	ResultInt ReadPinPad(int deviceId);

	ResultLong get_PinPadDevice(int deviceId);

	ResultInt put_ModemMode(int deviceId, int var1);

	ResultInt get_ModemMode(int deviceId);

	ResultInt PowerOnModem(int deviceId);

	ResultInt PowerOffModem(int deviceId);

	ResultInt WriteModem(int deviceId);

	ResultInt ReadModem(int deviceId);

	ResultLong get_ModemDevice(int deviceId);

	ResultInt put_ReadSize(int deviceId, int var1);

	ResultInt get_ReadSize(int deviceId);

	ResultInt put_NeedResultFlag(int deviceId, boolean var1);

	ResultBoolean get_NeedResultFlag(int deviceId);

	ResultInt OpenPinPad(int deviceId);

	ResultInt ClosePinPad(int deviceId);

	ResultInt OpenModem(int deviceId);

	ResultInt CloseModem(int deviceId);

	ResultInt put_ModemConnectionType(int deviceId, int var1);

	ResultInt get_ModemConnectionType(int deviceId);

	ResultInt put_ModemAddress(int deviceId, String var1);

	ResultString get_ModemAddress(int deviceId);

	ResultInt put_ModemPort(int deviceId, int var1);

	ResultInt get_ModemPort(int deviceId);

	ResultInt GetModemStatus(int deviceId);

	ResultInt GetPinPadStatus(int deviceId);

	ResultInt get_WriteSize(int deviceId);

	ResultInt get_ModemStatus(int deviceId);

	ResultInt get_ModemSignal(int deviceId);

	ResultString get_ModemOperator(int deviceId);

	ResultString get_ModemError(int deviceId);

	ResultInt GetDeviceMetrics(int deviceId);

	ResultString get_DeviceDescription(int deviceId);

	ResultInt GetCurrentMode(int deviceId);

	ResultBoolean get_OutOfPaper(int deviceId);

	ResultBoolean get_PrinterConnectionFailed(int deviceId);

	ResultBoolean get_PrinterMechanismError(int deviceId);

	ResultBoolean get_PrinterCutMechanismError(int deviceId);

	ResultBoolean get_PrinterOverheatError(int deviceId);

	ResultInt GetCurrentStatus(int deviceId);

	ResultInt GetLastSummary(int deviceId);

	ResultInt get_AdvancedMode(int deviceId);

	ResultInt put_BottomMargin(int deviceId, int var1);

	ResultInt get_BottomMargin(int deviceId);

	ResultInt EKLZGetKPK(int deviceId);

	ResultInt get_EKLZKPK(int deviceId);

	ResultInt put_BarcodeVersion(int deviceId, int var1);

	ResultInt get_BarcodeVersion(int deviceId);

	ResultInt put_TaxPassword(int deviceId, String var1);

	ResultString get_TaxPassword(int deviceId);

	ResultInt put_Classifier(int deviceId, String var1);

	ResultString get_Classifier(int deviceId);

	ResultInt put_FiscalPropertyNumber(int deviceId, int var1);

	ResultInt get_FiscalPropertyNumber(int deviceId);

	ResultInt put_FiscalPropertyValue(int deviceId, String var1);

	ResultString get_FiscalPropertyValue(int deviceId);

	ResultInt put_FiscalPropertyType(int deviceId, int var1);

	ResultInt get_FiscalPropertyType(int deviceId);

	ResultInt put_FiscalPropertyPrint(int deviceId, boolean var1);

	ResultBoolean get_FiscalPropertyPrint(int deviceId);

	ResultInt WriteFiscalProperty(int deviceId);

	ResultInt ReadFiscalProperty(int deviceId);

	ResultBoolean get_HasNotSendedDocs(int deviceId);

	ResultInt RunFNCommand(int deviceId);

	ResultInt get_CounterDimension(int deviceId);

	ResultInt put_CounterDimension(int deviceId, int var1);

	ResultInt put_DiscountNumber(int deviceId, int i);

	ResultInt get_DiscountNumber(int deviceId) ;

	ResultInt DeleteLastBarcode(int deviceId) ;

	ResultDouble get_DiscountInSession(int deviceId);

	ResultDouble get_ChargeInSession(int deviceId) ;

	ResultInt get_NetworkError(int deviceId);

	ResultInt get_OFDError(int deviceId);

	ResultInt get_FNError(int deviceId);

	ResultInt get_TimeoutACK(int deviceId);

	ResultInt put_TimeoutACK(int deviceId, int i);

	ResultInt get_TimeoutENQ(int deviceId);

	ResultInt put_TimeoutENQ(int deviceId, int i);

	ResultInt AddBarcode(int deviceId);

	ResultInt GetBarcodeArrayStatus(int deviceId);

	ResultInt Correction(int deviceId);

	ResultInt ReturnCorrection(int deviceId);

	ResultInt BuyCorrection(int deviceId);

	ResultInt BuyReturnCorrection(int deviceId);

	ResultInt put_PrintCheck(int deviceId, boolean b);

	ResultBoolean get_PrintCheck(int deviceId);

	ResultString getCachedFnRegNum(int deviceId);

	ResultString getCachedKktNumber(int deviceId);

	ResultBoolean isCachedAutonomous(int deviceId);

	ResultBoolean isCachedService(int deviceId);

	ResultBoolean isCachedCrypt(int deviceId);

	ResultBoolean isCachedInternetPay(int deviceId);

	ResultString getCachedFnLifePhase(int deviceId);

	ResultString getCachedTaxSystems(int deviceId);

	ResultString getCachedDefaultTaxSystem(int deviceId);

	ResultString getCachedOrgInfoInn(int deviceId);

	ResultString getCachedOrgInfoAddress(int deviceId);

	ResultString getCachedOrgInfoName(int deviceId) ;

	ResultString getCachedOfdInfoInn(int deviceId);

	ResultInt put_DiscountSumm(int deviceId, double value);

	ResultInt put_TaxSumm(int deviceId, double value);

	ResultInt put_FeatureOfCalculation(int deviceId, int value);

	ResultInt put_MethodOfCalculation(int deviceId, int value);

	ResultInt get_FNState(int deviceId);

	ResultVoid refreshCachedValues(int deviceId);

	ResultBoolean hasNewSoftwareForFlashing(int deviceId);

	ResultVoid flashAllIfAvailable(int deviceId);

	ResultVoid printImage(int deviceId, String filename);
}
