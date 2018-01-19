import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
import org.apache.commons.math3.stat.inference.TTest;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Stat {
	public static void main(String[] args) {

		// path settings
		String filePath1 = "/Users/chenwang/Documents/Experiment Results/EDA_original_wsc095/";
		String filePath2 = "/Users/chenwang/Documents/Experiment Results/EDA_MO_wsc09MO5/";
		String filePath3 = "/Users/chenwang/Documents/Experiment Results/EDA_MT_wsc09MT3/";
		String filePath4 = "/Users/chenwang/Documents/Experiment Results/PSO_wsc091/";

		// String statPath = "/Users/chenwang/Documents/Experiment
		// Results/results1/merged1and2.txt";

		double[] sample1_totalTime;
		double[] sample2_totalTime;
		double[] sample3_totalTime;
		double[] sample4_totalTime;

		double[] sample1_bestFitnessSoFar;
		double[] sample2_bestFitnessSoFar;
		double[] sample3_bestFitnessSoFar;
		double[] sample4_bestFitnessSoFar;

		double[][] sample1_meanBestFitnessPerGen;// [iteration][seed]
		double[][] sample2_meanBestFitnessPerGen;
		double[][] sample3_meanBestFitnessPerGen;
		double[][] sample4_meanBestFitnessPerGen;

		int sampleSize = 30;
		int interation = 30;
		int interation4PSO = 200;

		double p1_2 = 0.0;
		double p1_3 = 0.0;
		double p1_4 = 0.0;

		sample1_totalTime = new double[sampleSize];
		sample2_totalTime = new double[sampleSize];
		sample3_totalTime = new double[sampleSize];
		sample4_totalTime = new double[sampleSize];

		sample1_bestFitnessSoFar = new double[sampleSize];
		sample2_bestFitnessSoFar = new double[sampleSize];
		sample3_bestFitnessSoFar = new double[sampleSize];
		sample4_bestFitnessSoFar = new double[sampleSize];

		sample1_meanBestFitnessPerGen = new double[sampleSize][interation];// [iteration][seed]
		sample2_meanBestFitnessPerGen = new double[sampleSize][interation];
		sample3_meanBestFitnessPerGen = new double[sampleSize][interation];
		sample4_meanBestFitnessPerGen = new double[sampleSize][interation];

		try {

			TTest ttest = new TTest();

			// List<String> mergedlines = new ArrayList<String>();

			for (int i = 0; i < sampleSize; i++) {
				List<String> lines4sample1 = Files.readAllLines(Paths.get(filePath1 + i + ".stat"));

				// get total time for seed
				double seed_TotalTime = 0.0;
				double seed_bestFitness = 0.0;

				for (int j = 0; j < interation; j++) {
					String[] pop = lines4sample1.get(j).split("\\s+");
					seed_TotalTime += Double.parseDouble(pop[1]) + Double.parseDouble(pop[2]);

					sample1_meanBestFitnessPerGen[i][j] = Double.parseDouble(pop[3]);

					// get best fitness so far for each seed
					if (j == interation - 1) {
						seed_bestFitness = Double.parseDouble(pop[3]);
					}
				}

				// put total time and bestFitness into sample array
				sample1_totalTime[i] = seed_TotalTime;
				sample1_bestFitnessSoFar[i] = seed_bestFitness;
			}

			for (int i = 0; i < sampleSize; i++) {
				List<String> lines4sample2 = Files.readAllLines(Paths.get(filePath2 + i + ".stat"));

				// get total time for seed
				double seed_TotalTime = 0.0;
				double seed_bestFitness = 0.0;

				for (int j = 0; j < interation; j++) {
					String[] pop = lines4sample2.get(j).split("\\s+");
					seed_TotalTime += Double.parseDouble(pop[1]) + Double.parseDouble(pop[2]);

					sample2_meanBestFitnessPerGen[i][j] = Double.parseDouble(pop[3]);

					// get best fitness so far for each seed
					if (j == interation - 1) {
						seed_bestFitness = Double.parseDouble(pop[3]);
					}
				}

				// put total time and bestFitness into sample array
				sample2_totalTime[i] = seed_TotalTime;
				sample2_bestFitnessSoFar[i] = seed_bestFitness;
			}

			for (int i = 0; i < sampleSize; i++) {
				List<String> lines4sample3 = Files.readAllLines(Paths.get(filePath3 + i + ".stat"));

				// get total time for seed
				double seed_TotalTime = 0.0;
				double seed_bestFitness = 0.0;

				for (int j = 0; j < interation; j++) {
					String[] pop = lines4sample3.get(j).split("\\s+");
					seed_TotalTime += Double.parseDouble(pop[1]) + Double.parseDouble(pop[2]);
					
					sample3_meanBestFitnessPerGen[i][j] = Double.parseDouble(pop[3]);

					// get best fitness so far for each seed
					if (j == interation - 1) {
						seed_bestFitness = Double.parseDouble(pop[3]);
					}
				}

				// put total time and bestFitness into sample array
				sample3_totalTime[i] = seed_TotalTime;
				sample3_bestFitnessSoFar[i] = seed_bestFitness;
			}

			for (int i = 0; i < sampleSize; i++) {
				List<String> lines4sample4 = Files.readAllLines(Paths.get(filePath4 + i + ".stat"));

				// get total time for seed
				double seed_TotalTime = 0.0;
				double seed_bestFitness = 0.0;

				for (int j = 0; j < interation4PSO; j++) {
					String[] pop = lines4sample4.get(j).split("\\s+");
					seed_TotalTime += Double.parseDouble(pop[1]) + Double.parseDouble(pop[2]);
					// get best fitness so far for each seed
					if (j == interation4PSO - 1) {
						seed_bestFitness = Double.parseDouble(pop[5]);
					}
				}

				// put total time and bestFitness into sample array
				sample4_totalTime[i] = seed_TotalTime;
				sample4_bestFitnessSoFar[i] = seed_bestFitness;
			}

			System.out.println("=======================EDA 0 ===============================");
			StatisticalSummary stat1_time = new DescriptiveStatistics(sample1_totalTime);
			System.out.println(new BigDecimal(stat1_time.getMean()).setScale(6, BigDecimal.ROUND_HALF_UP) + "$\\pm$"
					+ new BigDecimal(stat1_time.getStandardDeviation()).setScale(6, BigDecimal.ROUND_HALF_UP));

			StatisticalSummary stat1_fitness = new DescriptiveStatistics(sample1_bestFitnessSoFar);
			System.out.println(new BigDecimal(stat1_fitness.getMean()).setScale(6, BigDecimal.ROUND_HALF_UP) + "$\\pm$"
					+ new BigDecimal(stat1_fitness.getStandardDeviation()).setScale(6, BigDecimal.ROUND_HALF_UP));

			System.out.println("=======================EDA 1 ===============================");
			StatisticalSummary stat2_time = new DescriptiveStatistics(sample2_totalTime);
			System.out.println(new BigDecimal(stat2_time.getMean()).setScale(6, BigDecimal.ROUND_HALF_UP) + "$\\pm$"
					+ new BigDecimal(stat2_time.getStandardDeviation()).setScale(6, BigDecimal.ROUND_HALF_UP));

			StatisticalSummary stat2_fitness = new DescriptiveStatistics(sample2_bestFitnessSoFar);
			System.out.println(new BigDecimal(stat2_fitness.getMean()).setScale(6, BigDecimal.ROUND_HALF_UP) + "$\\pm$"
					+ new BigDecimal(stat2_fitness.getStandardDeviation()).setScale(6, BigDecimal.ROUND_HALF_UP));

			System.out.println("====P for time ==" + ttest.tTest(stat1_time, stat2_time));
			System.out.println("====P for fitness ==" + ttest.tTest(stat1_fitness, stat2_fitness));

			System.out.println("=======================EDA 2 ===============================");
			StatisticalSummary stat3_time = new DescriptiveStatistics(sample3_totalTime);
			System.out.println(new BigDecimal(stat3_time.getMean()).setScale(6, BigDecimal.ROUND_HALF_UP) + "$\\pm$"
					+ new BigDecimal(stat3_time.getStandardDeviation()).setScale(6, BigDecimal.ROUND_HALF_UP));

			StatisticalSummary stat3_fitness = new DescriptiveStatistics(sample3_bestFitnessSoFar);
			System.out.println(new BigDecimal(stat3_fitness.getMean()).setScale(6, BigDecimal.ROUND_HALF_UP) + "$\\pm$"
					+ new BigDecimal(stat3_fitness.getStandardDeviation()).setScale(6, BigDecimal.ROUND_HALF_UP));

			System.out.println("====P for time ==" + ttest.tTest(stat1_time, stat3_time));
			System.out.println("====P for fitness ==" + ttest.tTest(stat1_fitness, stat3_fitness));

			System.out.println("=======================PSO  ===============================");
			StatisticalSummary stat4_time = new DescriptiveStatistics(sample4_totalTime);
			System.out.println(new BigDecimal(stat4_time.getMean()).setScale(6, BigDecimal.ROUND_HALF_UP) + "$\\pm$"
					+ new BigDecimal(stat4_time.getStandardDeviation()).setScale(6, BigDecimal.ROUND_HALF_UP));

			StatisticalSummary stat4_fitness = new DescriptiveStatistics(sample4_bestFitnessSoFar);
			System.out.println(new BigDecimal(stat4_fitness.getMean()).setScale(6, BigDecimal.ROUND_HALF_UP) + "$\\pm$"
					+ new BigDecimal(stat4_fitness.getStandardDeviation()).setScale(6, BigDecimal.ROUND_HALF_UP));

			System.out.println("====P for time ==" + ttest.tTest(stat1_time, stat4_time));
			System.out.println("====P for fitness ==" + ttest.tTest(stat1_fitness, stat4_fitness));

			// Files.write(Paths.get(statPath), mergedlines);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		// draw function
//		DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
//		// for (String value : entropy4Gen) {
//		for (int i = 0; i < sampleSize; i++) {
//			double sum1Pergen = 0.00;
//			for (int j = 0; j < interation; j++) {
//				sum1Pergen += sample1_meanBestFitnessPerGen[j][i];
//			}
//			System.out.println(sum1Pergen / interation);
//			line_chart_dataset.addValue(sum1Pergen / interation, "EDA0", i + "");
//		}
//		
//		System.out.println("================");
//		
//		for (int i = 0; i < sampleSize; i++) {
//			double sum2Pergen = 0.00;
//			for (int j = 0; j < interation; j++) {
//				sum2Pergen += sample2_meanBestFitnessPerGen[j][i];
//			}
//			System.out.println(sum2Pergen / interation);
//			line_chart_dataset.addValue(sum2Pergen / interation, "EDA1", i + "");
//		}
//		
//		System.out.println("================");
//
//		
//		for (int i = 0; i < sampleSize; i++) {
//			double sum3Pergen = 0.00;
//			for (int j = 0; j < interation; j++) {
//				sum3Pergen += sample3_meanBestFitnessPerGen[j][i];
//			}
//			System.out.println(sum3Pergen / interation);
//			line_chart_dataset.addValue(sum3Pergen / interation, "EDA2", i + "");
//		}
//
//		JFreeChart lineChartObject = ChartFactory.createLineChart("Changes in Entropy, Fitness and Discount rate",
//				"Generation", "Value", line_chart_dataset, PlotOrientation.VERTICAL, true, true, false);
//
//		int width = 640; /* Width of the image */
//		int height = 480; /* Height of the image */
//		File lineChart = new File("EntropyChanges.jpeg");
//		try {
//			ChartUtilities.saveChartAsJPEG(lineChart, lineChartObject, width, height);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
