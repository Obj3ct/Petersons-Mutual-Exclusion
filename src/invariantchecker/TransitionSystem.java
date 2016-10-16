
package invariantchecker;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class TransitionSystem {
    
    private final int numOfProcesses;
    private Graph<StateNode> graph;
    
    public TransitionSystem(int numOfProcesses) {
        this.numOfProcesses = numOfProcesses;
        graph = new Graph<>();
        buildGraph();
    }
    
    public static void main(String[] args) {
        
        // numOfProcesses <= 6 (otherwise too slow)
        int numOfProcesses = 2;
        
        if(numOfProcesses < 2)
            System.out.println("Process count must be >= 2");
        else {
            TransitionSystem ts = new TransitionSystem(numOfProcesses);
            ts.printTransitionSystem();
        }
    }
    
    private void buildGraph() {
        
        // Processing queue
        Queue<StateNode> queue = new LinkedList<>();
        // Initial state
        int[] initialArr = new int[numOfProcesses];
        int[] initialY = new int[numOfProcesses]; // 0..n-1
        int[] initialP = new int[numOfProcesses]; // 1..n
        int[] initialJ = new int[numOfProcesses];
        Arrays.fill(initialJ, 1);
        // Set up initial arrays
        for(int x = 0; x < numOfProcesses; x++) {
            initialY[x] = x;
            initialP[x] = x + 1;
        }
            
        StateNode initialState = new StateNode(initialArr, initialY, initialP, initialJ);
        queue.add(initialState);
        
        while(!queue.isEmpty()) {
            // Get current state
            StateNode current = queue.remove();
            int[] currentStates = current.getProcessStates();
            int[] currentJ = current.getJ();
            int[] currentP = current.getP();
            int[] currentY = current.getY();
            // Iterate length of processes
            for(int i = 0; i < currentStates.length; i++) {
                // Copy original process array values
                int[] statesCopy = new int[currentStates.length];
                System.arraycopy(currentStates, 0, statesCopy, 0, currentStates.length);
                int[] jCopy = new int[currentJ.length];
                System.arraycopy(currentJ, 0, jCopy, 0, currentJ.length);
                int[] pCopy = new int[currentP.length];
                System.arraycopy(currentP, 0, pCopy, 0, currentP.length);
                int[] yCopy = new int[currentY.length];
                System.arraycopy(currentY, 0, yCopy, 0, currentY.length);
                
                switch(StateOptions.values()[statesCopy[i]]) {
                    case NONCRIT:
                        // Go to FORALL
                        statesCopy[i]++;
                        break;
                    case FORALL:
                        // Go to WAIT
                        if(jCopy[i] <= numOfProcesses - 1) {

                            pCopy[i] = jCopy[i];
                            yCopy[jCopy[i]] = i;
                            statesCopy[i]++;
                        }
                        // Go to CRIT
                        else {
                            statesCopy[i] += 2;
                        }
                        break;
                    case WAIT:
                        if(yCopy[jCopy[i]] != i) {
                            // GO back to FOR ALL 
                            statesCopy[i]--;
                            jCopy[i]++;
                        }                                    
                        break;
                    case CRIT:
                        pCopy[i] = 0;
                        // GO back to NONCRIT
                        statesCopy[i]++;
                        break;
                }
               
                // Mod processVal to size of possible options
                statesCopy[i] %= StateOptions.values().length;
                // Produce next state
                StateNode next = new StateNode(statesCopy, yCopy, pCopy, jCopy);
                // Inseret for evaluation and storage
                if(!graph.containsNode(next)) {
                    // Add node
                    graph.addVertex(next);
                    // Add to queue for processing
                    queue.add(next);
                }
                // Add edge from head to next
                graph.addEdge(current, next);
            }
        }
    }
    
    private void printTransitionSystem() {
        
        System.out.println("--- Printing Transition System ---");
        
        // States
        Iterator<StateNode> stateIterator = graph.keyIterator();
        StateOptions[] options = StateOptions.values();
        while(stateIterator.hasNext()) {
            StateNode next = stateIterator.next();
            
            int[] processStates = next.getProcessStates();
            
            System.out.print("<");
            for(int i = 0; i < processStates.length; i++) {
                System.out.print(options[i].toString().toLowerCase() + "" + i);
                
                if(i < processStates.length - 1)
                    System.out.print(", ");
            }
            System.out.print(">\n");
        }
            
        
        // Transition Relations (->)
        
        // Initial
        
        // Labels (L)
        
        System.out.println("--- Printing Complete");
    }
}
