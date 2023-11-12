package christmas.util.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Menu {
    MUSHROOM_SOUP(MenuType.APPETIZER,"양송이수프",6000),
    TAPAS(MenuType.APPETIZER,"타파스", 5500),
    CAESAR_SALAD(MenuType.APPETIZER,"시저샐러드", 8000),

    T_BONE_STEAK(MenuType.MAIN,"티본스테이크", 55000),
    BBQ_RIBS(MenuType.MAIN,"바비큐립", 54000),
    SEAFOOD_PASTA(MenuType.MAIN,"해산물파스타", 35000),
    CHRISTMAS_PASTA(MenuType.MAIN,"크리스마스파스타", 25000),

    CHOCO_CAKE(MenuType.DESSERT,"초코케이크", 15000),
    ICE_CREAM(MenuType.DESSERT,"아이스크림", 5000),

    ZERO_COKE(MenuType.DRINK,"제로콜라", 3000),
    RED_WINE(MenuType.DRINK,"레드와인", 60000),
    CHAMPAGNE(MenuType.DRINK,"샴페인", 25000);

    private final MenuType type;
    private final String name;
    private final int price;

    Menu(MenuType type, String name, int price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    private static final Map<String, Menu> ENTIRE_MENU = new HashMap<>();

    static {
        Arrays.stream(Menu.values())
                .forEach(menu -> ENTIRE_MENU.put(menu.getName(), menu));
    }

    public MenuType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public static Menu getMenuByName(String name) {
        return ENTIRE_MENU.get(name);
    }

    public static boolean isValidMenu(String name) {
        return Arrays.stream(values()).anyMatch(menu -> menu.name.equals(name));
    }
}
