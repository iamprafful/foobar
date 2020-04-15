import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static class Block {
        private int x;
        private int y;

        public Block(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    static class QueueNode {
        Block block;
        int dist;

        public QueueNode(Block block, int dist) {
            this.block = block;
            this.dist = dist;
        }
    };

    static int rowNum[] = { -1, 0, 0, 1 };
    static int colNum[] = { 0, -1, 1, 0 };

    public static void main(String[] args) {
        int[][] arr = {{0, 0, 0, 0, 0, 0},
                       {1, 1, 1, 1, 1, 0},
                       {1, 1, 1, 1, 1, 1},
                       {0, 0, 0, 0, 0, 0},
                       {0, 1, 1, 1, 1, 1},
                       {0, 0, 0, 0, 0, 0}};
        System.out.println(solution(arr));
    }

    public static int solution(int[][] map) {
        int count = 0;
        int rows = map.length;
        int cols = map[0].length;
        ArrayList<Block> walls = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map[i][j] == 1) {
                    walls.add(new Block(i, j));
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (Block block : walls) {
            map[block.getX()][block.getY()] = 0;
            count = bfs(map, rows, cols);
            if (min > count) {
                min = count;
            }
            map[block.getX()][block.getY()] = 1;
        }
        return min;
    }

    public static int bfs(int[][] map, int rows, int cols) {
        int count = Integer.MAX_VALUE;
        Queue<QueueNode> q = new LinkedList<>();
        Boolean[][] visited = new Boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                visited[i][j] = false;
            }
        }
        Block start = new Block(0, 0);
        QueueNode s = new QueueNode(start, 1);
        visited[0][0] = true;
        q.add(s);
        while (q.size() != 0) {
            QueueNode currNode = q.peek();
            Block block = currNode.block;
            if (block.getX() == rows - 1 && block.getY() == cols - 1) {
                return currNode.dist;
            }
            q.remove();
            for (int i = 0; i < 4; i++) {
                int row = block.getX() + rowNum[i];
                int col = block.getY() + colNum[i];
                if (((row >= 0) && (row < rows) && (col >= 0) && (col < cols)) && map[row][col] == 0
                        && !visited[row][col]) {
                    visited[row][col] = true;
                    QueueNode Adjcell = new QueueNode(new Block(row, col), currNode.dist + 1);
                    q.add(Adjcell);
                }
            }
        }
        return count;
    }
}
