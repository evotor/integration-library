package ru.evotor.framework.core.action.event.receipt.before_positions_edited;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import ru.evotor.IBundlable;
import ru.evotor.framework.Utils;
import ru.evotor.framework.core.action.datamapper.ChangesMapper;
import ru.evotor.framework.core.action.event.receipt.changes.IChange;
import ru.evotor.framework.core.action.event.receipt.changes.position.IPositionChange;

/**
 * Событие, которое сообщает об изменении чека пользователем или другим приложением, и предоставляет возможность приложению внести свои изменения.
 * <p>
 * Константы события указывают тип чека, позиции которого будет изменять приложение.
 * <p>
 * Чтобы приложение получало событие, значение константы необходимо указать в элементе action intent-фильтра соотвествующей службы.
 */
public class BeforePositionsEditedEvent implements IBundlable {
    private static final String TAG = "PositionsEditedEvent";

    /**
     * Будут изменены позиции чека продажи.
     * <p>
     * Значение константы: <code>evo.v2.receipt.sell.beforePositionsEdited</code>.
     */
    public static final String NAME_SELL_RECEIPT = "evo.v2.receipt.sell.beforePositionsEdited";
    /**
     * Будут изменены позиции чека возврата.
     * <p>
     * Значение константы: <code>evo.v2.receipt.payback.beforePositionsEdited</code>.
     */
    public static final String NAME_PAYBACK_RECEIPT = "evo.v2.receipt.payback.beforePositionsEdited";
    /**
     * Будут изменены позиции чека покупки.
     * <p>
     * Значение константы: <code>evo.v2.receipt.buy.beforePositionsEdited</code>.
     */
    public static final String NAME_BUY_RECEIPT = "evo.v2.receipt.buy.beforePositionsEdited";
    /**
     * Будут изменены позиции чека возврата покупки.
     * <p>
     * Значение константы: <code>evo.v2.receipt.buyback.beforePositionsEdited</code>.
     */
    public static final String NAME_BUYBACK_RECEIPT = "evo.v2.receipt.buyback.beforePositionsEdited";

    private static final String KEY_RECEIPT_UUID = "receiptUuid";
    private static final String KEY_CHANGES = "changes";

    @NonNull
    private final String receiptUuid;
    @NonNull
    private final List<IPositionChange> changes;

    public BeforePositionsEditedEvent(
            @NonNull String receiptUuid,
            @NonNull List<IPositionChange> changes
    ) {
        this.receiptUuid = receiptUuid;
        this.changes = changes;
    }

    @Nullable
    public static BeforePositionsEditedEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }

        String receiptUuid = bundle.getString(KEY_RECEIPT_UUID, null);

        return new BeforePositionsEditedEvent(
                receiptUuid,
                Utils.filterByClass(
                        ChangesMapper.INSTANCE.create(bundle.getParcelableArray(KEY_CHANGES)),
                        IPositionChange.class
                )
        );
    }

    @Override
    @NonNull
    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putString(KEY_RECEIPT_UUID, receiptUuid);
        Parcelable[] changesParcelable = new Parcelable[changes.size()];
        for (int i = 0; i < changesParcelable.length; i++) {
            IChange change = changes.get(i);
            changesParcelable[i] = ChangesMapper.INSTANCE.toBundle(change);
        }
        result.putParcelableArray(KEY_CHANGES, changesParcelable);
        return result;
    }

    /**
     * Возвращает идентификатор чека, позиции которого будут изменены.
     * @return receiptUuid — идентификатор чека.
     */
    @NonNull
    public String getReceiptUuid() {
        return receiptUuid;
    }

    /**
     * Возвращает список сделанных в чеке изменений.
     * @return changes — список изменений.
     * @see IChange
     */
    @NonNull
    public List<IPositionChange> getChanges() {
        return changes;
    }
}
