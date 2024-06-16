package myTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * TestRunner class that runs the tests of the ListAdapterTest class
 * using JUnit.
 */
public class TestRunner {

    /**
     * Main method that runs the tests of the ListAdapterTest class using JUnit.
     * Prints information about the tests performed, the number of failed tests, and the execution time.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Runs the tests of the ListAdapterTest class
        Result result = JUnitCore.runClasses(ListAdapterTest.class);

        // Prints the total number of tests run
        System.out.println("\nNumber of tests run: " + result.getRunCount());
        
        // Prints the number of failed tests
        System.out.println("Number of failed tests: " + result.getFailureCount());
        
        // Prints the total execution time of the tests in milliseconds
        System.out.println("Test execution time: " + result.getRunTime() + "ms");

        // Checks if there are any failed tests
        if (result.getFailureCount() == 0) {
            System.out.println("\nAll tests were completed successfully.");
        } else {
            System.out.println("\nNot all tests were completed successfully.");
            System.out.println("Please review the errors below:\n");
            
            // Iterates through all failures and prints the details
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }
}
