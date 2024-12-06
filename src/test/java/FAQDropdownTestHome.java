import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePageObject;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class FAQDropdownTestHome {
    private WebDriver driver;
    private String QUESTIONADD;
    private String QUESTION;
    private String ANSWERADD;
    private String ANSWER;
    private final String scooterUrl = "https://qa-scooter.praktikum-services.ru/";

    // Инициализация параметров
    public FAQDropdownTestHome(String questionadd, String question,String answeradd, String answer) {
        QUESTIONADD = questionadd;
        QUESTION = question;
        ANSWERADD = answeradd;
        ANSWER = answer;
    }

    // Данные для параметризованного теста
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"accordion__heading-0", "Сколько это стоит? И как оплатить?",".//div[@id='accordion__heading-0']//p", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"accordion__heading-1","Хочу сразу несколько самокатов! Так можно?",".//div[@id='accordion__heading-1']//p", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"accordion__heading-2","Как рассчитывается время аренды?",".//div[@id='accordion__heading-2']//p", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"accordion__heading-3","Можно ли заказать самокат прямо на сегодня?",".//div[@id='accordion__heading-3']//p", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"accordion__heading-4","Можно ли продлить заказ или вернуть самокат раньше?",".//div[@id='accordion__heading-4']//p", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"accordion__heading-5","Вы привозите зарядку вместе с самокатом?",".//div[@id='accordion__heading-5']//p", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"accordion__heading-6","Можно ли отменить заказ?",".//div[@id='accordion__heading-6']//p", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"accordion__heading-7","Я жизу за МКАДом, привезёте?",".//div[@id='accordion__heading-7']//p", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        });
    }


    @Before
    public void prepare() {
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get(scooterUrl);
    }

    @Test
    public void checkQuestionTest() {
        HomePageObject homePageObject = new HomePageObject(driver);
        homePageObject.clickСookieButton();
        homePageObject.scrollQuestions();
        String getQuestionText = homePageObject.checkFAQQuestion(QUESTIONADD);
        assertEquals("Тест не отработал! Неверный вопрос",QUESTION,getQuestionText);
    }


    @Test
    public void checkAnswerTest() {
        HomePageObject homePageObject = new HomePageObject(driver);
        homePageObject.clickСookieButton();
        homePageObject.scrollQuestions();
        homePageObject.clickAllQuestions(QUESTIONADD);
        String getAnswertext = homePageObject.getAnswerQuestions(ANSWERADD);
        assertEquals("Тест не отработал! Неверный ответ", ANSWER, getAnswertext);
    }

    @After
    public void closeDriver() {
            driver.quit();
        }
    }

