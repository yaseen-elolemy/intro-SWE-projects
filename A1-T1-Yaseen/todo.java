import java.util.ArrayList;

public class todo extends item{ //this class is for the list
    protected int nItems=0;
    ArrayList<item> items;
    protected int[] stats = new int[2];     //index 0: for finished, index 1: for unfinished
    item lastdeleted;

    public todo()   //create a new list instance
    {
        items =  new ArrayList<>();
    }

    void addItem(String iName, int Itime, String iDesc, int Importance)
    {
        Importance = 1;
        //iDesc = "";       //was a failed atempt to try and initialize the variable
        if(iDesc == "")
        {
            item newitem = new item(iName, Itime, Importance);
            items.add(newitem);
            newitem.displayItem();

        }
        else
        {
            item newitem = new item(iName, Itime, iDesc, Importance);
            items.add(newitem);
            newitem.displayItem();

        }
        updateStats();
        nItems++;

    }


    void removeItem(int index)
    {
        lastdeleted = items.get(index);
        items.remove(index);

        nItems--;
    }

    void displayAllTasks() {
        System.out.println("Index\tName\t\tDeadline\tDescription\t\t\tPriority\tStatus");
        for (int i = 0; i < nItems; i++) {
            item currentItem = items.get(i);
            String status = currentItem.status ? "finished" : "pending";
            System.out.println(String.format("%d\t%s\t\t%d\t\t%s\t\t%d\t\t%s",
                    i, currentItem.name, currentItem.time, currentItem.description, currentItem.importance, status));
        }
    }

    void editTask(String usin, int index)
    {

        items.get(index).editItem(usin);
    }

    boolean finishTask(int ind)
    {
        if(!items.get(ind).status)
        {
            items.get(ind).status = true;
            updateStats();
            return true;

        }
        else
        {

            return false;
        }
    }
    void sortTasks(String user)
    {
        //ArrayList<item> newItem = new ArrayList<>();
        if(user.equals("T") || user.equals("t"))        // sort by time
        {
            for(int i = 0; i < nItems; ++i)
            {
                for(int j = i; j < nItems; j++)
                {
                    item temp;
                    if(items.get(j).time < items.get(i).time)
                    {
                        temp = items.get(i);
                        items.set(i, items.get(j));
                        items.set(j, temp);
                    }
                }

            }
        }
        if(user.equals("P") || user.equals("p"))        // sort by time
        {
            for(int i = 0; i < nItems; ++i)
            {
                for(int j = i; j < nItems; j++)
                {
                    item temp;
                    if(items.get(j).time > items.get(i).time)
                    {
                        temp = items.get(i);
                        items.set(i, items.get(j));
                        items.set(j, temp);
                    }
                }

            }
        }

    }

    void setPriority(int index, int desire)
    {
        if(desire < 0 || desire > 3)
        {
            System.out.println("Error: please not and choose within the possible range, ascending by priority level. and try again...");
        }
        else
            items.get(index).importance = desire;
    }

    void updateStats()
    {
        stats[0] = 0;
        stats[1] = 0;
        for(int i =0; i < nItems; i++)
        {
            if(items.get(i).status)
            {
                stats[0]++;
            }
            else if(!items.get(i).status)
            {
                stats[1]++;
            }
        }
    }

    void printStatistics()
    {
        System.out.println("Finished tasks: " + stats[0]);

        System.out.println("Unfinished tasks: "+ stats[1]);
    }
    void clearAll()
    {
        items.clear();
        nItems = 0;
        stats[0] = 0;
        stats[1] = 0;
    }

    void undo()
    {
        addItem(lastdeleted.name, lastdeleted.time, lastdeleted.description, lastdeleted.importance);
    }

}


