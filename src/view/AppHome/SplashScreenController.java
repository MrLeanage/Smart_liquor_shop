package view.AppHome;

import com.jfoenix.controls.JFXProgressBar;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreenController implements Initializable {



    @FXML
    private JFXProgressBar systemProgressBar;

    public static JFXProgressBar systemProgress;

    @FXML
    private Label progressStatusLabel;

    public static Label progressStatus;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        systemProgress = systemProgressBar;
        progressStatus = progressStatusLabel;
    }
}
