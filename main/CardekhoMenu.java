package com.jspiders.cardekho_case_study_jdbc.main;

import java.util.Scanner;

import com.jspiders.cardekho_case_study_jdbc.operation.CarOperation;

public class CardekhoMenu {
	public static boolean flag = true;
	 private static Scanner inp = new Scanner(System.in);
	private static boolean flag2 = true;
	private static boolean flag3=true;
	public static boolean flag4=true;
	public static boolean flag5=true;

	public static void main(String[] args) {

		while (flag) {
			mainMenu();
		}
	}
	public static void searchCar() {
		if(CarOperation.isDatabaseEmpty()) {
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("Their Are No Car_Details Available In DataBase To Search");
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			flag2=false;
		}
		else {
			System.out.println("Total Number Car Id Present In DataBase To Search Are :"+CarOperation.carIds().size()+"||Which Are :>"+CarOperation.carIds());
			System.out.println("*******************************************************");
			System.out.println("By Using Which Property You Want To Search Car_Details \n=================================================="+ " \n 1.CarName"
					+ "\n 2.CarBrand"
					+ "\n 3.Car_Fuel_Type"
					+ "\n 4.Car_Id"
					+ "\n 5.Car_Price"
					+ "\n 6.Go_Back_TO_MainMenu");
			int choice =inp.nextInt();
			switch (choice) {
			case 1:
				CarOperation.searchByName();
				break;
			case 2:
				CarOperation.searchByBrand();
				break;
			case 3:
				CarOperation.searchByFuelType();
				break;
			case 4:
				CarOperation.searchByCarId();
				break;
			case 5:
				CarOperation.searchByPrice();
				break;
			case 6:
				flag2= false; break;

			default:
				System.out.println("Invailid Input");
				break;
			}
		}
		
	}
	
	public static void updateCar() {

		if(CarOperation.carIds().size()== 0) {
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("Their Are No Car_Details Available In DataBase To Update");
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			flag3=false;
		}
		else {
		System.out.println("Total Number Car Id Present In DataBase are :"+CarOperation.carIds().size()+"||Which Are :"+CarOperation.carIds());
			System.out.println(" Which Property You Want To Update In Car_Details"+ " \n 1.CarName"
					+ "\n 2.CarBrand"
					+ "\n 3.Car_Fuel_type"
					+ "\n 4.Car_Id"
					+ "\n 5.Car_Price"
					+ "\n 6.Go_Back");
			int choice =inp.nextInt();
			switch (choice) {
			case 1:
				
				CarOperation.updateCarName();;
				break;
			case 2:
				
				CarOperation.updateBrand();
				break;
			case 3:
				
				CarOperation.updateFuelType();
				break;
			case 4:
				
				CarOperation.updateId();
				break;
			case 5:
				
				CarOperation.updatePrice();
				break;
			case 6:
				flag3= false;
				 break;

			default:
				System.out.println("(((((Invalid Input Please Give Correct Input)))))");
				break;
			}
		}
		
	}

	public static void mainMenu() {
		 Scanner inp = new Scanner(System.in);
		System.out.println("\n=======Main Menu=======\n" + " 1.Add Car_Details\n" + " 2.Search Car_Details\n "
				+ "3.Update Car_Details\n " + "4.Delete Car_Details\n " + "5.All Car_Details \n" + " 6.Exit \n========================\n");

		int inpMain = inp.nextInt();
		switch (inpMain) {
		case 1: {
			System.out.println("_____Adding_Car_Details_To_DataBase_____\n ");
			CarOperation.addCars();
			break;

		}
		case 2: {
			System.out.println("_____Searching_Car_Details_In_DataBase_____\n");
			flag2 = true;
			while (flag2) {
				searchCar();
			}
			break;
		}
		case 3: {
			flag4=true;
			while (flag4) {
				System.out.println("_____Updating_Car_Details_In_DataBase_____\n");
				System.out.println("Do You Want To See All Car Detail Then Press 1 Or To Continue Updating Press 2 Or To Go To Main Menu Press Any Other Number");
				int input =inp.nextInt();
				if (input==1) {
					CarOperation.allCarDetails();
				}
				else if(input==2) {
					flag3=true;
					while (flag3) {
					updateCar();		
				} } 
				else {
				flag4=false;
			}
			}
			break;
		}
		case 4: {
			flag5=true;
			while (flag5) {
				System.out.println("_____Deleting_Car_Details_From_DataBase_____\n");
				CarOperation.deleteCar();
				
			}
			
			break;
		}
		case 6: {
			flag = false;
			System.out.println("!!!!!_____Thank You_____!!!!!");
			break;
		}
		case 5:
			System.out.println("_____Getting_Car_Details_From_DataBase_____\n");
			CarOperation.allCarDetails();
			break;

		default:
			System.out.println("(((((Invalid Input Please Give Correct Input)))))");
			break;
		}
	}

	

}
