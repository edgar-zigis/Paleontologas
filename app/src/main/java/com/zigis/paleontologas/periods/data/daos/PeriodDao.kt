package com.zigis.paleontologas.periods.data.daos

import androidx.room.Dao
import com.zigis.paleontologas.application.data.BaseDao
import com.zigis.paleontologas.periods.data.entities.Period

@Dao
interface PeriodDao : BaseDao<Period>