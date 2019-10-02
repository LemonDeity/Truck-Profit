import java.util.Scanner;
import java.io.IOException;
import java.io.File;
public class Main {

    public static void main(String[] args) throws IOException{
	    Scanner input = new Scanner(new File("truck.txt"));
	    int NUM_TEST = input.nextInt();
	    input.nextLine();
		for (int i = 0; i < NUM_TEST; i++) {
			int TRUCK_MAX_WEIGHT = input.nextInt();
			int NUM_ITEMS = input.nextInt();
			Item[] items = new Item[NUM_ITEMS];

			for (int j = 0; j < items.length; j++) {
				int w = input.nextInt();
				int c = input.nextInt();
				items[j] = new Item(w,c);
			}

			int[][] arr = new int[NUM_ITEMS][TRUCK_MAX_WEIGHT+1];
			System.out.println(getNum(arr,items,0,0));
		}
    }

    public static int getNum(int[][] arr, Item[] items, int i, int j){
    	if (i == arr.length) return arr[arr.length-1][arr[0].length-1];

    	Item item = items[i];

    	if (j < arr[i].length){
			try{
				//can add weight
				int C = arr[i][j-item.Weight] + item.Cost;
				if (C > arr[i][j-1] && C > arr[i-1][j]){
					arr[i][j] = C;
				}else if(arr[i-1][j] > arr[i][j-1]){
					arr[i][j] = arr[i-1][j];
				}else{
					arr[i][j] = arr[i][j-1];
				}
			}catch(Exception ArrayIndexOutOfBoundsException){
				if(i == 0 && j-item.Weight < 0){
					arr[i][j] = 0;
				}else if(i == 0){
					//means can't look above
					if (j%item.Weight == 0) arr[i][j] = item.Cost*(j/item.Weight);
				}else if(j - item.Weight < 0){
					//check above
					if (arr[i-1][j] > 0){
						arr[i][j] = arr[i-1][j];
					}
				}else{
					System.out.println("Unchecked Exception found");
				}
			}
			return getNum(arr,items,i,j+1);
		}else{
    		return getNum(arr,items,i+1,0);
		}
	}

	public static class Item {
		int Weight;
		int Cost;

		public Item(int Weight, int Cost){
			this.Weight = Weight;
			this.Cost = Cost;
		}

	}

	/*public static int getNum(int[][] arr, Item[] items){
		for (int i = 0; i < arr.length; i++) {
			Item item = items[i];
			for (int j = 0; j < arr[i].length; j++) {
				//I represents item
				//j represents max weight of truck
				try{
					//can add weight
					int C = arr[i][j-item.Weight] + item.Cost;
					if (C > arr[i][j-1] && C > arr[i-1][j]){
						arr[i][j] = C;
					}else if(arr[i-1][j] > arr[i][j-1]){
						arr[i][j] = arr[i-1][j];
					}else{
						arr[i][j] = arr[i][j-1];
					}
				}catch(Exception ArrayIndexOutOfBoundsException){
					if(i == 0 && j-item.Weight < 0){
						arr[i][j] = 0;
					}else if(i == 0){
						//means can't look above
						if (j%item.Weight == 0) arr[i][j] = item.Cost*(j/item.Weight);
					}else if(j - item.Weight < 0){
						//check above
						if (arr[i-1][j] > 0){
							arr[i][j] = arr[i-1][j];
						}
					}else{
						System.out.println("Unchecked Exception found");
					}
				}
			}
		}

		return arr[arr.length-1][arr[0].length-1];
	}*/
	//checks to see if you can add weight
}
