import de.tudresden.sumo.cmd.Simulation;
import de.tudresden.sumo.util.SumoCommand;
import it.polito.appeal.traci.SumoTraciConnection;

void main() {

    String sumoBinary;
    String os = System.getProperty("os.name");
    if (os.toLowerCase().contains("windows")) {
        sumoBinary = "Resources/sumo-gui.exe";
    }
    else {
        sumoBinary = "Resources/sumo-gui";
    }
    System.out.println("User is running: " + os);
    System.out.println("Thus the binary path is: " + sumoBinary);


    String configFile = "Resources/test4.sumocfg";

    //SumoTraciConnection connection = new SumoTraciConnection(sumoBinary, configFile);
    SumoTraciConnection connection = new SumoTraciConnection(sumoBinary, configFile);

    try {
        // Verbindung aufgebaut
        connection.addOption("delay", "30");
        connection.addOption("start", "true");
        connection.addOption("quit-on-end", "true");
        connection.runServer();
        System.out.println("Verbindung erfolgreich: Simulationsschritt ausgeführt.");
        int step = 1;

        //----------------
        Vehicle2 v2 = null;
        //v2.setSpeed(50);
        //System.out.println("speed war:" + v2.getSpeed());
        //-----------------

        while (step <= 36) {
            connection.do_timestep();
            if (step == 1) {
                // Hier ist t_0 jetzt aktiv und kann abgefragt werden.
                v2 = new Vehicle2(connection);
                v2.setSpeed(50);
            }
            //SumoCommand simTime = Simulation.getTime();
            if (step <= 9) {
                System.out.println("Speed " +step +" ist: " + v2.getSpeed());
            }
            double timeSeconds = (double)connection.do_job_get(Simulation.getTime()); // könnte methode sein
            System.out.println("Time: " + timeSeconds);
            step++;
        }
    } catch (Exception e) {
        System.out.println("Connection failed: " + e.getMessage());
    } finally {
        connection.close();
    }
}

