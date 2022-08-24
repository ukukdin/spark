package Spark_Scala

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions


object ShuffleByRepartition {
  def main(args: Array[String]): Unit = {
    
  
   val conf = new SparkConf()
              .setAppName("ShuffleByRepartition")
              .setMaster("local")
              
             val sc = new SparkContext(conf)
             val data = sc.textFile("outputt.txt")
             println(data.getNumPartitions +"몇개일까요")
             val distData = data.map(r =>r+"_map")
             print(distData.getNumPartitions+"몇개일까요???")// 다른 파티션과 전혀 간섭이 없는 map스타일의 작업은 트랜스포매이션은 파티션의 변화가 없습니다. 
             //이제 re파티션을 10개로 해줍니다. 그리고 순서가 섞여서 새로운 new 데이터가 만들어집니다.
             val newData = distData.repartition(10)
             newData.getNumPartitions
             print(newData.toDebugString)
             println("새롭게 만든 데이터의 갯수"+newData.count)
             newData.collect.foreach(println)
             
//            data.saveAsTextFile("dataoutputt.txt")
//            저장된 파일 내용 확인 (partition별로 원본 파일 내용 유지되어 있음)
//            newData.saveAsTextFile("newData") 
             // 저장된 파일 내용 확인 (partition 별로 원본 파일 내용 섞여 있음)
             val intRDD2 = sc.parallelize(1 to 20)
//             intRDD2.saveAsTextFile("intRDD2")
             print(intRDD2)
             val intRDD5 = intRDD2.repartition(5)
//             intRDD5.saveAsTextFile("intRDD5")
             print(intRDD5)             
  } 
  
  
  
  /*앞스테이지와 뒤스테이지 간에 결과를 보자 
			코드를 작성할때 newdata.count를 실행하고 rdd가 만들어지느 ㄴ과정을 보면 셔플이 발생을 한다. 앞쪽 스테이지 와 뒷쪽 스테이지는 동일한 작업이고 앞에는 count만 하는것이고 뒷것은 그냥 가져온것이다
			즉 동일한 데이터를 읽어오는 동일한 rdd인데 앞스테이지를 가져온다. 
			  
   * 셔플데이터가 쓰이는지 확인을 ui통해서 확인을 할수있다. 새로운 액션을 실행해보자 
   * */
  
             
}