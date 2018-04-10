package patterns.AdapterPattern;

/**
 * Created by Nano.son on 2018. 4. 8.
 */
public class MyCamera {
    private SonyLens lens;

    public MyCamera(SonyLens lens) {
        this.lens = lens;
    }

    public MyCamera() {
    }

    public void setLens(SonyLens lens) {
        this.lens = lens;
    }

    public void takePicture() {
        System.out.println("셔터를 눌렀다");
        lens.useLens();
        System.out.println("찰칵");
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MyCamera{");
        sb.append("lens=").append(lens);
        sb.append('}');
        return sb.toString();
    }
}
