package ru.evotor.framework.core.action.event.receipt.print_group;

import android.os.Bundle;
import android.support.annotation.NonNull;

import ru.evotor.IBundlable;

public abstract class PrintGroupEvent implements IBundlable {
    PrintGroupEvent() { }

    @NonNull
    public Bundle toBundle() {
        Bundle result = new Bundle();
        return result;
    }

}
