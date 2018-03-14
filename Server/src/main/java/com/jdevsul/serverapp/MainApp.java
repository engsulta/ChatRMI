package com.jdevsul.serverapp;



import com.jdevsul.server.db.DatabaseHandler;
import com.jdevsul.server.util.ServerAssistUtil;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    //   Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginFxml.fxml"));
        ServerAssistUtil.loadWindow(getClass().getResource("/fxml/LoginFxml.fxml"), null, "ServerLogin");
      //  Scene scene = new Scene(root);
        
       // stage.setScene(scene);
       // stage.show();
        
        new Thread(new Runnable() {
           @Override
           public void run() {
                DatabaseHandler.getInstance();
           }
       }).start();

        
        }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
