for my twoStacksQueue, i designed the data strucutre as enqueueing an element to stack1. 
and for stack2, if it is empty, I used stack2.push(stack1.pop()) to move stack1's elements to 
stack 2, because of FILO, the first element in stack 1 will now become the last element in stack2, so 
this ensures that dequeuing will start from first element to last element from stack1. And if stack2 is
not empty, it will pop the last element from it.first()method is when stack2 is empty, if stack1 is not empty,
then stack1's elements will also be transferred to stack2 (because of FILO, now the last element of stack2 is stack1's first),
then if stack2 is not empty, it returns top element (which is actually first element of stack1).
Enqueue operation is O(1) and dequeue operation is O(n)



for the NewStack, I designed two stacks: stack that contains added elements, and a minStack that 
contains minimum elements. The push method first pushes the element to the stack, and then it checks whether
it is smaller or equal to top element in minStack, if it is, this element will now become thhe top element in 
minStack. pop()method pops out an element from the stack and checks if it is the top element of "minStack", if it is, the top element in "minStackwill
also get poped. top()method returns the top element of the stack without removing it. minElement() returns the minimum element currently in the stack.
the minElement is O(1) runtime because accessing the top element of the minStack is constant-time. 
I think the space usage is proportaional to how often minElement is accessed. In worst case, every element in Stack needs to be pushed to minStack, which results in O(n)
space complexity.