import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Streams {
    public static void main(String[] args) {
        Customer sheridan = new Customer("Sheridan");
        Customer ivanova = new Customer("Ivanova");
        Customer garibaldi = new Customer("Garibaldi");

        sheridan.addOrder(new Order(1, 100)).addOrder(new Order(2, 200)).addOrder(new Order(3, 300));
        ivanova.addOrder(new Order(4, 400)).addOrder(null).addOrder(new Order(5, 500));

        List<Customer> customers = Arrays.asList(sheridan, ivanova, garibaldi);


        /**
         * Receive price for given order id  for some customer if such order is exists.
         *
         * Throw IllegalStateException if order with given id not exists
         */

        long price = sheridan.getOrderById(1)
                .map(Order::getPrice)
                .orElseThrow(() -> new IllegalStateException("Order not found"));


        /**
         * Build list with customer's names in upper case for all customers
         */

        List<String> customersNamesInUpperCase = customers.stream()
                .map(Customer::getName)
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        /**
         * Build Map where key will be orderId and value order itself.
         *
         * Map should contains all non-null orders where price higher than 200 for all customers.
         */

        Map<Integer, Order> nonNullOrdersWithPriceHigherThan200 = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(Objects::nonNull)
                .filter(order -> order.getPrice() > 200)
                .collect(Collectors.toMap(Order::getId, Function.identity()));
    }
}

class Customer {
    private String name;

    private List<Order> orders = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Customer addOrder(Order order) {
        orders.add(order);
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Return non-empty Optional<Order> when customer has order with given id.
     *
     * Otherwise Optional<Order> should be empty
     */
    public Optional<Order> getOrderById(int id) {
        return orders.stream()
                .filter(order -> order.getId() == id)
                .findFirst();
    }
}

class Order {
    private int id;
    private long price;


    public Order(int id, long price) {
        this.id = id;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public long getPrice() {
        return price;
    }
}
