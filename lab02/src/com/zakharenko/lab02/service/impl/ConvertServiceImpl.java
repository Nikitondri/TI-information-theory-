package com.zakharenko.lab02.service.impl;

import com.zakharenko.lab02.service.ConvertService;

import java.util.Arrays;
import java.util.BitSet;

public class ConvertServiceImpl implements ConvertService {
    int size;
    int lenght;
    boolean isCorrect = true;

    @Override
    public BitSet convertBytesToBits(byte[] byteArr) {
        if(isCorrect){
            size = byteArr.length * 8;
            lenght = byteArr.length;
            isCorrect = false;
        } else{
            isCorrect = true;
        }
        return BitSet.valueOf(byteArr);
    }

    @Override
    public byte[] convertBitsToBytes(BitSet bitSet) {
        byte[] byteArr = Arrays.copyOfRange(bitSet.toByteArray(), 0, lenght) ;
        return byteArr;
    }

    @Override
    public BitSet convertStringToBits(String plainText) {
        char[] chars = plainText.toCharArray();
        BitSet result = new BitSet(chars.length);
        for(int i = 0; i < size; i++){
            if(chars[i] == '1'){
                result.set(i);
            }
        }
        return result;
    }

    @Override
    public String convertBitsToString(BitSet bitSet) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < size; i++){
            if(bitSet.get(i)){
                stringBuilder.append("1");
            } else {
                stringBuilder.append("0");
            }
        }
        return stringBuilder.toString();
    }
}
