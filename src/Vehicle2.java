import de.tudresden.sumo.cmd.Simulation;
import de.tudresden.sumo.cmd.Vehicle;
import it.polito.appeal.traci.SumoTraciConnection;


public class Vehicle2 {
    String id;
    SumoTraciConnection con;

    public Vehicle2(SumoTraciConnection con) {
        id = "t_0";
        this.con = con;
    }

    public double getSpeed() {
        try {
            return (double)con.do_job_get(Vehicle.getSpeed(id)); // vehicle.getspeed returns SumoCommand
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setSpeed(int x) {
        try {
            con.do_job_set(Vehicle.setSpeed(id, 10));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
