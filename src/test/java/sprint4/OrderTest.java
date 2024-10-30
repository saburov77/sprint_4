package sprint4;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pageobject.MainPage;
import pageobject.OrderPage;

import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Parameterized.class)
public class OrderTest {
    private static WebDriver driver;

    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String period;
    private final String color;
    private final String note;

    public OrderTest(String name, String surname, String address, String metro, String phone,
                     String date, String period, String color, String note) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.color = color;
        this.note = note;

    }

    @Parameterized.Parameters
    public static Object[][] orderInfo() {
        return new Object[][]{
                {"Незнайка", "Солнечный", "ул.Лунная, 15", "Лубянка", "+79997775533", "27.12.2024", "двое суток", "серая безысходность", "Без комментариев!"},
                {"Мурлыка", "Сиамский", "ул.Крылатские коты, 13", "Воробьёвы горы", "+79957735593", "16.11.2024", "семеро суток", "чёрный жемчуг", "У двери мяукнуть, а не царапать!"}
        };
    }
    @Before
    public void startActivity() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new FirefoxDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru");
    }
    @Test
    public void checkOrder() {
        //Создание объекта класса главной страницы
        MainPage objMainPage = new MainPage(driver);
        //Создание объекта класса страницы заказа
        OrderPage objOrderPage = new OrderPage(driver);
        //Ожидание загрузки главной страницы
        objMainPage.waitPageLoad();
        //Подтвердить согласие на использование куки
        objMainPage.clickConfirmButton();
        //Кликнуть по кнопке "Заказать" в центре страницы
        objMainPage.clickOnMiddleOrderButton();
        //Заполняем поля имя, фамилия, адрес, метро, телефон
        objOrderPage.spellingField(name, surname, address, metro, phone);
        //Кликнуть по кнопке "Далее"
        objOrderPage.clickNextButton();
        //Ввести дату и комментарий
        objOrderPage.inputField(date, note);
        //Выбрать период
        objOrderPage.selectRentPeriod(period);
        //Выбрать цвет самоката
        objOrderPage.selectColor(color);
        //Кликнуть на кнопку "Заказать" ниже заполненной формы. При использовании кнопки "Заказать"
        objMainPage.clickOnMiddleOrderButton();
        //Кликнуть на кнопку "Да".
        objOrderPage.clickOnYesButton();
        //Проверить, что текст в окне содержит текст "Заказ оформлен"
        MatcherAssert.assertThat("Заказ не оформлен", objOrderPage.readOrderStatus(), containsString("Заказ оформлен"));
        //Кликнуть на кнопку "Посмотреть статус"
        objOrderPage.clickOnStatusButton();
        //Ожидание загрузки страницы с информацией о заказе
        objOrderPage.waitPageLoad();
        //Кликнуть по логотипу самокат, что бы вернутся на главную страницу.
        objOrderPage.clickOnLogo();
        //Ожидание загрузки главной страницы
        objMainPage.waitPageLoad();
        //Кликнуть по кнопке "Заказать" сверху страницы
        objMainPage.clickOnTopOrderButton();
        //Для того чтобы убедится что кнопка "Заказать" работает, заполняем ещё раз поля формы "Для кого самокат"
        objOrderPage.spellingField(name, surname, address, metro, phone);


    }

  @After
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }
}
