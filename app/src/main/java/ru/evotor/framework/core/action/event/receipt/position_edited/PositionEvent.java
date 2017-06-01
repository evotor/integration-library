package ru.evotor.framework.core.action.event.receipt.position_edited;

import android.os.Bundle;

import ru.evotor.framework.core.action.datamapper.PositionMapper;
import ru.evotor.framework.receipt.Position;

/**
 * Created by a.kuznetsov on 18/05/2017.
 */

public abstract class PositionEvent {
    private static final String KEY_RECEIPT_UUID = "receiptUuid";
    private static final String KEY_POSITION = "position";

    private final String receiptUuid;
    private final Position position;

    public PositionEvent(Bundle extras) {
        this(
                extras.getString(KEY_RECEIPT_UUID, null),
                PositionMapper.from(extras.getBundle(KEY_POSITION))
        );
    }

    public PositionEvent(
            String receiptUuid,
            Position position
    ) {
        this.receiptUuid = receiptUuid;
        this.position = position;
    }

    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putString(KEY_RECEIPT_UUID, receiptUuid);
        result.putBundle(KEY_POSITION, PositionMapper.toBundle(position));
        return result;
    }

    public String getReceiptUuid() {
        return receiptUuid;
    }

    public Position getPosition() {
        return position;
    }
}
