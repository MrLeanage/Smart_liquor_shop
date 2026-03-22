package view.AppHome;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.UserServices;
import util.authenticate.UserAuthentication;
import util.loader.HaivyPreloader;

public class Main extends Application {

//    private static final int COUNT_LIMIT = 200000;
    private static final int COUNT_LIMIT = 2;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene(new AnchorPane());
        UserAuthentication userAuthentication = new UserAuthentication(scene);
        UserServices userServices = new UserServices();
        Boolean resultVal = userServices.addDefaultAdminUser();
        //Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        if(resultVal){
            userAuthentication.showLoginScreen();
            //primaryStage.setScene(new Scene(root, 1355, 730));
            primaryStage.setTitle("Retail MANAGEMENT SYSTEM - SMART LIQUOR SHOP");
            primaryStage.getIcons().add(new Image("/lib/img/haivy-icon.png"));
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.sizeToScene();
            primaryStage.show();
        }


    }
    @Override
    public void init() throws Exception{
        for(int i = 0; i< COUNT_LIMIT; i++){
            double progress = (100*i) / COUNT_LIMIT;
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
        }
    }


    public static void main(String[] args) {

        LauncherImpl.launchApplication(Main.class, HaivyPreloader.class, args);
        //launch(args);
    }
}
