Emily Lu
peekTopN design: it initializes an arralylist and iterates through element of the heap. It checks
if topN size is less than n; if yes, it inserts the current element, if it is full, it checks if the element
is bigger than smallest element in the list and replaces it if yes. The time complexity is O(n*m), m is number of elements in the heap and n
is number of top elements to get because in worst case, each element might need to compare with all elements in the topN list
space comlexity is O(n), n is number of elemnts in the topN list

I spent around 12 hours on the assignment
I learned a lot about arrayheap(sort a max heap), including methods like insertion and removal, which are complicated