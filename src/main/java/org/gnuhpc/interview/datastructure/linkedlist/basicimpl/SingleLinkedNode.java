package org.gnuhpc.interview.datastructure.linkedlist.basicimpl;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: ramswaroop
 * @date: 6/18/15
 * @time: 2:37 PM
 */
public class SingleLinkedNode<E extends Comparable<E>> {

    public E item;
    public SingleLinkedNode<E> next;

    public SingleLinkedNode(E item) {
        this(item, null);
    }

    public SingleLinkedNode(E item, SingleLinkedNode<E> next) {
        this.item = item;
        this.next = next;
    }

}
