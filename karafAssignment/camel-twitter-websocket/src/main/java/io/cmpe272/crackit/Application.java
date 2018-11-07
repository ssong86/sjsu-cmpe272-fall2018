package io.cmpe272.crackit;

import org.apache.camel.main.Main;


public final class Application {

    private Application() {
    }

    public static void main(String[] args) throws Exception {
        System.out.println("\n\n\n\n");
        System.out.println("===============================================");
        System.out.println("Open your web browser on http://localhost:9090/index.html");
        System.out.println("Press ctrl+c to stop this example");
        System.out.println("===============================================");
        System.out.println("\n\n\n\n");

        // create a new Camel Main so we can easily start Camel
        Main main = new Main();

        TwitterWebSocketRoute route = new TwitterWebSocketRoute();

        // add our routes to Camel
        main.addRouteBuilder(route);

        // and run, which keeps blocking until we terminate the JVM (or stop CamelContext)
        main.run();
    }

}
