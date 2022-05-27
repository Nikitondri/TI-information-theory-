package com.zakharenko.lab04.service;

import com.zakharenko.lab04.service.exception.ServiceException;

public interface ValidateService {
    boolean validateCrypt(long p, long q, long h, long x, long k);
    boolean validateDecrypt(long p, long q, long h);
}
