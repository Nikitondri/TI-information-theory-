package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import service.CryptService;
import service.FileService;
import service.exception.ServiceException;
import service.impl.CryptServiceImpl;
import service.impl.FileServiceImpl;
import util.MethodEnum;


public class Controller {

    @FXML
    public SplitMenuButton buttonActon;
    @FXML
    public MenuButton fileMenuButton;
    @FXML
    public MenuButton methodMenuButton;
    @FXML
    public TextArea sourceText;
    @FXML
    public TextField keyText;
    @FXML
    public TextArea resultText;

    private boolean isEncrypt = true;
    private MethodEnum method = MethodEnum.COLUMN;

    @FXML
    public void initialize(){
        sourceText.setFont(new Font(30 ));
        resultText.setFont(new Font(30));
        FileService fileService = new FileServiceImpl();
        MenuItem fileOpenItem = new MenuItem("Open file");
        MenuItem fileSaveItem = new MenuItem("Save file");
        fileMenuButton.getItems().remove(0);
        fileMenuButton.getItems().remove(0);
        fileMenuButton.getItems().addAll(fileOpenItem, fileSaveItem);
        fileOpenItem.setOnAction((event) -> {
            System.out.println("Open File");
            sourceText.setText(fileService.read());
        });
        fileSaveItem.setOnAction((event) -> {
            System.out.println("Save File");
            fileService.write(resultText.getText());
        });


        MenuItem encryptItem = new MenuItem("Encrypt");
        MenuItem decryptItem = new MenuItem("Decrypt");
        buttonActon.getItems().remove(0);
        buttonActon.getItems().remove(0);
        buttonActon.setText("Encrypt");
        buttonActon.getItems().addAll(encryptItem, decryptItem);
        encryptItem.setOnAction((event) -> {
            buttonActon.setText("Encrypt");
            isEncrypt = true;
        });
        decryptItem.setOnAction((event) -> {
            buttonActon.setText("Decrypt");
            isEncrypt = false;
        });

        MenuItem columnMethodItem = new MenuItem("Column method");
        MenuItem vigenereMethodItem = new MenuItem("Vinegere method");
        MenuItem decimationMethodItem = new MenuItem("Decimation method");
        methodMenuButton.getItems().remove(0);
        methodMenuButton.getItems().remove(0);
        methodMenuButton.setText("Column method");
        methodMenuButton.getItems().addAll(columnMethodItem, vigenereMethodItem, decimationMethodItem);
        columnMethodItem.setOnAction((event) -> {
            methodMenuButton.setText("Column method");
            method = MethodEnum.COLUMN;
        });
        vigenereMethodItem.setOnAction((event) -> {
            methodMenuButton.setText("Vinegere method");
            method = MethodEnum.VIGENERE;
        });
        decimationMethodItem.setOnAction((event) -> {
            methodMenuButton.setText("Decimation method");
            method = MethodEnum.DECIMATION;
        });
    }


    public void buttonActionClicked(ActionEvent actionEvent) {
        CryptService cryptService = new CryptServiceImpl();
        try {
            switch (method) {
                case COLUMN -> {
                    if (isEncrypt) {
                        System.out.println("Encrypt COLUMN");
                        resultText.setText(cryptService.encryptColumn(sourceText.getText(), keyText.getText()));
                    } else {
                        System.out.println("Decrypt COLUMN");
                        resultText.setText(cryptService.decryptColumn(sourceText.getText(), keyText.getText()));
                    }
                }
                case VIGENERE -> {
                    if (isEncrypt) {
                        System.out.println("Encrypt VIGENERE");
                        resultText.setText(cryptService.encryptVigenere(sourceText.getText(), keyText.getText()));
                    } else {
                        System.out.println("Decrypt VIGENERE");
                        resultText.setText(cryptService.decryptVigenere(sourceText.getText(), keyText.getText()));
                    }
                }
                case DECIMATION -> {
                    if (isEncrypt) {
                        System.out.println("Encrypt DECIMATION");
                        resultText.setText(cryptService.encryptDecimation(sourceText.getText(), keyText.getText()));
                    } else {
                        System.out.println("Decrypt DECIMATION");
                        resultText.setText(cryptService.decryptDecimation(sourceText.getText(), keyText.getText()));
                    }
                }
            }
        } catch (ServiceException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error fields");
            alert.setHeaderText("Error fields");
            alert.setContentText("Please? fill correct fields!");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }
    }
}
