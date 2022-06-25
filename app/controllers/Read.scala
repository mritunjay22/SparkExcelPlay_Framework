package controllers

import org.apache.spark.sql.SparkSession

import java.util.Properties

object Read {
def main(args:Array[String]): Unit ={
    // val sc = new SparkContext("local[*]","SparkObject")  // Spark context
    // var df = sc.textFile("C:\\Users\\HP\\Desktop\\Zone5 Employee data.csv")   // spark context  // RDD
    val spark = SparkSession.builder().master("local[*]").appName("SparkObj").getOrCreate()
      //formatChange="excel"  com.crealytics.spark.excel

    //import spark.implicits._
    //   val u = df.toDF()
    val dataFr = spark.read.format("excel")
      .option("header","true").option("maxRowsInMemory",250)
      .option("inferSchema","true")
      .option("treatEmptyValuesAsNulls","false")
      .load("D:\\Scala_Projects\\filesToRead\\part1.xlsx")   // DataFrame
    dataFr.show(false)
    //dataFr.count();




}
}
