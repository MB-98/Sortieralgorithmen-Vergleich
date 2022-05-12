import java.util.Random;

public class Sortieralgorithmen {

	static long selectionsort(int[] selectionArray, long selectTime, String st) {
		if (st.toLowerCase().equals("aufsteigend")) {
			final long timeStartNano = System.nanoTime();
			for (int i = 0; i < selectionArray.length - 1; i++) {
				int min_idx = i;
				for (int j = i + 1; j < selectionArray.length; j++)
					if (selectionArray[j] < selectionArray[min_idx])
						min_idx = j;
				int temp = selectionArray[min_idx];
				selectionArray[min_idx] = selectionArray[i];
				selectionArray[i] = temp;

			}
			selectTime = System.nanoTime() - timeStartNano;
		} else {
			final long timeStartNano = System.nanoTime();
			for (int i = 0; i < selectionArray.length - 1; i++) {
				int max_idx = i;
				for (int j = i + 1; j < selectionArray.length; j++)
					if (selectionArray[j] > selectionArray[max_idx])
						max_idx = j;
				int temp = selectionArray[max_idx];
				selectionArray[max_idx] = selectionArray[i];
				selectionArray[i] = temp;

			}
			selectTime = System.nanoTime() - timeStartNano;

		}
		System.out.println(" ");
		System.out.println("Verlaufszeit der Select-Sort Schleife: " + (selectTime) + " Nanosek.");
		return selectTime;
	}

	static long insertSort(int[] insertionArray, long insertTime, String st) {
		if (st.toLowerCase().equals("aufsteigend")) {
			final long timeStartNano = System.nanoTime();
			int k;
			for (int i = 0; i < insertionArray.length; i++) {
				for (int j = insertionArray.length - 1; j > 0; j--) {
					if (insertionArray[j - 1] > insertionArray[j]) {
						k = insertionArray[j];
						insertionArray[j] = insertionArray[j - 1];
						insertionArray[j - 1] = k;
					}
				}
			}

			insertTime = System.nanoTime() - timeStartNano;
		} else {
			final long timeStartNano = System.nanoTime();
			int k;
			for (int i = 0; i < insertionArray.length; i++) {
				for (int j = insertionArray.length - 1; j > 0; j--) {
					if (insertionArray[j - 1] < insertionArray[j]) {
						k = insertionArray[j];
						insertionArray[j] = insertionArray[j - 1];
						insertionArray[j - 1] = k;
					}
				}
			}

			insertTime = System.nanoTime() - timeStartNano;
		}
		System.out.println(" ");
		System.out.println("Verlaufszeit der Insert-Sort Schleife: " + (insertTime) + " Nanosek.");
		return insertTime;
	}

	static long quickSort(int array[], long quickTime, String st) {

		final long timeStartNano = System.nanoTime();

		qSort(array, 0, array.length - 1, st);

		quickTime = System.nanoTime() - timeStartNano;

		System.out.println(" ");
		System.out.println("Verlaufszeit der Quick-Sort Schleife: " + (quickTime) + " Nanosek.");
		return quickTime;
	}

	public static void qSort(int x[], int links, int rechts, String st) {
		if (links < rechts) {
			int i = partition(x, links, rechts, st);
			qSort(x, links, i - 1, st);
			qSort(x, i + 1, rechts, st);
		}
	}

	public static int partition(int x[], int links, int rechts, String st) {
		if (st.toLowerCase().equals("aufsteigend")) {
			int pivot, i, j, help;
			pivot = x[rechts];
			i = links;
			j = rechts - 1;
			while (i <= j) {
				if (x[i] > pivot) {
					help = x[i];
					x[i] = x[j];
					x[j] = help;
					j--;
				} else
					i++;
			}
			help = x[i];
			x[i] = x[rechts];
			x[rechts] = help;

			return i;
		} else {
			int pivot, i, j, help;
			pivot = x[rechts];
			i = links;
			j = rechts - 1;
			while (i <= j) {
				if (x[i] < pivot) {
					help = x[i];
					x[i] = x[j];
					x[j] = help;
					j--;
				} else
					i++;
			}
			help = x[i];
			x[i] = x[rechts];
			x[rechts] = help;

			return i;
		}
	}

	static long bubbleSort(int array[], long bubbleTime, String st) {
		if (st.toLowerCase().equals("aufsteigend")) {
			final long timeStartNano = System.nanoTime();
			for (int i = 0; i < array.length - 1; i++) {
				for (int j = 0; j < array.length; j++) {
					if (array[j] > array[i + 1]) {
						int x = array[j];
						array[j] = array[i + 1];
						array[i + 1] = x;
					}
				}
			}
			bubbleTime = System.nanoTime() - timeStartNano;
		} else {
			final long timeStartNano = System.nanoTime();
			for (int i = 0; i < array.length - 1; i++) {
				for (int j = 0; j < array.length; j++) {
					if (array[j] < array[i + 1]) {
						int x = array[j];
						array[j] = array[i + 1];
						array[i + 1] = x;
					}
				}
			}
			bubbleTime = System.nanoTime() - timeStartNano;
		}
		System.out.println(" ");
		System.out.println("Verlaufszeit der Bubble-Sort Schleife: " + (bubbleTime) + " Nanosek.");
		return bubbleTime;
	}

	public static int[] copyArray(int[] array) {
		int[] newArray = new int[array.length];
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = array[i];
		}
		return newArray;
	}

	public static int[] randomNum(int x) {

		int a[] = new int[x];
		Random rd = new Random();
		for (int i = 0; i < x; i++) {

			a[i] = rd.nextInt(1000);

		}
		return a;
	}

	public static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
