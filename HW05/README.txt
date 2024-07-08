Emily Lu
how I implement remove method:
I call removeRecursive method in remove method and in the removeRecursive method, if the node to be removed has no children or one child, the size 
decreases and returns the child or null if no child. 
If a node has two children, the method finds smallest value in right subtree and replace the node to be removed with that value. 
finally, in remove method, it returns true if the current size is smaller than inital size(which means there is a node that is removed)

extra credit:
in the driver method, I used a bubble sort method to compare the date of input files so that polls are processed in date order

I spent around 10 hours on this assignemtna and I learned a lot about binary search trees, especially the different traversals. 