package patterns.FacadePattern;

/**
 * Created by Nano.son on 2018. 4. 9.
 */
public class Main {
    public static void main(String[] args) {
        CoffeeMachine myCoffeeMachine = new CoffeeMachine();

        System.out.println(myCoffeeMachine.espresso());

        System.out.println(myCoffeeMachine.iceAmericano());
    }
}
