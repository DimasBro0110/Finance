package com.dimas.brosalin.orm.DAOImpl

import com.dimas.brosalin.orm.DAO.ModelUserDAO
import com.dimas.brosalin.orm.Session.SessionConnection
import com.dimas.brosalin.orm.models.User
import com.dimas.brosalin.orm.schema.SchemaMySQL
import org.squeryl.PrimitiveTypeMode._

/**
  * Created by DmitriyBrosalin on 02/10/2016.
  */
class UserDAO(val sessionFactory: SessionConnection)
  extends ModelUserDAO{

  override def save(user: User) = {

    try {

      this.sessionFactory.sessionConnection()

      transaction {

        SchemaMySQL.user.insert(user)

      }

    }catch {

      case ex: Exception =>
        println(ex)

    }

  }

  override def update(user: User) = {

    try{

      this.sessionFactory.sessionConnection()

      transaction{

        SchemaMySQL.user.update(
          model =>
            where(model.id === user.id)
            .set(
              model.age := user.age,
              model.firstName := user.firstName,
              model.lastName := user.lastName,
              model.password := user.password
            )
        )

      }

    }catch {

      case ex: Exception =>
        println(ex)

    }

  }

  override def getModelById(id: Int): User = {

    try{

      this.sessionFactory.sessionConnection()

      transaction {

        SchemaMySQL
          .user
          .where(model => model.id === id)
          .single

      }

    }catch {
      case ex: Exception =>
        println(ex)
        null
    }

  }

  override def getModelList: List[User] = {

    try{

      this.sessionFactory.sessionConnection()

      transaction(

        from(SchemaMySQL.user)(slc => select(slc)).toList

      )

    }catch{

      case ex : Exception =>

        println(ex)
        null

    }

  }
}
