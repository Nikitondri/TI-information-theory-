package com.zakharenko.lab04.service.impl;

import com.zakharenko.lab04.dal.IOFileDal;
import com.zakharenko.lab04.dal.exception.DalException;
import com.zakharenko.lab04.dal.impl.IOFileDalImpl;
import com.zakharenko.lab04.service.CryptDSAService;
import com.zakharenko.lab04.service.exception.ServiceException;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CryptDSAServiceImpl implements CryptDSAService {

    private static final long START_H = 100;
    private final IOFileDal ioFileDal;

    public CryptDSAServiceImpl() {
        ioFileDal = new IOFileDalImpl();
    }

    @Override
    public List<String> cryptDSA(byte[] plaintext, long p, long q, long h, long x, long k) throws ServiceException {
        List<String> result = new ArrayList<>();
//        byte[] plaintext = inputText.getBytes();
        long g = fastExp(h, (p - 1) / q, p);
        long y = fastExp(g, x, p);
        long hash = findHash(plaintext, q, START_H);
        long r = fastExp(g, k, p) % q;
//        long s = (1 / k) * (hash + x * r) % q;
        long s = (fastExp(k, q-2, q)*((hash+(x*r))) % q) % q;
        if(r == 0 || s == 0){
            throw new ServiceException("Not correct K");
        }
        result.add("inputText");
        result.add(Long.toString(r));
        result.add(Long.toString(s));
        result.add(Long.toString(y));
        result.add(Long.toString(hash));
        return result;
    }


    @Override
    public List<String> decryptDSA(byte[] bytes, long p, long q, long h, long y) throws ServiceException {
        String cryptText = new String(bytes);
//        List<String> cryptList = new ArrayList<>(Arrays.asList(cryptText.split("[\n ]")));
        List<String> cryptList = new ArrayList<>(Arrays.asList(cryptText.split(" ")));
        String str = " " + cryptList.get(cryptList.size() - 1) + " " + cryptList.get(cryptList.size() - 2);
        int addedSize = str.getBytes().length;
        int startSize = bytes.length - addedSize;
        int size = cryptList.size();
        long r;
        long s;
        try {
            r = Long.parseLong(cryptList.get(size - 2));
            s = Long.parseLong(cryptList.get(size - 1));
        } catch (Exception e){
            throw new ServiceException(e);
        }
//        String cryptStr = findStr(cryptList);
//        byte[] bytesArr = cryptStr.getBytes();
        long hash = findHash1(bytes, q, START_H, startSize);
        long g = fastExp(h, (p - 1) / q, p);

        long w = fastExp(s, q - 2, q);
        long u1 = (hash * w) % q;
        long u2 = (r * w) % q;
        long v = ((fastExp(g, u1, p) * fastExp(y, u2, p)) % p) % q;

        List<String> result = new ArrayList<>();
        result.add(Long.toString(v));
        result.add(Long.toString(r));
        result.add(Long.toString(w));
        result.add(Long.toString(u1));
        result.add(Long.toString(u2));

        return result;
    }

//    private String findStr(List<String> cryptList){
//        int size = cryptList.size();
//        cryptList.remove(size - 1);
//        cryptList.remove(size - 2);
//        StringBuilder cryptStrBuilder = new StringBuilder();
////        for(String str : cryptList){
////            cryptStrBuilder.append(str).append(" ");
////        }
//        for(int i = 0; i < cryptList.size(); i++){
//            cryptStrBuilder.append(cryptList.get(i));
//            if(i != cryptList.size() - 1){
//                cryptStrBuilder.append(" ");
//            }
//        }
//        return cryptStrBuilder.toString();
//    }

    public long fastExp(long number, long power, long modulo) {
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

    private long findHash(byte[] bytes, long n, long h){
        for (int i = 0; i < bytes.length; i++){
            h = fastExp(h + bytes[i], 2, n);
        }
        return h;
    }
    private long findHash1(byte[] bytes, long n, long h, int size){
        for (int i = 0; i < size; i++){
            h = fastExp(h + bytes[i], 2, n);
        }
        return h;
    }
}
