package com.PEDG1.unmapa.structures;


import com.PEDG1.unmapa.data.Comment;

public class Avltree<T extends Comment> {

    public TreeNode<T> root;
    public int tamaño = 0;

    public Avltree(TreeNode root) {
        this.root = root;
    }

    public int caseheight(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return node.height;
        }
    }

    public int height(TreeNode node) {
        node.height = 1 + Math.max(caseheight(node.right), caseheight(node.left));
        if (node.parentNode != null) {
            height(node.parentNode);
        }
        return node.height;
    }

    public TreeNode find(T val, TreeNode Root) {

        if (Root.key.getNunComment() == val.getNunComment()) {
            return Root;
        } else if (val.getNunComment() > Root.key.getNunComment() && Root.right != null) {
            return find(val, Root.right);
        } else if (val.getNunComment() < Root.key.getNunComment() && Root.left != null) {
            return find(val, Root.left);
        } else {
            return Root;
        }
    }

    public TreeNode next(TreeNode N) {
        if (N.right != null) {
            return LeftDecendat(N.right);
        } else {
            return RightAncestor(N);
        }
    }

    private TreeNode LeftDecendat(TreeNode N) {
        if (N.left == null) {
            return N;
        } else {
            return LeftDecendat(N.left);
        }
    }

    private TreeNode RightAncestor(TreeNode N) {
        if (N == null) {
            return null;
        } else if (N.key.compareTo(N.parentNode.key) > 0) {
            return N.parentNode;
        } else {
            return RightAncestor(N.parentNode);
        }
    }

    public Stack rangeSearch(T x, T y, TreeNode Root) {
        Stack<T> L = new Stack<>();

        TreeNode N = find(x, Root);

        int n = y.compareTo(N.key);
        while (n <= 0) {
            int t = x.compareTo(N.key);
            if (t >= 0) {
                L.InsertarNodo(N.key);
            }
            N = next(N);
        }

        return L;
    }

    public void insert(T key, TreeNode root) {
        tamaño+=1;
        TreeNode<Comment> newNode = new TreeNode(key);
        TreeNode<Comment> parent = find(key, root);

        if (newNode.key.getNunComment() == parent.key.getNunComment()) {
            //Nada
        } else if (newNode.key.getNunComment() > parent.key.getNunComment() && parent.right == null) {
            parent.right = newNode;
            parent.right.parentNode = parent;
        } else if (newNode.key.getNunComment() < parent.key.getNunComment() && parent.left == null) {
            parent.left = newNode;
            parent.left.parentNode = parent;
        }
        height(parent);

    }

    public void AVLinsert(T key, TreeNode root) {
        insert(key, root);
        TreeNode N = find(key, root);
        Rebalance(N);
    }

    public void Rebalance(TreeNode N) {

        TreeNode P = N.parentNode;

        if (caseheight(N.left) > caseheight(N.right) + 1) {
            RebalanceRight(N);
        }

        if (caseheight(N.right) > caseheight(N.left) + 1) {
            RebalanceLeft(N);
        }

        height(N);
        if (P != null) {
            Rebalance(P);
        }
    }

    public void RebalanceRight(TreeNode N) {
        TreeNode M = N.left;

        if (caseheight(M.right) > caseheight(M.left)) {
            RottateLeft(M);
        }
        RottateRight(N);
        height(N);
    }

    public void RebalanceLeft(TreeNode N) {
        TreeNode M = N.right;

        if (caseheight(M.left) > caseheight(M.right)) {
            RottateRight(M);
        }
        RottateLeft(N);
        height(N);
    }

    public void RottateRight(TreeNode X) {
        TreeNode P = X.parentNode;
        TreeNode Y = X.left;
        TreeNode B = Y.right;

        if (P == null) {
            this.root = Y;
            System.out.println("la ruta es: " + X.key.getNunComment());
        }

        Y.parentNode = P;
        if (P != null) {
            if (P.left.key.getNunComment() == X.key.getNunComment()) {
                P.left = Y;
            } else if (P.right.key.getNunComment() == X.key.getNunComment()) {
                P.right = Y;
            }
        }

        X.parentNode = Y;
        Y.right = X;
        if (B != null) {
            B.parentNode = X;
        }
        X.left = B;
    }

    public void RottateLeft(TreeNode X) {
        TreeNode P = X.parentNode;
        TreeNode Y = X.right;
        TreeNode B = Y.left;

        if (P == null) {
            this.root = Y;
            System.out.println("la ruta es: " + X.key.getNunComment());
        }

        Y.parentNode = P;
        if (P != null) {
            if (P.left.key.getNunComment() == X.key.getNunComment()) {
                P.left = Y;
            } else if (P.right.key.getNunComment() == X.key.getNunComment()) {
                P.right = Y;
            }
        }

        X.parentNode = Y;
        Y.left = X;
        if (B != null) {
            B.parentNode = X;
        }
        X.right = B;
    }

    public void AVLDelete(T N) {
        TreeNode M = Delete(N);
        Rebalance(M);
    }

    public TreeNode Delete(T N) {
        TreeNode ToDelete = find(N, root);

        if (ToDelete.right == null) {
            ToDelete.left.parentNode = ToDelete.parentNode;
            TreeNode ret = ToDelete.parentNode;
            if (ToDelete.parentNode.right.key == ToDelete.key) {
                ToDelete.parentNode.right = null;
                ToDelete.parentNode.right = ToDelete.left;
            } else if (ToDelete.parentNode.left.key == ToDelete.key) {
                ToDelete.parentNode.left = null;
                ToDelete.parentNode.left = ToDelete.left;
            }
            height(root);
            return ret;

        } else {
            TreeNode x = next(ToDelete);
            ToDelete.key = x.key;
            TreeNode ret = x.parentNode;
            x.parentNode.left = x.right;
            x.right.parentNode = x.parentNode;
            x = null;
            height(root);
            return ret;
        }

    }

}
