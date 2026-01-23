package com.cnarios.challenges.pages;

import com.cnarios.challenges.base.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ProductListingAndPaginationPage extends BasePage
{
    //locators
    By pageLocator = By.xpath("//button[@type='button' and contains(@class,'MuiPaginationItem-page')]");
    By itemLocator = By.xpath("//p[contains(text(),'Category')]/parent::div");
    By nextLocator = By.xpath("//button[text()='Next']");
    By prevLocator = By.xpath("//button[text()='Prev']");
    By nextArrowLocator = By.xpath("//button[@aria-label='Go to next page']");
    By prevArrowLocator = By.xpath("//button[@aria-label='Go to previous page']");
    By currentPageLocator = By.xpath("//button[@aria-current='page']");

    //context locators
    By nameLocator = By.xpath(".//h6[1]");
    By priceLocator = By.xpath(".//h6[2]");
    By categoryLocator = By.xpath(".//p");
    By ratingLocator = By.xpath(".//span[contains(@class,'iconFilled')]");
    By ratingStarLocator = By.xpath(".//*[name()='svg']");

    //url
    String pageAddress = "https://www.cnarios.com/challenges/product-listing-pagination#challenge";

    //records
    public record ProductDetails(String name, double price, String category, int rating, String page) {};

    private int firstPage = 1;
    private int lastPage = 5;

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
        List<WebElement> pages;
        List<WebElement> items;

        List<ProductDetails> products = new ArrayList<>();

        pages = searchElements(pageLocator);

        for(WebElement page : pages)
        {
            page.click();

            String productPage = page.getAttribute("aria-label").replace("page ", "").trim();

            items = searchElements(itemLocator);

            for(WebElement item : items)
            {
                String productName = item.findElement(nameLocator).getText();
                double productPrice = Double.parseDouble(item.findElement(priceLocator).getText().replace("$", "").trim());
                String productCategory = item.findElement(categoryLocator).getText().replace("Category: ", "").trim();
                int productRating = item.findElements(ratingLocator).size();

                products.add(new ProductDetails(productName, productPrice, productCategory, productRating, productPage));
            }
        }

        return products;
    }

    public List<ProductDetails> getListOfProductsPerCategory(String productCategory)
    {
        List<ProductDetails> products = getListOfProducts();

        return products.stream().filter(p -> p.category().equals(productCategory)).toList();
    }

    public int getAmountOfProductsPerCategory(String productCategory)
    {
        return getListOfProductsPerCategory(productCategory).size();
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

    public List<String> getHighestRatedPRoductsPerCategory(String productCategory)
    {
        List<ProductDetails> products = getListOfProductsPerCategory(productCategory);

        List<String> highestRatedProducts = new ArrayList<>();

        for(ProductDetails product : products)
        {
            if(product.rating == 5)
            {
                highestRatedProducts.add(product.name);
            }
        }

        return highestRatedProducts;
    }

    public List<Object> getMostExpensiveProductPerCategory(String productCategory)
    {
        List<ProductDetails> products = getListOfProductsPerCategory(productCategory);
        List<Object> mostExpensiveProduct = new ArrayList<>();

        Optional<ProductDetails> product = products.stream().max(Comparator.comparingDouble(ProductDetails::price));

        if(product.isPresent())
        {
            mostExpensiveProduct.add(product.get().name());
            mostExpensiveProduct.add(product.get().price());
        }

        return mostExpensiveProduct;
    }

    public void navigateToPage(int pageNumber)
    {
        List<WebElement> pages = searchElements(pageLocator);

        WebElement page = pages.get(pageNumber - 1);
        page.click();
    }

    public int getCurrentPageNumber()
    {
        String currentPage = searchElement(currentPageLocator).getAttribute("aria-label");

        return Integer.parseInt(currentPage.replace("page ", "").trim());
    }

    public boolean isCurrentPage(int pageNumber)
    {
        return pageNumber == getCurrentPageNumber();
    }

    public void clickNext()
    {
        if(getCurrentPageNumber() != lastPage)
        {
            clickElement(nextLocator);
        }
    }

    public void clickPrevious()
    {
        if(getCurrentPageNumber() != firstPage)
        {
            clickElement(prevLocator);
        }
    }

    public void navigateToFirstPage(int startPage)
    {
        int firstPage = 1;

        if(startPage != firstPage)
        {
            for(int i = 0; i < startPage - firstPage; i++)
            {
                clickElement(prevArrowLocator);
            }
        }
    }

    public void navigateToLastPage(int startPage)
    {
        int lastPage = 5;

        if(startPage != lastPage)
        {
            for(int i = 0; i < lastPage - startPage; i++)
            {
                clickElement(nextArrowLocator);
            }
        }
    }

    public boolean validateProducts()
    {
        List<WebElement> products = searchElements(itemLocator);

        for(WebElement product : products)
        {
            if(!validateProductAttributes(product))
            {
                return false;
            }
        }

        return true;
    }

    private boolean validateProductAttributes(WebElement element)
    {
        if(element.findElement(nameLocator).getText().isEmpty())
        {
            logger.info("Product name is missing");
            return false;
        }

        if(!element.findElement(priceLocator).getText().contains("$"))
        {
            logger.info("$ symbol not present");
            return false;
        }

        if(!element.findElement(categoryLocator).getText().contains("Category"))
        {
            logger.info("Category not present");
            return false;
        }

        for(WebElement icon : element.findElements(ratingLocator))
        {
            if(!icon.findElement(ratingStarLocator).getAttribute("focusable").equals("false"))
            {
                logger.info("Rating star is editable");
                return false;
            }
        }

        return true;
    }
}
