package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class OrderPage {
    //Создать переменную вебдрайвера
    private WebDriver driver;

    //Локаторы формы "для кого самокат"
    // Локатор для поля "Имя"
    private final By inputName = By.xpath(".//input[contains(@placeholder,'Имя')]");
    // Локатор для поля "Фамилия"
    private final By inputSurname = By.xpath(".//input[contains(@placeholder,'Фамилия')]");
    // Локатор для поля "Адрес"
    private final By inputAddress = By.xpath(".//input[contains(@placeholder,'Адрес')]");
    // Локатор для поля "Станция метро"
    private final By inputMetro = By.xpath(".//input[contains(@placeholder,'Станция')]");
    //Локатор для выпадающего списка
    private final By stationMetro = By.className("select-search__select");
    // Локатор для поля "Телефон"
    private final By inputPhone = By.xpath(".//input[contains(@placeholder,'Телефон')]");
    //Локатор для кнопки "Далее"
    private final By nextButton = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");

    //Локаторы для формы "Про аренду".
    //Поле даты.
    private final By inputDate = By.xpath(".//input[contains(@placeholder,'Когда')]");
    private final By selectedDay = By.xpath(".//div[contains(@class, 'react-datepicker__day--selected')]");
    //Локаторы для выпадающего списка - период
    private final By rentPeriodSelect = By.xpath(".//div[text()='* Срок аренды']");
    private final String rentPeriodPattern = ".//div[@class='Dropdown-option' and text()='%s']";
    //Локаторы для выбора цвета
    private final By greyLabel = By.xpath(".//input[@id = 'grey']");
    private final By blackLabel = By.xpath(".//input[@id = 'black']");
    //Локатор для поля комментарий
    private final By inputNote = By.xpath(".//input[contains(@placeholder,'Комментарий')]");

    //Лакатор для кнопки "Да"
    private final By yesButton = By.xpath(".//button[text()='Да']");
    //Локатор для кнопки "Посмотреть статус"
    private final By statusButton = By.xpath(".//button[text()='Посмотреть статус']");
    //Локатор для блока с информацией о заказе
    private final By trackOrderInfo = By.xpath(".//div[contains(@class, 'Track_OrderInfo')]");
    //Локатор для модального окна подтверждения заказа
    private final By statusOrder = By.xpath(".//div[contains(@class, 'Order_ModalHeader')]");
    //Локатор для логотипа самокат.
    private final By scooterLogo = By.xpath(".//a[contains(@class, 'Header_LogoScooter')]");
    //Конструктор класса
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Заполнить поля формы "Для кого самокат"
    public void spellingField(String name, String surname, String address, String metro, String phone) {
        //Ввести имя
        driver.findElement(inputName).sendKeys(name);
        //Ввести фамилию
        driver.findElement(inputSurname).sendKeys(surname);
        //ВВести адрес
        driver.findElement(inputAddress).sendKeys(address);
        //Ввести станцию метро
        driver.findElement(inputMetro).sendKeys(metro);
        //Кликнуть по введенной станции метро в выпадающем списке
        driver.findElement(stationMetro).click();
        //Ввести номер телефона
        driver.findElement(inputPhone).sendKeys(phone);
    }
    //Кликнуть по кнопке "Далее"
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    //Ввести дату и комментарий
    public void inputField(String date, String note) {
        driver.findElement(inputDate).sendKeys(date);
        //Кликнуть по выбранной дате, чтобы закрыть выпадающий список с календарем.
        driver.findElement(selectedDay).click();
        driver.findElement(inputNote).sendKeys(note);

    }

    //Выбрать период
    public void selectRentPeriod(String period) {
        driver.findElement(rentPeriodSelect).click();
        driver.findElement(By
                .xpath(String.format(rentPeriodPattern, period))).click();
    }

    //Выбрать цвет самоката
    public void selectColor(String color) {
        if (Objects.equals(color, "чёрный жемчуг")) {
            driver.findElement(blackLabel).click();
        }
        if (Objects.equals(color, "серая безысходность")) {
            driver.findElement(greyLabel).click();
        }
    }
    //Кликнуть на кнопку да.
    public void clickOnYesButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(yesButton));
        driver.findElement(yesButton).click();
    }
    //Прочитать заголовок на всплывающем окне "Статус заказа"
    public String readOrderStatus() {
        return driver.findElement(statusOrder).getText();
    }
    //Кликнуть на кнопку "Посмотреть статус"
    public void clickOnStatusButton() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(statusButton));
        driver.findElement(statusButton).click();
    }
    //Ожидание загрузки страницы с информацией о заказе
    public void waitPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(trackOrderInfo));
    }
    //Кликнуть по логотипу самокат, что бы вернутся на главную страницу
    public void clickOnLogo() {
        driver.findElement(scooterLogo).click();
    }
}

