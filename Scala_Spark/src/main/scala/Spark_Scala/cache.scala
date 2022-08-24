package Spark_Scala

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions

object cache {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
					.setAppName("partF")
					.setMaster("local")

					val sc = new SparkContext(conf)
    	
    	
     val data = sc.textFile("dataoutputt.txt")
     
     val distData = data.map(r => r+ "_map")
       distData.name = "distData"
       distData.cache //--캐시
       //코드상으로 캐시를 하게되면 캐시를 할것이라고 플래그만 달아놓은 상태이고 액션이 실행되면 그때서야 실행이 됩니ㅏㄷ. 
       print(distData.take(5)) //distData.top(5)과는 다른 결과 나옴. 
      //웹ui 확인 (storage탭에서 Fraction Cached부분이 100% 가 아님을 확인) 100퍼가 안되는 이유는 take(5) 할대 distdata는 파티션이 2개고 1개만 사용해도 5개를 가지고올수 있슴 그리고 spark는 게을러서 반만 올라간것입니다. 
      //(tast도 1개만 실행 되었음을 확인 )
      distData.collect
      //strogate 탭에서 fraction cached부분이 100퍼 임을 확인, tastk도 모두 실행 되었읆을 확인.
      
          print(distData.getStorageLevel)  //캐시 storagelevel 확인
          print(distData.unpersist()) //-캐시삭제
            
    }
}