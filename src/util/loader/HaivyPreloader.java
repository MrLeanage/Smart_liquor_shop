package util.loader;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.AppHome.SplashScreenController;

public class HaivyPreloader extends Preloader {

    private Stage preloaderStage;
    private Scene scene;

    public HaivyPreloader() {
    }

    @Override
    public void init() throws Exception{
        Parent parentRoot = FXMLLoader.load(getClass().getResource("/view/AppHome/splashScreen.fxml"));
        scene = new Scene(parentRoot);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.preloaderStage = primaryStage;
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(new Image("/lib/img/haivy-icon.png"));
        preloaderStage.setScene(scene);
        preloaderStage.setResizable(false);
        preloaderStage.sizeToScene();
        preloaderStage.show();
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification preloaderNotification){
        if(preloaderNotification instanceof ProgressNotification){
            SplashScreenController.systemProgress.setProgress(((ProgressNotification) preloaderNotification).getProgress() /100);
            if(((ProgressNotification) preloaderNotification).getProgress() < 15){
                SplashScreenController.progressStatus.setText("Initializing System... " + ((ProgressNotification) preloaderNotification).getProgress() + " %");
            }else if(((ProgressNotification) preloaderNotification).getProgress() < 25){
                SplashScreenController.progressStatus.setText("Loading Libraries... "+ ((ProgressNotification) preloaderNotification).getProgress() + " %");
            }else if(((ProgressNotification) preloaderNotification).getProgress() < 35){
                SplashScreenController.progressStatus.setText("Initializing System Database Connection... "+ ((ProgressNotification) preloaderNotification).getProgress() + " %");
            }else if(((ProgressNotification) preloaderNotification).getProgress() < 50){
                SplashScreenController.progressStatus.setText("Connecting to Database Server... "+ ((ProgressNotification) preloaderNotification).getProgress() + " %");
            }else if(((ProgressNotification) preloaderNotification).getProgress() < 60) {
                SplashScreenController.progressStatus.setText("Establishing Connection... "+ ((ProgressNotification) preloaderNotification).getProgress() + " %");
            }else if(((ProgressNotification) preloaderNotification).getProgress() < 75) {
                SplashScreenController.progressStatus.setText("Verifying Connection... "+ ((ProgressNotification) preloaderNotification).getProgress() + " %");
            }else if(((ProgressNotification) preloaderNotification).getProgress() < 55) {
                SplashScreenController.progressStatus.setText("Finalizing System... "+ ((ProgressNotification) preloaderNotification).getProgress() + " %");
            }else if(((ProgressNotification) preloaderNotification).getProgress() < 100) {
                SplashScreenController.progressStatus.setText("Loading User assets... "+ ((ProgressNotification) preloaderNotification).getProgress() + " %");
            }


        }
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification stateChangeNotification){
        StateChangeNotification.Type type = stateChangeNotification.getType();
        switch (type){
            case BEFORE_START:
                preloaderStage.hide();
                break;
        }

    }
}
