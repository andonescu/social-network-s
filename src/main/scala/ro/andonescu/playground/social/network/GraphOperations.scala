package ro.andonescu.playground.social.network

import ro.andonescu.playground.social.network.model.Connection
import scalax.collection.GraphPredef._
import scalax.collection.{Graph, GraphEdge}

object GraphOperations {

  /**
   * Transforms the given list of {@link ro.andonescu.playground.social.network.model.Connection}s to a {@link scalax.collection.Graph}
   */
  def interpret[N](connections: Seq[Connection[N]]): Graph[N, GraphEdge.UnDiEdge] =
    Graph(connections.map(con => con.userA ~ con.userB): _*)

  /**
   * Each user has friends, so we will start with the fact that start & end really exists
   */
  def chainOfFriendsBetween[N](start: N, end: N)(context: Graph[N, GraphEdge.UnDiEdge]): Seq[N] = {

    val startNode = context.find(start).get
    val endNode = context.find(end).get

    //1. go step by step, through all the `friends` of each node; a breadth-first search algorithm
    def iterateThroughFriends(successors: Seq[context.NodeT], predecessorsMap: Map[context.NodeT, Seq[context.NodeT]]): Seq[context.NodeT] =
      successors match {
        case head :: _ if head == endNode => predecessorsMap.get(head).get :+ endNode
        case head :: tail => {
          // get head successors which are not already present in our seq
          val headSuccessors = head.diSuccessors.toSeq.filterNot(predecessorsMap.contains(_))
          // for each new head successor we will add in the map the proper steps to get to it
          val headPredecessorsWithIt = predecessorsMap.get(head).get :+ head
          val newMapSuccessors = headSuccessors.map(_ -> headPredecessorsWithIt).toMap
          iterateThroughFriends(tail ++ headSuccessors, predecessorsMap ++ newMapSuccessors)
        }
        case _ => Seq.empty
      }


    //2. TODO: further optimisation, start both ways (from the start point & end point) and see what matches appear
    val successors = startNode.diSuccessors.toSeq
    val predecessorsMap = successors.map(_ -> Seq(startNode)).toMap

    iterateThroughFriends(successors, predecessorsMap).map(_.value)
  }

}
