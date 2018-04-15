package patterns.DecoratorPattern;

/**
 * Created by Nano.son on 2018. 4. 15.
 */
public class Shield_Gundam extends Decorator {
    public Shield_Gundam(Gundam gundam) {
        super(gundam);
    }

    @Override
    public int getDefense() {
        return gundam.getDefense() + 10;
    }

    @Override
    public String equipmentList() {
        return "Shield /"+gundam.equipmentList();
    }
}
