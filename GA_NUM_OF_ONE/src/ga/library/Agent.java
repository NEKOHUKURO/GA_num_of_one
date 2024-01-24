package ga.library;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Agent {
    public static int NUM_OF_AGENT = 0;
    public static int GENOME_LENGT = 0;
    public boolean[] genome;
    public Long id;
    public static Agent AgentSet[];
    public static Agent SubAgentSet[];
    public static int GENERATION = 0;
    public static Long NUM_OF_SUB_SET = 0L;
    private static boolean isSetValue = false;
    private static double ERROR_RATE;
    private static boolean isSorted = false;

    public Agent() throws Exception{
        this.id = Agent.NUM_OF_SUB_SET;
        Agent.NUM_OF_SUB_SET++;
        this.genome = new boolean[GENOME_LENGT];
    }

    public static <T extends Agent> void InitValue(int NUM_OF_AGENT, int GENOME_LENGT, double ERROR_RATE) throws Exception {
        Agent.NUM_OF_AGENT = NUM_OF_AGENT;
        Agent.AgentSet = new Agent[NUM_OF_AGENT];
        Agent.SubAgentSet = new Agent[NUM_OF_AGENT];
        Agent.GENOME_LENGT = GENOME_LENGT;
        Agent.ERROR_RATE = ERROR_RATE;
        Agent.isSetValue = true;
        
    }

    public static <T extends Agent> void InitSet(T[] set) throws Exception {
        Agent.AgentSet = new Agent[NUM_OF_AGENT];
        Agent.SubAgentSet = new Agent[NUM_OF_AGENT];
        Agent.isSetValue = true;
        for(int i=0; i < Agent.NUM_OF_AGENT; i++) {
            Agent.AgentSet[i] = set[i];
        }
    }

    static int matching() throws Exception{
        throw new Exception("undefined matching");
    }

    public double evaluate() throws Exception{
        throw new Exception("undefined evaluate");
    }

    public int actionSelect() throws Exception{
        throw new Exception("undefined moveSelect");
    }

    public <T extends Agent> void makeDescendants(T fianse, T child) throws Exception{
        if (isSetValue == false) {
            throw new Exception("must set initial value");
        }

        Random random = new Random();
        double u=0;
        for (int i = 0; i < Agent.GENOME_LENGT; i++) {
            if (random.nextDouble() < Agent.ERROR_RATE) {
                u++;
                child.genome[i] = random.nextBoolean();
            } else if(random.nextBoolean()) {
                child.genome[i] = this.genome[i];
            } else {
                child.genome[i] = fianse.genome[i];
            }
        }
    }

    public void makeInItGenome() throws Exception{
        if (isSetValue == false) {
            throw new Exception("must set initial value");
        }
        Random random = new Random();
        for(int i=0; i<Agent.GENOME_LENGT; i++) {
            this.genome[i] = random.nextBoolean();
        }
    }

    public static <T extends Agent> void shiftAgent() throws Exception{
        if (isSetValue == false) {
            throw new Exception("must set initial value");
        }
        Agent.isSorted = false;
        for(int i = 0;i < Agent.NUM_OF_AGENT; i++) {
            Agent swp = Agent.AgentSet[i];
            Agent.AgentSet[i] = Agent.SubAgentSet[i];
            Agent.SubAgentSet[i] = swp;
        }
    }
    public static void sortAgent() throws Exception{
        if (isSetValue == false) {
            throw new Exception("must set initial value");
        }
        Agent.isSorted = true;
        Comparator<Agent> comparator = new Comparator<Agent>() {
            @Override
            public int compare(Agent obj1, Agent obj2) {
                try {
                    if(obj2.evaluate() > obj1.evaluate()) return 1;
                } catch (Exception e) {
                    return 0;
                }
                return -1;
            }
        };
        Arrays.sort(AgentSet, comparator);
    }
}
