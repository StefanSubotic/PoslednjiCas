import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SwagLabTests extends BaseTest {

    LoginPage loginPage;
    InventoryPage inventoryPage;

    CartPage cartPage;

    CheckStepOnePage checkStepOnePage;

    @BeforeMethod
    public void setUp()
    {
    driver = openBrowser();
    loginPage = new LoginPage(driver);
    inventoryPage = new InventoryPage(driver);
    cartPage = new CartPage(driver);
    checkStepOnePage = new CheckStepOnePage(driver);


    }

    @Test
    public void login()
    {
    loginPage.LoginOnPage("standard_user", "secret_sauce");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

    }

    @Test
    public void buyProduct()
    {
        loginPage.LoginOnPage("standard_user", "secret_sauce");
        inventoryPage.addBackPack();
        inventoryPage.clickOnCart();

        Assert.assertEquals(cartPage.getInfoPrice(), "$29.99");
        Assert.assertEquals(cartPage.getProductName(), "Sauce Labs Backpack");

        cartPage.clickcheckout();
        checkStepOnePage.inputPersonalInfo("Stefan", "Subotic", "11000");

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
        checkStepOnePage.clickFinish();

        Assert.assertEquals(checkStepOnePage.getInfoMessage(), "Thank you for your order!");

    }


    @AfterMethod
    public void after()
    {
        driver.quit();
    }



}
