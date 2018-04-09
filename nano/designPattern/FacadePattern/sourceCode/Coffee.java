package patterns.FacadePattern;

import patterns.FacadePattern.WaterManager.Water;

/**
 * Created by Nano.son on 2018. 4. 9.
 */

public class Coffee {
    private final double concentration;
    private int temperature;
    private final int amount;

    //단순하게 물의 양 == 커피의 양 이라고 생각
    public Coffee(Grinder.Bean bean, Water water) {
        concentration = (bean.getScale()*bean.getAmount())/(double)water.getAmount();
        temperature = water.getTemperature();
        amount = water.getAmount();
    }

    public Coffee (Coffee coffee, Water water) {
        concentration = coffee.getConcentration()/(double)water.getAmount();
        amount = coffee.getAmount() + water.getAmount();
        temperature = water.getTemperature();
    }

    public double getConcentration() {
        return concentration;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Coffee{");
        sb.append("concentration=").append(concentration);
        sb.append(", temperature=").append(temperature);
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}
