package service.crypt.decryptor.impl;

import service.crypt.AbstractVigenereCrypt;
import service.crypt.decryptor.Decryptor;
import service.exception.ServiceException;

import java.util.Locale;

public class VigenereDecryptorImpl extends AbstractVigenereCrypt implements Decryptor {
    @Override
    public String decrypt(String sourceText, String key) throws ServiceException {
        if(key.isEmpty() || sourceText.isEmpty()){
            throw new ServiceException();
        }
        String str = sourceText.toUpperCase(Locale.ROOT);
        var k = key.toUpperCase(Locale.ROOT);
        k = getValidKey(k);
//        if(k.isEmpty() || sourceText.isEmpty()){
//            throw new ServiceException();
//        }
        int keywordIndex = 0;
        StringBuilder result = new StringBuilder();
        for(char symbol : str.toCharArray()){
            if(list.contains(symbol)){
                int c = (list.indexOf(symbol) +  33 - (list.indexOf(k.toCharArray()[keywordIndex]))) % 33;
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
