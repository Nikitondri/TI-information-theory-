package service.impl;

import dal.ReaderDal;
import dal.impl.ReaderTxtDal;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.FileService;

public class FileServiceImpl implements FileService {
    private final FileChooser fileChooser;

    public FileServiceImpl(){
        fileChooser = new FileChooser();
    }

    @Override
    public String read() {
        ReaderDal reader = new ReaderTxtDal();
        return reader.read(fileChooser.showOpenDialog(new Stage()).getPath());
    }

    @Override
    public void write(String text) {
        ReaderDal reader = new ReaderTxtDal();
        reader.write(fileChooser.showOpenDialog(new Stage()).getPath(), text);
    }


}
