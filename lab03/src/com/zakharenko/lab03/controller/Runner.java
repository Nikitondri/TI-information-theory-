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
        String str = "test\n adawdaw  adawda \n qdawdaw213213 asd \n23 34";
        String[] test = str.split("[\n ]");
        System.out.println(test);
    }
}
