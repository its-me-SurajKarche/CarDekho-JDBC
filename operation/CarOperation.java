package com.jspiders.cardekho_case_study_jdbc.operation;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import com.jspiders.cardekho_case_study_jdbc.entity.Car;
import com.jspiders.cardekho_case_study_jdbc.main.CardekhoMenu;

public class CarOperation {

	private static String filePath = "D:\\WEJA2\\cardekho_case_study_jdbc\\cardb_info.properties";
	private static PreparedStatement preparedStatement;
	private static Connection connection;
	private static String query;
	private static FileInputStream fileInputStream;
	private static Properties properties = new Properties();
	private static ResultSet resultSet;
	private static int result;
	private static Scanner scanner = new Scanner(System.in);
	private static ArrayList<Integer> carIds;

	public static void openConnection() {
		try {
			fileInputStream = new FileInputStream(filePath);
			properties.load(fileInputStream);
			Class.forName(properties.getProperty("dpath"));
			connection = DriverManager.getConnection(properties.getProperty("dburl"), properties);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
			if (fileInputStream != null) {
				fileInputStream.close();
			}
			if (result != 0) {
				result = 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void addCars() {
		try {
			System.out.println("carIds which are already registered are as below:  \n ==> "+carIds()+" <=="+"\n*****************************");
			openConnection();
			System.out.println("how many cars you want to add");
			int amount = scanner.nextInt();
			query = "insert into car values(?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(query);
			for (int i = 1; i <= amount; i++) {
				System.out.println("Enter The CarID You Want To Add OR Type Exit To Go To MainMenu");
				String id=scanner.next();
				if (id.equalsIgnoreCase("exit")) {
					i=amount;
				}
				else {
					int carId=Integer.parseInt(id);
					Car car = new Car();
					car.setCarID(carId);
					scanner.nextLine();
					System.out.println("add carName for "+i+" car");
					car.setCarName(scanner.nextLine());
					System.out.println("add brandName for "+i+" car");
					car.setBrandName(scanner.nextLine());
					System.out.println("add fuelType for "+i+" car");
					car.setFuelType(scanner.nextLine());
					System.out.println("add price for "+i+" car");
					car.setPrice(scanner.nextFloat());
					preparedStatement.setInt(1,car.getCarID());
					preparedStatement.setString(2,car.getCarName());
					preparedStatement.setString(3,car.getBrandName());
					preparedStatement.setString(4,car.getFuelType());
					preparedStatement.setFloat(5, car.getPrice());
					result=result+preparedStatement.executeUpdate();

				}
				
			}
			System.out.println("query ok ,total row(s) affected =>"+result);
			scanner.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			closeConnection();
		}

	}
	public static ArrayList<Integer> carIds(){
		try {
			openConnection();
			query="select * from car ";
			preparedStatement= connection.prepareStatement(query);
			resultSet=preparedStatement.executeQuery();
			carIds= new ArrayList <Integer>();
			while(resultSet.next()) {
				carIds.add(resultSet.getInt(1));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			closeConnection();
			}
		return carIds;
	}
	public static void searchByName() {
		try {
			openConnection();
			query="select * from car where carName=?";
			preparedStatement= connection.prepareStatement(query);
			System.out.println("enter car name you want to search");
			String name=scanner.nextLine();
			preparedStatement.setString(1, name);
			resultSet=preparedStatement.executeQuery();
			boolean flag=true;
			System.out.println("Following Are The Cars We Found With CarName :"+name+"\n=======================================");
			while (resultSet.next()) {
				if (name.equalsIgnoreCase(resultSet.getString(2))) {
					flag=false;
					processQuery();
				}
			}
		
		 if(flag){
			System.out.println("=======================================\n*** No Car Found With CarName You Entered ***\n=======================================");
		}
			
		} catch (Exception e) {
		     e.printStackTrace();
		}
		finally {
			closeConnection();
		}
	}
	
	public static void searchByBrand() {
		try {
			openConnection();
			query="select * from car where brandName=?";
			preparedStatement= connection.prepareStatement(query);
			System.out.println("Enter Car Brandname You Want To Search");
			String name=scanner.nextLine();
			preparedStatement.setString(1, name);
			resultSet=preparedStatement.executeQuery();
			boolean flag=true;
			System.out.println("Following Are The Cars We Found With BrandName :"+name+"\n=======================================");
			while (resultSet.next()) {
				if (name.equalsIgnoreCase(resultSet.getString(3))) {
					flag=false;
					processQuery();
				}
			}
		
		 if(flag){
			System.out.println("=======================================\n*** No Car Found With BrandName You Entered ***\n=======================================");
		}
			
		} catch (Exception e) {
		     e.printStackTrace();
		}
		finally {
			closeConnection();
		}
	}
	
	public static void searchByFuelType() {
		try {
			openConnection();
			query="select * from car where fuelType=?";
			preparedStatement= connection.prepareStatement(query);
			System.out.println("Enter Car FuelType You Want To Search");
			String name=scanner.nextLine();
			preparedStatement.setString(1, name);
			resultSet=preparedStatement.executeQuery();
			boolean flag=true;
			System.out.println("Following Are The Cars We Found With FuelType :"+name+"\n=======================================");
			while (resultSet.next()) {
				if (name.equalsIgnoreCase(resultSet.getString(4))) {
					flag=false;
					processQuery();
				}
			}
		
		 if(flag){
			System.out.println("=======================================\n*** No Car Found With FuelType You Entered ***\n=======================================");
		}
			
		} catch (Exception e) {
		     e.printStackTrace();
		}
		finally {
			closeConnection();
		}
	}
	
	public static void searchByCarId() {
		try {
			openConnection();
			query="select * from car where carId=?";
			preparedStatement= connection.prepareStatement(query);
			System.out.println("Enter CarId You Want To Search");
			int inp=scanner.nextInt();
			scanner.nextLine();
			preparedStatement.setInt(1, inp);
			resultSet=preparedStatement.executeQuery();
			boolean flag=true;
			System.out.println("Following Are The Cars We Found With CarId :"+inp+"\n=======================================");
			while (resultSet.next()) {
				if (inp==resultSet.getInt(1)) {
					flag=false;
					processQuery();
				}
			}
		
		 if(flag){
			System.out.println("=======================================\n*** No Car Found With CarId You Entered ***\n=======================================");
		}
			
		} catch (Exception e) {
		     e.printStackTrace();
		}
		finally {
			closeConnection();
		}
	}
	
	public static void searchByPrice() {
		try {
			openConnection();
			query="select * from car where price=?";
			preparedStatement= connection.prepareStatement(query);
			System.out.println("Enter CarPrice You Want To Search");
			float inp=scanner.nextFloat();
			scanner.nextLine();
			preparedStatement.setFloat(1, inp);
			resultSet=preparedStatement.executeQuery();
			boolean flag=true;
			System.out.println("Following Are The Cars We Found With Price :"+inp+"\n=======================================");
			while (resultSet.next()) {
				if (inp==resultSet.getFloat(5)) {
					flag=false;
					processQuery();
				}
			}
		
		 if(flag){
			System.out.println("=======================================\n*** No Car Found With Price You Entered ***\n=======================================");
		}
			
		} catch (Exception e) {
		     e.printStackTrace();
		}
		finally {
			closeConnection();
		}
	}
	public static void updateCarName() {
		try {
			System.out.println("How Many Cars CarName You Want To Change");
			int amount=scanner.nextInt();
			if (amount<=carIds.size()) {
				for (int i = 0; i <amount ; i++) {
					openConnection();
					query="update car set carName=? where carId=?";
					preparedStatement=connection.prepareStatement(query);
					System.out.println("Enter CarId Of Car Who's CarName You Want To Change");
					int carId=scanner.nextInt();
					scanner.nextLine();
					if (carIds.contains(carId)) {
						preparedStatement.setInt(2,carId);
						System.out.println("Enter A New Name For Car With CarId :"+carId);
						String name= scanner.nextLine();
						preparedStatement.setString(1,name);
						result=result+preparedStatement.executeUpdate();
						System.out.println("The New Name For Car With Id :"+carId+" is: "+name);
					}
					else {
						System.out.println("Enter Valid CarId");
						i=amount;
					}
					}
				System.out.println("Query Ok , Total Row(s) Affected :"+result);
			}
			else {
				System.out.println("That Number Of Cars Not Available In Data Base");
			}
			
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			closeConnection();
		}
		
	}
	
	public static void updateBrand() {
		try {
			
			System.out.println("How Many Cars BrandName You Want To Change");
			int amount=scanner.nextInt();
			if (amount<=carIds.size()) {
				for (int i = 0; i <amount ; i++) {
				openConnection();
				query="update car set brandName=? where carId=?";
				preparedStatement=connection.prepareStatement(query);
				System.out.println("Enter CarId Of Car Who's BrandName You Want To Change");
				int carId=scanner.nextInt();
				scanner.nextLine();
				if (carIds.contains(carId)) {
					preparedStatement.setInt(2,carId);
					System.out.println("Enter A New BrandName For Car With CarId :"+carId);
					String name= scanner.nextLine();
					preparedStatement.setString(1,name);
					result=result+preparedStatement.executeUpdate();
					System.out.println("The New BrandName For Car With Id :"+carId+" is: "+name);
				}
				else {
					System.out.println("Enter Valid CarId");
					i=amount;
				}
				}
			System.out.println("Query Ok , Total Row(s) Affected :"+result);
			}
			else {
				System.out.println("That Number Of Cars Not Available In Data Base");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			closeConnection();
		}
		
	}
	
	public static void updateFuelType() {
		try {
			
			System.out.println("How Many Cars FuelType You Want To Change");
			int amount=scanner.nextInt();
			if (amount<=carIds.size()) {
				for (int i = 0; i <amount ; i++) {
				openConnection();
				query="update car set fuelType=? where carId=?";
				preparedStatement=connection.prepareStatement(query);
				System.out.println("Enter CarId Of Car Who's FuelType You Want To Change");
				int carId=scanner.nextInt();
				scanner.nextLine();
				if (carIds.contains(carId)) {
					preparedStatement.setInt(2,carId);
					System.out.println("Enter A New FuelType For Car With CarId :"+carId);
					String name= scanner.nextLine();
					preparedStatement.setString(1,name);
					result=result+preparedStatement.executeUpdate();
					System.out.println("The New FuelType For Car With Id :"+carId+" is: "+name);
				}
				else {
					System.out.println("Enter Valid CarId");
					i=amount;
				}
			}
			System.out.println("Query Ok , Total Row(s) Affected :"+result);
			}
			else {
				System.out.println("That Number Of Cars Not Available In Data Base");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			closeConnection();
		}
		
	}
	
	public static void updateId() {
		try {
			System.out.println("How Many Cars Id You Want To Change");
			int amount=scanner.nextInt();
			if (amount<=carIds.size()) {
				for (int i = 0; i <amount ; i++) {
				openConnection();
				query="update car set carId=? where carId=?";
				preparedStatement=connection.prepareStatement(query);
				System.out.println("Enter CarId Of Car Who's CarId You Want To Change");
				int carId=scanner.nextInt();
				if (carIds.contains(carId)) {
					preparedStatement.setInt(2,carId);
					System.out.println("Enter A New CarId For Car With CarId :"+carId);
					int id= scanner.nextInt();
					preparedStatement.setInt(1, id);
					result=result+preparedStatement.executeUpdate();
					System.out.println("The New Id For Car With Id :"+carId+" is: "+id);
				}
				else {
					System.out.println("Enter Valid CarId");
					i=amount;
				}
			}
			System.out.println("Query Ok , Total Row(s) Affected :"+result);
			}
			else {
				System.out.println("That Number Of Cars Not Available In Data Base");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			closeConnection();
		}
		
	}
	
	public static void updatePrice() {
		try {
			
			System.out.println("How Many Cars Price You Want To Change");
			int amount=scanner.nextInt();
			if (amount<=carIds.size()) {
				for (int i = 0; i <amount ; i++) {
				openConnection();
				query="update car set price=? where carId=?";
				preparedStatement=connection.prepareStatement(query);
				System.out.println("Enter CarId Of Car Who's Price You Want To Change");
				int carId=scanner.nextInt();
				if (carIds.contains(carId)) {
					preparedStatement.setInt(2,carId);
					System.out.println("Enter A New Price For Car With CarId :"+carId);
					float price= scanner.nextFloat();
					preparedStatement.setFloat(1, price);
					result=result+preparedStatement.executeUpdate();
					System.out.println("The New Price For Car With Id :"+carId+" is: "+price);
				}
				else {
					System.out.println("Enter Valid CarId");
					i=amount;
				}
			}
			System.out.println("Query Ok , Total Row(s) Affected :"+result);
			}
			else {
				System.out.println("That Number Of Cars Not Available In Data Base");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			closeConnection();
		}
		
	}
	
	public static void deleteCar() {
		if(isDatabaseEmpty()) {
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("Their Are No Car_Details Available In DataBase To Delete");
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			CardekhoMenu.flag5=false;
		}
		else {
		try {
			System.out.println("DO YOU WANT TO SEE ALL CAR DETAILS BEFORE DELETING THEN PRESS 1 OR TO GO TO MAINMENU PRESS 2 OR TO CONTINUE DELETE PROCESS PRESS ANY OTHER NUMBER");
			int choose= scanner.nextInt();
			if (choose==1) {
				allCarDetails();
			}
			else if (choose==2) {
				CardekhoMenu.flag5=false;
			}
			else {
				System.out.println("How Many Cars You Want To Delete");
				int amount=scanner.nextInt();
				if (amount<=carIds().size()) {
					for (int i = 0; i <amount ; i++) {
					System.out.println("Enter CarId Of Car Which You Want To Delete Or Type 'Exit' TO Stop Deleting Process");
					String carId=scanner.next();
					if(carId.equalsIgnoreCase("exit")) {
						i=amount;
					}
					else {
						openConnection();
						query="delete from car where carId=?";
						preparedStatement=connection.prepareStatement(query);
						
						int id=Integer.parseInt(carId);
							if(carIds.contains(id)) {
								preparedStatement.setInt(1,id);
								result=result+preparedStatement.executeUpdate();
								System.out.println("Car With Id :"+carId+" Has Been Deleted Successfully");
							}
							else {
								System.out.println("Id Does Not Match With The Ids In DataBase Please Enter Correct Id");
							}	
					}
				}
				System.out.println("Query Ok , Total Row(s) Affected :"+result);
				}
				else {
					System.out.println("That Number Of Cars Not Available In Data Base");
				}
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			closeConnection();
		}
		}
		
	}
	public static void allCarDetails() {
		if(carIds().size()== 0) {
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("Their Are No Car_Details Available In DataBase To Display");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		}
		else {
			System.out.println("Following Are The All The Car Details Available In DataBase :");
			System.out.println("-------------------------------------------------------------");
		try {
			openConnection();
			query="select * from car";
			preparedStatement = connection.prepareStatement(query);
			resultSet=preparedStatement.executeQuery();
			int i=1;
			while (resultSet.next()) {
				System.out.print(i++);
				processQuery();
			}
			System.out.println("Type 'Back' To Go Back TO Recent On Going Process Or Press Any Key To Exit The Application");
			if (scanner.next().equalsIgnoreCase("back")) {
//				CardekhoMenu.flag=true;s
				scanner.nextLine();
			}
			else {
				CardekhoMenu.flag=false;
				CardekhoMenu.flag4=false;
				CardekhoMenu.flag5=false;
				System.out.println("!!!!!Thank You!!!!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	}
	public static void processQuery() {
		try {
			Car car = new Car();
			car.setCarID(resultSet.getInt(1));
			car.setCarName(resultSet.getString(2));
			car.setBrandName(resultSet.getString(3));
			car.setFuelType(resultSet.getString(4));
			car.setPrice(resultSet.getFloat(5));
			System.out.println("]"+car);
			System.out.println("________________________________________________________________________");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static boolean isDatabaseEmpty() {
		boolean empty=true;
		try {
			openConnection();
			query="select * from car";
			preparedStatement=connection.prepareStatement(query);
			resultSet=preparedStatement.executeQuery();
			while (resultSet.next()) {
				empty=false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();	
		}
		return empty;
	}
	
}
