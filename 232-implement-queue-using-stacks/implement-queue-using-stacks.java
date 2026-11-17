class MyQueue {
    private final Stack<Integer> inStack;
    private final Stack<Integer> outStack;

    public MyQueue() {
        inStack=new Stack<>();
        outStack=new Stack<>();
        
    }
    
    public void push(int x) {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
        
        inStack.push(x);
        
        while (!outStack.isEmpty()) {
            inStack.push(outStack.pop());
        }
    }
        
    
    public int pop() {
        return inStack.pop();
    }
        
    
    
    public int peek() {
        return inStack.peek();
        
    }
    
    public boolean empty() {
        return inStack.isEmpty();
        
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */