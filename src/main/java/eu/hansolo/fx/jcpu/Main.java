package eu.hansolo.fx.jcpu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class Main extends Application {
    private Jcpu jcpu;


    @Override public void init() {
        jcpu = JcpuBuilder.create()
                          .title("Title")
                          .info("Info")
                          .titleColor(Color.MAGENTA)
                          .infoColor(Color.CYAN)
                          .titleTextAlignment(TextAlignment.RIGHT)
                          .infoTextAlignment(TextAlignment.CENTER)
                          .build();
    }

    @Override public void start(final Stage stage) {
        StackPane pane = new StackPane(jcpu);
        pane.setPadding(new Insets(10));

        Scene scene = new Scene(pane);

        stage.setTitle("JCPU");
        stage.setScene(scene);
        stage.show();
    }

    @Override public void stop() {
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}