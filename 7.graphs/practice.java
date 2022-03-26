import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class practice {

    class Edge {
        int v, w;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }


    public static int findEdge(ArrayList<Edge>[] graph,int u,int v) {
        for(int i=0;i<graph[u].size();i++){

            Edge e = graph[u].get(i);
            if(e.v == v) 
                return i;
        }

        return -1;
    }


    public static void dfs_compo(ArrayList<Edge>[] graph,int src,boolean[] vis) {

         vis[src] = true;

         for(Edge e:graph[src]) {
             if(!vis[e.v]) {
                 dfs_compo(graph, e.v, vis);
             }
         }
    }

    public static void gcc(ArrayList<Edge>[] graph)
    {
        int N = graph.length;

        boolean vis[] = new boolean[N];

        int component = 0;
        for(int i=0;i<N;i++){
            if(!vis[i])
           {    component++;
                dfs_compo(graph,i,vis);}
        }
    }

    // 785
    
    public boolean isBipartite(int[][] graph,int src,int[] vis) {
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);

        int color = 0;

        while(que.size() != 0) {
            int size = que.size();
            while(size-- > 0){
                int rvtx = que.removeFirst();
                if(vis[rvtx] != -1) 
                {
                    if(color != vis[rvtx])
                    {
                        return false;
                    }
                    continue;
                }

                vis[rvtx] = color;
                for(int v:graph[rvtx]) 
                {
                    if(vis[v] == -1)
                    {
                        que.addLast(v);
                    }
                }
            }
            color = (color + 1) % 2;
        }
        return true;
    }

    public boolean isBipartite(int[][] graph) {

        int n = graph.length;
        int[] vis = new int[n];
        Arrays.fill(vis, -1);

        for(int i=0;i<n;i++) {
            if(vis[i] == -1 && !isBipartite(graph,i,vis)) return false;
        }

        return true;
    }
}
