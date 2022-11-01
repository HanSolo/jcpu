package eu.hansolo.fx.jcpu;

import eu.hansolo.toolbox.Helper;
import eu.hansolo.toolboxfx.font.Fonts;
import eu.hansolo.toolboxfx.geom.Bounds;
import javafx.beans.DefaultProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
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
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.time.Instant;
import java.util.Locale;


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
    private              Font                          font;
    private              Font                          nameFont;
    private              Font                          valueUnitFont;
    private              double                        bkgBarHeight;
    private              double                        fgdBarHeight;
    private              double                        areaHeight;
    private              double                        bkgBarPosY;
    private              double                        fgdBarPosY;
    private              double                        spacer;
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
    private              Color                         _value1Color;
    private              ObjectProperty<Color>         value1Color;
    private              Color                         _value2Color;
    private              ObjectProperty<Color>         value2Color;
    private              Color                         _value3Color;
    private              ObjectProperty<Color>         value3Color;
    private              Color                         _value1TextColor;
    private              ObjectProperty<Color>         value1TextColor;
    private              Color                         _value2TextColor;
    private              ObjectProperty<Color>         value2TextColor;
    private              Color                         _value3TextColor;
    private              ObjectProperty<Color>         value3TextColor;
    private              double                        _minValue1;
    private              DoubleProperty                minValue1;
    private              double                        _maxValue1;
    private              DoubleProperty                maxValue1;
    private              double                        _value1;
    private              DoubleProperty                value1;
    private              double                        value1Factor;
    private              double                        _minValue2;
    private              DoubleProperty                minValue2;
    private              double                        _maxValue2;
    private              DoubleProperty                maxValue2;
    private              double                        _value2;
    private              DoubleProperty                value2;
    private              double                        value2Factor;
    private              double                        _minValue3;
    private              DoubleProperty                minValue3;
    private              double                        _maxValue3;
    private              DoubleProperty                maxValue3;
    private              double                        _value3;
    private              DoubleProperty                value3;
    private              double                        value3Factor;
    private              String                        _nameValue1;
    private              StringProperty                nameValue1;
    private              String                        _nameValue2;
    private              StringProperty                nameValue2;
    private              String                        _nameValue3;
    private              StringProperty                nameValue3;
    private              String                        _formatStringValue1;
    private              StringProperty                formatStringValue1;
    private              String                        _formatStringValue2;
    private              StringProperty                formatStringValue2;
    private              String                        _formatStringValue3;
    private              StringProperty                formatStringValue3;


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
        _value1Color        = Color.ORANGE;
        _value2Color        = Color.LIME;
        _value3Color        = Color.RED;
        _value1TextColor    = Constants.DEFAULT_UNIT_VALUE_COLOR;
        _value2TextColor    = Constants.DEFAULT_UNIT_VALUE_COLOR;
        _value3TextColor    = Constants.DEFAULT_UNIT_VALUE_COLOR;
        _minValue1          = 0;
        _maxValue1          = 100;
        _minValue2          = 0;
        _maxValue2          = 100;
        _minValue3          = 0;
        _maxValue3          = 100;
        _value1             = 0;
        value1Factor        = 0;
        _value2             = 0;
        value2Factor        = 0;
        _value3             = 0;
        value3Factor        = 0;
        _nameValue1         = "";
        _nameValue2         = "";
        _nameValue3         = "";
        _formatStringValue1 = "%.0f";
        _formatStringValue2 = "%.0f";
        _formatStringValue3 = "%.0f";
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

    public Color getValue1Color() { return null == value1Color ? _value1Color : value1Color.get(); }
    public void setValue1Color(final Color value1Color) {
        if (null == this.value1Color) {
            _value1Color = value1Color;
            redraw();
        } else {
            this.value1Color.set(value1Color);
        }
    }
    public ObjectProperty<Color> value1ColorProperty() {
        if (null == value1Color) {
            value1Color  = new ObjectPropertyBase<>(_value1Color) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "value1Color"; }
            };
            _value1Color = null;
        }
        return value1Color;
    }

    public Color getValue2Color() { return null == value2Color ? _value2Color : value2Color.get(); }
    public void setValue2Color(final Color value2Color) {
        if (null == this.value2Color) {
            _value2Color = value2Color;
            redraw();
        } else {
            this.value2Color.set(value2Color);
        }
    }
    public ObjectProperty<Color> value2ColorProperty() {
        if (null == value2Color) {
            value2Color  = new ObjectPropertyBase<>(_value2Color) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "value2Color"; }
            };
            _value2Color = null;
        }
        return value2Color;
    }
    
    public Color getValue3Color() { return null == value3Color ? _value3Color : value3Color.get(); }
    public void setValue3Color(final Color value3Color) {
        if (null == this.value3Color) {
            _value3Color = value3Color;
            redraw();
        } else {
            this.value3Color.set(value3Color);
        }
    }
    public ObjectProperty<Color> value3ColorProperty() {
        if (null == value3Color) {
            value3Color  = new ObjectPropertyBase<>(_value3Color) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "value3Color"; }
            };
            _value3Color = null;
        }
        return value3Color;
    }

    public Color getValue1TextColor() { return null == value1TextColor ? _value1TextColor : value1TextColor.get(); }
    public void setValue1TextColor(final Color value1TextColor) {
        if (null == this.value1TextColor) {
            _value1TextColor = value1TextColor;
            redraw();
        } else {
            this.value1TextColor.set(value1TextColor);
        }
    }
    public ObjectProperty<Color> value1TextColorProperty() {
        if (null == value1TextColor) {
            value1TextColor  = new ObjectPropertyBase<>(_value1TextColor) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "bar1Color"; }
            };
            _value1TextColor = null;
        }
        return value1TextColor;
    }

    public Color getValue2TextColor() { return null == value2TextColor ? _value2TextColor : value2TextColor.get(); }
    public void setValue2TextColor(final Color value2TextColor) {
        if (null == this.value2TextColor) {
            _value2TextColor = value2TextColor;
            redraw();
        } else {
            this.value2TextColor.set(value2TextColor);
        }
    }
    public ObjectProperty<Color> value2TextColorProperty() {
        if (null == value2TextColor) {
            value2TextColor  = new ObjectPropertyBase<>(_value2TextColor) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "bar2Color"; }
            };
            _value2TextColor = null;
        }
        return value2TextColor;
    }

    public Color getValue3TextColor() { return null == value3TextColor ? _value3TextColor : value3TextColor.get(); }
    public void setValue3TextColor(final Color value3TextColor) {
        if (null == this.value3TextColor) {
            _value3TextColor = value3TextColor;
            redraw();
        } else {
            this.value3TextColor.set(value3TextColor);
        }
    }
    public ObjectProperty<Color> value3TextColorProperty() {
        if (null == value3TextColor) {
            value3TextColor  = new ObjectPropertyBase<>(_value3TextColor) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "value3TextColor"; }
            };
            _value3TextColor = null;
        }
        return value3TextColor;
    }

    public double getMinValue1() { return null == minValue1 ? _minValue1 : minValue1.get(); }
    public void setMinValue1(final double minValue1) {
        if (null == this.minValue1) {
            if (minValue1 > getMaxValue1()) { setMaxValue1(minValue1); }
            _minValue1 = Helper.clamp(-Double.MAX_VALUE, getMaxValue1(), minValue1);
            calculateValue1Factor();
            redraw();
        } else {
            this.minValue1.set(minValue1);
        }
    }
    public DoubleProperty minValue1Property() {
        if (null == minValue1) {
            minValue1 = new DoublePropertyBase(_minValue1) {
                @Override protected void invalidated() {
                    double value = get();
                    if (value > getMaxValue1()) { setMaxValue1(value); }
                    calculateValue1Factor();
                    redraw();
                }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "minValue1"; }
            };
        }
        return minValue1;
    }

    public double getMaxValue1() { return null == maxValue1 ? _maxValue1 : maxValue1.get(); }
    public void setMaxValue1(final double maxValue1) {
        if (null == this.maxValue1) {
            if (maxValue1 < getMinValue1()) { setMinValue1(maxValue1); }
            _maxValue1 = Helper.clamp(getMinValue1(), Double.MAX_VALUE, maxValue1);
            calculateValue1Factor();
        } else {
            this.maxValue1.set(maxValue1);
        }
    }
    public DoubleProperty maxValue1Property() {
        if (null == maxValue1) {
            maxValue1 = new DoublePropertyBase(_maxValue1) {
                @Override protected void invalidated() {
                    final double value = get();
                    if (value < getMinValue1()) setMinValue1(value);
                    calculateValue1Factor();
                    redraw();
                }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "maxValue1"; }
            };
        }
        return maxValue1;
    }
    
    public double getValue1() { return null == value1 ? _value1 : value1.get(); }
    public void setValue1(final double value1) {
        if (null == this.value1) {
            _value1 = Helper.clamp(getMinValue1(), getMaxValue1(), value1);
            calculateValue1Factor();
            redraw();
        } else {
            this.value1.set(value1);
        }
    }
    public DoubleProperty value1Property() {
        if (null == value1) {
            value1 = new DoublePropertyBase(_value1) {
                @Override protected void invalidated() {
                    set(Helper.clamp(getMinValue1(), getMaxValue1(), get()));
                    calculateValue1Factor();
                    redraw();
                }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "value1"; }
            };
        }
        return value1;
    }

    public double getMinValue2() { return null == minValue2 ? _minValue2 : minValue2.get(); }
    public void setMinValue2(final double minValue2) {
        if (null == this.minValue2) {
            if (minValue2 > getMaxValue2()) { setMaxValue2(minValue2); }
            _minValue2 = Helper.clamp(-Double.MAX_VALUE, getMaxValue2(), minValue2);
            calculateValue2Factor();
            redraw();
        } else {
            this.minValue2.set(minValue2);
        }
    }
    public DoubleProperty minValue2Property() {
        if (null == minValue2) {
            minValue2 = new DoublePropertyBase(_minValue2) {
                @Override protected void invalidated() {
                    double value = get();
                    if (value > getMaxValue2()) { setMaxValue2(value); }
                    calculateValue2Factor();
                    redraw();
                }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "minValue2"; }
            };
        }
        return minValue2;
    }

    public double getMaxValue2() { return null == maxValue2 ? _maxValue2 : maxValue2.get(); }
    public void setMaxValue2(final double maxValue2) {
        if (null == this.maxValue2) {
            if (maxValue2 < getMinValue2()) { setMinValue2(maxValue2); }
            _maxValue2 = Helper.clamp(getMinValue2(), Double.MAX_VALUE, maxValue2);
            calculateValue2Factor();
            redraw();
        } else {
            this.maxValue2.set(maxValue2);
        }
    }
    public DoubleProperty maxValue2Property() {
        if (null == maxValue2) {
            maxValue2 = new DoublePropertyBase(_maxValue2) {
                @Override protected void invalidated() {
                    final double value = get();
                    if (value < getMinValue2()) setMinValue2(value);
                    calculateValue2Factor();
                    redraw();
                }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "maxValue2"; }
            };
        }
        return maxValue2;
    }
    
    public double getValue2() { return null == value2 ? _value2 : value2.get(); }
    public void setValue2(final double value2) {
        if (null == this.value2) {
            _value2 = Helper.clamp(getMinValue2(), getMaxValue2(), value2);
            calculateValue2Factor();
            redraw();
        } else {
            this.value2.set(value2);
        }
    }
    public DoubleProperty value2Property() {
        if (null == value2) {
            value2 = new DoublePropertyBase(_value2) {
                @Override protected void invalidated() {
                    set(Helper.clamp(getMinValue2(), getMaxValue2(), get()));
                    calculateValue2Factor();
                    redraw();
                }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "value2"; }
            };
        }
        return value2;
    }

    public double getMinValue3() { return null == minValue3 ? _minValue3 : minValue3.get(); }
    public void setMinValue3(final double minValue3) {
        if (null == this.minValue3) {
            if (minValue3 > getMaxValue3()) { setMaxValue3(minValue3); }
            _minValue3 = Helper.clamp(-Double.MAX_VALUE, getMaxValue3(), minValue3);
            calculateValue3Factor();
            redraw();
        } else {
            this.minValue3.set(minValue3);
        }
    }
    public DoubleProperty minValue3Property() {
        if (null == minValue3) {
            minValue3 = new DoublePropertyBase(_minValue3) {
                @Override protected void invalidated() {
                    double value = get();
                    if (value > getMaxValue3()) { setMaxValue3(value); }
                    calculateValue3Factor();
                    redraw();
                }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "minValue3"; }
            };
        }
        return minValue3;
    }

    public double getMaxValue3() { return null == maxValue3 ? _maxValue3 : maxValue3.get(); }
    public void setMaxValue3(final double maxValue3) {
        if (null == this.maxValue3) {
            if (maxValue3 < getMinValue3()) { setMinValue3(maxValue3); }
            _maxValue3 = Helper.clamp(getMinValue3(), Double.MAX_VALUE, maxValue3);
            calculateValue3Factor();
            redraw();
        } else {
            this.maxValue3.set(maxValue3);
        }
    }
    public DoubleProperty maxValue3Property() {
        if (null == maxValue3) {
            maxValue3 = new DoublePropertyBase(_maxValue3) {
                @Override protected void invalidated() {
                    final double value = get();
                    if (value < getMinValue3()) setMinValue3(value);
                    calculateValue3Factor();
                    redraw();
                }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "maxValue3"; }
            };
        }
        return maxValue3;
    }
    
    public double getValue3() { return null == value3 ? _value3 : value3.get(); }
    public void setValue3(final double value3) {
        if (null == this.value3) {
            _value3 = Helper.clamp(getMinValue3(), getMaxValue3(), value3);
            calculateValue3Factor();
            redraw();
        } else {
            this.value3.set(value3);
        }
    }
    public DoubleProperty value3Property() {
        if (null == value3) {
            value3 = new DoublePropertyBase(_value3) {
                @Override protected void invalidated() {
                    set(Helper.clamp(getMinValue3(), getMaxValue3(), get()));
                    calculateValue3Factor();
                    redraw();
                }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "value3"; }
            };
        }
        return value3;
    }

    public String getNameValue1() { return null == nameValue1 ? _nameValue1 : nameValue1.get(); }
    public void setNameValue1(final String nameValue1) {
        if (null == this.nameValue1) {
            _nameValue1 = nameValue1;
            redraw();
        } else {
            this.nameValue1.set(nameValue1);
        }
    }
    public StringProperty nameValue1Property() {
        if (null == nameValue1) {
            nameValue1 = new StringPropertyBase(_nameValue1) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "nameValue1"; }
            };
            _nameValue1 = null;
        }
        return nameValue1;
    }

    public String getNameValue2() { return null == nameValue2 ? _nameValue2 : nameValue2.get(); }
    public void setNameValue2(final String nameValue2) {
        if (null == this.nameValue2) {
            _nameValue2 = nameValue2;
            redraw();
        } else {
            this.nameValue2.set(nameValue2);
        }
    }
    public StringProperty nameValue2Property() {
        if (null == nameValue2) {
            nameValue2 = new StringPropertyBase(_nameValue2) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "nameValue2"; }
            };
            _nameValue2 = null;
        }
        return nameValue2;
    }

    public String getNameValue3() { return null == nameValue3 ? _nameValue3 : nameValue3.get(); }
    public void setNameValue3(final String nameValue3) {
        if (null == this.nameValue3) {
            _nameValue3 = nameValue3;
            redraw();
        } else {
            this.nameValue3.set(nameValue3);
        }
    }
    public StringProperty nameValue3Property() {
        if (null == nameValue3) {
            nameValue3 = new StringPropertyBase(_nameValue3) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "nameValue3"; }
            };
            _nameValue3 = null;
        }
        return nameValue3;
    }

    public String getFormatStringValue1() { return null == formatStringValue1 ? _formatStringValue1 : formatStringValue1.get(); }
    public void setFormatStringValue1(final String formatStringValue1) {
        if (null == this.formatStringValue1) {
            _formatStringValue1 = formatStringValue1;
            redraw();
        } else {
            this.formatStringValue1.set(formatStringValue1);
        }
    }
    public StringProperty formatStringValue1Property() {
        if (null == formatStringValue1) {
            formatStringValue1 = new StringPropertyBase(_formatStringValue1) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "formatStringValue1"; }
            };
            _formatStringValue1 = null;
        }
        return formatStringValue1;
    }

    public String getFormatStringValue2() { return null == formatStringValue2 ? _formatStringValue2 : formatStringValue2.get(); }
    public void setFormatStringValue2(final String formatStringValue2) {
        if (null == this.formatStringValue2) {
            _formatStringValue2 = formatStringValue2;
            redraw();
        } else {
            this.formatStringValue2.set(formatStringValue2);
        }
    }
    public StringProperty formatStringValue2Property() {
        if (null == formatStringValue2) {
            formatStringValue2 = new StringPropertyBase(_formatStringValue2) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "formatStringValue2"; }
            };
            _formatStringValue2 = null;
        }
        return formatStringValue2;
    }

    public String getFormatStringValue3() { return null == formatStringValue3 ? _formatStringValue3 : formatStringValue3.get(); }
    public void setFormatStringValue3(final String formatStringValue3) {
        if (null == this.formatStringValue3) {
            _formatStringValue3 = formatStringValue3;
            redraw();
        } else {
            this.formatStringValue3.set(formatStringValue3);
        }
    }
    public StringProperty formatStringValue3Property() {
        if (null == formatStringValue3) {
            formatStringValue3 = new StringPropertyBase(_formatStringValue3) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return Jcpu.this; }
                @Override public String getName() { return "formatStringValue3"; }
            };
            _formatStringValue3 = null;
        }
        return formatStringValue3;
    }
    
    
    private void calculateValue1Factor() { value1Factor = getValue1() / (getMaxValue1() - getMinValue1()); }
    private void calculateValue2Factor() { value2Factor = getValue2() / (getMaxValue2() - getMinValue2()); }
    private void calculateValue3Factor() { value3Factor = getValue3() / (getMaxValue3() - getMinValue3()); }


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
            insetLeft         = minSize * 0.02;
            insetRight        = minSize * 0.02;
            insetTop          = minSize * 0.1;
            insetBottom       = minSize * 0.12;
            contentBounds.set(insetLeft, insetTop, width - insetLeft - insetRight, height - insetTop - insetBottom);
            font              = Fonts.latoRegular(minSize * 0.075);
            nameFont          = Fonts.latoRegular(minSize * 0.09);
            valueUnitFont     = Fonts.latoRegular(minSize * 0.09);
            spacer            = contentBounds.getHeight() * 0.05;
            bkgBarHeight      = contentBounds.getHeight() * 0.02;
            fgdBarHeight      = contentBounds.getHeight() * 0.125;
            areaHeight        = contentBounds.getHeight() * 0.3;
            fgdBarPosY        = areaHeight * 0.55;
            bkgBarPosY        = fgdBarPosY + (fgdBarHeight - bkgBarHeight) * 0.5;
            canvas.setWidth(width);
            canvas.setHeight(height);
            canvas.relocate(getInsets().getLeft(), getInsets().getTop());

            redraw();
        }
    }

    private void redraw() {
        // ******************** Background ************************************
        ctx.clearRect(0, 0, width, height);
        ctx.setFill(getBackgroundColor());
        ctx.fillRoundRect(0, 0, width, height, minSize * 0.05, minSize * 0.05);

        // ******************** Title *****************************************
        ctx.setFill(getTitleColor());
        ctx.setFont(font);
        ctx.setTextBaseline(VPos.CENTER);
        ctx.setTextAlign(getTitleTextAlignment());
        switch(getTitleTextAlignment()) {
            case LEFT   -> ctx.fillText(getTitle(), insetLeft, insetTop * 0.5, width);
            case CENTER -> ctx.fillText(getTitle(), width * 0.5, insetTop * 0.5, width);
            case RIGHT  -> ctx.fillText(getTitle(), width - insetRight, insetTop * 0.5, width);
            default     -> ctx.fillText(getTitle(), insetLeft, insetTop * 0.5, width);
        }

        // ******************** Info ******************************************
        ctx.setFill(getInfoColor());
        ctx.setTextAlign(getInfoTextAlignment());
        switch(getInfoTextAlignment()) {
            case LEFT   -> ctx.fillText(getInfo(), insetLeft, height - insetBottom * 0.5, width);
            case CENTER -> ctx.fillText(getInfo(), width * 0.5, height - insetBottom * 0.5, width);
            case RIGHT  -> ctx.fillText(getInfo(), width - insetRight, height - insetBottom * 0.5, width);
            default     -> ctx.fillText(getInfo(), insetLeft, height - insetBottom * 0.5, width);
        }

        // ******************** Content Area **********************************
        ctx.setTextBaseline(VPos.TOP);
        // Bar Value 1
        ctx.setFill(getForegroundColor());
        ctx.fillRect(contentBounds.getX(), contentBounds.getY() + bkgBarPosY, contentBounds.getWidth(), bkgBarHeight);
        ctx.setFill(getValue1Color());
        ctx.fillRect(contentBounds.getX(), contentBounds.getY() + fgdBarPosY, contentBounds.getWidth() * value1Factor, fgdBarHeight);
        ctx.setFill(getValue1TextColor());
        ctx.setTextAlign(TextAlignment.LEFT);
        ctx.setFont(valueUnitFont);
        ctx.fillText(getNameValue1(), insetLeft, contentBounds.getY(), width * 0.5);
        ctx.setTextAlign(TextAlignment.RIGHT);
        ctx.setFont(valueUnitFont);
        ctx.fillText(String.format(Locale.US, getFormatStringValue1(), getValue1()), width - insetRight, contentBounds.getY(), width * 0.5);

        // Bar Value 2
        ctx.setFill(getForegroundColor());
        ctx.fillRect(contentBounds.getX(), contentBounds.getY() + areaHeight + spacer + bkgBarPosY, contentBounds.getWidth(), bkgBarHeight);
        ctx.setFill(getValue2Color());
        ctx.fillRect(contentBounds.getX(), contentBounds.getY() + areaHeight + spacer + fgdBarPosY, contentBounds.getWidth() * value2Factor, fgdBarHeight);
        ctx.setFont(nameFont);
        ctx.setFill(getValue2TextColor());
        ctx.setTextAlign(TextAlignment.LEFT);
        ctx.fillText(getNameValue2(), insetLeft, contentBounds.getY() + areaHeight + spacer, width * 0.5);
        ctx.setTextAlign(TextAlignment.RIGHT);
        ctx.setFont(valueUnitFont);
        ctx.fillText(String.format(Locale.US, getFormatStringValue2(), getValue2()), width - insetRight, contentBounds.getY() + areaHeight + spacer);

        // Bar Value 3
        ctx.setFill(getForegroundColor());
        ctx.fillRect(contentBounds.getX(), contentBounds.getY() + areaHeight + spacer + areaHeight + spacer + bkgBarPosY, contentBounds.getWidth(), bkgBarHeight);
        ctx.setFill(getValue3Color());
        ctx.fillRect(contentBounds.getX(), contentBounds.getY() + areaHeight + spacer + areaHeight + spacer + fgdBarPosY, contentBounds.getWidth() * value3Factor, fgdBarHeight);
        ctx.setFont(nameFont);
        ctx.setFill(getValue3TextColor());
        ctx.setTextAlign(TextAlignment.LEFT);
        ctx.fillText(getNameValue3(), insetLeft, contentBounds.getY() + areaHeight + spacer + areaHeight + spacer);
        ctx.setTextAlign(TextAlignment.RIGHT);
        ctx.setFont(valueUnitFont);
        ctx.fillText(String.format(Locale.US, getFormatStringValue3(), getValue3()), width - insetRight, contentBounds.getY() + areaHeight + spacer + areaHeight + spacer);
    }
}