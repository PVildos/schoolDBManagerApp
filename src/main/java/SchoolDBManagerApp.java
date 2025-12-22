import java.util.Scanner;

public class SchoolDBManagerApp {
    public static void main(String[] args) {
        String nextQuery = "q";
        while (nextQuery.equals("q")) {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Δωσε username");
            String username = userInput.nextLine();
            System.out.println("Δωσε password");
            String password = userInput.nextLine();
            System.out.println("Δωσε query");
            String userQuery = userInput.nextLine();
            DatabaseOperation operation = new DatabaseOperation(username, password, userQuery);
            operation.csvToTable(operation);
            operation.updateDatabase(operation);
            operation.printUserQueryResultSet(operation.getQueryResult(operation, operation.getUserRequest()));
            System.out.println("Πατα q για αλλο query ή 0 για τερματισμό");
            nextQuery = userInput.nextLine();
        }
    }
}
