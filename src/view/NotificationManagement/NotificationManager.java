package view.NotificationManagement;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import impl.org.controlsfx.skin.NotificationBar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Screen;
import javafx.stage.Window;
import javafx.util.Duration;
import org.controlsfx.control.action.Action;
import org.controlsfx.tools.Utils;

public class NotificationManager {
    private static final String STYLE_CLASS_DARK = "red";
    private String title;
    private String text;
    private Node graphic;
    private ObservableList<Action> actions = FXCollections.observableArrayList();
    private Pos position;
    private Duration hideAfterDuration;
    private boolean hideCloseButton;
    private EventHandler<ActionEvent> onAction;
    private Window owner;
    private Screen screen;
    private List<String> styleClass;

    private NotificationManager() {
        this.position = Pos.BOTTOM_RIGHT;
        this.hideAfterDuration = Duration.seconds(5.0D);
        this.screen = Screen.getPrimary();
        this.styleClass = new ArrayList();
    }

    public static NotificationManager create() {
        return new NotificationManager();
    }

    public NotificationManager text(String text) {
        this.text = text;
        return this;
    }

    public NotificationManager title(String title) {
        this.title = title;
        return this;
    }

    public NotificationManager graphic(Node graphic) {
        this.graphic = graphic;
        return this;
    }

    public NotificationManager position(Pos position) {
        this.position = position;
        return this;
    }

    public NotificationManager owner(Object owner) {
        if (owner instanceof Screen) {
            this.screen = (Screen)owner;
        } else {
            this.owner = Utils.getWindow(owner);
        }

        return this;
    }

    public NotificationManager hideAfter(Duration duration) {
        this.hideAfterDuration = duration;
        return this;
    }

    public NotificationManager onAction(EventHandler<ActionEvent> onAction) {
        this.onAction = onAction;
        return this;
    }

    public NotificationManager darkStyle() {
        this.styleClass.add("dark");
        return this;
    }

    public NotificationManager redStyle() {
        this.styleClass.add("red");
        return this;
    }

    public NotificationManager hideCloseButton() {
        this.hideCloseButton = true;
        return this;
    }

    public NotificationManager action(Action... actions) {
        this.actions = actions == null ? FXCollections.observableArrayList() : FXCollections.observableArrayList(actions);
        return this;
    }

    public void showWarning() {
        this.graphic(new ImageView(NotificationManager.class.getResource("/org/controlsfx/dialog/dialog-warning.png").toExternalForm()));
        this.show();
    }

    public void showInformation() {
        this.graphic(new ImageView(NotificationManager.class.getResource("/org/controlsfx/dialog/dialog-information.png").toExternalForm()));
        this.show();
    }

    public void showError() {
        this.graphic(new ImageView(NotificationManager.class.getResource("/org/controlsfx/dialog/dialog-error.png").toExternalForm()));
        this.show();
    }

    public void showConfirm() {
        this.graphic(new ImageView(NotificationManager.class.getResource("/org/controlsfx/dialog/dialog-confirm.png").toExternalForm()));
        this.show();
    }

    public void show() {
        NotificationManager.NotificationPopupHandler.getInstance().show(this);
    }

    private static final class NotificationPopupHandler {
        private static final NotificationManager.NotificationPopupHandler INSTANCE = new NotificationManager.NotificationPopupHandler();
        private double startX;
        private double startY;
        private double screenWidth;
        private double screenHeight;
        private final Map<Pos, List<Popup>> popupsMap = new HashMap();
        private final double padding = 15.0D;
        private ParallelTransition parallelTransition = new ParallelTransition();
        private boolean isShowing = false;

        private NotificationPopupHandler() {
        }

        static final NotificationManager.NotificationPopupHandler getInstance() {
            return INSTANCE;
        }

        public void show(NotificationManager notification) {
            Window window;
            if (notification.owner == null) {
                Rectangle2D screenBounds = notification.screen.getVisualBounds();
                this.startX = screenBounds.getMinX();
                this.startY = screenBounds.getMinY();
                this.screenWidth = screenBounds.getWidth();
                this.screenHeight = screenBounds.getHeight();
                window = Utils.getWindow((Object)null);
            } else {
                this.startX = notification.owner.getX();
                this.startY = notification.owner.getY();
                this.screenWidth = notification.owner.getWidth();
                this.screenHeight = notification.owner.getHeight();
                window = notification.owner;
            }

            this.show(window, notification);
        }

        private void show(Window owner, final NotificationManager notification) {
            Window ownerWindow;
            for(ownerWindow = owner; ownerWindow instanceof PopupWindow; ownerWindow = ((PopupWindow)ownerWindow).getOwnerWindow()) {
            }

            Scene ownerScene = ownerWindow.getScene();
            if (ownerScene != null) {
                String stylesheetUrl = NotificationManager.class.getResource("/lib/css/notificationpopup.css").toExternalForm();
                if (!ownerScene.getStylesheets().contains(stylesheetUrl)) {
                    ownerScene.getStylesheets().add(0, stylesheetUrl);
                }
            }

            final Popup popup = new Popup();
            popup.setAutoFix(false);
            final Pos p = notification.position;
            NotificationBar notificationBar = new NotificationBar() {
                public String getTitle() {
                    return notification.title;
                }

                public String getText() {
                    return notification.text;
                }

                public Node getGraphic() {
                    return notification.graphic;
                }

                public ObservableList<Action> getActions() {
                    return notification.actions;
                }

                public boolean isShowing() {
                    return NotificationManager.NotificationPopupHandler.this.isShowing;
                }

                protected double computeMinWidth(double height) {
                    String text = this.getText();
                    Node graphic = this.getGraphic();
                    return (text == null || text.isEmpty()) && graphic != null ? graphic.minWidth(height) : 400.0D;
                }

                protected double computeMinHeight(double width) {
                    String text = this.getText();
                    Node graphic = this.getGraphic();
                    return (text == null || text.isEmpty()) && graphic != null ? graphic.minHeight(width) : 100.0D;
                }

                public boolean isShowFromTop() {
                    return NotificationManager.NotificationPopupHandler.this.isShowFromTop(notification.position);
                }

                public void hide() {
                    NotificationManager.NotificationPopupHandler.this.isShowing = false;
                    NotificationManager.NotificationPopupHandler.this.createHideTimeline(popup, this, p, Duration.ZERO).play();
                }

                public boolean isCloseButtonVisible() {
                    return !notification.hideCloseButton;
                }

                public double getContainerHeight() {
                    return NotificationManager.NotificationPopupHandler.this.startY + NotificationManager.NotificationPopupHandler.this.screenHeight;
                }

                public void relocateInParent(double x, double y) {
                    switch(p) {
                        case BOTTOM_LEFT:
                        case BOTTOM_CENTER:
                        case BOTTOM_RIGHT:
                            popup.setAnchorY(y - 15.0D);
                        default:
                    }
                }
            };
            notificationBar.getStyleClass().addAll(notification.styleClass);
            notificationBar.setOnMouseClicked((e) -> {
                if (notification.onAction != null) {
                    ActionEvent actionEvent = new ActionEvent(notificationBar, notificationBar);
                    notification.onAction.handle(actionEvent);
                    this.createHideTimeline(popup, notificationBar, p, Duration.ZERO).play();
                }

            });
            popup.getContent().add(notificationBar);
            popup.show(owner, 0.0D, 0.0D);
            double anchorX = 0.0D;
            double anchorY = 0.0D;
            double barWidth = notificationBar.getWidth();
            double barHeight = notificationBar.getHeight();
            switch(p) {
                case BOTTOM_LEFT:
                case TOP_LEFT:
                case CENTER_LEFT:
                    anchorX = 15.0D + this.startX;
                    break;
                case BOTTOM_CENTER:
                case TOP_CENTER:
                case CENTER:
                    anchorX = this.startX + this.screenWidth / 2.0D - barWidth / 2.0D - 7.5D;
                    break;
                case BOTTOM_RIGHT:
                case TOP_RIGHT:
                case CENTER_RIGHT:
                default:
                    anchorX = this.startX + this.screenWidth - barWidth - 15.0D;
            }

            switch(p) {
                case BOTTOM_LEFT:
                case BOTTOM_CENTER:
                case BOTTOM_RIGHT:
                default:
                    anchorY = this.startY + this.screenHeight - barHeight - 15.0D;
                    break;
                case TOP_LEFT:
                case TOP_CENTER:
                case TOP_RIGHT:
                    anchorY = 15.0D + this.startY;
                    break;
                case CENTER_LEFT:
                case CENTER:
                case CENTER_RIGHT:
                    anchorY = this.startY + this.screenHeight / 2.0D - barHeight / 2.0D - 7.5D;
            }

            popup.setAnchorX(anchorX);
            popup.setAnchorY(anchorY);
            this.isShowing = true;
            notificationBar.doShow();
            this.addPopupToMap(p, popup);
            Timeline timeline = this.createHideTimeline(popup, notificationBar, p, notification.hideAfterDuration);
            timeline.play();
        }

        private void hide(Popup popup, Pos p) {
            popup.hide();
            this.removePopupFromMap(p, popup);
        }

        private Timeline createHideTimeline(final Popup popup, NotificationBar bar, final Pos p, Duration startDelay) {
            KeyValue fadeOutBegin = new KeyValue(bar.opacityProperty(), 1.0D);
            KeyValue fadeOutEnd = new KeyValue(bar.opacityProperty(), 0.0D);
            KeyFrame kfBegin = new KeyFrame(Duration.ZERO, new KeyValue[]{fadeOutBegin});
            KeyFrame kfEnd = new KeyFrame(Duration.millis(500.0D), new KeyValue[]{fadeOutEnd});
            Timeline timeline = new Timeline(new KeyFrame[]{kfBegin, kfEnd});
            timeline.setDelay(startDelay);
            timeline.setOnFinished(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    NotificationManager.NotificationPopupHandler.this.hide(popup, p);
                }
            });
            return timeline;
        }

        private void addPopupToMap(Pos p, Popup popup) {
            Object popups;
            if (!this.popupsMap.containsKey(p)) {
                popups = new LinkedList();
                this.popupsMap.put(p, (List<Popup>) popups);
            } else {
                popups = (List)this.popupsMap.get(p);
            }

            this.doAnimation(p, popup);
            ((List)popups).add(popup);
        }

        private void removePopupFromMap(Pos p, Popup popup) {
            if (this.popupsMap.containsKey(p)) {
                List<Popup> popups = (List)this.popupsMap.get(p);
                popups.remove(popup);
            }

        }

        private void doAnimation(Pos p, Popup changedPopup) {
            List<Popup> popups = (List)this.popupsMap.get(p);
            if (popups != null) {
                double newPopupHeight = ((Node)changedPopup.getContent().get(0)).getBoundsInParent().getHeight();
                this.parallelTransition.stop();
                this.parallelTransition.getChildren().clear();
                boolean isShowFromTop = this.isShowFromTop(p);
                double sum = 0.0D;
                double[] targetAnchors = new double[popups.size()];

                int i;
                Popup _popup;
                double anchorYTarget;
                for(i = popups.size() - 1; i >= 0; --i) {
                    _popup = (Popup)popups.get(i);
                    anchorYTarget = ((Node)_popup.getContent().get(0)).getBoundsInParent().getHeight();
                    if (isShowFromTop) {
                        if (i == popups.size() - 1) {
                            sum = this.startY + newPopupHeight + 15.0D;
                        } else {
                            sum += anchorYTarget;
                        }

                        targetAnchors[i] = sum;
                    } else {
                        if (i == popups.size() - 1) {
                            sum = changedPopup.getAnchorY() - anchorYTarget;
                        } else {
                            sum -= anchorYTarget;
                        }

                        targetAnchors[i] = sum;
                    }
                }

                for(i = popups.size() - 1; i >= 0; --i) {
                    _popup = (Popup)popups.get(i);
                    anchorYTarget = targetAnchors[i];
                    if (anchorYTarget < 0.0D) {
                        _popup.hide();
                    }

                    final double oldAnchorY = _popup.getAnchorY();
                    final double distance = anchorYTarget - oldAnchorY;
                    Popup final_popup = _popup;
                    Transition t = new Transition() {
                        {
                            this.setCycleDuration(Duration.millis(350.0D));
                        }

                        protected void interpolate(double frac) {
                            double newAnchorY = oldAnchorY + distance * frac;
                            final_popup.setAnchorY(newAnchorY);
                        }
                    };
                    t.setCycleCount(1);
                    this.parallelTransition.getChildren().add(t);
                }

                this.parallelTransition.play();
            }
        }

        private boolean isShowFromTop(Pos p) {
            switch(p) {
                case TOP_LEFT:
                case TOP_CENTER:
                case TOP_RIGHT:
                    return true;
                case CENTER_LEFT:
                case CENTER:
                default:
                    return false;
            }
        }
    }
}
