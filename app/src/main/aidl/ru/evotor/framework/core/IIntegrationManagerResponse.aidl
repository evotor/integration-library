package ru.evotor.framework.core;
import android.os.Bundle;

oneway interface IIntegrationManagerResponse {
    void onResult(in Bundle value);
    void onError(int errorCode, String errorMessage, in Bundle data);
}