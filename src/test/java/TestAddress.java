import org.eu.tz95.tools.Main;
import org.eu.tz95.tools.Setup;
import org.eu.tz95.tools.connect.base.Address;
import org.eu.tz95.tools.connect.Ipv4Address;
import org.eu.tz95.tools.connect.Ipv6Address;
import org.eu.tz95.tools.util.ConnectionUtil;
import org.junit.Test;

public class TestAddress {



    @Test
    public void testGetProperty() {
        new Setup("ipv6").update();
    }

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
        // String ipv4Address = instance.getAddress();
        System.out.println("IPv4 Address: " + instance);
    }

    @Test
    public void testMain(){
        String[] args = {"ipv6"};
        Main.main(args);
    }

    @Test
    public void testConnect(){
        boolean result = ConnectionUtil.testConnection();
        if (result) {
            System.out.println("Connection successful");
        } else {
            System.out.println("Connection failed");
        }
    }

}
