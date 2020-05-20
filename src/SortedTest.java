import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Purpose: The SortedTest class is used to compare the implemented algorithms
 *           in term of the number of sheets used WHEN the list of shapes is
 *           SORTED
 *
 *           You can add additional methods if you need to in this class
 * 
 * @author RYK
 * @since 30/10/2019 extended by @author
 */

public class SortedTest {
	public static void main(String[] args) {
		System.out.println("*********************************************");
		System.out.println("**************** Sorted Test ****************");
		System.out.println("*********************************************");
		System.out.println();

		/*
		 * Generate a random shape list and then check the number of sheets used
		 * when
		 ** this shape list is passed un-sorted
		 ** the list is sorted based on different criteria as set in the speciication document.
		 * 
		 * run several tests for different sizes of the "list of shapes" 
		 */

		/*
		 * HINT: you might want to implements the comparTo method in the Shape
		 * class or implement the Comparator Interface to do the sorting
		 */

		SortedTest st = new SortedTest();
		// total number of tests - you need to CHANGE this value
		int noOfTests = 1;

		// number of repetitions for each test - you need to CHANGE this value
		int noOfRep = 5;

		// number of shapes needed for the first run - you need to CHANGE this
		// value
		int noOfShapes = 10000;

		// the increment in the number of shapes - you need to CHANGE this value
		int increment = 10000;
		Comparator<Shape> byHeight = Comparator.comparing(Shape::getHeight);
		Comparator<Shape> byWidth = Comparator.comparing(Shape::getWidth);
		for (int i = 0; i < noOfTests; i++) {
			System.out.println("//////// TEST /////////");
			List<List<Shape>> shapesList = st.generateList(noOfShapes, noOfRep);
			System.out.println("Unsorted:"); 	// UNSORTED
			st.nextFit(shapesList);				// RUN NEXT FIT
			st.firstFit(shapesList);			// RUN FIRST FIT

			System.out.println();

			System.out.println("Sorted by area in ascending order:"); // SHAPES LIST IS ORDERED IN ASCENDING ORDER
			for (List<Shape> list : shapesList) {
				Collections.sort(list);	// SORT BY AREA ASCENDING
			}
			st.nextFit(shapesList);		// RUN NEXT FIT
			st.firstFit(shapesList);		// RUN FIRST FIT

			System.out.println();

			for (List<Shape> list : shapesList) {
				Collections.reverse(list); // SORT BY AREA DESCENDING
			}
			System.out.println("Sorted by area in descending order:"); // SHAPES LIST IS ORDERED IN ASCENDING ORDER
			st.nextFit(shapesList);		// RUN NEXT FIT
			st.firstFit(shapesList);		// RUN FIRST FIT

			System.out.println();

			for (List<Shape> list : shapesList) {
				Collections.sort(list, byHeight); // SORT BY HEIGHT ASCENDING
			}
			System.out.println("Sorted by height in ascending order:"); // SHAPES LIST IS ORDERED IN ASCENDING ORDER
			st.nextFit(shapesList);		// RUN NEXT FIT
			st.firstFit(shapesList);		// RUN FIRST FIT

			System.out.println();

			for (List<Shape> list : shapesList) {
				Collections.reverse(list); // SORT BY HEIGHT DESCENDING
			}
			System.out.println("Sorted by height in descending order:"); // SHAPES LIST IS ORDERED IN DESCENDING ORDER
			st.nextFit(shapesList);		// RUN NEXT FIT
			st.firstFit(shapesList);		// RUN FIRST FIT

			System.out.println();

			for (List<Shape> list : shapesList) {
				Collections.sort(list, byWidth); // SORT BY WIDTH ASCENDING
			}
			System.out.println("Sorted by width in ascending order:"); // SHAPES LIST IS ORDERED IN ASCENDING ORDER
			st.nextFit(shapesList);		// RUN NEXT FIT
			st.firstFit(shapesList);		// RUN FIRST FIT

			System.out.println();

			for (List<Shape> list : shapesList) {
				Collections.reverse(list); // SORT BY WIDTH DESCENDING
			}
			System.out.println("Sorted by width in descending order:"); // SHAPES LIST IS ORDERED IN DESCENDING ORDER
			st.nextFit(shapesList);		// RUN NEXT FIT
			st.firstFit(shapesList);		// RUN FIRST FIT
			System.out.println("//////// END TEST /////////");
			System.out.println();

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
		int totalSheets = 0;
		for (List<Shape> shapes : shapesList) {
			Algorithms algo = new Algorithms();
			List<Sheet> sheets = algo.nextFit(shapes);
			totalSheets += sheets.size();
		}
		System.out.format("NextFit was carried out %d times with %d shapes%n", shapesList.size(), shapesList.get(0).size());
		displayPerformance(totalSheets/shapesList.size());
	}

	private void firstFit(List<List<Shape>> shapesList) {
		int totalSheets = 0;
		for (List<Shape> shapes : shapesList) {
			Algorithms algo = new Algorithms();
			List<Sheet> sheets = algo.firstFit(shapes);
			totalSheets += sheets.size();
		}
		System.out.format("FirstFit was carried out %d times with %d shapes%n", shapesList.size(), shapesList.get(0).size());
		displayPerformance(totalSheets/shapesList.size());
	}

	private void displayPerformance(int meanSheets) {
		System.out.format("%d Sheets%n", meanSheets);
	}

	private List<Shape> generateShapes(int numberOfShapes){
		Generator generator = new Generator();
		return generator.generateShapeList(numberOfShapes);
	}
}
