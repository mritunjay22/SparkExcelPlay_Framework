package controllers

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

import java.util.Properties

class SparkObjectReq(path:String,userName:String,pass:String,tableName:String) {
  object SparkObject {
    //using data frame
    def writeToDb(): Unit ={
      val url = "jdbc:mysql://localhost/scala_demo"
     // val sc = new SparkContext("local[*]","SparkObject")  // Spark context
    // var df = sc.textFile("C:\\Users\\HP\\Desktop\\Zone5 Employee data.csv")   // spark context  // RDD
    val spark = SparkSession.builder().master("local[*]").appName("SparkObj").getOrCreate()
      var formatChange ="csv"
        if(path.contains(".xlsx") || path.contains(".xlsm")){
        formatChange ="com.crealytics.spark.excel"
          //formatChange="excel"
      }
      //import spark.implicits._
     //   val u = df.toDF()
      val dataFr = spark.read.format(formatChange)
        .option("header","true").option("maxRowsInMemory",250)
        .option("inferSchema","true")
        .option("treatEmptyValuesAsNulls","false")
        .load(path)   // DataFrame
        dataFr.show(false)
         //dataFr.count();
        val properties = new Properties()
       properties.setProperty("user",userName)     //user name
       properties.setProperty("password",pass)  // No password
       dataFr.write.mode("overwrite").jdbc(url,tableName,properties)  // data write to Mysql
        //spark.newSession()


    }
    def writeToDbLarge(): Unit ={
      val url = "jdbc:mysql://localhost/scala_demo"
      val spark = SparkSession.builder().master("local").appName("SparkObj2").getOrCreate()
       val sc = new SparkContext("local[*]","SparkObject")  // Spark context
        var df = sc.textFile(path)   // spark context  // RDD
      val properties = new Properties()
      properties.setProperty("user",userName)     //user name
      properties.setProperty("password",pass)  // No password
      var formatChange ="csv"
      if(path.contains(".xlsx") || path.contains(".xlsm")){
        // formatChange ="com.crealytics.spark.excel"
        formatChange="excel"
      }

      import spark.implicits._
         val dataSet = df.toDF()

      dataSet.write.mode("overwrite").jdbc(url,tableName,properties)  // data write to Mysql

    }
  }

}
