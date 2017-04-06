package com.chen;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by lenovo on 2017/4/6.
 */
public class Transform {
    private String g;
    private  int radixSrc;
    private int radixTgt;
    public String transform(String g, int radixSrc, int radixTgt){

        StringBuffer str = new StringBuffer();
       int size =  g.length();
       int sum = 0;
       for (int i = 0 ; i < size ; ++i){
           sum += (int)g.charAt(i) * radixSrc;
       }
       if(sum > 0){
           while (sum > radixTgt ){

               int t = sum % radixTgt;
               str.append(t);
           }
       }

       System.out.println(str.toString());
        return str.toString();
    }
    
    public static void main(String[] args) throws IOException {

    }
}
