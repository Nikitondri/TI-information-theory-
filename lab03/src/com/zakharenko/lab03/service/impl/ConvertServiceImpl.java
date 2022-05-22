package com.zakharenko.lab03.service.impl;

import com.zakharenko.lab03.service.ConvertService;
import com.zakharenko.lab03.service.exception.ServiceException;

import java.util.List;

public class ConvertServiceImpl implements ConvertService {
    @Override
    public int[] convertByteArrToIntArr(byte[] byteArr) throws ServiceException {
        int[] arrInt = new int[byteArr.length];
        for(int i = 0; i < byteArr.length; i++){
            arrInt[i] = byteArr[i];
        }
        return arrInt;
    }

    @Override
    public String convertListIntToString(List<Integer> list) {
        StringBuilder result = new StringBuilder();
        for(Integer el : list){
            result.append(el).append(" ");
        }
        return result.toString();
    }

    @Override
    public String convertListListLongToString(List<List<Long>> listDec) {
        StringBuilder stringBuilder = new StringBuilder();
        for(List<Long> list : listDec){
            for(Long el : list){
                stringBuilder.append(el).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
