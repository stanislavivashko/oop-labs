package shop;

public class Order {

    public static final int ITEM_COUNT = 5;

    public static final double DISCOUNT_THRESHOLD_FIVE = 1000.0;
    public static final double DISCOUNT_THRESHOLD_TEN = 3000.0;

    public static final double DISCOUNT_PERCENT_FIVE = 0.05;
    public static final double DISCOUNT_PERCENT_TEN = 0.10;

    public static final double FREE_DELIVERY_THRESHOLD = 2000.0;
    public static final double DELIVERY_COST = 150.0;

    public static boolean isValidOrder(double[] prices, int[] quantities, int size) {
        for (var i = 0; i < size; ++i) {
            if (prices[i] <= 0.0 || quantities[i] <= 0) {
                return false;
            }
        }
        return true;
    }

    public static void calculateItemTotals(double[] prices, int[] quantities, double[] totals, int size) {
        for (var i = 0; i < size; ++i) {
            totals[i] = prices[i] * quantities[i];
        }
    }

    public static int calculateTotalQuantity(int[] quantities, int size) {
        var totalQty = 0;
        for (var qty : quantities) {
            totalQty += qty;
        }
        return totalQty;
    }

    public static double calculateSubtotal(double[] totals, int size) {
        var subtotal = 0.0;
        for (var total : totals) {
            subtotal += total;
        }
        return subtotal;
    }

    public static DiscountLevel determineDiscount(double subtotal) {
        if (subtotal >= DISCOUNT_THRESHOLD_TEN) {
            return DiscountLevel.TEN_PERCENT;
        }
        if (subtotal >= DISCOUNT_THRESHOLD_FIVE) {
            return DiscountLevel.FIVE_PERCENT;
        }
        return DiscountLevel.NONE;
    }

    public static double calculateDiscountAmount(double subtotal, DiscountLevel level) {
        return switch (level) {
            case TEN_PERCENT -> subtotal * DISCOUNT_PERCENT_TEN;
            case FIVE_PERCENT -> subtotal * DISCOUNT_PERCENT_FIVE;
            case NONE -> 0.0;
        };
    }

    public static double applyDiscount(double total, DiscountLevel level) {
        var discountAmount = calculateDiscountAmount(total, level);
        return total - discountAmount;
    }

    public static double calculateDeliveryFee(double totalAfterDiscount) {
        if (totalAfterDiscount < FREE_DELIVERY_THRESHOLD) {
            return DELIVERY_COST;
        }
        return 0.0;
    }
}