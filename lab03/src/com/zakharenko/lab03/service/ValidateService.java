package com.zakharenko.lab03.service;

public interface ValidateService {
    boolean validatePrivateKey(int p, int q);
    boolean validatePublicKey(int n, int b);
}
