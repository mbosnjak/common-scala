package edu.washington.cs.knowitall.common
package main

import Timing._
import java.util.Scanner

/**
 * This class is to be extended by an object to provide a simple main class
 * that processes lines.
 *
 * If the flag "-i" is specified, the program executes interactively.
 * Otherwise lines are expected from stdin.
 */
abstract class LineProcessor {
  def init(args: Array[String]) {}
  def exit(ns: Long) {}
  def process(line: String): String
  def main(args: Array[String]) {
    init(args)
    val scanner = new Scanner(System.in)

    val condition =
      if (args.length > 0 && args.contains("-i")) () => true
      else () => scanner.hasNextLine

    val ns = time {
      while (condition()) {
        println(process(scanner.nextLine))
      }
    }

    exit(ns)
  }
}
