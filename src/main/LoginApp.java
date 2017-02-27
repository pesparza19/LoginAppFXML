package main;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import security.Authenticator;

public class LoginApp extends Application {
    private Stage stage;
    private User loggedUser;

    private static LoginApp instance;

    public LoginApp() {
        instance = this;
    }

    public static LoginApp getInstance() {
        return instance;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override 
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            gotoLogin();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(LoginApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public boolean userLogging(String userId, String password){
        if (Authenticator.validate(userId, password)) {
            loggedUser = User.of(userId);
            gotoProfile();
            return true;
        } else {
            return false;
        }
    }

    public void userLogout(){
        loggedUser = null;
        gotoLogin();
    }

    private void gotoProfile() {
        try {
            replaceSceneContent("/fxml/profile.fxml");
        } catch (Exception ex) {
            Logger.getLogger(LoginApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gotoLogin() {
        try {
            replaceSceneContent("/fxml/login.fxml");
        } catch (Exception ex) {
            Logger.getLogger(LoginApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Parent replaceSceneContent(String fxml) throws Exception {
        Parent page = (Parent) FXMLLoader.load(LoginApp.class.getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page, 700, 550);
            //scene.getStylesheets().add(LoginApp.class.getResource("demo.css").toExternalForm());       
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        return page;
    }
}