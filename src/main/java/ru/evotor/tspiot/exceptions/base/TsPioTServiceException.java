package ru.evotor.tspiot.exceptions.base;

/** Базовый класс для исключений, которые могу возникнуть на стороне клиента ТС ПИоТ*/
public class TsPioTServiceException extends Exception {

    public TsPioTServiceException() {
        super();
    }

    public TsPioTServiceException(String message) {
        super(message);
    }

    public TsPioTServiceException(Exception e) {
        super(e);
    }
}
