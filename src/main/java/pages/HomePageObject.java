package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


// Класс домашней страницы
public class HomePageObject {

    private WebDriver driver;


    // Кнопка подтверждения Cookie
    private static final By COOKIE_BUTTON = By.className("App_CookieButton__3cvqF");
    // Заголовок раздела "Вопросы о важном"
    private static final By IMPORTANT_QUESTIONS = By.xpath(".//div[text()='Вопросы о важном']");
    // Вопрос в списке
    private final String questionIndex = "accordion__heading-%d";
    // Ответы в спискке
    private final String answerIndex = ".//div[@id='accordion__heading-%d']//p";
    // Кнопка заказать Верхняя
    public static final By buttonOrderUp = By.xpath(".//div[@class='Header_Nav__AGCXC']//button[text()='Заказать']");
    // Кнопка заказать Нижняя
    public static final By buttonOrderDown = By.xpath(".//div[@class='Home_FinishButton__1_cWm']//button[text()='Заказать']");


    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    // Метод принятие Cookie
    public void clickСookieButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(COOKIE_BUTTON));
        driver.findElement(COOKIE_BUTTON).click();
    }
    //
    public void clickOrderButtonOn (String button) {
        if (button.equals("header")) {
            driver.findElement(buttonOrderUp).click();
        } else if (button.equals("home")) {
            driver.findElement(buttonOrderDown).click();
        }
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
    public String checkFAQQuestion(int number){
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(By.id(String.format(questionIndex, number))));
        return driver.findElement(By.id(String.format(questionIndex, number))).getText();
    }



    // Метод нажатия на зону вопроса
    public void  clickAllQuestions(int numberQuestion) {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(By.id(String.format(questionIndex, numberQuestion))));
        driver.findElement(By.id(String.format(questionIndex, numberQuestion))).click();
    }



    // Метод получения текста ответа
    public String getAnswerQuestions (int numberAnswer) {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(By.id(String.format(answerIndex,numberAnswer))));
        return driver.findElement(By.xpath(String.format(answerIndex, numberAnswer))).getText();
    }


    // Нажатие на кнопку закзать
    public void clickOrderButton(String orderButton){
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(orderButton)));
        driver.findElement(By.xpath(orderButton)).click();

    }


}




