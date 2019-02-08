package fs;
/*各种类型的class对象的获取,同一类型是单例的*/
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "com.bean.bean1";
		try {
			Class<?> clazz = Class.forName(path);
			System.out.println(clazz+"hascode:"+clazz.hashCode());
			
			Class str1 = String.class;
			Class str2 = path.getClass();
			System.out.println(str1==str2);
			Class intc = int.class;
			int[] arr1 = new int[10];
			int[] arr2 = new int[20];
			int[][] arr3 = new int[30][3];
			double[] arr4 = new double[10];
			System.out.println(arr1.getClass().hashCode());
			System.out.println(arr2.getClass().hashCode());
			System.out.println(arr3.getClass().hashCode());
			System.out.println(arr4.getClass().hashCode());
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
