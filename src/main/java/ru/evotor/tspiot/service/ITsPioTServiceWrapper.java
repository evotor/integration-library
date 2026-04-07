package ru.evotor.tspiot.service;

import java.util.List;

import ru.evotor.tspiot.exceptions.TsPioTErrorHolderException;
import ru.evotor.tspiot.exceptions.base.TsPioTServiceException;
import ru.evotor.tspiot.model.MarkingCode;
import ru.evotor.tspiot.result.CodesCheckResult;
import ru.evotor.tspiot.result.KktInfo;

public interface ITsPioTServiceWrapper {
    KktInfo getKktInfo() throws TsPioTServiceException, TsPioTErrorHolderException;

    CodesCheckResult getMarkedProductsInfo(List<MarkingCode> codes) throws TsPioTServiceException, TsPioTErrorHolderException;
}
