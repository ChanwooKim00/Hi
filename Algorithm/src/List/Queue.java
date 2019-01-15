package List;

public class Queue {
	public Node first;
	public Node last;

	public Queue() {
		this.first = null;
		this.last = null;
	}

	public void insert(Object data) {
		Node newNode = new Node(data);
		if (first == null) {
			first = newNode;
			last = newNode;
		} else {
			last.nextNode = newNode;
			last = newNode;
		}
	}

	public Object select() {
		if (first == null) {
			return null;
		}
		return first.data;
	}

	public Node select(int index) {
		Node result = first;
		while (--index != 0) {
			result = first.nextNode;
		}
		return result;
	}

	public Object delete() {
		Object data = select();
		first = first.nextNode;

		return data;
	}
}
