package com.headlamp;
import java.util.ArrayList;
/**
 * HDLmModResponse short summary.
 *
 * HDLmModResponse description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
public class HDLmModResponse {
	/* An instance of this class is created to return the results
	   from some sort of rule modification call. Of course, not all
	   of the fields will always be set. However, all of the fields
	   may be set in some cases. */
	private ArrayList<String>  nodePath = null;
	private HDLmTree           treeNode = null;
	/* Get a few values */
	protected  ArrayList<String> getNodePath() {
		return nodePath;
	}
	protected HDLmTree getTreeNode() {
		return treeNode;
	}
	/* Set or reset the node path. Note that the caller can  
     not pass a null value for the new node path value. 
     This is an an error condition. */
	protected void setNodePath(ArrayList<String> newNodePath) {
		if (newNodePath == null) {
			String  errorText = "New node path value passed to setNodePath is null";
			throw new NullPointerException(errorText);
		}
		nodePath = newNodePath; 
	}
	/* Set or reset the tree node. Note that the caller can 
	   not pass a null value for the new tree node. This is 
	   an error condition. */
	protected void setTreeNode(HDLmTree newTreeNode) {
		if (newTreeNode == null) {
			String  errorText = "New tree node value passed to setNodePath is null";
			throw new NullPointerException(errorText);
		}
		treeNode = newTreeNode;
	}
}