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
    private final InputView inputView;
    private final OutputView outputView;

    private boolean validInput = false;

    public ChristmasController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void startOrderingProcess() {
        validInput = false;
        inputView.welcome();
        while (!validInput) {
            try {
                VisitDate visitDate = inputView.readDate();
                outputView.printFreeView(visitDate.getDate());
                validInput = true;
                processOrderItemInput(visitDate);
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void processOrderItemInput(VisitDate visitDate) {
        validInput = false;
        while (!validInput) {
            try {
                OrderItem orderItem = inputView.readOrder();
                OrderItemDTO orderItemDTO = orderItem.toDTO();
                outputView.printOrder(orderItemDTO);
                validInput = true;
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
        EventManager eventManager = createEventManager(order);
        displayGiftAndBenefits(eventManager);
        displayAfterTotalPrice(eventManager.actualBenefit(), order.getBeforeTotalPrice(), eventManager.totalBenefit());
    }

    private EventManager createEventManager(Order order) {
        EventGroup eventGroup = initializeEvents(order);
        EventGroupFacade eventGroupFacade = EventGroupFacade.of(eventGroup);
        return EventManager.of(eventGroupFacade);
    }

    private void displayGiftAndBenefits(EventManager eventManager) {
        outputView.printPresent(eventManager.gift());
        outputView.printBenefit(eventManager.getEventDetails());
        outputView.printTotalBenefit(eventManager.totalBenefit());
    }

    public void displayAfterTotalPrice(Integer actualBenefit, Integer beforeTotalPrice, Integer totalBenefit) {
        EventBadge eventBadge = EventBadge.of(totalBenefit);
        Integer freeView = beforeTotalPrice - actualBenefit;

        outputView.printLastOrderPrice(freeView);
        outputView.printBadge(eventBadge.determineBadge());
    }

    private EventGroup initializeEvents(Order order) {
        Map<EventType, EventStrategy> events = createEventStrategies(order);
        return EventGroup.of(order, events);
    }

    private Map<EventType, EventStrategy> createEventStrategies(Order order) {
        Map<EventType, EventStrategy> events = new HashMap<>();

        events.put(EventType.PRESENT, PresentEventStrategy.from(order.getBeforeTotalPrice()));
        events.put(EventType.D_DAY, createDdayEventStrategy(order));
        events.put(EventType.WEEKDAY, createWeekdayEventStrategy(order));
        events.put(EventType.WEEKEND, createWeekendEventStrategy(order));
        events.put(EventType.SPECIAL, createSpecialEventStrategy(order));

        return events;
    }

    private EventStrategy createDdayEventStrategy(Order order) {
        return DdayEventStrategy.of(order.getOrderDate(), order.getBeforeTotalPrice());
    }

    private EventStrategy createWeekdayEventStrategy(Order order) {
        int dessertCount = order.findMenuCount(MenuType.DESSERT);
        return WeekdayEventStrategy.of(order.getOrderDate(), dessertCount);
    }

    private EventStrategy createWeekendEventStrategy(Order order) {
        int mainCount = order.findMenuCount(MenuType.MAIN);
        return WeekendEventStrategy.of(order.getOrderDate(), mainCount);
    }

    private EventStrategy createSpecialEventStrategy(Order order) {
        return SpecialEventStrategy.of(order.getOrderDate(), order.getBeforeTotalPrice());
    }
}
