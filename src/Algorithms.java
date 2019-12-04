/**
 * @Purpose: The Algorithms class contains the two algorithms you have to implement  
 * Do NOT modify the names and signatures of the two algorithm methods
 * 
 * @author  RYK
 * @since   30/10/2019
 * extended by @author 
 *
 **/

import java.util.ArrayList;
import java.util.List;

public class Algorithms {


	private Sheet target_sheet;
	private Shelf target_shelf;

	/**
	 * This method is used to implement the next fit algorithm
	 * 
	 * @param shapes:a list of shapes representing customer requests(shapes to be
	 *            cut/placed)
	 * @return a list of the sheets used to fulfil all customer requests (i.e.
	 *         place all the shapes). e.g. if you pass a "shapes" list that has
	 *         two shapes {(w=200,h=200),(w=50,h=100)}, then the returned list
	 *         of sheets should show us all the information needed (e.g. that
	 *         one sheet is used, this sheet has one shelf and this shelf has
	 *         two shapes in it). In the test program, you can use the returned
	 *         list to retrieve the total number of sheets used.
	 **/

	public List<Sheet> nextFit(List<Shape> shapes) {

		/*
		 * Start with an empty list of sheets (remember each sheet has a width
		 * of 300 and a height of 250 as specified in the Sheet class)
		 */
		List<Sheet> usedSheets = new ArrayList<Sheet>();

		target_sheet = new Sheet();
		usedSheets.add(target_sheet);

		for (Shape shape : shapes) {
			// FIRST SHELF ON A SHEET
			if ( target_sheet.getShelves().size() == 0 ) {
				target_shelf = new Shelf();
				target_shelf.place(shape);
				// target_shelf.setHeight(shape);
				target_sheet.addShelf(target_shelf);
				continue;
			}

			boolean fitted = fitOnShelf(shape, target_shelf);

			if ( fitted )
				continue;

			// ROTATE SHAPE
			shape.rotate();

			// TRY FITTING ONTO THE SHELF AGAIN
			fitted = fitOnShelf(shape, target_shelf);

			if ( fitted )
				continue;
			
			// ROTATE SHAPE BACK TO ITS ORIGINAL ORIENTATION
			shape.rotate();

			fitted = newShelf(target_sheet, shape);

			if ( fitted )
				continue;

			// ROTATE SHAPE
			shape.rotate();

			fitted = newShelf(target_sheet, shape);

			if ( fitted )
				continue;

			// ROTATE SHAPE BACK TO ITS ORIGINAL ORIENTATION
			shape.rotate();

			target_sheet = newSheet();
			target_shelf = new Shelf();
			target_shelf.place(shape);
			target_sheet.addShelf(target_shelf);
			usedSheets.add(target_sheet);

			// try fitting on shelf
			// rotate
			// try fitting on shelf
			// rotate
			//NOT FITTING ON SHELF
			// make new shelf and place
			// rotate
			// make new shelf and place
			// CANNOT CREATE NEW SHELF ON THIS SHEET
			//new sheet

		}


		/*
		 * Add in your own code so that the method will place all the shapes
		 * according to NextFit under ALL the assumptions mentioned in the
		 * specification
		 */

		return usedSheets;
	}

	// TRY FITTING SHAPE ONTO SHELF. RETURN TRUE IF SUCCESSFULLY PLACED
	private boolean fitOnShelf(Shape shape, Shelf shelf) {
		int shelfHeight = shelf.getHeight();
		int shelfUsedWidth = shelf.getUsedWidth();

		int shapeHeight = shape.getHeight();
		int shapeWidth = shape.getWidth();

		if (shapeHeight <= shelfHeight) {
			if (shapeWidth <= 300 - shelfUsedWidth) {
				shelf.place(shape);
				return true;
			}
		}

		return false;
	}


	private Sheet newSheet() {
		return new Sheet();
	}

	// IF NEW SHELF HEIGHT IS LESS THAN SHEET MAX HEIGHT THEN A NEW SHELF IS CREATED
	private boolean newShelf(Sheet sheet, Shape shape) {
		int totalShelvesHeight = sheet.allShelvesHeight();
		int shapeHeight = shape.getHeight();

		if ( shapeHeight + totalShelvesHeight > 250)
			return false;
			
		target_shelf = new Shelf();
		target_shelf.place(shape);
		sheet.addShelf(target_shelf);
		return true;
	}






	/**
	 * This method is used to implement the first fit algorithm
	 * 
	 * @param shapes:a list of shapes representing customer requests (shapes to be
	 *            cut/placed)
	 * @return a list of the sheets used to fulfil all customer requests (i.e. place
	 *         all the shapes). e.g. if you pass a "shapes" list that has two
	 *         shapes {(w=200,h=200),(w=50,h=100)}, then the returned list of
	 *         sheets should show us all the information needed (e.g. that one
	 *         sheet is used, this sheet has one shelf and this shelf has two
	 *         shapes in it). In the test program, you can use the returned list
	 *         to retrieve the total number of sheets used
	 **/
	public List<Sheet> firstFit(List<Shape> shapes) {

		/*
		 * Start with an empty list of sheets (remember each sheet has a width
		 * of 300 and a height of 250 as specified in the Sheet class)
		 */
		List<Sheet> usedSheets = new ArrayList<Sheet>();

		/*
		 * Add in your own code so that the method will place all the shapes
		 * according to FirstFit under the assumptions mentioned in the spec
		 */
		
		return usedSheets;
	}

}
