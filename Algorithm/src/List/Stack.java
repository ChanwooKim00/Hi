package List;

public class Stack {
	public Node first;
	
	public Stack() {
		this.first = null;
	}
	
	public void insert(Object data) {
		Node newNode = new Node(data);
		newNode.nextNode = first;
		first=newNode;
	}

	public Object select() {
		if (first == null) {
			return null;
		}
		return first.data;
	}
	public Object select(int index) {
		Node result = first;
		if(index==0) {
			return first.data;
		}else {
			while(--index != 0) {
				result = first.nextNode;
			}
			return result;
		}
	}
	
	public Object delete() {
		Object node=select();
		first = first.nextNode;
		return node;
	}
}
