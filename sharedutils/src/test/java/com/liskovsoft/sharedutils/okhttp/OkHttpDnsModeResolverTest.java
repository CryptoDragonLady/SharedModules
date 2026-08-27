package com.liskovsoft.sharedutils.okhttp;

import com.liskovsoft.sharedutils.okhttp.OkHttpDNSSelector.IPvMode;
import com.liskovsoft.sharedutils.prefs.GlobalPreferences;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OkHttpDnsModeResolverTest {
    @Test
    public void existingAddressModesKeepTheirDnsBehavior() {
        assertEquals(
                IPvMode.IPV4_FIRST,
                OkHttpDnsModeResolver.resolve(GlobalPreferences.DNS_TYPE_IPV4));
        assertEquals(
                IPvMode.IPV4_ONLY,
                OkHttpDnsModeResolver.resolve(GlobalPreferences.DNS_TYPE_IPV4_ONLY));
        assertEquals(
                IPvMode.SYSTEM,
                OkHttpDnsModeResolver.resolve(GlobalPreferences.DNS_TYPE_SYSTEM));
    }

    @Test
    public void ipv6OnlyPreferenceUsesIpv6OnlyDns() {
        assertEquals(
                IPvMode.IPV6_ONLY,
                OkHttpDnsModeResolver.resolve(GlobalPreferences.DNS_TYPE_IPV6_ONLY));
    }
}
