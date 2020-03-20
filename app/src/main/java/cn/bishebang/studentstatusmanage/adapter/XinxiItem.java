package cn.bishebang.studentstatusmanage.adapter;

public class XinxiItem {

    private String id;
    private String xuehao;
    private String name;
    private String dianhua;
    private String banji;
    private String zhuanye;

    public XinxiItem(String id, String xuehao, String name, String dianhua, String banji, String zhuanye) {
        this.id = id;
        this.xuehao = xuehao;
        this.name = name;
        this.dianhua = dianhua;
        this.banji = banji;
        this.zhuanye = zhuanye;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXuehao() {
        return xuehao;
    }

    public void setXuehao(String xuehao) {
        this.xuehao = xuehao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDianhua() {
        return dianhua;
    }

    public void setDianhua(String dianhua) {
        this.dianhua = dianhua;
    }

    public String getBanji() {
        return banji;
    }

    public void setBanji(String banji) {
        this.banji = banji;
    }

    public String getZhuanye() {
        return zhuanye;
    }

    public void setZhuanye(String zhuanye) {
        this.zhuanye = zhuanye;
    }
}
