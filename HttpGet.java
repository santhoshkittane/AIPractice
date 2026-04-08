import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.Scanner;

/**
 * Simple HTTP GET utility using java.net.http.HttpClient (Java 11+).
 *
 * Usage:
 *  - java -cp bin HttpGet https://example.com
 *  - java -cp bin HttpGet           (will prompt for a URL)
 */
public class HttpGet {

    public static void main(String[] args) {
        String url;

        if (args.length > 0) {
            url = args[0];
        } else {
            System.out.print("Enter URL to GET (e.g. https://httpbin.org/get): ");
            Scanner sc = new Scanner(System.in);
            url = sc.nextLine().trim();
            sc.close();
            if (url.isEmpty()) {
                System.err.println("No URL provided. Exiting.");
                return;
            }
        }

        // Normalize URL
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(20))
                    .GET()
                    .header("User-Agent", "JavaHttpClient/1.0")
                    .build();
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid URL: " + url);
            return;
        }

        System.out.println("Sending GET to: " + url);

        try {
            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

            int status = response.statusCode();
            System.out.println("HTTP Status Code received: " + status);

            // Verify status code is 200 (OK)
            if (status == 200) {
                System.out.println("Verification: OK (200)");
            } else {
                System.err.println("Verification: Unexpected status (expected 200). Received: " + status);
            }

            System.out.println("Headers:");
            for (Map.Entry<String, java.util.List<String>> h : response.headers().map().entrySet()) {
                System.out.println("  " + h.getKey() + ": " + String.join(", ", h.getValue()));
            }

            byte[] bodyBytes = response.body();
            if (bodyBytes == null || bodyBytes.length == 0) {
                System.out.println("(empty body)");
            } else {
                // Try to decode as UTF-8 for display; if non-textual, show bytes length
                String bodyStr = new String(bodyBytes, StandardCharsets.UTF_8);
                System.out.println("\nBody (first 16k chars shown):\n");
                if (bodyStr.length() > 16_384) {
                    System.out.println(bodyStr.substring(0, 16_384));
                    System.out.println("\n... (truncated, total bytes=" + bodyBytes.length + ")");
                } else {
                    System.out.println(bodyStr);
                }
            }

        } catch (IOException e) {
            System.err.println("I/O error while sending request: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Request interrupted");
        }
    }

}
