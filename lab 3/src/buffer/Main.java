package buffer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("Enter number of measurements:");
            if (!scanner.hasNextInt()) {
                System.out.println("\nInvalid count");
                return;
            }
            int count = scanner.nextInt();

            if (!Buffer.isValidCount(count)) {
                System.out.println("\nInvalid count");
                return;
            }

            int[] data = Buffer.createBuffer(count);

            System.out.println("Enter " + count + " measurements:");
            for (int i = 0; i < count; i++) {
                data[i] = scanner.nextInt();
            }

            System.out.println("Enter threshold:");
            int threshold = scanner.nextInt();

            System.out.println("Enter scale factor:");
            int factor = scanner.nextInt();

            System.out.print("\nMeasurements: ");
            Buffer.printRange(data);

            System.out.println("Sum: " + Buffer.calculateSum(data));
            System.out.printf("Average: %.5f\n", Buffer.calculateAverage(data));

            MinMaxResult minMax = Buffer.findMinMax(data);
            System.out.println("Minimum: " + minMax.min());
            System.out.println("Maximum: " + minMax.max());

            Integer firstAboveIdx = Buffer.findFirstAbove(data, threshold);
            if (firstAboveIdx != null) {
                System.out.println("First above " + threshold + ": " + data[firstAboveIdx] + " (index " + firstAboveIdx + ")");
            } else {
                System.out.println("No values above " + threshold);
            }

            int[] scaled = Buffer.copyBuffer(data);
            Buffer.scaleValues(scaled, factor);

            System.out.print("Scaled by " + factor + ": ");
            Buffer.printRange(scaled);

            System.out.print("Original: ");
            Buffer.printRange(data);

            System.out.println("Live buffers: " + Buffer.liveBufferCount());

            Buffer.destroyBuffer(scaled);
            scaled = null;

            Buffer.destroyBuffer(data);
            data = null;

            System.out.println("\nLive buffers after cleanup: " + Buffer.liveBufferCount());

            // --- Додаткове завдання (growBuffer) ---
            System.out.println("\nEnter number of extra measurements:");
            if (scanner.hasNextInt()) {
                int extraCount = scanner.nextInt();

                int[] growData = Buffer.createBuffer(count);
                int[] grownBuffer = Buffer.growBuffer(growData, extraCount);

                if (grownBuffer == null) {
                    System.out.println("Invalid count");
                    Buffer.destroyBuffer(growData);
                } else {
                    System.out.println("Enter " + extraCount + " measurements:");
                    for (int i = count; i < grownBuffer.length; i++) {
                        grownBuffer[i] = scanner.nextInt();
                    }

                    System.out.println("Live buffers: " + Buffer.liveBufferCount());
                    Buffer.destroyBuffer(grownBuffer);
                }
            }
        }
    }
}