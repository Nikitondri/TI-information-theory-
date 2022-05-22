package com.zakharenko.lab02.dal.impl;

import com.zakharenko.lab02.dal.IOFileDal;
import com.zakharenko.lab02.dal.exception.DalException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class IOFileDalImpl implements IOFileDal {
    @Override
    public byte[] readFile(String path) throws DalException {
        try {
            FileInputStream fis = new FileInputStream(path);
            return fis.readAllBytes();
        } catch (IOException e) {
            throw new DalException(e);
        }
    }

    @Override
    public void writeFile(String path, byte[] byteArr) throws DalException {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(byteArr);
        } catch (IOException e) {
            throw new DalException(e);
        }
    }
}
