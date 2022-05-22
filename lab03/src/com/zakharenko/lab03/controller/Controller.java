package com.zakharenko.lab03.controller;

import com.zakharenko.lab03.dal.IOFileDal;
import com.zakharenko.lab03.dal.exception.DalException;
import com.zakharenko.lab03.dal.impl.IOFileDalImpl;
import com.zakharenko.lab03.service.ConvertService;
import com.zakharenko.lab03.service.CryptService;
import com.zakharenko.lab03.service.ValidateService;
import com.zakharenko.lab03.service.exception.ServiceException;
import com.zakharenko.lab03.service.impl.ConvertServiceImpl;
import com.zakharenko.lab03.service.impl.RabinCryptServiceImpl;
import com.zakharenko.lab03.service.impl.ValidateServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    public TextArea inputArea;
    @FXML
    public TextArea outputArea;
    @FXML
    public Button cryptButton;
    @FXML
    public TextField pInput;
    @FXML
    public Button fileButton;
    @FXML
    public TextField fileInput;
    @FXML
    public Button decryptButton;
    @FXML
    public TextField qInput;
    @FXML
    public TextField bInput;
    @FXML
    public Button randomButton;

    private final CryptService cryptService = new RabinCryptServiceImpl();
    private final ConvertService convertService = new ConvertServiceImpl();
    private final ValidateService validateService = new ValidateServiceImpl();
    private final IOFileDal ioFileDal = new IOFileDalImpl();
    private final FileChooser fileChooser = new FileChooser();
    private List<Integer> plainList = new ArrayList<>();
    private List<Integer> plainCryptList = new ArrayList<>();
    private String path;

    @FXML
    public void initialize() {
        inputArea.setWrapText(true);
        outputArea.setWrapText(true);
        pInput.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                pInput.setText(newValue.replaceAll("\\D", ""));
            }
        });
        qInput.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                qInput.setText(newValue.replaceAll("\\D", ""));
            }
        });
        bInput.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                bInput.setText(newValue.replaceAll("\\D", ""));
            }
        });

        fileButton.setOnAction(actionEvent -> {
            try {
                path = fileChooser.showOpenDialog(new Stage()).getPath();

//                plainCryptList = ioFileDal.readListIntegerFromFile(path);

                plainList = ioFileDal.readFileToIntArr(path);
//                plainCryptList = ioFileDal.readFileToIntList(path);

                inputArea.setText(convertService.convertListIntToString(plainList));
            } catch (DalException e) {
                showErrorMessage("File open: " + e.getMessage());
            }
        });

        cryptButton.setOnAction(actionEvent -> {
            if (inputArea.getText().isEmpty() || fileInput.getText().isEmpty()
                    || pInput.getText().isEmpty() || qInput.getText().isEmpty()) {
                showErrorMessage("Input fields are empty");
            } else {
                if(bInput.getText().isEmpty()){
                    randomB();
                }
                int q = 0;
                int p = 0;
                int b = 0;
                try{
                    q = Integer.parseInt(qInput.getText());
                    p = Integer.parseInt(pInput.getText());
                    b = Integer.parseInt(bInput.getText());
                } catch (Exception e) {
                    showErrorMessage("encrypt parse exception");
                }
                int n = p * q;
                if(!validateService.validatePrivateKey(p, q) || !validateService.validatePublicKey(n, b)){
                    showErrorMessage("Encrypt validate exception");
                    return;
                }
                try {
                    List<Integer> listCrypt = cryptService.encrypt(plainList, n, b);
//                    ioFileDal.writeStringFile("src/resources/" + fileInput.getText(), cryptText);
                    ioFileDal.writeListIntegerToFile("src/resources/" + fileInput.getText(), listCrypt);
                    String cryptText = convertService.convertListIntToString(listCrypt);
                    outputArea.setText(cryptText);
                } catch (ServiceException | DalException e) {
                    showErrorMessage("Encrypt IO exception");
                }
            }
        });

        decryptButton.setOnAction(actionEvent -> {
            if (inputArea.getText().isEmpty() || fileInput.getText().isEmpty()
                    || pInput.getText().isEmpty() || qInput.getText().isEmpty()) {
                showErrorMessage("Input fields are empty");
            } else {
                if(bInput.getText().isEmpty()){
                    randomB();
                }
                int q = 0;
                int p = 0;
                int b = 0;
                try{
                    q = Integer.parseInt(qInput.getText());
                    p = Integer.parseInt(pInput.getText());
                    b = Integer.parseInt(bInput.getText());
                } catch (Exception e) {
                    showErrorMessage("Decrypt: " + e.getMessage());
                }
                int buf;
                if(p < q){
                    buf = p;
                    p = q;
                    q = buf;
                }
                int n = p * q;
                if(!validateService.validatePrivateKey(p, q) || !validateService.validatePublicKey(n, b)){
                    showErrorMessage("Decrypt incorrect validate");
                    return;
                }
                try {
                    String pathResult = "src/resources/" + fileInput.getText();
                    List<List<Long>> listDec = new ArrayList<>();
                    plainCryptList = ioFileDal.readListIntegerFromFile(path);
                    byte[] resultBytes = cryptService.decrypt(plainCryptList, n, p, q, b, listDec);
                    ioFileDal.writeBytesFile(pathResult, resultBytes);
                    outputArea.setText(convertService.convertListListLongToString(listDec));
                    ioFileDal.openFile(pathResult);
                } catch (ServiceException | DalException e) {
                    showErrorMessage("Decrypt IO Exception");
                }
            }
        });

        randomButton.setOnAction(actionEvent -> randomB());

    }

    private void randomB(){
        if(pInput.getText().isEmpty() || qInput.getText().isEmpty()){
            showErrorMessage("Fields p and q are empty");
        } else {
            try {
                int p = Integer.parseInt(pInput.getText());
                int q = Integer.parseInt(qInput.getText());
                int n = p*q;
                int b = (int) (Math.random() * n);
                bInput.setText(Integer.toString(b));
            } catch (Exception e){
                showErrorMessage("Error input fields");
            }
        }
    }

    private void showErrorMessage(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }
}
