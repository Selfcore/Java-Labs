package Domain;

public interface DriverObserver {
    void notify(OrderRequest order, Driver driver);
}
