import de.tudresden.sumo.cmd.Simulation;
import de.tudresden.sumo.util.SumoCommand;
import it.polito.appeal.traci.SumoTraciConnection;

void main() throws Exception {

    String sumoBinary = "Resources/sumo-gui.exe";
    String configFile = "Resources/2.sumocfg";

    SumoTraciConnection connection = new SumoTraciConnection(sumoBinary, configFile);

    // commandline commands -> --...
    connection.addOption("delay", "30");
    connection.addOption("start", "true");
    connection.addOption("quit-on-end", "true");

    connection.runServer();

    int step = 0;
    while (step < 3600) {

            connection.do_timestep();

            SumoCommand simTime = Simulation.getTime();
            System.out.println("Time: " + simTime);

            step++;
    }

    connection.close();
}

