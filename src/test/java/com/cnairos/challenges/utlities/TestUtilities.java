package com.cnairos.challenges.utlities;

import java.util.List;

public class TestUtilities extends BaseTest
{
    public boolean areListsEqual(List<String> listA, List<String> listB)
    {
        List<String> normalizedListA = listA.stream().sorted().toList();
        List<String> normalizedListB = listB.stream().sorted().toList();

        return normalizedListA.equals(normalizedListB);
    }
}
