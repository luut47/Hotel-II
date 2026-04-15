package com.example.hotellapp.utils;
import android.content.Context;
import android.content.Intent;

import com.example.hotellapp.activities.AdminHomeActivity;
import com.example.hotellapp.activities.GuestHomeActivity;
import com.example.hotellapp.activities.ReceptionistHomeActivity;
public class RoleRouter {
    public static void goToHomeByRole(Context context, String roleName) {
        Intent intent;
        if("Admin".equalsIgnoreCase(roleName)){
            intent = new Intent(context, AdminHomeActivity.class);
        }else if("Receptionist".equalsIgnoreCase(roleName)){
            intent = new Intent(context, ReceptionistHomeActivity.class);
        }else{
            intent = new Intent(context, GuestHomeActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
}
