package ru.evotor.framework.core.action.event.receipt.position_edited;

import android.os.Bundle;

import ru.evotor.framework.receipt.Position;

/**
 * Created by a.kuznetsov on 18/05/2017.
 */

public class PositionRemovedEvent extends PositionEvent {
    public static final String BROADCAST_ACTION_SELL_RECEIPT = "evotor.intent.action.receipt.sell.POSITION_REMOVED";
    public static final String BROADCAST_ACTION_PAYBACK_RECEIPT = "evotor.intent.action.receipt.payback.POSITION_REMOVED";

    public PositionRemovedEvent(Bundle extras) {
        super(extras);
    }

    public PositionRemovedEvent(String receiptUuid, Position position) {
        super(receiptUuid, position);
    }
}
