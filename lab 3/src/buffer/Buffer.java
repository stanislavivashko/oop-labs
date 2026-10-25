package buffer;

public class Buffer {

    public static final int MAX_COUNT = 100;
    private static int activeBufferCount = 0;

    public static boolean isValidCount(int count) {
        return count > 0 && count <= MAX_COUNT;
    }

    public static int liveBufferCount() {
        return activeBufferCount;
    }

    public static int[] createBuffer(int count) {
        if (!isValidCount(count)) {
            return null;
        }
        activeBufferCount++;
        return new int[count];
    }

    public static void destroyBuffer(int[] buffer) {
        if (buffer != null && activeBufferCount > 0) {
            activeBufferCount--;
        }
    }

    public static void printRange(int[] buffer) {
        for (int i = 0; i < buffer.length; i++) {
            System.out.print(buffer[i] + (i == buffer.length - 1 ? "" : " "));
        }
        System.out.println();
    }

    public static int calculateSum(int[] buffer) {
        int sum = 0;
        for (int val : buffer) {
            sum += val;
        }
        return sum;
    }

    public static double calculateAverage(int[] buffer) {
        if (buffer.length == 0) return 0.0;
        return (double) calculateSum(buffer) / buffer.length;
    }

    public static MinMaxResult findMinMax(int[] buffer) {
        int min = buffer[0];
        int max = buffer[0];
        for (int val : buffer) {
            if (val < min) min = val;
            if (val > max) max = val;
        }
        return new MinMaxResult(min, max);
    }

    public static Integer findFirstAbove(int[] buffer, int threshold) {
        for (int i = 0; i < buffer.length; i++) {
            if (buffer[i] > threshold) {
                return i;
            }
        }
        return null;
    }

    public static int[] copyBuffer(int[] buffer) {
        if (buffer == null) return null;
        int[] copy = createBuffer(buffer.length);
        if (copy != null) {
            System.arraycopy(buffer, 0, copy, 0, buffer.length);
        }
        return copy;
    }

    public static void scaleValues(int[] buffer, int factor) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] *= factor;
        }
    }

    public static int[] growBuffer(int[] oldBuffer, int extraCount) {
        int newSize = oldBuffer.length + extraCount;
        if (!isValidCount(newSize) || extraCount <= 0) {
            return null;
        }

        int[] newBuffer = createBuffer(newSize);
        if (newBuffer != null) {
            System.arraycopy(oldBuffer, 0, newBuffer, 0, oldBuffer.length);
            destroyBuffer(oldBuffer);
        }
        return newBuffer;
    }
}