package controllers

import org.apache.spark.sql.SparkSession

import java.util.Properties

class SparkJdbcSync( sourceTable:String,destinationTable:String,sourceDatabase:String , destinationDatabase:String, userName:String , password:String) {

  object JDBCRddObject {

    def sync(): Unit ={
      val driver = "com.mysql.jdbc.Driver"
      Class.forName(driver).newInstance()

      //  val conf = new SparkConf().setAppName("readFromDB").setMaster("local[*]")//.set("spark.executor.memory",)
      //  val sc = new SparkContext(conf)
      val spark = SparkSession.builder().master("local").appName("readAndWrite").getOrCreate()
      val dataFrameMySql = spark.read.format("jdbc")
        .option("url",sourceDatabase)
        .option("driver",driver)
        .option("dbtable",sourceTable)
        .option("user",userName)
        .option("password",password)
        //.option("numPartitions",2)
        //.option("lowerBound",0)
        //.option("upperBound",4)
        .load()
        println("MySQL dataFrame Loaded")

      val properties = new Properties()
      properties.setProperty("user",userName)
      properties.setProperty("password",password)
      dataFrameMySql.write.mode("overwrite").jdbc(destinationDatabase,destinationTable,properties)
     // println("Sync Complete")

    }

  }

}
