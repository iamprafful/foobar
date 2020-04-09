public class Solution {

    public static String[] solution(String[] l) {
        // Your code here
        int max = getkMaxLength(l);
        int arr[] = toInteger(l, max);
        int sortedArr[] = sort(arr);
        String[] output = toVersion(sortedArr, max);
        return output;
    }

    public static int[] toInteger(String[] arr, int max) {
        int[] op = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            String strMinor = "";
            for (int j = 0; j < max + 1; j++) {
                strMinor = strMinor + "0";
            }
            String strRevision = strMinor;
            arr[i] = arr[i].replace(".", ",");
            String[] version = arr[i].split(",");
            int major = Integer.parseInt(version[0]) + 1;
            int minor = 0, revision = 0;
            if (version.length >= 2) {
                minor = Integer.parseInt(version[1]) + 1;
                strMinor = "";
                for (int j = 0; j < max + 1 - String.valueOf(minor).length(); j++) {
                    strMinor = strMinor + "0";
                }
                strMinor = strMinor + String.valueOf(minor);
                if (version.length == 3) {
                    revision = Integer.parseInt(version[2]) + 1;
                    strRevision = "";
                    for (int j = 0; j < max + 1 - String.valueOf(revision).length(); j++) {
                        strRevision = strRevision + "0";
                    }
                    strRevision = strRevision + String.valueOf(revision);
                }
            }
            op[i] = Integer.parseInt(String.valueOf(major) + strMinor + strRevision);

        }
        return op;
    }

    public static int[] sort(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
        return arr;
    }

    public static String[] toVersion(int[] arr, int max) {
        String[] op = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            String str = String.valueOf(arr[i]);
            int revision = Integer.parseInt(str.substring(str.length() - max - 1, str.length())) - 1;
            int minor = Integer.parseInt(str.substring(str.length() - ((max) * 2) - 1, str.length() - max - 1)) - 1;
            int major = Integer.parseInt(str.substring(0, str.length() - ((max + 1) * 2))) - 1;
            String version = String.valueOf(major);
            if (minor != -1) {
                version = version + "." + String.valueOf(minor);
                if (revision != -1)
                    version = version + "." + String.valueOf(revision);
            }
            op[i] = version;
        }
        return op;
    }

    public static int getkMaxLength(String[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].replace(".", ",");
            String[] version = arr[i].split(",");
            for (int j = 0; j < version.length; j++) {
                if (version[j].length() > max)
                    max = version[j].length();
            }
        }
        return max;
    }
}
