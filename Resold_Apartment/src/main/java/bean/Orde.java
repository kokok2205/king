package bean;

public class Orde {
    private int oid;//预约id
    private String oname;//预约人姓名
    private String osex;//预约人性别
    private String ophone;//预约人电话
    private String optime;//预约时间
    private String otime;//当前时间

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getOsex() {
        return osex;
    }

    public void setOsex(String osex) {
        this.osex = osex;
    }

    public String getOphone() {
        return ophone;
    }

    public void setOphone(String ophone) {
        this.ophone = ophone;
    }

    public String getOptime() {
        return optime;
    }

    public void setOptime(String optime) {
        this.optime = optime;
    }

    public String getOtime() {
        return otime;
    }

    public void setOtime(String otime) {
        this.otime = otime;
    }
}
