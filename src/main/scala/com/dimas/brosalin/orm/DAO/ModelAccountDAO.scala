package com.dimas.brosalin.orm.DAO

import com.dimas.brosalin.orm.models.{Account, User}
import com.typesafe.config.ConfigFactory

/**
  * Created by DmitriyBrosalin on 30/09/2016.
  */
trait ModelAccountDAO
{

  def save(account: Account)
  def update(account: Account)
  def getModelById(id: Int): Account
  def getModelList: List[Account]

}
