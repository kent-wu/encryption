package com.huawei.it;

public class BASE64Encription {

    private BASE64Encription() {
    }

    public static String encode(byte[] btData, int iLen) {
        Object lszData = null;
        Object lbtTmp = null;
        String lstEncoding = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        if (btData == null) {
            return null;
        } else {
            if (iLen <= 0 || iLen > btData.length) {
                iLen = btData.length;
            }

            boolean lbFlag = iLen % 3 == 0;
            int liGroup = iLen / 3;
            int ii = liGroup;
            if (!lbFlag) {
                ii = liGroup + 1;
            }

            char[] var10 = new char[4 * ii];
            byte[] var11 = new byte[3];
            ii = 0;
            int jj = 0;

            int kk;
            for (kk = 0; ii < liGroup; ++ii) {
                var11[0] = btData[kk++];
                var11[1] = btData[kk++];
                var11[2] = btData[kk++];
                var10[jj++] = lstEncoding.charAt(var11[0] >> 2 & 63);
                var10[jj++] = lstEncoding.charAt((var11[0] & 3) << 4 | var11[1] >> 4 & 15);
                var10[jj++] = lstEncoding.charAt((var11[1] & 15) << 2 | var11[2] >> 6 & 3);
                var10[jj++] = lstEncoding.charAt(var11[2] & 63);
            }

            if (!lbFlag) {
                var11[0] = btData[kk++];
                var10[jj++] = lstEncoding.charAt(var11[0] >> 2 & 63);
                var10[jj + 1] = 61;
                var10[jj + 2] = 61;
                if (iLen % 3 == 1) {
                    var10[jj] = lstEncoding.charAt((var11[0] & 3) << 4);
                } else {
                    var11[1] = btData[kk];
                    var10[jj++] = lstEncoding.charAt((var11[0] & 3) << 4 | var11[1] >> 4 & 15);
                    var10[jj] = lstEncoding.charAt((var11[1] & 15) << 2);
                }
            }

            return new String(var10);
        }
    }

    public static byte[] decode(String stData) {
        Object lszTmp = null;
        Object lbtData = null;
        int liLen = stData.length();
        if (liLen % 4 != 0) {
            return null;
        } else {
            int liGroup = liLen / 4;
            int ii = liGroup * 3;
            boolean lbFlag = true;
            char[] var9 = new char[4];
            if (stData.charAt(liLen - 1) == 61) {
                --liLen;
                --ii;
                --liGroup;
                lbFlag = false;
                if (stData.charAt(liLen - 1) == 61) {
                    --liLen;
                    --ii;
                }
            }

            int jj;
            for (jj = 0; jj < liLen; ++jj) {
                var9[0] = stData.charAt(jj);
                if (var9[0] != 43 && (47 > var9[0] || var9[0] > 57) && (65 > var9[0] || var9[0] > 90) && (97 > var9[0] || var9[0] > 122)) {
                    return null;
                }
            }

            byte[] var10 = new byte[ii];
            ii = 0;
            jj = 0;

            int kk;
            for (kk = 0; ii < liGroup; ++ii) {
                var9[0] = returnToData(stData.charAt(kk++));
                var9[1] = returnToData(stData.charAt(kk++));
                var9[2] = returnToData(stData.charAt(kk++));
                var9[3] = returnToData(stData.charAt(kk++));
                var10[jj++] = (byte) (var9[0] << 2 | var9[1] >> 4 & 3);
                var10[jj++] = (byte) (var9[1] << 4 | var9[2] >> 2 & 15);
                var10[jj++] = (byte) (var9[2] << 6 | var9[3] & 63);
            }

            if (!lbFlag) {
                var9[0] = returnToData(stData.charAt(kk++));
                var9[1] = returnToData(stData.charAt(kk++));
                var10[jj++] = (byte) (var9[0] << 2 | var9[1] >> 4 & 3);
                if (liLen % 4 == 3) {
                    var9[2] = returnToData(stData.charAt(kk));
                    var10[jj] = (byte) (var9[1] << 4 | var9[2] >> 2 & 15);
                }
            }

            lszTmp = null;
            return var10;
        }
    }

    private static char returnToData(char cChar) {
        if (65 <= cChar && cChar <= 90) {
            cChar = (char) (cChar - 65);
        } else if (97 <= cChar && cChar <= 122) {
            cChar = (char) (cChar - 97);
            cChar = (char) (cChar + 26);
        } else if (48 <= cChar && cChar <= 57) {
            cChar = (char) (cChar - 48);
            cChar = (char) (cChar + 52);
        } else if (cChar == 43) {
            cChar = 62;
        } else {
            cChar = 63;
        }

        return cChar;
    }

    
}
