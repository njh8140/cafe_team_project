package Cafe;

import java.util.Date;

public class Order {
    private int orderNo;          // 주문 번호
    private int productNo;		  // 상품 번호
    private int orderQnt;         // 주문 수량
    private int orderPrice;       // 총 주문 가격
    private Date orderDate;       // 주문 날짜
    private String orderPayment;  // 결제 방식

    // 생성자
    public Order(int orderNo, int productNo, int orderQnt, int orderPrice, Date orderDate, String orderPayment) {
        this.orderNo = orderNo;
        this.productNo = productNo;
        this.orderQnt = orderQnt;
        this.orderPrice = orderPrice;
        this.orderDate = orderDate;
        this.orderPayment = orderPayment;
    }

    // Getter and Setter
    //public int getOrderNo() { return orderNo; }
    //public int getOrderQnt() { return orderQnt; }
    //public int getOrderPrice() { return orderPrice; }
    //public Date getOrderDate() { return orderDate; }
    //public String getOrderPayment() { return orderPayment; }

    // 주문 정보 출력
    public void showInfo() {
        System.out.println("주문 번호: " + orderNo);
        System.out.println("상품 번호: " + productNo);
        System.out.println("주문 수량: " + orderQnt);
        System.out.println("총 주문 가격: " + orderPrice);
        System.out.println("주문 날짜: " + orderDate);
        System.out.println("결제 방식: " + orderPayment);
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
