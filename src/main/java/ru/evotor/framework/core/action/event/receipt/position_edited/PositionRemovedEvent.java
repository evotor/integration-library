package ru.evotor.framework.core.action.event.receipt.position_edited;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.framework.receipt.Position;

/**
 * Created by a.kuznetsov on 18/05/2017.
 */

public class PositionRemovedEvent extends PositionEvent {
    public static final String BROADCAST_ACTION_SELL_RECEIPT = "evotor.intent.action.receipt.sell.POSITION_REMOVED";
    public static final String BROADCAST_ACTION_PAYBACK_RECEIPT = "evotor.intent.action.receipt.payback.POSITION_REMOVED";

    @Nullable
    public static PositionRemovedEvent create(@Nullable Bundle extras) {
        if (extras == null) {
            return null;
        }
        String receiptUuid = getReceiptUuid(extras);
        if (receiptUuid == null) {
            return null;
        }
        Position position = getPosition(extras);
        if (position == null) {
            return null;
        }
        return new PositionRemovedEvent(receiptUuid, position);
    }

    public PositionRemovedEvent(@NonNull String receiptUuid, @NonNull Position position) {
        super(receiptUuid, position);
    }
}
