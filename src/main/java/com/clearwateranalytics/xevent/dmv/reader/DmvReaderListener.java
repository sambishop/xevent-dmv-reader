package com.clearwateranalytics.xevent.dmv.reader;

public interface DmvReaderListener {
    void visitAction(Action action);

    void beginEventPackageVisit(Package package_);
    void endEventPackageVisit();

    void beginEventVisit(Event event);
    void endEventVisit();

    void visitEventColumn(EventColumn eventColumn);
}
