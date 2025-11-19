import de.tudresden.sumo.cmd.Simulation;
import de.tudresden.sumo.util.SumoCommand;
import it.polito.appeal.traci.SumoTraciConnection;

void main() {

    String configFile = "Resources/test4.sumocfg";
    // Select Windows (.exe) or UNIX binary based on static function Util.getOSType()
    String sumoBinary = Util.getOSType().equals("Windows")
            ? "Resources/sumo-gui.exe"
            : "Resources/sumo-gui";

    System.out.println(sumoBinary);

    SumoTraciConnection connection = new SumoTraciConnection(sumoBinary, configFile);

    try {
        // Connection has been established
        connection.addOption("delay", "30");
        connection.addOption("start", "true");
        connection.addOption("quit-on-end", "true");
        connection.runServer();
        System.out.println("Verbindung erfolgreich: Simulationsschritt ausgeführt.");
        int step = 1;

        //----------------
        Vehicle2 v2 = null;
        //v2.setSpeed(50);
        //-----------------

        while (step <= 36) {
            connection.do_timestep();
            if (step == 1) {
                // t_0 is active starting here and can be called upon
                v2 = new Vehicle2(connection);
                v2.setSpeed(50);
            }
            //SumoCommand simTime = Simulation.getTime();
            if (step <= 9) {
                System.out.println("Speed " +step +" is: " + v2.getSpeed());
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
}

