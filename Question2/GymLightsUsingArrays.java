import java.util.Arrays;

// Using Arrays

public class GymLightsUsingArrays {
	
	public static int countLightSwitches(int[] entryTimes,int[] exitTimes) {
		int n = entryTimes.length;
		
		// Sort both entry and exit times
		int[] sortedEntries = Arrays.copyOf(entryTimes,n);
		int[] sortedExits = Arrays.copyOf(exitTimes, n);
		Arrays.sort(sortedEntries);
		Arrays.sort(sortedExits);
		
		int i=0; //Pointer for entryTimes
		int j=0; //Pointer for exitTimes
		int currentOccupancy = 0;
		int switchOnCount = 0;
		
		while(i < n) {
			if(sortedEntries[i] < sortedExits[j]) {
				// A person enters a room
				if(currentOccupancy == 0) {
					// Gym was empty, turn lights on
					switchOnCount++;
				}
				currentOccupancy++;
				i++;
			}else { // A person leaves gym
				currentOccupancy--;
				j++;
			}
		}
		
		return switchOnCount;
	}

	public static void main(String[] args) {
		int[] entryTimes1 = {1, 2, 4};
		int[] exitTimes1 = {3, 5, 6};
		System.out.println("Number of times the lights were switched on: "+ countLightSwitches(entryTimes1,exitTimes1));
		
	}

}
