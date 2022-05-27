package com.zakharenko.lab04.service;

import com.zakharenko.lab04.dal.exception.DalException;
import com.zakharenko.lab04.service.exception.ServiceException;

public interface FileService {
    String readString(String path) throws ServiceException;
    void writeStringFile(String path, String str) throws ServiceException;
    byte[] readFileToByteArr(String path) throws ServiceException;
    void writeBytesFile(String path, byte[] byteArr) throws ServiceException;
    void appendBytesFile(String path, byte[] byteArr) throws ServiceException;
}
