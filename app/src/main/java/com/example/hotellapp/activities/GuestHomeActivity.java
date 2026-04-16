package com.example.hotellapp.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotellapp.R;
import com.example.hotellapp.model.User;
import com.example.hotellapp.utils.SessionManager;

public class GuestHomeActivity extends AppCompatActivity {
    private TextView tvNotice;
    private TextView tvRole, tvFullName, tvEmail, tvPhone, tvCCCD, tvAddress, tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_guest_info);

        tvRole = findViewById(R.id.tvRole);
        tvFullName = findViewById(R.id.tvFullName);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvCCCD = findViewById(R.id.tvCitizenId);
        tvAddress = findViewById(R.id.tvAddress);
        tvStatus = findViewById(R.id.tvStatus);

        tvNotice = findViewById(R.id.tvNotice);
        loadUserInfor();
    }
    private void loadUserInfor(){
        SessionManager session = new SessionManager(this);

        tvRole.setText(getSafeText(session.getRole()));
        tvFullName.setText(getSafeText(session.getFullName()));
        tvEmail.setText(getSafeText(session.getEmail()));
        tvPhone.setText(getSafeText(session.getPhone()));
        tvCCCD.setText(getSafeText(session.getCitizenId()));
        tvAddress.setText(getSafeText(session.getAddress()));
        tvStatus.setText(getSafeText(session.getStatus()));
        notifyUserUpdateInfo(session.getPhone(), session.getCitizenId(), session.getAddress());
    }
    private String getSafeText(String value){
        return (value == null || value.trim().isEmpty()) ? "-" : value;
    }

    private void notifyUserUpdateInfo(String phone, String citizenId, String address){
        boolean isPhone = (phone == null || phone.trim().isEmpty());
        boolean isCitizenId = (citizenId == null || citizenId.trim().isEmpty());
        boolean isAddress = (address == null || address.trim().isEmpty());
        if(!isPhone && !isCitizenId && !isAddress){
            tvNotice.setText("");
        }else{
            String noti = "Bạn nên cập nhập:";
            if(isPhone){
                if(!noti.equals("Bạn nên cập nhập:")) noti += ", ";
                noti += " Số điện thoại";
            }
            if (isCitizenId) {
                if(!noti.equals("Bạn nên cập nhập:")) noti += ", ";
                noti += " Căn cước công dân";
            }
            if(isAddress){
                if(!noti.equals("Bạn nên cập nhập:")) noti += ", ";
                noti += " Địa chỉ";
            }
            tvNotice.setText(noti);
        }

    }
    private void updateUserGuestInfo(User user){

    }
}