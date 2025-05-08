package org.eu.tz95.tools.connect;

import org.eu.tz95.tools.connect.base.Address;
import org.eu.tz95.tools.util.ConnectionUtil;

import java.net.*;
import java.net.http.HttpResponse;
import java.util.Enumeration;

public class Ipv6Address extends Address {

    private static Address ipv6Instance;

    public static synchronized Address getInstance() {
        if (ipv6Instance == null){
            ipv6Instance = new Ipv6Address();
        }
        return ipv6Instance;
    }

    @Override
    public void updateAddress() {
        // getLocalIPV6Address();
        if (ConnectionUtil.testConnection()) {
            getOnlineAddress();
        }
    }

    public void getLocalIPV6Address(){
        String localIpv6 = null;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                if (ni.isUp() && !ni.isLoopback()) {
                    Enumeration<InetAddress> addresses = ni.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress addr = addresses.nextElement();
                        if (addr instanceof Inet6Address && !addr.isLoopbackAddress() && !addr.toString().contains("fe80")) {
                            localIpv6 = addr.getHostAddress();
                            break;
                        }
                    }
                }
                if (localIpv6 != null) {
                    break;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        this.address = localIpv6;
    }

    public void getOnlineAddress(){
        HttpResponse<String> resp = ConnectionUtil.sendRequest("https://api6.ipify.org", new String[]{"Accept", "application/text"}, null);

        if (resp.statusCode() == 200) {
            this.address = resp.body();
        } else {
            System.out.println("Failed to get online address");
        }

    }

    private Ipv6Address() {
        updateAddress();
    }

}
