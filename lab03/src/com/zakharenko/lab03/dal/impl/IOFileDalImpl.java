package com.zakharenko.lab03.dal.impl;

import com.zakharenko.lab03.dal.IOFileDal;
import com.zakharenko.lab03.dal.exception.DalException;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    public List<Integer> readFileToIntArr(String path) throws DalException {
        List<Integer> result = new ArrayList<>();
        int buf;
        try {
            InputStream inputStream = new FileInputStream(path);
            while ((buf = inputStream.read()) != -1){
                result.add(buf);
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            throw new DalException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Integer> readFileToIntList(String path) throws DalException {
        List<Integer> result = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new FileInputStream(path));
            while (scanner.hasNextInt()){
                result.add(scanner.nextInt());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new DalException(e);
        }
        return result;
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
    public String readString(String path) throws DalException {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            throw new DalException(e);
        }
    }

    @Override
    public void openFile(String path) throws DalException {
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(new File(path));
        } catch (IOException e) {
            throw new DalException(e);
        }
    }

    @Override
    public void writeListIntegerToFile(String path, List<Integer> list) throws DalException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);
            objOutputStream.writeObject(list);
            byte[] byteArr = outputStream.toByteArray();
            outputStream.close();
            objOutputStream.close();
            writeBytesFile(path, byteArr);
        } catch (IOException e) {
            throw new DalException(e);
        }
    }

    @Override
    public List<Integer> readListIntegerFromFile(String path) throws DalException {
        byte[] byteArr = readFileToByteArr(path);
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArr);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Object obj = objectInputStream.readObject();
            objectInputStream.close();
            inputStream.close();
            return (List<Integer>) obj;
        } catch (IOException | ClassNotFoundException e) {
            throw new DalException(e);
        }
    }
}
