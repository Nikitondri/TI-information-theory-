package service.crypt.encryptor.impl;

import service.crypt.encryptor.Encryptor;
import service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ColumnEncryptorImpl implements Encryptor {
    @Override
    public String encrypt(String sourceText, String sourceKeys) throws ServiceException {
        if(sourceKeys.isEmpty() || sourceText.isEmpty()){
            throw new ServiceException();
        }
        String sourceKey = sourceKeys.toUpperCase(Locale.ROOT);
        String key = sourceKey.replaceAll("[^a-zA-Z]", "");
        char[] sourceTextArr = sourceText.toCharArray();
        char[] keyArr = new char[Math.min(key.length(), sourceText.length())];

        if(key.length() > sourceText.length()){
            key.getChars(0, sourceText.length() - 1, keyArr, 0);
        } else {
            keyArr = key.toCharArray();
        }
        boolean[] boolArr = new boolean[sourceText.length()];
        List<Character> englishSourceText = createEnglishSourceText(boolArr, sourceTextArr);
        List<List<Character>> tableList = createTableList(englishSourceText, keyArr.length);
        List<List<Character>> result = transposition(tableList);
        List<List<Character>> resultSort = sort(keyArr, result);
        return collectStr(sourceTextArr, resultSort, boolArr);
    }

    private List<Character> createEnglishSourceText(boolean[] boolArr, char[] sourceTextArr){
        List<Character> englishSourceText = new ArrayList<>();
        for(int i = 0; i < sourceTextArr.length; i++) {
            if (('a' <= sourceTextArr[i] && sourceTextArr[i] <= 'z')||('A' <= sourceTextArr[i] && sourceTextArr[i] <= 'Z')){
                englishSourceText.add(sourceTextArr[i]);
                boolArr[i] = true;
            } else {
                boolArr[i] = false;
            }
        }
        return englishSourceText;
    }

    private List<List<Character>> createTableList(List<Character> englishSourceText, int length){
        List<List<Character>> tableList = new ArrayList<>();
        List<Character> lineText = new ArrayList<>();
        int i = 0;
        for(char ch : englishSourceText){
            lineText.add(ch);
            i++;
            if(i == length){
                tableList.add(new ArrayList<>(lineText));
                i = 0;
                lineText = new ArrayList<>();
            }
        }
        if(i != 5){
            tableList.add(new ArrayList<>(lineText));
        }
        return tableList;
    }

    private List<List<Character>> transposition(List<List<Character>> matrix){
        List<List<Character>> result = new ArrayList<>();
        int size = matrix.get(0).size();
        int lengthEndLine = matrix.get(matrix.size() - 1).size();
        for(int i = 0; i < size; i++){
            List<Character> line = new ArrayList<>();
            int length;
            if(i < lengthEndLine){
                length = matrix.size();
            } else {
                length = matrix.size() - 1;
            }
            for(int j = 0; j < length; j++){
                if (matrix.get(j).get(i) != null) {
                    line.add(matrix.get(j).get(i));
                }
            }
            result.add(line);
        }
        return result;
    }

    private List<List<Character>> sort(char[] key, List<List<Character>> matrix){
        List<List<Character>> result = new ArrayList<>(matrix);
        boolean isSort;
        for (int i = 0; i < key.length - 1; i++){
            isSort = false;
            for(int j = 0; j < key.length - 1; j++){
                if(key[j] > key[j+1]){
                    char buf = key[j];
                    key[j] = key[j + 1];
                    key[j + 1] = buf;
                    if(result.size() > j + 1) {
                        Collections.swap(result, j, j + 1);
                    }
                    isSort = true;
                }
            }
            if(!isSort){
                break;
            }
        }
        return result;
    }



    private String collectStr(char[] sourceTextArr, List<List<Character>> resultSort, boolean[] boolArr){
        List<Character> finalStr = new ArrayList<>();
        for(List<Character> list : resultSort){
            finalStr.addAll(list);
        }
        StringBuilder stringBuilder = new StringBuilder();
        int j = 0;
        for(int i = 0; i < sourceTextArr.length; i++){
            if(boolArr[i]){
                stringBuilder.append(finalStr.get(j));
                j++;
            } else {
                stringBuilder.append(sourceTextArr[i]);
            }
        }
        return stringBuilder.toString();
    }
}
