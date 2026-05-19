package ru.evotor.tspiot.exceptions;

import ru.evotor.tspiot.exceptions.base.TsPioTServiceException;
import ru.evotor.tspiot.result.model.base.ErrorDescription;
import ru.evotor.tspiot.result.model.errors.TsPioTErrorsDescriptionWrapper;

/** Исключение обёртка для передачи ошибок от драйвера ТС ПИоТ */
public class TsPioTErrorHolderException extends TsPioTServiceException {

    public final TsPioTErrorsDescriptionWrapper<? extends ErrorDescription> errorDescription;

    public TsPioTErrorHolderException(TsPioTErrorsDescriptionWrapper<? extends ErrorDescription> errorDescription) {
        super();
        this.errorDescription = errorDescription;
    }
}
