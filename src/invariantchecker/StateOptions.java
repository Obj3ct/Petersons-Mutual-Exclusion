
package invariantchecker;

public enum StateOptions {
        
    NONCRIT(0),
    FORALL(1),
    WAIT(2), 
    CRIT(3);
        
    private int weight;
    
    private StateOptions(int weight) {
        this.weight = weight;
    }
    
    public int getWeight() {
        return this.weight;
    }
}
