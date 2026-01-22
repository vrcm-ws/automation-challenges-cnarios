package com.cnarios.challenges.pages;

import com.cnarios.challenges.base.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductListingAndPaginationPage extends BasePage
{
    //locators
    By pageLocator = By.xpath("//button[@type='button' and contains(@class,'MuiPaginationItem-page')]");
    By itemLocator = By.xpath("//p[contains(text(),'Category')]/parent::div");

    //context locators
    By titleLocator = By.xpath(".//h6[1]");
    By priceLocator = By.xpath(".//h6[2]");
    By categoryLocator = By.xpath(".//p");
    By ratingLocator = By.xpath(".//span[contains(@class,'iconFilled')]");

    //url
    String pageAddress = "https://www.cnarios.com/challenges/product-listing-pagination#challenge";

    //records
    public record ProductDetails(String name, double price, String category, int rating, String page) {};

    public ProductListingAndPaginationPage(WebDriver driver, Logger logger)
    {
        super(driver, logger);
    }

    public void loadPage()
    {
        loadPage(pageAddress);
    }

    public List<ProductDetails> getListOfProducts()
    {
        List<WebElement> pages = new ArrayList<>();
        List<WebElement> items = new ArrayList<>();

        List<ProductDetails> products = new ArrayList<>();

        pages = searchElements(pageLocator);

        for(WebElement page : pages)
        {
            page.click();

            String productPage = page.getAttribute("aria-label").replace("page ", "").trim();

            items = searchElements(itemLocator);

            for(WebElement item : items)
            {
                String productName = item.findElement(titleLocator).getText();
                double productPrice = Double.parseDouble(item.findElement(priceLocator).getText().replace("$", "").trim());
                String productCategory = item.findElement(categoryLocator).getText().replace("Category: ", "").trim();
                int productRating = item.findElements(ratingLocator).size();

                products.add(new ProductDetails(productName, productPrice, productCategory, productRating, productPage));
            }
        }

        return products;
    }

    public int getAmountOfProductsPerCategory(String category)
    {
        List<ProductDetails> products = getListOfProducts();

        return (int)products.stream().filter(p -> p.category().equals(category)).count();
    }

    public String getPageNumberOfProduct(String productName)
    {
        List<ProductDetails> products = getListOfProducts();

        Optional<ProductDetails> product = products.stream().filter(p -> p.name().equals(productName)).findFirst();

        if(product.isPresent())
        {
            return product.get().page();
        }

        return "";
    }
}
