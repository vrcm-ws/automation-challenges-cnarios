package com.cnairos.challenges.tests;

import com.cnairos.challenges.utlities.DataProviders;
import com.cnairos.challenges.utlities.TestUtilities;
import com.cnarios.challenges.pages.ProductListingAndPaginationPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ProductListingAndPaginationTests extends TestUtilities
{
    @Test(dataProviderClass = DataProviders.class, dataProvider = "PLP_001")
    public void test_PLP_001(String productCategory, int expectedAmountOfProducts)
    {
        ProductListingAndPaginationPage page = new ProductListingAndPaginationPage(driver, logger);
        page.loadPage();

        int actualNumberOfProducts = page.getAmountOfProductsPerCategory(productCategory);

        Assert.assertEquals(expectedAmountOfProducts, actualNumberOfProducts);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "PLP_002")
    public void test_PLP_002(String productName, String expectedPageNumber)
    {
        ProductListingAndPaginationPage page = new ProductListingAndPaginationPage(driver, logger);
        page.loadPage();

        String actualPageNumber = page.getPageNumberOfProduct(productName);

        Assert.assertEquals(actualPageNumber, expectedPageNumber);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "PLP_003")
    public void test_PLP_003(String productCategory, List<String> expectedListOfProducts)
    {
        ProductListingAndPaginationPage page = new ProductListingAndPaginationPage(driver, logger);
        page.loadPage();

        List<String> listOfProducts = page.getHighestRatedPRoductsPerCategory(productCategory);

        Assert.assertTrue(areListsEqual(listOfProducts, expectedListOfProducts));
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "PLP_004")
    public void test_PLP_004(String productCategory, String expectedProductName, double expectedProductPrice)
    {
        ProductListingAndPaginationPage page = new ProductListingAndPaginationPage(driver, logger);
        page.loadPage();

        List<Object> actualMostExpensiveProduct = page.getMostExpensiveProductPerCategory(productCategory);

        String actualProductName = actualMostExpensiveProduct.get(0).toString();
        double actualProductPrice = (double) actualMostExpensiveProduct.get(1);

        Assert.assertEquals(actualProductName, expectedProductName);
        Assert.assertEquals(actualProductPrice, expectedProductPrice);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "PLP_005")
    public void test_PLP_005(int pageNumber, int expectedPreviosPage, int expectedNextPage)
    {
        ProductListingAndPaginationPage page = new ProductListingAndPaginationPage(driver, logger);
        page.loadPage();

        int firstPage = 1;
        int lastPage = 5;

        //navigate to page - page button
        page.navigateToPage(pageNumber);
        Assert.assertTrue(page.isCurrentPage(pageNumber));

        //press prev button
        page.navigateToPage(pageNumber);
        page.clickPrevious();
        Assert.assertTrue(page.isCurrentPage(expectedPreviosPage));

        //press next button
        page.navigateToPage(pageNumber);
        page.clickNext();
        Assert.assertTrue(page.isCurrentPage(expectedNextPage));

        //navigate to page - prev arrow
        page.navigateToPage(pageNumber);
        page.navigateToFirstPage(pageNumber);
        Assert.assertTrue(page.isCurrentPage(firstPage));

        //navigate to page - next arrow
        page.navigateToPage(pageNumber);
        page.navigateToLastPage(pageNumber);
        Assert.assertTrue(page.isCurrentPage(lastPage));
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "PLP_006")
    public void test_PLP_006(int pageNumber)
    {
        ProductListingAndPaginationPage page = new ProductListingAndPaginationPage(driver, logger);
        page.loadPage();
        page.navigateToPage(pageNumber);

        Assert.assertTrue(page.isCurrentPage(pageNumber));
        Assert.assertTrue(page.validateProducts());
    }
}
