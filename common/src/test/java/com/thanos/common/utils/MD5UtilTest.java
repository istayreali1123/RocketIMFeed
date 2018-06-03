package com.thanos.common.utils;

import org.junit.Test;

/**
 * Created by wangjialong on 6/3/18.
 */
public class MD5UtilTest {

    @Test
    public void testMD5() {
        String testStr1 = "龙帅很帅";
        String md5Str1 = "fd2a9edaf4f9fbc96f915b4841b28625";

        String str1Val = MD5Util.md5(testStr1);
        assert md5Str1.equals(str1Val);

        String testStr2 = "ghostjialong is an excellent engineer!";
        String md5Str2 = "a030bcaf6f525e26ba5ed822be18cc01";
        String str2Val = MD5Util.md5(testStr2);
        assert md5Str2.equals(str2Val);

    }
}
