package service.crypt.decryptor;

import service.exception.ServiceException;

public interface Decryptor {
    String decrypt(String sourceText, String key) throws ServiceException;
}
