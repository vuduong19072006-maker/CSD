package warehouse.structure;

import warehouse.model.Item;

/** Node cua BST. ref tro nguoc ve Node trong linked list de xoa dong bo. */
public class TreeNode {

    public Item info;
    public Node ref;
    public TreeNode left, right;

    public TreeNode(Item info, Node ref) {
        this.info = info;
        this.ref = ref;
    }
}
