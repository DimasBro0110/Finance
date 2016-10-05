package com.dimas.brosalin.orm.DAOImpl

import com.dimas.brosalin.orm.DAO.ModelLegsToDAO
import com.dimas.brosalin.orm.Session.SessionConnection
import com.dimas.brosalin.orm.models.LegsTo
import com.dimas.brosalin.orm.schema.SchemaMySQL
import org.squeryl.PrimitiveTypeMode._
/**
  * Created by DmitriyBrosalin on 04/10/2016.
  */


class LegsToDAO(val sessionFactory: SessionConnection)
  extends ModelLegsToDAO
{

  override def save(legs: LegsTo) = {

    try {

      this.sessionFactory.sessionConnection()

      transaction {

        SchemaMySQL.account.update(model =>
          where(model.id === legs.accountToID)
            .set(model.accountBalance := model.accountBalance.~ + legs.moneyValue)
        )

      }

      inTransaction{

        SchemaMySQL.legsTo.insert(legs)

      }

    } catch {

      case ex: Exception =>
        println(ex)

    }

  }

  override def update(legs: LegsTo) = {

  }

  override def getById(id: Int): LegsTo = {

    try{

      this.sessionFactory.sessionConnection()

      transaction{

        SchemaMySQL.legsTo.where(model => model.id === id).single

      }


    }catch{

      case ex: Exception =>
        println(ex)
        null

    }

  }

  override def getModelList(): List[LegsTo] = {

    try{

      this.sessionFactory.sessionConnection()

      transaction {

        from(SchemaMySQL.legsTo)(slc => select(slc)).toList

      }

    }catch{

      case ex: Exception =>
        println(ex)
        null

    }

  }
}
