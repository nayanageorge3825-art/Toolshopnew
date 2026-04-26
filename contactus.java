package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Basetest;
import pages.contactpage;

public class contactus extends Basetest {

    @Test
    public void testContactForm() {

        contactpage page = new contactpage(driver);

        page.open();

        String email = "user" + System.currentTimeMillis() + "@test.com";

        page.fillForm("John", "Doe", email, "Customer service", "Test Message");

        // ✅ Use a REAL file (IMPORTANT)
        page.uploadFile("C:\\Users\\HP\\Desktop\\Assgignment4.txt");
        

        page.submit();

        // ✅ Validation
        Assert.assertTrue(page.isSuccessMessageDisplayed(), "Form submission failed");

        System.out.println("✅ Contact test executed");
    }
}