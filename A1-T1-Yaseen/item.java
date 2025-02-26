import java.util.Scanner;

public class item {
    public String name;             //item name
    public int time;                //item scheduled time
    public String description = "";      //item description, may or may not exist
    public boolean status = false;  //item status, true when done
    public int importance;          //from 1 -> 3, dictating level of importance

    public item()
    {

    }

    public  item(String name, int time, String description, int importance)   //create a new item when description exists
    {
        this.name = name;
        this.time = time;
        this.description = description;
        this.importance = importance;

    }
    public item(String name, int time, int importance)      //create new item without description
    {
        this.name = name;
        this.time = time;
        this.importance = importance;
    }

    public void displayItem()
    {
        System.out.print("Name: " + this.name + ", Deadline: " + this.time + ", Description: " + this.description + ", Priority: " + this.importance);
        if(!status)
            System.out.println(", Status: pending");
        else
            System.out.println(", Status: finished");
    }



    public void displayItems()      //special implementation for when displaying everything
    {
        int descLength = this.description.length();
        System.out.print( this.name + "\t\t" +  this.time + "\t\t\t" );// this.description + "\t\t" +  this.importance+ "\t");
       /* for(int i = 0; i < descLength; ++i)
        {
            System.out.print(" ");
        }*/

        System.out.print(this.description + "\t\t" +  this.importance+ "\t");

        if(!status)
            System.out.println("pending");
        else
            System.out.println("finished");
    }

    public void editItem(String choice)
    {
        Scanner Classer = new Scanner(System.in);
        if(choice.equals("T") || choice.equals("Time"))     //change deadline time
        {
            int nTime;
            System.out.print("Enter new time: ");
            nTime = Classer.nextInt();
            this.time = nTime;
        }
        else if(choice.equals("D") || choice.equals("Description"))
        {
            String newDesc;
            System.out.print("Enter new description: ");
            newDesc = Classer.nextLine();
            if(newDesc.length() > 30)
            {
                System.out.println("Error: please make sure description is less than 30 characters long and try again...");
            }
            else
            {
                this.description = newDesc;
            }
        }

        else if(choice.equals("P") || choice.equals("Priority"))
        {
            int nPri=0;
            System.out.print("Enter new priority level[1->3]: ");
            nPri = Classer.nextInt();
            if(nPri>3 || nPri < 1)
            {
                System.out.println("Error: please make sure priority level is in specified range and try again...");
            }
        }
        else {
            System.out.println("Error: wrong choice...");
        }

    }

}
