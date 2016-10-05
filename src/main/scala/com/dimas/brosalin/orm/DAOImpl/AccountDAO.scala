package com.dimas.brosalin.orm.DAOImpl

import com.dimas.brosalin.orm.DAO.ModelAccountDAO
import com.dimas.brosalin.orm.Session.SessionConnection
import com.dimas.brosalin.orm.models.Account
import com.dimas.brosalin.orm.schema.SchemaMySQL
import org.squeryl.PrimitiveTypeMode._

/**
  * Created by DmitriyBrosalin on 30/09/2016.
  */
class AccountDAO(val sessionFactory: SessionConnection) extends ModelAccountDAO{


  override def save(account: Account): Unit = {

    this.sessionFactory.sessionConnection()

    transaction(

      SchemaMySQL.account.insert(account)

    )

  }

  override def update(account: Account): Unit = {

    this.sessionFactory.sessionConnection()

    transaction(

      SchemaMySQL.account.update(
        model =>
          where(model.id === account.id)
          .set(model.accountBalance := account.accountBalance)
      )

    )

  }

  override def getModelById(id: Int): Account = {

    try {

      this.sessionFactory.sessionConnection()

      transaction(

        SchemaMySQL.
          account.
          where(model => model.id === id).single

      )

    } catch {

      case ex : Exception =>

        println(ex)
        null

    }

  }

  override def getModelList: List[Account] = {

    try{

      this.sessionFactory.sessionConnection()

      transaction(

        from(SchemaMySQL.account)(slc => select(slc)).toList

      )

    }catch{

      case ex : Exception =>

        println(ex)
        null

    }

  }
}
