import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * @Purpose: The PerformanceTest class is used to compare the implemented
 *           algorithms in term of time and the number of sheets used
 *
 *           You can add additional methods if you need to in this class
 * 
 * @author RYK
 * @since 30/10/2019 extended by @author
 */

public class PerformanceTest {

	public static void main(String[] args) {

		System.out.println("***********************************************");
		System.out.println("*********** Performance analysis **************");
		System.out.println("**********************************************");
		/*
		 * You must complete the Generator class in order to generate a random
		 * test values. You must complete the Algorithms class in order to call
		 * the two algorithms.
		 * 
		 * You can use any additional method you created in this class
		 */

		/**
		 * Remember: You need to calculate the time and number of sheets used
		 * for each run of each algorithm.
		 * 
		 * You are expected to run several tests (e.g. noOfTests=5) of your
		 * programs for, 10000, 20000, 30000, 40000, 50000 shapes
		 * (noOfShapes=10000, increment=10000) so that one can see how the
		 * algorithms perform for large datasets.
		 * 
		 * You are expected to run the same test a number of times to ensure
		 * accurate result (noOfRep=4). In this case, you need to calculate the
		 * average time and sheets needed for each run
		 **/

		// total number of tests - you need to CHANGE this value
		int noOfTests = 5;

		// number of repetitions for each test - you need to CHANGE this value
		int noOfRep = 5;

		// number of shapes needed for the first run - you need to CHANGE this
		// value
		int noOfShapes = 10000;

		// the increment in the number of shapes - you need to CHANGE this value
		int increment = 10000;

		PerformanceTest p = new PerformanceTest();

		for (int i = 0; i < noOfTests; i++) {
			List<List<Shape>> shapesList = p.generateList(noOfShapes, noOfRep);
			p.nextFit(shapesList);
			p.firstFit(shapesList);
			noOfShapes += increment;
		}
	}

	private List<List<Shape>> generateList(int numberOfShapes, int noOfRep) {
		List<List<Shape>> listOfLists = new ArrayList<List<Shape>>();
		for (int j = 0; j < noOfRep; j++) {
			listOfLists.add(generateShapes(numberOfShapes));
		}

		return listOfLists;
	}

	private void nextFit(List<List<Shape>> shapesList) {
		long totalTime = 0;
		int totalSheets = 0;
		for (List<Shape> shapes : shapesList) {
			Algorithms algo = new Algorithms();
			long start = System.nanoTime();
			List<Sheet> sheets = algo.nextFit(shapes);
			long end = System.nanoTime();
			totalTime 	+= end - start;
			totalSheets += sheets.size();
		}
		System.out.format("NextFit was carried out %d times with %d shapes%n", shapesList.size(), shapesList.get(0).size());
		displayPerformance(totalSheets/shapesList.size(), totalTime/shapesList.size());
	}

	private void firstFit(List<List<Shape>> shapesList) {
		long totalTime = 0;
		int totalSheets = 0;
		for (List<Shape> shapes : shapesList) {
			Algorithms algo = new Algorithms();
			long start = System.nanoTime();
			List<Sheet> sheets = algo.firstFit(shapes);
			long end = System.nanoTime();
			totalTime 	+= end - start;
			totalSheets += sheets.size();
		}
		System.out.format("FirstFit was carried out %d times with %d shapes%n", shapesList.size(), shapesList.get(0).size());
		displayPerformance(totalSheets/shapesList.size(), totalTime/shapesList.size());
	}

	private void displayPerformance(int meanSheets, long meanTime) {
		System.out.format("%d Sheets%n%d ms%n", meanSheets, meanTime);
	}

	private List<Shape> generateShapes(int numberOfShapes){
		Generator generator = new Generator();
		return generator.generateShapeList(numberOfShapes);
	}
}
