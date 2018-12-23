package b_tree;

import java.util.Scanner;

public class Binarytreeuse {
	
	public static Binarytreenode<Integer> inputLevel()
	{
		Scanner s=new Scanner(System.in);
		int rootdata;
		System.out.println("Enter the data: ");
		rootdata=s.nextInt();
		if(rootdata==-1)
		{
			return null;
		}
		Binarytreenode<Integer> root=new Binarytreenode<>(rootdata);
		QueueLL<Binarytreenode<Integer>> q=new QueueLL<>();
		q.enqueue(root);
		while(!q.isEmpty())
		{
			Binarytreenode<Integer> current;
			try {
				current=q.dequeue();
			} catch (QueueEmptyException1 e) {
				// TODO Auto-generated catch block
				return null;
			}
			System.out.println("Enter left child of "+current.data);
			int leftchild=s.nextInt();
			if(leftchild!=-1)
			{
				Binarytreenode<Integer> lchild=new Binarytreenode<>(leftchild);
				q.enqueue(lchild);
				current.left=lchild;
			}
			System.out.println("Enter right child of "+current.data);
			int rightchild=s.nextInt();
			if(rightchild!=-1)
			{
				Binarytreenode<Integer> rchild=new Binarytreenode<>(rightchild);
				q.enqueue(rchild);
				current.right=rchild;
			}
		}
		
		return root;
		
	}
	
	public static void print(Binarytreenode<Integer> root)
	{
		if(root==null)
			return;
		String toBeprinted=root.data+": ";
		if(root.left!=null)
		{
			toBeprinted+="L"+root.left.data+",";
		}
		if(root.right!=null)
		{
			toBeprinted+="R"+root.right.data;
		}
		System.out.println(toBeprinted);
		print(root.left);
		print(root.right);
	}
	
	public static Binarytreenode<Integer> takeinput(Scanner s)
	{
		int rootdata;
		System.out.println("Enter root data: ");
		rootdata=s.nextInt();
		if(rootdata==-1)
		{
			return null;
		}
		Binarytreenode<Integer> root=new Binarytreenode<>(rootdata);
		root.left=takeinput(s);
		root.right=takeinput(s);
		return root;
	}

	public static void main(String[] args) {
		
		//Scanner s=new Scanner(System.in);
		//Binarytreenode<Integer> root=takeinput(s);
	//	print(root);
		Binarytreenode<Integer> root=inputLevel();
		print(root);

	}

}
