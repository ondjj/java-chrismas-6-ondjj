package christmas.view;

import static christmas.util.Constants.DASH;
import static christmas.util.Constants.ZERO;

import christmas.dto.OrderItemDTO;
import christmas.util.Parser;
import java.util.Map;

public class OutputView {
    private static final String FREE_VIEW = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU = "<주문 메뉴>";
    private static final String BEFORE_TOTAL_PRICE = "<할인 전 총주문 금액>";
    private static final String PRESENT_MENU = "<증정 메뉴>";
    private static final String DISCOUNT_LIST = "<혜택 내역>";
    private static final String DISCOUNT_TOTAL_PRICE = "<총혜택 금액>";
    private static final String LAST_ORDER_PRICE = "<할인 후 예상 결제 금액>";
    private static final String BADGE = "<12월 이벤트 배지>";
    private static final String WON = "원";
    private static final String COUNT = "개";
    private static final String SPACE = " ";
    private static final char LINE = '\n';


    public void printFreeView(Integer day) {
        System.out.println(formatDay(day));
    }

    private static String formatDay(Integer day) {
        return String.format(FREE_VIEW, day);
    }

    public void printOrder(final OrderItemDTO orderItemDTO) {
        System.out.println(LINE + ORDER_MENU);
        Map<String, Integer> item = orderItemDTO.getItem();
        for (Map.Entry<String, Integer> entry : item.entrySet()) {
            System.out.println(entry.getKey() + SPACE + entry.getValue() + COUNT);
        }
    }

    public void printBeforePrice(final Integer totalPrice) {
        String decimalPrice = Parser.decimalFormatter(totalPrice);
        System.out.println(LINE + BEFORE_TOTAL_PRICE);
        System.out.println(decimalPrice + WON);
    }

    public void printPresent(final String present) {
        System.out.println(LINE + PRESENT_MENU);
        System.out.println(present);
    }

    public void printBenefit(StringBuilder discountList) {
        System.out.println(LINE + DISCOUNT_LIST);
        System.out.println(discountList.toString());
    }

    public void printTotalBenefit(final Integer totalBenefit) {
        System.out.println(LINE + DISCOUNT_TOTAL_PRICE);
        String formattedBenefit = Parser.decimalFormatter(totalBenefit) + WON;
        formattedBenefit = getDash(totalBenefit, formattedBenefit);
        System.out.println(formattedBenefit);
    }

    private static String getDash(final Integer totalBenefit, String formattedBenefit) {
        if (totalBenefit != ZERO) {
            formattedBenefit = DASH + formattedBenefit;
        }
        return formattedBenefit;
    }

    public void printLastOrderPrice(final Integer freeView) {
        System.out.println(LINE + LAST_ORDER_PRICE);
        System.out.println(Parser.decimalFormatter(freeView) + WON);
    }

    public void printBadge(String badge) {
        System.out.println(LINE + BADGE);
        System.out.println(badge);
    }
}
