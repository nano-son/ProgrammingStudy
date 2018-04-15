package patterns.DecoratorPattern;

/**
 * Created by Nano.son on 2018. 4. 15.
 */
public class Main {
    public static void main(String[] args) {
        Gundam baseGundam = new ConcreteGundam(10,10,10);
        baseGundam.status();

        Gundam m_gundam = new MachineGun_Gundam(baseGundam);
        m_gundam.status();

        Gundam w_gundam = new Wing_Gundam(m_gundam);
        w_gundam.status();

        //머신건, 날개, 방패를 장착한 건담
        Gundam s_gundam = new Shield_Gundam(w_gundam);
        s_gundam.status();

        //머신건 두개에, 날개하나, 방패 두 개를 장착한 건담
        Gundam mixed_gundam = new MachineGun_Gundam(new Shield_Gundam(s_gundam));
        mixed_gundam.status();
    }
}
