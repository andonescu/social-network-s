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


  def chainOfFriendsBetween[N](start: N, end: N)(context: Graph[N, GraphEdge.UnDiEdge]): Seq[N] = {

    //1. go step by step, through all the `friends` of each node; a breadth-first search algorithm

    //2. further optimisation, start both ways (from the start point & end point) and see what matches appear
    ???
  }
}
