package com.clearwateranalytics.xevent.dmv.reader;

public class DmvPrinter implements DmvReaderListener {
    @Override
    public void visitAction(Action action) {
        System.err.println("visit action: " + action);
    }

    @Override
    public void beginEventPackageVisit(Package package_) {
        System.err.println("begin event package: " + package_);
    }

    @Override
    public void endEventPackageVisit() {
        System.err.println("end event package");
    }

    @Override
    public void beginEventVisit(Event event) {
        System.err.println("\tbegin event: " + event);
    }

    @Override
    public void endEventVisit() {
        System.err.println("\tend event");
    }

    @Override
    public void visitEventColumn(EventColumn eventColumn) {
        System.err.println("\t\t" + eventColumn);
    }   
}
