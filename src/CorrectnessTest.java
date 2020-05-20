import java.util.ArrayList;
import java.util.List;
/**
 * @Purpose: The CorrectnessTest class is used to validate the correctness of
 *           the implemented algorithms. You can add additional methods if you
 *           need
 * 
 * @author RYK
 * @since 30/10/2019 extended by @author
 */

public class CorrectnessTest {

	public static void main(String[] args) {
		System.out.println("*********************************************");
		System.out.println("*********** Correctness testing *************");
		System.out.println("*********************************************");
		System.out.println();

		CorrectnessTest ct = new CorrectnessTest();
		ct.nf_same_shelf_test();
		ct.nf_same_shelf_rotate_test();
		ct.nf_new_sheet_test();
		ct.nf_new_shelf_test();
		ct.nf_new_sheet_shape_limit_test();

		ct.ff_same_shelf_test();
		ct.ff_same_shelf_rotate_test();
		ct.ff_new_shelf_test();
		ct.ff_new_sheet_test();
		ct.ff_new_sheet_shape_limit_test();
	}
	 //NEXT FIT TESTS
	private void nf_same_shelf_test() {
		List<Shape> shapes = new ArrayList<Shape>();
		Shape shapeA = new Shape(200, 50);
		Shape shapeB = new Shape(100, 30);
		shapes.add(shapeA);
		shapes.add(shapeB);

		Algorithms algo = new Algorithms();
		List<Sheet> sheets = algo.nextFit(shapes);

		int sheetNum = 0;
		System.out.format("SAME SHELF TEST %n");
		for (Sheet sheet : sheets) {
			sheetNum++;
			System.out.format("Sheet number: %d %n", sheetNum);
			sheet.displaySheet();
		}
	}

	private void nf_same_shelf_rotate_test() {
		List<Shape> shapes = new ArrayList<Shape>();
		Shape shapeA = new Shape(200, 50);
		Shape shapeB = new Shape(30, 100);
		shapes.add(shapeA);
		shapes.add(shapeB);

		Algorithms algo = new Algorithms();
		List<Sheet> sheets = algo.nextFit(shapes);

		int sheetNum = 0;
		System.out.format(" SAME SHELF ROTATE TEST  %n");
		for (Sheet sheet : sheets) {
			sheetNum++;
			System.out.format("Sheet number: %d%n", sheetNum);
			sheet.displaySheet();
		}
	}

	private void nf_new_shelf_test() {
		List<Shape> shapes = new ArrayList<Shape>();
		Shape shapeA = new Shape(50, 50);
		Shape shapeB = new Shape(200, 200);
		shapes.add(shapeA);
		shapes.add(shapeB);

		Algorithms algo = new Algorithms();
		List<Sheet> sheets = algo.nextFit(shapes);

		int sheetNum = 0;
		System.out.format("SECOND SHELF %n");
		for (Sheet sheet : sheets) {
			sheetNum++;
			System.out.format("Sheet number: %d  %n", sheetNum);
			sheet.displaySheet();
		}
	}

	private void nf_new_sheet_test() {
		List<Shape> shapes = new ArrayList<Shape>();
		Shape shapeA = new Shape(250, 250);
		Shape shapeB = new Shape(250, 250);
		shapes.add(shapeA);
		shapes.add(shapeB);

		Algorithms algo = new Algorithms();
		List<Sheet> sheets = algo.nextFit(shapes);

		int sheetNum = 0;
		System.out.format("NO SPACE FOR SECOND SHELF TEST %n");
		for (Sheet sheet : sheets) {
			sheetNum++;
			System.out.format("Sheet number: %d  %n", sheetNum);
			sheet.displaySheet();
		}
	}

	private void nf_new_sheet_shape_limit_test() {
		List<Shape> shapes = new ArrayList<Shape>();
		Shape shape = new Shape(50, 50);

		for (int i = 0; i < 21; i++) {
			shapes.add(shape);
		}

		Algorithms algo = new Algorithms();
		List<Sheet> sheets = algo.nextFit(shapes);

		int sheetNum = 0;
		System.out.format("SHAPE LIMIT TEST %n");
		for (Sheet sheet : sheets) {
			sheetNum++;
			System.out.format("Sheet number: %d %n", sheetNum);
			sheet.displaySheet();
		}
	}

	 //FIRST FIT TESTS
	private void ff_same_shelf_test() {
		List<Shape> shapes = new ArrayList<Shape>();
		Shape shapeA = new Shape(200, 30);
		Shape shapeB = new Shape(100, 10);
		shapes.add(shapeA);
		shapes.add(shapeB);

		Algorithms algo = new Algorithms();
		List<Sheet> sheets = algo.firstFit(shapes);

		int sheetNum = 0;
		System.out.format("SAME SHELF TEST  %n");
		for (Sheet sheet : sheets) {
			sheetNum++;
			System.out.format("Sheet number: %d  %n", sheetNum);
			sheet.displaySheet();
		}
	}

	private void ff_same_shelf_rotate_test() {
		List<Shape> shapes = new ArrayList<Shape>();
		Shape shapeA = new Shape(200, 30);
		Shape shapeB = new Shape(10, 100);
		shapes.add(shapeA);
		shapes.add(shapeB);

		Algorithms algo = new Algorithms();
		List<Sheet> sheets = algo.firstFit(shapes);

		int sheetNum = 0;
		System.out.format("SAME SHELF ROTATE TEST  %n");
		for (Sheet sheet : sheets) {
			sheetNum++;
			System.out.format("Sheet number: %d  %n", sheetNum);
			sheet.displaySheet();
		}
	}

	private void ff_new_shelf_test() {
		List<Shape> shapes = new ArrayList<Shape>();
		Shape shapeA = new Shape(200, 30);
		Shape shapeB = new Shape(50, 50);
		shapes.add(shapeA);
		shapes.add(shapeB);

		Algorithms algo = new Algorithms();
		List<Sheet> sheets = algo.firstFit(shapes);

		int sheetNum = 0;
		System.out.format("SECOND SHELF  %n");
		for (Sheet sheet : sheets) {
			sheetNum++;
			System.out.format("Sheet number: %d  %n", sheetNum);
			sheet.displaySheet();
		}
	}

	private void ff_new_sheet_test() {
		List<Shape> shapes = new ArrayList<Shape>();
		Shape shapeA = new Shape(200, 30);
		Shape shapeB = new Shape(300, 250);
		shapes.add(shapeA);
		shapes.add(shapeB);

		Algorithms algo = new Algorithms();
		List<Sheet> sheets = algo.firstFit(shapes);

		int sheetNum = 0;
		System.out.format("NO SPACE FOR SECOND SHELF TEST  %n");
		for (Sheet sheet : sheets) {
			sheetNum++;
			System.out.format("Sheet number: %d  %n", sheetNum);
			sheet.displaySheet();
		}
	}

	private void ff_new_sheet_shape_limit_test() {
		List<Shape> shapes = new ArrayList<Shape>();
		Shape shape = new Shape(50, 50);

		for (int i = 0; i < 21; i++) {
			shapes.add(shape);
		}

		Algorithms algo = new Algorithms();
		List<Sheet> sheets = algo.firstFit(shapes);

		int sheetNum = 0;
		System.out.format("SHAPE LIMIT TEST  %n");
		for (Sheet sheet : sheets) {
			sheetNum++;
			System.out.format("Sheet number: %d  %n", sheetNum);
			sheet.displaySheet();
		}
	}
}
