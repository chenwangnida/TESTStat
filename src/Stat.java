import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.StatisticalSummary;

public class Stat {
	public static void main(String[] args) {

		// path settings
		String filePath1 = "/Users/chenwang/Documents/Experiment Results/EDA_original_results4/";
		String filePath2 = "/Users/chenwang/Documents/Experiment Results/EDA_Linear__results4/";
		// String statPath = "/Users/chenwang/Documents/Experiment
		// Results/results1/merged1and2.txt";

		double[] sample1_totalTime;
		double[] sample2_totalTime;

		double[] sample1_bestFitnessSoFar;
		double[] sample2_bestFitnessSoFar;

		int sampleSize = 30;
		int interationTimes = 5;

		try {

			sample1_totalTime = new double[sampleSize];
			sample2_totalTime = new double[sampleSize];

			sample1_bestFitnessSoFar = new double[sampleSize];
			sample2_bestFitnessSoFar = new double[sampleSize];

			List<String> mergedlines = new ArrayList<String>();

			for (int i = 0; i < sampleSize; i++) {
				List<String> lines4sample1 = Files.readAllLines(Paths.get(filePath1 + i + ".stat"));

				// get total time for seed
				double seed_TotalTime = 0.0;
				double seed_bestFitness = 0.0;

				for (int j = 0; j < interationTimes; j++) {
					String[] pop = lines4sample1.get(j).split("\\s+");
					seed_TotalTime += Double.parseDouble(pop[1]) + Double.parseDouble(pop[2]);
					// get best fitness so far for each seed
					if (j == interationTimes - 1) {
						seed_bestFitness = Double.parseDouble(pop[3]);
					}
				}

				// put total time and bestFitness into sample array
				sample1_totalTime[i] = seed_TotalTime;
				sample1_bestFitnessSoFar[i] = seed_bestFitness;
			}

			StatisticalSummary stat1_time = new DescriptiveStatistics(sample1_totalTime);

			System.out.println(stat1_time.getMean() + "+/-" + stat1_time.getStandardDeviation());

			StatisticalSummary stat1_fitness = new DescriptiveStatistics(sample1_bestFitnessSoFar);
			System.out.println(stat1_fitness.getMean() + "+/-" + stat1_fitness.getStandardDeviation());

			// for (int i = 0; i < 30; i++) {
			// List<String> lines1 = Files.readAllLines(Paths.get(filePath1 + i + ".stat"));
			// List<String> filteredlines1 = new ArrayList<String>();
			// for (int j = 0; j < 30; j++) {
			// filteredlines1.add(lines1.get(j));
			// }
			//
			// String Str1 = " " + i + " " + "1";
			// filteredlines1.forEach(filteredline1 ->
			// mergedlines.add(filteredline1.concat(Str1)));
			// }
			//
			// for (int m = 0; m < 30; m++) {
			// List<String> lines2 = Files.readAllLines(Paths.get(filePath2 + m + ".stat"));
			// List<String> filteredlines2 = new ArrayList<String>();
			// for (int j = 0; j < 30; j++) {
			// filteredlines2.add(lines2.get(j));
			// }
			//
			// String Str2 = " " + m + " " + "2";
			// filteredlines2.forEach(filteredline2 ->
			// mergedlines.add(filteredline2.concat(Str2)));
			// }

			// Files.write(Paths.get(statPath), mergedlines);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
