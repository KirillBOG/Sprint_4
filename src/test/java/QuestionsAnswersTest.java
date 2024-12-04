import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePageObject;

public class QuestionsAnswersTest {

    private WebDriver driver;

    @Before
    public void prepare(){
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test

    public void complianceCheckFaq(){
        HomePageObject homePageObject = new HomePageObject(driver);
        homePageObject.clickСookieButton();
        homePageObject.scrollQuestions();
        homePageObject.arrayQuestionAnswer();

    }

    @After
    public void closeDriver(){
        driver.quit();
    }
}
