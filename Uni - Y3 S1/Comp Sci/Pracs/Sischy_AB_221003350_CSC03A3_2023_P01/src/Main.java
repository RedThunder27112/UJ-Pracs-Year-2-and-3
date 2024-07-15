import java.util.Arrays;

public class Main 
{

	public static void main(String[] args) 
	{
		System.out.println("Creating Integer Matrix 1");
		Matrix<Integer> matrix = new Matrix<Integer>(4,3);
		matrix.matrixInt();
		
		matrix.display();
		//displays int matrix
		System.out.println("Creating Integer Matrix 2");
		Matrix<Integer> matrix2 = new Matrix<Integer>(4,3);
		matrix2.matrixInt();
		matrix2.display();
		System.out.println("");
		
		System.out.println("Adding 2 to Matrix 1");
		try 
		{
			matrix.addScalar(2);
		} catch (MatrixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		matrix.display();
		
		System.out.println("");
		System.out.println("Transposing Matrix 1");
		matrix.transpose();
		
		System.out.println("");
		System.out.println("Creating double matrix");
		Matrix<Double> matrix3 = new Matrix<Double>(4,3);
		matrix3.matrixDouble();
		matrix3.display();
		
		System.out.println("");
		System.out.println("multiplying matrix 1 by 3");
		try {
			matrix.multiplyScalar(3);
		} catch (MatrixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		matrix.display();
		
		System.out.println("");
		System.out.println("multiplying double matrix 3 by 3");
		try {
			matrix3.multiplyScalar(3.0);
		} catch (MatrixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		matrix3.display();
		
		System.out.println("");
		System.out.println("adding scalar to double matrix 3 by 3");
		try {
			matrix3.addScalar(3.0);
		} catch (MatrixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		matrix3.display();
		
		
		System.out.println("---------------");
		System.out.println("rest not displayed, no time");

		System.out.println("---------------");
		

	}

}
