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
	private int numberOfShapes = 0;

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

		for (Shape shape : shapes) {
			if (usedSheets.size() == 0) {					// WHEN NO SHEETS HAVE BEEN CREATED
				target_sheet = newSheet(shape);
				usedSheets.add(target_sheet);
				continue;
			}

			if (numberOfShapes >= 20) {
				target_sheet = newSheet(shape);
				usedSheets.add(target_sheet);
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

			target_sheet = newSheet(shape);
			usedSheets.add(target_sheet);
		}

		return usedSheets;
	}

	private Sheet newSheet(Shape shape) {
		target_sheet = new Sheet();					// CREATE NEW SHEET
		target_shelf = new Shelf();					// CREATE NEW SHELF
		target_shelf.place(shape);					// PLACE SHAPE
		target_sheet.addShelf(target_shelf);		// ADD SHELF TO SHEET
		numberOfShapes = 1;

		return target_sheet;
	}

	// TRY FITTING SHAPE ONTO SHELF. RETURN TRUE IF SUCCESSFULLY PLACED
	private boolean fitOnShelf(Shape shape, Shelf shelf) {
		int shelfHeight = shelf.getHeight();								// GET THE HEIGHT OF THE SHELF
		int shelfUsedWidth = shelf.getUsedWidth();							// GET THE USED WIDTH OF THE SHELF

		int shapeHeight = shape.getHeight();								// GET THE SHAPE HEIGHT
		int shapeWidth = shape.getWidth();									// GET THE WIDTH 0F THE SHAPE

		if (shapeHeight <= shelfHeight) {									// CHECK THAT THE SHAPE HEIGHT IS <= TO THE SHELF HEIGHT
			if (shapeWidth <= Sheet.SHEET_WIDTH - shelfUsedWidth) {			// CHECK THAT THE REMAINING SPACE IN THE SHELF IS >= THAN THE SHAPE WIDTH
				shelf.place(shape);											// PLACE SHAPE ONTO SHELF
				numberOfShapes++;
				return true;												// RETURN TRUE ONCE THE SHAPE HAS BEEN PLACED
			}
		}

		return false;														// SHAPE WASN'T FITTED
	}

	// IF NEW SHELF HEIGHT IS LESS THAN SHEET MAX HEIGHT THEN A NEW SHELF IS CREATED
	private boolean newShelf(Sheet sheet, Shape shape) {
		int totalShelvesHeight = sheet.allShelvesHeight();			// GET THE HEIGHT OF ALL THE SHELVES IN THE SHEET
		int shapeHeight = shape.getHeight();						// GET THE HEIGHT OF THE SHAPE

		if ( shapeHeight + totalShelvesHeight > Sheet.SHEET_HEIGHT )	// CHECK THAT THERE IS SPACE FOR THIS SHAPE
			return false;												// RETURN FALSE WHEN THE SHAPE CANNOT FIT ONTO THIS SHEET
			
		target_shelf = new Shelf();										// MAKE A NEW SHELF
		target_shelf.place(shape);										// PLACE THE SHAPE ONTO THE SHELF
		sheet.addShelf(target_shelf);									// ADD THE SHELF TO THE SHEET
		numberOfShapes++;

		return true;													// RETURN TRUE WHEN THE SHAPE HAS BEEN FITTED
	}

	private int shapeLimit(Sheet sheet) {
		int numberOfShapes = 0;
		for (Shelf shelf: sheet.getShelves()) {		// FOREACH OF THE SHELVES IN A SHEET TRY TO FIT THE SHAPE
			numberOfShapes += shelf.getShapes().size();
		}
		return numberOfShapes;
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

		for (Shape shape : shapes) {
			if (usedSheets.size() == 0) {					// WHEN NO SHEETS HAVE BEEN CREATED
				target_sheet = newSheet(shape);
				usedSheets.add(target_sheet);
				continue;
			}

			boolean fitted = false;							// HAS THE SHAPE BEEN FITTED
			for (Sheet sheet : usedSheets) {				// FOREACH SHEET TRY TO FIT THE SHAPE ONTO EACH OF THE SHELVES
				if (shapeLimit(sheet) >= 20)				// MAX NUMBER OF SHAPES ON A SHEET
					continue;

				for (Shelf shelf: sheet.getShelves()) {		// FOREACH OF THE SHELVES IN A SHEET TRY TO FIT THE SHAPE
					fitted = fitOnShelf(shape, shelf);

					if ( fitted )
						break;								// BREAK OUT OF THE SHELF LOOP AS SHAPE HAS BEEN FITTED
		
					// ROTATE SHAPE
					shape.rotate();
		
					// TRY FITTING ONTO THE SHELF AGAIN
					fitted = fitOnShelf(shape, shelf);
		
					if ( fitted )
						break;								// BREAK OUT OF THE SHELF LOOP AS SHAPE HAS BEEN FITTED

					
					// ROTATE SHAPE BACK TO ITS ORIGINAL ORIENTATION
					shape.rotate();
				}

				if ( fitted )
					break;									// BREAK OUT OF THE SHEET LOOP AS SHAPE HAS BEEN FITTED

				fitted = newShelf(sheet, shape);
				if ( fitted )
					break;									// BREAK OUT OF THE SHEET LOOP AS SHAPE HAS BEEN FITTED IN A NEW SHELF

				// ROTATE SHAPE
				shape.rotate();

				fitted = newShelf(sheet, shape);
				
				if ( fitted )
					break;

				// ROTATE SHAPE BACK TO ITS ORIGINAL ORIENTATION
				shape.rotate();
			}

			if ( fitted )
				continue;									// CONTINUE TO THE NEXT SHAPE AS THE SHAPE HAS BEEN FITTED

			target_sheet = newSheet(shape);
			usedSheets.add(target_sheet);
		}

		/*
		 * Add in your own code so that the method will place all the shapes
		 * according to FirstFit under the assumptions mentioned in the spec
		 */
		
		return usedSheets;
	}

}
