package www.inbridge.com.ecashproject.utils;

import android.util.Log;

/**
 * Created by USER on 2/25/2017.
 */

public class AdminApproveMerchantData {


    public String merchantname;
    public String merchantcode;
    public String merchantcategory;
    public AdminApproveMerchantData()
    {

    }

    public AdminApproveMerchantData(String merchantname,String merchantcategory,String merchantcode)
    {
        this.merchantname=merchantname;
        this.merchantcategory=merchantcategory;
        this.merchantcode=merchantcode;
        Log.e("code", this.merchantcode );
    }
}
