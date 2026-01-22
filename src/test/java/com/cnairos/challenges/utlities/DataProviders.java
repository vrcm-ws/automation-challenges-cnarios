package com.cnairos.challenges.utlities;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

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

    @DataProvider(name = "PLP_003")
    public Object[][] listOfProductsPerCategory()
    {
        return new Object[][] { {"Books", List.of("The Pragmatic Programmer", "Sapiens: A Brief History of Humankind", "Atomic Habits", "Clean Code")},
                                {"Sports", List.of("Wilson Pro Staff Tennis Racket", "Callaway Golf Set", "Adidas Predator Football", "Nike Mercurial Football Boots")},
                                {"Home", List.of("Samsung Smart Refrigerator", "KitchenAid Stand Mixer", "Breville Barista Express", "Instant Pot Duo", "Philips Air Fryer XXL", "Dyson V15 Detect Vacuum")},
                                {"Clothing", List.of("Nike Air Force 1 Sneakers", "Patagonia Fleece Sweater", "The North Face Jacket", "Under Armour Running Shoes")},
                                {"Electronics", List.of("Sony PlayStation 5", "GoPro HERO11 Black", "Bose QuietComfort 45", "Apple MacBook Air M2", "Apple iPhone 14 Pro", "Sony WH-1000XM5 Headphones")} };
    }
}
