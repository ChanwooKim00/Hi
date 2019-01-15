package List;

public class LinkedList {
	public Node first;
	public Node last;
	public int size;

	public LinkedList() {
		this.size = 0;
	}

	public Node getNode(int index) {
		Node node = null;
		if (index == 0) {
			node=first;
			return node;
		} else if (index == size - 1) {
			node = last;
		} else {
			node=first;
			for (int i = 0; i <= index-1; i++) {
				node = node.nextNode;
			}
		}
		return node;
	}
	
//	public Node getNode(int index) {
//		Node node = first;
//		int mid = size / 2;
//		if (index <= mid) {
//			for (int i = 0; i == index; i++) {
//				node = node.nextNode;
//			}
//		} else if (index > mid) {
//			node = last;
//			for (int i = 0; i == index; i++) {
//				node = node.preNode;
//			}
//		}
//		return node;
//	}

//======================== Node Add============
	public void addFirst(Object data) {
		Node newNode = new Node(data);
		if (first != null && last!=null) {
			newNode.nextNode = first;
			first.preNode = newNode;
			first = newNode;
		}else if (first == null && last == null) {
			first = newNode;
			last = newNode;
		}  
		size++;
	}

	public void addLast(Object data) {
		if (size == 0) {
			addFirst(data);
		} else {
			Node newNode = new Node(data);
			last.nextNode = newNode;
			newNode.preNode = last;
			last = newNode;
			size++;
		}
	}

	public void addMid(int index, Object data) {
		if (index == 0) {
			addFirst(data);
		} else if (index == size - 1) {
			addLast(data);
		} else {
			Node newNode = new Node(data);
			Node preNode = getNode(index - 1);
			Node nextNode = preNode.nextNode;

			newNode.preNode = preNode;
			newNode.nextNode = nextNode;

			preNode.nextNode = newNode;
			nextNode.preNode = newNode;

			size++;
		}
	}

//======================== Node Delete============
	public Object deleteFirst() {
		if (size == 0) {
			return null;
		}
		Node deleteNode = first;
		first = null;
		first = deleteNode.nextNode;
		first.preNode = null;
		size--;

		return deleteNode.data;
	}

	public Object deleteLast() {
		if (size == 0) {
			return null;
		}
		Node deleteNode = last;
		last = null;
		last = deleteNode.preNode;
		last.nextNode = null;
		size--;

		return deleteNode.data;
	}

	public Object delete(int index) {
		if (size == 0) {
			return null;
		}
		if (index == 0) {
			return deleteFirst();
		} else if (index == size - 1) {
			return deleteLast();
		} else {
			Node deleteNode = getNode(index);
			Node preNode = deleteNode.preNode;
			Node nextNode = deleteNode.nextNode;

			preNode.nextNode = nextNode;

			nextNode.preNode = preNode;

			size--;

			return deleteNode.data;
		}
	}

	public static void main(String[] args) {
		LinkedList linkedList = new LinkedList();
		linkedList.addFirst(1);
		linkedList.addFirst(2);
		linkedList.addFirst("hi");
		linkedList.addFirst(3);
		linkedList.addFirst(4);
		for (int i = 0; i < linkedList.size; i++) {
			System.out.println(i + "번 째 = " + linkedList.getNode(i).data);
		}
	}
}
