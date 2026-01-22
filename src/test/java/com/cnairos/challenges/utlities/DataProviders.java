package com.cnairos.challenges.utlities;

import org.testng.annotations.DataProvider;

public class DataProviders
{
    @DataProvider(name = "PLP_001")
    public Object[][] amountOfProductsPerCategory()
    {
        return new Object[][] { {"Books", 10},
                                {"Sports", 10},
                                {"Home", 10},
                                {"Clothing", 10},
                                {"Electronics", 10} };
    }

    @DataProvider(name = "PLP_002")
    public Object[][] pageNumberOfProduct()
    {
        return new Object[][] { {"Sony PlayStation 5", "1"},
                                {"Nike Air Force 1 Sneakers", "2"},
                                {"Apple Watch Series 9", "3"},
                                {"Apple MacBook Air M2", "4"},
                                {"Clean Code", "5"} };
    }
}
