package patterns.DecoratorPattern;

/**
 * Created by Nano.son on 2018. 4. 15.
 */
public class ConcreteGundam implements Gundam {
    private final int power;
    private final int defense;
    private final int speed;

    public ConcreteGundam(int power, int defense, int speed) {
        this.power = power;
        this.defense = defense;
        this.speed = speed;
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public int getDefense() {
        return this.defense;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public String equipmentList() {
        return "base";
    }
}
