package com.example.applications.listener;


import com.example.applications.room.PersonalMassageEntity;

public interface OnAsyncTaskListener {
    void onTaskCompleted(PersonalMassageEntity result);
}
