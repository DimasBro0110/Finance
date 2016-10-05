package com.dimas.brosalin.orm.DAO

import com.dimas.brosalin.orm.models.LegsFrom

/**
  * Created by DmitriyBrosalin on 04/10/2016.
  */
trait ModelLegsFromDAO {

  def save(leg: LegsFrom)
  def update(leg: LegsFrom)
  def getById(id: Int): LegsFrom
  def getModelList(): List[LegsFrom]

}
