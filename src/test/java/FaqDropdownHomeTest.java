import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePageObject;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class FaqDropdownHomeTest{
    private WebDriver driver;
    private final int questionAdd;
    private String questionIndex;
    private final int answerAdd;
    private String answerIndex;
    private final String scooterUrl = "https://qa-scooter.praktikum-services.ru/";

    // Инициализация параметров
    public FaqDropdownHomeTest(int questionAdd, String questionIndex, int answerAdd, String answerIndex) {
       this.questionAdd = questionAdd;
       this.questionIndex = questionIndex;
       this.answerAdd = answerAdd;
       this.answerIndex = answerIndex;
    }

    // Данные для параметризованного теста
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, "Сколько это стоит? И как оплатить?",0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1,"Хочу сразу несколько самокатов! Так можно?",1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2,"Как рассчитывается время аренды?",2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3,"Можно ли заказать самокат прямо на сегодня?",3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4,"Можно ли продлить заказ или вернуть самокат раньше?",4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5,"Вы привозите зарядку вместе с самокатом?",5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6,"Можно ли отменить заказ?",6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7,"Я жизу за МКАДом, привезёте?",7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
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
        String getQuestionText = homePageObject.checkFAQQuestion(questionAdd);
        assertEquals("Тест не отработал! Неверный вопрос", questionIndex,getQuestionText);
    }


    @Test
    public void checkAnswerTest() {
        HomePageObject homePageObject = new HomePageObject(driver);
        homePageObject.clickСookieButton();
        homePageObject.scrollQuestions();
        homePageObject.clickAllQuestions(questionAdd);
        String getAnswertext = homePageObject.getAnswerQuestions(answerAdd);
        assertEquals("Тест не отработал! Неверный ответ", answerIndex, getAnswertext);
    }

    @After
    public void closeDriver() {
            driver.quit();
        }
    }

