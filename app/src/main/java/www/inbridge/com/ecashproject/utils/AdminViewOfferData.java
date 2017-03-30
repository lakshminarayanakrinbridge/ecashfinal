package www.inbridge.com.ecashproject.utils;

/**
 * Created by USER on 3/30/2017.
 */

public class AdminViewOfferData {

    private String mname;
    private String mid;
    private String mkey;
    private String moffer;
    private String mvalidity;
    private String mcategory;

    public AdminViewOfferData(String mname,String mid,String mkey,String moffer,String mvalidity,String mcategory)
    {
        this.mid=mid;
        this.mname=mname;
        this.mkey=mkey;
        this.moffer=moffer;
        this.mvalidity=mvalidity;
        this.mcategory=mcategory;
    }

    public String getMname() {
        return mname;
    }

    public String getMcategory() {
        return mcategory;
    }

    public String getMkey() {

        return mkey;
    }

    public String getMoffer() {
        return moffer;
    }

    public String getMvalidity() {
        return mvalidity;
    }

}
