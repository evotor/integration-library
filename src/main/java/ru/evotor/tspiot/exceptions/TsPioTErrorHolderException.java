package ru.evotor.tspiot.exceptions;

import ru.evotor.tspiot.exceptions.base.TsPioTServiceException;
import ru.evotor.tspiot.result.Errors;

/** Исключение обёртка для передачи ошибок от драйвера ТС ПИоТ */
public class TsPioTErrorHolderException extends TsPioTServiceException {

    public final Errors code;

    public final String message;

    public TsPioTErrorHolderException(Errors code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
}
