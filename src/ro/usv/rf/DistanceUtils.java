package ro.usv.rf;

/**
 * Distances utilities
 * @author vasyl
 *
 */
public class DistanceUtils {
	protected static double euclidianDistanceGeneralized(double[] ds, double[] ds2) {
		double distance = 0;
		// Calculate the distance between two patterns
		for (int i = 0; i < ds.length; ++i) {
			distance += Math.pow(ds[i] - ds2[i], 2);
		}
		
		// Get the square root
		distance = Math.sqrt(distance);
		
		return Math.floor(distance * 100) /100;
	}
}
