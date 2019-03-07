package com.hui.algorithm;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * SipHashTests -- Tests for SipHash_2_4.java
 * <p>
 * (c)2013 Forward Computing and Control Pty. Ltd. (www.forward.com.au)<br>
 * This code may be freely used for both private and commercial use.<br>
 * Provide this copyright is maintained.<br>
 * 
 *
 */
public class SipHashTests {

	byte[] testKey = { (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x06,
			(byte) 0x07, (byte) 0x08, (byte) 0x09, (byte) 0x0a, (byte) 0x0b, (byte) 0x0c, (byte) 0x0d, (byte) 0x0e,
			(byte) 0x0f };

	byte[] testMsg = { (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x06,
			(byte) 0x07, (byte) 0x08, (byte) 0x09, (byte) 0x0a, (byte) 0x0b, (byte) 0x0c, (byte) 0x0d, (byte) 0x0e };
	

	/**
	 * Hash the testKey with the testMsg as described in https://131002.net/siphash/siphash.pdf 
	 * <br>The result is in BigEndian format
	 */
	@Test
	public void pdfTest() {
		SipHash_2_4 sipHash = new SipHash_2_4();
		// note in the pdf https://131002.net/siphash/siphash.pdf the longs, v0,v1,v2,v3 are shown in BigEndian format
		long hashResult = sipHash.hash(testKey, testMsg);
		byte[] result = BytesUtil.longToBytes(hashResult); // in BigEndian format
		System.out.println("result(in BigEndian) of hashing testMsg  = " + SipHash_2_4.toHex(result) + "  -- matches result in https://131002.net/siphash/siphash.pdf");
		byte[] ans = {(byte) 0xa1, (byte) 0x29, (byte) 0xca, (byte) 0x61, (byte) 0x49, (byte) 0xbe, (byte) 0x45, (byte) 0xe5};
		assertTrue(cmpByteArrays(ans, result));
		System.out.println();
	}

	
	/**
	 * Test the hash of a single -ve byte, (byte)0x81 
	 * <br>The result is in LittleEndian format
	 */
	@Test
	public void negativeTest() {
		SipHash_2_4 sipHash = new SipHash_2_4();
		// Some other Java implementations do not handle -ve bytes correctly 
		byte[] in = { (byte) 0x81 };
		long hashResult = sipHash.hash(testKey, in);
		byte[] result = BytesUtil.longToBytesLE(hashResult); // in BigEndian format
		System.out.println("result(in Little Endian) of hashing 0x81  = " + SipHash_2_4.toHex(result));
		byte[] ans = { (byte) 0x0E, (byte) 0x7E, (byte) 0x74, (byte) 0x97, (byte) 0x19, (byte) 0x88, (byte) 0x23,
				(byte) 0x3A };
		assertTrue(cmpByteArrays(result, ans));
		System.out.println();
	}


	/**
	 * Test the standard message test contained in the C reference implementation https://131002.net/siphash/siphash24.c
	 * <br>The results are in LittleEndian format
	 */
	@Test
	public void referenceTests() {
		SipHash_2_4 sipHash = new SipHash_2_4();
		// in the C reference implementation https://131002.net/siphash/siphash24.c
		// the results are shown as hex digits in LittleEndian format
		System.out.println("results(in Little Endian) for C Reference tests -- matches results in https://131002.net/siphash/siphash24.c");
		int MAXLEN = 64;
		for (int i = 0; i < MAXLEN; ++i) {
			byte[] in = new byte[i];
			for (int j = 0; j < i; j++) {
				in[j] = (byte) j;
			}
			long hashResult = sipHash.hash(testKey, in);
			byte[] result = BytesUtil.longToBytesLE(hashResult);
			System.out.println("Test msg length = " + i + " result(in LittleEndian) " + BytesUtil.toHex(result, 0, result.length));
			assertTrue(cmpByteArrays(result, vectors[i]));
		}
		System.out.println();
	}

	private boolean cmpByteArrays(byte[] b1, byte[] b2) {
		boolean match = true;
		for (int j = 0; j < SipHash_2_4.LONG_BYTES; j++) {
			if (b1[j] != b2[j]) {
				match = false;
				System.out.println(" Failed at vector at positon " + j);
				break;
			}
		}
		return match;
	}

	/*
	 * SipHash-2-4 output with k = 00 01 02 ... and in = (empty string) in = 00 (1 byte) in = 00 01 (2 bytes) in = 00 01
	 * 02 (3 bytes) ... in = 00 01 02 ... 3e (63 bytes)
	 */
	private byte vectors[][] = {
			{ (byte) 0x31, (byte) 0x0e, (byte) 0x0e, (byte) 0xdd, (byte) 0x47, (byte) 0xdb, (byte) 0x6f, (byte) 0x72 },
			{ (byte) 0xfd, (byte) 0x67, (byte) 0xdc, (byte) 0x93, (byte) 0xc5, (byte) 0x39, (byte) 0xf8, (byte) 0x74 },
			{ (byte) 0x5a, (byte) 0x4f, (byte) 0xa9, (byte) 0xd9, (byte) 0x09, (byte) 0x80, (byte) 0x6c, (byte) 0x0d },
			{ (byte) 0x2d, (byte) 0x7e, (byte) 0xfb, (byte) 0xd7, (byte) 0x96, (byte) 0x66, (byte) 0x67, (byte) 0x85 },
			{ (byte) 0xb7, (byte) 0x87, (byte) 0x71, (byte) 0x27, (byte) 0xe0, (byte) 0x94, (byte) 0x27, (byte) 0xcf },
			{ (byte) 0x8d, (byte) 0xa6, (byte) 0x99, (byte) 0xcd, (byte) 0x64, (byte) 0x55, (byte) 0x76, (byte) 0x18 },
			{ (byte) 0xce, (byte) 0xe3, (byte) 0xfe, (byte) 0x58, (byte) 0x6e, (byte) 0x46, (byte) 0xc9, (byte) 0xcb },
			{ (byte) 0x37, (byte) 0xd1, (byte) 0x01, (byte) 0x8b, (byte) 0xf5, (byte) 0x00, (byte) 0x02, (byte) 0xab },
			{ (byte) 0x62, (byte) 0x24, (byte) 0x93, (byte) 0x9a, (byte) 0x79, (byte) 0xf5, (byte) 0xf5, (byte) 0x93 },
			{ (byte) 0xb0, (byte) 0xe4, (byte) 0xa9, (byte) 0x0b, (byte) 0xdf, (byte) 0x82, (byte) 0x00, (byte) 0x9e },
			{ (byte) 0xf3, (byte) 0xb9, (byte) 0xdd, (byte) 0x94, (byte) 0xc5, (byte) 0xbb, (byte) 0x5d, (byte) 0x7a },
			{ (byte) 0xa7, (byte) 0xad, (byte) 0x6b, (byte) 0x22, (byte) 0x46, (byte) 0x2f, (byte) 0xb3, (byte) 0xf4 },
			{ (byte) 0xfb, (byte) 0xe5, (byte) 0x0e, (byte) 0x86, (byte) 0xbc, (byte) 0x8f, (byte) 0x1e, (byte) 0x75 },
			{ (byte) 0x90, (byte) 0x3d, (byte) 0x84, (byte) 0xc0, (byte) 0x27, (byte) 0x56, (byte) 0xea, (byte) 0x14 },
			{ (byte) 0xee, (byte) 0xf2, (byte) 0x7a, (byte) 0x8e, (byte) 0x90, (byte) 0xca, (byte) 0x23, (byte) 0xf7 },
			// test result reversed since here it is LittleEndian and in the pdf it is shown in BigEndian
			{ (byte) 0xe5, (byte) 0x45, (byte) 0xbe, (byte) 0x49, (byte) 0x61, (byte) 0xca, (byte) 0x29, (byte) 0xa1 },
			//
			{ (byte) 0xdb, (byte) 0x9b, (byte) 0xc2, (byte) 0x57, (byte) 0x7f, (byte) 0xcc, (byte) 0x2a, (byte) 0x3f },
			{ (byte) 0x94, (byte) 0x47, (byte) 0xbe, (byte) 0x2c, (byte) 0xf5, (byte) 0xe9, (byte) 0x9a, (byte) 0x69 },
			{ (byte) 0x9c, (byte) 0xd3, (byte) 0x8d, (byte) 0x96, (byte) 0xf0, (byte) 0xb3, (byte) 0xc1, (byte) 0x4b },
			{ (byte) 0xbd, (byte) 0x61, (byte) 0x79, (byte) 0xa7, (byte) 0x1d, (byte) 0xc9, (byte) 0x6d, (byte) 0xbb },
			{ (byte) 0x98, (byte) 0xee, (byte) 0xa2, (byte) 0x1a, (byte) 0xf2, (byte) 0x5c, (byte) 0xd6, (byte) 0xbe },
			{ (byte) 0xc7, (byte) 0x67, (byte) 0x3b, (byte) 0x2e, (byte) 0xb0, (byte) 0xcb, (byte) 0xf2, (byte) 0xd0 },
			{ (byte) 0x88, (byte) 0x3e, (byte) 0xa3, (byte) 0xe3, (byte) 0x95, (byte) 0x67, (byte) 0x53, (byte) 0x93 },
			{ (byte) 0xc8, (byte) 0xce, (byte) 0x5c, (byte) 0xcd, (byte) 0x8c, (byte) 0x03, (byte) 0x0c, (byte) 0xa8 },
			{ (byte) 0x94, (byte) 0xaf, (byte) 0x49, (byte) 0xf6, (byte) 0xc6, (byte) 0x50, (byte) 0xad, (byte) 0xb8 },
			{ (byte) 0xea, (byte) 0xb8, (byte) 0x85, (byte) 0x8a, (byte) 0xde, (byte) 0x92, (byte) 0xe1, (byte) 0xbc },
			{ (byte) 0xf3, (byte) 0x15, (byte) 0xbb, (byte) 0x5b, (byte) 0xb8, (byte) 0x35, (byte) 0xd8, (byte) 0x17 },
			{ (byte) 0xad, (byte) 0xcf, (byte) 0x6b, (byte) 0x07, (byte) 0x63, (byte) 0x61, (byte) 0x2e, (byte) 0x2f },
			{ (byte) 0xa5, (byte) 0xc9, (byte) 0x1d, (byte) 0xa7, (byte) 0xac, (byte) 0xaa, (byte) 0x4d, (byte) 0xde },
			{ (byte) 0x71, (byte) 0x65, (byte) 0x95, (byte) 0x87, (byte) 0x66, (byte) 0x50, (byte) 0xa2, (byte) 0xa6 },
			{ (byte) 0x28, (byte) 0xef, (byte) 0x49, (byte) 0x5c, (byte) 0x53, (byte) 0xa3, (byte) 0x87, (byte) 0xad },
			{ (byte) 0x42, (byte) 0xc3, (byte) 0x41, (byte) 0xd8, (byte) 0xfa, (byte) 0x92, (byte) 0xd8, (byte) 0x32 },
			{ (byte) 0xce, (byte) 0x7c, (byte) 0xf2, (byte) 0x72, (byte) 0x2f, (byte) 0x51, (byte) 0x27, (byte) 0x71 },
			{ (byte) 0xe3, (byte) 0x78, (byte) 0x59, (byte) 0xf9, (byte) 0x46, (byte) 0x23, (byte) 0xf3, (byte) 0xa7 },
			{ (byte) 0x38, (byte) 0x12, (byte) 0x05, (byte) 0xbb, (byte) 0x1a, (byte) 0xb0, (byte) 0xe0, (byte) 0x12 },
			{ (byte) 0xae, (byte) 0x97, (byte) 0xa1, (byte) 0x0f, (byte) 0xd4, (byte) 0x34, (byte) 0xe0, (byte) 0x15 },
			{ (byte) 0xb4, (byte) 0xa3, (byte) 0x15, (byte) 0x08, (byte) 0xbe, (byte) 0xff, (byte) 0x4d, (byte) 0x31 },
			{ (byte) 0x81, (byte) 0x39, (byte) 0x62, (byte) 0x29, (byte) 0xf0, (byte) 0x90, (byte) 0x79, (byte) 0x02 },
			{ (byte) 0x4d, (byte) 0x0c, (byte) 0xf4, (byte) 0x9e, (byte) 0xe5, (byte) 0xd4, (byte) 0xdc, (byte) 0xca },
			{ (byte) 0x5c, (byte) 0x73, (byte) 0x33, (byte) 0x6a, (byte) 0x76, (byte) 0xd8, (byte) 0xbf, (byte) 0x9a },
			{ (byte) 0xd0, (byte) 0xa7, (byte) 0x04, (byte) 0x53, (byte) 0x6b, (byte) 0xa9, (byte) 0x3e, (byte) 0x0e },
			{ (byte) 0x92, (byte) 0x59, (byte) 0x58, (byte) 0xfc, (byte) 0xd6, (byte) 0x42, (byte) 0x0c, (byte) 0xad },
			{ (byte) 0xa9, (byte) 0x15, (byte) 0xc2, (byte) 0x9b, (byte) 0xc8, (byte) 0x06, (byte) 0x73, (byte) 0x18 },
			{ (byte) 0x95, (byte) 0x2b, (byte) 0x79, (byte) 0xf3, (byte) 0xbc, (byte) 0x0a, (byte) 0xa6, (byte) 0xd4 },
			{ (byte) 0xf2, (byte) 0x1d, (byte) 0xf2, (byte) 0xe4, (byte) 0x1d, (byte) 0x45, (byte) 0x35, (byte) 0xf9 },
			{ (byte) 0x87, (byte) 0x57, (byte) 0x75, (byte) 0x19, (byte) 0x04, (byte) 0x8f, (byte) 0x53, (byte) 0xa9 },
			{ (byte) 0x10, (byte) 0xa5, (byte) 0x6c, (byte) 0xf5, (byte) 0xdf, (byte) 0xcd, (byte) 0x9a, (byte) 0xdb },
			{ (byte) 0xeb, (byte) 0x75, (byte) 0x09, (byte) 0x5c, (byte) 0xcd, (byte) 0x98, (byte) 0x6c, (byte) 0xd0 },
			{ (byte) 0x51, (byte) 0xa9, (byte) 0xcb, (byte) 0x9e, (byte) 0xcb, (byte) 0xa3, (byte) 0x12, (byte) 0xe6 },
			{ (byte) 0x96, (byte) 0xaf, (byte) 0xad, (byte) 0xfc, (byte) 0x2c, (byte) 0xe6, (byte) 0x66, (byte) 0xc7 },
			{ (byte) 0x72, (byte) 0xfe, (byte) 0x52, (byte) 0x97, (byte) 0x5a, (byte) 0x43, (byte) 0x64, (byte) 0xee },
			{ (byte) 0x5a, (byte) 0x16, (byte) 0x45, (byte) 0xb2, (byte) 0x76, (byte) 0xd5, (byte) 0x92, (byte) 0xa1 },
			{ (byte) 0xb2, (byte) 0x74, (byte) 0xcb, (byte) 0x8e, (byte) 0xbf, (byte) 0x87, (byte) 0x87, (byte) 0x0a },
			{ (byte) 0x6f, (byte) 0x9b, (byte) 0xb4, (byte) 0x20, (byte) 0x3d, (byte) 0xe7, (byte) 0xb3, (byte) 0x81 },
			{ (byte) 0xea, (byte) 0xec, (byte) 0xb2, (byte) 0xa3, (byte) 0x0b, (byte) 0x22, (byte) 0xa8, (byte) 0x7f },
			{ (byte) 0x99, (byte) 0x24, (byte) 0xa4, (byte) 0x3c, (byte) 0xc1, (byte) 0x31, (byte) 0x57, (byte) 0x24 },
			{ (byte) 0xbd, (byte) 0x83, (byte) 0x8d, (byte) 0x3a, (byte) 0xaf, (byte) 0xbf, (byte) 0x8d, (byte) 0xb7 },
			{ (byte) 0x0b, (byte) 0x1a, (byte) 0x2a, (byte) 0x32, (byte) 0x65, (byte) 0xd5, (byte) 0x1a, (byte) 0xea },
			{ (byte) 0x13, (byte) 0x50, (byte) 0x79, (byte) 0xa3, (byte) 0x23, (byte) 0x1c, (byte) 0xe6, (byte) 0x60 },
			{ (byte) 0x93, (byte) 0x2b, (byte) 0x28, (byte) 0x46, (byte) 0xe4, (byte) 0xd7, (byte) 0x06, (byte) 0x66 },
			{ (byte) 0xe1, (byte) 0x91, (byte) 0x5f, (byte) 0x5c, (byte) 0xb1, (byte) 0xec, (byte) 0xa4, (byte) 0x6c },
			{ (byte) 0xf3, (byte) 0x25, (byte) 0x96, (byte) 0x5c, (byte) 0xa1, (byte) 0x6d, (byte) 0x62, (byte) 0x9f },
			{ (byte) 0x57, (byte) 0x5f, (byte) 0xf2, (byte) 0x8e, (byte) 0x60, (byte) 0x38, (byte) 0x1b, (byte) 0xe5 },
			{ (byte) 0x72, (byte) 0x45, (byte) 0x06, (byte) 0xeb, (byte) 0x4c, (byte) 0x32, (byte) 0x8a, (byte) 0x95 } };

}
