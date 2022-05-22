package service;

import service.exception.ServiceException;

public interface CryptService {
    String encryptColumn(String sourceText, String key) throws ServiceException;
    String decryptColumn(String sourceText, String key) throws ServiceException;
    String encryptVigenere(String sourceText, String key) throws ServiceException;
    String decryptVigenere(String sourceText, String key) throws ServiceException;
    String encryptDecimation(String sourceText, String key) throws ServiceException;
    String decryptDecimation(String sourceText, String key) throws ServiceException;

}
