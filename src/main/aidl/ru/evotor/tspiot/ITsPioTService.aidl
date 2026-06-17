package ru.evotor.tspiot;

import ru.evotor.tspiot.model.MarkingCode;
import ru.evotor.tspiot.model.ClientInfo;
import ru.evotor.tspiot.result.TsPioTResult;

interface ITsPioTService {
    TsPioTResult getKktInfo();

    TsPioTResult getMarkedProductsInfo(in List<MarkingCode> codes, in String userUuid);

    TsPioTResult getMarkedProductsInfoInternal(in List<MarkingCode> codes, in ClientInfo clientInfo);
}