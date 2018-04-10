package patterns.FacadePattern;

/**
 * Created by Nano.son on 2018. 4. 9.
 */
public class CoffeeMachine {
    private Grinder grinder;
    private WaterManager waterManager;

    public CoffeeMachine() {
        this.grinder = new Grinder();
        this.waterManager = new WaterManager(new Boiler());
    }

    public Coffee hotAmericano() {
        Coffee c = espresso();
        waterManager.setWaterTemperature(90);
        WaterManager.Water water = waterManager.getWater(200);
        return new Coffee(c, water);
    }

    public Coffee iceAmericano() {
        Coffee c = espresso();
        waterManager.setWaterTemperature(4);
        WaterManager.Water water = waterManager.getWater(250);
        return new Coffee(c, water);
    }

    public Coffee espresso() {

        waterManager.setWaterTemperature(90);
        WaterManager.Water water = waterManager.getWater(50);
        Grinder.Bean bean = grinder.grind(20);
        return new Coffee(bean, water);
    }
}
