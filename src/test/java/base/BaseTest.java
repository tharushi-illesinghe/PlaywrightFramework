package base;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchResultsPage;
import utils.PlaywrightFactory;
import utils.ReportManager;
import utils.ScreenshotUtil;

import java.lang.reflect.Method;

import static utils.ReportManager.extent;

public class BaseTest {

    protected Page page;
    protected BrowserContext context;
    protected PlaywrightFactory factory;
    protected ExtentTest test;
    protected ProductPage productPage;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(Method method, String browser) {

        // Initialize ExtentReports
        extent = ReportManager.getReports();
        test = extent.createTest(method.getName());

        factory = new PlaywrightFactory();
        page = factory.initBrowser(browser);
        context = factory.getContext();

        // Log in report
        test.info("Browser initialized and new page opened");

        HomePage home = new HomePage(page);
        test.info("Navigating to eBay home page");
        home.navigate();

        String productToSearch = "wallets";
        test.info("Searching for product: " + productToSearch);
        home.searchProduct(productToSearch);

        SearchResultsPage results = new SearchResultsPage(page);
        Page productPageTab = results.openFirstProduct(context);

        productPage = new ProductPage(productPageTab);
        test.info("First product page opened successfully");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        // Log pass/fail to report
        if (result.getStatus() == ITestResult.FAILURE) {

            test.fail("Test Failed: " + result.getThrowable());

            // Take screenshot
            String screenshotPath =
                    ScreenshotUtil.takeScreenshot(page, result.getName());

            // Attach screenshot to Extent
            test.addScreenCaptureFromPath(screenshotPath);

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed");

        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test Skipped");

        }

        // Close browser
        factory.closeBrowser();
        test.info("Browser closed");

        // Flush the report so it generates the HTML
        extent.flush();
    }
}