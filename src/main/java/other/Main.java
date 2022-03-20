package other;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        TreeNode node10 = new TreeNode(10);
        TreeNode node11 = new TreeNode(11);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.right = node8;
        node5.left = node10;
        node5.right = node11;
        node3.left = node6;
        node3.right = node7;
        node7.left = node9;
//        printNode(node1);
        printNodeFirstStack(node1);
        prePrintStack(node1);
//        printNodeMidStack(node1);
//        printNodeLastStack(node1);

    }

    public static void prePrintStack(TreeNode root){
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()){
            root = stack.pop();
            System.out.print(root.id+", ");
            if (root.right!=null){
                stack.push(root.right);
            }
            if (root.left!=null){
                stack.push(root.left);
            }
        }
    }


    public static void printNodeLastStack(TreeNode treeNode){
        List<TreeNode> stack = new ArrayList<>();
        stack.add(treeNode);
        TreeNode lastNode = null;
        while (stack.size()>0 && (treeNode = stack.get(stack.size()-1))!=null){
            if (lastNode == null || (lastNode.left !=null && lastNode.left.id == treeNode.id) || (lastNode.right != null && lastNode.right.id == treeNode.id)){
                lastNode = treeNode;
                if (treeNode.left!=null){
                    stack.add(treeNode.left);
                }else if(treeNode.right!=null){
                    stack.add(treeNode.right);
                }else {
                    System.out.println(treeNode.id);
                    stack.remove(stack.size()-1);
                }
            }else {
                if (treeNode.left != null && treeNode.left.id == lastNode.id){
                    if (treeNode.right!=null){
                        lastNode = treeNode;
                        stack.add(treeNode.right);
                    }else {
                        System.out.println(treeNode.id);
                        lastNode = treeNode;
                        stack.remove(stack.size()-1);
                    }
                }else if (treeNode.right.id == lastNode.id){
                    System.out.println(treeNode.id);
                    lastNode = treeNode;
                    stack.remove(stack.size()-1);
                }else {
                    System.out.println("======");
                }
            }

        }
    }


//    public static void printNodeMidStack(TreeNode treeNode){
//        List<TreeNode> stack = new ArrayList<>();
//        stack.add(treeNode);
//        TreeNode lastNode = null;
//        while (stack.size()>0 && (treeNode = stack.get(stack.size()-1))!=null){
//            if (lastNode == null || (lastNode.left !=null && lastNode.left.id == treeNode.id) || (lastNode.right != null && lastNode.right.id == treeNode.id)){
//                lastNode = treeNode;
//                if (treeNode.left!=null){
//                    stack.add(treeNode.left);
//                }else if(treeNode.right!=null){
//                    System.out.println(treeNode.id);
//                    stack.add(treeNode.right);
//                }else {
//                    System.out.println(treeNode.id);
//                    stack.remove(stack.size()-1);
//                }
//            }else {
//                if (treeNode.left != null && treeNode.left.id == lastNode.id){
//                    System.out.println(treeNode.id);
//                    if (treeNode.right!=null){
//                        lastNode = treeNode;
//                        stack.add(treeNode.right);
//                    }else {
//                        lastNode = treeNode;
//                        stack.remove(stack.size()-1);
//                    }
//                }else if (treeNode.right.id == lastNode.id){
//                    lastNode = treeNode;
//                    stack.remove(stack.size()-1);
//                }else {
//                    System.out.println("======");
//                }
//            }
//
//        }
//    }

    public static void printNodeFirstStack(TreeNode treeNode){
        List<TreeNode> stack = new ArrayList<>();
        stack.add(treeNode);
        TreeNode lastNode = null;
        while (stack.size()>0 && (treeNode = stack.get(stack.size()-1))!=null){
            if (lastNode == null || (lastNode.left !=null && lastNode.left.id == treeNode.id) || (lastNode.right != null && lastNode.right.id == treeNode.id)){
                System.out.println(treeNode.id);
                lastNode = treeNode;
                if (treeNode.left!=null){
                    stack.add(treeNode.left);
                }else if(treeNode.right!=null){
                    stack.add(treeNode.right);
                }else {
                    stack.remove(stack.size()-1);
                }
            }else {
                if (treeNode.left != null && treeNode.left.id == lastNode.id){
                    if (treeNode.right!=null){
                        lastNode = treeNode;
                        stack.add(treeNode.right);
                    }else {
                        lastNode = treeNode;
                        stack.remove(stack.size()-1);
                    }
                }else if (treeNode.right.id == lastNode.id){
                    lastNode = treeNode;
                    stack.remove(stack.size()-1);
                }else {
                    System.out.println("======");
                }
            }

        }
    }

    public static void printNode(TreeNode treeNode){
        System.out.println(treeNode.id);
        if (treeNode.left!=null){
            printNode(treeNode.left);
        }
        if (treeNode.right!=null){
            printNode(treeNode.right);
        }
    }
}

class TreeNode {
    int id;
    public TreeNode(int id) {
        this.id = id;
    }
    TreeNode left = null;
    TreeNode right = null;

}

