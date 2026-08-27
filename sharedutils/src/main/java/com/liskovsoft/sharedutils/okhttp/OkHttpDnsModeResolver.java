package com.liskovsoft.sharedutils.okhttp;

import com.liskovsoft.sharedutils.okhttp.OkHttpDNSSelector.IPvMode;
import com.liskovsoft.sharedutils.prefs.GlobalPreferences;

/** Maps the persisted DNS preference to the address-family mode used by OkHttp. */
public final class OkHttpDnsModeResolver {
    private OkHttpDnsModeResolver() {
    }

    public static IPvMode resolve(int dnsType) {
        switch (dnsType) {
            case GlobalPreferences.DNS_TYPE_IPV4:
                return IPvMode.IPV4_FIRST;
            case GlobalPreferences.DNS_TYPE_IPV4_ONLY:
                return IPvMode.IPV4_ONLY;
            case GlobalPreferences.DNS_TYPE_IPV6_ONLY:
                return IPvMode.IPV6_ONLY;
            default:
                return IPvMode.SYSTEM;
        }
    }
}
