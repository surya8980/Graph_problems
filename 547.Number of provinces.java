class Solution {
    public static void dfs(int node,ArrayList<ArrayList<Integer>>adjls,int[] vis ){
        vis[node]=1;
        for(int it: adjls.get(node)){
            if(vis[it]==0){
                dfs(it,adjls,vis);
            }
        }
    }
    public int findCircleNum(int[][] isConnected) {
        ArrayList<ArrayList<Integer>> adjls = new ArrayList<ArrayList<Integer>>();
        /*traverse by bfs or dfs
        for 1st time it calls dfs, it visits 1 and 2 next it  will again call dfs to visit 3
        like this how many times the dfs is called that is returned*/
        int V = isConnected.length;
        for(int i=0;i<V;i++){
            adjls.add(new ArrayList<Integer>());
        }
        //change the matrix into list
        for(int i=0;i<V;i++){
            for(int j=0;j<V;j++){
                if(isConnected[i][j]==1&&i!=j){
                    adjls.get(i).add(j);
                    adjls.get(j).add(i);
                }
            }
        }
        int vis [] = new int[V];
        int count =0;
        for(int i=0;i<V;i++){
            if(vis[i]==0){
                count++;
                dfs(i,adjls,vis);
            }
        }
        return count;        
    }
}