package net.example.paul.rapapp.domain;

/**
 * Created by 牛谱乐
 * Timer 2017/2/13 21:48
 * E-mail niupuyue@togogo.net
 */
public class DataMidInfo {
    private String stat;
    private String data;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataMidInfo{" +
                "stat=" + stat +
                ", data='" + data + '\'' +
                '}';
    }
}
