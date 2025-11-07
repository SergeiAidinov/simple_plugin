package com.gmail.aydinov.sergey;

import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.gmail.aydinov.sergey.simpledebugger.core.MyBreakpointListener;
import com.gmail.aydinov.sergey.simpledebugger.core.SimpleDebuggerWorkFlow;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Activator implements BundleActivator {
	
	MyBreakpointListener listener = new MyBreakpointListener();

    @Override
    public void start(BundleContext context) throws Exception {
        // Записываем информацию о запускe
       // writeStartupInfo(context);
    	String host = System.getProperty("debug.host");
        String portStr = System.getProperty("debug.port");
        int port = portStr != null ? Integer.parseInt(portStr) : -1;
        System.out.println("Debug config: host=" + host + " port=" + port);
        SimpleDebuggerWorkFlow simpleDebuggerWorkFlow = SimpleDebuggerWorkFlow.instanceOfHostAndPort(host, port);
        Thread thread = new Thread(simpleDebuggerWorkFlow);
        thread.start();
        DebugPlugin.getDefault().getBreakpointManager().addBreakpointListener(listener);

     // SWT UI
        Display display = Display.getDefault(); // берём существующий Display
        Shell shell = new Shell(display);
        shell.setText("Simple Button Window");
        shell.setSize(300, 200);

        // Кнопка
        Button button = new Button(shell, SWT.PUSH);
        button.setText("Click Me!");
        button.setBounds(100, 70, 100, 30);

        button.addListener(SWT.Selection, e -> {
            System.out.println("Button clicked!");
            writeClickInfo(context);
        });

        shell.open();

        // Обработка событий SWT
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
		 
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Plugin stopped");
    }

    private File getPluginDataDir(BundleContext context) {
        // создаём подпапку plugin_data внутри стандартной директории плагина
        File stateDir = context.getDataFile("plugin_data");
        if (!stateDir.exists()) stateDir.mkdirs();
        return stateDir;
    }

    private void writeStartupInfo(BundleContext context) {
        try {
            File stateDir = getPluginDataDir(context);
            File logFile = new File(stateDir, "plugin_startup.log");
            try (FileWriter writer = new FileWriter(logFile, true)) {
                writer.write("Plugin started at " + java.time.LocalDateTime.now() + "\n");
            }
            System.out.println("Startup info written to: " + logFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeClickInfo(BundleContext context) {
        try {
            File stateDir = getPluginDataDir(context);
            File logFile = new File(stateDir, "plugin_startup.log");
            try (FileWriter writer = new FileWriter(logFile, true)) {
                writer.write("Button clicked at " + java.time.LocalDateTime.now() + "\n");
            }
            System.out.println("Click info written to: " + logFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}