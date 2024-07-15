
public class Main {

	public static void main(String[] args) 
	{
		Matrix<Integer> intMatrix1 = new Matrix<Integer>(2,3,2);
		Matrix<Integer> intMatrix2 = new Matrix<Integer>(3,2,3);
		
		System.out.println("int Array 1");
		intMatrix1.display();
		
		System.out.println("int Array 2");
		intMatrix2.display();
		/*
		System.out.println("int Array 1 + 2");
		try {
			intMatrix1.addMatrix(intMatrix2);
		} catch (MatrixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		intMatrix1.display();
		
		System.out.println("int Array 1 add scalar 2");
		try {
			intMatrix1.addScalar(2);
		} catch (MatrixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		intMatrix1.display();
		
		System.out.println("int Array 1 multiply scalar 2");
		try {
			intMatrix1.multiplyScalar(2);
		} catch (MatrixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		intMatrix1.display();
		*/
		
		System.out.println("int Array 1 transposed");
		intMatrix1.transpose();
		
		System.out.println("int Array 2 * int array 1");
		try {
			intMatrix2.multiplyMatrix(intMatrix1);
		} catch (MatrixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		

	}

}
