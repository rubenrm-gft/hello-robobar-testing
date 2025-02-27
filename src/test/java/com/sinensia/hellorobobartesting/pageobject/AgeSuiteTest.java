package com.sinensia.hellorobobartesting.pageobject;// Generated by Selenium IDE

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AgeSuiteTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    OrderPage orderPage;


    @BeforeAll
    public void setUp() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }

    @BeforeEach
    public void setupTest() {
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        orderPage = new OrderPage(driver);
        driver.get("http://localhost:3000/");
    }

    @Test
    public void underageCola() {
        cartPage.addColaButton.click();
        assertThat(cartPage.totalField.getText(), is("€1.25"));
        cartPage.addColaButton.click();
        assertEquals(cartPage.totalField.getText(), "€2.50");
        // 6 | click | css=.btn-success |
        cartPage.checkoutButton.click();
        // 7 | click | css=.btn-success |
        checkoutPage.orderButton.click();
        // 8 | assertText | css=p | Coming right up! ~bzzzt~
        assertThat(orderPage.confirmationMessage.getText(), is("Coming right up! ~bzzzt~"));
    }

    @Test
    public void underageBeer() {
        // 2 | click | css=.ng-scope:nth-child(2) > td .input-group-append > .btn |
        cartPage.addBeerButton.click();
        // 3 | assertText | css=tr:nth-child(4) > .ng-binding | €2.00
        // 1x RoboBeer = €2.00
        assertThat(cartPage.totalField.getText(), is("€2.00"));
        // 4 | click | css=.ng-scope:nth-child(2) > td .input-group-append > .btn |
        cartPage.addBeerButton.click();
        // 5 | assertText | css=tr:nth-child(4) > .ng-binding | €4.00
        // 2x RoboBeer = €4.00
        assertThat(cartPage.totalField.getText(), is("€4.00"));
        // 6 | click | css=.btn-success |
        cartPage.checkoutButton.click();
        // 7 | mouseOver | css=.btn-success |

        // 8 | click | id=ageInput |
        checkoutPage.ageInput.click();
        // 9 | type | id=ageInput | 17
        checkoutPage.ageInput.sendKeys("17");
        // 10 | click | css=.btn-success |
        checkoutPage.orderButton.click();
        // 11 | assertElementPresent | css=.alert-danger |
        {
            assert (orderPage.alertDiv.isDisplayed());
        }
    }

    @Test
    public void adultBeer() {
        // 2 | click | css=.ng-scope:nth-child(2) > td .input-group-append > .btn |
        cartPage.addBeerButton.click();
        // 3 | assertText | css=tr:nth-child(4) > .ng-binding | €2.00
        // 1x RoboBeer = €2.00
        assertThat(cartPage.totalField.getText(), is("€2.00"));
        // 4 | click | css=.ng-scope:nth-child(2) > td .input-group-append > .btn |
        cartPage.addBeerButton.click();
        // 5 | assertText | css=tr:nth-child(4) > .ng-binding | €4.00
        // 2x RoboBeer = €4.00
        assertThat(cartPage.totalField.getText(), is("€4.00"));
        // 6 | click | css=.btn-success |
        cartPage.checkoutButton.click();
        // 7 | mouseOver | css=.btn-success |
        {
            WebElement element = cartPage.checkoutButton;
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        // 8 | click | id=ageInput |
        checkoutPage.ageInput.click();
        // 9 | type | id=ageInput | 19
        checkoutPage.ageInput.sendKeys("19");
        // 10 | click | css=.btn-success |
        checkoutPage.orderButton.click();
        // 11 | assertText | css=p | Coming right up! ~bzzzt~
        assertThat(orderPage.confirmationMessage.getText(), is("Coming right up! ~bzzzt~"));
    }
}
