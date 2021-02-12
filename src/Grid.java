import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

class Grid
{
    List<Coordinate> coordinates;
    List<Integer> idsUsed;
    int numberOfCor;
    Random rand = new Random();
    Grid()
    {
        coordinates = new ArrayList<>();
        numberOfCor = rand.nextInt(20)+1;
        idsUsed = new ArrayList<>();
    }

    public void generateCor()
    {
        for(int i=0;i<numberOfCor;i++)
        {
            int x = rand.nextInt(21)-10;
            int y = rand.nextInt(21)-10;

            while(locUsed(x, y))
            {
                x = rand.nextInt(21)-10;
                y = rand.nextInt(21)-10;
            }

            int eventID = rand.nextInt(401)-1;
            while(idUsed(eventID))
            {
                eventID = rand.nextInt(401)-1;
            }

            idsUsed.add(eventID);
            Coordinate temp = new Coordinate(x, y, eventID);
            temp.generateTickets();
            coordinates.add(temp);
        }
    }

    public boolean locUsed(int x, int y)
    {
        for(Coordinate c:coordinates)
        {
            if(c.x==x && c.y==y)
            {
                return true;
            }
        }
        return false;
    }

    public boolean idUsed(int eventID)
    {
        return idsUsed.contains(eventID);
    }

    public void getCloset(int x, int y) {
        PriorityQueue<Coordinate> pq =
                new PriorityQueue<>((a, b)-> (Math.abs(b.x-x) + Math.abs(b.y-y)) - (Math.abs(a.x-x) + Math.abs(a.y-y)));

        for(int i=0;i<coordinates.size();i++)
        {
            pq.add(coordinates.get(i));
            if(pq.size()>5)
            {
                pq.poll();
            }
        }

        List<Coordinate> res = new ArrayList<>();
        List<Integer> prices = new ArrayList<>();
        while(!pq.isEmpty()) {
            Coordinate poped = pq.poll();
            int cheapestPrice = getCheapPrice(poped);
            System.out.println("Closet coordinate is " + poped.x + " " + poped.y +
                    " whose eventID is " + poped.eventID + " and cheapest Price ticket is " + cheapestPrice);
        }
    }

    private int getCheapPrice(Coordinate poped) {
        List<Ticket> tickets = poped.tickets;
        int min=Integer.MAX_VALUE;
        for(Ticket t:tickets)
        {
            min=Math.min(min, t.price);
        }
        return min;
    }
}