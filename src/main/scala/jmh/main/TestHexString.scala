package jmh.main

// Must not be in default package
import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

/* Default settings for benchmarks in this class */
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Array(Mode.Throughput))
@State(Scope.Thread)
class TestHexString {
  val randomStrings = (0 to 100).map(_ => randomString())

  @Benchmark
  def dropWhile =
    randomStrings.map(_.dropWhile(_ == ' '))

  @Benchmark
  def replaceFirst =
    randomStrings.map(_.replaceFirst("^ +", ""))

  def randomString(): String = {
    val i = scala.util.Random.nextInt(100)
    val a = (0 to i).map(_ => ' ').mkString
    val b = Array.fill(200)(0.toByte)
    scala.util.Random.nextBytes(b)
    a + b.toList.map(_.toChar).mkString
  }

  def f(b: String): String =
    b.substring(scala.util.Random.nextInt(5))
}
