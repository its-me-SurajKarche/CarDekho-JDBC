package com.jspiders.cardekho_case_study_jdbc;

public class App {

	public static void main(String[] args) {
		try {
			System.out.println(10/5);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("finally");
		}
		System.out.println("outside");
	}
}
