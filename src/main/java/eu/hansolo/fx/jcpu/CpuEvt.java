package eu.hansolo.fx.jcpu;

import eu.hansolo.toolbox.evt.EvtPriority;
import eu.hansolo.toolbox.evt.EvtType;
import eu.hansolo.toolbox.evt.type.ChangeEvt;


public class CpuEvt extends ChangeEvt {
    public static final EvtType<CpuEvt> ANY    = new EvtType<>(ChangeEvt.ANY, "ANY");
    public static final EvtType<CpuEvt> UPDATE = new EvtType<>(CpuEvt.ANY, "UPDATE");
    public static final EvtType<CpuEvt> RESIZE = new EvtType<>(CpuEvt.ANY, "RESIZE");
    

    private final Data data;


    // ******************** Constructors **************************************
    public CpuEvt(final EvtType<? extends CpuEvt> evtType, final Data data) {
        super(evtType);
        this.data = data;
    }
    public CpuEvt(final Object src, final EvtType<? extends CpuEvt> evtType) {
        super(src, evtType);
        this.data = null;
    }
    public CpuEvt(final Object src, final EvtType<? extends CpuEvt> evtType, final Data data) {
        super(src, evtType);
        this.data = data;
    }
    public CpuEvt(final Object src, final EvtType<? extends CpuEvt> evtType, final EvtPriority priority) {
        super(src, evtType, priority);
        this.data = null;
    }
    public CpuEvt(final Object src, final EvtType<? extends CpuEvt> evtType, final EvtPriority priority, final Data data) {
        super(src, evtType, priority);
        this.data = data;
    }


    // ******************** Methods *******************************************
    public EvtType<? extends CpuEvt> getEvtType() { return (EvtType<? extends CpuEvt>) super.getEvtType(); }

    public Data getData() { return data; }
}
