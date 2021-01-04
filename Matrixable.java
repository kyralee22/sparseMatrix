// written by kyra lee 2019

public interface Matrixable<anyType>
{
   public anyType get(int r, int c);				//returns the element at row r, col c
   public anyType set(int r, int c, anyType x);	//changes element at (r,c), returns old value
   public void add(int r, int c, anyType x);	   //adds obj at row r, col c
   public anyType remove(int r, int c);
   public int size();			//returns # actual elements stored
   public int numRows();		//returns # rows set in constructor
   public int numColumns();	//returns # cols set in constructor
}


