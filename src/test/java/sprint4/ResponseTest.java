package sprint4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.MainPage;


@RunWith(Parameterized.class)
public class ResponseTest {
    private WebDriver driver;
    private String questionId;
    private String textId;
    private String responseId;

        public ResponseTest(String questionId, String textId, String responseId) {
            // Локатор для id вопроса
            this.questionId = questionId;
            // Образец ответа
            this.textId = textId;
            // Локатор для id ответа
            this.responseId = responseId;
        }

    @Parameterized.Parameters
    public static Object[][] getQuestions() {
        return new Object[][] {
                { "accordion__heading-0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", "accordion__panel-0"},
                { "accordion__heading-1", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", "accordion__panel-1"},
                //В строку ниже специально внесены изменения, что бы проверить как программа реагирует на несовпадение с образцом.
                { "accordion__heading-2", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите за заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", "accordion__panel-2"},
                { "accordion__heading-3", "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", "accordion__panel-3"},
                { "accordion__heading-4", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", "accordion__panel-4"},
                { "accordion__heading-5", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", "accordion__panel-5"},
                { "accordion__heading-6", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", "accordion__panel-6"},
                //В строку ниже специально внесены изменения, что бы проверить как программа реагирует на несовпадение с образцом.
                { "accordion__heading-7", "Да, обязательно. Всем самокаты! И Москве, и Московской области.", "accordion__panel-7"},
        };
    }
    @Before
    public void startActivity() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox","--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    @Test
    public void checkQuestions() {
            //Создание объекта класса главной страницы
            MainPage objMainPage = new MainPage(driver);
            //Ожидание загрузки страницы
            objMainPage.waitPageLoad();
            //Подтвердить согласие на использование куки
            objMainPage.clickConfirmButton();
            //Кликнуть на вопрос
            objMainPage.clickOnQuestion(questionId);
            //Дождаться загрузки ответа
            objMainPage.waitFindResponse(responseId);
            //Сравнить ответ с образцом
            Assert.assertEquals("Текст не соответствует образцу",textId,
                driver.findElement(By.id(responseId)).getText());
    }

   @After
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }
}


