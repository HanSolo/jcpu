package eu.hansolo.fx.jcpu;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashMap;


public class JcpuBuilder <B extends JcpuBuilder<B>> {
    private HashMap<String, Property> properties = new HashMap<>();


    // ******************** Constructors **************************************
    protected JcpuBuilder() {}


    // ******************** Methods *******************************************
    public static final JcpuBuilder create() {
        return new JcpuBuilder();
    }

    public final B title(final String title) {
        properties.put("title", new SimpleStringProperty(title));
        return (B)this;
    }

    public final B info(final String info) {
        properties.put("info", new SimpleStringProperty(info));
        return (B)this;
    }

    public final B titleTextAlignment(final TextAlignment textAlignment) {
        properties.put("titleTextAlignment", new SimpleObjectProperty<>(textAlignment));
        return (B)this;
    }

    public final B infoTextAlignment(final TextAlignment textAlignment) {
        properties.put("infoTextAlignment", new SimpleObjectProperty<>(textAlignment));
        return (B)this;
    }

    public final B backgroundColor(final Color backgroundColor) {
        properties.put("backgroundColor", new SimpleObjectProperty(backgroundColor));
        return (B)this;
    }

    public final B foregroundColor(final Color foregroundColor) {
        properties.put("foregroundColor", new SimpleObjectProperty(foregroundColor));
        return (B)this;
    }

    public final B titleColor(final Color titleColor) {
        properties.put("titleColor", new SimpleObjectProperty<>(titleColor));
        return (B)this;
    }

    public final B infoColor(final Color infoColor) {
        properties.put("infoColor", new SimpleObjectProperty<>(infoColor));
        return (B)this;
    }

    public final B timestamp(final Instant timestamp) {
        properties.put("timestamp", new SimpleObjectProperty<>(timestamp));
        return (B)this;
    }
    public final B timestamp(final ZonedDateTime timestamp) {
        properties.put("timestamp", new SimpleObjectProperty<>(timestamp.toInstant()));
        return (B)this;
    }

    public final B value1Color(final Color color) {
        properties.put("value1Color", new SimpleObjectProperty<>(color));
        return (B)this;
    }

    public final B value2Color(final Color color) {
        properties.put("value2Color", new SimpleObjectProperty<>(color));
        return (B)this;
    }

    public final B value3Color(final Color color) {
        properties.put("value3Color", new SimpleObjectProperty<>(color));
        return (B)this;
    }

    public final B value1TextColor(final Color color) {
        properties.put("value1TextColor", new SimpleObjectProperty<>(color));
        return (B)this;
    }

    public final B value2TextColor(final Color color) {
        properties.put("value2TextColor", new SimpleObjectProperty<>(color));
        return (B)this;
    }

    public final B value3TextColor(final Color color) {
        properties.put("value3TextColor", new SimpleObjectProperty<>(color));
        return (B)this;
    }

    public final B minValue1(final double value) {
        properties.put("minValue1", new SimpleDoubleProperty(value));
        return (B)this;
    }

    public final B maxValue1(final double value) {
        properties.put("maxValue1", new SimpleDoubleProperty(value));
        return (B)this;
    }

    public final B value1(final double value) {
        properties.put("value1", new SimpleDoubleProperty(value));
        return (B)this;
    }

    public final B minValue2(final double value) {
        properties.put("minValue2", new SimpleDoubleProperty(value));
        return (B)this;
    }

    public final B maxValue2(final double value) {
        properties.put("maxValue2", new SimpleDoubleProperty(value));
        return (B)this;
    }

    public final B value2(final double value) {
        properties.put("value2", new SimpleDoubleProperty(value));
        return (B)this;
    }

    public final B minValue3(final double value) {
        properties.put("minValue3", new SimpleDoubleProperty(value));
        return (B)this;
    }

    public final B maxValue3(final double value) {
        properties.put("maxValue3", new SimpleDoubleProperty(value));
        return (B)this;
    }

    public final B value3(final double value) {
        properties.put("value3", new SimpleDoubleProperty(value));
        return (B)this;
    }

    public final B nameValue1(final String name) {
        properties.put("nameValue1", new SimpleStringProperty(name));
        return (B)this;
    }

    public final B nameValue2(final String name) {
        properties.put("nameValue2", new SimpleStringProperty(name));
        return (B)this;
    }

    public final B nameValue3(final String name) {
        properties.put("nameValue3", new SimpleStringProperty(name));
        return (B)this;
    }

    public final B formatStringValue1(final String formatString) {
        properties.put("formatStringValue1", new SimpleStringProperty(formatString));
        return (B)this;
    }

    public final B formatStringValue2(final String formatString) {
        properties.put("formatStringValue2", new SimpleStringProperty(formatString));
        return (B)this;
    }

    public final B formatStringValue3(final String formatString) {
        properties.put("formatStringValue3", new SimpleStringProperty(formatString));
        return (B)this;
    }



    public final Jcpu build() {
        final Jcpu jcpu = new Jcpu();
        for (String key : properties.keySet()) {
            switch (key) {
                case "title"              -> jcpu.setTitle(((StringProperty) properties.get(key)).get());
                case "info"               -> jcpu.setInfo(((StringProperty) properties.get(key)).get());
                case "titleTextAlignment" -> jcpu.setTitleTextAlignment(((ObjectProperty<TextAlignment>) properties.get(key)).get());
                case "infoTextAlignment"  -> jcpu.setInfoTextAlignment(((ObjectProperty<TextAlignment>) properties.get(key)).get());
                case "backgroundColor"    -> jcpu.setBackgroundColor(((ObjectProperty<Color>) properties.get(key)).get());
                case "foregroundColor"    -> jcpu.setForegroundColor(((ObjectProperty<Color>) properties.get(key)).get());
                case "titleColor"         -> jcpu.setTitleColor(((ObjectProperty<Color>) properties.get(key)).get());
                case "infoColor"          -> jcpu.setInfoColor(((ObjectProperty<Color>) properties.get(key)).get());
                case "timestamp"          -> jcpu.setTimestamp(((ObjectProperty<Instant>) properties.get(key)).get());
                case "value1Color"        -> jcpu.setValue1Color(((ObjectProperty<Color>) properties.get(key)).get());
                case "value2Color"        -> jcpu.setValue2Color(((ObjectProperty<Color>) properties.get(key)).get());
                case "value3Color"        -> jcpu.setValue3Color(((ObjectProperty<Color>) properties.get(key)).get());
                case "value1TextColor"    -> jcpu.setValue1TextColor(((ObjectProperty<Color>) properties.get(key)).get());
                case "value2TextColor"    -> jcpu.setValue2TextColor(((ObjectProperty<Color>) properties.get(key)).get());
                case "value3TextColor"    -> jcpu.setValue3TextColor(((ObjectProperty<Color>) properties.get(key)).get());
                case "minValue1"          -> jcpu.setMinValue1(((DoubleProperty) properties.get(key)).get());
                case "maxValue1"          -> jcpu.setMaxValue1(((DoubleProperty) properties.get(key)).get());
                case "value1"             -> jcpu.setValue1(((DoubleProperty) properties.get(key)).get());
                case "minValue2"          -> jcpu.setMinValue2(((DoubleProperty) properties.get(key)).get());
                case "maxValue2"          -> jcpu.setMaxValue2(((DoubleProperty) properties.get(key)).get());
                case "value2"             -> jcpu.setValue2(((DoubleProperty) properties.get(key)).get());
                case "minValue3"          -> jcpu.setMinValue3(((DoubleProperty) properties.get(key)).get());
                case "maxValue3"          -> jcpu.setMaxValue3(((DoubleProperty) properties.get(key)).get());
                case "value3"             -> jcpu.setValue3(((DoubleProperty) properties.get(key)).get());
                case "nameValue1"         -> jcpu.setNameValue1(((StringProperty) properties.get(key)).get());
                case "nameValue2"         -> jcpu.setNameValue2(((StringProperty) properties.get(key)).get());
                case "nameValue3"         -> jcpu.setNameValue3(((StringProperty) properties.get(key)).get());
                case "formatStringValue1" -> jcpu.setFormatStringValue1(((StringProperty) properties.get(key)).get());
                case "formatStringValue2" -> jcpu.setFormatStringValue2(((StringProperty) properties.get(key)).get());
                case "formatStringValue3" -> jcpu.setFormatStringValue3(((StringProperty) properties.get(key)).get());
            }
        }
        return jcpu;
    }
}

