package sun.encryption.util;

import sun.encryption.constants.Constants;
import sun.encryption.domain.Network;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.List;

import java.util.Enumeration;

public class NetworkUtil {

    /**
     * 一台机器可能存在多个网卡, 故返回数组
     * 网络接口地址(NetworkInterface) ----> 接口地址(InterfaceAddress) ----> IP地址(InetAddress)
     *  ----> 网络接口地址(NetworkInterface)
     * @return
     * @throws Exception
     */
    private static List<Network> getMacAddresses() throws Exception {

        // 获取机器上的所有网络接口, 返回结果至少包含一项(即, loopback本地环回测试)
        // getNetworkInterfaces() + getInetAddresses()可以获取到所有IP地址
        Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();

        StringBuilder sb = new StringBuilder();

        ArrayList<String> networkNames = new ArrayList<String>();

        // 保存mac地址列表
        ArrayList<Network> macList = new ArrayList<Network>();

        // 使用标签, 跳出多重循环
        here:
        while (en.hasMoreElements()) {

            NetworkInterface iface = en.nextElement();

            // 获取对应网络接口的所有接口地址(InterfaceAddress)
            List<InterfaceAddress> addrs = iface.getInterfaceAddresses();

            for (InterfaceAddress addr : addrs) {

                // InetAddress: Internet Protocol (IP) address: IP地址
                // 返回网络接口地址对应的IP地址
                InetAddress ip = addr.getAddress();

                // 由IP地址获取网络接口(NetworkInterface)
                // 方便方法搜索到绑定到其的具体IP地址的网络接口(NetworkInterface)
                NetworkInterface networkInterface = NetworkInterface.getByInetAddress(ip);

                // 若为空, 跳过
                if (networkInterface == null) {

                    continue;

                }

                // 获取以太网等名称, 如：eth0, eth1, wlan1
                String name = networkInterface.getName();

                // 获取描述
                String displayName = networkInterface.getDisplayName();

                // 当网络接口有权限连接, 并且其具有MAC地址时, 返回二进制MAC硬件地址
                byte[] mac = networkInterface.getHardwareAddress();

                // 是否为虚拟网络接口
                boolean virtual = networkInterface.isVirtual();

                // 网络接口是否启动
                boolean up = networkInterface.isUp();

                if (mac == null) {

                    continue;

                }

                // 清空StringBuffer中的内容
                sb.delete(0, sb.length());

                // 转换MAC地址格式
                for (int i = 0; i < mac.length; i++) {

                    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));

                }

                Network network = new Network();

                // 若网络接口首次出现, 避免返回结果重复
                if (!networkNames.contains(name)) {

                    networkNames.add(name);

                    network.setName(name);
                    network.setDisplayName(displayName);
                    network.setMac(sb.toString());
                    network.setVirtual(virtual);
                    network.setUp(up);

                    macList.add(network);

                }

                // 若找到, 跳出
                if (networkNames.contains(Constants.WIRELESS)) {

                    break here;

                }

            }

        }

        return macList;

    }


    // 根据字符串"wireless"找到对应mac地址
    // 方法有效的条件是: PC机具有无线网卡
    // linux再议
    public static String getWirelessMacAddress() throws Exception {

        List<Network> macs = getMacAddresses();

        for (Network mac: macs) {

            if (mac.getDisplayName().toLowerCase().contains(Constants.WIRELESS)) {

                return mac.getMac();

            }

        }

        return null;

    }




    public static void main(String[] args) throws Exception {

        String wirelessMac = getWirelessMacAddress();

        String text = DLLUtil.textEncryption(wirelessMac);

        System.out.println(text);

//        List<Network> macs = getMacAddresses();
//
//        for (Network mac: macs) {
//
//            System.out.println(mac.getName());
//            System.out.println(mac.getDisplayName());
//            System.out.println(mac.getMac());
//            System.out.println(mac.isVirtual());
//            System.out.println(mac.isUp());
//
//            System.out.println("------------------");
//
//        }

    }

}
