import de.tudresden.sumo.cmd.Simulation;
import de.tudresden.sumo.util.SumoCommand;
import it.polito.appeal.traci.SumoTraciConnection;

void main() throws Exception {

    String sumoBinary = "C:/Program Files (x86)/Eclipse/Sumo/bin/sumo-gui.exe";
    String configFile = "C:/Users/memem/Downloads/Code/Sumo Networks/mysecondconfig.sumocfg";

    SumoTraciConnection connection = new SumoTraciConnection(sumoBinary, configFile);

    // commandline commands -> --...
    connection.addOption("delay", "200");
    connection.addOption("start", "true");
    connection.addOption("quit-on-end", "true");

    connection.runServer();

    int step = 0;
    while (step < 50) {

            connection.do_timestep();

            SumoCommand simTime = Simulation.getTime();
            System.out.println("Time: " + simTime);

            step++;
    }

    connection.close();
}

