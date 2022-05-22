package com.zakharenko.lab02.service;

import com.zakharenko.lab02.service.exception.ServiceException;

public interface FileService {
    byte[] read() throws ServiceException;
    void write(String path, byte[] bytes) throws ServiceException;
}
