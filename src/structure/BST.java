package warehouse.structure;

/** Cay nhi phan tim kiem theo code, dung lam chi muc tra cuu nhanh. */
public class BST {

    private TreeNode roots;

    public boolean isEmpty() {
        return roots == null;
    }

    private void inOrder(TreeNode x) {
        if (x == null) {
            return;
        }
        inOrder(x.left);
        System.out.println(x.info);
        inOrder(x.right);
    } // Duyet

    public void display() {
        if (roots == null) {
            System.out.println("Empty!");
            return;
        }
        inOrder(roots);
    } // Hien thi tang dan theo code

    public void insert(Node node) {
        if (node == null || node.info == null) {
            return;
        }

        TreeNode newTree = new TreeNode(node.info, node);

        if (roots == null) {
            roots = newTree;
            return;
        }

        TreeNode p = roots;
        while (true) {
            int cmp = newTree.info.getCode()
                    .compareToIgnoreCase(p.info.getCode());

            if (cmp < 0) {
                if (p.left == null) {
                    p.left = newTree;
                    return;
                }
                p = p.left;
            } else if (cmp > 0) {
                if (p.right == null) {
                    p.right = newTree;
                    return;
                }
                p = p.right;
            } else {
                return;
            }
        }
    } // Chen Node moi vao tree

    public TreeNode search(String code) {
        TreeNode p = roots;
        while (p != null) {
            int cmp = code.compareToIgnoreCase(p.info.getCode());
            if (cmp == 0) {
                return p;
            }
            p = (cmp < 0) ? p.left : p.right;
        }
        return null;
    } // Tim nut theo code

    public Node delete(String code) {
        TreeNode parent = null, p = roots;

        // Tim nut can xoa va cha cua no
        while (p != null) {
            int cmp = code.compareToIgnoreCase(p.info.getCode());
            if (cmp == 0) {
                break;
            }
            parent = p;
            p = (cmp < 0) ? p.left : p.right;
        }
        if (p == null) {
            return null; // khong tim thay
        }

        // Merge: tim nut thay the p
        TreeNode child;
        if (p.left == null) {
            child = p.right;
        } else {
            child = p.left;
            if (p.right != null) {
                // Treo cay con phai vao nut phai nhat cua cay con trai
                TreeNode r = p.left;
                while (r.right != null) {
                    r = r.right;
                }
                r.right = p.right;
            }
        }

        // Noi cha cua p voi child
        if (parent == null) {
            roots = child;
        } else if (parent.left == p) {
            parent.left = child;
        } else {
            parent.right = child;
        }
        return p.ref; // tra ve Node trong list de ProductLinkedList go ra
    } // Xoa nut theo code (kieu merging)
}
