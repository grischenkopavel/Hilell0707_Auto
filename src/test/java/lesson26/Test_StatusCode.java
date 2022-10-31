package lesson26;/*
Created by Pavel Gryshchenko on 27.10.2022
*/

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test_StatusCode {

    @Test
    void StatusCodeTest() throws InterruptedException, IOException {
        HttpURLConnection c = (HttpURLConnection) new URL("https://the-internet.herokuapp.com/status_codes").openConnection();
        c.connect();
        int statusCode = c.getResponseCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode, 200);
    }
}
