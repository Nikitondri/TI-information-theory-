package com.zakharenko.lab03.service;

import com.zakharenko.lab03.service.exception.ServiceException;

import java.util.List;

public interface CryptService {
    List<Integer> encrypt(List<Integer> plainText, int n, int b) throws ServiceException;
    byte[] decrypt(List<Integer> crypt, int n, int p, int q, int b, List<List<Long>> listDec) throws ServiceException;
}
