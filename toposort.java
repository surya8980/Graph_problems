class Solution
{
    
    //Function to return list containing vertices in Topological order. 
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) 
    {
        int [] indegree = new int[V];
        for(int i=0;i<V;i++){
            for(int it:adj.get(i)){
                indegree[it]++;
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<V;i++){
            if(indegree[i]==0){
                q.add(i);
            }
        }
        
        int topo[] = new int[V];
        int i=0;
        while(!q.isEmpty()){
            int k = q.peek();
            q.remove();
            topo[i++]=k;
            //node is in the topo sort so remove it from adh=jacent node's indegree
            for(int it: adj.get(k)){
                indegree[it]--;
                if(indegree[it]==0){
                    q.add(it);
                }
            }
        }
        return topo;
    }
}