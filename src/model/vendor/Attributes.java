package model.vendor;
/**
 * @author Leith Ahmad
 * Class for the attributes of the consumables
 */
public class Attributes {
    //Different types of attributes
    enum Type {
        Damage,
        Health,
        BleedingDamage
    }
    private final Type type;
    private int hp;
    private int strength;
    private int resilience;

    /**
     * Constructor for the class that initializes the type of attribute
     * @param type type of attribute
     */
    public Attributes(Type type) {
        this.type = type;
    }

    /**
     * Set method for the attributes
     */
    public void setAttributes() {
    }
}
