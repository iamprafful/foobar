class Solution {
    static int arr[][] = new int[8][8];

    static int getMinMoves(int x, int y, int tx, int ty) {
        if (x == tx && y == ty) {
            return arr[0][0];
        } else if (arr[Math.abs(x - tx)][Math.abs(y - ty)] != 0) {
            return arr[Math.abs(x - tx)][Math.abs(y - ty)];
        } else {
            int x1, y1, x2, y2;
            if (x <= tx) {
                if (y <= ty) {
                    x1 = x + 2;
                    y1 = y + 1;
                    x2 = x + 1;
                    y2 = y + 2;
                } else {
                    x1 = x + 2;
                    y1 = y - 1;
                    x2 = x + 1;
                    y2 = y - 2;
                }
            } else if (y <= ty) {
                x1 = x - 2;
                y1 = y + 1;
                x2 = x - 1;
                y2 = y + 2;
            } else {
                x1 = x - 2;
                y1 = y - 1;
                x2 = x - 1;
                y2 = y - 2;
            }
            arr[Math.abs(x - tx)][Math.abs(y - ty)] = Math.min(getMinMoves(x1, y1, tx, ty), getMinMoves(x2, y2, tx, ty)) + 1;
            arr[Math.abs(y - ty)][Math.abs(x - tx)] = arr[Math.abs(x - tx)][Math.abs(y - ty)];
            return arr[Math.abs(x - tx)][Math.abs(y - ty)];
        }
    }

    public static int solution(int src, int dest) {
        int minMoves = 0;
        int n, x, y, tx, ty;
        n = 64;
        x = src % 8;
        y = src / 8;
        tx = dest % 8;
        ty = dest / 8;
        if ((x == 1 && y == 1 && tx == 2 && ty == 2) || (x == 2 && y == 2 && tx == 1 && ty == 1)) {
            minMoves = 4;
        } else if ((x == 1 && y == n && tx == 2 && ty == n - 1) || (x == 2 && y == n - 1 && tx == 1 && ty == n)) {
            minMoves = 4;
        } else if ((x == n && y == 1 && tx == n - 1 && ty == 2) || (x == n - 1 && y == 2 && tx == n && ty == 1)) {
            minMoves = 4;
        } else if ((x == n && y == n && tx == n - 1 && ty == n - 1)
                || (x == n - 1 && y == n - 1 && tx == n && ty == n)) {
            minMoves = 4;
        } else {
            arr[1][0] = 3;
            arr[0][1] = 3;
            arr[1][1] = 2;
            arr[2][0] = 2;
            arr[0][2] = 2;
            arr[2][1] = 1;
            arr[1][2] = 1;
            minMoves = getMinMoves(x, y, tx, ty);
        }
        return minMoves;
    }
}
