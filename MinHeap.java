import java.util.Scanner;

class MinHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    private int parent(int index) {
        return heap[getParentIndex(index)];
    }

    private int leftChild(int index) {
        return heap[getLeftChildIndex(index)];
    }

    private int rightChild(int index) {
        return heap[getRightChildIndex(index)];
    }

    private void swap(int indexOne, int indexTwo) {
        int temp = heap[indexOne];
        heap[indexOne] = heap[indexTwo];
        heap[indexTwo] = temp;
    }

    public void insert(int value) {
        if (size == capacity) {
            throw new IllegalStateException("Heap is full!");
        }
        heap[size] = value;
        size++;
        heapifyUp();
    }

    public int removeMin() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty!");
        }
        int minValue = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown();
        return minValue;
    }

    public int peek() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty!");
        }
        return heap[0];
    }

    private void heapifyUp() {
        int index = size - 1;
        while (hasParent(index) && parent(index) > heap[index]) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
                smallerChildIndex = getRightChildIndex(index);
            }
            if (heap[index] <= heap[smallerChildIndex]) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;
        }
    }

    public void printHeap() {
        System.out.print("Heap elements: ");
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}

public class SimpleHeap {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MinHeap minHeap = new MinHeap(10);

        System.out.println("Simple Min-Heap Program");
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Insert Element");
            System.out.println("2. Get Minimum");
            System.out.println("3. Remove Minimum");
            System.out.println("4. Print Heap");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter element to insert: ");
                    int value = scanner.nextInt();
                    minHeap.insert(value);
                    System.out.println("Element inserted.");
                    break;
                case 2:
                    try {
                        System.out.println("Minimum element: " + minHeap.peek());
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Removed element: " + minHeap.removeMin());
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    minHeap.printHeap();
                    break;
                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
