package listeners;

import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.TestResult;
import utilities.FileManager;
import utilities.Logs;
import utilities.WebDriverProvider;

public class AllureListeners implements TestLifecycleListener {
    @Override
    public void beforeTestStop(TestResult result) {
        Logs.debug("Tomando evidencias para el reporte de Allure");

        final var status = result.getStatus();

        switch (status) {
            case BROKEN, FAILED -> {
                if (new WebDriverProvider().get() != null) {
                    FileManager.getScreenshot();
                    FileManager.getPageSource();
                }

            }


        }
    }
}
