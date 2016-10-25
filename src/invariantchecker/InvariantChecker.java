
package invariantchecker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class InvariantChecker {
    ArrayList<State> states = new ArrayList<>();
    HashSet<State> R_reachableStates = new HashSet();
    HashSet<State> I_initialStates = new HashSet();
    Stack<State> U_emptyStack = new Stack();
    boolean B_satisfy = true;
    

    // fdfs = forward DFS
    public String fdfs() {
        // Initialise states with some values.
        initStates();
        
        while(!I_initialStates.isEmpty() && !R_reachableStates.isEmpty() && B_satisfy) {
            // This is messy. Not sure how to handle it.
            State s = (State) I_initialStates.toArray()[0];
            visit(s);
        }
        
        // Do all states in R satisfy phi?
        if(B_satisfy) {
            return "yes";
        } else {
            return "no, "; // Reverse List
        }
    }
    
    public void visit(State s) {
        U_emptyStack.push(s); // Push s on the stack        push(s, U);
        R_reachableStates.add(s); // Mark s as reachable    R := R U {s};
        do { // loop until either U is empty or not B
            State sPrime = U_emptyStack.peek();
            
            
            // Iterate through an array of all the POST states of sPrime
            for (State sP : (State[]) sPrime.POSTs.toArray()) {
                if(R_reachableStates.contains(sP)) {
                    U_emptyStack.pop();
                    B_satisfy = B_satisfy;// && satisfyConditions(String... conditions);
                } else {
                    // This is where it gets messy.
                    State sPP = null; //FIX THIS shouldn't be null.
                    
                    // push(s'', U);
                    U_emptyStack.push(sPP);
                    // R := R U {s''};
                    R_reachableStates.add(sPP);
                }
            }
        } while(U_emptyStack.empty() || !B_satisfy); //until ((U = e) V !b)
    } // endproc
    
    public boolean satisfyConditions(String... conditions) {
        return false;
    }
    
    
//    public boolean reachable;
//    public String name;
//    public String[] labels;
//    public boolean initialState;
//    public HashSet<State> POSTs;
//    public HashSet<State> PREs;
    
    public void initStates() {
        // TS from tutorial 2
        State s0 = new State();
        s0.name = "s0";
        s0.labels = new String[] {"a"};
        s0.initialState = true;
        
        State s1 = new State();
        s1.name = "s1";
        s1.labels = new String[] {};
        s1.initialState = false;
        
        State s2 = new State();
        s2.name = "s2";
        s2.labels = new String[] {"a", "b"};
        s2.initialState = false;
        
        State s3 = new State();
        s3.name = "s3";
        s3.labels = new String[] {"b"};
        s3.initialState = false;
        
        State s4 = new State();
        s4.name = "s4";
        s4.labels = new String[] {};
        s4.initialState = false;
        
        //POSTS
        s0.POSTs.add(s1);
        s0.POSTs.add(s2);
        
        s1.POSTs.add(s1);
        s1.POSTs.add(s3);
        s1.POSTs.add(s4);
        
        s2.POSTs.add(s0);
        s2.POSTs.add(s4);
        
        // S3 has no POSTS
        
        s4.POSTs.add(s3);
        s4.POSTs.add(s2);
        
        //PRES
        // Ommitted since it's not used in algorithm.
        
        //Adding to instance variables
        //R_reachableStates.add(s0); // "Arbirary initial state not in R"? Implies initial states aren't reachable?
        states.add(s0);
        states.add(s1);
        states.add(s2);
        states.add(s3);
        states.add(s4);
        
        I_initialStates.add(s0);
    }
    
    public void initStates2() {
        // TS from Assignment 1
        State s1 = new State();
        
        
        State s2 = new State();
        State s3 = new State();
        State s4 = new State();
        State s5 = new State();
    }
    
    
}
