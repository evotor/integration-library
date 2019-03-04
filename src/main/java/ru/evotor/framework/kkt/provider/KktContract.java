package ru.evotor.framework.kkt.provider;

import android.net.Uri;

import ru.evotor.framework.core.DoNotUseThis;

@DoNotUseThis()
public final class KktContract {
    public static String AUTHORITY = "ru.evotor.evotorpos.kkt";
    public static Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public interface Columns {
        String SUPPORTED_FFD_VERSION = "SUPPORTED_FFD_VERSION";
        String REGISTERED_AGENT_TYPES = "REGISTERED_AGENT_TYPES";
        String REGISTERED_SUBAGENT_TYPES = "REGISTERED_SUBAGENT_TYPES";
        String IS_VAT_RATE_20_AVAILABLE = "IS_VAT_RATE_20_AVAILABLE";
    }
}
