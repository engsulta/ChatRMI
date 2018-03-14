/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.server.util;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import static javafx.fxml.FXMLLoader.load;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author sulta
 */
public class ServerAssistUtil {

    private static final String img_loc = "/images/logo2.png";

    private static void setStageImage(Stage stage) {
        stage.getIcons().add(new Image(img_loc));

    }

    public static void loadWindow(URL loc, Stage parentStage, String name) {
          Stage stage=null;
        try {
            Parent parent = FXMLLoader.load(loc);
            if (parentStage!=null){
                stage =parentStage;
                
            }else{
                stage=new Stage(StageStyle.UNDECORATED);
            }
            stage.setTitle(name);
            stage.setScene(new Scene(parent));
            stage.show();
            setStageImage(stage);

        } catch (IOException ex) {
            Logger.getLogger(ServerAssistUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
