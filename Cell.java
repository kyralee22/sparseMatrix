// written by kyra lee 2019
public class Cell
{
   private int row;
   private int col;
   private String value;
   
   public Cell(int ro, int co, String val)
   {
      row = ro;
      col = co;
      value = val;
   }
   public int getRow()
   {
      return row;
   }
   public int getCol()
   {
      return col;
   }
   public String getValue()
   {
      return value;
   }
   public String toString()
   {
      return "The value at row: " + row + ", col: " + col + " is " + value; 
   }
}

