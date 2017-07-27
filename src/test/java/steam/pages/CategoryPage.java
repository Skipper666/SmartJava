package steam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import smartframework.base.BaseElement;
import smartframework.base.BasePage;
import smartframework.elements.Label;
import smartframework.model.Game;
import smartframework.utils.Configuration;

import java.util.ArrayList;
import java.util.List;


public class CategoryPage extends BasePage {

    private static final By pageLocator = By.xpath("//h2");

    private Label btnDiscounts = new Label(By.xpath("//div[contains(@id,'select_Discounts')]"));
    private Label discountsRows = new Label(By.xpath("//div[@id='DiscountsRows' and contains(@style,'opacity: 1')]//a"));
    private static By gameNameLocator = By.xpath(".//div[contains(@class,'item_name')]");
    private static By gameLinkLocator = By.xpath("./a");
    private static By gameOriginPriceLocator = By.xpath(".//div[contains(@class,'original_price')]");
    private static By gameDiscountLocator = By.xpath(".//div[@class='discount_pct']");
    private static By gameFinalPriceLocator = By.xpath(".//div[contains(@class,'final')]");

    private static final String GAME_WITH_MAX_DISCOUNT_TEMPLATE = "//a[@href='%s']";
    private static final String comparableText = Configuration.resourceBundle.getString("browsing");
    public static Game gameWithMaxDiscount = null;

    public CategoryPage() {
        super(pageLocator, comparableText);
    }

    public void clickDiscounts() {
        btnDiscounts.click();
        log.info("Перешли на вкладку 'Скидки'");
    }

    public void selectGameWithMaxDiscount() {
        List<WebElement> allItems = discountsRows.waitForElementsPresent();
        List<Game> allInfoAboutGame = new ArrayList<>();
        for (WebElement element : allItems) {
            String gameName = BaseElement.searchInnerElement(element, gameNameLocator).getText();
            String gameLink = element.getAttribute("href");
            String gameOriginPrice = BaseElement.searchInnerElement(element, gameOriginPriceLocator).getText();
            String gameDiscount = BaseElement.searchInnerElement(element, gameDiscountLocator).getText();
            String gameFinalPrice = BaseElement.searchInnerElement(element, gameFinalPriceLocator).getText();
            Game game = new Game(gameName, gameLink, gameOriginPrice, gameDiscount, gameFinalPrice);
            allInfoAboutGame.add(game);
        }
        int maxPct = 0;
        for (Game game : allInfoAboutGame) {
            if (Integer.parseInt(game.getDiscount().substring(1, 3)) > maxPct) {
                maxPct = Integer.parseInt(game.getDiscount().substring(1, 3));
                gameWithMaxDiscount = game;
            }
        }
        Label linkGameWithMaxDiscount = new Label(By.xpath(String.format(GAME_WITH_MAX_DISCOUNT_TEMPLATE, gameWithMaxDiscount.getLink())));
        linkGameWithMaxDiscount.click();
        log.info("Переход в игру с max скидкой.Игра с max скидкой - " + gameWithMaxDiscount.getNameGame() + ". Скидка составляет: " + gameWithMaxDiscount.discount);
    }
}
