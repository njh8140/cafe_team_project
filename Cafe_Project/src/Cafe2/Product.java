package Cafe2;

import java.io.Serializable;

public class Product implements Serializable {
	   private int productNo;
	    private String productName;
	    private int productPrice;
	    private int productQnt;
	    private String productMemo;
	    private boolean productStatus;

	    // 기본 생성자
	    public Product() {
	        this.productNo = 0;
	        this.productName = "Unknown";
	        this.productPrice = 0;
	        this.productQnt = 0;
	        this.productMemo = "";
	        this.productStatus = false;
	    }

	    // 매개변수가 있는 생성자
	    public Product(int productNo, String productName, int productPrice, int productQnt, String productMemo, boolean productStatus) {
	        this.productNo = productNo;
	        this.productName = productName;
	        this.productPrice = productPrice;
	        this.productQnt = productQnt;
	        this.productMemo = productMemo;
	        this.productStatus = productStatus;
	    }

	    // 정보 출력 메서드
	    public void showInfo() {
	    	if (productStatus == true) {
		        System.out.println("상품 번호: " + productNo);
		        System.out.println("상품 이름: " + productName);
		        System.out.println("상품 가격: " + productPrice);
		        System.out.println("상품 수량: " + productQnt);
		        System.out.println("상품 메모: " + productMemo);
	    	} 
	   }
	    
	    public int  getProductNo() {
	    	return productNo;
	    }
	    
		public String getProductName() {
			return productName;
		}
		
		public int getProductQnt() {
			return productQnt;
		}
		
		public int getProductPrice() {
			return productPrice;
		}
		
		public String getProductMemo() {
			return productMemo;
		}
		
		public boolean getProductStatus() {
			return productStatus;
		}
		
		@Override
		public int hashCode() {
			String productNo = this.productNo + "";
			return productNo.hashCode();
			//return Objects.hashCode(name);
		}

		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Product) {
				Product tmp = (Product)obj;
				
				if(productNo == tmp.productNo) {
					return true;
				}else {
					return false;
				}
			} 
			return false;
		}
	
}
