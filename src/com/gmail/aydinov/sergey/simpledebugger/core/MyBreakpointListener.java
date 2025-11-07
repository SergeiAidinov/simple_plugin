package com.gmail.aydinov.sergey.simpledebugger.core;

import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IBreakpointListener;
import org.eclipse.debug.core.model.IBreakpoint;

public class MyBreakpointListener implements IBreakpointListener {

    @Override
    public void breakpointAdded(IBreakpoint breakpoint) {
        System.out.println("BP ADDED: " + breakpoint);
    }

    @Override
    public void breakpointRemoved(IBreakpoint breakpoint, org.eclipse.core.resources.IMarkerDelta delta) {
        System.out.println("BP REMOVED: " + breakpoint);
    }

    @Override
    public void breakpointChanged(IBreakpoint breakpoint, org.eclipse.core.resources.IMarkerDelta delta) {
        System.out.println("BP CHANGED: " + breakpoint);
    }

    public void start() {
        DebugPlugin.getDefault().getBreakpointManager().addBreakpointListener(this);
    }

    public void stop() {
        DebugPlugin.getDefault().getBreakpointManager().removeBreakpointListener(this);
    }
}

