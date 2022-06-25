package controllers
import org.apache.spark._
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

import java.sql
import java.sql.DriverManager
import java.util.Properties

object SparkObject {

  val url = "jdbc:mysql://localhost/scala_demo"
  def main(args:Array[String]): Unit ={
//    val sc = new SparkContext("local[*]","SparkObject")  // Spark context
//    var df = sc.textFile("C:\\Users\\HP\\Desktop\\Zone5 Employee data.csv")   // spark context  // RDD
//   // df.foreach(println)
//    val spark = SparkSession.builder().master("local").appName("SparkObj").getOrCreate()
//    var dataFr = spark.read.format("csv")
//      .option("header","true").option("maxRowsInMemory",10)
//      .option("inferSchema","true")
//      .option("treatEmptyValuesAsNulls","false")
//      .load("C:\\Users\\HP\\Desktop\\Zone5 Employee data.csv")   // DataFrame
//     val sparkObj = new SparkObjectReq("C:\\Users\\HP\\Desktop\\Zone5 Employee data.csv","root","","employee","csv")
//      sparkObj.SparkObject.writeToDb()

       //  dataFr.show(false)
        // val properties = new Properties()
        // properties.setProperty("user","root")     //user name
        // properties.setProperty("password","")  // No password
        //  dataFr.write.mode("overwrite").jdbc(url,"employee",properties)  // data write to Mysql

  }

}