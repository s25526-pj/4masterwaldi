package Zadanie1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectContainer<T> {

    private Node headObject;
    private Filterer<T> constructorFilter;

    public ObjectContainer(Filterer<T> constructorFilter) {
        this.constructorFilter = constructorFilter;
    }

    class Node {
        private T object;
        private Node next;

        public Node(T object) {
            this.object = object;
            this.next = null;
        }

        public T getObject() {
            return object;
        }

        public void setObject(T object) {
            this.object = object;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public boolean verifyConstructorFilter(T object) {
        return (constructorFilter.filter(object));
    }

    public void add(T object) {
        if (!verifyConstructorFilter(object)) {
            throw new IllegalArgumentException("Cant be");
        }
        Node newNode = new Node(object);
        newNode.next = null;
        if (this.headObject == null) {
            this.headObject = newNode;
        } else {
            Node lastNode = this.headObject;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            lastNode.next = newNode;
        }
    }

    public void print() {
        Node node = this.headObject;
        while (node != null) {
            System.out.println(node.object);
            node = node.next;
        }
    }

    public List<T> getWithFilter(Filterer<T> filterer) {
        Node node = this.headObject;
        List<T> listToReturn = new ArrayList<>();
        while (node != null) {
            if (filterer.filter(node.getObject())) {
                listToReturn.add(node.getObject());
            }
            node = node.next;
        }
        return listToReturn;
    }

    public void delate(T object) {
        Node currentNode = this.headObject;
        Node prevNode = null;
        if (currentNode != null && currentNode.object != object) {
            prevNode = currentNode;
            currentNode = currentNode.next;
        }
        if (currentNode != null) {
            prevNode.next = currentNode.next;
        }

    }

    public void removeIf(Filterer<T> filterer) {
        Node currentNode = this.headObject;
        while (currentNode != null) {
            if (filterer.filter(currentNode.getObject())) {
                this.delate(currentNode.getObject());
            }
            currentNode = currentNode.next;
        }
    }

    public File storeToFile(String fileName, Filterer<T> filterer, Saver<T> saver) {
        Node currentNode = this.headObject;
        File fileWithObjects = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileWithObjects))) {
            while (currentNode != null) {
                if (filterer.filter(currentNode.getObject())) {
                    writer.write(saver.saveToFile(currentNode.getObject()) + "\n");
                }
                currentNode = currentNode.next;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return fileWithObjects;
    }

    public Node getHeadObject() {
        return headObject;
    }

    public void setHeadObject(Node headObject) {
        this.headObject = headObject;
    }
}