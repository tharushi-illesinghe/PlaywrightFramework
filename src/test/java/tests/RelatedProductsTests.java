package tests;

import base.BaseTest;
import pages.*;
import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class RelatedProductsTests extends BaseTest {

    @Test(priority = 0)
    public void verifyRelatedProductsSectionDisplayed() {

        Assert.assertTrue(
                productPage.isSimilarItemsDisplayed(),
                "'Similar items' section is NOT displayed"
        );
        test.pass("'Similar items' section verified successfully");
    }

    @Test(priority = 1)
    public void verifyRelatedProductsAreClickable() {

        Locator links = productPage.getSimilarProductLinks();
        int count = links.count();

        Assert.assertTrue(count > 0, "No similar products found");

        int maxToTest = Math.min(count, 3);

        for (int i = 0; i < maxToTest; i++) {
            Locator link = links.nth(i);

            Assert.assertTrue(link.isVisible());
            Assert.assertTrue(link.getAttribute("href").contains("/itm/"));

            Page newTab = productPage.clickSimilarProduct(link);
            Assert.assertTrue(newTab.url().contains("/itm/"));
            newTab.close();
        }
        test.pass("All similar products verified successfully");
    }

    @Test(priority = 2)
    public void verifySixRelatableProductsDisplayed() {

        test.info("Scrolling down to load Similar Items section");
        productPage.scrollToSimilarItems();

        test.info("Waiting for similar products to be visible");
        productPage.waitForSimilarProducts();

        Locator similarProductLinks = productPage.getSimilarProductLinks();
        int count = similarProductLinks.count();

        test.info("Number of similar products displayed: " + count);

        Assert.assertEquals(
                count,
                6,
                "The number of similar products displayed in EBay is NOT 6. " +
                        "Website is currently displaying 4 similar products."
        );

        test.pass("Verified exactly 6 similar products are displayed");
    }

    @Test(priority = 3)
    public void verifyMainProductNotInSimilarItemsById() {

        test.info("Scrolling to Similar Items section");
        productPage.scrollToSimilarItems();

        test.info("Waiting for similar products to be visible");
        productPage.waitForSimilarProducts();

        String mainId = productPage.getMainProductId();
        test.info("Main product ID: " + mainId);

        List<String> similarIds = productPage.getSimilarProductIds();
        test.info("Similar product IDs found: " + similarIds);

        boolean mainInSimilar = similarIds.contains(mainId);
        Assert.assertFalse(mainInSimilar, "Main product is included in Similar Items!");
        test.pass("Main product ID is NOT included in Similar Items");
    }

}



