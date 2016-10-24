/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invariantchecker;

import java.util.HashSet;

/**
 *
 * @author Azielio
 */
public class State {
    public boolean reachable;
    public String name;
    public String[] labels;
    public boolean initialState;
    public HashSet<State> POSTs;
    public HashSet<State> PREs;
    
    
    public State(String name, String[] labels, boolean initialState, HashSet<State> POSTs, 
                    HashSet<State> PREs) {
        // Initialize State variables
        this.reachable = false;
        this.name = name;
        this.labels = labels;
        this.initialState = initialState;
        this.POSTs = POSTs;
        this.PREs = PREs;
    }
    
    public State() {
        this.POSTs = new HashSet();
        this.PREs = new HashSet();
    }
}
