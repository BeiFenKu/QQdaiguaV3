package com.king.YH_Fragment;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by KingLee on 2018/4/16.
 */

public class test {
    public static void main(String args[]) throws ParseException {
        String word = "\n" +
                "     var URL_PARAM = {\"from\":0,\"param\":\"1278991552\"};\n" +
                "     var GUEST_INFO = {\"is_superclub\":0,\"is_year_club\":0,\"is_club\":1,\"club_end_timestamp\":0,\"user_tag\":{\"vip_reflux\":0,\"svip_reflux\":0},\"level\":5,\"add_step\":10,\"qq_speed\":\"1.6\",\"speed_today\":\"1.6\",\"is_login\":1,\"has_error\":false,\"ret\":0,\"iospay_white\":1,\"servertime\":1528031512,\"today\":\"2018-06-03\",\"uin\":1278991552,\"left_days\":0};\n" +
                "     var GUEST_LEVEL_INFO = {\"ret\":0,\"QzoneVisitor\":\"0\"," +
                "\"WeishiVideoview\":\"0\",\"iBaseDays\":\"0.0\",\"iCostMs\":\"16\",\"iMaxLvlRealDays\":\"0.0\",\"iMaxLvlTotalDays\":\"5.7\",\"iMedal\":\"0\",\"iMobileGameOnline\":\"0\",\"iMobileQQOnline\":\"0\",\"iMobileQQOnlineTime\":\"0\",\"iMqing\":\"0\",\"iNextLevelDay\":\"59\",\"iNoHideOnline\":\"0\",\"iNoHideOnlineTime\":\"0\",\"iPCQQOnline\":\"0\",\"iPCQQOnlineTime\":\"0\",\"iPCSafeOnline\":\"0\",\"iQQLevel\":\"53\",\"iQQSportStep\":\"0\",\"iQWalletPay\":\"0\",\"iRealDays\":\"0.0\",\"iSVip\":\"0\",\"iSqq\":\"0\",\"iSqqLevel\":\"0\",\"iSqqSpeedRate\":\"0\",\"iSvrDays\":\"0.0\",\"iTotalActiveDay\":\"3073\",\"iTotalDays\":\"3.7\",\"iUseQQMusic\":\"0\",\"iVip\":\"1\",\"iVipLevel\":\"5\",\"iVipSpeedRate\":\"0\",\"iYearVip\":\"0\",\"sFaceUrl\":\"\\/\\/thirdqq.qlogo.cn\\/g?b=sdk&k=wwz4NQm2CUmRvSLEMNtb0A&s=100&t=1524998407\",\"sNickName\":\"+\"};";

        String pattern = " (.+?)\"is_superclub\":(.*?),\"(.+?)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(word);
        if (m.find( )) {
//            System.out.println("Found value: " + m.group(0) );
//            System.out.println("Found value: " + m.group(1) );
            System.out.println("Found value: " + m.group(2) );
        } else {
            System.out.println("NO MATCH");
        }
    }
}
