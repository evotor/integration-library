package ru.evotor.devices.commons.result;

import ru.evotor.devices.commons.exception.DeviceServiceException;

public abstract class AbstractResult {

    public ErrorDescription packException(DeviceServiceException exc) {
        return ErrorDescriptionPacker.packException(exc);
    }

    protected void unpackException() throws DeviceServiceException {
        ErrorDescription errorDescription = this.getErrorDescription();
        DeviceServiceException exception = ErrorDescriptionPacker.unpackException(errorDescription);
        if (exception != null) {
            throw exception;
        }
    }

    abstract ErrorDescription getErrorDescription();

}