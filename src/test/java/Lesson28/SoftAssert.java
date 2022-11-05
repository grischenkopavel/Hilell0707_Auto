package Lesson28;/*
Created by Pavel Gryshchenko on 03.11.2022
*/

import org.testng.Assert;
import org.testng.annotations.Test;

public class SoftAssert {
    @Test
    void testCaseOne() {
        System.out.println("*** test case one started ***");
        Assert.assertEquals(5, 5);
        System.out.println("hard assert succeed ....");
        Assert.assertTrue("Hello".equals("hello"));
        System.out.println("*** test case executed successfully ***");
    }

    @Test
    void testCaseTwo() {
        org.testng.asserts.SoftAssert softAssert = new org.testng.asserts.SoftAssert();
        System.out.println("*** test case one started ***");
        softAssert.assertEquals(5, 5);
        System.out.println("hard assert succeed ....");
        softAssert.assertTrue("Hello".equals("hello"));
        System.out.println("*** test case executed successfully ***");
        softAssert.assertAll(); // if above asserts will be green code go forward, if one fail it will be break
        System.out.println("*** this will be executed only if all above assert will pass ***");
    }


}
