package com.zakharenko.lab02.service.impl;

import com.zakharenko.lab02.service.CryptLFSRService;

import java.util.BitSet;

public class CryptLFSRServiceImpl implements CryptLFSRService {
    @Override
    public BitSet generateKey(int size, BitSet startState) {
        BitSet key = new BitSet(size);
        for(int i = 0; i < size; i++){
            if(startState.get(0)){
                key.set(i);
            }
            boolean xor = startState.get(0) ^ startState.get(32) ^ startState.get(33) ^ startState.get(37);
            for(int j = 1; j < 38; j++){
                if(startState.get(j)){
                    startState.set(j-1);
                } else{
                    startState.clear(j-1);
                }
            }
            if(xor){
                startState.set(37);
            } else {
                startState.clear(37);
            }
        }
        return key;
    }

    @Override
    public BitSet encrypt(BitSet inputData, BitSet key) {
        BitSet result = new BitSet(inputData.size());
        for (int i = 0; i < inputData.size(); i++){
            if(inputData.get(i) ^ key.get(i)){
                result.set(i);
            }
        }
        return result;
    }
}
