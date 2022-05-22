package com.zakharenko.lab03.controller;

import com.zakharenko.lab03.dal.IOFileDal;
import com.zakharenko.lab03.dal.exception.DalException;
import com.zakharenko.lab03.dal.impl.IOFileDalImpl;
import com.zakharenko.lab03.service.CryptService;
import com.zakharenko.lab03.service.exception.ServiceException;
import com.zakharenko.lab03.service.impl.RabinCryptServiceImpl;

import java.util.ArrayList;
import java.util.List;


public class Runner {
    public static void main(String[] args) throws DalException, ServiceException {
//        List<Integer> list = new ArrayList<>();
//        list.add(10);
//        list.add(15);
//        list.add(25);
        IOFileDal ioFileDal = new IOFileDalImpl();
//        ioFileDal.writeListIntegerToFile("src/resources/testList", list);
        ioFileDal.writeListIntegerToFile("src/resources/testList", null);

//        System.out.println(ioFileDal.readListIntegerFromFile("src/resources/testList"));
    }
}
