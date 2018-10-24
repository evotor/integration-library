package ru.evotor.framework.core.action.event.receipt.position_edited;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.framework.receipt.Position;

/**
 * @deprecated Используйте {@link ru.evotor.framework.receipt.position.event.PositionAddedEvent}
 */
@Deprecated
public class PositionAddedEvent extends PositionEvent {
    public static final String BROADCAST_ACTION_SELL_RECEIPT = "evotor.intent.action.receipt.sell.POSITION_ADDED";
    public static final String BROADCAST_ACTION_PAYBACK_RECEIPT = "evotor.intent.action.receipt.payback.POSITION_ADDED";
    public static final String BROADCAST_ACTION_BUY_RECEIPT = "evotor.intent.action.receipt.buy.POSITION_ADDED";
    public static final String BROADCAST_ACTION_BUYBACK_RECEIPT = "evotor.intent.action.receipt.buyback.POSITION_ADDED";

    @Nullable
    public static PositionAddedEvent create(@Nullable Bundle extras) {
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
        return new PositionAddedEvent(receiptUuid, position);
    }

    public PositionAddedEvent(@NonNull String receiptUuid, @NonNull Position position) {
        super(receiptUuid, position);
    }
}
