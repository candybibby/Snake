package package1;

/**
 *  @author French People
 *  Class Obstacle
 *  Models obstacle and manages their creation in a valid space.
 */
public class Obstacle {


			//ATTRIBUTES

	/** Synchronizer of the class*/
	private Synchronizer synchronizer;

	/** Level for the obstacle*/
	private int level;


	/** Constant : easy level */
	 public final static int EASY = 0; 

	 /** Constant : medium level */
	 public final static int MEDIUM = 1;

	 /** Constant : hard level */
	 public final static int HARD = 2;

	 /** Constant : expert level */
	 public final static int EXPERT = 3;


			//CONSTRUCTORS

	/**
	 * Empty constructor
	 * Initialize the obstacles on the area
	 * As an easy level
	 */
	public Obstacle(Synchronizer synchronizer){
		this(EASY, synchronizer);
	}


	/**
	 * Constructor with 1 param
	 * Initialize the obstacles according to the level
	 * @param lvl , difficulty of the game
	 */
	public Obstacle(int lvl, Synchronizer sync){
		this.synchronizer = sync;

		//Check if the level is valid 
		if (  (lvl<EASY)  ||  (lvl>EXPERT) )
			lvl = EASY;
		this.level = lvl;
		//Add the "wall" around the area of the game
		this.initializeArea();

		//And the obstacles inside the area according to the level
		switch (this.level) {

			case MEDIUM :
				this.levelMedium();
				break;

			case HARD :
				this.levelHard();
				break;

			case EXPERT :
				this.levelExpert();
				break;
				
			default :
				break;

		}
	}



			//METHODS

	/**
	 * Method initializeArea
	 * create the wall around the area
	 */
	public void initializeArea(){
		// level easy contains only the area
		int length1 = this.synchronizer.getGameAreaHeight();		// length of the first dimension
		for (int i = 0; i<length1; i++){
			int length2 = this.synchronizer.getGameAreaWidth();		// length of the second dimension
			for (int j = 0; j<length2 ; j++){
				this.synchronizer.writeThisCell(i,0, Symbol.WALL);
				this.synchronizer.writeThisCell(0,j, Symbol.WALL);
				this.synchronizer.writeThisCell(length1-1,j, Symbol.WALL);
				this.synchronizer.writeThisCell(i,length2-1, Symbol.WALL);

			}
		}
	}


	/**
	 * Method levelMedium
	 * Used to create the obstacles in the area
	 * for a medium level
	 * There is just one obstacle
	 */
	public void levelMedium(){
		// we are going to build one obstacle
		// just an horizontal wall 
		
		int wallLenght = synchronizer.getGameAreaWidth()/4;
		// this wall will start at the coordinates (x, y) :
		int x = (int)(Math.random()*(this.synchronizer.getGameAreaWidth() - wallLenght - 2));
		int y = (int)(Math.random()*this.synchronizer.getGameAreaHeight() - 2);

		while ( (x < 2) || (y < 2) ){
			x = (int)(Math.random()*(this.synchronizer.getGameAreaWidth() - wallLenght - 2) );
			y = (int)(Math.random()*(this.synchronizer.getGameAreaHeight()  - 2));
		}

		
		// while the obstacle is different to (1/6) of the larger
		// and while x is not out of bounds
		
		for (int i = 0; i<wallLenght; i++) 
			this.synchronizer.writeThisCell(x+i, y, Symbol.WALL);
		
		

	}

	/**
	 * Method levelHard
	 * Used to create the obstacles in the area
	 * for a hard level
	 * There are 3 different obstacles
	 */
	public void levelHard(){
		// two obstacle horizontal and one vertical
		// we have to define 2 variables for the start of each obstacle
		// to check if the coordinates are different at the beginning
		
		int wallLenght = this.synchronizer.getGameAreaWidth()/4;

		int x1 = (int)(Math.random()*(this.synchronizer.getGameAreaWidth() - wallLenght - 2));
		int y1 = (int)(Math.random()*this.synchronizer.getGameAreaHeight() - 2);

		while ( (x1 < 2) || (y1 < 2) ){
			x1 = (int)(Math.random()*(this.synchronizer.getGameAreaWidth() - wallLenght - 2) );
			y1 = (int)(Math.random()*(this.synchronizer.getGameAreaHeight()  - 2));
		}

		
		int x2 = (int)(Math.random()*(this.synchronizer.getGameAreaWidth() - wallLenght - 2));
		int y2 = (int)(Math.random()*this.synchronizer.getGameAreaHeight() - 2);
		// the 2 horizontal obstacles musn't be placed on the same coordinates
		while ( (x2== x1) || (x2 < 2 ) )
			x2 = (int)(Math.random()*(this.synchronizer.getGameAreaWidth() - wallLenght) - 2);
		while ( (y2==y1) || (y2 < 2 ) )
			y2 = (int)(Math.random()*this.synchronizer.getGameAreaHeight() - 2);

		int x3 = (int)(Math.random()*this.synchronizer.getGameAreaWidth());
		int y3 = (int)(Math.random()* (this.synchronizer.getGameAreaHeight() - wallLenght) - 2);
		// the vertical obstacle should be in another way than the horizontal obstacles
		while ( (y3==y1) || (y3 == y2) || (y3 < 2) )
			y3 = (int)(Math.random()*(this.synchronizer.getGameAreaHeight() - wallLenght) - 2);
		while ( (x3==x1) || (x3 == x2) || (x3 < 2))
			x3 = (int)(Math.random()*this.synchronizer.getGameAreaWidth() - 2);


		//
		for (int i = 0; i<wallLenght; i++) 
			this.synchronizer.writeThisCell(x1+i, y1, Symbol.WALL);

		//
		for (int i = 0; i<wallLenght; i++) 
			this.synchronizer.writeThisCell(x2+i, y2, Symbol.WALL);

		wallLenght = this.synchronizer.getGameAreaHeight()/4;
		
		//Vertical
		for (int i = 0; i<wallLenght; i++) 
			this.synchronizer.writeThisCell(x3, y3+i, Symbol.WALL);
		

	}


	/**
	 * Method levelExpert
	 * Used to create the obstacles in the area
	 * for an expert level
	 * There are 5 different obstacles !
	 */
	public void levelExpert(){

		// three obstacles horizontal and two vertical
			// we have to define 2 variables for the start of each obstacle
			// to check if the coordinates are different at the beginning

			int wallLenght = this.synchronizer.getGameAreaWidth()/4;
		
			int x1 = (int)(Math.random()*(this.synchronizer.getGameAreaWidth() - wallLenght - 2));
			int y1 = (int)(Math.random()*this.synchronizer.getGameAreaHeight() - 2);

			while ( (x1 < 2) || (y1 < 2) ){
				x1 = (int)(Math.random()*(this.synchronizer.getGameAreaWidth() - wallLenght - 2) );
				y1 = (int)(Math.random()*(this.synchronizer.getGameAreaHeight()  - 2));
			}
			
			int x2 = (int)(Math.random()*(this.synchronizer.getGameAreaWidth() - wallLenght - 2));
			int y2 = (int)(Math.random()*this.synchronizer.getGameAreaHeight() - 2);

			int x3 = (int)(Math.random()*(this.synchronizer.getGameAreaWidth() - wallLenght - 2));
			int y3 = (int)(Math.random()*this.synchronizer.getGameAreaHeight() - 2);

			//The 3 horizontal obstacles musn't be placed on the same coordinates
			while ((x2 == x1) || (x3 == x1) || (x3 == x2) || (x2 < 2) || (x3 < 2)){
				x2 = (int)(Math.random()*(this.synchronizer.getGameAreaWidth() - wallLenght - 2));
				x3 = (int)(Math.random()*this.synchronizer.getGameAreaWidth() - 2);
			}
			while ((y2 == y1) || (y3 == y1) || (y3 == y2) || (y2 < 2) || (y3 < 2) ){
				y2 = (int)(Math.random()*(this.synchronizer.getGameAreaWidth() - wallLenght - 2));
				y3 = (int)(Math.random()*this.synchronizer.getGameAreaHeight() - 2);
			}
			
			wallLenght = this.synchronizer.getGameAreaHeight()/4;

			int x4 = (int)(Math.random()*(this.synchronizer.getGameAreaWidth() - 2) );
			int y4 = (int)(Math.random()*(this.synchronizer.getGameAreaHeight() - wallLenght - 2));
			
			while ( (x4 < 2) || (y4 < 2) ){
				x4 = (int)(Math.random()*(this.synchronizer.getGameAreaWidth() - 2) );
				y4 = (int)(Math.random()*(this.synchronizer.getGameAreaHeight() - wallLenght - 2));
			}

			int x5 = (int)(Math.random()*(this.synchronizer.getGameAreaWidth() - 2));
			int y5 = (int)(Math.random()*(this.synchronizer.getGameAreaHeight() - wallLenght - 2));

			
			int x6 = (int)(Math.random()*(this.synchronizer.getGameAreaWidth() - 2));
			int y6 = (int)(Math.random()*(this.synchronizer.getGameAreaHeight() - wallLenght - 2));

			//The 3 vertical obstacles musn't be placed on the same coordinates
			while ((y4 == y5) || (y4 == y6) || (y5 == y6) || (y5 < 2) || (y6 < 2) ){
				y5 = (int)(Math.random()*(this.synchronizer.getGameAreaHeight() - wallLenght - 2));
				y6 = (int)(Math.random()*(this.synchronizer.getGameAreaHeight() - wallLenght - 2));
			}
			while ((x4 == x5) || (x4 == x6) || (x5 == x6) || (x5 < 2) || (x6 < 2)){
				x5 = (int)(Math.random()*(this.synchronizer.getGameAreaWidth() - 2));
				x6 = (int)(Math.random()*(this.synchronizer.getGameAreaWidth() - 2));
			}


			//
			for (int i = 0; i<wallLenght; i++) 
				this.synchronizer.writeThisCell(x1+i, y1, Symbol.WALL);

			//
			for (int i = 0; i<wallLenght; i++) 
				this.synchronizer.writeThisCell(x2+i, y2, Symbol.WALL);
			
			//
			for (int i = 0; i<wallLenght; i++) 
				this.synchronizer.writeThisCell(x3+i, y3, Symbol.WALL);

			wallLenght = this.synchronizer.getGameAreaHeight()/5;
			
			//Vertical
			for (int i = 0; i<wallLenght; i++) 
				this.synchronizer.writeThisCell(x4, y4+i, Symbol.WALL);
			
			//Vertical
			for (int i = 0; i<wallLenght; i++) 
				this.synchronizer.writeThisCell(x5, y5+i, Symbol.WALL);
			
			for (int i = 0; i<wallLenght; i++) 
				this.synchronizer.writeThisCell(x6, y6+i, Symbol.WALL);

	}



	/**
	 * Getter of level
	 * @return int level
	 */
	public int getLevel(){
		return this.level;
	}

	/**
	 * Setter of level
	 * @param int lvl, new level
	 */
	public void setLevel(int lvl){
		if (lvl>=0 && lvl<= 3)
			this.level = lvl;
	}
	
	public Synchronizer getSynchronizer(){
		return this.synchronizer;
	}


}