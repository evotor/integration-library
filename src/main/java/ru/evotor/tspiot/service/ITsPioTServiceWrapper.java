package ru.evotor.tspiot.service;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
import ru.evotor.tspiot.exceptions.base.TsPioTServiceException;
import ru.evotor.tspiot.model.ClientInfo;
import ru.evotor.tspiot.model.MarkingCode;
import ru.evotor.tspiot.result.model.CodesCheckResult;
import ru.evotor.tspiot.result.model.KktInfo;

public interface ITsPioTServiceWrapper {
    KktInfo getKktInfo() throws TsPioTServiceException;

    CodesCheckResult getMarkedProductsInfo(@NonNull List<MarkingCode> codes, @Nullable String userUuid) throws TsPioTServiceException;

    CodesCheckResult getMarkedProductsInfo(@NonNull List<MarkingCode> codes, @NonNull ClientInfo clientInfo) throws TsPioTServiceException;
}
