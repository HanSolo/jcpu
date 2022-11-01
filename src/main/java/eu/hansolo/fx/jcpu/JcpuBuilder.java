package eu.hansolo.fx.jcpu;

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

    public final B timestamp(final Instant timestamp) {
        properties.put("timestamp", new SimpleObjectProperty<>(timestamp));
        return (B)this;
    }
    public final B timestamp(final ZonedDateTime timestamp) {
        properties.put("timestamp", new SimpleObjectProperty<>(timestamp.toInstant()));
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

    public final B formatString(final String FORMAT_STRING) {
        properties.put("formatString", new SimpleStringProperty(FORMAT_STRING));
        return (B)this;
    }

    public final B minValue(final double MIN_VALUE) {
        properties.put("minValue", new SimpleDoubleProperty(MIN_VALUE));
        return (B)this;
    }

    public final B maxValue(final double MAX_VALUE) {
        properties.put("maxValue", new SimpleDoubleProperty(MAX_VALUE));
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
                case "timestamp"          -> jcpu.setTimestamp(((ObjectProperty<Instant>) properties.get(key)).get());
                case "backgroundColor"    -> jcpu.setBackgroundColor(((ObjectProperty<Color>) properties.get(key)).get());
                case "foregroundColor"    -> jcpu.setForegroundColor(((ObjectProperty<Color>) properties.get(key)).get());
                case "titleColor"         -> jcpu.setTitleColor(((ObjectProperty<Color>) properties.get(key)).get());
                case "infoColor"          -> jcpu.setInfoColor(((ObjectProperty<Color>) properties.get(key)).get());
                //case "formatString"     -> jcpu.setFormatString(((StringProperty) properties.get(key)).get());
                //case "minValue"         -> jcpu.setMinValue(((DoubleProperty) properties.get(key)).get());
                //case "maxValue"         -> jcpu.setMaxValue(((DoubleProperty) properties.get(key)).get());
            }
        }
        return jcpu;
    }
}

