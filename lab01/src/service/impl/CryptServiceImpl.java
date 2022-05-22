package service.impl;

import service.CryptService;
import service.crypt.decryptor.Decryptor;
import service.crypt.decryptor.impl.ColumnDecryptorImpl;
import service.crypt.decryptor.impl.DecimationDecryptorImpl;
import service.crypt.decryptor.impl.VigenereDecryptorImpl;
import service.crypt.encryptor.Encryptor;
import service.crypt.encryptor.impl.ColumnEncryptorImpl;
import service.crypt.encryptor.impl.DecimationEncryptorImpl;
import service.crypt.encryptor.impl.VigenereEncryptorImpl;
import service.exception.ServiceException;

public class CryptServiceImpl implements CryptService {
    @Override
    public String encryptColumn(String sourceText, String key) throws ServiceException {
        Encryptor encryptor = new ColumnEncryptorImpl();
        return encryptor.encrypt(sourceText, key);
    }

    @Override
    public String decryptColumn(String sourceText, String key) throws ServiceException {
        Decryptor decryptor = new ColumnDecryptorImpl();
        return decryptor.decrypt(sourceText, key);
    }

    @Override
    public String encryptVigenere(String sourceText, String key) throws ServiceException {
        Encryptor encryptor = new VigenereEncryptorImpl();
        return encryptor.encrypt(sourceText, key);
    }

    @Override
    public String decryptVigenere(String sourceText, String key) throws ServiceException {
        Decryptor decryptor = new VigenereDecryptorImpl();
        return decryptor.decrypt(sourceText, key);
    }

    @Override
    public String encryptDecimation(String sourceText, String key) throws ServiceException {
        Encryptor encryptor = new DecimationEncryptorImpl();
        return encryptor.encrypt(sourceText, key);
    }

    @Override
    public String decryptDecimation(String sourceText, String key) throws ServiceException {
        Decryptor decryptor = new DecimationDecryptorImpl();
        return decryptor.decrypt(sourceText, key);
    }
}
