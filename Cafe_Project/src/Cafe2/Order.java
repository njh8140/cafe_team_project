package Cafe2;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order implements Serializable {
    private int orderNo;          // 주문 번호
    private int productNo;		  // 상품 번호
    private int orderQnt;         // 주문 수량
    private int orderPrice;       // 총 주문 가격
    private Date orderDate;       // 주문 날짜
    private int orderPayment;  // 결제 방식

    // 생성자
    public Order(int orderNo, int productNo, int orderQnt, int orderPrice, Date orderDate, int orderPayment) {
        this.orderNo = orderNo;
        this.productNo = productNo;
        this.orderQnt = orderQnt;
        this.orderPrice = orderPrice;
        this.orderDate = orderDate;
        this.orderPayment = orderPayment;
    }

    // Getter and Setter
    public int getOrderNo() { return orderNo; }
    public int getOrderQnt() { return orderQnt; }
    public int getOrderPrice() { return orderPrice; }
    public Date getOrderDate() { return orderDate; }
    public int getOrderPayment() { return orderPayment; }
    public int getProductNo() {return productNo;}

    // 주문 정보 출력
    public void showInfo() {
        System.out.println("주문 번호: " + orderNo);
        System.out.println("상품 번호: " + productNo);
        System.out.println("주문 수량: " + orderQnt);
        System.out.println("총 주문 가격: " + orderPrice);
       
//        System.out.println("다듬기 전 : " + orderDate);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatedNow = dateFormat.format(orderDate);
        System.out.println("주문 날짜: " + formatedNow);
        
        String howtopay = null;
        if (orderPayment == 1) {
        	howtopay = "카드";
        }else if (orderPayment == 2) {
        	howtopay = "네이버페이";
        }else if (orderPayment == 3) {
        	howtopay = "카카오페이";
        }else if (orderPayment == 4) {
        	howtopay = "삼성페이";
        }else {
        	howtopay = "현금";
        }
        System.out.println("결제 방식: " + howtopay);
       // System.out.println("1. 카드, 2. 네이버페이, 3. 카카오페이, 4. 삼성페이, 5.현금");
    }

    // Equals 및 HashCode 메서드 (Set에 저장 시 중복 방지)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Order order = (Order) obj;
        return orderNo == order.orderNo;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(orderNo);
    }
}
