package pages;

import com.microsoft.playwright.*;

public class SearchResultsPage {

    private Page page;
    private String firstProductLink = "a.s-card__link";

    public SearchResultsPage(Page page) {
        this.page = page;
    }

    public Page openFirstProduct(BrowserContext context) {
        return context.waitForPage(() -> {
            page.locator(firstProductLink)
                    .first()
                    .click(new Locator.ClickOptions().setForce(true));
        });
    }
}
