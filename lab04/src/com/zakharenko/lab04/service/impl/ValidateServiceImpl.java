package com.zakharenko.lab04.service.impl;

import com.zakharenko.lab04.service.ValidateService;

public class ValidateServiceImpl implements ValidateService {

    @Override
    public boolean validateCrypt(long p, long q, long h, long x, long k) {
        return isCorrectP(p, q) && isCorrectQ(q) && isCorrectH(h, p) && isCorrectX(x, q) && isCorrectK(k, q);
    }

    @Override
    public boolean validateDecrypt(long p, long q, long h) {
        return isCorrectP(p, q) && isCorrectQ(q) && isCorrectH(h, p);
    }

    private boolean isCorrectQ(long q){
        return isPrime(q);
    }

    private boolean isCorrectP(long p, long q){
        return isPrime(p) && (p - 1) % q == 0;
    }

    private boolean isCorrectH(long h, long p){
        return h > 1 &&  h < (p - 1);
    }

    private boolean isCorrectX(long x, long q){
        return x > 0 && x < q;
    }

    private boolean isCorrectK(long k, long q){
        return k > 0 && k < q;
    }

    private boolean isPrime(long inputNumber) {
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
