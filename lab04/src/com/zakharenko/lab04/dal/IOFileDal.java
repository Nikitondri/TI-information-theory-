package com.zakharenko.lab04.dal;

import com.zakharenko.lab04.dal.exception.DalException;

public interface IOFileDal {
    String readString(String path) throws DalException;
    void writeStringFile(String path, String str) throws DalException;
    byte[] readFileToByteArr(String path) throws DalException;
    void writeBytesFile(String path, byte[] byteArr) throws DalException;
    void appendBytesFile(String path, byte[] byteArr) throws DalException;
}
