package controllers

import com.mysql.cj.jdbc.Driver

import javax.inject._
import play.api._
import play.api.libs.json._
import play.api.mvc._


import java.sql
import java.sql.DriverManager

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson("Hello"))
  }

  def sayHi()= Action{implicit  request: Request[AnyContent] =>
    Ok(Json.toJson("Hello Mritunjay"))
  }
  def dataExport()=Action{implicit request:Request[AnyContent]=>
    val json = request.body.asJson.get
    val url = "jdbc:mysql://localhost/scala_demo"
    val driver = "com.mysql.jdbc.Driver"
    val user= json("username").toString().replace("\"","")
    val password = json("password").toString().replace("\"","")
    val filePath = json("path").toString().replace("\"","")  // file Path
    val tableName = json("tablename").toString().replace("\"","")
    val format = json("format").toString().replace("\"","")
//    println(user)
//    println(password)
   // println(filePath)
    val sparkObj = new SparkObjectReq(filePath,user,password,tableName)  //Write To DB
    val t1 = System.nanoTime()
    sparkObj.SparkObject.writeToDb()
    val t2 = (System.nanoTime()-t1) / 1000000000;
    println(t2)
    Ok(Json.toJson("Success"))
  }

  def dataExportSync()= Action{implicit request :Request[AnyContent] =>
    val json = request.body.asJson.get
    val url = "jdbc:mysql://localhost/scala_demo"     ////Source Database
    val driver = "com.mysql.jdbc.Driver"
    val user= json("username").toString().replace("\"","")
    val password = json("password").toString().replace("\"","")
    val filePath = json("path").toString().replace("\"","")  // file Path
    println(filePath)
    val tableName = json("tablename").toString().replace("\"","")
    val destinationDatabase = "jdbc:mysql://localhost/scala_sync"    //Destination Database

    val sparkObj = new SparkObjectReq(filePath,user,password,tableName)  //Write To DB
    val t1 = System.nanoTime()
    sparkObj.SparkObject.writeToDb()
   // sparkObj.SparkObject.writeToDbLarge()
    val t2 = (System.nanoTime()-t1) / 1000000000;
    println(t2)
    val sync = new SparkJdbcSync(tableName,tableName+"Sync",url,destinationDatabase,user,password)
    val s1 = System.nanoTime()
     sync.JDBCRddObject.sync()// to Sync The Table
    val s2 = (System.nanoTime()-t1) / 1000000000;
    println("Sync Complete time :  "+ s2)
    Ok(Json.parse(
      """{
       "status" : "Complete",
       "Sync" : "True"
       }
        """
    ))
  }



}
