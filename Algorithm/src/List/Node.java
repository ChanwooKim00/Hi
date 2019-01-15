package List;

public class Node {
	public Object data;
	public Node nextNode;
	public Node preNode;

	public Node(Object data) {
		this.data = data;
		this.nextNode = null;
		this.preNode = null;
	}

}
