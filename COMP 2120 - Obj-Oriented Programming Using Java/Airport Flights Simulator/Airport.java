import java.util.LinkedList;
import java.util.Queue;

public class Airport
{
    public Queue<String> takeOff;
    public Queue<String> landing;
    public Queue<String> landed;
    public Queue<String> takenOff;
    private static int r = 0;

    public Airport()
    {
        takeOff = new LinkedList<>();
        landing = new LinkedList<>();
        landed = new LinkedList<>();
        takenOff = new LinkedList<>();
    }

    public Queue<String> addTakeOff(String flight_number)
    {
        takeOff.add(flight_number);
        return takeOff;
    }

    public Queue<String> addLanding(String flight_number)
    {
        landing.add(flight_number);
        return landing;
    }

    public Queue<String> getLanded(int i)
    {
        return landed;
    }

    public Queue<String> getTakenOff(int i)
    {
        return takenOff;
    }

    public String handleNextAction()
    {
        if (landing.size() > 0) {
            String flight = landing.remove();
            landed.add(flight);
            return "Flight " + flight + " is landing.";
        }
        if (takeOff.size() > 0) {
            String flight = takeOff.remove();
            takenOff.add(flight);
            return "Flight " + flight + " is taking off.";
        } else {
            return "No plane is waiting to land or take-off.";
        }
    }

    public Object waitingPlanes()
    {
        if (takeOff.size() > 0) {
            System.out.println("Planes waiting for take-off");
            System.out.println("---------------------------");
            return takeOff;
        } else if (landing.size() > 0) {
            System.out.println("Planes waiting for land.");
            return landing;
        } else
            return "No plane is in the landing and take-off queues.";
    }

    public String log()
    {
        if (takenOff.size() > 0 || landed.size() > 0) {
            int i;
            for (i = 0; i <= landed.size(); i++) {
                System.out.println("List of the landing/take-off activities");
                System.out.println("---------------------------------------");
                String result = "Flight " + landed + " landed.\n";;
                if(r++ != 0){
                    result += "Flight " + takenOff + "taken-off.";
                }
                return result;
            }
        }else
            return "No activity exists.";
        return null;
    }
}
