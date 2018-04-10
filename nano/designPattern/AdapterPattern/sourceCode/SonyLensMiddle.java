package patterns.AdapterPattern;

/**
 * Created by Nano.son on 2018. 4. 8.
 */
public class SonyLensMiddle implements SonyLens {
    private int year;
    private int performance_score;
    private int min_distance;
    private int max_distance;

    public SonyLensMiddle(int year, int performance_score, int min_distance, int max_distance) {
        this.year = year;
        this.performance_score = performance_score;
        this.min_distance = min_distance;
        this.max_distance = max_distance;
    }

    @Override
    public void useLens() {
        System.out.println("Sony사 렌즈를 사용!!");
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SonyLensMiddle{");
        sb.append("year=").append(year);
        sb.append(", performance_score=").append(performance_score);
        sb.append(", min_distance=").append(min_distance);
        sb.append(", max_distance=").append(max_distance);
        sb.append('}');
        return sb.toString();
    }
}
