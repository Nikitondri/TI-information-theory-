package com.zakharenko.lab02.controller;

import com.zakharenko.lab02.service.ConvertService;
import com.zakharenko.lab02.service.CryptLFSRService;
import com.zakharenko.lab02.service.FileService;
import com.zakharenko.lab02.service.exception.ServiceException;
import com.zakharenko.lab02.service.impl.ConvertServiceImpl;
import com.zakharenko.lab02.service.impl.CryptLFSRServiceImpl;
import com.zakharenko.lab02.service.impl.FileServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.BitSet;

public class Controller {
    @FXML
    public TextArea inputArea;
    @FXML
    public TextArea keyArea;
    @FXML
    public TextArea outputArea;
    @FXML
    public Button cryptButton;
    @FXML
    public TextField stateInput;
    @FXML
    public Button fileButton;
    @FXML
    public TextField fileInput;

    private final ConvertService convertService = new ConvertServiceImpl();
    private final CryptLFSRService cryptService = new CryptLFSRServiceImpl();
    private final FileService fileService = new FileServiceImpl();

    @FXML
    public void initialize(){
        inputArea.setWrapText(true);
        outputArea.setWrapText(true);
        keyArea.setWrapText(true);
        stateInput.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("[01]*")) {
                stateInput.setText(newValue.replaceAll("[^01]", ""));
            }
            if(stateInput.getText().length() > 38){
                stateInput.setText(oldValue);
            }
        });
        inputArea.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("[01]*")) {
                inputArea.setText(newValue.replaceAll("[^01]", ""));
            }
        });
//        keyArea.textProperty().addListener((observableValue, oldValue, newValue) -> {
//            keyArea.setText("");
//        });
//        outputArea.textProperty().addListener((observableValue, oldValue, newValue) -> {
//            outputArea.setText("");
//        });
        cryptButton.setOnAction(event -> {
            if(stateInput.getText().length() != 38 || inputArea.getText().isEmpty() || fileInput.getText().isEmpty()){
                showErrorMessage();
            } else {
                BitSet inputBitSet = convertService.convertStringToBits(inputArea.getText());
                BitSet stateBitSet = convertService.convertStringToBits(stateInput.getText());
                BitSet keyBitSet = cryptService.generateKey(inputBitSet.size(), stateBitSet);
                BitSet cipherBitSet = cryptService.encrypt(inputBitSet, keyBitSet);
                keyArea.setText(convertService.convertBitsToString(keyBitSet));
                outputArea.setText(convertService.convertBitsToString(cipherBitSet));
                try {
                    fileService.write(fileInput.getText(), convertService.convertBitsToBytes(cipherBitSet));
                } catch (ServiceException e) {
                    showErrorMessage();
                }
            }
        });
        fileButton.setOnAction(event -> {
            try {
                inputArea.setText(convertService.convertBitsToString(convertService.convertBytesToBits(fileService.read())));
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        });
    }

    private void showErrorMessage(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error fields");
        alert.setHeaderText("Error fields");
        alert.setContentText("Please, fill correct fields!");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }
}
