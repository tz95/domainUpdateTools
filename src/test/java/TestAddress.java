import org.eu.tz95.connect.base.Address;
import org.eu.tz95.connect.Ipv4Address;
import org.eu.tz95.connect.Ipv6Address;
import org.junit.Test;

public class TestAddress {

    @Test
    public void testIpv6() {
        Address instance = Ipv6Address.getInstance();
        instance.updateAddress();
        String ipv6Address = instance.getAddress();
        System.out.println("IPv6 Address: " + ipv6Address);
    }

    @Test
    public void testIpv4() {
        Address instance = Ipv4Address.getInstance();
        instance.updateAddress();
        String ipv4Address = instance.getAddress();
        System.out.println("IPv4 Address: " + ipv4Address);
    }

}
