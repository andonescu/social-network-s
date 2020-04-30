package ro.andonescu.playground.social.network

import ro.andonescu.playground.social.network.model.Connection
import scalax.collection.GraphPredef._
import scalax.collection.{Graph, GraphEdge}

import scala.annotation.tailrec

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
    val friendsSeq = startNode.diSuccessors.toSeq

    val friendsMap = collection.mutable.Map(friendsSeq.map(_ -> Seq(startNode)): _*)

    //1. go step by step, through all the `friendsSeq` of each node; a breadth-first search algorithm
    @tailrec
    def iterateThroughFriends(friendsSeq: Seq[context.NodeT]): Seq[context.NodeT] =
      friendsSeq match {
        case head :: _ if head.value == end => friendsMap.get(head).getOrElse(Seq.empty) :+ head
        case head :: tail => {
          // get head successors which are not already present in our seq
          val friendsNotVisited = head.diSuccessors.filterNot(friendsMap.contains(_))
          // for each new head successor we will add in the map the proper steps to get to it
          val pathThroughFriendsToHead = friendsMap.get(head).getOrElse(Seq.empty) :+ head
          // global based knowledge, add the details about the new links
          friendsMap ++= friendsNotVisited.map(_ -> pathThroughFriendsToHead)

          iterateThroughFriends(tail ++ friendsNotVisited)
        }
        case _ => Seq.empty
      }

    //2. TODO: further optimisation, start both ways (from the start point & end point) and see what matches appear

    iterateThroughFriends(friendsSeq).map(_.value)
  }

}
