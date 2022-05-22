package service.crypt.decryptor.impl;

import service.crypt.decryptor.Decryptor;
import service.exception.ServiceException;

import java.util.*;

public class ColumnDecryptorImpl implements Decryptor {
//    private final Map<Character, Integer> map;
    private final List<LineSize> listSize;

    public ColumnDecryptorImpl(){
//        map = new HashMap<>();
        listSize = new ArrayList<>();
    }

    @Override
    public String decrypt(String sourceText, String sourceKeys) throws ServiceException {
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
        char[] sortKey = sort(keyArr, englishSourceText.size());
        List<LineStr> tableMap = fillMap(englishSourceText, sortKey);




//        Map<Character, List<Character>> tableMap = fillMap(englishSourceText, sortKey);
        List<List<Character>> tableList = createTableList(tableMap, keyArr);
        for(int i = 0; i < tableList.size(); i++){
            System.out.println(tableList.get(i));
        }
        System.out.println();
        List<List<Character>> transpositionTableList = transposition(tableList);
        for(int i = 0; i < transpositionTableList.size(); i++){
            System.out.println(transpositionTableList.get(i));
        }
        return collectStr(sourceTextArr, transpositionTableList, boolArr);
    }

    private List<List<Character>> createTableList(List<LineStr> tableMap, char[] key){
        List<List<Character>> result = new ArrayList<>();
        for(char ch: key){
//            result.add(tableMap.get(ch));
            for(LineStr lineStr : tableMap){
                if(lineStr.character == ch){
                    result.add(lineStr.line);
                    lineStr.character = '0';
                    break;
                }
            }
        }
        return result;
    }

    private List<LineStr> fillMap(List<Character> englishSourceText, char[] key){
        List<LineStr> result = new ArrayList<>();
        int count = 0;
        for (char c : key) {
            System.out.print(c + " ");
            List<Character> line = new ArrayList<>();
            int length = 0;
            for(LineSize lineSize : listSize){
                if(lineSize.character == c){
                    length = lineSize.size;
                    lineSize.character = '0';
                    break;
                }
            }
            System.out.println(length);
            for(int i = 0; i < length; i++){
                line.add(englishSourceText.get(count));
                count++;
            }
            result.add(new LineStr(c, line));
        }

        return result;
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

    private List<List<Character>> transposition(List<List<Character>> matrix){
        List<List<Character>> result = new ArrayList<>();
        int lineMax = 0;
        for(int i = 0; i < matrix.size(); i++){
            if(matrix.get(i).size() > lineMax){
                lineMax = matrix.get(i).size();
            }
        }
        int countMax = 0;
        for(LineSize lineSize : listSize){
            if(lineSize.size == lineMax){
                countMax++;
            }
        }
        int size = matrix.get(0).size();
        for(int i = 0; i < size; i++){
            List<Character> line = new ArrayList<>();
            int length;
            if(i < size - 1){
                length = matrix.size();
            } else {
//                length = matrix.size() - countMax;
                length = countMax;
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

    private char[] sort(char[] keyChar, int lengthText){
        char[] key = Arrays.copyOf(keyChar, keyChar.length);
        int minLength = lengthText/key.length;
        int numMaxLength = lengthText % key.length;
        int maxLength = minLength + 1;
        for(int i = 0; i < key.length; i++){
            if(i < numMaxLength){
                listSize.add(new LineSize(key[i], maxLength));
//                map.put(key[i], maxLength);
            } else{
                listSize.add(new LineSize(key[i], minLength));
//                map.put(key[i], minLength);
            }
        }
        for (LineSize lineSize : listSize){
            System.out.println(lineSize.character + " " + lineSize.size);
        }
        boolean isSort;
        for (int i = 0; i < key.length - 1; i++){
            isSort = false;
            for(int j = 0; j < key.length - 1; j++){
                if(Character.toUpperCase(key[j]) > Character.toUpperCase(key[j+1])){
                    char buf = key[j];
                    key[j] = key[j + 1];
                    key[j + 1] = buf;
                    isSort = true;
                }
            }
            if(!isSort){
                break;
            }
        }
        return key;
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

    public static class LineSize{
        public Character character;
        public Integer size;

        public LineSize(Character character, Integer size) {
            this.character = character;
            this.size = size;
        }
    }

    public static class LineStr{
        public Character character;
        public List<Character> line;

        public LineStr(Character character, List<Character> line) {
            this.character = character;
            this.line = line;
        }
    }
}
