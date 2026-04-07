package ru.evotor.tspiot;

import ru.evotor.tspiot.model.MarkingCode;
import ru.evotor.tspiot.result.TsPioTResult;

interface ITsPioTService {
    TsPioTResult getKktInfo();

    TsPioTResult getMarkedProductsInfo(in List<MarkingCode> codes);
}