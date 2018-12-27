package com.hong.common.utils;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * logback获取本地ip
 *
 */
public class IpConvertUtils extends ClassicConverter {

	public static String getLocalAddress() {
		String la = "127.0.0.1";
		try {
			InetAddress localAddr = InetAddress.getLocalHost();
			if (!localAddr.isLoopbackAddress() && !localAddr.isLinkLocalAddress() && localAddr.isSiteLocalAddress()) {
				return localAddr.getHostAddress();
			}
			Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
			NETWORK_LOOP:
			for (; n.hasMoreElements(); ) {
				NetworkInterface e = n.nextElement();
				Enumeration<InetAddress> a = e.getInetAddresses();
				for (; a.hasMoreElements(); ) {
					InetAddress addr = a.nextElement();
					if (!addr.isLoopbackAddress() && !addr.isLinkLocalAddress() && addr.isSiteLocalAddress()) {
						la = addr.getHostAddress();
						break NETWORK_LOOP;
					}
				}
			}
		} catch (Exception ignored) {

		}
		return la;
	}

	@Override
	public String convert(ILoggingEvent event) {
		return getLocalAddress();
	}
}
