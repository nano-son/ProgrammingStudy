package patterns.DecoratorPattern;

/**
 * Created by Nano.son on 2018. 4. 15.
 */
public class Wing_Gundam extends Decorator {
    public Wing_Gundam(Gundam gundam) {
        super(gundam);
    }

    @Override
    public int getSpeed() {
        return gundam.getSpeed()+10;
    }

    @Override
    public String equipmentList() {
        return "Wing /" + gundam.equipmentList();
    }
}
