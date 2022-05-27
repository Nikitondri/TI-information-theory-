package com.zakharenko.lab04.dal.impl;

import com.zakharenko.lab04.dal.IOFileDal;
import com.zakharenko.lab04.dal.exception.DalException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class IOFileDalImpl implements IOFileDal {
    @Override
    public byte[] readFileToByteArr(String path) throws DalException {
        try {
            FileInputStream fis = new FileInputStream(path);
            byte[] bytes = fis.readAllBytes();
            fis.close();
            return bytes;
        } catch (IOException e) {
            throw new DalException(e);
        }
    }

    @Override
    public String readString(String path) throws DalException {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            throw new DalException(e);
        }
    }

    @Override
    public void writeStringFile(String path, String str) throws DalException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            throw new DalException(e);
        }
    }

    @Override
    public void writeBytesFile(String path, byte[] byteArr) throws DalException {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(byteArr);
            fos.close();
        } catch (IOException e) {
            throw new DalException(e);
        }
    }

    @Override
    public void appendBytesFile(String path, byte[] byteArr) throws DalException {
        try {
            FileOutputStream fos= new FileOutputStream(new File(path), true);
            fos.write(byteArr, 0, byteArr.length);
            fos.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
