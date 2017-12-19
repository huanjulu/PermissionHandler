# PermissionHandler

Simplifying check permissions in android


# Gradle usage

``` java
 compile 'com.huanjulu.android:permissionhandler:1.0.2'
```

# What can this library do?

 - With this library, you can easily check for a series of related permissions on a project.
 - Using this library, you can easily apply the permissions you need, such as Camera permission, Storage Permisson... And so on.
 - You can easily check the user for the "Never ask Again" option. And you can customize your future operations.
 
----

 - 利用这个library , 你可以很方便的在项目中检查是否拥有一系列的相关权限
 - 利用这个library , 你可以很方便的去申请你所需要的权限，比如Camera permission, Storage Permisson ...等等.
 -你可以很方便的检查用户是够勾选了 “Never ask Again”选项。并且你可以自定义你以后的操作.



[![N|Solid](https://cldup.com/dTxpPi9lDf.thumb.png)](https://nodesource.com/products/nsolid)



# New Features!

  - Import a HTML file and watch it magically convert to Markdown
  

# How to Use

-  ##### First
You nees decelar the permission what you need in your "AndroidManifest.xml" file. like
``` java
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.CAMERA" />
```

-  ##### Request the permissions which you need
-  ##### 申请你所需要的权限
``` java
         //ask camera permission
        PermissionHandler.requestPermission(this, AppPermission.CAMERA.INSTANCE);
```
``` java
        //ask storage permission
        PermissionHandler.requestPermission(this, AppPermission.READ_EXTERNAL_STORAGE.INSTANCE);
```
-  ##### In the permissions dialog of the system,listen the click event of user
-  ##### 在系统的权限申请弹窗中，监听用户的点击事件

if you do this, the first thing what you need to do is that overrive the onRequestPermissionsResult funtion ,like this 
``` java
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        PermissionHandler.onRequestPermissionsResultReceived(requestCode, permissions, grantResults, appPermissionGranted -> {

            Toast.makeText(this, " Permission Granted", Toast.LENGTH_SHORT).show();
            // you select granted
            return null;

        }, appPermissionDenied -> {

            Toast.makeText(this, " Permission Defined", Toast.LENGTH_SHORT).show();
            // you select  defined
            return null;
        });


    }
```



-  ##### Check to see if you have the permissions what you need
-  ##### 检查是否拥有你所需要的权限

here is some code what check camera permsiion, storage permission..so on 
``` java
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
```
``` java

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
```
-  ##### Finally,enjoy use it !!



# License
``` java
  Copyright 2015 Anthony Restaino
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
