package service.crypt.encryptor.impl;

import service.crypt.AbstractDecimationCrypt;
import service.crypt.encryptor.Encryptor;
import service.exception.ServiceException;

public class DecimationEncryptorImpl extends AbstractDecimationCrypt implements Encryptor {
    @Override
    public String encrypt(String sourceText, String sourceKey) throws ServiceException {
        if(sourceKey.isEmpty() || sourceText.isEmpty()){
            throw new ServiceException();
        }
        String key = sourceKey.replaceAll("[^0-9]", "");

        int inputIndex;
        int outputIndex;
        char outputSymbol;
        char inputSymbol;
        String outputSymbolString;
        String inputSymbolString;

        int keyInt;
        try {
            keyInt = Integer.parseInt(key);
        } catch (NumberFormatException e) {
            throw new ServiceException(e);
        }
        if(gcd(keyInt, 26) != 1){
            throw new ServiceException();
        }
        StringBuilder resultTextField = new StringBuilder();
        for (int i = 0; i < sourceText.length(); i++) {
            inputSymbol = sourceText.charAt(i);
            if((inputSymbol >= 'A' && 'Z' >= inputSymbol) || (inputSymbol >= 'a' && 'z' >= inputSymbol)) {
                if (Character.isLowerCase(inputSymbol)) {
                    if (alphabetLowerCase.indexOf(inputSymbol) != -1) {
                        if (Character.isWhitespace(inputSymbol)) {
                            resultTextField.append(" ");
                        } else {
                            inputIndex = alphabetLowerCase.indexOf(inputSymbol);
                            outputIndex = (inputIndex * keyInt) % 26;
                            outputSymbol = alphabetLowerCase.charAt(outputIndex);
                            outputSymbolString = Character.toString(outputSymbol);
                            resultTextField.append(outputSymbolString);
                        }
                    } else {
                        inputSymbolString = Character.toString(inputSymbol);
                        resultTextField.append(inputSymbolString);
                    }
                }

                if (Character.isUpperCase(inputSymbol)) {
                    if (alphabetUpperCase.indexOf(inputSymbol) != -1) {
                        if (Character.isWhitespace(inputSymbol)) {
                            resultTextField.append(" ");
                        } else {
                            inputIndex = alphabetUpperCase.indexOf(inputSymbol);
                            outputIndex = (inputIndex * keyInt) % 26;
                            outputSymbol = alphabetUpperCase.charAt(outputIndex);
                            outputSymbolString = Character.toString(outputSymbol);
                            resultTextField.append(outputSymbolString);
                        }
                    } else {
                        inputSymbolString = Character.toString(inputSymbol);
                        resultTextField.append(inputSymbolString);
                    }
                }
                if (Character.isDigit(inputSymbol)) {
                    inputSymbolString = Character.toString(inputSymbol);
                    resultTextField.append(inputSymbolString);
                }
            } else {
                resultTextField.append(inputSymbol);
            }
        }
        return resultTextField.toString();
    }

    private int gcd(int a, int b) {
        int c;
        while (b > 0) {
            c = a % b;
            a = b;
            b = c;
        }
        return Math.abs(a);
    }
}
