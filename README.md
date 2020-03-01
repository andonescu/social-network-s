# SocialNetwork

We are building a social network.  In this social network, each user has friends.

A chain of friends between two users, user A and user B, is a sequence of users starting with A and ending with B, such that for each user in the chain, ua, the subsequent user, ua + 1, are friends.

Given a social network and two users, user A and user B, please write a function that computes the length of the shortest chain of friends between A and B.

Please write answers to the following discussion questions and include them in your solution as comments:

1. How did you represent the social network?  Why did you choose this representation?

The social network in the end represents a graph which consists of a set of vertices and a set of edges. 

2. What algorithm did you use to compute the shortest chain of friends?  What alternatives did you consider?  Why did you choose this algorithm over the alternatives?

I see two alternatives: Dijkstra or A Star Algorithms. First one will give the expected result, when the second one will give us the best result in computer time.

3. Please enumerate the test cases you considered and explain their relevance

+ everyone is connected with everyone 
+ everyone has only two connections (A ~ B ~ C ~ D ~ E)
+ + Bridge connection - An individual whose weak ties fill a structural hole, providing the only link between two individuals or clusters. 
+ ???

## Input data

input data:
```json
[
   {
   	   "userA" : "John",
   	   "userB" : "Bogdan"
   }
]
``````