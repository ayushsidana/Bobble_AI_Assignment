import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class TreeSerialization {
    public static void main(String args[]) throws IOException {
        
        if (args[0].equals("create")) {
            String splitBy = ",";
            BufferedReader br = new BufferedReader(new FileReader(args[1]));
            // BufferedReader br = new BufferedReader(new FileReader("Input.csv"));
            String line = br.readLine();
            PrintWriter pr=new PrintWriter(new FileWriter(args[2]));
            int temp=0;
            while (line != null) {

                String[] b = line.split(splitBy);
                for (int i = 0; i < b.length; i++) {
                    TreeNode root = makeTree(b[i]);
                    String s = serialize(root);
                    
                    if(temp!=0)
                    pr.write("\n");
                    pr.write(s);
                    pr.flush();
                    temp++;
                    
                }
                line = br.readLine();
            }
            pr.close();
            br.close();
        }
        if(args[0].equals("load")){
            BufferedReader br=new BufferedReader(new FileReader(args[1]));
            String str="";
            while((str=br.readLine())!=null)
            {
                TreeNode root = deserialize(str);
                printTree(root);
                System.out.println();
            }
            br.close();
        }
    }

    public static TreeNode makeTree(String s) {
        if (s.length() == 0) {
            return null;
        }
        if (s.length() == 1) {
            TreeNode temp = new TreeNode(s.charAt(0));
            temp.left = null;
            temp.right = null;
            return temp;
        }
        int length = s.length();
        length = length / 2;
        TreeNode root = new TreeNode(s.charAt(length));
        root.left = makeTree(s.substring(0, length));
        root.right = makeTree(s.substring(length + 1, s.length()));
        return root;
    }

    public static void printTree(TreeNode root) {
        if (root == null)
            return;
        printTree(root.left);
        System.out.print(root.c);
        printTree(root.right);
    }

    public static String serialize(TreeNode root) {
        if(root == null) return "";
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        StringBuilder sb = new StringBuilder();
        // use tree level order traversal to serialize the tree
        while(!q.isEmpty()) {
            TreeNode n = q.poll();
            if(n == null) {
                sb.append("null ");
            } else{
                sb.append(n.val + " ");
                q.add(n.left);
                q.add(n.right);
            }
        }
        return sb.toString().trim();
    }

    public static TreeNode deserialize(String data) {
        if(data == "") return null;
        String[] vals = data.split(" ");
        if(vals.length == 0) return null;
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(vals[0]));
        q.add(root);
        TreeNode p = null;
        String val;
        for(int i = 1; i < vals.length; ) {
            p = q.poll();
            val = vals[i++];
            if(val.equals("null")) {
                p.left = null;
            } else {
                p.left = new TreeNode(Integer.valueOf(val));
                q.add(p.left);
            }
            if(i < vals.length) {
                val = vals[i++];
                if(val.equals("null")) {
                    p.right = null;
                } else {
                    p.right = new TreeNode(Integer.valueOf(val));
                    q.add(p.right);
               }
            }
        }
        return root;
    }
}

class TreeNode {
    int val;
    char c;
    TreeNode left;
    TreeNode right;

    TreeNode(int  x) {
        val = x;
        c = (char) val;
    }
}