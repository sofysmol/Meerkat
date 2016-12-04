/*
 * Copyright (c) 2015, Anastasia Izmaylova and Ali Afroozeh, Centrum Wiskunde & Informatica (CWI)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this 
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this 
 *    list of conditions and the following disclaimer in the documentation and/or 
 *    other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, 
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY 
 * OF SUCH DAMAGE.
 *
 */

package org.meerkat.util

import scala.util.matching.Regex
import scala.collection.mutable._
import scala.collection.JavaConversions._
import scalax.collection.Graph
import scalax.collection.edge._
import scalax.collection.immutable._
import scalax.collection.GraphPredef.{EdgeLikeIn, Param}
import scalax.collection.edge.LDiEdge
import scala.util.control.Breaks._
//мой код
trait Input{
  def length: Int
  val lineColumns:Array[(Int, Int)]
  val regexMap: Map[Regex, java.util.regex.Matcher]
  def calcLineColumns: Unit
  def charAt(i: Int): scala.Char
  def substring(start: Int, end: Int): String
  def startsWith(prefix: String, toffset: Int): Option[Int]//Boolean
  def endsWith(suffix: String): Boolean
  def matchRegex(r: Regex, start: Int, end: Int): Boolean
  def matchRegex(r: Regex, start: Int): Int
  def lineNumber(i: Int): Int
  def columnNumber(i: Int): Int
  def lineColumn(i: Int): (Int, Int)
}
//мой код
class InputString(val s: String) extends Input{ //DefaultGraphImpl[N,E[X] <: EdgeLikeIn[X]]) {

  def length = {
    var l = s.length()
    l
  }

  val lineColumns: Array[(Int, Int)] = Array.ofDim[(Int, Int)](length + 1)

  val regexMap: Map[Regex, java.util.regex.Matcher] = new java.util.HashMap[Regex, java.util.regex.Matcher]()

  calcLineColumns

  def calcLineColumns: Unit = {
    var lineCount = 0
    var lineNumber = 1
    var columnNumber = 1

    // Empty input: only the end of line symbol
    if(length == 1) {
      lineColumns(0) = (lineNumber, columnNumber)
    } else {
      for (i <- 0 until length) {
        lineColumns(i) = (lineNumber, columnNumber)
        if (s.charAt(i) == '\n') {
          lineCount += 1
          lineNumber += 1
          columnNumber = 1
        } else if (s.charAt(i) == '\r') {
          columnNumber = 1
        } else {
          columnNumber += 1
        }
      }
    }
    lineColumns(length) = (lineNumber, columnNumber)
  }

  def charAt(i: Int): scala.Char = {
    val c = s.charAt(i)
    c
  }

  def substring(start: Int, end: Int): String = {
    val c = s.substring(start, end)
    c
  }

  def startsWith(prefix: String, toffset: Int):Option[Int] =
  {
    val w = s.startsWith(prefix, toffset)
    println(s"StartWith prefix=$prefix toOffset=$toffset return=$w")
    if(w)
      Some(toffset+prefix.length)
    else None
  }

  def endsWith(suffix: String) = {
    val w = s.endsWith(suffix)
    println(s"EndWith suffix=$suffix return=$w")
    w
  }

  def matchRegex(r: Regex, start: Int, end: Int): Boolean = {
    if(start < 0) return false
    val matcher = regexMap.getOrElse(r, {val matcher: java.util.regex.Matcher = r.pattern.matcher(s); matcher})
    matcher.region(start, end)
    return matcher.matches()
  }

  def matchRegex(r: Regex, start: Int): Int = {
    if(start < 0) return -1
    val matcher = regexMap.getOrElse(r, {val matcher: java.util.regex.Matcher = r.pattern.matcher(s); matcher})
    matcher.region(start, length)
    if (matcher.lookingAt()) matcher.end else -1
  }

  def lineNumber(i: Int): Int  = lineColumns(i)._1

  def columnNumber(i: Int): Int = lineColumns(i)._2

  def lineColumn(i: Int): (Int, Int) = lineColumns(i)

}
//[Int, DiEdge[Int]]
//class InputGraph[N, E[X] <: EdgeLikeIn[X]](graph: Graph[N,E]) extends Input{
class InputGraph(g: Graph[Int,LDiEdge]) extends Input{
  private def n(outer: Int): g.NodeT = g get outer
  def length: Int = g.order//graphSize//g.order//graph.count(x => true)
  val lineColumns:Array[(Int, Int)] = Array.ofDim[(Int, Int)](length + 1)
  val regexMap: Map[Regex, java.util.regex.Matcher] = new java.util.HashMap[Regex, java.util.regex.Matcher]()
  calcLineColumns
  def calcLineColumns: Unit ={
    var lineCount = 0
    var lineNumber = 1
    var columnNumber = 1

    // Empty input: only the end of line symbol
    if(length == 1) {
      lineColumns(0) = (lineNumber, columnNumber)
    } else {
      for (i <- 0 until length) {
        lineColumns(i) = (lineNumber, columnNumber)
        if (charAt(i) == '\n') {
          lineCount += 1
          lineNumber += 1
          columnNumber = 1
        } else if (charAt(i) == '\r') {
          columnNumber = 1
        } else {
          columnNumber += 1
        }
      }
    }
    lineColumns(length) = (lineNumber, columnNumber)
  }
  def charAt(i: Int): scala.Char = {
    val f = n(i).edges.head.label.toString().charAt(0)
    println(f)
    f
  }
  def substring(start: Int, end: Int): String = {
    println("substring")
    if(n(end) isPredecessorOf n(start)){
      val some = n(start) pathTo n(end)
      val path = some.get
      val res = path.edges.flatMap { x => x.label.toString() }
      println(res.mkString)
      return res.mkString
    }
    return ""
  }

  def startsWith(prefix: String, toffset: Int): Option[Int] = {//: Boolean = {
    var i = n(toffset)
    var b = false
    breakable {for(s <-prefix.toCharArray)
    {
      val res = i.outgoing.filter(x => x.label.toString().equals(s.toString));
      if (res.size > 0) {
        i = res.head.nodes.last
        if(prefix.last == s) b = true
      }
      else{
        i = n(toffset)
        break
      }
    }}

    if(b)
      Some(i.value)
    else None
    //val r = !res.isEmpty
    //println(r)
    //r
  }
  def endsWith(suffix: String): Boolean = {
    val res = n(this.length-1).incoming.filter { x => x.label.toString() == suffix }
    res.size > 0
  }
  def matchRegex(r: Regex, start: Int, end: Int): Boolean = true
  def matchRegex(r: Regex, start: Int): Int = -1
  def lineNumber(i: Int): Int = lineColumns(i)._1
  def columnNumber(i: Int): Int = lineColumns(i)._2
  def lineColumn(i: Int): (Int, Int) = lineColumns(i)
}

object Input {

  def apply(s:String) = new InputString(s)
  def apply(s: Graph[Int,LDiEdge]) = new InputGraph(s)

  implicit def toInput(s: Graph[Int,LDiEdge]) = Input(s)
  implicit def toInput(s: String) = Input(s)
}
