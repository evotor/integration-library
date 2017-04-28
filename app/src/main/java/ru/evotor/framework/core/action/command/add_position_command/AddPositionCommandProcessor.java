package ru.evotor.framework.core.action.command.add_position_command;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;

import ru.evotor.framework.core.action.processor.ActionProcessor;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */

public abstract class AddPositionCommandProcessor extends ActionProcessor {

    public AddPositionCommandProcessor() {
        super(AddPositionCommand.NAME);
    }

    @Override
    public void process(Bundle bundle, Callback callback) {
        call(AddPositionCommand.create(bundle), new AddProductCommandProcessorCallback(callback));
    }

    public abstract void call(AddPositionCommand command, AddProductCommandProcessorCallback callback);

    public class AddProductCommandProcessorCallback {

        private final Callback callback;

        AddProductCommandProcessorCallback(Callback callback) {
            this.callback = callback;
        }

        public void startActivity(Intent intent) throws RemoteException {
            callback.startActivity(intent);
        }

        public void onResult(AddPositionCommandResult result) throws RemoteException {
            callback.onResult(result.toBundle());
        }

        public void finish() throws RemoteException {
            callback.skip();
        }
    }
}

