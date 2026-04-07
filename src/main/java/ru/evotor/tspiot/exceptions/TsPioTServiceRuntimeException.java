package ru.evotor.tspiot.exceptions;

import ru.evotor.tspiot.exceptions.base.TsPioTServiceException;

public class TsPioTServiceRuntimeException extends TsPioTServiceException {

    public TsPioTServiceRuntimeException(Exception ex) { super(ex); }
}
