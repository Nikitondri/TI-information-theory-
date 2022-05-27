package com.zakharenko.lab04.service.impl;

import com.zakharenko.lab04.dal.IOFileDal;
import com.zakharenko.lab04.dal.exception.DalException;
import com.zakharenko.lab04.dal.impl.IOFileDalImpl;
import com.zakharenko.lab04.service.FileService;
import com.zakharenko.lab04.service.exception.ServiceException;

import java.util.Arrays;

public class FileServiceImpl implements FileService {
    private final IOFileDal ioFileDal;

    public FileServiceImpl() {
        ioFileDal = new IOFileDalImpl();
    }

    @Override
    public String readString(String path) throws ServiceException {
        try {
            byte[] bytes = ioFileDal.readFileToByteArr(path);
            return Arrays.toString(bytes);
//            return ioFileDal.readString(path);
        } catch (DalException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void writeStringFile(String path, String str) throws ServiceException {
        try {
            byte[] bytes = str.getBytes();
            ioFileDal.writeBytesFile(path, bytes);
//            ioFileDal.writeStringFile(path, str);
        } catch (DalException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public byte[] readFileToByteArr(String path) throws ServiceException {
        try {
            return ioFileDal.readFileToByteArr(path);
        } catch (DalException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void writeBytesFile(String path, byte[] byteArr) throws ServiceException {
        try {
            ioFileDal.writeBytesFile(path, byteArr);
        } catch (DalException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void appendBytesFile(String path, byte[] byteArr) throws ServiceException {
        try {
            ioFileDal.appendBytesFile(path, byteArr);
        } catch (DalException e) {
            throw new ServiceException(e);
        }
    }
}
