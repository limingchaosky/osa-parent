package com.goldencis.osa;

import org.junit.Test;

/**
 * Created by limingchao on 2018/10/16.
 */
public class StringTest {

    @Test
    public void stringTest() {
        String str = "/osa/user/userList";

        int index = str.indexOf("/osa");
        System.out.println(index);

        String substring = str.substring(index + "/osa".length());
        System.out.println(substring);

    }

}
