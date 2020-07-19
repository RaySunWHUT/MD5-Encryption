package sun.encryption.domain;

/**
 * Network: 实体domain类
 * author: Sun Rui
 */
public class Network {

    // 实际名称
    private String name;

    // 描述
    private String displayName;

    // MAC地址
    private String mac;

    // 是否为虚拟网卡
    private boolean virtual;

    // 是否启动
    private boolean up;

    public boolean isVirtual() {
        return virtual;
    }

    public void setVirtual(boolean virtual) {
        this.virtual = virtual;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
