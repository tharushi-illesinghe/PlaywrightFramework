package pages;

import com.microsoft.playwright.Page;

public class HomePage {

    private Page page;
    private String searchBox = "input#gh-ac";
    private String searchButton = "button#gh-search-btn";

    public HomePage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.navigate("https://www.ebay.com");
    }

    public void searchProduct(String product) {
        page.fill(searchBox, product);
        page.click(searchButton);
    }
}
