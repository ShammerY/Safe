package com.example.safe;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SafeContentController implements Initializable {
    @FXML
    private Button changeCodeBT;
    @FXML
    private Button closeSafeBT;
    @FXML
    private TextArea contentTA;
    @FXML
    private Button saveBT;
    @FXML
    void changeCode(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(SafeApplication.class.getResource("changePassword.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Change Password");
            stage.setScene(scene);
            stage.show();
            Stage currentStage = (Stage)saveBT.getScene().getWindow();
            currentStage.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    @FXML
    void closeSafe(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    void saveContent(ActionEvent event) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File("content.json"));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        Gson gson = new Gson();
        writer.write(gson.toJson(contentTA.getText()));
        writer.flush();
        fos.close();
    }
    private void loadContent() throws IOException{
        try{
            FileInputStream fis= new FileInputStream(new File("content.json"));
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder msj = new StringBuilder();
            String line = "";
            while((line = reader.readLine())!=null){
                msj.append(line);
            }
            Gson gson = new Gson();
            String content = gson.fromJson(msj.toString(),String.class);
            fis.close();
            contentTA.setText(content);
        }catch(FileNotFoundException ignored){}
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            loadContent();
        }catch(Exception ignored){}
    }
}