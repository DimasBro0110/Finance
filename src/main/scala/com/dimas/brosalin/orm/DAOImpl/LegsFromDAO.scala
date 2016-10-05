package com.dimas.brosalin.orm.DAOImpl

import com.dimas.brosalin.orm.DAO.ModelLegsFromDAO
import com.dimas.brosalin.orm.Session.SessionConnection
import com.dimas.brosalin.orm.models.LegsFrom
import com.dimas.brosalin.orm.schema.SchemaMySQL
import org.squeryl.PrimitiveTypeMode._


/**
  * Created by DmitriyBrosalin on 04/10/2016.
  */
class LegsFromDAO(val sessionFactory: SessionConnection)
  extends ModelLegsFromDAO{

  override def save(leg: LegsFrom) = {

   try{

     this.sessionFactory.sessionConnection()

     transaction{

       SchemaMySQL.account.update(model =>
         where(model.id === leg.accountFromID)
           .set(model.accountBalance := model.accountBalance.~ - leg.moneyValue)
       )

     }

     inTransaction{

       SchemaMySQL.legsFrom.insert(leg)

     }

   } catch{
     case ex: Exception =>
       println(ex)
   }

  }

  override def update(leg: LegsFrom) = {

  }

  override def getById(id: Int): LegsFrom = {

    try{

      this.sessionFactory.sessionConnection()

      transaction {

        SchemaMySQL.legsFrom.where(model => model.id === id).single

      }

    }catch{

      case ex: Exception =>
        println(ex)
        null

    }

  }

  override def getModelList(): List[LegsFrom] = {

    try{

      this.sessionFactory.sessionConnection()

      transaction{

        from(SchemaMySQL.legsFrom)(slc => select(slc)).toList

      }

    }catch{

      case ex: Exception =>
        println(ex)
        null

    }

  }

}
