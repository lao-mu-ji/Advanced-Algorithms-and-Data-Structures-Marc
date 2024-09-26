# Lab 2: Stack & Queue ADT
This lab will give you practice about stack and queue ADT. You are to download the supporting files provided in the archive lab2.zip (see at the bottom).
## Part 1: stack with findMin
In this part you have to first implement an array-based stack class and then using that class, you have to implement another kind of stack (a stack with one more operation called findMin).

Write the class ArrayStack which implements an array-based stack  by using the provided class template ArrayStack.java. The methods must match the given complexity
Using the previous class, write the class StackMin which implements a stack with the extra operation findMin. A stack of type StackMin can only handle Comparable object. Such a stack supports the same methods as a normal stack plus the method findMin: if s is a non empty StackMin,  s.findMin() returns the minimum value currently in the stack. The complexity of findMin must be Θ(1) (just like all the other methods). You must use the provided class template StackMin.java
In term of memory usage what is the worst case and when does it occur?
Supporting files:

StackInterface.java
ArrayStack.java
StackMin.java
EmptyStackException.java
TestArrayStack.java
TestStackMin.java
### etc..
