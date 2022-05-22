package dal;

public interface ReaderDal {
    String read(String path);
    void write(String path, String text);
}
