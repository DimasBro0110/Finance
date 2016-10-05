package com.dimas.brosalin.orm.DAO

import com.typesafe.config.ConfigFactory
import org.squeryl.adapters.MySQLAdapter
import org.squeryl.{Session, SessionFactory}

/**
  * Created by DmitriyBrosalin on 30/09/2016.
  */
trait SessionEstablishment {

  final def getProperties:
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

  final def sessionConnection(
                       name: String,
                       password: String,
                       host: String,
                       schema: String,
                       driverName: String
                       ): Unit = {

    Class.forName(s"$driverName")
    SessionFactory.concreteFactory = Some(()
    => Session.create(
        java.sql.DriverManager.getConnection(
          s"jdbc:mysql://$host/$schema", s"$name", s"$password"),
        new MySQLAdapter)
    )

  }

}
