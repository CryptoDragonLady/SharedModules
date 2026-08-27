package com.liskovsoft.sharedutils.okhttp

import com.liskovsoft.sharedutils.okhttp.OkHttpDNSSelector.IPvMode
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.InetAddress

class OkHttpDNSSelectorTest {
    private val ipv4First = InetAddress.getByAddress(byteArrayOf(1, 1, 1, 1))
    private val ipv4Second = InetAddress.getByAddress(byteArrayOf(8, 8, 8, 8))
    private val ipv6 = InetAddress.getByAddress(ByteArray(16).apply { this[15] = 1 })

    @Test
    fun ipv4FirstMovesIpv4AddressesAheadWithoutDroppingIpv6() {
        val addresses = listOf(ipv6, ipv4First, ipv4Second)

        assertEquals(
            listOf(ipv4First, ipv4Second, ipv6),
            OkHttpDNSSelector.selectAddresses(addresses, IPvMode.IPV4_FIRST)
        )
    }

    @Test
    fun ipv4OnlyDropsEveryIpv6Address() {
        val addresses = listOf(ipv6, ipv4First, ipv4Second)

        assertEquals(
            listOf(ipv4First, ipv4Second),
            OkHttpDNSSelector.selectAddresses(addresses, IPvMode.IPV4_ONLY)
        )
    }

    @Test
    fun systemPreservesResolverOrder() {
        val addresses = listOf(ipv6, ipv4First, ipv4Second)

        assertEquals(addresses, OkHttpDNSSelector.selectAddresses(addresses, IPvMode.SYSTEM))
    }
}
