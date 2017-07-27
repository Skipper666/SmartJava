package battleShip.tests;

import battleShip.pages.BattleShipGamePage;
import org.testng.annotations.Test;
import smartframework.base.BaseTest;

public class BattleShipTest extends BaseTest {

    @Test
    public void BattleShip() {

        BattleShipGamePage battleShipGamePage = new BattleShipGamePage();
        battleShipGamePage.chooseRandomEnemy();
        battleShipGamePage.arrangeShips();
        battleShipGamePage.clickStartButton();
        battleShipGamePage.waitEnemy();
        battleShipGamePage.play();
    }
}
