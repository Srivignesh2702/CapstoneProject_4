package seleniumProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import io.cucumber.java.en.*;

public class StepDefinitions {
    public static WebDriver driver;

    @Given("I am on the home page")
    public void I_am_on_the_home_page() {
        System.setProperty("webdriver.gecko.driver", "C:/Users/Vijaya Arun/Desktop/capstone_project/seleniumProject/src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("http://the-internet.herokuapp.com");
    }

    @Then("I should see the title as {string}")
    public void i_should_see_the_title_as(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Title doesn't match");
        System.out.println("Test1");
    }

    @When("I click on {string} link")
    public void i_click_on_link(String linkText) {
        WebElement link = driver.findElement(By.linkText(linkText));
        link.click();
        System.out.println("Test2");
    }

    @Then("I should see text as {string}")
    public void i_should_see_text_as(String expectedText) throws InterruptedException {
    	Thread.sleep(7000);
        WebElement body = driver.findElement(By.xpath("//h3[contains(text(),'A/B Test Variation 1')]"));
        Assert.assertTrue(body.getText().contains(expectedText), "Text not found on the page");
        System.out.println("Test3");
    }

    @When("I navigate back to the homepage")
    public void i_navigate_back_to_the_homepage() {
        driver.navigate().back();
        System.out.println("Test4");
    }

    @And("I click on {string} dropdown link")
    public void i_click_on_dropdown_link(String linkText) throws InterruptedException {
    	Thread.sleep(4000);
    	 WebElement dropdownLink = driver.findElement(By.xpath("//a[contains(text(),'"+linkText+"')]"));
    	 System.out.println(dropdownLink);
        dropdownLink.click();
        System.out.println("Test5");
    }

    @And("I select {string} from dropdown")
    public void i_select_option_from_dropdown(String option) {
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        dropdown.click();
        WebElement optionElement = dropdown.findElement(By.xpath("//option[text()='" + option + "']"));
        optionElement.click();
        System.out.println("Test6");
    }

    @Then("I should see {string} selected")
    public void i_should_see_option_selected(String expectedOption) {
        WebElement selectedOption = driver.findElement(By.cssSelector("option[selected='selected']"));
        Assert.assertEquals(selectedOption.getText(), expectedOption, "Option not selected");
        System.out.println("Test7");
    }

    @And("I click on {string} frames link")
    public void i_click_on_frames_link(String linkText) {
        WebElement framesLink = driver.findElement(By.linkText(linkText));
        framesLink.click();
        System.out.println("Test8");
    }

    @Then("I should see {string} link")
    public void i_should_see_nested_frames_link(String expectedLinkText) {
        WebElement nestedFramesLink = driver.findElement(By.linkText(expectedLinkText));
        Assert.assertTrue(nestedFramesLink.isDisplayed(), "Nested Frames link is not displayed");
        System.out.println("Test9");
    }

    @Then("I should see {string} link in the page")
    public void i_should_see_frame_link_in_the_page(String expectedLinkText) {
        WebElement frameLink = driver.findElement(By.xpath("//a[contains(text(),'"+expectedLinkText+"')]"));
        Assert.assertTrue(frameLink.isDisplayed(), "Frame link is not displayed");
        System.out.println("Test10");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Test10");
        }
    }
}
