import java.lang.reflect.Method;

/**
 * Prints the Java runtime version and some related properties.
 * Uses System properties so it works on older Java versions and
 * attempts to call Runtime.version() via reflection when available
 * to show structured version information on Java 9+.
 */
public class PrintJavaVersion {

    public static void main(String[] args) {
        String javaVersion = System.getProperty("java.version");
        String javaVendor  = System.getProperty("java.vendor");
        String javaHome    = System.getProperty("java.home");

        System.out.println("Java version (System property): " + javaVersion);
        System.out.println("Java vendor  : " + javaVendor);
        System.out.println("Java home    : " + javaHome);

        // Try to call Runtime.version() on Java 9+ via reflection (works on older JDKs too)
        try {
            Method versionMethod = Runtime.class.getMethod("version"); // Java 9+
            Object runtimeVersion = versionMethod.invoke(null); // static method
            System.out.println("Runtime.version(): " + runtimeVersion);
        } catch (NoSuchMethodException nsme) {
            // Running on Java 8 or earlier — Runtime.version() not available
        } catch (Exception e) {
            // Ignore other reflection errors but print a short message
            System.out.println("Could not retrieve Runtime.version() via reflection: " + e.getMessage());
        }
    }

}
