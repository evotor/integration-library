package ru.evotor.framework.core.action.command.processor;

import android.os.Bundle;

import ru.evotor.framework.core.action.command.AddProductCommand;
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
        call(AddProductCommand.create(bundle), callback);
    }

    public abstract void call(AddProductCommand event, Callback callback);
}

