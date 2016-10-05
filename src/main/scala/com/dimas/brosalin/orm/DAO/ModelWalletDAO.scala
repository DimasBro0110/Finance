package com.dimas.brosalin.orm.DAO

import com.dimas.brosalin.orm.models.Wallet

/**
  * Created by DmitriyBrosalin on 02/10/2016.
  */
trait ModelWalletDAO {

  def save(wallet: Wallet)
  def update(wallet: Wallet)
  def getModelById(id: Int): Wallet
  def getModelList: List[Wallet]

}
