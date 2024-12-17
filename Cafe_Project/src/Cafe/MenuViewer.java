package Cafe;

import java.util.Scanner;

public class MenuViewer {
	static Scanner sc = new Scanner(System.in);
	
	public static void showMenu() {
		System.out.println("=====================================");
		System.out.println("=========== 카페 전산 시스템 ===========");
		System.out.println("=====================================");
		System.out.println("");
		System.out.println("=========== 모드를 선택하세요 ===========");
		System.out.println("");
		System.out.println("1. 상품 관리");
		System.out.println("2. 주문 관리");
		System.out.println("3. 프로그램 종료");
	}
}
