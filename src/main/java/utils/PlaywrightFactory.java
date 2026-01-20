package utils;

import com.microsoft.playwright.*;

public class PlaywrightFactory {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;

    public Page initBrowser(String browserName) {
        playwright = Playwright.create();

        if (browserName.equalsIgnoreCase("chrome")) {
            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );
        } else if (browserName.equalsIgnoreCase("edge")) {
            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setChannel("msedge") // Edge channel
                            .setHeadless(false));
        }
        context = browser.newContext();
        return context.newPage();
    }

    public BrowserContext getContext() {
        return context;
    }

    public void closeBrowser() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}
