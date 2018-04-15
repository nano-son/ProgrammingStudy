package patterns.DecoratorPattern;

/**
 * Created by Nano.son on 2018. 4. 15.
 */
public abstract class Decorator implements Gundam {
    protected Gundam gundam;

    public Decorator(Gundam gundam) {
        this.gundam = gundam;
    }

    @Override
    public int getPower() {
        return gundam.getPower();
    }

    @Override
    public int getDefense() {
        return gundam.getDefense();
    }

    @Override
    public int getSpeed() {
        return gundam.getSpeed();
    }

    @Override
    public String equipmentList() {
        return gundam.equipmentList();
    }
}
