package controller.handlersAndActions;

import controller.GameLogic;
import model.items.DamagePotion;
import model.items.Shield;

/**
 * @author Mahmoud Daabas
 * @author Annie Tran
 * @author Vilgot Mattsson
 * This class has the items that are available for purchase in the shop.
 */
public class ShopItems {
    private GameLogic controller;

    private DamagePotion dmgPot;

    private Shield shield;

    //Used to limit the purchase of items.
    private int damagePotionLimit;
    private int shieldLimit;

    /**
     * Constructs the class.
     * @param controller GameLogic-object used to initialize own GameLogic-object
     */
    public ShopItems(GameLogic controller) {
        this.controller = controller;

        dmgPot = new DamagePotion(2, false);

        shield = new Shield(false);
    }

    /**
     * Method that allows the user to purchase health.
     */
    public void buyHealth() {
        if (controller.getPlayer().getPlayerHealth() < 10 && controller.getPlayer().getGold() > 1) {
            controller.getMusicPlayer().playSoundEffects("resources/soundtracks/heartBeatSound.wav");
            int previousHealth = controller.getPlayer().getPlayerHealth();
            previousHealth++;
            controller.getPlayer().setPlayerHealth(previousHealth);
            controller.getMainFrame().getHealthBar().increaseHealth();
            controller.getPlayer().setGold(controller.getPlayer().getGold() - 2);
            controller.getCounter().updateCoinLabel();
            controller.getMainFrame().getTextArea().setText("You purchased 1 HP for 2 gold.");
            controller.getMainFrame().getLabelsAndStatus().getLblPotionStatus().setVisible(false);
        }
        else if (controller.getPlayer().getGold() < 2) {
            controller.getMainFrame().getTextArea().setText("You don't have enough gold!");
        }
        else {
            controller.getMainFrame().getTextArea().setText("You already have full hp!");
        }
    }

    /**
     * Method that allows the user to purchase a damage potion.
     */
    public void buyDamagePotion() {
        if (controller.getPlayer().getGold() > 2 && damagePotionLimit == 0) {
            controller.getMusicPlayer().playSoundEffects("resources/soundtracks/itemBoughtSound.wav");
            controller.getMainFrame().getBtnDamagePotion().setVisible(true);
            controller.getPlayer().setGold(controller.getPlayer().getGold() - 3);
            controller.getCounter().updateCoinLabel();
            controller.getMainFrame().getTextArea().setText("You purchased a damage potion for 3 gold. \n" +
                    "You can activate it on the bottom right of the screen. \n" +
                    "The potion will only remain active for 1 level, use it wisely!");
            damagePotionLimit = 1;
        }
        else if (damagePotionLimit == 1) {
            controller.getMainFrame().getTextArea().setText("You already have a damage potion.\n" +
                    "You need to consume it before buying a new one.");
        }
        else {
            controller.getMainFrame().getTextArea().setText("You don't have enough gold!");
        }
    }

    /**
     * Method that allows the user to purchase a shield.
     */
    public void buyShield() {
        if (controller.getPlayer().getGold() > 1 && shieldLimit == 0) {
            controller.getMusicPlayer().playSoundEffects("resources/soundtracks/itemBoughtSound.wav");
            controller.getMainFrame().getBtnShield().setVisible(true);
            controller.getPlayer().setGold(controller.getPlayer().getGold() - 2);
            controller.getCounter().updateCoinLabel();
            controller.getMainFrame().getTextArea().setText("You purchased a shield potion for 2 gold. \n" +
                    "You can activate it on the bottom right of the screen. \n" +
                    "The shield will only remain active for 1 hit where you will not take damage. \n");
            shieldLimit = 1;
        }
        else if (shieldLimit == 1) {
            controller.getMainFrame().getTextArea().setText("You already have a shield.\n" +
                    "You need to equip it before buying a new one.");
        }
        else {
            controller.getMainFrame().getTextArea().setText("You don't have enough gold!");
        }
    }

    /**
     * Allows you to set the Damage Potion limit from outside of the class.
     * @param damagePotionLimit new limit
     */
    public void setDamagePotionLimit(int damagePotionLimit) {
        this.damagePotionLimit = damagePotionLimit;
    }

    /**
     * Returns DamagePotion-object
     * @return dmgPot
     */
    public DamagePotion getDmgPot() {
        return dmgPot;
    }

    /**
     * Allows you to set the shield limit from outside the class.
     * @param shieldLimit new shield limit
     */
    public void setShieldLimit(int shieldLimit) {
        this.shieldLimit = shieldLimit;
    }

    /**
     * Returns the shield for outside use.
     * @return shield
     */
    public Shield getShield() {
        return shield;
    }
}
