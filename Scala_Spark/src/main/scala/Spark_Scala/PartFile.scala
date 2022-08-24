package Spark_Scala

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions

object PartFile {
  def main(args: Array[String]): Unit = {
    
     val conf = new SparkConf()
				   .setAppName("partF")
				   .setMaster("local")
				   
				   val sc = new SparkContext(conf)
				   val rd = sc.textFile("dataoutputt.txt")
				   print(rd.getNumPartitions)
				   val rd2 = sc.textFile("NOTICE")
				   rd2.getNumPartitions
				   val rd3 = sc.textFile("dataoutputt.txt,NOTICE")
				   rd3.getNumPartitions
  			   val rd4 = sc.textFile("FR*,NO*,da*")
				   rd.saveAsTextFile("rd")
				   rd2.saveAsTextFile("rd2")
				   rd3.saveAsTextFile("rd3")
				   rd4.saveAsTextFile("rd4") 
     
    }
				   
				   
  
}