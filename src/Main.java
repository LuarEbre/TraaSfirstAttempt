import de.tudresden.sumo.cmd.Simulation;
import de.tudresden.sumo.util.SumoCommand;
import it.polito.appeal.traci.SumoTraciConnection;

void main() {

    String sumoBinary = "Resources/sumo.exe";
    String configFile = "Resources/2.sumocfg";

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
        while (step <= 36) {
            connection.do_timestep();
            //SumoCommand simTime = Simulation.getTime();
            double timeSeconds = (double)connection.do_job_get(Simulation.getTime()); // könnte methode sein
            System.out.println("Time: " + timeSeconds);
            step++;
        }
    } catch (Exception e) {
        System.out.println("Verbindung fehlgeschlagen: " + e.getMessage());
    } finally {
        connection.close();
    }


}

