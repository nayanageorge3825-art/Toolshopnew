package tests;

import base.Basetest;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataProviderUtil;

public class LoginTest extends Basetest {

    @Test(dataProvider = "loginData", dataProviderClass = DataProviderUtil.class)
    public void loginTest(String email, String password) throws InterruptedException {

        LoginPage lp = new LoginPage(driver);

        lp.openLoginPage();
        lp.login(email, password);

        Thread.sleep(6000);

        System.out.println("Login executed for: " + email);
    }
}