package com.example.safe;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.*;

public class SafeController {
    @FXML
    private TextField code1TF;
    @FXML
    private TextField code2TF;
    @FXML
    private TextField code3TF;
    @FXML
    private TextField code4TF;
    @FXML
    private TextField code5TF;
    @FXML
    private TextField code6TF;
    @FXML
    private Button openBT;
    @FXML
    void open(ActionEvent event) throws IOException{
        String inputCode = code1TF.getText()+code2TF.getText()+code3TF.getText()+code4TF.getText()+code5TF.getText()+code6TF.getText();
        String code = loadCode();
        if(inputCode.equals(code)){
            openSafe();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("WRONG SAFE CODE");
            alert.showAndWait();
        }
    }
    @FXML
    public void moveToNext(KeyEvent keyEvent) {
        TextField tf = (TextField) keyEvent.getSource();
        tf.setText(keyEvent.getCharacter());
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
            return gson.fromJson(msj.toString(),String.class);
        }catch(Exception ex){
            return "000000";
        }
    }
    void openSafe(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(SafeApplication.class.getResource("safeContent.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Safe Content");
            stage.setScene(scene);
            stage.show();
            Stage currentStage = (Stage) this.openBT.getScene().getWindow();
            currentStage.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

}
