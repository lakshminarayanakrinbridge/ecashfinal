package www.inbridge.com.ecashproject.utils;

/**
 * Created by USER on 2/25/2017.
 */

public class AdminSearchMerchantData {

    public String merchantcodeter;
    public String categoryter;
    public String merchantnameter;
    public String ecashbalanceter;


    public AdminSearchMerchantData()
    {

    }

    public AdminSearchMerchantData(String merchantname,String category,String merchantcode,String ecashbalance)
    {
        this.merchantnameter=merchantname;
        this.categoryter=category;
        this.merchantcodeter=merchantcode;
        this.ecashbalanceter=ecashbalance;

    }
}
