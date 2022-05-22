package com.zakharenko.lab02.view.impl;

import com.zakharenko.lab02.view.IOView;

import java.util.BitSet;

public class IOViewImpl implements IOView {
    @Override
    public void printBits(BitSet bitSet) {
        int temp = 0;
        for(int i = 0; i < bitSet.size(); i++){
            if(bitSet.get(i)){
                System.out.print("1 ");
            } else{
                System.out.print("0 ");
            }
            temp++;
            if(temp == 37){
                System.out.println();
                temp = 0;
            }
        }
    }
}
