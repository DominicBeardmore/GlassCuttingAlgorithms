import java.util.List;

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

		System.out.println();

		// PerformanceTest p = new PerformanceTest();
		// p.test();


		Algorithms algo = new Algorithms();
		Generator generator = new Generator();
		List<Shape> shapes = generator.generateShapeList(10);
		List<Sheet> sheets = algo.nextFit(shapes);

		int sheetNum = 0;
		for (Sheet sheet : sheets) {
			sheetNum++;
			System.out.format("Sheet number: %d%n", sheetNum);
			sheet.displaySheet();
		}


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
		int noOfTests = 0;

		// number of repetitions for each test - you need to CHANGE this value
		int noOfRep = 0;

		// number of shapes needed for the first run - you need to CHANGE this
		// value
		int noOfShapes = 0;

		// the increment in the number of shapes - you need to CHANGE this value
		int increment = 0;

	}

	private List<Shape> generateShapes(){
		Generator generator = new Generator();
		return generator.generateShapeList(10);
	}

	// public void test() {
	// 	Generator generator = new Generator();
	// 	List<Shape> shapes = generator.generateShapeList(10);
	// 	Sheet sheet = new Sheet();
	// 	Shelf shelf = new Shelf();
	// 	for (Shape shape : shapes) {
	// 		shelf.place(shape);
	// 	}

	// 	sheet.addShelf(shelf);
	// 	sheet.displaySheet();
	// }
}
