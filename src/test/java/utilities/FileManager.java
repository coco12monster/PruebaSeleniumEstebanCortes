package utilities;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private static final String screenshotPath = "src/test/resources/screenshots";
    private static final String pageSourcePath = "src/test/resources/pageSource";

    public static void getScreenshot(String screenshotName) {
        Logs.debug("Tomando screenshot");

        final var screenshotFile = ((TakesScreenshot) new WebDriverProvider().get())
                .getScreenshotAs(OutputType.FILE);

        final var path = String.format("%s/%s.png", screenshotPath, screenshotName);

        try {
            FileUtils.copyFile(screenshotFile, new File(path));
        } catch (IOException ioException) {
            Logs.error("Error al tomar el screenshot: %s", ioException.getLocalizedMessage());
        }

    }

    public static void getPageSource(String fileName) {
        Logs.debug("Tomando evidencia del page source");

        final var path = String.format("%s/%s.xml", pageSourcePath, fileName);

        try {
            final var file = new File(path);
            Logs.debug("Creando el page source");
            if (file.getParentFile().mkdirs()) {
                final var fileWriter = new FileWriter(file);
                final var pageSource = new WebDriverProvider().get().getPageSource();
                if (pageSource != null) {
                    fileWriter.write(Jsoup.parse(pageSource).toString());
                }
                fileWriter.close();
            }

        } catch (IOException ioException) {
            Logs.error("Error al crear page source: %s", ioException.getLocalizedMessage());
        }

    }


    public static void deletePreviousEvidence() {
        try {
            Logs.debug("Borrando las carpetas de screenshots");
            FileUtils.deleteDirectory(new File(screenshotPath));
            FileUtils.deleteDirectory(new File(pageSourcePath));
        } catch (IOException ioException) {
            Logs.error("Error al borrar los screenshots: %s", ioException.getLocalizedMessage());
        }

    }

    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] getScreenshot() {
        return ((TakesScreenshot) new WebDriverProvider().get()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "pageSource", type = "text/html", fileExtension = "txt")
    public static String getPageSource() {
        final var pageSpurce = new WebDriverProvider().get().getPageSource();

        return pageSpurce != null ?
                Jsoup.parse(pageSpurce).toString() : "Error al tomar el page spurce";
    }


}