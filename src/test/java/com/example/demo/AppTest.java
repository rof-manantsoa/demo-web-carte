package com.example.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AppTest {

    private CardService cardService;

    @Before
    public void init() {
        cardService = new CardService();
    }

    @Test
    public void errorExpcetionTest() {

        String[] COLORS = {"coeur", "trefle", "pique", "carreau"};
        List<String> randomColors = DataTypeUtils.getRandList(COLORS);
        String[] VALUES = {"as", "roi", "reine", "valet", "10", "9", "8", "7",
                "6", "5", "4", "3", "2"};
        List<String> randomValues = DataTypeUtils.getRandList(VALUES);
        List<String> random10Maim = cardService.colorValuesCombinaison(DataTypeUtils.getRandList(VALUES),
                DataTypeUtils.getRandList(COLORS), 10);
        List<String> order10Maim = cardService.orderRandomCombinaison(random10Maim, randomValues, randomColors);

        System.out.println(randomColors);
        System.out.println(randomValues);
        System.out.println(random10Maim);
        System.out.println(order10Maim);

    }

    @Test
    public void orderTest() {
        List<String> randomColors = Arrays.asList("trefle", "coeur", "pique", "carreau");
        List<String> randomValues = Arrays.asList("reine", "10", "4", "roi", "5", "7",
                "2", "as", "valet", "6", "9", "3", "8");
        List<String> random10 = Arrays.asList("5_trefle", "6_trefle", "6_coeur",
                "10_pique", "6_carreau", "5_coeur",
                "2_trefle", "9_pique", "valet_pique", "4_trefle");

        List<String> expectedOrder = Arrays.asList("10_pique", "4_treffle", "5_trefle",
                "5_coeur", "2_trefle", "valet_pique",
                "6_trefle", "6_coeur", "6_carreau", "9_pique");

        Assert.assertEquals(expectedOrder, cardService.orderRandomCombinaison(random10, randomValues, randomColors));
    }
}
