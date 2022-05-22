package com.zakharenko.lab02.runner;

import com.zakharenko.lab02.dal.IOFileDal;
import com.zakharenko.lab02.dal.exception.DalException;
import com.zakharenko.lab02.dal.impl.IOFileDalImpl;
import com.zakharenko.lab02.service.ConvertService;
import com.zakharenko.lab02.service.CryptLFSRService;
import com.zakharenko.lab02.service.impl.ConvertServiceImpl;
import com.zakharenko.lab02.service.impl.CryptLFSRServiceImpl;
import com.zakharenko.lab02.view.IOView;
import com.zakharenko.lab02.view.impl.IOViewImpl;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

public class Runner {
    public static void main(String[] args) throws DalException {
        IOFileDal ioFileDal = new IOFileDalImpl();
        ConvertService convertService = new ConvertServiceImpl();
        IOView ioView = new IOViewImpl();
        CryptLFSRService crypt = new CryptLFSRServiceImpl();
        BitSet bitSet = convertService.convertBytesToBits(ioFileDal.readFile("src/resources/res"));
//        System.out.println(bitSet);
//        bitSet.stream().forEach(System.out::println);
        BitSet bitSet1 = new BitSet(1);
        System.out.println(bitSet1);
        ioView.printBits(bitSet1);
        for(int i = 0; i < 38; i++){
            bitSet1.set(i);
        }
        System.out.println("\n///////////////");
        ioView.printBits(bitSet1);
        System.out.println("\n///////////////////");
        BitSet key = crypt.generateKey(bitSet.size(), bitSet1);
        ioView.printBits(key);
        System.out.println("\n///////////////////");
        BitSet crypto = crypt.encrypt(bitSet, key);
        ioView.printBits(crypto);

        ioFileDal.writeFile(
                "src/resources/rrrr",
                convertService.convertBitsToBytes(crypto)
        );
    }
}
