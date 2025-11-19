import de.tudresden.sumo.cmd.Simulation;
import de.tudresden.sumo.cmd.Vehicle;
import it.polito.appeal.traci.SumoTraciConnection;


public class Vehicle2 {
    String id;
    SumoTraciConnection con;

    public Vehicle2(SumoTraciConnection con) {
        id = "f_0";
        this.con = con;
    }

    public int getSpeed() {
        try {
            return (int)con.do_job_get(Vehicle.getSpeed(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setSpeed(int x) {
        x = 50;
        Vehicle.setSpeed(id, x);
    }

}
