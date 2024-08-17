package engcake.excavator.utils;

public class CollectionsUtils {
    public static <T> T getLastElement(Iterable<T> iterable) {
        T lastElement = null;
        for (T element : iterable) {
            lastElement = element;
        }
        return lastElement;
    }
}
