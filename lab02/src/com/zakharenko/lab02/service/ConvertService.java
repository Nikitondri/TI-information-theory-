package com.zakharenko.lab02.service;

import java.util.BitSet;

public interface ConvertService {
    BitSet convertBytesToBits(byte[] byteArr);
    byte[] convertBitsToBytes(BitSet bitSet);
    BitSet convertStringToBits(String plainText);
    String convertBitsToString(BitSet bitSet);
}
