package Spark_Scala

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions

object Linege {
def main(args: Array[String]): Unit = {
  
            val conf = new SparkConf()
              .setAppName("Lineage")
              .setMaster("local")
              
             val sc = new SparkContext(conf)
             val rdd = sc.textFile("outputt.txt",10)
             val rdd_wc = rdd.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_, 5)
             println(rdd_wc.count)
             print(rdd.toDebugString) // 원본 파일을 바로 읽고 난 다음에 나온기록
             println(rdd_wc.toDebugString) //일단 outputt.txt를 읽엇고 2개의 rdd가 생긴것이다 map과 hadoop의 rdd가 생겼다. 그리고  10개 밑에 과정은 파티션의 과정이 없어서 하나의 과정이다.
             //셔플에 의해서 스테이지가 달라지는것을 알수있다. 
             println(rdd.getNumPartitions) //10개
             println(rdd_wc.getNumPartitions) //reducebykey 처럼 5개로 바뀌어있을것이다. 
             
             
}  
}