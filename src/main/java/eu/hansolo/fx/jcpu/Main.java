package eu.hansolo.fx.jcpu;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Random;


public class Main extends Application {
    private static final Random         RND = new Random();
    private              Jcpu           jcpu;
    private              long           lastTimerCall;
    private              AnimationTimer timer;


    @Override public void init() {
        jcpu = JcpuBuilder.create()
                          .title("Title")
                          .info("Info")
                          .titleColor(Color.WHITE)
                          .infoColor(Color.WHITE)
                          .titleTextAlignment(TextAlignment.LEFT)
                          .infoTextAlignment(TextAlignment.LEFT)
                          .value1Color(Color.rgb(0, 200, 200))
                          .value2Color(Color.rgb(100, 200, 0))
                          .value3Color(Color.rgb(200, 0, 0))
                          .value1TextColor(Constants.DEFAULT_UNIT_VALUE_COLOR)
                          .value2TextColor(Constants.DEFAULT_UNIT_VALUE_COLOR)
                          .value3TextColor(Constants.DEFAULT_UNIT_VALUE_COLOR)
                          .minValue1(0)
                          .maxValue1(100)
                          .value1(50)
                          .formatStringValue1("%.0f%%")
                          .nameValue1("CPU")
                          .minValue2(0)
                          .maxValue2(100)
                          .value2(30)
                          .formatStringValue2("%.0f%%")
                          .nameValue2("MEM")
                          .minValue3(0)
                          .maxValue3(100)
                          .value3(120)
                          .nameValue3("TEMP")
                          .formatStringValue3("%.0f%%")
                          .build();

        jcpu.compressedProperty().addListener((o, ov, nv) -> {
            if (nv) {
                jcpu.setValue1Color(Color.rgb(0, 150, 150));
                jcpu.setValue2Color(Color.rgb(50, 150, 0));
                jcpu.setValue3Color(Color.rgb(150, 0, 0));
                jcpu.setValue1TextColor(Color.WHITE);
                jcpu.setValue2TextColor(Color.WHITE);
                jcpu.setValue3TextColor(Color.WHITE);
            } else {
                jcpu.setValue1Color(Color.rgb(0, 200, 200));
                jcpu.setValue2Color(Color.rgb(100, 200, 0));
                jcpu.setValue3Color(Color.rgb(200, 0, 0));
                jcpu.setValue1TextColor(Constants.DEFAULT_UNIT_VALUE_COLOR);
                jcpu.setValue2TextColor(Constants.DEFAULT_UNIT_VALUE_COLOR);
                jcpu.setValue3TextColor(Constants.DEFAULT_UNIT_VALUE_COLOR);
            }
        });

        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(final long now) {
                if (now > lastTimerCall + 1_000_000_000L) {
                    jcpu.setValue1(RND.nextDouble() * (jcpu.getMaxValue1() - jcpu.getMinValue1()));
                    jcpu.setValue2(RND.nextDouble() * (jcpu.getMaxValue2() - jcpu.getMinValue2()));
                    jcpu.setValue3(RND.nextDouble() * (jcpu.getMaxValue3() - jcpu.getMinValue3()));
                    lastTimerCall = now;
                }
            }
        };
    }

    @Override public void start(final Stage stage) {
        StackPane pane = new StackPane(jcpu);
        pane.setPadding(new Insets(10));

        Scene scene = new Scene(pane);

        stage.setTitle("JCPU");
        stage.setScene(scene);
        stage.show();

        timer.start();
    }

    @Override public void stop() {
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}