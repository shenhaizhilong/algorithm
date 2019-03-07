package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/4 16:19
 *
 *913. Cat and Mouse
 * DescriptionHintsSubmissionsDiscussSolution
 * A game on an undirected graph is played by two players, Mouse and Cat, who alternate turns.
 *
 * The graph is given as follows: graph[a] is a list of all nodes b such that ab is an edge of the graph.
 *
 * Mouse starts at node 1 and goes first, Cat starts at node 2 and goes second, and there is a Hole at node 0.
 *
 * During each player's turn, they must travel along one edge of the graph that meets where they are.  For example, if the Mouse is at node 1, it must travel to any node in graph[1].
 *
 * Additionally, it is not allowed for the Cat to travel to the Hole (node 0.)
 *
 * Then, the game can end in 3 ways:
 *
 * If ever the Cat occupies the same node as the Mouse, the Cat wins.
 * If ever the Mouse reaches the Hole, the Mouse wins.
 * If ever a position is repeated (ie. the players are in the same position as a previous turn, and it is the same player's turn to move), the game is a draw.
 * Given a graph, and assuming both players play optimally, return 1 if the game is won by Mouse, 2 if the game is won by Cat, and 0 if the game is a draw.
 *
 *
 *
 * Example 1:
 *
 * Input: [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
 * Output: 0
 * Explanation:
 * 4---3---1
 * |   |
 * 2---5
 *  \ /
 *   0
 *
 *
 * Note:
 *
 * 3 <= graph.length <= 50
 * It is guaranteed that graph[1] is non-empty.
 * It is guaranteed that graph[2] contains a non-zero element.
 *
 */
public class CatandMouse {

    private static final int Unknown = 0;
    private static final int Tie = -1;  // re-define tie to -1, to avoid init memo
    private static final int MouseWin = 1; // mouse win, mouse play optimally (win 1 > tie 0 > lose 2)
    private static final int CatWin = 2; // cat win, cat play optimally (win 2 > tie 0 > lose 1)

    public int catMouseGame(int[][] graph) {
        int N = graph.length;
        int[][] memo = new int[N][N];
        // memo[i][j] , i is the mouse position, j is the cat position.
        memo[0][0] = MouseWin; // cat can't in the hole
        for (int i = 1; i < N; i++) {
            memo[0][i] = MouseWin;  // mouse first reaches the hole
            memo[i][i] = CatWin; // cat catch mouse in the same node.
        }
        int ans = dfs(graph, 1, 2, memo);
      //  Matrix.print(memo);
        return Math.max(0,ans);

    }

    private int dfs(int[][] graph, int mouse, int cat, int[][] memo)
    {
        if(memo[mouse][cat] != Unknown)return memo[mouse][cat];

        memo[mouse][cat] = Tie; // if we re-visited this position, means there is a cycle, then it should be tie;

        int[] mouseNeighbours = graph[mouse];  // mouse next step
        int[] catNeighbours = graph[cat];      // cat next step

        int mouseDefault = CatWin; // first we set mouse to lose, we will update it later.
        for(int mouseNext: mouseNeighbours)
        {
            if(mouseNext == cat)continue; // mouse can't go for a cat...

            int catDefault = MouseWin;// first se set cat to lose, we will update it later.
            for(int catNext: catNeighbours)
            {
                if(catNext == 0)continue; // cat can't go to hole
                int next = dfs(graph, mouseNext, catNext, memo);
                if(next == CatWin)
                {
                    catDefault = CatWin;// cat win, doesn't need to continue
                    break;
                }
                if(next == Tie)
                {
                    catDefault = Tie;  //tie is better than MouseWin, cat and mouse tie, we need to continue, since maybe cat can win
                }
            }

            if(catDefault == MouseWin)
            {
                mouseDefault = MouseWin;  // mouse win, doesn't need to continue
                break;
            }

            if(catDefault == Tie)
            {
                mouseDefault = Tie;   //tie is better than CatWin, cat and mouse tie, we need to continue, since maybe mouse can win
            }

        }

        memo[mouse][cat] = mouseDefault;
        return mouseDefault;
    }

    public static void main(String[] args) {

        int[][] graph = {{2,5},{3},{0,4,5},{1,4,5},{2,3},{0,2,3}};
        int[][] graph2 = {{6},{4},{9},{5},{1,5},{3,4,6},{0,5,10},{8,9,10},{7},{2,7},{6,7}};
        CatandMouse catandMouse = new CatandMouse();
        System.out.println(catandMouse.catMouseGame(graph));
        System.out.println(catandMouse.catMouseGame(graph2));

    }
}
