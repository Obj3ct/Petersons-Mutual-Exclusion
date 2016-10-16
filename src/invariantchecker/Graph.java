
package invariantchecker;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph<E> {
    
    protected Map<E, List<E>> data;
    
    public Graph() {
        data = new LinkedHashMap<>();
    }
    
    public void addVertex(E vertex) {
        
        if(data.containsKey(vertex))
            return;
        
        List<E> list = new LinkedList<>();
        data.put(vertex, list);
    }
    
    public void removeVertex(E vertex) {
        
        if(!data.containsKey(vertex))
            return;
        
        data.remove(vertex);
    }
    
    public void addEdge(E vertexA, E vertexB) {
        
        if(!data.containsKey(vertexA) || !data.containsKey(vertexB))
            return;
        
        List<E> list = data.get(vertexA);
        
        if(!list.contains(vertexB))
            list.add(vertexB);
    }
    
    public void removeEdge(E vertexA, E vertexB) {
     
        if(!data.containsKey(vertexA) || !data.containsKey(vertexB))
            return;
        
        List<E> list = data.get(vertexA);
        
        if(list.contains(vertexB))
            list.remove(vertexB);
    }
    
    public Iterator<E> getEdgesForVertex(E vertex) {
        return data.get(vertex).iterator();
    }
    
    public void printInfo() {

        data.keySet().stream().forEach((key) -> {
            System.out.println("Vertex: " + key.toString());
            
                //for(E e : data.get(key))
                //   System.out.println("\t" + e);
        });
    }
    
    public Iterator<E> keyIterator() {
        return data.keySet().iterator();
    }
    
    public Iterator<List<E>> valuesIterator() {
        return data.values().iterator();
    }
    
    public boolean containsNode(E node) {
        return data.containsKey(node);
    }
    
    public int size() {
        return data.size();
    }
}
