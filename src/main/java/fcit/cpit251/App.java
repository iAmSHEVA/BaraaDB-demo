package fcit.cpit251;


import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int choice = 0;
        Scanner in0 = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        Scanner in2 = new Scanner(System.in);
        Scanner in3 = new Scanner(System.in);
        try {
            DBConnection db = DBConnection.getInstance();


            do {
                System.out.println("To-Do list program");
                System.out.println("1.Insert:\n2.Update:\n3.Delete:\n4.Retrieve all tasks:\n\n**Any wrong choice will close the program");
                System.out.print("\nEnter your choice: ");
                choice = in0.nextInt();
                System.out.println();
                if (choice == 1) {
                    System.out.println("-Insert");
                    String name;
                    boolean flag;
                    System.out.print("Enter your task name: ");
                    name = in.nextLine();
                    System.out.print("\nEnter your task status (true or false): ");
                    flag = in2.nextBoolean();
                    Task t = new Task(name, flag);
                    t.insertTask();
                    t.retrieveTasks();
                    System.out.println();

                } else if (choice == 2) {
                    System.out.println("-Update");
                    String name;
                    boolean flag;
                    int id;
                    System.out.print("Enter your task name: ");
                    name = in.nextLine();
                    System.out.print("\nEnter your task status (true or false): ");
                    flag = in2.nextBoolean();
                    System.out.print("\nEnter your task id to update: ");
                    id = in3.nextInt();
                    Task t = new Task(name, flag);
                    t.updateTask(name, flag, id);
                    t.retrieveTasks();
                    System.out.println();
                } else if (choice == 3) {
                    System.out.println("-Delete");
                    int id;
                    Task t = new Task(" ", false);
                    t.retrieveTasks();
                    System.out.print("Enter your task id to delete: ");
                    id = in.nextInt();
                    t.deleteTask(id);
                    t.retrieveTasks();
                    System.out.println();
                } else if (choice == 4) {
                    System.out.println("All tasks: ");
                    Task t = new Task(" ", false);
                    t.retrieveTasks();
                    System.out.println();
                } else
                    System.exit(0);

            } while (choice != 0);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch(InputMismatchException e){

        }
    }
}
