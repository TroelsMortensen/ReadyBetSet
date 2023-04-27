package Utility;

import javafx.application.Platform;

import java.util.concurrent.Semaphore;

public class WaitForRunLater {
    public static void waitForRunLater() {
        Semaphore semaphore = new Semaphore(0);
        Platform.runLater(() -> semaphore.release());
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
