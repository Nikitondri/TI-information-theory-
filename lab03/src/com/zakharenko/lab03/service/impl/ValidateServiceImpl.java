package com.zakharenko.lab03.service.impl;

import com.zakharenko.lab03.service.ValidateService;

public class ValidateServiceImpl implements ValidateService {
    @Override
    public boolean validatePrivateKey(int p, int q) {
        if(p == q){
            return false;
        }
        if(p % 4 != 3 || q % 4 != 3){
            return false;
        }
        if(!isPrime(p) || !isPrime(q)){
            return false;
        }
        return isMutuallyPrime(p, q);
    }



    @Override
    public boolean validatePublicKey(int n, int b) {
//        int b;
//        try{
//            b = Integer.parseInt(stringB);
//        } catch (Exception e){
//            return false;
//        }
//        if(n < 256){
//            return false;
//        }
        return b < n;
    }

    private boolean isPrime(int inputNumber) {
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

    private boolean isMutuallyPrime(long n1, long n2) {
        return (gcdByEuclid(n1, n2) == 1);
    }

    private long gcdByEuclid(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}
