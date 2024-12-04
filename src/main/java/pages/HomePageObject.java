package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Random;


// Класс домашней страницы
public class HomePageObject {

    private WebDriver driver;

    private final String[][] etalonText = {{"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
            {"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
            {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
            {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
            {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
            {"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
            {"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
            {"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}};

    // Кнопка "Заказать"
    private final By orderButton = By.xpath(".//button[text()='Заказать']");
    // Кнопка подтверждения Cookie
    private static final By cookieButton = By.className("App_CookieButton__3cvqF");
    // Заголовок раздела "Вопросы о важном"
    private static final By importantQuestions = By.xpath(".//div[text()='Вопросы о важном']");
    // Вопросы из блока "Вопросы о важном", под массив
    private static final String Questions = "accordion__heading-%d";
    // Ответы из блока "Вопросы о важном", под массив
    private static final String answerQuestions = ".//div[@id='accordion__panel-%d']//p";
    //Текст вопроса позитив
    //private final String positiveQuestions = "Текст %d вопросса корректный.";
    // Текст ответа позитив
    //private final String positiveAnswer = "Текст %d ответа корректный.";
    //Текст вопроса негатив
    private final String negativeQuestions = "Текст %d вопроса некорректный.";
    //Текст ответа негатив
    private final String negativeAnswer = "Текст %d ответа некорректный.";



    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    // Метод принятие Cookie
    public void clickСookieButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(cookieButton));
        driver.findElement(cookieButton).click();
    }

    // Метод скролинга
    public void scrollAll(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    // Метод скролинга до блока с Вопросами о Важном
    public void  scrollQuestions() {
        WebElement element = driver.findElement(importantQuestions);
        scrollAll(element);
    }

    // Метод получения текста вопросов
    public String receivingAllQuestions(int itemIndex){
        return driver.findElement(By.id(String.format(Questions, itemIndex))).getText();
    }




    // Метод нажатия на зону вопроса
    public void  clickAllQuestions(int itemIndex) {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(By.id(String.format(Questions, itemIndex))));
        driver.findElement(By.id(String.format(Questions, itemIndex))).click();
    }

    // Метод получения текста ответа
    public String getAnswerQuestions (int itemIndex) {
        return driver.findElement(By.xpath(String.format(answerQuestions,itemIndex))).getText();
    }



    // Нажатие на кнопку закзать
    public void clickOrderButton(){
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(orderButton));
        List<WebElement> buttonsElement = driver.findElements(orderButton);
        Random random = new Random();
        int randomIndex = random.nextInt(buttonsElement.size());
        buttonsElement.get(randomIndex).click();

    }


    //Метод записи текста вопросов и ответов в массив
    public void arrayQuestionAnswer() {
        for (int i = 0; i < etalonText.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    String question = receivingAllQuestions(i);
                    String originQuestion = etalonText[i][j];
                    if (!originQuestion.equals(question)) {
                        System.out.println(String.format(negativeQuestions, i + 1));
                        System.out.println("Текст оригинального вопроса: " + originQuestion);
                        System.out.println("Текст полученного вопроса:   " + question);
                    }
                    clickAllQuestions(i);
                } else {
                    String answer = getAnswerQuestions(i);
                    String originAnswer = etalonText[i][j];
                    if (!originAnswer.equals(answer)) {
                        System.out.println(String.format(negativeAnswer, i + 1));
                        System.out.println("Текст оригинального ответа: " + originAnswer);
                        System.out.println("Текст полученного ответа:   " + answer);
                    }
                }
            }
        }
    }
}




