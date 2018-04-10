package patterns.AdapterPattern;

/**
 * Created by Nano.son on 2018. 4. 8.
 */
public class LensAdapter implements SonyLens {
    private CanonLens cLens;

    public LensAdapter(CanonLens cLens) {
        this.cLens = cLens;
    }

    @Override
    public void useLens() {
        System.out.println("렌즈 어댑터를 사용!");
        cLens.takePicture();
    }
}
