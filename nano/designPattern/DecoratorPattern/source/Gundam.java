package patterns.DecoratorPattern;

/**
 * Created by Nano.son on 2018. 4. 15.
 */
public interface Gundam {

    int getPower();

    int getDefense() ;

    int getSpeed();

    String equipmentList();

    default void status() {
        System.out.println("--------------------------");
        System.out.println("equipment List:"+this.equipmentList());
        System.out.println("power:"+this.getPower());
        System.out.println("defense:"+this.getDefense());
        System.out.println("speed:"+this.getSpeed());
    }
}
