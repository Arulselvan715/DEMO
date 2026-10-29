
import com.rest.CLI.TableCli;
import com.rest.Repository.MenuItemRepository;
import com.rest.Service.MenuItemService;
import com.rest.Service.OrderService;

public class Main {

    public static void main(String[] args) {

        // Initialize shared menu repository
        MenuItemRepository menuRepository =
                new MenuItemRepository();

        // Initialize services
        MenuItemService menuService =
                new MenuItemService(menuRepository);

        OrderService orderService =
                new OrderService(menuRepository);

        // Start restaurant management CLI
        TableCli cli =
                new TableCli(menuService, orderService);

        cli.start();
    }
}