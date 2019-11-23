package com.olimp;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * You are given a node that is the beginning of a linked list. This list always contains a tail and a loop.
 * Your objective is to determine the length of the loop.
 *
 *   Non-looping lists
 *     ✓ should return 0 when no list is provided
 *     ✓ should return 0 when a single node is provided
 *     ✓ should return 0 when a non-looping list is provided
 *
 *   Lists with a loop and a tail
 *     ✓ should parse the tail and return 1 for the Selfie loop
 *     ✓ should return 7 for a list of 8 nodes, 7 of which are in a loop
 *     ✓ should return 5 for a list of 8 nodes, 5 of which are in a loop
 *
 *   Fully looped lists
 *     ✓ should return 1 for a self-referencing node
 *     ✓ should return 4 for a list of 4 nodes, all of which are in a loop
 */

public class Main {

    public static void main(String[] args) {
        Tests();
    }

    private static void Tests() {
        assertEquals("", "", "");
    }

    public static class LoopInspector {

        public int loopSize(Node node) {
            if (node == null || node.getNext() == null) {
                return 0;
            }
            HashSet<Node> visitedNodes = new HashSet<>();
            Node lastNode = node;
            do {
                visitedNodes.add(lastNode);
                lastNode = lastNode.getNext();
                if (lastNode == null) {
                    return 0; //we don't have loops
                }
            } while (!visitedNodes.contains(lastNode));

            int visitedCounter = 0;
            Node circleStart = lastNode;
            do {
                lastNode = lastNode.getNext();
                visitedCounter++;
            } while (circleStart != lastNode);
            return visitedCounter;
        }

    }

}
