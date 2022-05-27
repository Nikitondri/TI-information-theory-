package com.zakharenko.lab04.runner;

import com.zakharenko.lab04.service.CryptDSAService;
import com.zakharenko.lab04.service.exception.ServiceException;
import com.zakharenko.lab04.service.impl.CryptDSAServiceImpl;

import java.util.List;

public class Runner {
    //p: 9973 q: 277
    public static void main(String[] args) {
//        for(long p = 0; p < 10000; p++){
//            if(isPrime(p)){
//                for(long q = 0; q < 5000; q++){
//                    if(isPrime(q) && (p - 1) % q == 0 ){
//                        System.out.println("p: " + p + " q: " + q);
//                    }
//                }
//            }
//        }
        CryptDSAServiceImpl cryptDSAService = new CryptDSAServiceImpl();
        System.out.println(cryptDSAService.fastExp(1640, 100, 9973));
    }

    private static boolean isPrime(long inputNumber){
        boolean isItPrime = true;
        if(inputNumber <= 1) {
            isItPrime = false;
        } else {
            for (int i = 2; i <= inputNumber / 2; i++) {
                if ((inputNumber % i) == 0) {
                    isItPrime = false;
                    break;
                }
            }
        }
        return isItPrime;
    }

}
