package ru.evotor.tspiot.service;

public interface ITsPioTConnectionWrapper {
    void onTsPioTServiceConnected(ITsPioTServiceWrapper tsPioTService);

    void onTsPioTServiceDisconnected();
}
