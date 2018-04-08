package patterns.AdapterPattern;

/**
 * Created by Nano.son on 2018. 4. 8.
 */
public class Main {
    public static void main(String[] args) {
        MyCamera myCamera = new MyCamera(new SonyLensMiddle(2016, 60, 30, 60));
        myCamera.takePicture();

        System.out.println("\n**********************\n");
        CanonLensHigh cLens = new CanonLensHigh(2018, 85, 30, 70);
        myCamera.setLens(new LensAdapter(cLens));
        myCamera.takePicture();
    }
}
