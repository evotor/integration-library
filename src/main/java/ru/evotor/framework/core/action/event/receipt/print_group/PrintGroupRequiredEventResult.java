package ru.evotor.framework.core.action.event.receipt.print_group;

import android.os.Bundle;
import android.os.Parcelable;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ru.evotor.IBundlable;
import ru.evotor.framework.Utils;
import ru.evotor.framework.core.action.datamapper.ChangesMapper;
import ru.evotor.framework.core.action.event.receipt.changes.IChange;
import ru.evotor.framework.core.action.event.receipt.changes.position.SetPrintGroup;
import ru.evotor.framework.core.action.event.receipt.changes.receipt.SetExtra;

/**
 * Результат обработки события {@link PrintGroupRequiredEvent}.
 */
public class PrintGroupRequiredEventResult implements IBundlable {

    private static final String KEY_RECEIPT_EXTRA = "extra";
    private static final String KEY_SET_PRINT_GROUPS = "setPrintGroups";

    @Nullable
    public static PrintGroupRequiredEventResult create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return new PrintGroupRequiredEventResult(
                SetExtra.from(bundle.getBundle(KEY_RECEIPT_EXTRA)),
                Utils.filterByClass(
                        ChangesMapper.INSTANCE.create(bundle.getParcelableArray(KEY_SET_PRINT_GROUPS)),
                        SetPrintGroup.class
                )
        );
    }

    @Nullable
    private final SetExtra extra;
    @NonNull
    private final List<SetPrintGroup> setPrintGroups;

    public PrintGroupRequiredEventResult(
            @Nullable SetExtra extra,
            @NonNull List<SetPrintGroup> setPrintGroups
    ) {
        this.extra = extra;
        this.setPrintGroups = setPrintGroups;
    }

    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putBundle(KEY_RECEIPT_EXTRA, extra == null ? null : extra.toBundle());
        Parcelable[] setPrintGroupsParcelableArray = new Parcelable[this.setPrintGroups.size()];
        for (int i = 0; i < setPrintGroupsParcelableArray.length; i++) {
            IChange change = this.setPrintGroups.get(i);
            setPrintGroupsParcelableArray[i] = ChangesMapper.INSTANCE.toBundle(change);
        }
        bundle.putParcelableArray(KEY_SET_PRINT_GROUPS, setPrintGroupsParcelableArray);
        return bundle;
    }

    /**
     * Возвращает дополнителье поля чека, которые были указаны при создании результата.
     * @return список объектов {@link SetExtra}.
     */
    @Nullable
    public SetExtra getExtra() {
        return extra;
    }

    /**
     * Возвращает список печатных групп, указанных при создании результата.
     * @return список объектов {@link SetPrintGroup}.
     */
    @NonNull
    public List<SetPrintGroup> getSetPrintGroups() {
        return setPrintGroups;
    }
}