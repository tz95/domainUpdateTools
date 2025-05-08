package org.eu.tz95.tools.connect;

import org.eu.tz95.tools.connect.base.Address;
import org.eu.tz95.tools.util.ConnectionUtil;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Enumeration;
import java.util.HashMap;

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
        /*String localipv4 = null;
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
                            InetAddress localHost = InetAddress.getLocalHost();
                            localHost.isSiteLocalAddress();
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
        this.address = localipv4;*/

        // getLocalIPV4Address();
        if (ConnectionUtil.testConnection()){
            getOnlineAddress();
        }else {
            System.out.println("Failed to connect to the internet");
        }
    }


    public void getLocalIPV4Address() {
        InetAddress localHost = null;
        HashMap<Integer, InetAddress> addressHashMap = new HashMap<>();
        int i = 1;
        try{
            for (Enumeration nis = NetworkInterface.getNetworkInterfaces();nis.hasMoreElements();) {
                NetworkInterface ni = (NetworkInterface)nis.nextElement();
                for (Enumeration addresses = ni.getInetAddresses();addresses.hasMoreElements();) {
                    InetAddress addr = (InetAddress)addresses.nextElement();
                    if (addr instanceof Inet4Address && !addr.isLoopbackAddress()) {
                        if (addr.isSiteLocalAddress()) {
                            addressHashMap.put(i++,addr);
                            System.out.println("IPv4 Address: " + addr.getHostAddress());
                            break;
                        }else if (localHost == null) {
                            localHost = addr;
                        }
                    }
                }
            }
            if (localHost != null){
                this.address = localHost.getHostAddress();
                return;
            }
            InetAddress localHost1 = InetAddress.getLocalHost();
            if (localHost1 instanceof Inet4Address) {
                this.address = localHost1.getHostAddress();
            }else{
                throw new UnknownHostException("No IPv4 address found");
            }
        }catch (Exception e){
            // new UnknownHostException("Failed to determine local host address: " + e).printStackTrace();
            e.printStackTrace();
        }
    }

    public void getOnlineAddress(){
        /*HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("https://checkip.amazonaws.com"))
                .header("Accept", "application/text")
                .timeout(Duration.ofSeconds(5))
                .build();
        HttpResponse<String> resp = null;
        try {
            resp = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }*/

        HttpResponse<String> resp = ConnectionUtil.sendRequest("https://checkip.amazonaws.com", new String[]{"Accept", "application/text"}, null);

        if (resp.statusCode() == 200) {
            this.address = resp.body();
        } else {
            System.out.println("Failed to get online address");
        }


    }


    private Ipv4Address() {
        updateAddress();
        // getLocalHostLanAddress();
    }

}
