package patterns.AdapterPattern;

/**
 * Created by Nano.son on 2018. 4. 8.
 */
public class CanonLensHigh implements CanonLens {
    private final int year;
    private final int min_distance, max_distance;
    private final int preformanceScore;

    public CanonLensHigh(int year, int preformanceScore, int min_distance, int max_distance) {
        this.year = year;
        this.min_distance = min_distance;
        this.max_distance = max_distance;
        this.preformanceScore = preformanceScore;
    }

    @Override
    public void takePicture() {
        System.out.println(year+"산 캐논 렌즈를 사용!!");
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CanonLensHigh{");
        sb.append("year=").append(year);
        sb.append(", min_distance=").append(min_distance);
        sb.append(", max_distance=").append(max_distance);
        sb.append(", preformanceScore=").append(preformanceScore);
        sb.append('}');
        return sb.toString();
    }
}
