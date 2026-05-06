package ru.evotor.tspiot.service;

import java.util.List;
import ru.evotor.tspiot.exceptions.base.TsPioTServiceException;
import ru.evotor.tspiot.model.MarkingCode;
import ru.evotor.tspiot.result.model.CodesCheckResult;
import ru.evotor.tspiot.result.model.KktInfo;

public interface ITsPioTServiceWrapper {
    KktInfo getKktInfo() throws TsPioTServiceException;

    CodesCheckResult getMarkedProductsInfo(List<MarkingCode> codes, String userUuid) throws TsPioTServiceException;
}
