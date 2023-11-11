package christmas.controller;

import christmas.dto.OrderItemDTO;
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
        outputView.printFreeView();
        outputView.printOrder(orderItemDTO);
    }

}
