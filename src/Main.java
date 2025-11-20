import de.tudresden.sumo.cmd.Simulation;
import de.tudresden.sumo.util.SumoCommand;
import it.polito.appeal.traci.SumoTraciConnection;
import de.tudresden.sumo.cmd.Vehicle;

void main() {

    String configFile = "Resources/test5.sumocfg";
    String configFile2= "Resources/2.sumocfg";

    Vehicle2[] cars = new Vehicle2[50];

    // Select Windows (.exe) or UNIX binary based on static function Util.getOSType()
    String sumoBinary = Util.getOSType().equals("Windows")
            ? "Resources/sumo-gui.exe" // or sumo.exe to start without gui
            : "Resources/sumo-gui";

    System.out.println(sumoBinary);

    SumoTraciConnection connection = new SumoTraciConnection(sumoBinary, configFile);

    try {
        // Connection has been established
        connection.addOption("delay", "50");
        connection.addOption("start", "true");
        connection.addOption("quit-on-end", "true");
        connection.runServer();
        System.out.println("Connected to Sumo.");
        int step = 0;

        Vehicle2 v = null;

        while (step < 200) {
            connection.do_timestep();

            if(step == 1) {
                for (int i = 0; i < 50; i++) {
                    connection.do_job_set(Vehicle.addFull("v" + i, "r1", "t_0", // vehID declared, type Id in .rou
                            "now", "0", "0", "max",
                            "current", "max", "current", "",
                            "", "", 0, 0)
                    );
                    v = new Vehicle2("v" + i, connection);
                    v.setSpeed();
                    cars[i] = v; // cars saved in array -> list better
                }
            }

            if (step >=2 && step<37) { // after 36 the car despawns -> must check if still active later
                if (cars[0] != null) {
                    System.out.println("Speed for car  " + cars[0].getID() + " is: " + cars[0].getSpeed()); // testing first car
                }
            }

            if (step == 50) {
                if (cars[15] != null) {
                    System.out.println("SPEED FOR CAR " + cars[15].getID() + " IS: " + cars[15].getSpeed());
                }
            }
            double timeSeconds = (double)connection.do_job_get(Simulation.getTime()); // could be replaced by a method
            System.out.println("Time: " + timeSeconds);

            step++;
        }

    } catch (Exception e) {
        System.out.println("Connection failed: " + e.getMessage());
    } finally {
        connection.close();
    }

    // changing maps after connection is closed
    SumoTraciConnection connection2 = new SumoTraciConnection(sumoBinary, configFile2);
    try {
        connection2.addOption("delay", "100");
        connection2.addOption("start", "true");
        connection2.addOption("quit-on-end", "true");
        connection2.runServer();
        for (int i = 0; i < 360; i++) {
            connection2.do_timestep();
        }
    } catch (Exception e) {
        System.out.println("Connection failed: " + e.getMessage());
    }  finally {
        connection2.close();
    }

}

