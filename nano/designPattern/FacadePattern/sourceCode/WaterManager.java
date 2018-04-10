package patterns.FacadePattern;

/**
 * Created by Nano.son on 2018. 4. 9.
 */
public class WaterManager {
    private final Boiler boiler;

    public static class Water {
        private final int temperature;
        private final int amount;

        private Water(int temperature, int amount) {
            this.temperature = temperature;
            this.amount = amount;
        }

        public int getTemperature() {
            return temperature;
        }

        public int getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Water{");
            sb.append("temperature=").append(temperature);
            sb.append(", amount=").append(amount);
            sb.append('}');
            return sb.toString();
        }
    }

    public WaterManager(Boiler boiler) {
        this.boiler = boiler;
    }

    public Water getWater(int amount) {
        return new Water(boiler.getWaterTemperature(), amount);
    }

    public void setWaterTemperature(int temperature) {
        this.boiler.setWaterTemperature(temperature);
    }

    public int getWaterTemperature() {
        return this.boiler.getWaterTemperature();
    }
}
