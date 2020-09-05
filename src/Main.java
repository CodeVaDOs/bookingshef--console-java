import controller.BookingController;
import controller.FlightController;
import entity.Booking;
import entity.Flight;
import enums.Destinations;

import java.time.Instant;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FlightController fControler = new FlightController();
        BookingController bControler = new BookingController();
        int command;

        do {
            printMenu();
            System.out.print("\nВведите код действия: ");
            command = scanner.nextInt();

            switch (command) {
                case 1: {
                    fControler.getOnlineFlights()
                            .stream()
                            .forEach(i -> {
                                System.out.println(i);
                            });
                }
                break;
                case 2: {
                    System.out.print("\nВведите ID рейса: ");
                    long id = scanner.nextLong();
                    Optional<String> s = fControler.getFlightInfo(id)
                            .map(Flight::toString);

                    System.out.println(s.orElse("\nРейс с данным ID отсутствует."));
                }
                break;
                case 3: {
                    System.out.print("\nВведите имя: ");
                    String name = scanner.next();
                    System.out.print("Введите фамилию: ");
                    String surname = scanner.next();

                    System.out.print("Введите ID рейса: ");
                    Long id = scanner.nextLong();

                    System.out.println(
                            fControler.getFlightInfo(id)
                                    .flatMap(f -> bControler.reserve(name, surname, f))
                                    .map(Booking::toString)
                                    .orElse("Ошибка бронирования рейса.")
                    );
                }
                break;
                case 4: {
                    System.out.print("\nВведите ID брони: ");
                    long id = scanner.nextLong();
                    boolean b = bControler.cancelReserve(id);
                    System.out.println(b ? "Успешное удаление!" : "Ошибка удаления!");
                }
                break;
                case 5: {
                    System.out.print("\nВведите имя: ");
                    String name = scanner.next();
                    System.out.print("Введите фамилию: ");
                    String surname = scanner.next();

                    bControler.findFlightByCredit(name, surname).stream()
                            .forEach(item -> System.out.println(item));
                }
                break;
                case 6: {
                    Random rand = new Random();
                    System.out.print("Введите количество рейсов: ");
                    int count = scanner.nextInt();

                    List<Destinations> destinationsList = new ArrayList<>(Arrays.asList(Destinations.values()));

                    for (int i = 0; i < count; i++) {
                        Collections.shuffle(destinationsList);
                        Flight newFlight = new Flight(Instant.now().plusMillis(1000 * 60 * 60 * (rand.nextInt(524) + 1)), destinationsList.get(0), rand.nextInt(100));
                        fControler.save(newFlight);
                        System.out.println(newFlight);
                    }
                }
                break;
                case 0:
                    fControler.saveDataToFile();
                    bControler.saveDataToFile();
                    break;
                default:
                    System.out.println("Ошибка действия!");
            }
        } while (command != 0);
    }

    public static void printMenu() {
        System.out.println("\n*** МЕНЮ ***");
        System.out.println("1. Онлайн табло");
        System.out.println("2. Посмотреть информацию о рейсе");
        System.out.println("3. Поиск и бронировка рейса");
        System.out.println("4. Отменить бронирование");
        System.out.println("5. Мои рейсы");
        System.out.println("6. Генерация данных");
        System.out.println("0. Выход");
    }
}
