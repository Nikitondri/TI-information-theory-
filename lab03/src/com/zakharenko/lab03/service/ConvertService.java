package com.zakharenko.lab03.service;

import com.zakharenko.lab03.service.exception.ServiceException;

import java.util.List;
import java.util.ServiceConfigurationError;

public interface ConvertService {
    int[] convertByteArrToIntArr(byte[] byteArr) throws ServiceException;
    String convertListIntToString(List<Integer> list);
    String convertListListLongToString(List<List<Long>> listDec);
}
