package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MainPage {
    //Драйвер
    private WebDriver driver;
    //Локатор для картитнки самоката
    private final By scooterImg = By.xpath(".//img[@src='/assets/blueprint.png']");
    //Локатор для кнопки куки
    private final By cookButton = By.id("rcc-confirm-button");
    //Локатор для кнопки "Заказать" сверху страницы
    private final By topOrderButton = By.xpath(".//div[@class = 'Header_Nav__AGCXC']/button");
    //Локатор для кнопки "Заказать" в середине страницы
    private final By middleOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");


    //Конструктор класса
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    //Ждём загрузки главной страницы по загрузке картинки с самокатом, так как дольше всего грузится
    public void waitPageLoad()
    {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(scooterImg));
    }
    //Подтвердить согласие на использование куки
    public void clickConfirmButton() {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.findElement(cookButton).click();
    }
    //Кликнуть на соответствующий вопрос
    public void clickOnQuestion(String questionId) {
        driver.findElement(By.id(questionId)).click();
    }
    //Дождаться загрузки ответа
    public void waitFindResponse (String responseId) {new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOf(driver.findElement(By.id(responseId))));}
    //Кликнуть по кнопке Заказать в середине страницы
    public void clickOnMiddleOrderButton() {
        driver.findElement(middleOrderButton).click();
    }
    //Кликнуть по кнопке Заказать сверху страницы
    public void clickOnTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }

}
