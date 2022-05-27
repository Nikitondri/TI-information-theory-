package com.zakharenko.lab04.service;

import com.zakharenko.lab04.service.exception.ServiceException;

import java.util.List;

public interface CryptDSAService {
    List<String> cryptDSA(byte[] bytes, long p, long q, long h, long x, long k) throws ServiceException;
    List<String> decryptDSA(byte[] bytes, long p, long q, long h, long y) throws ServiceException;
}
