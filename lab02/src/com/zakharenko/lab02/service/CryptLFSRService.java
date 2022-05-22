package com.zakharenko.lab02.service;

import java.util.BitSet;

public interface CryptLFSRService {
    BitSet generateKey(int size, BitSet startState);
    BitSet encrypt(BitSet inputData, BitSet key);
}
