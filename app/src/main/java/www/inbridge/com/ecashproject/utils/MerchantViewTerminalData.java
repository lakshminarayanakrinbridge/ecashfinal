package www.inbridge.com.ecashproject.utils;

/**
 * Created by USER on 2/25/2017.
 */

public class MerchantViewTerminalData {


    private String cashiername;
    private String mobilenumber;
    private String emailid;
    private String terminalid;


    public MerchantViewTerminalData(String cashiername,String mobilenumber,String emailid,String terminalid)
    {
        this.setCashiername(cashiername);
        this.setMobilenumber(mobilenumber);
        this.setEmailid(emailid);
        this.setTerminalid(terminalid);

    }

    public String getCashiername() {
        return cashiername;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setCashiername(String cashiername) {
        this.cashiername = cashiername;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public void setTerminalid(String terminalid) {
        this.terminalid = terminalid;
    }

    public String getEmailid() {
        return emailid;

    }

    public String getTerminalid() {
        return terminalid;
    }
}
