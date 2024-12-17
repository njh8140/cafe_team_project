package Cafe2;

import java.nio.file.spi.FileSystemProvider;
import java.util.Scanner;

public class CafendVer2 {
    public static void main(String[] args) {
        // CafeManager 인스턴스 생성
        CafeManager manager = new CafeManager();

        int choice; // 사용자 선택값 저장

        while (true) {
            // 메뉴 출력
            MenuViewer.showMenu();
            try {
                // 사용자 입력 처리
                System.out.print("선택: ");
                choice = Integer.parseInt(MenuViewer.sc.nextLine());

                switch (choice) {
                    case 1:
                        // 상품 관리
                        productManagement(manager);
                        break;

                    case 2:
                        // 주문 관리
                    	orderManagement(manager);
                        break;

                    case 3:
                    	// 정산 관리
                    	salesManagement(manager);
                    	break;
                    	
                    case 4:
                        // 프로그램 종료
                    	manager.saveProduct();
                    	manager.saveOrder();
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    

                    default:
                        // 잘못된 입력
                        System.out.println("잘못된 입력입니다. 1~4 사이의 숫자를 입력해주세요.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요.");
            } catch (Exception e) {
            	System.out.println("error!!@!@!");
            	System.out.println(e.getMessage());
            }
        }
    }

    // 상품 관리 기능
    private static void productManagement(CafeManager manager) {
        int subChoice;

        while (true) {
        	System.out.println("===================================");
            System.out.println("============== 상품 관리 =============");
            System.out.println("===================================");
            System.out.println("1. 상품 추가");
            System.out.println("2. 상품 검색");
            System.out.println("3. 상품 삭제");
            System.out.println("4. 상품 수정");
            System.out.println("5. 모든 상품 출력");
            System.out.println("6. 이전 메뉴로 돌아가기");
            System.out.print("선택: ");

            try {
                subChoice = Integer.parseInt(MenuViewer.sc.nextLine());

                switch (subChoice) {
                    case 1:
                    	// 상품 추가
                        manager.inputProduct();
                        break;

                    case 2:
                    	// 상품 검색
                        manager.searchProduct();
                        break;

                    case 3:
                    	// 상품 삭제
                        manager.deleteProduct();
                        break;

                    case 4:
                    	// 상품 수정
                        manager.updateProduct();
                        break;

                    case 5:
                    	// 모든 상품 출력
                        manager.printAllProduct();
                        break;

                    case 6:
                    	// 이전 메뉴로 돌아가기
                        System.out.println("이전 메뉴로 돌아갑니다.");
                        return;

                    default:
                        System.out.println("잘못된 입력입니다. 1~6 사이의 숫자를 입력해주세요.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요.");
            }
        }
    }
    
    private static void orderManagement(CafeManager manager) {
        int subChoice;

        while (true) {        	
        	System.out.println("===================================");
            System.out.println("============== 주문 관리 =============");
            System.out.println("===================================");
            System.out.println("1. 주문 추가");
            System.out.println("2. 주문 삭제");
            System.out.println("3. 주문 수정");
            System.out.println("4. 모든 주문 출력");
            System.out.println("5. 이전 메뉴로 돌아가기");
            System.out.println("6. 임의 주문내역 추가");
            System.out.print("선택: ");

            try {
                subChoice = Integer.parseInt(MenuViewer.sc.nextLine());

                switch (subChoice) {
                    case 1:
                    	// 상품 추가
                        manager.inputOrder();
                        break;

                    case 2:
                    	// 상품 삭제
                        manager.deleteOrder();
                        break;

                    case 3:
                    	// 상품 수정
                        manager.updateOrder();
                        break;

                    case 4:
                    	// 모든 상품 출력
                        manager.printAllOrder();
                        break;

                    case 5:
                    	// 이전 메뉴로 돌아가기
                        System.out.println("이전 메뉴로 돌아갑니다.");
                        return;
                       
                    case 6:
                    	// 임시 주문 데이터 생성
                    	for(int i=0; i<30; i++) 
                    		manager.orderProcedure();
                        System.out.println("이전 메뉴로 돌아갑니다.");
                        break;
                    default:
                        System.out.println("잘못된 입력입니다. 1~6 사이의 숫자를 입력해주세요.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요.");
            }
        }
    }

    private static void salesManagement(CafeManager manager) {
    	manager.salesManagement();
        int subChoice;

        while (true) {
        	System.out.println("===================================");
            System.out.println("============== 정산 관리 =============");
            System.out.println("===================================");
            System.out.println("1. 일별 정산 조회");
            System.out.println("2. 월별 정산 조회");
            System.out.println("3. 일년 정산 조회");
            System.out.println("4. 이전 메뉴로 돌아가기");
            System.out.print("선택: ");

            try {
                subChoice = Integer.parseInt(MenuViewer.sc.nextLine());

                switch (subChoice) {
                    case 1:
                    	// 일별 정산 조회
                        manager.dailySales();
                        break;

                    case 2:
                    	// 월별 정산 조회
                        manager.monthSales();
                        break;

                    case 3:
                    	// 일년 정산 조회
                        manager.yearSales();
                        break;

                    case 4:
                    	// 이전 메뉴로 돌아가기
                        System.out.println("이전 메뉴로 돌아갑니다.");
                        return;                        

                    default:
                        System.out.println("잘못된 입력입니다. 1~4 사이의 숫자를 입력해주세요.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요.");
            }
        }
    }

}
