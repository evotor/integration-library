package ru.evotor.framework.core.action.command.add_product_command;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;

import ru.evotor.framework.core.action.processor.ActionProcessor;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */

public abstract class AddProductCommandProcessor extends ActionProcessor {
    public AddProductCommandProcessor() {
        super(AddProductCommand.NAME);
    }

    @Override
    public void process(Bundle bundle, Callback callback) {
        call(AddProductCommand.create(bundle), new AddProductCommandProcessorCallback(callback));
    }

    public abstract void call(AddProductCommand command, AddProductCommandProcessorCallback callback);

    public class AddProductCommandProcessorCallback {

        private final Callback callback;

        AddProductCommandProcessorCallback(Callback callback) {
            this.callback = callback;
        }

        public void startActivity(Intent intent) throws RemoteException {
            callback.startActivity(intent);
        }

        public void finish() throws RemoteException {
            callback.skip();
        }
    }
}

