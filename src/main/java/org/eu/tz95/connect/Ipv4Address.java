package org.eu.tz95.connect;

import org.eu.tz95.connect.base.Address;

import java.net.*;
import java.util.Enumeration;

public class Ipv4Address extends Address {

    private static Address ipv4Instance;

    public static synchronized Address getInstance() {
        if (ipv4Instance == null){
            ipv4Instance = new Ipv4Address();
        }
        return ipv4Instance;
    }

    @Override
    public void updateAddress() {
        String localipv4 = null;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                if (ni.isUp() && !ni.isLoopback()) {
                    Enumeration<InetAddress> addresses = ni.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress addr = addresses.nextElement();
                        if (addr instanceof Inet4Address && !addr.isLoopbackAddress()) {
                            localipv4 = addr.getHostAddress();
                            break;
                        }
                    }
                }
                if (localipv4 != null) {
                    break;
                }
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        this.address = localipv4;
    }


    private Ipv4Address() {
        updateAddress();
    }

}
