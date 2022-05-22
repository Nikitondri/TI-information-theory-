package com.zakharenko.lab03.service.impl;

import com.zakharenko.lab03.service.ConvertService;
import com.zakharenko.lab03.service.CryptService;
import com.zakharenko.lab03.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;


public class RabinCryptServiceImpl implements CryptService {

    ConvertService convertService;

    public RabinCryptServiceImpl() {
        convertService = new ConvertServiceImpl();
    }

    @Override
    public List<Integer> encrypt(List<Integer> plainText, int n, int b) throws ServiceException {
//        StringBuilder result = new StringBuilder();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < plainText.size(); i++) {
            result.add((plainText.get(i) * (plainText.get(i) + b)) % n);
        }
        return result;
    }

    @Override
    public byte[] decrypt(List<Integer> crypt, int n, int p, int q, int b, List<List<Long>> listDec) throws ServiceException {
        StringBuilder builder = new StringBuilder();

        byte[] resultBytes = new byte[crypt.size()];
        int[] roots = euclidAlg(p, q);
        for(int i = 0; i < crypt.size(); i++){

            long d = (((long) b * b % n) + (4L * crypt.get(i)) % n) % n;
            long mP = fastExp(d, (p + 1) / 4, p);
            long mQ = fastExp(d, (q + 1) / 4, q);
            long d1 = (roots[1] * p * mQ + roots[2] * q * mP) % n;
            long d2 = n - d1;
            long d3 = (roots[1] * p * mQ - roots[2] * q * mP) % n;
            long d4 = n - d3;
            long[] arrD = new long[]{d1, d2, d3, d4};

            listDec.add(new ArrayList<>());

            for(long dI : arrD){
                long m;
                if (((dI - b) % 2) == 0) {
                    m = ((dI - b) / 2) % n;
                } else {
                    m = ((dI - b + n) / 2) % n;
                }

                listDec.get(listDec.size() - 1).add(m);

                if(m >= 0 && m < 256){
                    resultBytes[i] = (byte) m;
                    builder.append(resultBytes[i]).append(" ");
                }
            }
        }
        return resultBytes;
    }

    public int[] euclidAlg(int a, int b) {
        int[] GCD = new int[]{a, b};
        int[] arrA = new int[]{1, 0};
        int[] arrB = new int[]{0, 1};

        while (GCD[1] != 0) {
            int quotient = GCD[0] / GCD[1];
            arrA = new int[]{arrA[1], arrA[0] - quotient * arrA[1]};
            arrB = new int[]{arrB[1], arrB[0] - quotient * arrB[1]};
            GCD = new int[]{GCD[1], GCD[0] % GCD[1]};
        }

        return new int[]{GCD[0], arrA[0], arrB[0]};
    }

    public long fastExp(long number, int power, int modulo) {
        long res = 1;
        while (power != 0) {
            while (power % 2 == 0) {
                power /= 2;
                number = (number * number) % modulo;
                System.out.println();
            }
            power--;
            res = (res * number) % modulo;
            System.out.println();
        }
        return res;

    }


}
