package com.hui.Geometry;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/24 1:26
 *
 *
 * 959. Regions Cut By Slashes
 * DescriptionHintsSubmissionsDiscussSolution
 * In a N x N grid composed of 1 x 1 squares, each 1 x 1 square consists of a /, \, or blank space.  These characters divide the square into contiguous regions.
 *
 * (Note that backslash characters are escaped, so a \ is represented as "\\".)
 *
 * Return the number of regions.
 *
 *
 *
 * Example 1:
 *
 * Input:
 * [
 *   " /",
 *   "/ "
 * ]
 * Output: 2
 * Explanation: The 2x2 grid is as follows:
 *
 * Example 2:
 *
 * Input:
 * [
 *   " /",
 *   "  "
 * ]
 * Output: 1
 * Explanation: The 2x2 grid is as follows:
 *
 * Example 3:
 *
 * Input:
 * [
 *   "\\/",
 *   "/\\"
 * ]
 * Output: 4
 * Explanation: (Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.)
 * The 2x2 grid is as follows:
 *
 * Example 4:
 *
 * Input:
 * [
 *   "/\\",
 *   "\\/"
 * ]
 * Output: 5
 * Explanation: (Recall that because \ characters are escaped, "/\\" refers to /\, and "\\/" refers to \/.)
 * The 2x2 grid is as follows:
 *
 * Example 5:
 *
 * Input:
 * [
 *   "//",
 *   "/ "
 * ]
 * Output: 3
 * Explanation: The 2x2 grid is as follows:
 *
 *
 *
 * Note:
 *
 * 1 <= grid.length == grid[0].length <= 30
 * grid[i][j] is either '/', '\', or ' '.
 *
 */
public class RegionsCutBySlashes {

    // core idea is transfer the geometric to kn*kn matrix, which is digit image
    // up -scale it to 3n*3n; why 3n*3n ? 2n*2n is too thick, which made it is not correctly
    // for an example,
    //, grid is ['/','/'. '\'] , if we up-scale it to 2n2n, there are 5 islands,
    // island 2 and island 3 should be in one island rather than 2 islands.
    // 4n*4n also works, but it will cost too much

    public int regionsBySlashes(String[] grid) {

        int N = grid.length;
        boolean[][] image = new boolean[3*N][3*N];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                if(grid[i].charAt(j) == '/')
                {
                    image[3*i][3*j +2] = true;
                    image[3*i +1][3*j +1]= true;
                    image[3*i +2][3*j] = true;
                }else if(grid[i].charAt(j) == '\\')
                {
                    image[3*i][3*j] = true;
                    image[3*i +1][3*j +1]= true;
                    image[3*i +2][3*j +2] = true;
                }
            }
        }

        // island count
        int ans = 0;
        for (int i = 0; i < 3 * N; i++) {
            for (int j = 0; j < 3 * N; j++) {
                if(!image[i][j])
                {
                    ans++;
                    dfs(image, i,j);
                }
            }
        }

        return ans;

    }

    // island count
    // visit this island and marked it as visited.
    private void dfs(boolean[][] image, int i, int j)
    {
        if(i < 0 || j < 0 || i > image.length -1 || j > image.length -1 || image[i][j] == true)return;
        // mark this position as visited.
        image[i][j] = true;
        dfs(image, i +1, j);
        dfs(image, i -1, j);
        dfs(image, i, j +1);
        dfs(image, i, j -1);
    }



}
