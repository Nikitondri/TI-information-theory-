package service.crypt.encryptor.impl;

import service.crypt.AbstractVigenereCrypt;
import service.crypt.encryptor.Encryptor;
import service.exception.ServiceException;

import java.util.*;

public class VigenereEncryptorImpl extends AbstractVigenereCrypt implements Encryptor {

    @Override
    public String encrypt(String sourceText, String key) throws ServiceException {

        String str = sourceText.toUpperCase(Locale.ROOT);
        String keyUpper = key.toUpperCase(Locale.ROOT);
        String k = getValidKey(keyUpper);
        System.out.println(k);
//        if(k.isEmpty() || sourceText.isEmpty()){
//            throw new ServiceException();
//        }

        var keywordIndex = 0;
        StringBuilder result = new StringBuilder();
        for(char symbol : str.toCharArray()){
            if(list.contains(symbol)){
                int c = (list.indexOf(symbol) + (list.indexOf(k.toCharArray()[keywordIndex]))) % 33;
                result.append(list.get(c));
                keywordIndex++;
                if (keywordIndex == k.length()) {
                    k = convertKey(k);
                    keywordIndex = 0;
                }
            } else {
                result.append(symbol);
            }
        }
        return result.toString();
    }
}
