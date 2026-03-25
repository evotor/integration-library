package ru.evotor.integrations;

import ru.evotor.integrations.result.IntegrationCallback;
import ru.evotor.integrations.model.ClientInfo;

interface ITsPioTService {

    void getKktInfo(in IntegrationCallback callback);

    void checkMarks(in List<String> codes, in ClientInfo clientInfo, in IntegrationCallback callback);
}