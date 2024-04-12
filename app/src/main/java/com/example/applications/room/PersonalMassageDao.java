package com.example.applications.room;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonalMassageDao {
    @Insert
    void insert(PersonalMassageEntity personalMassageEntity);

    @Query("SELECT * FROM personal_massage WHERE token = :token")
    PersonalMassageEntity getByToken(String token);

    @Query("SELECT * FROM personal_massage")
    List<PersonalMassageEntity> getAll();

    @Update
    void update(PersonalMassageEntity personalMassageEntity);

    @Delete
    void delete(PersonalMassageEntity personalMassageEntity);
}
