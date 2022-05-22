package dal.impl;

import dal.ReaderDal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReaderTxtDal implements ReaderDal {
    @Override
    public String read(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void write(String path, String text) {
        try {
            Files.writeString(Path.of(path), text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
