package ru.evotor.framework.core.action.event.receipt.position_edited;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.IBundlable;
import ru.evotor.framework.core.action.datamapper.PositionMapper;
import ru.evotor.framework.receipt.Position;

/**
 * @deprecated Используйте {@link ru.evotor.framework.receipt.position.event.PositionEvent}
 */
@Deprecated
public abstract class PositionEvent implements IBundlable {
    private static final String KEY_RECEIPT_UUID = "receiptUuid";
    private static final String KEY_POSITION = "position";

    @NonNull
    private final String receiptUuid;
    @NonNull
    private final Position position;

    @Nullable
    static String getReceiptUuid(@NonNull Bundle bundle) {
        return bundle.getString(KEY_RECEIPT_UUID, null);
    }

    @Nullable
    static Position getPosition(@NonNull Bundle bundle) {
        return PositionMapper.from(bundle.getBundle(KEY_POSITION));
    }

    PositionEvent(
            @NonNull String receiptUuid,
            @NonNull Position position
    ) {
        this.receiptUuid = receiptUuid;
        this.position = position;
    }

    @NonNull
    @Override
    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putString(KEY_RECEIPT_UUID, receiptUuid);
        result.putBundle(KEY_POSITION, PositionMapper.toBundle(position));
        return result;
    }

    @NonNull
    public String getReceiptUuid() {
        return receiptUuid;
    }

    @NonNull
    public Position getPosition() {
        return position;
    }
}
