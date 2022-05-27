package com.zakharenko.lab04.controller;

import com.zakharenko.lab04.service.CryptDSAService;
import com.zakharenko.lab04.service.FileService;
import com.zakharenko.lab04.service.ValidateService;
import com.zakharenko.lab04.service.exception.ServiceException;
import com.zakharenko.lab04.service.impl.CryptDSAServiceImpl;
import com.zakharenko.lab04.service.impl.FileServiceImpl;
import com.zakharenko.lab04.service.impl.ValidateServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public TextField fileInput;
    @FXML
    public TextArea inputArea;
    @FXML
    public Button cryptButton;
    @FXML
    public Button fileButton;
    @FXML
    public Button decryptButton;
    @FXML
    public TextField cryptPEdit;
    @FXML
    public TextField cryptQEdit;
    @FXML
    public TextField cryptHEdit;
    @FXML
    public TextField cryptXEdit;
    @FXML
    public TextField cryptKEdit;
    @FXML
    public TextField decryptPEdit;
    @FXML
    public TextField decryptQEdit;
    @FXML
    public TextField decryptYEdit;
    @FXML
    public Label cryptRLabel;
    @FXML
    public Label cryptSLabel;
    @FXML
    public Label cryptHashLabel;
    @FXML
    public Label decryptWLabel;
    @FXML
    public Label decryptU1Label;
    @FXML
    public Label decryptU2Label;
    @FXML
    public Label decryptVLabel;
    @FXML
    public TextField decryptHEdit;
    private final CryptDSAService cryptDSAService = new CryptDSAServiceImpl();
    private final ValidateService validateService = new ValidateServiceImpl();
    private final FileService fileService = new FileServiceImpl();
    private final FileChooser fileChooser = new FileChooser();
    private byte[] bytes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inputArea.setWrapText(true);
        cryptButton.setOnAction(actionEvent -> cryptButtonAction());
        fileButton.setOnAction(actionEvent -> fileButtonAction());
        decryptButton.setOnAction(actionEvent -> decryptButtonAction());
    }

    private void decryptButtonAction(){
        long q;
        long p;
        long h;
        long y;
        try{
            q = Long.parseLong(decryptQEdit.getText());
            p = Long.parseLong(decryptPEdit.getText());
            h = Long.parseLong(decryptHEdit.getText());
            y = Long.parseLong(decryptYEdit.getText());
            if(validateService.validateDecrypt(p, q, h)){
                List<String> list = cryptDSAService.decryptDSA(bytes, p, q, h, y);
                if(list.get(0).equals(list.get(1))){
                    showMessage("the signature is authentic");
                } else {
                    showMessage("the signature is NOT authentic");
                }
                decryptWLabel.setText(list.get(2));
                decryptU1Label.setText(list.get(3));
                decryptU2Label.setText(list.get(4));
                decryptVLabel.setText(list.get(0));
            } else {
                showMessage("validate exception");
            }
        } catch (Exception e){
            showMessage("parse error");
        }
    }

    private void fileButtonAction(){
        String path = "";
        try {
            path = fileChooser.showOpenDialog(new Stage()).getPath();
            try {
//                String inputText = fileService.readString(path);
                bytes = fileService.readFileToByteArr(path);
                inputArea.setText(Arrays.toString(bytes));
            } catch (ServiceException e) {
                showMessage("Error input file");
            }
        } catch (Exception e){
            showMessage("File not selected");
        }
    }

    private void cryptButtonAction(){
//        String path = fileChooser.showOpenDialog(new Stage()).getPath();
        long q;
        long p;
        long h;
        long x;
        long k;
        try{
            q = Long.parseLong(cryptQEdit.getText());
            p = Long.parseLong(cryptPEdit.getText());
            h = Long.parseLong(cryptHEdit.getText());
            x = Long.parseLong(cryptXEdit.getText());
            k = Long.parseLong(cryptKEdit.getText());
            if(validateService.validateCrypt(p, q, h, x, k)){
                try {
//                String inputText = fileService.readString(path);
//                inputArea.setText(inputText);
                    List<String> cryptList = cryptDSAService.cryptDSA(bytes, p, q, h, x, k);
                    decryptYEdit.setText(cryptList.get(3));
                    String outputText = " " + cryptList.get(1) + " " + cryptList.get(2);
                    cryptRLabel.setText(cryptList.get(1));
                    cryptSLabel.setText(cryptList.get(2));
                    cryptHashLabel.setText(cryptList.get(4));
                    fileService.writeBytesFile("src/resources/" + fileInput.getText(), bytes);
                    fileService.appendBytesFile("src/resources/" + fileInput.getText(), outputText.getBytes());
//                    fileService.writeStringFile("src/resources/" + fileInput.getText(), outputText);
                } catch (ServiceException e) {
                    showMessage(e.getMessage());
                }
            } else {
                showMessage("validate exception");
            }
        } catch (Exception e){
            showMessage("parse exception");
        }
    }

    private void showMessage(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText("Info");
        alert.setContentText(message);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }
}
