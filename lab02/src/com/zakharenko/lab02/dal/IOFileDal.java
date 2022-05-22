package com.zakharenko.lab02.dal;

import com.zakharenko.lab02.dal.exception.DalException;

public interface IOFileDal {
    byte[] readFile(String path) throws DalException;
    void writeFile(String path, byte[] byteArr) throws DalException;
}
