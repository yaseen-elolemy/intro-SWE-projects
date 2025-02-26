import java.util.Scanner;


public class Main {

    void Main() {
    }
    public static void main(String[] args) {
        int choice=0;
        Scanner reader = new Scanner(System.in);

        System.out.println("Welcome to TODO List");
        todo newlist = new todo();
        while(choice != 11)
        {
            System.out.println("\nPlease select from the menu bellow: ");
            System.out.println("1. Create a new task\n2. Remove existing task\n3. Display all tasks\n4. Edit existing task\n5. Mark as done\n6. Change list order\n7. Change task priority\n8. View task statistics");
            System.out.printf("9. Clear all\n10. Undo last delete\n11. Exit\n-> ");
            choice = reader.nextInt();
            reader.nextLine();

            switch(choice)
            {
                case 1:     //creating a new task
                    String taskname;
                    System.out.printf("Please enter taskname: ");
                    taskname = reader.nextLine();

                    int deadline;
                    System.out.printf("deadline[HHmm]: ");
                    while(true)
                    {
                        deadline = reader.nextInt();
                        reader.nextLine();
                        if(deadline < 0 || deadline > 2359)
                        {
                            System.out.println("Error: deadline has to be between 0000 and 2359, please try again...");
                            System.out.printf("deadline[HHmm]: ");
                        }
                        else{
                            break;
                        }
                    }



                    String description;
                    System.out.printf("short task description: ");

                    while(true) {
                        description = reader.nextLine();
                        if (description.length() > 30) {
                            System.out.println("Error: description has to be less than 30 characters long, please try again...");   //for some reason System.err bugs out?!?!?!?!
                            System.out.printf("short task description: ");
                        }
                        else
                            break;
                    }

                    int importance = 1; //hardcoded to least important for now!!! TODO change it to scan later

                    newlist.addItem(taskname, deadline, description, importance);
                    //System.out.println(newlist); for debugging TODO remove at the end
                    break;
                case 2:
                    int index;
                    System.out.printf("Please enter task index to remove: ");
                    index = reader.nextInt();
                    newlist.removeItem(index);
                    break;
                case 3:
                    newlist.displayAllTasks();
                    break;
                case 4:
                    System.out.println("Which field would you like to change?[(T)ime, (D)escription]");
                    System.out.print("-> ");
                    String Choice;
                    Choice = reader.nextLine();
                    int task;
                    System.out.println("Which task would you like to edit?");
                    System.out.print("-> ");
                    task = reader.nextInt();
                    newlist.editTask(Choice, task);

                    break;
                case 5:
                    int taskin;
                    System.out.println("Which task did you finish[empty for earliest]? ");
                    System.out.print("-> ");
                    taskin = reader.nextInt();
                    if(newlist.finishTask(taskin))
                    {
                        System.out.println("Item successfully marked as done.");
                    }
                    else
                    {

                        System.out.println("Error: task is either finished or does NOT exist...");
                        System.out.println("Please make sure and try again!");
                    }
                    break;
                case 6:
                    //Sorting by priority or time
                    System.out.print("Would you like to sort by Priority or By Time?[P/T] ");
                    String ch;
                    ch = reader.next();
                    newlist.sortTasks(ch);
                    break;

                case 7:
                    int INDEX, pri;
                    System.out.println("Which task do you want to change by index? ");
                    System.out.print("-> ");
                    INDEX = reader.nextInt();
                    System.out.print("Enter desired priority[1 -> 3]: ");
                    pri = reader.nextInt();
                    newlist.setPriority(INDEX, pri);
                    break;

                case 8:
                    newlist.printStatistics();
                    break;

                case 9:
                    newlist.clearAll();
                    break;

                case 10:
                    newlist.undo();
                    break;
                case 11:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }



        reader.close();
        }
    }
