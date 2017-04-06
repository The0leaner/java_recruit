package com.chen;

import java.io.IOException;


public class FirstOne {

    private int a;
    public  String str;



    public String oct2Bin(int a){

        StringBuffer strB = new StringBuffer();
        this.a =a;

        if (a == 0){
            strB = null;
            return strB.toString();}

        if (a > 0){
            while(a > 2){
                int temp = a%2;

                strB.append(temp);
            }
        }else if (a  < 0){

            a = Math.abs(a);
            int temp = a%2;
            strB.append(temp);

        }

        str = strB.toString();
        return str;
    }

    public static void main(String[] args) throws IOException {
        int a =System.in.read() ;
        String str = new String();
        System.out.println(str);

    }
}
