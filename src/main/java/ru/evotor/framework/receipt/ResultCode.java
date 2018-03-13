/*
 * Copyright (c) 2014 ATOL (http://www.atol.com), all rights reserved
 */
package ru.evotor.framework.receipt;

public enum ResultCode {
    /**
     * Everything is OK
     */
    PE_RES_OK,
    /**
     * Terminated by user or by any reasons
     */
    PE_RES_TERMINATED,
    /**
     * Driver not initialized
     */
    PE_ERR_DRV_NOT_INITIALIZED,
    /**
     * Driver error
     */
    PE_ERR_DRV_ERROR,
    /**
     * Transfer was successful, but error code was returned by the device
     */
    PE_ERR_ECR_ERROR,
    /**
     * Transfer was successful, but error code was returned by the device.
     * Critical operation
     */
    PE_ERR_ECR_ERROR_CHECK_CRITICAL_OPERATION,
    /**
     * Date/time synchronization required
     */
    PE_ERR_DATETIME_SYNC_REQUIRED,
    /**
     * Date/time synchronization not allowed
     */
    PE_ERR_DATETIME_SYNC_NOT_ALLOWED,
    /**
     * Device service exception
     */
    PE_ERR_REMOTE_EXCEPTION,

    /**
     * Касса недоступна/отсутствует
     */
    PE_ERR_NO_KKM_FOUND
}