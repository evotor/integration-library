/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.evotor.framework.core;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import ru.evotor.IBundlable;

/**
 * Object used to communicate responses back to the IntegrationManager
 */
public class IntegrationResponse implements Parcelable {
    private static final String TAG = "IntegrationResponse";

    private IIntegrationManagerResponse mIntegrationManagerResponse;

    /**
     * @hide
     */
    public IntegrationResponse(IIntegrationManagerResponse response) {
        mIntegrationManagerResponse = response;
    }

    public IntegrationResponse(Parcel parcel) {
        mIntegrationManagerResponse =
                IIntegrationManagerResponse.Stub.asInterface(parcel.readStrongBinder());
    }

    public void onResult(IBundlable data) {
        onResult(data == null ? null : data.toBundle());
    }

    public void onResult(Bundle result) {
        try {
            mIntegrationManagerResponse.onResult(result);
        } catch (RemoteException e) {
            // this should never happen
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void onRequestContinued() {
        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            Log.v(TAG, "IntegrationResponse.onRequestContinued");
        }
        //TODO
        //try {
        //    mIntegrationManagerResponse.onRequestContinued();
        //} catch (RemoteException e) {
        //    // this should never happen
        //}
    }

    public void onError(int errorCode, @NonNull String errorMessage) {
        onError(errorCode, errorMessage, (IBundlable) null);
    }

    public void onError(int errorCode, @NonNull String errorMessage, @Nullable IBundlable data) {
        onError(errorCode, errorMessage, data == null ? null : data.toBundle());
    }

    public void onError(int errorCode, @NonNull String errorMessage, @Nullable Bundle data) {
        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            Log.v(TAG, "IntegrationResponse.onError: " + errorCode + ", " + errorMessage);
        }
        try {
            mIntegrationManagerResponse.onError(errorCode, errorMessage, data);
        } catch (RemoteException e) {
            // this should never happen
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStrongBinder(mIntegrationManagerResponse.asBinder());
    }

    public static final Creator<IntegrationResponse> CREATOR =
            new Creator<IntegrationResponse>() {
                public IntegrationResponse createFromParcel(Parcel source) {
                    return new IntegrationResponse(source);
                }

                public IntegrationResponse[] newArray(int size) {
                    return new IntegrationResponse[size];
                }
            };
}
