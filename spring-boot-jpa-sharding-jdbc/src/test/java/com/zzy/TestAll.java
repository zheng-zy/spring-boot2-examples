package com.zzy;

import org.junit.Test;

import java.util.UUID;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2018/12/27.
 */
public class TestAll {

    @Test
    public void test(){
        String md5 = UUID.randomUUID().toString().replaceAll("-", "");
        String md52 = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println("md5 = " + md5);
        System.out.println("md52 = " + md52);

        md5 = "0cc3e50673d5465fa0e481c87abc3cd8";
        System.out.println(Math.abs(md5.hashCode())%3);
        System.out.println(md5.hashCode()%3);
        System.out.println(md52.hashCode()%3);
        System.out.println(md52.hashCode()%3);
    }

}
