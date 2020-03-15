package cn.bishebang.studentstatusmanage.adapter;

public class XuejiItem {

    private String id;
    private String stuname;
    private String ruxuenianfen;
    private String shifozaiji;

    public XuejiItem(String id, String stuname, String ruxuenianfen, String shifozaiji) {
        this.id = id;
        this.stuname = stuname;
        this.ruxuenianfen = ruxuenianfen;
        this.shifozaiji = shifozaiji;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getRuxuenianfen() {
        return ruxuenianfen;
    }

    public void setRuxuenianfen(String ruxuenianfen) {
        this.ruxuenianfen = ruxuenianfen;
    }

    public String getShifozaiji() {
        return shifozaiji;
    }

    public void setShifozaiji(String shifozaiji) {
        this.shifozaiji = shifozaiji;
    }
}
