package com.dimas.brosalin.orm.Session

import com.typesafe.config.ConfigFactory
import org.squeryl.adapters.MySQLAdapter
import org.squeryl.{Session, SessionFactory}

/**
  * Created by DmitriyBrosalin on 05/10/2016.
  */
class SessionConnection {

  private def getProperties:
  (String, String, String, String, String) = {

    val config = ConfigFactory.load()

    (
      config.getString("DB.user"),
      config.getString("DB.password"),
      config.getString("DB.host"),
      config.getString("DB.schema"),
      config.getString("DB.driverName")
      )
  }

  def sessionConnection(): Unit = {

    val (name, password, host, schema, driverName) = getProperties

    Class.forName(s"$driverName")

    val session = SessionFactory.concreteFactory = Some(()
    => Session.create(
        java.sql.DriverManager.getConnection(
          s"jdbc:mysql://$host/$schema", s"$name", s"$password"),
        new MySQLAdapter)
    )

    session
  }

}

