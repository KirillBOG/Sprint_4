package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Random;


// Класс домашней страницы
public class HomePageObject {

    private WebDriver driver;


    // Кнопка подтверждения Cookie
    private static final By COOKIE_BUTTON = By.className("App_CookieButton__3cvqF");
    // Заголовок раздела "Вопросы о важном"
    private static final By IMPORTANT_QUESTIONS = By.xpath(".//div[text()='Вопросы о важном']");



    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    // Метод принятие Cookie
    public void clickСookieButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(COOKIE_BUTTON));
        driver.findElement(COOKIE_BUTTON).click();
    }

    // Метод скролинга
    public void scrollAll(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    // Метод скролинга до блока с Вопросами о Важном
    public void  scrollQuestions() {
        WebElement element = driver.findElement(IMPORTANT_QUESTIONS);
        scrollAll(element);
    }

    // Метод получения текста вопросов
    public String checkFAQQuestion(String questionAdd){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.id(questionAdd)));
        return driver.findElement(By.id(questionAdd)).getText();
    }



    // Метод нажатия на зону вопроса
    public void  clickAllQuestions(String questionAdd) {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(By.id(questionAdd)));
        driver.findElement(By.id(questionAdd)).click();
    }



    // Метод получения текста ответа
    public String getAnswerQuestions (String answer) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(answer)));
        return driver.findElement(By.xpath(answer)).getText();
    }


    // Нажатие на кнопку закзать
    public void clickOrderButton(String orderButton){
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(orderButton)));
        driver.findElement(By.xpath(orderButton)).click();

    }


}




