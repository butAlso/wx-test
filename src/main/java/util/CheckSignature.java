package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CheckSignature {
    private static final String token = "ggwpggwp";

    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[] {token, timestamp, nonce};
        Arrays.sort(arr);
        StringBuffer buff = new StringBuffer();
        for(String s : arr) {
            buff.append(s);
        }
        String tempStr = null;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            // 三个参数拼接起来加密
            byte[] digest = md.digest(buff.toString().getBytes());
            tempStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return tempStr == null ? false :
                (tempStr.equalsIgnoreCase(signature) ? true : false);
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }
}
