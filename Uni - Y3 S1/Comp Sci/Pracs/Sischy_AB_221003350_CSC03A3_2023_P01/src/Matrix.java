import java.util.Arrays;

public class Matrix<T> implements IMatrix<T>
{

	T[][] matrix;
	T[] matrixR;
	T[] matrixC;
	
	int nRow;
	int nCol;
	IMatrix<T>[][] iMatrix;
	
	Matrix<T> tempMatrix;

	public Matrix(int col,int row)
	//public Matrix(int row,int col)
	{
		nRow = row;
		nCol = col;
		
		//iMatrix = (IMatrix<T>[][]) new Arrays[col][row];
		matrix = (T[][]) new Object[col][row];
		matrixR = (T[]) new Object[row];
		matrixC = (T[]) new Object[col];
	}
	
	public void matrixDouble()
	{
		for(int r = 0; r < numberRows(); r++)
		{
			for(int c = 0; c < numberCols(); c++)
			{
				Double x = (Math.random()*10);
				if(x instanceof Double)
				{
					
					try 
					{					
						setElement(c, r, (T) x);
					} catch (MatrixException e) {

						e.printStackTrace();
					}

				}

				
			}
		}
		
			
	}
	
	public void matrixInt()
	{
		for(int r = 0; r < numberRows(); r++)
		{
			for(int c = 0; c < numberCols(); c++)
			{
				Integer x = (int) (Math.random()*10);
				if(x instanceof Integer)
				{
					try 
					{					
						setElement(c, r, (T) x);
					} catch (MatrixException e) {

						e.printStackTrace();
					}

				}

				
			}
		}
	}
	
	public void display()
	{
		for(int r = 0; r < numberRows(); r++)
		{
			for(int c = 0; c < numberCols(); c++)
			{

				try {
					System.out.print(getElement(c,r) + " ");
				} catch (MatrixException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}
			System.out.print("\n");
		}
	}
	
	

	@Override
	public Integer numberRows() 
	{
		return nRow;
	}

	@Override
	public Integer numberCols() {
		// TODO Auto-generated method stub
		return nCol;
	}

	@Override
	public IMatrix getRow(Integer i) throws MatrixException 
	{
		for(int c = 0; c < numberCols(); c++)
		{
			matrixR[c] = getElement(c, i);
		}
		
		return tempMatrix;
	}

	@Override
	public IMatrix getCol(Integer j) throws MatrixException {
		
		for(int r = 0; r < numberRows(); r++)
		{
			matrixC[r] = getElement(j, r);
		}
		
		return tempMatrix;
	}

	@Override
	public T getElement(Integer i, Integer j) throws MatrixException {
		T output = matrix[i][j];//col row
		return output;
	}
	
	public void setElement(Integer i, Integer j, T element) throws MatrixException {

		matrix[i][j] = element;
	}

	@Override
	public IMatrix addMatrix(IMatrix otherMatrix) throws MatrixException 
	{
				
		for(int r = 0; r < numberRows(); r++)
		{
			for(int c = 0; c < numberCols(); c++)
			{

				
				if(otherMatrix.getElement(c, r) instanceof Integer)
				{
					Integer i = (int) otherMatrix.getElement(c, r);
					addScalar(i);
				}else
				if(otherMatrix.getElement(c, r) instanceof Double)
				{
					Double i = (double) otherMatrix.getElement(c, r);
					addScalar(i);	
				}
				
				
				
			}
		}
		
		return tempMatrix;
	}

	@Override
	public IMatrix addScalar(Integer c) throws MatrixException 
	{
		for(int r = 0; r < numberRows(); r++)
		{
			for(int col = 0; col < numberCols(); col++)
			{
				//if(tempMatrix.matrix[col][r] instanceof Integer)
				//{
				Integer i = (int) matrix[col][r];
				i = i + c;
				setElement(col, r, (T) i);
				//}
				
			}
		}
		
			

		return tempMatrix;
	}

	@Override
	public IMatrix addScalar(Double c) throws MatrixException {

		for(int r = 0; r < numberRows(); r++)
		{
			for(int col = 0; col < numberCols(); col++)
			{
				//if(tempMatrix.matrix[col][r] instanceof Double)
				//{
				Double i = (double) matrix[col][r];
				i = i + c;
				setElement(col, r, (T) i);
				//}
				
			}
		}
		return tempMatrix;
	}

	@Override
	public IMatrix multiplyScalar(Integer c) throws MatrixException {
		
		
		for(int r = 0; r < numberRows(); r++)
		{
			for(int col = 0; col < numberCols(); col++)
			{
				//if(tempMatrix.matrix[col][r] instanceof Integer)
				//{
				Integer i = (int) matrix[col][r];
				i = i * c;
				setElement(col, r, (T) i);
				//}
				
			}
		}
		
		return tempMatrix;
	}

	@Override
	public IMatrix multiplyScalar(Double c) throws MatrixException {
		for(int r = 0; r < numberRows(); r++)
		{
			for(int col = 0; col < numberCols(); col++)
			{
				//if(tempMatrix.matrix[col][r] instanceof Double)
				//{
				Double i = (double) matrix[col][r];
				i = i * c;
				setElement(col, r, (T) i);
				//}
				
			}
		}
		return tempMatrix;
	}

	@Override
	public IMatrix multiplyMatrix(IMatrix otherMatrix) throws MatrixException 
	{
		Matrix<T> multiMatrix = new Matrix(otherMatrix.numberCols(),numberRows());//col row
		boolean isInt = false;
		for(int r = 0; r < numberRows(); r++)
		{
			for(int col2 = 0; col2 < otherMatrix.numberCols(); col2++)
			{
				Double multiDouble = 0.0;
				Integer multiInt = 0;
				
				for(int c = 0; c < numberCols(); c++)
				{

					
					T i = getElement(c, col2);
					T i2 = (T) otherMatrix.getElement(r, c);
					
					
					
					if(i instanceof Integer && i2 instanceof Integer)
					{
						Integer n = (int) i;
						Integer n2 = (int) i2;
						multiInt = multiInt + n * n2;
						isInt = true;
					}
					
					if(i instanceof Double && i2 instanceof Double)
					{
						Double d = (double) i;
						Double d2 = (double) i2;
						multiDouble = multiDouble + d * d2;
					}
						
				}
				

				if(isInt)
				{
					multiMatrix.matrix[col2][r] = (T) multiInt;
				}else
				{
					multiMatrix.matrix[col2][r] = (T) multiDouble;
				}
				
				
			}
		}
		return null;
	}

	@Override
	public IMatrix transpose() 
	{
		Matrix<T> transposeMatrix = new Matrix(numberCols(), numberCols());
		
		for(int c = 0; c < numberCols(); c++)
		{
			for(int r = 0; r < numberRows(); r++)
			{

				

				{
					transposeMatrix.matrix[r][c] = matrix[c][r];

				}

			}
		}
		
		for(int r = 0; r < numberRows(); r++)
		{
			for(int c = 0; c < numberCols(); c++)
			{
				System.out.print(transposeMatrix.matrix[c][r] + " ");
			}
			System.out.println("");
		}
	
		
		return transposeMatrix;
	}

}
