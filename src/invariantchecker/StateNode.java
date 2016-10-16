
package invariantchecker;

import java.util.Arrays;

public class StateNode {

    private int[] processStates;
    private int[] y;
    private int[] p;
    private int[] j;
    private boolean visited;
    
    public StateNode(int[] processStates, int[] y, int[] p, int[] j) {
        this.processStates = processStates;
        this.y = y;
        this.p = p;
        this.j = j;
        this.visited = false;
    }
    
    public int[] getProcessStates() {
        return processStates;
    }
    
    public int[] getY() {
        return y;
    }

    public int[] getP() {
        return p;
    }

    public int[] getJ() {
        return j;
    }
    
    public void visited() {
        this.visited = true;
    }
    
    public void notVisited() {
        this.visited = false;
    }
    
    public boolean isVisited() {
        return this.visited;
    }
    
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        for(int i : processStates)
            sb.append(i);
        sb.append(" Y: ");
        
        for(int i : y)
            sb.append(i);
        sb.append(" P: ");
        
        for(int i : p)
            sb.append(i);
        sb.append(" J: ");
        
        for(int i : j)
            sb.append(i);
        
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
    
        int hash = 1;
        hash += 997 + (processStates == null ? 0 : Arrays.hashCode(processStates));
        hash += 997 + (y == null ? 0 : Arrays.hashCode(y));
        hash += 997 + (p == null ? 0 : Arrays.hashCode(p));
        hash += 997 + (j == null ? 0 : Arrays.hashCode(j));
        hash += 991 + Boolean.hashCode(visited);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if (!StateNode.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final StateNode other = (StateNode) obj;
        if ((this.processStates == null) ? (other.processStates != null) : !Arrays.equals(this.processStates, other.processStates)) {
            return false;
        }
        if ((this.p == null) ? (other.p != null) : !Arrays.equals(this.p, other.p)) {
            return false;
        }
        if ((this.y == null) ? (other.y != null) : !Arrays.equals(this.y, other.y)) {
            return false;
        }
        if ((this.j == null) ? (other.j != null) : !Arrays.equals(this.j, other.j)) {
            return false;
        }
        if (this.visited != other.visited) {
            return false;
        }
        return true;
    }
}
