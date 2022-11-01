module eu.hansolo.fx.jcpu {
    // Java

    // Java-FX
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.swing;

    // 3rd party
    requires transitive eu.hansolo.toolbox;
    requires transitive eu.hansolo.toolboxfx;

    opens eu.hansolo.fx.jcpu to eu.hansolo.toolbox, eu.hansolo.toolboxfx;

    exports eu.hansolo.fx.jcpu;
}