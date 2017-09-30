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

import org.meerkat.sppf.NonPackedNode
import org.meerkat.tree.Tree

case class ParseSuccess(root: Tree, 
                        parseTimeStatistics: ParseTimeStatistics,
                        treeBuildingStatistics: TreeBuildingStatistics,
                        sppfStatistics: SPPFStatistics,
                        treeStatistics: TreeStatistics)
case class ParseGraphSuccess(roots: List[NonPackedNode],
                        parseTimeStatistics: ParseTimeStatistics,
                        sppfStatistics: SPPFStatistics)
case class ParseSemanticSuccess[T](results: List[T],
                                parseTimeStatistics: ParseTimeStatistics,
                                sppfStatistics: SPPFStatistics)

case class ParseError(index: Int, slot: String) {
  override def toString = s"Parse error at $slot and $index"
}

case class ParseTimeStatistics(nanoTime: Long, 
                               userTime: Long,
                               systemTime: Long) {
  override def toString = "User time: %d, Nano time: %d, System time:  %d\n".format(userTime, nanoTime, systemTime)  
}

case class TreeBuildingStatistics(nanoTime: Long,
                                  userTime: Long,
                                  systemTime: Long) {
  override def toString = "User time: %d, Nano time: %d, System time:  %d\n".format(userTime, nanoTime, systemTime)
}  

case class SPPFStatistics(nonterminalNodes: Int,
                          intermediateNodes: Int,
                          terminalNodes: Int,
                          packedNodes: Int,
                          ambiguousNodes: Int) {

  override def toString = "Nonterminal nodes: %d, Intermediate nodes: %d, Terminal nodes: %d, Packed nodes: %d, Ambiguous nodes: %d\n".format(nonterminalNodes, intermediateNodes, terminalNodes, packedNodes, ambiguousNodes)
}

case class TreeStatistics(nonterminalNodes: Int,
                          terminalNodes: Int,
                          ambiguousNodes: Int) {
  
  override def toString = "Nonterminal nodes: %d, Terminal nodes: %d, Ambiguous nodes: %d\n".format(nonterminalNodes, terminalNodes, ambiguousNodes)  
}


