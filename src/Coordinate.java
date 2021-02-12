import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Coordinate
{
    int x;
    int y;
    int eventID;
    List<Ticket> tickets;
    int numberOfTickets;
    Random rand = new Random();
    Coordinate(int x, int y, int eventID)
    {
        this.x=x;
        this.y=y;
        this.eventID=eventID;
        numberOfTickets = rand.nextInt(5)+1;
        tickets = new ArrayList<Ticket>();
    }

    public void generateTickets()
    {
        for(int i=0;i<numberOfTickets;i++)
        {
            int price = rand.nextInt(100);
            Ticket t = new Ticket(price);
            tickets.add(t);
        }
    }
}
