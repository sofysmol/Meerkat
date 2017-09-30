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

package org.meerkat.parsers

import org.meerkat.util.Input
import org.meerkat.util.visualization._

package object examplesgraph {
  def getResult[T, V](parser: AbstractCPSParsers.AbstractSymbol[T, V], input: Input, filename: String) = {
    parseGraph(parser, input) match {
      case Left(error) => println(error)
      case Right(ParseGraphSuccess(roots, _, _)) => roots.foreach(root => visualize(root, input, filename + root, "."))
    }
    /*if (result.isSuccess)
      result.asSuccess.roots.foreach(root => visualize(root, input, filename + root, "."))
    else println(result.)*/
  }

  def getSppfWithStarts[T, V](parser: AbstractCPSParsers.AbstractSymbol[T, V], input: Input, starts: Set[Int], filename: String) = {
    getSPPFLookup(parser, input).getStartNodesFilterByStarts(parser, starts) match {
      case Some(roots) => roots.foreach(root => visualize(root, input, filename + root, "."))
      case None => println("Not Found")
    }
  }

  def getSppfWithEnds[T, V](parser: AbstractCPSParsers.AbstractSymbol[T, V], input: Input, ends: Set[Int], filename: String) = {
    getSPPFLookup(parser, input).getStartNodesFilterByEnds(parser, ends) match {
      case Some(roots) => roots.foreach(root => visualize(root, input, filename + root, "."))
      case None => println("Not Found")
    }
  }

  def getSppfWithStartsAndEnds[T, V](parser: AbstractCPSParsers.AbstractSymbol[T, V], input: Input, starts: Set[Int], ends: Set[Int], filename: String) = {
    getSPPFLookup(parser, input).getStartNodesFilterByStartAndEnds(parser, starts, ends) match {
      case Some(roots) => roots.foreach(root => visualize(root, input, filename + root, "."))
      case None => println("Not Found")
    }
  }

  val toStr: String => String = x => x
  val toInt: String => Int = x => x.toInt

  trait BinaryOp extends ((Int, Int) => Int)

  val plus: BinaryOp = new BinaryOp {
    def apply(x: Int, y: Int) = x + y
  }
  val times: BinaryOp = new BinaryOp {
    def apply(x: Int, y: Int) = x * y
  }

  sealed trait Exp

  case class Add(l: Exp, r: Exp) extends Exp

  case class Mul(l: Exp, r: Exp) extends Exp

  case class Sub(l: Exp, r: Exp) extends Exp

  case class Div(l: Exp, r: Exp) extends Exp

  case class Neg(l: Exp) extends Exp

  case class Pow(l: Exp, r: Exp) extends Exp

  case class Num(n: Int) extends Exp

}