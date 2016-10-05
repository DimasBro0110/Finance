package com.dimas.brosalin.orm.DAOImpl

import com.dimas.brosalin.orm.DAO.ModelWalletDAO
import com.dimas.brosalin.orm.Session.SessionConnection
import com.dimas.brosalin.orm.models.Wallet
import com.dimas.brosalin.orm.schema.SchemaMySQL
import org.squeryl.PrimitiveTypeMode._

/**
  * Created by DmitriyBrosalin on 02/10/2016.
  */
class WalletDAO(sessionFactory: SessionConnection)
  extends ModelWalletDAO{

  override def save(wallet: Wallet) = {

    try{

      this.sessionFactory.sessionConnection()

      transaction {

        SchemaMySQL.wallet.insert(wallet)

      }

    }catch {

      case ex: Exception =>
        println(ex)

    }

  }

  override def update(wallet: Wallet) = {

    try{

      this.sessionFactory.sessionConnection()

      transaction {

        SchemaMySQL
          .wallet
          .update(
            model => where(model.id === wallet.id)
              .set(model.walletCurrency := wallet.walletCurrency)
          )

      }

    }catch {

      case ex: Exception =>
        println(ex)

    }

  }

  override def getModelById(id: Int): Wallet = {

    try {

      this.sessionFactory.sessionConnection()

      transaction{

        SchemaMySQL
          .wallet
          .where(model => model.id === id)
          .single

      }

    }catch{

      case ex: Exception =>
        println(ex)
        null
    }

  }

  override def getModelList: List[Wallet] = {

    try{

      this.sessionFactory.sessionConnection()

      transaction {

        from(SchemaMySQL.wallet)(slc => select(slc)).toList

      }

    }catch {

      case ex: Exception =>
        println(ex)
        null

    }

  }
}
