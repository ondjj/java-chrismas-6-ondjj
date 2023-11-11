package christmas.view;

import christmas.dto.OrderItemDTO;
import java.util.Map;

public class OutputView {
    private static final String FREE_VIEW = "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU = "<주문 메뉴>";
    private static final String TOTAL_PRICE = "<할인 전 총주문 금액>";
    private static final String PRESENT_MENU = "<증정 메뉴>";
    private static final String DISCOUNT_LIST = "<혜택 내역>";
    private static final String DISCOUNT_TOTAL_PRICE = "<총혜택 금액>";
    private static final String LAST_ORDER_PRICE = "<할인 후 예상 결제 금액>";
    private static final String BADGE = "<12월 이벤트 배지>";

    private static final char LINE = '\n';

    public void printMenu() {
        System.out.println(ORDER_MENU);
    }

    public void printFreeView() {
        System.out.println(FREE_VIEW);
    }

    public void printOrder(final OrderItemDTO orderItemDTO) {
        System.out.println(LINE + ORDER_MENU);
        Map<String, Integer> item = orderItemDTO.getItem();
        for (Map.Entry<String, Integer> entry : item.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue() + "개");
        }
    }
}
