package ru.evotor.tspiot.exceptions;

import ru.evotor.tspiot.exceptions.base.TsPioTServiceException;

public class ServiceNotConnectedException extends TsPioTServiceException {

    public ServiceNotConnectedException(Exception ex) { super(ex); }
}
