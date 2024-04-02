package com.example.applications.arcface;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public abstract class ArcFaceBaseActivity extends AppCompatActivity {
    /**
     * 权限检查
     *
     * @param neededPermissions 需要的权限
     * @return 是否全部被允许
     */
    protected boolean checkPermissions(String[] neededPermissions) {
        if (neededPermissions == null || neededPermissions.length == 0) {
            return true;
        }
        boolean allGranted = true;
        for (String neededPermission : neededPermissions) {
            allGranted &= ContextCompat.checkSelfPermission(this, neededPermission) == PackageManager.PERMISSION_GRANTED;
        }
        return allGranted;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean isAllGranted = true;
        for (int grantResult : grantResults) {
            isAllGranted &= (grantResult == PackageManager.PERMISSION_GRANTED);
        }
        afterRequestPermission(requestCode, isAllGranted);
    }

    public void saveStringToSp(String key,String data){
        SharedPreferences sp = getSharedPreferences("sp_ttit",MODE_PRIVATE);
        sp.edit().putString(key,data)
                .commit();
    }
    public String getStringFromSp(String key){
        SharedPreferences sp = getSharedPreferences("sp_ttit",MODE_PRIVATE);
        return sp.getString(key,"");
    }
    public String getStringFromSp(String key,String defaultValue){
        SharedPreferences sp = getSharedPreferences("sp_ttit",MODE_PRIVATE);
        return sp.getString(key,defaultValue);
    }
    /**
     * 请求权限的回调
     *
     * @param requestCode  请求码
     * @param isAllGranted 是否全部被同意
     */
    abstract void afterRequestPermission(int requestCode, boolean isAllGranted);

    protected void showToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
    protected void showLongToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

}
