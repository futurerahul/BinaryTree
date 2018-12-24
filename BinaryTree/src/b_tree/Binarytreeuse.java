package b_tree;

import java.util.Scanner;

public class Binarytreeuse {
	
	public static Binarytreenode<Integer> inputLevel()
	{
		Scanner s=new Scanner(System.in);
		int rootdata;
		System.out.println("Enter the root data: ");
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
	
	public static void printlevel(Binarytreenode<Integer> root)
	{
		QueueLL<Binarytreenode<Integer>> q=new QueueLL<>();
		q.enqueue(root);
		while(!q.isEmpty())
		{
			try {
				Binarytreenode<Integer> current=q.dequeue();
				System.out.print(current.data+":");
				if(current.left!=null)
				{
					q.enqueue(current.left);
					System.out.print("L"+current.left.data);
				}
				if(current.right!=null)
				{
					q.enqueue(current.right);
					System.out.print(",R"+current.right.data);
				}
				System.out.println();
			} catch (QueueEmptyException1 e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
	
	public static int height(Binarytreenode<Integer> root)
	{
		if(root==null)
		{
			return 0;
		}
		int curr_height=1;
		int l_height=height(root.left);
		int r_height=height(root.right);
		curr_height+=Math.max(l_height, r_height);
		return curr_height;
	}
	
	public static int diameter(Binarytreenode<Integer> root)
	{
		if(root==null)
		{
			return 0;
		}
		int c_dia=height(root.left)+height(root.right);
		int l_dia=diameter(root.left);
		int r_dia=diameter(root.right);
		int result=Math.max(c_dia,Math.max(l_dia,r_dia));
		return result;
	}
	
	public static Pair<Integer,Integer> heightDiameter (Binarytreenode<Integer> root)
	{
		if(root==null)
		{
			Pair<Integer,Integer> output=new Pair<>();
			output.height=0;
			output.diameter=0;
			return output;
		}
		
		Pair<Integer,Integer> lhd=heightDiameter(root.left);
		Pair<Integer,Integer> rhd=heightDiameter(root.right);
		int height=1+Math.max(lhd.height, rhd.height);
		int op1=lhd.height+rhd.height;
		int op2=lhd.diameter;
		int op3=rhd.diameter;
		int diameter=Math.max(op1, Math.max(op2, op3));
		Pair<Integer,Integer> output=new Pair<>();
		output.height=height;
		output.diameter=diameter;
		return output;
		
	}
	
	public static void mirror(Binarytreenode<Integer> root)
	{
		if(root==null){
			return;
		}
		Binarytreenode<Integer> temp=root.left;
		root.left=root.right;
		root.right=temp;
		
		mirror(root.left);
		mirror(root.right);
	}
	
	public static boolean findNode(Binarytreenode<Integer> root,int x)
	{
		if(root==null)
		{
			return false;
		}
		boolean ans=false;
		if(root.data==x)
		{
			ans=true;
		}
		boolean l_ans=findNode(root.left,x);
		boolean r_ans=findNode(root.right,x);
		return (ans||(l_ans||r_ans));
	}

	public static void main(String[] args) {
		
		//Scanner s=new Scanner(System.in);
		//Binarytreenode<Integer> root=takeinput(s);
	//	print(root);
		Binarytreenode<Integer> root=inputLevel();
		print(root);
		printlevel(root);
		System.out.println("Diameter of tree: "+ diameter(root));
		System.out.println("Diameter of tree: "+ heightDiameter(root).diameter+" Height of tree: "+ heightDiameter(root).height);

	}

}
