package com.cnairos.challenges.tests;

import com.cnarios.challenges.pages.ProductListingAndPaginationPage;
import com.cnairos.challenges.utlities.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductListingAndPaginationTests extends BaseTest
{
    @Test(dataProvider = "PLP_001")
    public void test_PLP_001(String productCategory, int amountOfProducts)
    {
        ProductListingAndPaginationPage page = new ProductListingAndPaginationPage(driver, logger);
        page.loadPage();

        int actualNumberOfProducts = page.getAmountOfProductsPerCategory(productCategory);

        Assert.assertEquals(amountOfProducts, actualNumberOfProducts);
    }

    @Test(dataProvider = "PLP_002")
    public void test_PLP_002(String productName, String pageNumber)
    {
        ProductListingAndPaginationPage page = new ProductListingAndPaginationPage(driver, logger);
        page.loadPage();

        String actualPageNumber = page.getPageNumberOfProduct(productName);

        Assert.assertEquals(actualPageNumber, pageNumber);
    }
}
