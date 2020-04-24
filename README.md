# SocialNetwork

We are building a social network.  In this social network, each user has friends.

A chain of friends between two users, user A and user B, is a sequence of users starting with A and ending with B, such that for each user in the chain, ua, the subsequent user, ua + 1, are friends.

Given a social network and two users, user A and user B, please write a function that computes the length of the shortest chain of friends between A and B.

Please write answers to the following discussion questions and include them in your solution as comments:

1. How did you represent the social network?  Why did you choose this representation?

The social network in the end represents a graph which consists of a set of vertices and a set of edges. 

2. What algorithm did you use to compute the shortest chain of friends?  What alternatives did you consider?  Why did you choose this algorithm over the alternatives?

I will use a BFS algorithm, to go from node to node and validate/check all of the friends of the given node. One step at a time. 

As an alternative, thinking at the best/quickest path between two points, initially I was thinking at A* algo, but thinking a little bit more on how to implement this over a social network (which is not a map in space) I got stuck. Too many problems, or future problems trying to implement something like this:

- evaluation method (if we are close of not from the destination)
- we could jump from fried to friend until the end of time...

This kind of algo (Dijkstra & A*) are not suitable for this kind of job.

So, only practicable algos on the graphs are suitable here.

3. Please enumerate the test cases you considered and explain their relevance

+ should we consider the theorem that with a connection of 7 people, a person is connected with anyone from the world. 

+ everyone is connected with everyone 
+ everyone has only two connections (A ~ B ~ C ~ D ~ E)
+ + Bridge connection - An individual whose weak ties fill a structural hole, providing the only link between two individuals or clusters. 
+ we have not discussed about sizes. The algo/implementation works for small social networks.
+ time in which we need to compute
+ multiple possibilities to obtain the same result

+ even each person has friend/s, there can be a possibility of clustering so that, for a given input : start & end, there is no solution.

## Input data

input data:
```json
[
   {
   	   "userA" : "John",
   	   "userB" : "Bogdan"
   }
]
```