package net.example.paul.rapapp.domain;

/**
 * Created by 牛谱乐
 * Timer 2017/2/13 21:46
 * E-mail niupuyue@togogo.net
 */
public class DataOutInfo {


    private String reson;
    private String result;
    private int error_code;

    public void setError_code(int error_code){
        this.error_code = error_code;
    }

    public int getError_code(){
        return error_code;
    }

    public String getReson() {
        return reson;
    }

    public String getResult() {
        return result;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "DataOutInfo{" +
                "reson='" + reson + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
