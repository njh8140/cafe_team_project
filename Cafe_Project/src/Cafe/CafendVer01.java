package Cafe;

public class CafendVer01 {
    public static void main(String[] args) {
        // CafeManager 인스턴스 생성
        CafeManager manager = new CafeManager();

        // 상품 추가
        manager.inputProduct();
        
        // 상품 검색
        manager.searchProduct(); 
        
        // 상품 삭제
        manager.deleteProduct();
        
        // 상품 수정
        manager.updateProduct();

        // 모든 상품 출력
        manager.showAllProduct();
        
        
        // 
    }
}