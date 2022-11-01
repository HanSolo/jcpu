package eu.hansolo.fx.jcpu;

import eu.hansolo.toolboxfx.font.Fonts;
import eu.hansolo.toolboxfx.geom.Bounds;
import javafx.beans.DefaultProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.StringProperty;
import javafx.beans.property.StringPropertyBase;
import javafx.collections.ObservableList;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.time.Instant;


/**
 * User: hansolo
 * Date: 01.11.22
 * Time: 09:22
 */
@DefaultProperty("children")
public class Jcpu extends Region {
    private static final double                        PREFERRED_WIDTH  = 250;
    private static final double                        PREFERRED_HEIGHT = 250;
    private static final double                        MINIMUM_WIDTH    = 50;
    private static final double                        MINIMUM_HEIGHT   = 50;
    private static final double                        MAXIMUM_WIDTH    = 1024;
    private static final double                        MAXIMUM_HEIGHT   = 1024;
    private static       String                        userAgentStyleSheet;
    private              double                        width;
    private              double                        height;
    private              double                        minSize;
    private              Canvas                        canvas;
    private              GraphicsContext               ctx;
    private              double                        insetTop;
    private              double                        insetBottom;
    private              double                        insetLeft;
    private              double                        insetRight;
    private              Bounds                        contentBounds;
    private              double                        fontSize;
    private              Color                         _backgroundColor;
    private              ObjectProperty<Color>         backgroundColor;
    private              Color                         _foregroundColor;
    private              ObjectProperty<Color>         foregroundColor;
    private              Color                         _titleColor;
    private              ObjectProperty<Color>         titleColor;
    private              Color                         _infoColor;
    private              ObjectProperty<Color>         infoColor;
    private              String                        _title;
    private              StringProperty                title;
    private              String                        _info;
    private              StringProperty                info;
    private              TextAlignment                 _titleTextAlignment;
    private              ObjectProperty<TextAlignment> titleTextAlignment;
    private              TextAlignment                 _infoTextAlignment;
    private              ObjectProperty<TextAlignment> infoTextAlignment;
    private              Instant                       _timestamp;
    private              ObjectProperty<Instant>       timestamp;


    // ******************** Constructors **************************************
    public Jcpu() {
        contentBounds       = new Bounds(5, 5, PREFERRED_WIDTH - 10, PREFERRED_HEIGHT - 10);
        _backgroundColor    = Constants.DEFAULT_BACKGROUND_COLOR;
        _foregroundColor    = Constants.DEFAULT_FOREGROUND_COLOR;
        _titleColor         = Constants.DEFAULT_FOREGROUND_COLOR;
        _infoColor          = Constants.DEFAULT_FOREGROUND_COLOR;
        _title              = "";
        _info               = "";
        _titleTextAlignment = TextAlignment.LEFT;
        _infoTextAlignment  = TextAlignment.LEFT;
        _timestamp          = Instant.now();
        initGraphics();
        registerListeners();
    }


    // ******************** Initialization ************************************
    private void initGraphics() {
        if (Double.compare(getPrefWidth(), 0.0) <= 0 || Double.compare(getPrefHeight(), 0.0) <= 0 || Double.compare(getWidth(), 0.0) <= 0 || Double.compare(getHeight(), 0.0) <= 0) {
            if (getPrefWidth() > 0 && getPrefHeight() > 0) {
                setPrefSize(getPrefWidth(), getPrefHeight());
            } else {
                setPrefSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
            }
        }

        getStyleClass().add("jcpu");

        canvas = new Canvas(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        ctx    = canvas.getGraphicsContext2D();

        getChildren().setAll(canvas);
    }

    private void registerListeners() {
        widthProperty().addListener(o -> resize());
        heightProperty().addListener(o -> resize());
    }


    // ******************** Methods *******************************************
    @Override protected double computeMinWidth(final double height) { return MINIMUM_WIDTH; }
    @Override protected double computeMinHeight(final double width)  { return MINIMUM_HEIGHT; }
    @Override protected double computePrefWidth(final double height) { return super.computePrefWidth(height); }
    @Override protected double computePrefHeight(final double width) { return super.computePrefHeight(width); }
    @Override protected double computeMaxWidth(final double height)  { return MAXIMUM_WIDTH; }
    @Override protected double computeMaxHeight(final double width)  { return MAXIMUM_HEIGHT; }

    @Override public ObservableList<Node> getChildren()              { return super.getChildren(); }

    public Color getBackgroundColor() { return null == backgroundColor ? _backgroundColor : backgroundColor.get(); }
    public void setBackgroundColor(final Color backgroundColor) {
        if (null == this.backgroundColor) {
            _backgroundColor = backgroundColor;
            redraw();
        } else {
            this.backgroundColor.set(backgroundColor);
        }
    }
    public ObjectProperty<Color> backgroundColorProperty() {
        if (null == backgroundColor) {
            backgroundColor = new ObjectPropertyBase<>(_backgroundColor) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "backgroundColor"; }
            };
            _backgroundColor = null;
        }
        return backgroundColor;
    }

    public Color getForegroundColor() { return null == foregroundColor ? _foregroundColor : foregroundColor.get(); }
    public void setForegroundColor(final Color foregroundColor) {
        if (null == this.foregroundColor) {
            _foregroundColor = foregroundColor;
            redraw();
        } else {
            this.foregroundColor.set(foregroundColor);
        }
    }
    public ObjectProperty<Color> foregroundColorProperty() {
        if (null == foregroundColor) {
            foregroundColor = new ObjectPropertyBase<>(_foregroundColor) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "foregroundColor"; }
            };
            _foregroundColor = null;
        }
        return foregroundColor;
    }

    public Color getTitleColor() { return null == titleColor ? _titleColor : titleColor.get(); }
    public void setTitleColor(final Color titleColor) {
        if (null == this.titleColor) {
            _titleColor = titleColor;
            redraw();
        } else {
            this.titleColor.set(titleColor);
        }
    }
    public ObjectProperty<Color> titleColorProperty() {
        if (null == titleColor) {
            titleColor = new ObjectPropertyBase<>(_titleColor) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "titleColor"; }
            };
            _titleColor = null;
        }
        return titleColor;
    }

    public Color getInfoColor() { return null == infoColor ? _infoColor : infoColor.get(); }
    public void setInfoColor(final Color infoColor) {
        if (null == this.infoColor) {
            _infoColor = infoColor;
            redraw();
        } else {
            this.infoColor.set(infoColor);
        }
    }
    public ObjectProperty<Color> infoColorProperty() {
        if (null == infoColor) {
            infoColor = new ObjectPropertyBase<>(_infoColor) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "infoColor"; }
            };
            _infoColor = null;
        }
        return infoColor;
    }

    public String getTitle() { return null == title ? _title : title.get(); }
    public void setTitle(final String title) {
        if (null == this.title) {
            _title = title;
            redraw();
        } else {
            this.title.set(title);
        }
    }
    public StringProperty titleProperty() {
        title = new StringPropertyBase(_title) {
            @Override protected void invalidated() { redraw(); }
            @Override public Object getBean() { return Jcpu.this; }
            @Override public String getName() { return "title"; }
        };
        _title = null;
        return title;
    }

    public String getInfo() { return null == info ? _info : info.get(); }
    public void setInfo(final String info) {
        if (null == this.info) {
            _info = info;
            redraw();
        } else {
            this.info.set(info);
        }
    }
    public StringProperty infoProperty() {
        info = new StringPropertyBase(_info) {
            @Override protected void invalidated() { redraw(); }
            @Override public Object getBean() { return Jcpu.this; }
            @Override public String getName() { return "info"; }
        };
        _info = null;
        return info;
    }
    
    public TextAlignment getTitleTextAlignment() { return null == titleTextAlignment ? _titleTextAlignment : titleTextAlignment.get(); }
    public void setTitleTextAlignment(final TextAlignment textAlignment) {
        if (null == this.titleTextAlignment) {
            _titleTextAlignment = textAlignment;
            redraw();
        } else {
            this.titleTextAlignment.set(textAlignment);
        }
    }
    public ObjectProperty<TextAlignment> titleTextAlignmentProperty() {
        if (null == titleTextAlignment) {
            titleTextAlignment = new ObjectPropertyBase<>(_titleTextAlignment) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "titleTextAlignment"; }
            };
            _titleTextAlignment = null;
        }
        return titleTextAlignment;
    }

    public TextAlignment getInfoTextAlignment() { return null == infoTextAlignment ? _infoTextAlignment : infoTextAlignment.get(); }
    public void setInfoTextAlignment(final TextAlignment textAlignment) {
        if (null == this.infoTextAlignment) {
            _infoTextAlignment = textAlignment;
            redraw();
        } else {
            this.infoTextAlignment.set(textAlignment);
        }
    }
    public ObjectProperty<TextAlignment> infoTextAlignmentProperty() {
        if (null == infoTextAlignment) {
            infoTextAlignment = new ObjectPropertyBase<>(_infoTextAlignment) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "infoTextAlignment"; }
            };
            _infoTextAlignment = null;
        }
        return infoTextAlignment;
    }

    public Instant getTimestamp() { return null == timestamp ? _timestamp : timestamp.get(); }
    public void setTimestamp(final Instant timestamp) {
        if (null == this.timestamp) {
            _timestamp = timestamp;
            redraw();
        } else {
            this.timestamp.set(timestamp);
        }
    }
    public ObjectProperty<Instant> timestampProperty() {
        if (null == timestamp) {
            timestamp = new ObjectPropertyBase<>(_timestamp) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "timestamp"; }
            };
            _timestamp = null;
        }
        return timestamp;
    }


    // ******************** Layout *******************************************
    @Override public void layoutChildren() {
        super.layoutChildren();
    }

    @Override public String getUserAgentStylesheet() {
        if (null == userAgentStyleSheet) { userAgentStyleSheet = Jcpu.class.getResource("jcpu.css").toExternalForm(); }
        return userAgentStyleSheet;
    }

    private void resize() {
        width       = getWidth() - getInsets().getLeft() - getInsets().getRight();
        height      = getHeight() - getInsets().getTop() - getInsets().getBottom();
        minSize     = width < height ? width : height;

        if (width > 0 && height > 0) {
            insetLeft   = minSize * 0.02;
            insetRight  = minSize * 0.02;
            insetTop    = minSize * 0.1;
            insetBottom = minSize * 0.1;
            fontSize    = minSize * 0.08;
            contentBounds.set(insetLeft, insetTop, width - insetLeft - insetRight, height - insetTop - insetBottom);
            canvas.setWidth(width);
            canvas.setHeight(height);
            canvas.relocate(getInsets().getLeft(), getInsets().getTop());

            redraw();
        }
    }

    private void redraw() {
        // Background
        ctx.clearRect(0, 0, width, height);
        ctx.setFill(getBackgroundColor());
        ctx.fillRect(0, 0, width, height);

        // Title
        ctx.setFill(getTitleColor());
        ctx.setFont(Fonts.latoRegular(fontSize));
        ctx.setTextBaseline(VPos.CENTER);
        ctx.setTextAlign(getTitleTextAlignment());
        switch(getTitleTextAlignment()) {
            case LEFT   -> ctx.fillText(getTitle(), insetLeft, insetTop * 0.5, width);
            case CENTER -> ctx.fillText(getTitle(), width * 0.5, insetTop * 0.5, width);
            case RIGHT  -> ctx.fillText(getTitle(), width - insetRight, insetTop * 0.5, width);
            default     -> ctx.fillText(getTitle(), insetLeft, insetTop * 0.5, width);
        }

        // Info
        ctx.setFill(getInfoColor());
        ctx.setTextAlign(getInfoTextAlignment());
        switch(getInfoTextAlignment()) {
            case LEFT   -> ctx.fillText(getInfo(), insetLeft, height - insetBottom * 0.5, width);
            case CENTER -> ctx.fillText(getInfo(), width * 0.5, height - insetBottom * 0.5, width);
            case RIGHT  -> ctx.fillText(getInfo(), width - insetRight, height - insetBottom * 0.5, width);
            default     -> ctx.fillText(getInfo(), insetLeft, height - insetBottom * 0.5, width);
        }

        // Content Area
        ctx.setFill(Color.RED);
        ctx.fillRect(contentBounds.getX(), contentBounds.getY(), contentBounds.getWidth(), contentBounds.getHeight());
    }
}