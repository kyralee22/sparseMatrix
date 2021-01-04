import java.io.*;
import java.util.*;
import java.io.*;

// written by kyra lee 2019

public class SparseMatrix <anyType> implements Matrixable <String>
{
   private ArrayList<Cell> list; // contains value, row, col
   private int numElements;
   private int numRows;
   private int numCols;
   private int key;
   
   public SparseMatrix(int r, int c)
   {
      list = new ArrayList();
      numElements = 0;
      numRows = r;
      numCols = c;
   }
   
   private int getKey(int r, int c)
   {
      key = (r * numCols) + c;
      return key;
   }
   
   public String toString()
   {
      int rows = numRows() + 1;
      int cols = numColumns() + 1;
      String[][] output = new String[rows][cols];
      for(int i = 0; i < list.size(); i++)
      {
         int r = (list.get(i)).getRow();
         int c = (list.get(i)).getCol();
         String value = (list.get(i)).getValue();
         output[r][c] = value;
      }
      for(int r = 0; r < output.length; r++)
      {
         String ans = "";
      
         for(int c = 0; c < output[0].length; c++)
         {
            if(output[r][c] == null)
               ans += "-";
            else
               ans += output[r][c];
         }
         System.out.println(ans);
      }
      return "";
   }
   
   public String get(int r, int c)			//returns the element at row r, col c
   {
      for(int i = 0; i < list.size(); i ++)
      {
         if((list.get(i)).getRow() == r)
         {
            if((list.get(i)).getCol() == c)
               return (list.get(i)).getValue();
         }
      }  
      return null;
   }
   public String set(int r, int c, String x)	//changes element at (r,c), returns old value
   {
      int key = getKey(r, c); // finds key
      for(int i = 0; i < list.size(); i++)
      {
         int tryKey = getKey((list.get(i)).getRow(), (list.get(i)).getCol());
         if(tryKey == key) // tests if keys are the same
         {
            Cell replace = new Cell(r, c, (String)(x));
            String oldValue = (list.get(i)).getValue(); // old Cell's value
            list.set(i, replace);
            
            return oldValue;
         }
      }
      return null;
   }
   public void add(int r, int c, String x)	   //adds obj at row r, col c
   {
      if(list.size() == 0)
         list.add(new Cell(r, c, x));
      else
      {
         int key = getKey(r, c); // finds key
         for(int i = 0; i < list.size(); i++)
         {
            int tryKey = getKey((list.get(i)).getRow(), (list.get(i)).getCol());         
            if(tryKey < key) 
            {
               if((i + 1) != list.size())
               {
                  int highKey = getKey((list.get(i+1)).getRow(), (list.get(i+1)).getCol());
                  if(key < highKey)
                  {
                     list.add(i+1, new Cell(r, c, x));
                     break;
                  }
                  else
                  {
                     list.add(new Cell(r, c, x));
                     break;
                  }
               }
            }
            else // if tryKey > key
            {
               list.add(i, new Cell(r, c, x));
               break;
            }
         }
      
      }
   }
   public String remove(int r, int c)
   {
      int reference = getKey(r, c);
      String el = "";
      for(int i = 0; i < list.size(); i++)
      {
         int newKey = getKey((list.get(i)).getRow(), (list.get(i)).getCol()); 
         if(newKey == reference)
         {
            el = (list.get(i)).getValue();
            list.remove(i);
         }
      }
      return el;
   }
   public int size()			//returns # actual elements stored
   {
      return list.size();
   }
   public int numRows()		//returns # rows set in constructor
   {
      ArrayList<Integer> rowCheck = new ArrayList();
      for(int i = 0; i < list.size(); i++)
      {
         int r = (list.get(i)).getRow();
         if(!(rowCheck.contains(r))) // doesn't already have this row recorded
            rowCheck.add(r);
      }
      
      int max = 0;
      for(int x = 0; x < rowCheck.size(); x++)
      {
         if(rowCheck.get(x) > rowCheck.get(max))
            max = x;
      }
      int num = rowCheck.get(max);
      return num;
   }
   public int numColumns()	//returns # cols set in constructor
   {
      ArrayList<Integer> colCheck = new ArrayList();
      for(int i = 0; i < list.size(); i++)
      {
         int c = (list.get(i)).getCol();
         if(!(colCheck.contains(c))) // doesn't already have this row recorded
            colCheck.add(c);
      }
      
      int max = 0;
      for(int x = 0; x < colCheck.size(); x++)
      {
         if(colCheck.get(x) > colCheck.get(max))
            max = x;
      }
      int num = colCheck.get(max);
      return num;
   }
   
   public boolean contains(String x)		//true if x exists in list
   {
      for(int i = 0; i < list.size(); i++)
      {
         String val = (list.get(i)).getValue();
         if(val.equals(x))
            return true;
      }
      return false;
   }
   public int[] getLocation(String x)	//returns location [r,c] of where x exists in list, null otherwise
   {
      int[] rc = new int[2];
      for(int i = 0; i < list.size(); i++)
      {
         String val = (list.get(i)).getValue();
         if(val.equals(x))
         {
            int r = (list.get(i)).getRow();
            int c = (list.get(i)).getCol();
            
            rc[0] = r;
            rc[1] = c;
         }
      }
      return rc;
   }
   public String[][] toArray()				//returns equivalent structure in 2-D array form
   {
      int cols = numColumns();
      int rows = numRows();
      
      String [][] struct = new String[rows][cols];
      for(int i = 0; i < list.size(); i++)
      {
         int nRow = (list.get(i)).getRow();
         int nCol = (list.get(i)).getCol();
         String nVal = (list.get(i)).getValue();
         
         struct[nRow][nCol] = nVal;
      }
      
      return struct;
   }
   public boolean isEmpty()					//returns true if there are no actual elements stored
   {
      int check = 0;
      for(int i = 0; i < list.size(); i++)
      {
         if((list.get(i)).getValue() != null);
         check++;
      }
      if(check == 0)
         return true;
      return false;
   }
   public void clear()						//clears all elements out of the list
   {
      for(int i = 0; i < list.size(); i++)
      {
         int r = (list.get(i)).getRow();
         int c = (list.get(i)).getCol();
         
         set(r, c, null);
      }
   }
   public void setBlank(char blank)		//allows the client to set the character that a blank spot in the array is
   														//represented by in String toString()
   {
      
   }
}


