package com.hui.BitOperation;

/**
 * @author: shenhaizhilong
 * @date: 2018/7/14 12:30
 */
public class BytesUtil {

    private static final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray(); // for toString routines

    /**
     * Convert a int to bytes in BigEndian format
     *
     * @param m  the int to convert
     * @return byte[4] containing the BigEndian format of the int
     */
    public static byte[] intToBytes(int m) {
        byte[] data = new byte[4];
        for (int i = 0; i < 4; i++) {
            data[3-i] =  (byte)((m >>> (8*i)) & 0xff);
        }
        return data;
    }


    /**
     * Converts byte[] in big Endian format to a int
     *
     * @param b  bytes in big Endian  format
     * @param offset  idx of first byte
     * @return the resulting int
     */
    public static int bytesToInt(byte[] b, int offset) {
        if ((b.length - offset) < 4) {
            throw new IllegalArgumentException("Less then 4 bytes starting from offset:" + offset);
        }
        int m = 0;
        for (int i = 0; i < 4; i++) {
            m =  (((int) b[i + offset]) & 0xff) +  (m << 8);
        }
        return m;
    }


    /**
     * Convert a int to bytes in Little Endian format
     *
     * @param m  the int to convert
     * @return byte[4] containing the  Little Endian format of the int
     */
    public static byte[] intToBytesLE(int m) {
        byte[] data = new byte[4];
        for (int i = 0; i < 4; i++) {
            data[i] =  (byte)((m >>> (8*i)) & 0xff);
        }
        return data;
    }
    /**
     * Converts byte[] in LittleEndian format to a int
     *
     * @param b  bytes in LittleEndian format
     * @param offset  idx of first byte
     * @return the resulting long
     */
    public static int bytesLEtoInt(byte[] b, int offset) {
        if ((b.length - offset) < 4) {
            throw new IllegalArgumentException("Less then 4 bytes starting from offset:" + offset);
        }
        int m = 0;
        for (int i = 0; i < 4; i++) {
            m |= ((((int) b[i + offset]) & 0xff) << (8 * i));
        }
        return m;
    }



    // Static Utility conversion methods

    /**
     * Converts byte[] in LittleEndian format to a long
     *
     * @param b  bytes in LittleEndian format
     * @param offset  idx of first byte
     * @return the resulting long
     */
    public static long bytesLEtoLong(byte[] b, int offset) {
        if ((b.length - offset) < 8) {
            throw new IllegalArgumentException("Less then 8 bytes starting from offset:" + offset);
        }
        long m = 0;
        for (int i = 0; i < 8; i++) {
            m |= ((((long) b[i + offset]) & 0xff) << (8 * i));
        }
        return m;
    }

    /**
     * Convert a long to bytes in LittleEndian format
     *
     * @param m  the long to convert
     * @return byte[8] containing the LittleEndian format of the long
     */
    public static byte[] longToBytesLE(long m) {
        byte[] b = new byte[8];
        for (int i = 0; i < 8; i++) {
            b[i] = (byte) ((m >>> 8 * i) & 0xff);
        }
        return b;
    }

    /**
     * Convert a long to bytes in BigEndian format
     *
     * @param m  the long to convert
     * @return byte[8] containing the BigEndian format of the long
     */
    public static byte[] longToBytes(long m) {
        byte[] b = new byte[8];
        for (int i = 0; i < 8; i++) {
            b[7 - i] = (byte) ((m >>> 8 * i) & 0xff);
        }
        return b;
    }

    /**
     * Converts byte[] in big Endian format to a long
     *
     * @param b  bytes in big Endian  format
     * @param offset  idx of first byte
     * @return the resulting long
     */
    public static long bytesToLong(byte[] b, int offset) {
        if ((b.length - offset) <8) {
            throw new IllegalArgumentException("Less then 8 bytes starting from offset:" + offset);
        }
        long m = 0;
        for (int i = 0; i < 8; i++) {
            m =  (((long) b[i + offset]) & 0xff) +  (m << 8);
        }
        return m;
    }

    /**
     * Convert a byte array to Hex Digits
     *
     * @param b  the byte array
     * @param offset  the starting index
     * @param length the length to convert
     * @return string contain the hex digits, 2 for each byte.
     */
    public static final String toHex(byte[] b, int offset, int length) {
        if ((b.length - offset) < length) {
            throw new IllegalArgumentException("Less then " + length + " bytes starting from offset:" + offset);
        }
        char[] buf = new char[length * 2];
        for (int i = 0, j = 0, k; i < length;) {
            k = b[offset + i++];
            buf[j++] = HEX_CHARS[(k >>> 4) & 0x0F];
            buf[j++] = HEX_CHARS[k & 0x0F];
        }
        return new String(buf);
    }
}
