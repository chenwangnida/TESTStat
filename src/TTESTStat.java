import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
import org.apache.commons.math3.stat.inference.*;

public class TTESTStat {
	public static void main(String[] args) {
		double[] sample1 = { 1, 2, 3, 4, 3, 5, 6.1, 3.4, 2.9, 4.4 };
		double[] sample2 = { 5.2, 4.2, 7.24, 4, 5, 6, 4.1, 5.9, 7.0, 8.0 };

		StatisticalSummary stat1 = new DescriptiveStatistics(sample1);
		StatisticalSummary stat2 = new DescriptiveStatistics(sample2);

		TTest ttest = new TTest();
		p_value = ttest.tTest(stat1, stat2);
		System.out.println(p_value);
	}
}