package Controllers;

import main.LoginApp;
import model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class ProfileController implements Initializable {
    @FXML private TextField user;
    @FXML private TextField phone;
    @FXML private TextField email;
    @FXML private TextArea address;
    @FXML private CheckBox subscribed;
    @FXML private Label success;

    @Override public void initialize(URL location, ResourceBundle resources) {
        User loggedUser = LoginApp.getInstance().getLoggedUser();
        user.setText(loggedUser.getId());
        if(loggedUser.getEmail() != null) {
            email.setText(loggedUser.getEmail());
        }
        if(loggedUser.getPhone() != null) {
            phone.setText(loggedUser.getPhone());
        }
        if(loggedUser.getAddress() != null) {
            address.setText(loggedUser.getAddress());
        }
        subscribed.setSelected(loggedUser.isSubscribed());
        success.setOpacity(0);
    }

    @FXML protected void processLogout() {
        LoginApp.getInstance().userLogout();
    }

    @FXML protected void processUpdate() {
        User loggedUser = LoginApp.getInstance().getLoggedUser();
        loggedUser.setEmail(email.getText());
        loggedUser.setPhone(phone.getText());
        loggedUser.setSubscribed(subscribed.isSelected());
        loggedUser.setAddress(address.getText());
        animateMessage();
    }

    private void animateMessage() {
        FadeTransition ft = new FadeTransition(new Duration(3000), success);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }    
}