package com.dimas.brosalin.orm.DAOImpl

import com.dimas.brosalin.orm.Session.SessionConnection
import com.dimas.brosalin.orm.models.Transaction
import com.dimas.brosalin.orm.schema.SchemaMySQL
import org.squeryl.PrimitiveTypeMode._

/**
  * Created by DmitriyBrosalin on 06/10/2016.
  */
class TransactionDAO(val sessionFactory: SessionConnection) {

  def save(tran: Transaction) = {

    this.sessionFactory.sessionConnection()

    transaction{

      SchemaMySQL.transaction.insert(tran)

    }

  }

}
