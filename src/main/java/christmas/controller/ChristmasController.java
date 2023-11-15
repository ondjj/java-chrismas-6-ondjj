package christmas.controller;

import christmas.dto.OrderItemDTO;
import christmas.model.EventBadge;
import christmas.model.EventGroup;
import christmas.model.EventGroupFacade;
import christmas.model.EventManager;
import christmas.model.Order;
import christmas.model.OrderItem;
import christmas.model.VisitDate;
import christmas.model.event.DdayEventStrategy;
import christmas.model.event.EventStrategy;
import christmas.model.event.PresentEventStrategy;
import christmas.model.event.SpecialEventStrategy;
import christmas.model.event.WeekdayEventStrategy;
import christmas.model.event.WeekendEventStrategy;
import christmas.util.enums.EventType;
import christmas.util.enums.MenuType;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.HashMap;
import java.util.Map;

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

    public void startOrderingProcess() {
        validInput = FALSE;
        inputView.welcome();
        while (!validInput) {
            try {
                VisitDate visitDate = inputView.readDate();
                outputView.printFreeView(visitDate.getDate());
                validInput = TRUE;
                processOrderItemInput(visitDate);
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void processOrderItemInput(VisitDate visitDate) {
        validInput = FALSE;
        while (!validInput) {
            try {
                OrderItem orderItem = inputView.readOrder();
                OrderItemDTO orderItemDTO = orderItem.toDTO();
                outputView.printOrder(orderItemDTO);
                validInput = TRUE;
                displayBeforeTotalPrice(visitDate, orderItem);
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void displayBeforeTotalPrice(VisitDate visitDate, OrderItem orderItem) {
        outputView.printBeforePrice(orderItem.totalPrice());
        Order order = Order.createOrder(visitDate, orderItem);
        benefitDetails(order);
    }

    public void benefitDetails(Order order) {
        EventGroup eventGroup = initializeEvents(order);
        EventGroupFacade eventGroupFacade = EventGroupFacade.of(eventGroup);
        EventManager eventManager = EventManager.of(eventGroupFacade);

        outputView.printPresent(eventManager.gift());
        outputView.printBenefit(eventManager.getEventDetails());
        outputView.printTotalBenefit(eventManager.totalBenefit());
        displayAfterTotalPrice(eventManager.actualBenefit(), order.getBeforeTotalPrice(), eventManager.totalBenefit());
    }

    public void displayAfterTotalPrice(Integer actualBenefit, Integer beforeTotalPrice, Integer totalBenefit) {
        EventBadge eventBadge = EventBadge.of(totalBenefit);
        Integer freeView = beforeTotalPrice - actualBenefit;

        outputView.printLastOrderPrice(freeView);
        outputView.printBadge(eventBadge.determineBadge());
    }

    private EventGroup initializeEvents(Order order) {
        Map<EventType, EventStrategy> events = new HashMap<>();

        events.put(EventType.PRESENT, PresentEventStrategy.from(order.getBeforeTotalPrice()));
        events.put(EventType.D_DAY, DdayEventStrategy.of(order.getOrderDate(), order.getBeforeTotalPrice()));
        events.put(EventType.WEEKDAY, WeekdayEventStrategy.of(order.getOrderDate(),
                order.findMenuCount(MenuType.DESSERT)));
        events.put(EventType.WEEKEND, WeekendEventStrategy.of(order.getOrderDate(),
                order.findMenuCount(MenuType.MAIN)));
        events.put(EventType.SPECIAL, SpecialEventStrategy.of(order.getOrderDate(), order.getBeforeTotalPrice()));
        return EventGroup.of(order, events);
    }
}
