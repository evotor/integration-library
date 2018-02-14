package ru.evotor.framework.core;

import android.os.Bundle;

public interface IntegrationManagerFuture {

    Result getResult() throws IntegrationException;

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
