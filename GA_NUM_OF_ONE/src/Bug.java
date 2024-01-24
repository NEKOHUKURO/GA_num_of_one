import java.util.Random;

import ga.library.Agent;

public class Bug extends Agent{
    public static void main(String arg[]) throws Exception {
        Bug AgentSet[] = new Bug[20];
        Bug.InitValue(20, 15, 0.05);
        for(int i=0; i < 20; i++) {
            AgentSet[i] = new Bug();
            AgentSet[i].makeInItGenome();
        }
        Bug.InitSet(AgentSet);
        
        Bug.sortAgent();
        System.out.println("-----------------ソート--------------------");
        for(int i = 0; i < Agent.NUM_OF_AGENT; i++) {
            System.out.print((Bug.AgentSet[i]).evaluate() + " : ");
            for(int j = 0; j < Agent.GENOME_LENGT; j++) {
                System.out.print(" "+(Bug.AgentSet[i].genome[j] ? 1:0));
            }
                            
            System.out.println();
        }
        for(int loop = 0; loop<500; loop++) {
            Bug.sortAgent();
            int index = 0;
            for(int j = 0; j<3; j++) {
                for (int k = 0; k<4;k++) {
                    Bug child = new Bug();
                    int f = Bug.matching();
                    if(Bug.AgentSet[j].id == Bug.AgentSet[f].id){
                        k--;
                        continue;
                    }
                    Bug.AgentSet[j].makeDescendants(Bug.AgentSet[f], child);
                    Bug.SubAgentSet[index] = child;
                    index++;
                }
            }
            for(int j = 3; j<7; j++) {
                for (int k = 0; k<2;k++) {
                    Bug child = new Bug();
                    int f = Bug.matching();
                    Bug.AgentSet[j].makeDescendants(Bug.AgentSet[f], child);
                    Bug.SubAgentSet[index] = child;
                    index++;
                }
            }
            Bug.shiftAgent();
        }
        Bug.sortAgent();
        System.out.println("-----------------ソート--------------------");
        for(int i = 0; i < Agent.NUM_OF_AGENT; i++) {
            System.out.print((Bug.AgentSet[i]).evaluate() + " : ");
            for(int j = 0; j < Agent.GENOME_LENGT; j++) {
                System.out.print(" "+(Bug.AgentSet[i].genome[j] ? 1:0));
            }
                            
            System.out.println();   
        }
    }
    

    Bug() throws Exception{
        super();
    }

    static int matching(){
        Random r = new Random();
        double x = r.nextDouble();
        int index = (int)((double)Bug.NUM_OF_AGENT *x*x);
        if (index == (double)Bug.NUM_OF_AGENT) {
            return Bug.NUM_OF_AGENT-1;
        }
        return index;
    }

    @Override
    public double evaluate() throws Exception{
        double e = 0;
        for (int i =0; i < this.genome.length; i++) if(this.genome[i])e++;
        return e;
    }

    @Override
    public int actionSelect() throws Exception{
        throw new Exception("undefined moveSelect");
    }

        public void makeInItGenome() throws Exception{
        Random random = new Random();
        for(int i=0; i<Agent.GENOME_LENGT; i++) {
            this.genome[i] = (random.nextDouble() < 1.0/15);
        }
    }
}
