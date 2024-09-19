class DisjointSet{
    List<Integer> parent = new ArrayList<>();
    List<Integer> size=new ArrayList<>();
    public DisjointSet(int n){
        for(int i=0;i<=n;i++){
            parent.add(i);
            size.add(1);
        }
    }
    public int findpar(int n){
        if(n==parent.get(n)) return n;
        int ulp_n=findpar(parent.get(n));
        parent.set(n,ulp_n);
        return parent.get(n);
    }
    public void UnionBySize(int u,int v){
        int ulp_u=findpar(u);
        int ulp_v= findpar(v);
        if(ulp_u==ulp_v) return;
        if(size.get(ulp_u)<size.get(ulp_v)){
            parent.set(ulp_u,ulp_v);
            size.set(ulp_v,size.get(ulp_u)+size.get(ulp_v));
        }else{
            parent.set(ulp_v,ulp_u);
            size.set(ulp_u,size.get(ulp_v)+size.get(ulp_u));
        }
    }
}
class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        //create a graph of type 3
        //create a graph of type 2
        //create a graph of type 1
        //count number of edges who are already connected
        ArrayList<ArrayList<Integer>> type3 = new ArrayList<>();
        for(int[]row:edges){
            int a = row[0];
            int b=row[1];
            int c=row[2];
            if(a==3){
                ArrayList<Integer> pair = new ArrayList<>();
                pair.add(b);
                pair.add(c);
                type3.add(pair);
            }
        }
        ArrayList<ArrayList<Integer>> type2 = new ArrayList<>();
        for(int[]row:edges){
            int a = row[0];
            int b=row[1];
            int c=row[2];
            if(a==2){
                ArrayList<Integer> pair = new ArrayList<>();
                pair.add(b);
                pair.add(c);
                type2.add(pair);
            }
        }
        ArrayList<ArrayList<Integer>> type1 = new ArrayList<>();
        for(int[]row:edges){
            int a = row[0];
            int b=row[1];
            int c=row[2];
            if(a==1){
                ArrayList<Integer> pair = new ArrayList<>();
                pair.add(b);
                pair.add(c);
                type1.add(pair);
            }
        }
        //iterate through type 3 edges
        // if(n==2&&edges[0][0]!=3) return -1;
        DisjointSet dsAlice = new DisjointSet(n);
        DisjointSet dsBob = new DisjointSet(n);
        int extraedge =0;
        for(int i=0;i<type3.size();i++){
            int u = type3.get(i).get(0);
            int v = type3.get(i).get(1);
            if(dsAlice.findpar(u)==dsAlice.findpar(v)&&dsAlice.findpar(u)==dsAlice.findpar(v)) extraedge++;
            dsAlice.UnionBySize(u, v);
            dsBob.UnionBySize(u,v);
        }
        for(int i=0;i<type2.size();i++){
            int u = type2.get(i).get(0);
            int v = type2.get(i).get(1);
            if(dsBob.findpar(u)==dsBob.findpar(v)) extraedge++;
            dsBob.UnionBySize(u, v);
        }
        for(int i=0;i<type1.size();i++){
            int u = type1.get(i).get(0);
            int v = type1.get(i).get(1);
            if(dsAlice.findpar(u)==dsAlice.findpar(v)) extraedge++;
            dsAlice.UnionBySize(u, v);
        }
        //if still there are any components that are not yet
        //beem visited/ connected by bob or alice then return -1
        // to check that if anyone's parent is himself other than ulp
        //i.e if 2 or more independent components are there return -1;
        int acomp=0;
        int bcomp =0;
        for(int i=1;i<=n;i++){
            if(dsAlice.findpar(i)==i) acomp++;
        }
        for(int i=1;i<=n;i++){
            if(dsBob.findpar(i)==i) bcomp++;
        }
        if(acomp>1||bcomp>1) return -1;
        return extraedge;
    }
}