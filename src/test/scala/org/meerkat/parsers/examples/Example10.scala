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

package org.meerkat.parsers.examples

import org.meerkat.Syntax._
import org.meerkat.parsers._
import Parsers._
import OperatorParsers._
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.FunSuite

@RunWith(classOf[JUnitRunner])
class Example10 extends FunSuite {
  
  implicit val L = layout { """\s?""".r }
  
  val Op: Nonterminal & BinaryOp = syn { "+" ^ { _ => plus } | "*" ^ { _ => times } }
    
  val Num = syn { "[0-9]".r }
    
  val E: OperatorNonterminal & Int 
    = syn ( Op ~ "(" ~ E.+(",") ~ ")"   & { case op~x => x.reduceLeft((y,z) => op(y,z)) }
          | left { E ~ "*" ~ E }        & { case x~y => x*y }
          |> "-" ~ E                    & { x => -x }
          |> left { E ~ "+" ~ E }       & { case x~y => x+y }
          | Num                         ^ toInt )
    
  val es : OperatorNonterminal & List[Int] = E.+(",")
  val seq: OperatorParsers.OperatorSequenceBuilder[BinaryOp~List[Int]] = Op ~ "(" ~ E.+(",") ~ ")"
            
  test("test") { 
    val parser: Nonterminal & Int = start(E($))
    val result = parse(parser, " * ( 1 + - 1 + 1 , 1 * - 1 * 1 ) ") // = 0 (+) or -1 (*)
    assert(result.isSuccess)
  }
  
}