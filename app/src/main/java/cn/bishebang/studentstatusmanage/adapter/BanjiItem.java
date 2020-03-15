package cn.bishebang.studentstatusmanage.adapter;

public class BanjiItem {

    private String id;
    private String nianji;
    private String zhuanye;
    private String num;

    public BanjiItem(String id,String nianji, String zhuanye, String num) {
        this.id = id;
        this.nianji = nianji;
        this.zhuanye = zhuanye;
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNianji() {
        return nianji;
    }

    public void setNianji(String nianji) {
        this.nianji = nianji;
    }

    public String getZhuanye() {
        return zhuanye;
    }

    public void setZhuanye(String zhuanye) {
        this.zhuanye = zhuanye;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
