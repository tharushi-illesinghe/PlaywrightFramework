package utils;

import com.microsoft.playwright.Page;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String takeScreenshot(Page page, String testName) {

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath =
                System.getProperty("user.dir")
                        + "/reports/screenshots/"
                        + testName + "_" + timestamp + ".png";

        page.screenshot(
                new Page.ScreenshotOptions()
                        .setPath(Paths.get(screenshotPath))
                        .setFullPage(true)
        );

        return screenshotPath;
    }
}
