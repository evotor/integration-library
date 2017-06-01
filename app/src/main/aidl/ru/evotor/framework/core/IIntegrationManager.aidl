package ru.evotor.framework.core;


import ru.evotor.framework.core.IIntegrationManagerResponse;
import android.os.Bundle;

interface IIntegrationManager {
    void call(in IIntegrationManagerResponse response, String action, in Bundle bundle);
}
