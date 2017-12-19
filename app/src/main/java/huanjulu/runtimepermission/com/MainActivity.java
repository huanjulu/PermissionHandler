package huanjulu.runtimepermission.com;

import android.support.annotation.NonNull;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import huanjulu.runtimpermission.com.AppPermission;
import huanjulu.runtimpermission.com.PermissionHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button storageRequest = (Button) findViewById(R.id.storage_request);
        Button cameraRequest = (Button) findViewById(R.id.camera_request);
        Button storageCheck = (Button) findViewById(R.id.storage_check);
        Button cameraCheck = (Button) findViewById(R.id.camera_check);


        cameraRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestCameraPermission();
            }
        });


        storageRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestStoragePermission();
            }
        });

        storageCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkStoragePermission();
            }
        });

        cameraCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkCameraPermission();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        PermissionHandler.onRequestPermissionsResultReceived(requestCode, permissions, grantResults, appPermissionGranted -> {

            Toast.makeText(this, "Camera Permission Granted", Toast.LENGTH_SHORT).show();

            // you select granted


            return null;

        }, appPermissionDenied -> {


            Toast.makeText(this, "Camera Permission Defined", Toast.LENGTH_SHORT).show();

            // you select  defined

            return null;
        });


    }

    private void requestCameraPermission() {

        //ask camera permission
        PermissionHandler.requestPermission(this, AppPermission.CAMERA.INSTANCE);


    }

    private void requestStoragePermission() {

        //ask storage permission
        PermissionHandler.requestPermission(this, AppPermission.READ_EXTERNAL_STORAGE.INSTANCE);

    }

    private void checkCameraPermission() {

        PermissionHandler.checkPermission(this, AppPermission.CAMERA.INSTANCE,

                //this permission was granted already
                granted -> {

                    Toast.makeText(this, "Camera Permission Granted", Toast.LENGTH_SHORT).show();

                    return null;
                },
                //this permission was denied already
                denied -> {
                    Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show();

                    return null;
                },

                //user select dot not ask again already
                rationale -> {
                    Toast.makeText(this, "Camera Permission Not Ask Again", Toast.LENGTH_SHORT).show();

                    return null;
                });
    }

    private void checkStoragePermission() {

        PermissionHandler.checkPermission(this, AppPermission.READ_EXTERNAL_STORAGE.INSTANCE,

                //this permission was granted already
                granted -> {
                    Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();
                    return null;
                },
                //this permission was denied already
                denied -> {
                    Toast.makeText(this, "Storage PermissionDenied", Toast.LENGTH_SHORT).show();
                    return null;
                },

                //user select dot not ask again already
                rationale -> {
                    Toast.makeText(this, "Storage Permission Not Ask Again", Toast.LENGTH_SHORT).show();
                    return null;
                });
    }
}
