package ru.evotor.integrations.result;

import ru.evotor.integrations.result.IntegrationResult;
import ru.evotor.integrations.result.IntegrationError;

interface IntegrationCallback {
    void onSuccess(in IntegrationResult result);
    void onFailure(in IntegrationError error);
}