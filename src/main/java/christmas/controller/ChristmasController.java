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
    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        inputView.welcome();
        VisitDate visitDate = inputView.readDate();
        OrderItem orderItem = inputView.readOrder();
        OrderItemDTO orderItemDTO = orderItem.toDTO();
        outputView.printFreeView(visitDate.getDate());
        outputView.printOrder(orderItemDTO);
        beforeTotalPrice(visitDate, orderItem);
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
