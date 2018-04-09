package patterns.FacadePattern;

/**
 * Created by Nano.son on 2018. 4. 9.
 */
public class Grinder {
    private int scale;

    public static class Bean {
        private final int amount; //양
        private final int scale; //갈린 크기

        private Bean(int amount, int scale) {
            this.amount = amount;
            this.scale = scale;
        }

        public int getAmount() {
            return amount;
        }

        public int getScale() {
            return scale;
        }
    }

    public Grinder(int scale) {
        this.scale = scale;
    }

    public Grinder() {
        scale = 5;
    }

    public Bean grind(int amount) {
        return new Bean(amount, this.scale);
    }
}
