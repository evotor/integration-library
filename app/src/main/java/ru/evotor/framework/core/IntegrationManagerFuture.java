package ru.evotor.framework.core;

import android.os.Bundle;

import java.io.IOException;

/**
 * Created by a.kuznetsov on 11/04/2017.
 */

public interface IntegrationManagerFuture {

    Result getResult() throws IOException, IntegrationException;

    class Result {
        private Type type;
        private Bundle data;

        public Result(Type type, Bundle data) {
            this.type = type;
            this.data = data;
        }

        public Type getType() {
            return type;
        }

        public Bundle getData() {
            return data;
        }

        public enum Type {
            INTERMEDIATE,
            FINISH
        }
    }
}
