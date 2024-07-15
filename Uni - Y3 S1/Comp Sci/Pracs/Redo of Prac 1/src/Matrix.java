
public class Matrix<T> implements IMatrix<T> 
{
	
	public T[][]matrix;
	T[]rows;
	T[]cols;
	
	int nRow;
	int nCol;
	
	Matrix<T> tempMatrix;
	
	public Matrix(int r, int c, T variable)
	{
		nRow = r;
		nCol = c;
		
		matrix = (T[][]) new Object[r][c];
		rows = (T[]) new Object[r];
		cols = (T[]) new Object[c];
		
		
		
		//fill arrays
		if(variable instanceof Integer)
		{
			Integer intAdd = (int) variable;
			for(int r1 = 0; r1 < nRow; r1++)
			{
				
				for(int c1 = 0; c1 < nCol; c1++)
				{
					matrix[r1][c1] = (T)intAdd;
					
				}
				intAdd++;
				
			}
			
		}else
		if(variable instanceof Double)
		{
			Double intAdd = (Double) variable;
			for(int r1 = 0; r1 < nRow; r1++)
			{
				
				for(int c1 = 0; c1 < nCol; c1++)
				{
					matrix[r1][c1] = (T)intAdd;
					
				}
				intAdd++;
				
			}		
		}	
	}
	
	public void display()
	{
		for(int r1 = 0; r1 < numberRows(); r1++)
		{
			
			for(int c1 = 0; c1 < numberCols(); c1++)
			{

				System.out.print(matrix[r1][c1] + " ");
				
			}
			System.out.println("");
		}
		System.out.println("");
		
	}
	

	@Override
	public Integer numberRows() 
	{
		return nRow;
	}

	@Override
	public Integer numberCols() 
	{
		return nCol;
	}

	@Override
	public IMatrix<T> getRow(Integer i) throws MatrixException {
		
		Matrix tMatrix = new Matrix(numberRows(),numberCols(),matrix[0][0]);
		for(int c1 = 0; c1 < nCol; c1++)
		{
			rows[c1] = matrix[i][c1];
			tMatrix.rows[c1] = rows[c1];
		}
		return tMatrix;
	}

	@Override
	public IMatrix<T> getCol(Integer j) throws MatrixException {
		
		Matrix tMatrix = new Matrix(numberRows(),numberCols(),matrix[0][0]);
		for(int r1 = 0; r1 < nRow; r1++)
		{
			cols[r1] = matrix[r1][j];
			tMatrix.cols[r1] = cols[r1];
		}
		
		
		
		return tMatrix;
	}

	@Override
	public T getElement(Integer i, Integer j) throws MatrixException {
		
		return matrix[i][j];
	}

	@Override
	public IMatrix<T> addMatrix(IMatrix<T> otherMatrix) throws MatrixException
	{

		T variable = otherMatrix.getElement(0, 0);
		
		if(variable instanceof Integer)
		{
			for(int r1 = 0; r1 < nRow; r1++)
			{
				
				for(int c1 = 0; c1 < nCol; c1++)
				{
					variable = otherMatrix.getElement(r1, c1);
					Integer intAdd1 = (int) variable;
					Integer intAdd2 = (int) matrix[r1][c1];
					Integer intTot = intAdd1 + intAdd2;
					matrix[r1][c1] = (T)intTot;
					
				}
			}
			
		}else
		if(variable instanceof Double)
		{
			for(int r1 = 0; r1 < nRow; r1++)
			{
				
				for(int c1 = 0; c1 < nCol; c1++)
				{
					variable = otherMatrix.getElement(r1, c1);
					Double intAdd1 = (Double) variable;
					Double intAdd2 = (Double) matrix[r1][c1];
					Double intTot = intAdd1 + intAdd2;
					matrix[r1][c1] = (T)intTot;
					
				}				
			}		
		}
		
		//return this
		tempMatrix = new Matrix(numberRows(),numberCols(),variable);
		for(int r1 = 0; r1 < nRow; r1++)
		{
			
			for(int c1 = 0; c1 < nCol; c1++)
			{
				tempMatrix.matrix[r1][c1] = matrix[r1][c1];
			}
		}
			
		
		return tempMatrix;
	}

	@Override
	public IMatrix<T> addScalar(Integer c) throws MatrixException {

		for(int r1 = 0; r1 < nRow; r1++)
		{
			
			for(int c1 = 0; c1 < nCol; c1++)
			{

				Integer intAdd1 = (int) c;
				Integer intAdd2 = (int) matrix[r1][c1];
				Integer intTot = intAdd1 + intAdd2;
				matrix[r1][c1] = (T)intTot;
				
			}
		}
		
		//return this
		tempMatrix = new Matrix(numberRows(),numberCols(),c);
		for(int r1 = 0; r1 < nRow; r1++)
		{
			
			for(int c1 = 0; c1 < nCol; c1++)
			{
				tempMatrix.matrix[r1][c1] = matrix[r1][c1];
			}
		}
		return tempMatrix;
	}

	@Override
	public IMatrix addScalar(Double c) throws MatrixException {


		for(int r1 = 0; r1 < nRow; r1++)
		{
			
			for(int c1 = 0; c1 < nCol; c1++)
			{

				Double intAdd1 = (double) c;
				Double intAdd2 = (double) matrix[r1][c1];
				Double intTot = intAdd1 + intAdd2;
				matrix[r1][c1] = (T)intTot;
				
			}
		}
		
		//return this
		tempMatrix = new Matrix(numberRows(),numberCols(),c);
		for(int r1 = 0; r1 < nRow; r1++)
		{
			
			for(int c1 = 0; c1 < nCol; c1++)
			{
				tempMatrix.matrix[r1][c1] = matrix[r1][c1];
			}
		}
		return tempMatrix;
		
	}

	@Override
	public IMatrix<T> multiplyScalar(Integer c) throws MatrixException {
		for(int r1 = 0; r1 < nRow; r1++)
		{
			
			for(int c1 = 0; c1 < nCol; c1++)
			{


				Integer intAdd2 = (int) matrix[r1][c1];
				Integer intTot = intAdd2 * c;
				matrix[r1][c1] = (T)intTot;
				
			}
		}
		
		//return this
		tempMatrix = new Matrix(numberRows(),numberCols(),c);
		for(int r1 = 0; r1 < nRow; r1++)
		{
			
			for(int c1 = 0; c1 < nCol; c1++)
			{
				tempMatrix.matrix[r1][c1] = matrix[r1][c1];
			}
		}
		return tempMatrix;

	}

	@Override
	public IMatrix multiplyScalar(Double c) throws MatrixException {
		for(int r1 = 0; r1 < nRow; r1++)
		{
			
			for(int c1 = 0; c1 < nCol; c1++)
			{

				Double intAdd2 = (Double) matrix[r1][c1];
				Double intTot = intAdd2 * c;
				matrix[r1][c1] = (T)intTot;
				
			}
		}
		
		//return this
		tempMatrix = new Matrix(numberRows(),numberCols(),c);
		for(int r1 = 0; r1 < nRow; r1++)
		{
			
			for(int c1 = 0; c1 < nCol; c1++)
			{
				tempMatrix.matrix[r1][c1] = matrix[r1][c1];
			}
		}
		return tempMatrix;
	}

	@Override
	public IMatrix<T> multiplyMatrix(IMatrix<T> otherMatrix) throws MatrixException {

		
		

		//make IMatrix into Matrix
		Matrix<T> tempOther = new Matrix(otherMatrix.numberRows(),otherMatrix.numberCols(),otherMatrix.getElement(0, 0));
		for(int r1 = 0; r1 < otherMatrix.numberRows(); r1++)
		{
			
			for(int c1 = 0; c1 < otherMatrix.numberCols(); c1++)
			{
				tempOther.matrix[r1][c1] = otherMatrix.getElement(r1, c1);
			}
		}
		
		tempMatrix = new Matrix(numberRows(),otherMatrix.numberCols(),matrix[0][0]);

		
		//IMatrix<T> tempRow = new Matrix(numberRows(),numberCols(),matrix[0][0]);
		
		
		
		for(int r1 = 0; r1 < nRow; r1++)
		{
			//System.out.println("r1:"+r1);
			
			for(int c1 = 0; c1 < otherMatrix.numberCols(); c1++)
			{
				//System.out.println("c1:"+c1);
				getRow(r1);
				tempOther.getCol(c1);
				
				T variable = matrix[0][0];
				if(variable instanceof Integer)
				{

					Integer total = (int) 0;
					for(int c2 = 0; c2 < nCol; c2++)
					{
						//ystem.out.println("c2:"+c2);
						//Integer intAdd = (Integer) matrix[r1][c2];
						//System.out.println("row mat" + c2 + " " + intAdd);
						Integer intAdd = (Integer) rows[c2];
						Integer intAdd2 = (Integer) tempOther.cols[c2];
						//System.out.println("row " + c2 + " " + intAdd2);
						total = total + intAdd*intAdd2;
						//System.out.println("total " + c2 + " " + total + "\n");
						
					
					}
					
					//for some reason it changes the tempMatrix matrix form 3x2 to 3x2
					tempMatrix.matrix[r1][c1] = (T) total;
				}
				
				if(variable instanceof Double)
				{
					Double total = (Double) 0.0;
					for(int c2 = 0; c2 < nCol; c2++)
					{
						//ystem.out.println("c2:"+c2);
						//Double intAdd = (Double) matrix[r1][c2];
						//System.out.println("row mat" + c2 + " " + intAdd);
						Double intAdd = (Double) rows[c2];
						Double intAdd2 = (Double) tempOther.cols[c2];
						//System.out.println("row " + c2 + " " + intAdd2);
						total = total + intAdd*intAdd2;
						//System.out.println("total " + c2 + " " + total + "\n");
						
					
					}
					
					
				}
				

				
		
			}

		}
		

		
		tempMatrix.display();
		return null;
	}

	@Override
	public IMatrix<T> transpose() 
	{
		
		tempMatrix = new Matrix(numberCols(),numberRows(),matrix[0][0]);
		
		for(int r1 = 0; r1 < nRow; r1++)
		{
			
			for(int c1 = 0; c1 < nCol; c1++)
			{
				tempMatrix.matrix[c1][r1] = matrix[r1][c1];
			}
		}
		tempMatrix.display();
		return tempMatrix;
	}



}
