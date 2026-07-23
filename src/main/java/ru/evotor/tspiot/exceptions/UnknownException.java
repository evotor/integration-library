package ru.evotor.tspiot.exceptions;

import ru.evotor.tspiot.exceptions.base.TsPioTServiceException;

public class UnknownException extends TsPioTServiceException {

    public UnknownException(String message) { super(message); }
}
