package service.crypt.encryptor;

import service.exception.ServiceException;

@FunctionalInterface
public interface Encryptor {
    String encrypt(String sourceText, String key) throws ServiceException;
}
