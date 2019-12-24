package TheFinalCode;

import java.util.Stack;
import java.util.Arrays;

public class StringTreeSet {

  private int size;
  private TreeNode root;
  private String rightmost;

  public StringIterator iterator() {
    return new TreeIterator();
  }

  public int size() {
    return size;
  }

  public void add(String value) {
    if (root == null) {
      root = new TreeNode(value);
      size++;
      rightmost = value;
    } else {
      add(value, root);
      if(value.compareTo(rightmost) > 0){
        rightmost = value;
      }
    }
  }

  public boolean contains(String value) {
    return contains(value, root);
  }

  public String remove(String value) {
    if (size > 0) {
      int c = value.compareTo(root.value);
      if (c == 0) {
        return removeRoot();
      } else {
        TreeNode parent = getParentOf(value);
        if (parent != null) {
          c = value.compareTo(parent.value);
          TreeNode remove = null;
          if (c < 0) {
            remove = parent.left;
            size--;
            if (remove.countChildren() == 2) {
              return complexRemove(remove);
            } else {
              if (remove.countChildren() == 0 || remove.hasLeftChild()) {
                parent.left = remove.left;
              } else {
                parent.left = remove.right;
              }
              return remove.value;
            }
          } else {
            remove = parent.right;
            size--;
            if (remove.countChildren() == 2) {
              return complexRemove(remove);
            } else {
              if (remove.countChildren() == 0 || remove.hasLeftChild()) {
                parent.right = remove.left;
              } else {
                parent.right = remove.right;
              }
              return remove.value;
            }
          }
        }
      }
    }
    return null;
  }

  private class TreeIterator implements StringIterator {

    private TreeNode current = root;

    public String next() {
      if(current.value.equals("SO I FOUND IT, BUT ITS ACTUALLY THE NEXT ONE OVER, SO... YEAH. GRAB THAT ONE.")){
        return null;
      }

      String returner = current.value;
      current = recursiveNext(current.value);
      return returner;
    }

    public TreeNode recursiveNext(String s) {
      // Start at the top of the tree
      return recursiveNext(s, root);
    }

    // A recursive algorithm that's gonna be... tricky. To say the least
    // The string is the value of the "current" node - the one we need to get the next of
    // The node is the one the algorithm is working from. A type of depth first search
    public TreeNode recursiveNext(String s, TreeNode t){
      if(t.value.equals(s)){
        if(t.hasLeftChild()){
          return t.left;
        }
        else if(t.hasRightChild()){
          return t.right;
        }
        else{
          return new TreeNode("SO I FOUND IT, BUT ITS ACTUALLY THE NEXT ONE OVER, SO... YEAH. GRAB THAT ONE.");
        }
      }
      else{
        if(t.hasLeftChild()){
          TreeNode n = recursiveNext(s, t.left);

          //Basically if you reached the bottom of the tree, try the node to the right instead
          if(n == null){
            if(t.hasRightChild()){
              TreeNode m = recursiveNext(s, t.right);

              return m;
              //I realized you return m in either case, so you know
              //But technically if it's wrong or right, you have to return it
            }
            else{
              //If it doesn't have a right, you just gotta move back up.
              return null;
            }
          }
          //If you found the thing, but you had to move back, grab the thing to the right
          else if(n.value.equals("SO I FOUND IT, BUT ITS ACTUALLY THE NEXT ONE OVER, SO... YEAH. GRAB THAT ONE.")){
            if(t.hasRightChild()){
              return t.right;
            }
            else{
              return n;
            }
          }
          else{
            return n;
          }
        }
        else if(t.hasRightChild()){
          TreeNode n = recursiveNext(s, t.right);

          //If you went to the right, it was your only option, so just return n
          //Yes, even if it's a bad node
          return n;
        }
        else{
          //Don't have a left or a right
          //And not the right node?
          //Bounce back a bad node. I'll catch it. You know, probably.
          return null;
        }
      }
    }

    public boolean hasNext() {
      if(current.value.equals("SO I FOUND IT, BUT ITS ACTUALLY THE NEXT ONE OVER, SO... YEAH. GRAB THAT ONE.")){
        return false;
      }
      return true;
    }
  }
  
  private String complexRemove(TreeNode node) {
    String result = node.value;
    if (goLeft()) {
      if (node.left.hasRightChild()) {
        TreeNode parent = getParentOfRightmostNode(node.left);
        node.value = parent.right.value;
        parent.right = parent.right.left;
      } else {
        node.value = node.left.value;
        node.left = node.left.left;
      }
    } else {
      if (node.right.hasLeftChild()) {
        TreeNode parent = getParentOfLeftmostNode(node.right);
        node.value = parent.left.value;
        parent.left = parent.left.right;
      } else {
        node.value = node.right.value;
        node.right = node.right.right;
      }
    }
    return result;
  }
  
  private TreeNode getParentOfLeftmostNode(TreeNode node) {
    TreeNode current = node;
    while (current.left.hasLeftChild()) {
      current = current.left;
    }
    return current;
  }
  
  private TreeNode getParentOfRightmostNode(TreeNode node) {
    TreeNode current = node;
    while (current.right.hasRightChild()) {
      current = current.right;
    }
    return current;
  }
  
  private boolean goLeft() {
    return Math.random() < 0.5;
  }
  
  private TreeNode getParentOf(String value) {
    TreeNode parent = root;
    TreeNode current;
    int c = value.compareTo(parent.value);
    if (c < 0) {
      current = parent.left;
    } else {
      current = parent.right;
    }
    while (current != null) {
      c = value.compareTo(current.value);
      if (c == 0) {
        return parent;
      } else if (c < 0) {
        parent = current;
        current = current.left;
      } else {
        parent = current;
        current = current.right;
      }
    }
    return null;
  }
  
  private String removeRoot() {
    String result = root.value;
    if (root.countChildren() == 0) {
      root = null;
    } else if (root.countChildren() == 2) {
      complexRemove(root);
    } else if (root.hasLeftChild()) {
      root = root.left;
    } else {
      root = root.right;
    }
    size--;
    return result;
  }
  
  private boolean contains(String value, TreeNode node) {
    if (node != null) {
      int c = value.compareTo(node.value);
      if (c == 0) {
        return true;
      } else if (c < 0) {
        return contains(value, node.left);
      } else {
        return contains(value, node.right);
      }
    }
    return false;
  }

  private void add(String value, TreeNode node) {
    int c = value.compareTo(node.value);
    if (c == 0) {
      node.value = value;
    } else if (c < 0) {
      if (node.hasLeftChild()) {
        add(value, node.left);
      } else {
        node.left = addTreeNode(value);
      }
    } else {
      if (node.hasRightChild()) {
        add(value, node.right);
      } else {
        node.right = addTreeNode(value);
      }
    }
  }  
  
  private TreeNode addTreeNode(String value) {
    size++;
    return new TreeNode(value);
  }
  
  private class TreeNode {
    private TreeNode left;
    private TreeNode right;
    private String value;
    
    public TreeNode(String value) {
      this.value = value;
    }
    
    public boolean hasLeftChild() {
      return left != null;
    }
    
    public boolean hasRightChild() {
      return right != null;
    }
    
    public int countChildren() {
      int result = 0;
      if (hasLeftChild()) {
        result++;
      }
      if (hasRightChild()) {
        result++;
      } 
      return result;
    }
  }
}