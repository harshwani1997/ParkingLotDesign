import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketSystem
{
    public static void main(String[] args)
    {
        Grid grid = new Grid();
        grid.generateCor();

           for(int i=0;i<grid.coordinates.size();i++)
           {
               Coordinate cor = grid.coordinates.get(i);
               List<Ticket> tickets = cor.tickets;
               List<Integer> prices = new ArrayList<>();
               for(int t=0;t<tickets.size();t++)
               {
                   prices.add(tickets.get(t).price);
               }

               System.out.println("The coordinate with eventID " + cor.eventID + " and location " + cor.x + " " + cor.y + " has following ticket prices: ");
               System.out.print(prices);
               System.out.println();
           }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Input your location coordinates ");
        int x = -1;
        int y = -1;

        boolean valid = false;
        while(!valid)
        {
            try
            {
                x = scanner.nextInt();
                y = scanner.nextInt();
                checkInput(x, y);
                valid = true;
            }
            catch(IndexOutOfBoundsException e)
            {
               System.out.println(e);
               valid=false;
            }
        }

        System.out.println("Coors are " + x + " and " + y);
        grid.getCloset(x, y);
    }

    private static boolean checkInput(int x, int y) throws IndexOutOfBoundsException{
        if(x<-10 || x>10 || y<-10 || y>10)
        {
            throw new IndexOutOfBoundsException("It is out of bounds. Try again");
        }
        return true;
    }
}