package com.jmc.tecnicaltestfifjmc.data.source.local.database.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jmc.tecnicaltestfifjmc.data.source.local.entity.PersonEntity

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPersona(characters: PersonEntity): Long

    @Query("select * from  person_table  WHERE correo = :mail and  password = :pass")
    fun getPersonaByMailAndPass(mail: String, pass: String): PersonEntity


    @Query("UPDATE person_table SET password = :pass WHERE correo = :mail")
    fun updatePersona(mail: String, pass: String)

}