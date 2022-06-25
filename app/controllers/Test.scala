package controllers

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

import java.util.Properties

object Test {
  def main(args:Array[String]): Unit ={
    val url = "jdbc:mysql://localhost/scala_demo"
    val spark = SparkSession.builder().master("local").appName("SparkObj2").getOrCreate()
   // val sc = new SparkContext("local[*]","SparkObject")  // Spark context
    var df = spark.sparkContext.textFile("D:\\Scala_Projects\\filesToRead\\MB51.xlsx")   // spark context  // RDD
    println(df.count())
    val properties = new Properties()
    properties.setProperty("user","root")     //user name
    properties.setProperty("password","")  // No password

    import spark.implicits._
    val dataSet = df.toDF()


    dataSet.write.mode("overwrite").jdbc(url,"testMb",properties)  // data write to Mysql
  }



}
