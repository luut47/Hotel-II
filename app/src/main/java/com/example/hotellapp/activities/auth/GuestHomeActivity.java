package com.example.hotellapp.activities.auth;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotellapp.R;
import com.example.hotellapp.api.RetrofitClient;
import com.example.hotellapp.model.auth.request.UpdateUserInfoRequest;
import com.example.hotellapp.model.auth.response.UpdateUserInfoResponse;
import com.example.hotellapp.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GuestHomeActivity extends AppCompatActivity {
    private TextView tvNotice;
    private TextView tvRole, tvFullName, tvEmail, tvPhone, tvCCCD, tvAddress, tvStatus;
    private Button btnUpdateInfo;

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

        btnUpdateInfo = findViewById(R.id.btnUpdateInfo);
        btnUpdateInfo.setOnClickListener(v -> showUpdateDialog());
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
    private void showUpdateDialog(){
        SessionManager session = new SessionManager(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_update_user_info, null);
        EditText edtUpdateFullName = view.findViewById(R.id.edtUpdateFullName);
        EditText edtUpdatePhone = view.findViewById(R.id.edtUpdatePhone);
        EditText edtUpdateCitizenId = view.findViewById(R.id.edtUpdateCitizenId);
        EditText edtUpdateAddress = view.findViewById(R.id.edtUpdateAddress);

        edtUpdateFullName.setText(session.getFullName());
        edtUpdatePhone.setText(session.getPhone());
        edtUpdateCitizenId.setText(session.getCitizenId());
        edtUpdateAddress.setText(session.getAddress());

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Cập nhập thông tin cá nhân")
                .setView(view)
                .setNegativeButton("Hủy",null)
                .setPositiveButton("Lưu",null)
                .create();
        dialog.show();

        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(v -> {
            String fullName = edtUpdateFullName.getText().toString().trim();
            String phone = edtUpdatePhone.getText().toString().trim();
            String citizenId = edtUpdateCitizenId.getText().toString().trim();
            String address = edtUpdateAddress.getText().toString().trim();
            if(!validateInput(fullName,phone,citizenId)) return;

            UpdateUserInfoRequest request = new UpdateUserInfoRequest(
                    fullName,
                    emptyToNull(phone),
                    emptyToNull(citizenId),
                    emptyToNull(address),
                    emptyToNull(session.getAvatarUrl()));
            updateUserGuestInfo(request,dialog);
        });
    }
    private boolean validateInput(String fullname, String phone, String citizenId){
        if(fullname.length() < 2){
            Toast.makeText(this, "Họ tên tối thiểu 2 ký tự", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!TextUtils.isEmpty(phone)&& !phone.matches("\\d{10}")){
            Toast.makeText(this, "Số điện thoại phải đúng 10 số", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!TextUtils.isEmpty(citizenId) && !citizenId.matches("\\d{12}")){
            Toast.makeText(this, "CCCD phải đúng 12 số", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private String emptyToNull(String value){
        return (value==null || value.trim().isEmpty()) ? null : value;
    }
    private void updateUserGuestInfo(UpdateUserInfoRequest request, AlertDialog dialog) {
        SessionManager session = new SessionManager(this);
        String token = session.getToken();

        if (token == null || token.trim().isEmpty()) {
            Toast.makeText(this, "Phiên đăng nhập đã hết, vui lòng đăng nhập lại", Toast.LENGTH_LONG).show();
            return;
        }

        RetrofitClient.getApiService()
                .updateUserGuestInfo("Bearer " + token, request)
                .enqueue(new Callback<UpdateUserInfoResponse>() {
                    @Override
                    public void onResponse(Call<UpdateUserInfoResponse> call,
                                           Response<UpdateUserInfoResponse> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().getUser() != null) {
                            session.saveLogin(token, response.body().getUser());
                            loadUserInfor();
                            dialog.dismiss();

                            Toast.makeText(
                                    GuestHomeActivity.this,
                                    response.body().getMessage(),
                                    Toast.LENGTH_SHORT
                            ).show();
                        } else {
                            Toast.makeText(
                                    GuestHomeActivity.this,
                                    "Cập nhật thất bại",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateUserInfoResponse> call, Throwable t) {
                        Toast.makeText(
                                GuestHomeActivity.this,
                                "Lỗi kết nối: " + t.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                });
    }
}