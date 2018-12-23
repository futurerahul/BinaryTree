package b_tree;

public class QueueLL<T> {
	
	class Node<T>
	{
		T data;
		Node<T> next;
		
		Node(T data)
		{
			this.data=data;
		}
	}
	
	private Node<T> front;
	private Node<T> rear;
	private int size=0;
	
	public int size()
	{
		return size;
	}
	public boolean isEmpty()
	{
		return (size==0);
	}
	public T front() throws QueueEmptyException1{
		if(size == 0){
			QueueEmptyException1 e = new QueueEmptyException1();
			throw e;
		}

		return front.data;
	}


	public void enqueue(T element){
		Node<T> newNode = new Node<T>(element);

		if(front == null){
			front = newNode;
			rear = newNode;
		}
		else{
			rear.next = newNode;
			rear = newNode;
		}

		size++;
	}

	public T dequeue() throws QueueEmptyException1{
		if(front == null){
			QueueEmptyException1 e = new QueueEmptyException1();
			throw e;
		}
		if(front == rear){
			rear = null;
		}
		T temp = front.data;
		front = front.next;
		size--;
		return temp;
	}

}
