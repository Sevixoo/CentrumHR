package com.centrumhr.desktop.service.threading;

import com.centrumhr.application.application.common.IHandler;
import javafx.application.Platform;

public class HandlerThread  implements IHandler {

    private static IHandler sMainThread;

    private HandlerThread() { }

    @Override
    public void post(Runnable runnable) {
        Platform.runLater( runnable );
    }

    public static IHandler getInstance() {
        if (sMainThread == null) {
            sMainThread = new HandlerThread();
        }
        return sMainThread;
    }
}