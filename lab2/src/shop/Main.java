package shop;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Конструкція try-with-resources автоматично закриє scanner
        try (var scanner = new Scanner(System.in)) {

            final var prices = new double[Order.ITEM_COUNT];
            final var quantities = new int[Order.ITEM_COUNT];

            System.out.println("Enter prices for " + Order.ITEM_COUNT + " items:");
            for (var i = 0; i < Order.ITEM_COUNT; ++i) {
                prices[i] = scanner.nextDouble();
            }

            System.out.println("\nEnter quantities:");
            for (var i = 0; i < Order.ITEM_COUNT; ++i) {
                quantities[i] = scanner.nextInt();
            }

            if (!Order.isValidOrder(prices, quantities, Order.ITEM_COUNT)) {
                System.out.println("\nInvalid input");
                return;
            }

            final var itemTotals = new double[Order.ITEM_COUNT];
            Order.calculateItemTotals(prices, quantities, itemTotals, Order.ITEM_COUNT);

            System.out.println("\nItem totals:");
            for (final var total : itemTotals) {
                System.out.println(total);
            }

            final int totalQuantity = Order.calculateTotalQuantity(quantities, Order.ITEM_COUNT);
            final double subtotal = Order.calculateSubtotal(itemTotals, Order.ITEM_COUNT);
            final DiscountLevel discountLevel = Order.determineDiscount(subtotal);
            final double discountAmount = Order.calculateDiscountAmount(subtotal, discountLevel);

            final double finalTotal = Order.applyDiscount(subtotal, discountLevel);
            final double deliveryFee = Order.calculateDeliveryFee(finalTotal);
            final double totalWithDelivery = finalTotal + deliveryFee;

            System.out.println("\nTotal quantity: " + totalQuantity);
            System.out.println("Subtotal: " + subtotal);

            if (discountLevel == DiscountLevel.TEN_PERCENT) {
                System.out.println("Discount: 10%");
            } else if (discountLevel == DiscountLevel.FIVE_PERCENT) {
                System.out.println("Discount: 5%");
            } else {
                System.out.println("Discount: 0%");
            }

            System.out.println("Discount amount: " + discountAmount);
            System.out.println("Final total: " + finalTotal);
            System.out.println("Delivery: " + deliveryFee);
            System.out.println("Total with delivery: " + totalWithDelivery);
        }
    }
}