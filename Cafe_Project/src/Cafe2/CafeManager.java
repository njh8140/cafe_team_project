package Cafe2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


public class CafeManager {
	
	static Scanner sc = new Scanner(System.in);
	 //상품 정보 저장
	 private Set<Product> productStorage = new HashSet<Product>();
	 private final File productDataFile = new File("product.dat");
	 //주문 정보 저장
	 private Set<Order> orderStorage = new HashSet<Order>();
	 private final File orderDataFile = new File("order.dat");

   // 기본 생성자
   public CafeManager() {
   	//파일에 저장한 정보 불러오기
       readProduct();
       readOrder();
   }


   // 상품 추가 메서드
   public void inputProduct() {
   	//콘솔에서 입력한 데이터를 변수에 저장
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
		
		//입력받은 데이터를 productStorage에 저장
		boolean isInput = inputProductStorage(productNo, productName, 
				productPrice, productQnt, productMemo, productStatus);
		
		if(isInput) {
			System.out.println("데이터 입력 완료");
		} else {
			System.out.println("데이터 입력 실패");
		}
   }
   
   //상품 등록에 필요한 데이터를 인수로 전달받아 productStorage에 저장하는 메서드
   private boolean inputProductStorage(int productNo, String productName, int productPrice, int productQnt, String productMemo, boolean productStatus) {
   	boolean isInput = false;
   	
   	Product result = new Product(productNo, productName, 
				productPrice, productQnt, productMemo, productStatus);
		
   	if(productStorage.add(result)) {
   		isInput = true;
		}
   	
   	return isInput;
   }
   

	// 상품 검색 메서드
		public void searchProduct() {
			//상품 전체 출력
			printAllProduct();
			
			//검색할 상품 번호를 콘솔에서 입력받음
			System.out.print("검색할 상품 번호 입력 ==>");
			int productNo = Integer.parseInt(sc.nextLine());
			
			//상품 번호를 인수로 전달해 검색한 데이터를 리턴하는 메서드 호출
			Product result = searchProductStorage(productNo);
			
			if(result != null) {
				result.showInfo();
			} else {
				System.out.println("해당 정보 없음!");
			}
	    
	    }
		
	//상품 번호를 인수로 전달받아 검색한 데이터를 리턴하는 메서드
		private Product searchProductStorage(int productNo) {
	    	Product result = null;
	    	
	    	Iterator<Product> it = productStorage.iterator();
			while(it.hasNext()) {
				Product tmp = it.next();
				if(tmp.getProductNo() == productNo) {
					result = tmp;
					
				}
			}
			
			return result;
	    }	

    // 상품 전체 출력 메서드
    public void printAllProduct() {
        System.out.println("=== 상품 목록 ===");
        for (Product product : productStorage) {
            product.showInfo();
            System.out.println("--------------------");
        }
       
      
    }
    
    //상품 삭제 메서드
    public void deleteProduct() {
    	//상품 전체 출력
		printAllProduct();
		
		//삭제할 상품 번호를 콘솔에서 입력받음
		System.out.print("삭제할 상품 번호 입력 ==>");
		int productNo = Integer.parseInt(sc.nextLine());
	
		//상품 번호를 인수로 전달받아 productStorage에서 해당 상품 번호의 데이터를 삭제하여 삭제 결과를 리턴하는 메서드를 호출
		boolean isRemove = deleteProductStorage(productNo);
		
		//삭제 결과 메세지 출력
		if(!isRemove) {
			System.out.println("삭제할 정보없음!");
		} else {
			System.out.println("삭제 완료");
		}
    }
    
    //상품 번호를 인수로 전달받아 productStorage에서 해당 상품 번호의 데이터를 삭제하여 삭제 결과를 리턴하는 메서드
    private boolean deleteProductStorage(int productNo) {
    	Iterator<Product> it = productStorage.iterator();
		boolean isRemove = false;
		while(it.hasNext()) {
			Product tmp = it.next();
			if(productNo == tmp.getProductNo()) {
				it.remove();
				isRemove = true;
				break;
			}
		}
		
		return isRemove;
    }
    
    //상품 수정 메서드
    public void updateProduct() {
    	//상품 전체 출력
		printAllProduct();
		
		//수정할 상품 번호를 콘솔에서 입력받음
		System.out.print("수정할 상품 번호 입력 ==>");
		int productNo = Integer.parseInt(sc.nextLine());
		
		//상품 번호를 인수로 전달해 검색한 데이터를 리턴하는 메서드 호출
		Product result = searchProductStorage(productNo);
		
		if(result != null) {
			//수정할 상품 정보 출력
			result.showInfo();
			//기존 상품명
			//String productName = result.getProductName();
			
			//수정할 상품 정보 콘솔에서 입력받아 저장
			System.out.print("상품명을 입력하세요==>");
			String productName  = sc.nextLine();
			System.out.print("상품가격을 입력하세요==>");
			int productPrice = Integer.parseInt(sc.nextLine());
			System.out.print("상품 수량==>");
			int productQnt = Integer.parseInt(sc.nextLine());
			System.out.print("상품 설명==>");
			String productMemo  = sc.nextLine();
			boolean productStatus = true;
			
			//수정할 상품정보가 이상 없으면 기존 상품 데이터 삭제 진행
			//상품 번호를 인수로 전달받아 productStorage에서 해당 상품 번호의 데이터를 삭제하여 삭제 결과를 리턴하는 메서드를 호출
			boolean isRemove = deleteProductStorage(productNo);
			
			//기존 상품 데이터가 정상적으로 삭제되었으면 상품 데이터 저장 진행
			if(isRemove) {
				//입력받은 데이터를 productStorage에 저장
				boolean isInput = inputProductStorage(productNo, productName, 
						productPrice, productQnt, productMemo, productStatus);
				
				if(isInput) {
					System.out.println("데이터 수정 완료");
				} else {
					System.out.println("데이터 수정 실패");
				}
			}
		} else {
			System.out.println("해당 정보 없음!");
		}
    }
    
    //주문 등록 메서드
    public void inputOrder() {
    	printAllProduct();
    	//상품 번호를 콘솔에서 입력받아 변수에 저장
        System.out.print("상품번호를 입력하세요==>");
		int productNo = Integer.parseInt(sc.nextLine());
		
		boolean resultOrder = inputOrderStorage(productNo);
		
		if(resultOrder) System.out.println("주문 완료");
    }
    
    //주문할 상품 번호를 인수로 전달받아 주문 등록을 진행 후 주문 등록이 정상적으로 되었는 지 여부를 리턴하는 메서드
    private boolean inputOrderStorage(int productNo) {
    	boolean resultOrder = false;
    	
    	Order temp = null;
    	//입력받은 상품 번호에 해당되는 상품 정보 리턴받음
		Product result = searchProductStorage(productNo);
		
		//상품 정보가 있으면 다음 과정 실행
		if(result != null) {
			//주문수량을 콘솔에서 입력받아 변수에 저장
			System.out.print("주문수량을 입력하세요==>");
			int orderQnt = Integer.parseInt(sc.nextLine());
			
			//주문할 상품의 현재 재고량이 주문량에 비해 크거나 같은지 확인
			if(result.getProductQnt() >= orderQnt) {
				//주문 정보 콘솔에서 입력받아 변수에 저장
				System.out.println("결제방식을 입력하세요");
				System.out.println("1. 카드, 2. 네이버페이, 3. 카카오페이, 4. 삼성페이, 5.현금");
				System.out.print("==>");
				int orderPayment = Integer.parseInt(sc.nextLine());
				//주문번호 자동으로 부여(수정 필요)
				int orderNo = orderStorage.size() + 1;	//배열길이 + 1
				//수정될 재고량 = 현재 상품 재고량 - 주문수량
				int updateQnt = result.getProductQnt() - orderQnt;
			
				//현재 시간 변수에 저장
				Date orderDate = new Date();
				//주문가격 계산
				int orderPrice = result.getProductPrice() * orderQnt;
				
				temp = new Order(orderNo, productNo, orderQnt, orderPrice, orderDate, 
						orderPayment);
			
				if(orderStorage.add(temp)) {
					//기존 상품 정보 저장
					String productName = result.getProductName();
					int productPrice = result.getProductPrice();
					String productMemo = result.getProductMemo();
					boolean productStatus = result.getProductStatus();
					
					//productStorage에서 상품 정보를 삭제하는 메서드를 호출해서 삭제가 정상적으로 진행되었는지를 리턴 받음
					boolean isRemove = deleteProductStorage(productNo);
					
					if(isRemove) {
						boolean isInput = inputProductStorage(productNo, productName, 
								productPrice, updateQnt, productMemo, productStatus);
						
						if(isInput) {
							resultOrder = true;
						} else {
							System.out.println("재고 수정 실패");
						}
						
					} else {
						System.out.println("삭제할 정보없음!");
					}
					
				} else {
					System.out.println("주문 등록 실패");
				}
			} else {
				System.out.println("상품 수량이 부족합니다.");
			}
		} else {
			System.out.println("입력한 상품번호는 존재하지 않습니다.");
		}
		
		return resultOrder;
    }
    
  //주문 정보 전체 출력 메서드
    public void printAllOrder() {
        System.out.println("=== 주문 목록 ===");
        for (Order order : orderStorage) {
            order.showInfo();
            System.out.println("--------------------");
        }
       
    }
	
    //파일에 상품 정보를 저장하는 메서드
    public void saveProduct() throws Exception {
		FileOutputStream fos = new FileOutputStream(productDataFile);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(productStorage);
		oos.flush();
		oos.close();
	}
    
    //파일에 저장되어 있는 상품 정보를 불러오는 하는 메서드
	public void readProduct(){
		if(!productDataFile.exists()) {
			return;
		}
		
		try {
			FileInputStream fis = new FileInputStream(productDataFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			productStorage =  (HashSet<Product>)ois.readObject();
		}catch(Exception e) {
			System.out.println("파일 읽기 오류");
			System.out.println(e.getMessage());
		}
	}
	
	//파일에 주문 정보를 저장하는 메서드
    public void saveOrder() throws Exception {
		FileOutputStream fos = new FileOutputStream(orderDataFile);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(orderStorage);
		oos.flush();
		oos.close();
	}
    
    //파일에 저장되어 있는 주문 정보를 불러오는 하는 메서드
	public void readOrder(){
		if(!productDataFile.exists()) {
			return;
		}
		
		try {
			FileInputStream fis = new FileInputStream(orderDataFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			orderStorage =  (HashSet<Order>)ois.readObject();
		}catch(Exception e) {
			System.out.println("파일 읽기 오류");
			System.out.println(e.getMessage());
		}
	}
    
	//주문 정보 삭제하는 메서드
    public void deleteOrder() {
    	//주문 정보 전체 출력
    	printAllOrder();
    	
    	//콘솔에서 입력한 정보를 입력받아 변수에 저장
		System.out.print("삭제할 주문 번호입력 ==>");
		int orderNo = Integer.parseInt(sc.nextLine());
		System.out.print("상품 재고에 반영하시겠습니까?(y/n) ==>");
		String orderUpdate = sc.nextLine();
			
		int resultOrder = deleteOrderStorage(orderUpdate, orderNo);
		
		//삭제가 되었는 지 안 되었는지 분기
		if(resultOrder > 0) {
			System.out.println("삭제 완료");
		} else {
			System.out.println("삭제할 정보 없음");
		}
		
    }
    
    //상품 재고에 삭제할 주문 수량 정보를 반영할 건지(y 인지 아닌지)와 주문 번호를 인수로 전달 받아 주문 정보를 orderStorage에서 삭제하는 메서드
    //삭제가 정상적으로 진행되었는 지 여부를 리턴하는 메서드
    public int deleteOrderStorage(String orderUpdate, int orderNo) {
    	//삭제 과정이 정상적으로 진행되지 않을 경우 다시 0으로 초기화
    	int productNo = 0;
    	
    	//orderStorage : 주문정보가 저장되어 있음
		//orderStorage가 Set 형태로 저장이 되어 있음.
		//orderStorage에 저장된 주문 정보를 하나씩 조회하기 위해 Iterator에 저장
		Iterator<Order> it = orderStorage.iterator();
		
		//배열에서 삭제가 되었는지 안되었는지 확인하기 위해서 선언
		boolean isRemove = false;
		
		while(it.hasNext()) {
			//OrderStorage에 저장된 값들 중 하나의 덩어리를 tmp값에 저장
			Order tmp = it.next();
			
			//입력받은 주문번호가 orderStorage 내부에 저장되어 있는지 확인
			//확인해서 주문번호가 있으면 삭제과정을 진행
			if(orderNo == tmp.getOrderNo()) {
				//상품 재고에 삭제한 값 반영
				if(orderUpdate.equals("y")) {
					//기존에 저장되어 있던 주문정보에서 주문수량, 상품번호 받아오기
					int orderQnt = tmp.getOrderQnt(); 
					productNo = tmp.getProductNo();
					
					Product result = searchProductStorage(productNo);
					if(result != null) {
						String productName = result.getProductName();
						int productPrice = result.getProductPrice();
						int productQnt= result.getProductQnt();
						String productMemo = result.getProductMemo();
						boolean productStatus = result.getProductStatus();
						int updateQnt = productQnt + orderQnt;
						
						//기존 상품 정보 삭제
						deleteProductStorage(productNo);
						
						//상품 정보 갱신
						boolean isInput = inputProductStorage(productNo, productName, productPrice, updateQnt, productMemo, productStatus);
						
						//상품 정보가 정상적으로 저장되었는 지 확인
						/*
						if(isInput) {
							System.out.println("데이터 입력 완료");
						} else {
							System.out.println("데이터 입력 실패");
						}	
						*/
					} else {
						productNo = 0;
					}
				} else {
					//정상적으로 삭제가 진행되었다는 것을 리턴시키기 위해 0보다 큰 임의의 값 저장
					productNo = 999999;
				}
				
				//주문번호가 있을 경우 it.remove() 라는 메소드를 통해서 삭제를 진행
				it.remove();
				break;
			}
		}
		
		return productNo;
    }
    
    //주문 정보 수정하는 메서드
    public void updateOrder() {
    	boolean resultOrder = false;
    	
    	//주문 정보 전체 출력
    	printAllOrder();
    	
    	//콘솔에서 입력한 정보를 입력받아 변수에 저장
		System.out.print("삭제할 주문 번호입력 ==>");
		int orderNo = Integer.parseInt(sc.nextLine());
		String orderUpdate = "y";
		
    	int productNo = deleteOrderStorage(orderUpdate, orderNo);	// 주문 삭제
    	if(productNo > 0) resultOrder = inputOrderStorage(productNo);	//정상적으로 주문이 삭제되었다면 주문 갱신(입력받은 주문 번호 이용)
    	
    	if(resultOrder) {
    		System.out.println("주문 수정 완료");
    	} else {
    		System.out.println("주문 수정 실패");
    	}
    }    
    public void orderProcedure() {
    	int orderNo = orderStorage.size() + 1;	//배열길이 + 1
    	int productNo = (int)(Math.random() * 2) + 1;	//1 또는 2
    	int productPrice = 0;
    	int orderQnt = (int)(Math.random() * 10) + 1;	//1~10 사이 랜덤
    	if(productNo == 1) productPrice = 2000;	// 아메리카노 가격
    	else productPrice = 3000;		//카페라떼 가격
    	
    	int orderPrice = productPrice * orderQnt;	//주문가격 계산
    	Date orderDate = null;
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.MONTH, + (int)(Math.random() * 12) + 1);	//현재 날짜 기준 1~12달 사이 랜덤 변경
    	Date currentTime=cal.getTime();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String release_Dt_start = formatter.format(currentTime);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
        	orderDate = sdf.parse(release_Dt_start);
			
		} catch (ParseException e) {
			e.getMessage();
		}
        
    	int orderPayment = (int)(Math.random() * 5) + 1;
    	
    	orderStorage.add(new Order(orderNo, productNo, orderQnt, orderPrice, orderDate, orderPayment));    	
    }
    
    public void salesManagement() {
//    	printAllOrder();
    }
    
    public void dailySales() {
        printAllOrder();
        System.out.print("정산할 날짜 입력 (ex.2024-11-30) ==> ");
        String day = sc.nextLine();

        // 날짜를 문자열로 포맷팅하기 위한 SimpleDateFormat 객체
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Order result = null;
        int sum = 0;
        
        Iterator<Order> it = orderStorage.iterator();
        while (it.hasNext()) {
            Order tmp = it.next();
            // OrderDate를 String으로 변환
            String date = dateFormat.format(tmp.getOrderDate());
            if (day.equals(date)) { // 입력한 날짜와 변환된 날짜를 비교
                result = tmp;
                int sumPrice = tmp.getOrderPrice();
                sum = sum + sumPrice;
//                break;
            }
        }
		if(result != null) {
            System.out.println(day + " 정산 금액 : " + sum);
//		result.showInfo();        
		}
		
    }
    
    public void monthSales() {
    	printAllOrder();
        System.out.print("정산할 년월 입력 (ex.2024-11) ==> ");
        String day = sc.nextLine();

        // 날짜를 문자열로 포맷팅하기 위한 SimpleDateFormat 객체
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Order result = null;
        int sum = 0;

        Iterator<Order> it = orderStorage.iterator();
        while (it.hasNext()) {
            Order tmp = it.next();
            // OrderDate를 String으로 변환
            String date = dateFormat.format(tmp.getOrderDate());
            if (day.equals(date)) { // 입력한 날짜와 변환된 날짜를 비교
                result = tmp;
                int sumPrice = tmp.getOrderPrice();
                sum = sum + sumPrice;
//                break;
            }
        }
		if(result != null) {
			System.out.println(day + "월 정산 금액 : " + sum);
//		result.showInfo();        
		}
		
    }
    	
    
    public void yearSales() {
    	printAllOrder();
    	 System.out.print("정산할 년도 입력 (ex.2024) ==> ");
         String day = sc.nextLine();

         // 날짜를 문자열로 포맷팅하기 위한 SimpleDateFormat 객체
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
         Order result = null;
         int sum = 0;

         Iterator<Order> it = orderStorage.iterator();
         while (it.hasNext()) {
             Order tmp = it.next();
             // OrderDate를 String으로 변환
             String date = dateFormat.format(tmp.getOrderDate());
             if (day.equals(date)) { // 입력한 날짜와 변환된 날짜를 비교
                 result = tmp;
                 int sumPrice = tmp.getOrderPrice();
                 sum = sum + sumPrice;
//                 break;
             }
         }
 		if(result != null) {
 			System.out.println(day + "년 정산 금액 : " + sum);
// 		result.showInfo();        
 		}
 		
     }
    }