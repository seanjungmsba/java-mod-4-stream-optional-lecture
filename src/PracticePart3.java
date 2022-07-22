import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PracticePart3 {

    /*
     * Goals:
     * See how operations can be combined in complex ways to arrive at a desired outcome
     * Understand the kinds of problems that streams are well-suited to
     * Practice stream/functional logic
     */
    public static void main(String[] args) {

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 5);

        // How can I get the biggest integer?
        Integer max = numbers
                .stream()
                .mapToInt(v -> v) // returns an IntStream consisting of the results of applying the given function to the elements of this stream
                .max()
                .orElseThrow(NoSuchElementException::new); // is used to get the value of this Optional instance if present.
        System.out.println("Biggest Integer: " + max);

        // How can I get the average?
        Double avg = numbers
                .stream()
                .mapToInt(v -> v) // returns an IntStream consisting of the results of applying the given function to the elements of this stream
                .average()
                .orElseThrow(NoSuchElementException::new); // is used to get the value of this Optional instance if present.
        System.out.println("Average: " + avg);


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        List<String> simulatedUserInput = Arrays.asList("", "Jay", " ", "St. Jay's Hospital", "1", "Jay Hospital");

        // How can I take that list and get a list of the non-blank Strings contained in that list?
        List<String> nonBlankString = simulatedUserInput
                .stream()
                .filter(item-> !item.isBlank())
                .collect(Collectors.toList());
        System.out.println("Non-blank strings: " + nonBlankString);

        // How can I take that list and get a list of the blank Strings contained in that list?
        List<String> blankString = simulatedUserInput
                .stream()
                .filter(item-> item.isBlank())
                .collect(Collectors.toList());
        System.out.println("Blank strings: " + blankString);

        // How can I take that list and make a set? (Note: Streams may not be the best way to convert lists to set)
        Set<String> simulatedUserInputSet = new HashSet<>(simulatedUserInput);
        System.out.println("simulatedUserInput Set: " + simulatedUserInputSet);

        // How can I print out each element in the list?
        System.out.println("========== PRINTING OUT ELEMENTS IN SET ==========");
        simulatedUserInputSet
                .stream()
                .forEach(System.out::println);
        System.out.println("==================================================");

        // How can I take that list and filter out blank Strings and then get a list
        // containing the remaining Strings but UPPERCASED
        List<String> uppercasedSimulatedUserInputSet = simulatedUserInputSet
                .stream()
                .filter(item-> !item.isBlank())
                .map(item -> item.toUpperCase())
                .collect(Collectors.toList());
        System.out.println("Uppercased simulated user input set: " + uppercasedSimulatedUserInputSet);

        // How can I take that list, remove null entries, remove all whitespace from each string,
        // and then get the average length of the remaining strings?
        Double averageLengthString = simulatedUserInput
                .stream()
                .filter(item-> !item.isBlank())
                .map(string -> string.trim())
                .mapToInt(string -> string.length())
                .average()
                .getAsDouble();
        System.out.println("Average length of the string: " + averageLengthString);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Set<WorkOrder> orders = Stream.of(new WorkOrder(BigDecimal.valueOf(1.33)),
                new WorkOrder(BigDecimal.valueOf(2.43)), new WorkOrder(BigDecimal.valueOf(1.66)),
                new WorkOrder(BigDecimal.valueOf(0.11)), new WorkOrder(BigDecimal.valueOf(1.75)),
                new WorkOrder(BigDecimal.valueOf(3.45)), new WorkOrder(BigDecimal.valueOf(1.25)),
                new WorkOrder(BigDecimal.valueOf(1.01)), new WorkOrder(BigDecimal.valueOf(2.75)),
                new WorkOrder(BigDecimal.valueOf(2.00)), new WorkOrder(BigDecimal.valueOf(3.33)),
                new WorkOrder(BigDecimal.valueOf(2.00)), new WorkOrder(BigDecimal.valueOf(4.1)),
                new WorkOrder(BigDecimal.valueOf(1.25)), new WorkOrder(BigDecimal.valueOf(0.5)),
                new WorkOrder(BigDecimal.valueOf(1.25)), new WorkOrder(BigDecimal.valueOf(0.3)),
                new WorkOrder(BigDecimal.valueOf(1.25)), new WorkOrder(BigDecimal.valueOf(0.00)),
                new WorkOrder(BigDecimal.valueOf(14.5)), new WorkOrder(BigDecimal.valueOf(1.25)),
                new WorkOrder(BigDecimal.valueOf(2.5)), new WorkOrder(BigDecimal.valueOf(2)),
                new WorkOrder(BigDecimal.valueOf(1.5)), new WorkOrder(BigDecimal.valueOf(1))).collect(Collectors.toSet());
        // Here is a set of WorkOrder objects with productive hours and IDs and descriptions and timestamps

        // How can I get the total number of productive hours?
        BigDecimal totalProductiveHours = orders
                .stream()
                .map(WorkOrder::getProductiveHours)
                .reduce(BigDecimal::add)
                .get();
        System.out.println("Total number of productive hours: " + totalProductiveHours);

        // How can I get the list of order IDs for orders that happened on a weekend?

        // How can I get the total number of productive hours that happened outside business hours (9-5 M-F)?

        // How can I get a collection of all the productive hours values, but without duplicates?

        // How can I get a list of all WorkOrders sorted by date? Most recent first

        // How can I take this set of orders and make a map from orderId to work order?
    }
}

class WorkOrder {
    private BigDecimal productiveHours;
    private LocalDateTime timestamp;
    private Long id;
    private String description;
    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);
    private static final Random RANDOM = new Random();
    public WorkOrder(BigDecimal productiveHours) {
        this.productiveHours = productiveHours;
        this.id = ID_GENERATOR.getAndIncrement();
        this.description = "This truck has some problems";
        this.timestamp = LocalDateTime.of(2022, 06, RANDOM.nextInt(1, 30), RANDOM.nextInt(24), RANDOM.nextInt(60), 0);
    }

    public BigDecimal getProductiveHours() {
        return productiveHours;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }


}