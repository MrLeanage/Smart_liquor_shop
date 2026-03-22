package util.loader;

import animatefx.animation.AnimationFX;
import animatefx.animation.RotateOut;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Loader {


    public void startUpAnimation(Circle circle1, Circle circle2, Circle circle3, Circle circle4, Circle circle5, Circle circle6, Circle circle7, Circle circle8, AnchorPane LoadingAnchorPane){
        //basePane.setVisible(true);


        new RotateOut(circle1).setCycleCount(5).setDelay(Duration.valueOf("100ms")).play();
        AnimationFX animationFX = new RotateOut(circle2);
        animationFX.setCycleCount(5).setDelay(Duration.valueOf("300ms")).play();
        new RotateOut(circle3).setCycleCount(5).setDelay(Duration.valueOf("400ms")).play();
        new RotateOut(circle4).setCycleCount(5).setDelay(Duration.valueOf("550ms")).play();
        new RotateOut(circle5).setCycleCount(5).setDelay(Duration.valueOf("700ms")).play();
        new RotateOut(circle6).setCycleCount(5).setDelay(Duration.valueOf("800ms")).play();
        new RotateOut(circle7).setCycleCount(5).setDelay(Duration.valueOf("900ms")).play();
        new RotateOut(circle8).setCycleCount(5).setDelay(Duration.valueOf("1000ms")).play();

        animationFX.setOnFinished((e -> {
            LoadingAnchorPane.setVisible(false);
        }));

    }
}
