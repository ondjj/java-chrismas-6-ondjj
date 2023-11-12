package christmas.controller;

import christmas.dto.OrderItemDTO;
import christmas.model.EventBadge;
import christmas.model.EventGroup;
import christmas.model.EventGroupFacade;
import christmas.model.EventManager;
import christmas.model.Order;
import christmas.model.OrderItem;
import christmas.model.VisitDate;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
    private static final boolean TRUE = true;
    private static final boolean FALSE = false;

    private final InputView inputView;
    private final OutputView outputView;

    boolean validInput = FALSE;

    public ChristmasController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        validInput = FALSE;
        inputView.welcome();
        while (!validInput) {
            try {
                VisitDate visitDate = inputView.readDate();
                outputView.printFreeView(visitDate.getDate());
                validInput = TRUE;
                setOrderItem(visitDate);
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void setOrderItem(VisitDate visitDate) {
        validInput = FALSE;
        while (!validInput) {
            try {
                OrderItem orderItem = inputView.readOrder();
                OrderItemDTO orderItemDTO = orderItem.toDTO();
                outputView.printOrder(orderItemDTO);
                beforeTotalPrice(visitDate, orderItem);
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void beforeTotalPrice(VisitDate visitDate, OrderItem orderItem) {
        outputView.printBeforePrice(orderItem.totalPrice());
        Order order = Order.createOrder(visitDate, orderItem);
        benefitDetails(order);
    }

    public void benefitDetails(Order order) {
        EventGroup eventGroup = EventGroup.of(order);
        EventGroupFacade eventGroupFacade = EventGroupFacade.of(eventGroup);
        EventManager eventManager = EventManager.of(eventGroupFacade);
        outputView.printPresent(eventManager.gift());
        outputView.printBenefit(eventManager.getEventDetails());
        outputView.printTotalBenefit(eventManager.totalBenefit());
        afterTotalPrice(eventManager.actualBenefit(), order.getBeforeTotalPrice());
    }

    public void afterTotalPrice(Integer discount, Integer beforeTotalPrice) {
        EventBadge eventBadge = EventBadge.of(beforeTotalPrice);
        Integer freeView = beforeTotalPrice - discount;
        outputView.printLastOrderPrice(freeView);
        outputView.printBadge(eventBadge.determineBadge());
    }
}
