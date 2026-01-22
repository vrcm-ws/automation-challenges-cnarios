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
}
