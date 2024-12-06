package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class RentalConditions {

        private WebDriver driver;


        //Заголовок страницы
         private final By pageTitle = By.className("Order_Header__BZXOb");
        //Поле ввода "Когда привезти самокат"
        private final By arrivalDate = By.xpath(".//div[@class='react-datepicker__input-container']//input");
        //Поле "Срок аренды"
        private final By fieldRental = By.className("Dropdown-placeholder");
        //Меню выбора срока аренды
        private final By periodRent = By.className("Dropdown-option");
        //Поле выбора цвета самоката
        private final By colorScooter = By.className("Checkbox_Label__3wxSf");
        //Поле "Комментарий для курьера"
        private final By coomentСourier = By.xpath(".//input[@placeholder='Комментарий для курьера']");
        //Кнопка "Назад"
        private final By buttonBack = By.xpath(".//button[text()='Назад']");
        //Кнопка "Нет"
        private final By buttonNo = By.xpath(".//button[text()='Нет']");
        //Кнопка "Да"
        private final By buttonYes = By.xpath(".//button[text()='Да']");
        //Кнопка "Посмотреть статус"
        private final By buttonStatus = By.xpath(".//button[text()='Посмотреть статус']");




    public RentalConditions(WebDriver driver) {
        this.driver = driver;
    }

    //Метод Проерки корректности заголовка страницы
    public String checkPageTitle() {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        return driver.findElement(pageTitle).getText();
    }

    //Метод выбора даты доставки
    public void deliveryArrivalDate(String date) {
        new WebDriverWait(driver,Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(arrivalDate));
        WebElement deliArrivalDate = driver.findElement(arrivalDate);
        deliArrivalDate.click();
        deliArrivalDate.sendKeys(date);
        deliArrivalDate.sendKeys(Keys.ENTER);
    }
    //Выбор срока аренды
    public String choiceRentalPeriod () {
        new WebDriverWait(driver,Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(fieldRental));
        driver.findElement(fieldRental).click();
        List<WebElement> listElements = driver.findElements(periodRent);
        Random random = new Random();
        int randomIndex = random.nextInt(listElements.size());
        listElements.get(randomIndex).click();
        return driver.findElement(fieldRental).getText();
    }

    //Метод выбора цвета самоката
    public String setColorScooter () {
        new WebDriverWait(driver,Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(colorScooter));
        List<WebElement> colorCheckBox = driver.findElements(colorScooter);
        Random random = new Random();
        int randomIndex = random.nextInt(colorCheckBox.size());
        colorCheckBox.get(randomIndex).click();
        return driver.findElement(colorScooter).getText();
    }
    //Метод заполнения поле "Комментарий"
    public void inputComment (String comment) {
        new WebDriverWait(driver,Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(coomentСourier));
        driver.findElement(coomentСourier).click();
        driver.findElement(coomentСourier).sendKeys(comment);
    }

    //Метод нажатия кнопки "Назад"
    public void clickButtonBack () {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(buttonBack));
        driver.findElement(buttonBack).click();
    }

        //Метод нажатия кнопки Заказать
            public void clickButtonOrder(String buttonOrder ) {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath (buttonOrder)));
                driver.findElement(By.xpath(buttonOrder)).click();
    }

    //Метод нажатия кнопки "Нет"
    public void clickButtonNo () {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(buttonNo));
        driver.findElement(buttonNo).click();
    }

    //Метод нажатия кнопки "Да"
    public void clickButtonYes () {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(buttonYes));
        driver.findElement(buttonYes).click();
    }

    //Метод нажатия кнопки "Посмотреть статус"
    public void clickButtonStatus () {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(buttonStatus));
        driver.findElement(buttonStatus).click();
    }

}

