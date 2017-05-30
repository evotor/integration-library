package ru.evotor.framework.core;

import android.os.Bundle;

import java.io.IOException;

/**
 * Created by a.kuznetsov on 11/04/2017.
 */

public interface IntegrationManagerFuture {

    Result getResult() throws IOException, IntegrationException;

    class Result {
        private final Type type;
        private final Error error;
        private final Bundle data;

        public Result(Bundle data) {
            this(Type.OK, null, data);
        }

        public Result(Error error) {
            this(Type.ERROR, error, null);
        }

        public Result(Type type, Error error, Bundle data) {
            this.type = type;
            this.error = error;
            this.data = data;
        }

        public Type getType() {
            return type;
        }

        public Bundle getData() {
            return data;
        }

        public Error getError() {
            return error;
        }

        public enum Type {
            OK,
            ERROR
        }
    }
}
