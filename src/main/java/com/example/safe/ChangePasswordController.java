package com.example.safe;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.*;

public class ChangePasswordController {
    @FXML
    public Button changeCodeBT;
    @FXML
    public Label messageLB;
    @FXML
    public Button returnBT;
    @FXML
    private Button closeBT;
    @FXML
    private TextField code1TF1;
    @FXML
    private TextField code2TF1;
    @FXML
    private TextField code3TF1;
    @FXML
    private TextField code4TF1;
    @FXML
    private TextField code5TF1;
    @FXML
    private TextField code6TF1;
    @FXML
    private TextField current1TF;
    @FXML
    private TextField current2TF;
    @FXML
    private TextField current3TF;
    @FXML
    private TextField current4TF;
    @FXML
    private TextField current5TF;
    @FXML
    private TextField current6TF;
    @FXML
    void changeCode(ActionEvent event) throws IOException{
        String current = current1TF.getText()+current2TF.getText()+current3TF.getText()+current4TF.getText()+current5TF.getText()+current6TF.getText();
        String newCode = code1TF1.getText()+code2TF1.getText()+code3TF1.getText()+code4TF1.getText()+code5TF1.getText()+code6TF1.getText();
        String code = loadCode();
        if(!current.equals(code)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("WRONG SAFE CODE");
            alert.showAndWait();
        }else{
            saveCode(newCode);
        }
    }
    private void saveCode(String newCode) throws IOException{
        FileOutputStream fos = new FileOutputStream(new File("data.json"));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        Gson gson = new Gson();
        String data = gson.toJson(newCode);
        writer.write(data);
        writer.flush();
        fos.close();
        messageLB.setText("Safe code saved");
    }
    private String loadCode() throws IOException {
        try{
            FileInputStream fis = new FileInputStream(new File("data.json"));
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line = "";
            StringBuilder msj = new StringBuilder();
            while((line=reader.readLine())!=null){
                msj.append(line);
            }
            Gson gson = new Gson();
            String code = gson.fromJson(msj.toString(),String.class);
            return code;
        }catch(Exception ex){
            return "000000";
        }
    }
    @FXML
    void typeKey(KeyEvent keyEvent){
        TextField tf = (TextField) keyEvent.getSource();
        tf.setText(keyEvent.getCharacter());
    }
    @FXML
    void closeSafe(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(SafeApplication.class.getResource("safe.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Safe");
        stage.setScene(scene);
        stage.show();
        Stage currentStage = (Stage)closeBT.getScene().getWindow();
        currentStage.close();
    }
    @FXML
    void returnWindow(ActionEvent event) throws IOException{
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(SafeApplication.class.getResource("safeContent.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Safe Content");
            stage.setScene(scene);
            stage.show();
            Stage currentStage = (Stage)returnBT.getScene().getWindow();
            currentStage.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

}
