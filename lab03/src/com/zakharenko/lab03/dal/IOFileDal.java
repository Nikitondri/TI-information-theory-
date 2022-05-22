package com.zakharenko.lab03.dal;

import com.zakharenko.lab03.dal.exception.DalException;

import java.util.List;

public interface IOFileDal {
    byte[] readFileToByteArr(String path) throws DalException;
    List<Integer> readFileToIntList(String path) throws DalException;
    String readString(String path) throws DalException;
    void writeBytesFile(String path, byte[] byteArr) throws DalException;
    void writeStringFile(String path, String str) throws DalException;
    List<Integer> readFileToIntArr(String path) throws DalException;
    void openFile(String path) throws DalException;
    void writeListIntegerToFile(String path, List<Integer> list) throws DalException;
    List<Integer> readListIntegerFromFile(String path) throws DalException;
}
