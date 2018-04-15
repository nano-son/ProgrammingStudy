package patterns.DecoratorPattern;

/**
 * Created by Nano.son on 2018. 4. 15.
 */
public class MachineGun_Gundam extends Decorator {
    public MachineGun_Gundam(Gundam gundam) {
        super(gundam);
    }

    @Override
    public int getPower() {
        return gundam.getPower() + 10;
    }

    @Override
    public String equipmentList() {
        return "MachineGun / " +gundam.equipmentList();
    }
}
