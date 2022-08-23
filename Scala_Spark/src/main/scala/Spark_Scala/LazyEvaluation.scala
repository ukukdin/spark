package Spark_Scala

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions
//잘못된 파일명을 넣었으면 바로 에러가 안나고 카운터 실행을 했기때문에 에러가 발생하게된다. 
//reducebykey를 읽는 트리거를 했고 실행하였기에 에러가 나온것이다. 아마 따로따로 설정하였으면 map filter 등등 그러면 오류가 나기전까지 실행이 되었을것이다. 
object LazyEvaluation {
 def main(args: Array[String]): Unit = {
            val conf = new SparkConf()
              .setAppName("Lazy Evaluatoin")
              .setMaster("local")
              
           val sc = new SparkContext(conf)
           val data = sc.textFile("outputt.txt")
           val distData = data.map(r=>r+"_map")
           print(distData.count+"카운트된값")
//           val dat = sc.textFile("outputt.md")
//           print(dat.count+"변경된값") 
           
         
           
//           val rdd_wc = sc.textFile("outputt.txt").flatMap(_.split(" ")).map((_, 1)).groupByKey() 
           val rdd_wc = sc.textFile("outputt.txt").flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_) 
           rdd_wc.collect.take(10).foreach(println)
       
//           val rdd_wc = sc.textFile("outputt.md")
           println(rdd_wc+"rddwc값입니다. 확인해보시죠")
 } 
 
 
}