package me.storm.ninegag.util;

/**
 * Created by Sagi on 11-Sep-16.
 */
public class FunctionVault {

    public void FunctionVault(){

    }
    public static long toAscii(String s) {
        StringBuilder sb = new StringBuilder();
        String ascString = null;
        long asciiInt;
        for (int i = 0; i < s.length(); i++) {
            sb.append((int) s.charAt(i));
            char c = s.charAt(i);
        }
        ascString = sb.toString();
        String cutString = ascString.substring(0, 15);
        asciiInt = Long.parseLong(cutString);
        return asciiInt;
    }
}
