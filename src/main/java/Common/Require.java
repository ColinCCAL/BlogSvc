package Common;

public class Require {

    public static void notNull(String objectName, Object object) {
        if (object != null) { return; }
        throw new IllegalArgumentException("Missing Module: " + objectName);
    }
}
