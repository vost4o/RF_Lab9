package ro.usv.rf;

/**
 * Main class of the application
 * 
 * @author vasyl
 *
 */
public class VMainClass {
	/**
	 * Main method
	 * 
	 * @param args - application parameters
	 */
	public static void main(String... args) {
		try {
			double[][] dataSet = FileUtils.readLearningSetFromFile("in.txt");

			double[] classes = DynamicKernelClassifier.performClassify(dataSet, 2);

			for (int i = 0; i < classes.length; ++i) {
				System.out.print(classes[i] + ", ");
			}
		} catch (USVInputFileCustomException e) {
			e.printStackTrace();
		}
	}
}
