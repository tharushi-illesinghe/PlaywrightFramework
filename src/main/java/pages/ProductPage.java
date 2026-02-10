package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.ArrayList;
import java.util.List;

public class ProductPage {

    private Page page;
    public Page getPage;

    private String similarItemsHeader = "h2.S4K3";
    private String similarProductLinks = "section.hg1B a.cGSI[href]";

    public ProductPage(Page page) {
        this.page = page;
    }

    // Scroll down the page
    public void scrollToSimilarItems() {
        page.mouse().wheel(0, 4000);
    }

    // Wait for similar products section
    public void waitForSimilarProducts() {
        page.waitForSelector(similarProductLinks,
                new Page.WaitForSelectorOptions()
                        .setTimeout(10000)
        );
    }

    public boolean isSimilarItemsDisplayed() {
        try {
            page.waitForSelector(similarItemsHeader,
                    new Page.WaitForSelectorOptions().setTimeout(20000));
            return true;
        } catch (PlaywrightException e) {
            return false;
        }
    }

    public Locator getSimilarProductLinks() {
        page.mouse().wheel(0, 4000);

        page.waitForSelector(similarProductLinks,
                new Page.WaitForSelectorOptions()
                        .setState(WaitForSelectorState.ATTACHED)
                        .setTimeout(30000));
        return page.locator(similarProductLinks);
    }

    public Page clickSimilarProduct(Locator productLink) {
        Page newTab = page.waitForPopup(productLink::click);
        newTab.waitForLoadState(LoadState.DOMCONTENTLOADED);
        return newTab;
    }

    public String getMainProductId() {
        String url = page.url();

        // Extract the product ID from the URL
        String productId = url.split("/itm/")[1].split("\\?")[0];
        return productId;
    }

    public List<String> getSimilarProductIds() {
        List<String> ids = new ArrayList<>();
        Locator similarLinks = page.locator(similarProductLinks);

        int count = similarLinks.count();
        for (int i = 0; i < count; i++) {
            String href = similarLinks.nth(i).getAttribute("href");
            if (href != null && href.contains("/itm/")) {
                String id = href.split("/itm/")[1].split("\\?")[0];
                ids.add(id);
            }
        }
        return ids;
    }


}
