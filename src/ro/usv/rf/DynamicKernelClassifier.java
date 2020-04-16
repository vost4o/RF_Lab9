package ro.usv.rf;

/**
 * This class is used to implement dynamic kernel classifier
 * 
 * @author vasyl
 *
 */
public class DynamicKernelClassifier {

	protected static double[] performClassify(double[][] dataSet, int predefineClassCount) {
		int numOfPatterns = dataSet.length;
		int numOfFeatures = dataSet[0].length;

		double[] iClass = new double[numOfPatterns];
		double[][] kArray = new double[predefineClassCount][numOfFeatures];
		for (int i = 0; i < predefineClassCount; ++i) {
			kArray[i] = initKernel(dataSet[i]);
		}

		// Algorithm itself
		boolean done = false;
		do {
			done = true;

			double[][] gravCenterOfPatterns = new double[predefineClassCount][numOfFeatures];
			int[] numOfPatternsForClass = new int[predefineClassCount];
			int kMin = 0;

			for (int i = 0; i < numOfPatterns; ++i) {

				double dMin = Double.MAX_VALUE;
				for (int k = 0; k < predefineClassCount; ++k) {
					double dIK = DistanceUtils.euclidianDistanceGeneralized(dataSet[i], kArray[k]);
					if (dIK < dMin) {
						dMin = dIK;
						kMin = k;
					}
				}

				numOfPatternsForClass[kMin] += 1;

				for (int j = 0; j < numOfFeatures; ++j) {
					gravCenterOfPatterns[kMin][j] += dataSet[i][j];
				}

				if (iClass[i] != kMin) {
					iClass[i] = kMin;
					done = false;
				}

				if (!done) {
					for (int k = 0; k < predefineClassCount; ++k) {
						for (int j = 0; j < numOfFeatures; ++j) {
							gravCenterOfPatterns[k][j] /= numOfPatternsForClass[k];
						}

						double minDistanceToGrav = Double.MAX_VALUE;
						int clossestPattern = 0;

						if (iClass[i] == k) {
							for (int i0 = 0; i0 < numOfPatterns; i0++) {
								double distance = DistanceUtils.euclidianDistanceGeneralized(dataSet[i0],
										gravCenterOfPatterns[k]);
								if (distance < minDistanceToGrav) {
									minDistanceToGrav = distance;
									clossestPattern = i0;
								}
							}

							kArray[k] = dataSet[clossestPattern];
						}
					}
				}
			}
		} while (!done);

		return iClass;
	}

	private static double[] initKernel(double[] dataSetRow) {
		double[] newDataSet = new double[dataSetRow.length];

		for (int i = 0; i < newDataSet.length; ++i) {
			newDataSet[i] = dataSetRow[i];
		}

		return newDataSet;
	}
}
