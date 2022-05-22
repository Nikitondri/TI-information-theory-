package com.zakharenko.lab02.service.impl;

import com.zakharenko.lab02.dal.IOFileDal;
import com.zakharenko.lab02.dal.exception.DalException;
import com.zakharenko.lab02.dal.impl.IOFileDalImpl;
import com.zakharenko.lab02.service.FileService;
import com.zakharenko.lab02.service.exception.ServiceException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileServiceImpl implements FileService {
    private final FileChooser fileChooser;

    public FileServiceImpl(){
        fileChooser = new FileChooser();
    }

    @Override
    public byte[] read() throws ServiceException {
        IOFileDal fileDal = new IOFileDalImpl();
        try {
            return fileDal.readFile(fileChooser.showOpenDialog(new Stage()).getPath());
        } catch (DalException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void write(String path, byte[] bytes) throws ServiceException {
        IOFileDal fileDal = new IOFileDalImpl();
        try {
            fileDal.writeFile("src/resources/" + path, bytes);
        } catch (DalException e) {
            throw new ServiceException(e);
        }
    }
}
