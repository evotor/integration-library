package ru.evotor.framework.core.action.event.receipt.position_edited;

import android.os.Bundle;

import ru.evotor.framework.receipt.Position;

/**
 * Created by a.kuznetsov on 18/05/2017.
 */

public class PositionEditedEvent extends PositionEvent {
    public static final String BROADCAST_ACTION_SELL_RECEIPT = "evotor.intent.action.receipt.sell.POSITION_EDITED";
    public static final String BROADCAST_ACTION_PAYBACK_RECEIPT = "evotor.intent.action.receipt.payback.POSITION_EDITED";

    public PositionEditedEvent(Bundle extras) {
        super(extras);
    }

    public PositionEditedEvent(String receiptUuid, Position position) {
        super(receiptUuid, position);
    }
}
