package ru.evotor.tspiot.exceptions;

import ru.evotor.tspiot.exceptions.base.TsPioTServiceException;
import ru.evotor.tspiot.result.model.base.ErrorDescription;
import ru.evotor.tspiot.result.model.errors.TsPioTErrorsDescription;

/** Исключение обёртка для передачи ошибок от драйвера ТС ПИоТ */
public class TsPioTErrorHolderException extends TsPioTServiceException {

    public final TsPioTErrorsDescription<? extends ErrorDescription> errorDescription;

    public TsPioTErrorHolderException(TsPioTErrorsDescription<? extends ErrorDescription> errorDescription) {
        super();
        this.errorDescription = errorDescription;
    }
}
