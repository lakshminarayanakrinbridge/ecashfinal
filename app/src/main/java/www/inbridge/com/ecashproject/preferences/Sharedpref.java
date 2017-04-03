package www.inbridge.com.ecashproject.preferences;

/**
 * Created by USER on 2/25/2017.
 */

public class Sharedpref {

    public static final String TAG_USERNAME = "name";
    public static final String JSON_ARRAY = "categories";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    //Keys for email and password as defined in our $_POST['mobile_no'] in https://3dedn.com/E-Cash/app/OTP_android.php
    public static final String KEY_MOBILENUMBER="mobile_no";
    //public static final String KEY_EMAILID="emailid";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "true";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "ecash";

    //This would be used to store the email of current logged in user
    public static final String USERNAME_SHARED_PREF = "username";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";




    public static final String MERCHANTID_SHARED_PREF="m_id";

    public static final String KEY_MERCHANTID="m_id";

    // public static final String OTPTIMESTAMP_SHARED_PREF="otp_timestamp";




    public static final String terminalname_shared_pref="tname";
    public static final String terminalmobile_shared_pref="tnum";
    public static final String terminalemail_shared_pref="tmail";




    public static final String LOGIN_FAILURE1="You will receive an OTP only on this number";
    public static final String LOGIN_FAILURE2="OTP not delivered";
    public static final String LOGIN_FAILURE3="Register Number already Exists";
    public static final String LOGIN_FAILURE4="Parameter missing";
    public static final String OTP_KEY="otp";

    public static final String MERCHANT_NAME_SHARED_PREF="mname";
    public static final String MERCHANT_KEY_SHARED_PREF="mkey";
    public static final String ACCOUNT_NAME_SHARED_PREF="aname";
    public static final String ACCOUNT_NUMBER_SHARED_PREF="anumber";
    public static final String IFSCCODE_SHARED_PREF="ifsccode";
    public static final String ADDRESS_SHARED_PREF="address";
    public static final String KYCDETAILS_SHARED_PREF="kycdetails";
    public static final String CATEGORY_SHARED_PREF="mcategory";
    public static final String MOBILENUMBER_SHARED_PREF="mobilenumber";
    public static final String EMAILID_SHARED_PREF="emailid";
    public static final String APPROVAL_STATUS="approvalstatus";
    public static final String LAT_SHARED_PREF="lat";
    public static final String LNG_SHARED_PREF="lng";
    public static final String ECASH_CASH_BAL_SHARED_PREF="cashbal";
    public static final String ECASH_CASH_BACK_BAL_SHARED_PREF="cashbackbal";
    public static final String MERCHANT_CODE_SHARED_PREF="mcode";
    public static final String KEY_MERCHANT_CODE="m_code";


    public static  String newstr=null;
    public static  String newstr1=null;
    public static  String newstr2=null;




        public static final String LATITUDE_SHARED_PREF="lat";
        public static final String LONGITUDE_SHARED_PREF="lng";
        public static final String KEY_CASHIERNAME1="cashiername1";
        public static final String KEY_CASHIERNAME2="cashiername2";
        public static final String KEY_CASHIERMOBILE1="cashiermobile1";
        public static final String KEY_CASHIERMOBILE2="cashiermobile2";
        public static final String KEY_CASHIEREMAIL1="cashieremail1";
        public static final String KEY_CASHIEREMAIl2="cashieremail2";
        public static final String TERMINAL1_CASHIERNAME="terminal_name1";
        public static final String TERMINAL1_MOBILENUMBER="mobile1";
        public static final String TERMINAL1_EMAILID="email1";
        public static final String TERMINAL2_CASHIERNAME="terminal_name2";
        public static final String TERMINAL2_MOBILENUMBER="mobile2";
        public static final String TERMINAL2_EMAILID="email2";




    }




