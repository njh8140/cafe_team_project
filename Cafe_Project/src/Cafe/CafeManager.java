package Cafe;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class CafeManager {
	 static Scanner sc = new Scanner(System.in);
	 private Set<Product> productStorage;

	    // 기본 생성자
	    public CafeManager() {
	        productStorage = new HashSet<Product>();
	        
	    }

	    // 상품 추가 메서드
	    public void inputProduct() {
	        System.out.print("상품명을 입력하세요==>");
			String productName = sc.nextLine();
			System.out.print("상품가격을 입력하세요==>");
			int productPrice = Integer.parseInt(sc.nextLine());
			System.out.print("상품 수량==>");
			int productQnt = Integer.parseInt(sc.nextLine());
			System.out.print("상품 설명==>");
			String productMemo  = sc.nextLine();
			boolean productStatus = true;
			int productNo = productStorage.size() + 1;	//배열길이 + 1
			
			Product temp = null;
			temp = new Product(productNo, productName, 
					productPrice, productQnt, productMemo, productStatus);
			
			if(productStorage.add(temp)) {
				System.out.println("데이터입력완료");
			} else {
				System.out.println("데이터입력실패");
			}
	    }
		public void searchProduct() { 
			System.out.print("검색할 이름입력 ==>");
			String productName = sc.nextLine();
			Product result = null;
			
			Iterator<Product> it = productStorage.iterator();
			while(it.hasNext()) {
				Product tmp = it.next();
				if(productName.equals(tmp.getProductName())) {
					result = tmp;
					break;
				}
			}
			
			if(result != null) {
				result.showInfo();
			} else {
				System.out.println("해당 정보 없음!");
			}
        
	    }

	    // 상품 전체 출력 메서드
	    public void showAllProduct() {
	        System.out.println("=== 상품 목록 ===");
	        for (Product product : productStorage) {
	            product.showInfo();
	            System.out.println("--------------------");
	        }
	       
	      
	    }
	    
	    public void deleteProduct() {
			System.out.print("삭제할 상품 이름입력 ==>");
			String productName = sc.nextLine();
		
			Iterator<Product> it = productStorage.iterator();
			boolean isRemove = false;
			while(it.hasNext()) {
				Product tmp = it.next();
				if(productName.equals(tmp.getProductName())) {
					it.remove();
					isRemove = true;
					System.out.println("삭제완료");
					break;
				}
			}
			if(!isRemove) {
				System.out.println("삭제할 정보없음!");
			}
	  
	    }
	    public void updateProduct() {
	    	System.out.print("수정할 상품 이름입력 ==>");
			String productName = sc.nextLine();
			Product result = null;
			
			Iterator<Product> it = productStorage.iterator();
			while(it.hasNext()) {
				Product tmp = it.next();
				if(productName.equals(tmp.getProductName())) {
					result = tmp;
					break;
				}
			}
			
			if(result != null) {
				result.showInfo();
				int productNo = result.getProductNo();
				productName = result.getProductName();
				
				it = productStorage.iterator();
				boolean isRemove = false;
				while(it.hasNext()) {
					Product tmp = it.next();
					if(productName.equals(tmp.getProductName())) {
						it.remove();
						isRemove = true;
						System.out.println("삭제완료");
						break;
					}
				}
				if(!isRemove) {
					System.out.println("삭제할 정보없음!");
				}else {
					System.out.print("상품가격을 입력하세요==>");
					int productPrice = Integer.parseInt(sc.nextLine());
					System.out.print("상품 수량==>");
					int productQnt = Integer.parseInt(sc.nextLine());
					System.out.print("상품 설명==>");
					String productMemo  = sc.nextLine();
					boolean productStatus = true;
					Product temp = null;
					temp = new Product(productNo, productName, 
							productPrice, productQnt, productMemo, productStatus);
					
					if(productStorage.add(temp)) {
						System.out.println("데이터입력완료");
					} else {
						System.out.println("데이터입력실패");
					}
				}
				
			} else {
				System.out.println("해당 정보 없음!");
				
			}
	    }
	
				
				
			
	}
	
