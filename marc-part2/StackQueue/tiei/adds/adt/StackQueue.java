package tiei.aads.adt;

import java.util.Stack;

/**
 * A class for queues implemented with stacks
 */
public class StackQueue<AnyType> implements QueueInterface<AnyType> {
    private Stack<AnyType> stack1,stack2;

    public StackQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    @Override
    public int size() {
        return stack1.size() + stack2.size();
    }

    @Override
    public void offer(AnyType item) {
        stack1.push(item);
    }

    public void shift(){
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
    }

    @Override
    public AnyType poll() throws EmptyQueueException {
        shift();
        if(stack2.isEmpty()){
            throw new EmptyQueueException();
        }
        return stack2.pop();
    }

    @Override
    public AnyType peek() throws EmptyQueueException {
        shift();
        if(stack2.isEmpty()){
            throw new EmptyQueueException();
        }
        return stack2.peek();
    }
}
