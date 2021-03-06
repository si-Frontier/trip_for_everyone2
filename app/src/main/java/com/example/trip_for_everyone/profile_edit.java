package com.example.trip_for_everyone;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.loader.content.CursorLoader;

import android.accessibilityservice.GestureDescription;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class profile_edit extends AppCompatActivity implements View.OnClickListener{

    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;
    private FirebaseStorage storage;

    private String name;
    private String address;

    MainActivity activity;
//    private int NavigationFragment2 = 1;
    private DatabaseReference mDatabase;
    //private Uri mImageCaptureUri = null;
    Uri albumURI, photoURI = null;
    Boolean album = false;
    private de.hdodenhof.circleimageview.CircleImageView mPhotoImageView;
    private ImageButton mButton;
    String mCurrentPhotoPath;
    private NavigationFragment navigationFragment = new NavigationFragment();
    private NavigationFragment1 navigationFragment1 = new NavigationFragment1();
    private NavigationFragment2 navigationFragment2 = new NavigationFragment2();
    private navigation3 navigation3 = new navigation3();
    private ImageButton profile_edit_ok;
    private TextView profile_name;
    private TextView profile_address;


    Fragment fragment1;
    Fragment fragment2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        mButton = (ImageButton) findViewById(R.id.button);
        mPhotoImageView = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.profile_img);
        profile_name = (TextView) findViewById(R.id.profile_name);
        profile_address = (TextView) findViewById(R.id.profile_address);
        profile_edit_ok = (ImageButton) findViewById(R.id.profile_edit_ok);

        //fragment ??????

        //requestActivity??? fragment1??? ?????????

        //???????????? ??????, text???






        mButton.setOnClickListener(this);
//        profile_edit_ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                replaceFragment(NavigationFragment2);
//            }
//        });
        storage = FirebaseStorage.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();



        FirebaseStorage storage = FirebaseStorage.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference fileUrl = mDatabase.child(uid);
        //DatabaseReference fileUrl = mDatabase.child(uid);
        //StorageReference storageRef = storage.getReference(); //??????????????????
        //mPhotoImageView.setCircleBackgroundColor(R.id.notification_background);
        //Bitmap bm = BitmapFactory.decodeFile(mCurrentPhotoPath);
        //mPhotoImageView.setImageBitmap(bm);



        mDatabase.child("users").child(uid).child("userName").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name = dataSnapshot.getValue(String.class);
                System.out.println("fname"+ name);
                profile_name.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Log.e("MainActivity", String.valueOf(databaseError.toException())); // ????????? ??????
            }
        });

        mDatabase.child("users").child(uid).child("address").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                address = snapshot.getValue(String.class);
                System.out.println("faddress"+ address);
                profile_address.setText(address);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        profile_edit_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment1 = new NavigationFragment2();

                String ok = "ok";
                Bundle bundle = new Bundle();
                bundle.putString("ok",ok); // Key, Value
                fragment1.setArguments(bundle);
                finish();

                //getSupportFragmentManager().beginTransaction().remove(fragment1).commit();
               // onResume();





            }
        });




    }

//    // ????????? ????????? ??????
//    public void replaceFragment(int fragment){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        switch (fragment){
//            case 1:
//                Fra
//        }
//        fragmentTransaction.replace(R.id.container, );
//        fragmentTransaction.commit();
//    }
    /**
     * ??????????????? ????????? ????????????
     */
    private void doTakePhotoAction() {


        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.e("captureCamera Error", ex.toString());
            }
            if (photoFile != null) {
                // getUriForFile??? ??? ?????? ????????? Manifest provier??? authorites??? ???????????? ???

                photoURI = Uri.fromFile(photoFile);

                // ???????????? ????????? ?????? FileProvier??? Return?????? content://??????!!, providerURI??? ?????? ????????? ???????????? ?????? ??????
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);


                startActivityForResult(takePictureIntent, PICK_FROM_CAMERA);
            }
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        // ????????? ????????? ????????? ????????? ??????
//        String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
//        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));
//
//        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
//        // ?????????????????? ????????? ??????????????? ????????? ?????? ????????? ???????????? ?????????.
//        //intent.putExtra("return-data", true);
//        startActivityForResult(intent, PICK_FROM_CAMERA);
        }
    }



    public File createImageFile() throws IOException {
        //??????


        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + ".jpg";
        File imageFile = null;
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/Pictures", "gyeom");

        if (!storageDir.exists()) {
            Log.i("mCurrentPhotoPath1", storageDir.toString());
            storageDir.mkdirs();
        }



        imageFile = new File(storageDir, imageFileName);

        mCurrentPhotoPath = imageFile.getAbsolutePath();




        Bitmap bm = BitmapFactory.decodeFile(mCurrentPhotoPath);
        mPhotoImageView.setImageBitmap(bm);


        return imageFile;
    }
    /**
     * ???????????? ????????? ????????????
     */
    private void doTakeAlbumAction()
    {
        // ?????? ??????
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);

    }

    private void cropImage(){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(photoURI, "image/*");

        intent.putExtra("outputX", 90);
        intent.putExtra("outputY", 90);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);
//        if(album == false){
//            //?????? ????????? ??????
//            intent.putExtra("output", photoURI);
//
//        }else if(album == true){
//
//            //?????? ????????? ??????
//            intent.putExtra("output", albumURI);
//        }
        startActivityForResult(intent, CROP_FROM_CAMERA);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        else {


            switch (requestCode) {
                case CROP_FROM_CAMERA: {
                    // ????????? ??? ????????? ???????????? ?????? ????????????.
                    // ??????????????? ???????????? ?????????????????? ???????????? ?????? ?????????
                    // ?????? ????????? ??????
                    final Bundle extras = data.getExtras();


                    if (extras != null) {
                        Bitmap photo = extras.getParcelable("data");
                        mPhotoImageView.setImageBitmap(photo);

                    }



                    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);

                    if (album == false) {
                        mediaScanIntent.setData(photoURI);
                    } else if (album == true) {
                        album = false;
                        mediaScanIntent.setData(albumURI);


//
                    }
                    this.sendBroadcast(mediaScanIntent);
////



//                    // ?????? ?????? ??????
//                    File f = new File(photoURI.getPath());
//                    if (f.exists()) {
//                        f.delete();
//                    }

//                    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//                    if(album == false){
//                        mediaScanIntent.setData(photoURI);
//                    }else if(album == true){
//                        album = false;
//                        mediaScanIntent.setData(albumURI);
//                    }
//                    this.sendBroadcast(mediaScanIntent);

                    break;


                }

                case PICK_FROM_ALBUM: {

                    album = true;


                    File albumFile = null;
                    photoURI = data.getData();
                    //Log.d(TAG, "Uri:" + String.valueOf(photoURI));
                    try {
                        albumFile = createImageFile();
                        //SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMHH_mmss");
                        //Date now = new Date();
                       //String filename = formatter.format(now) + ".png";
                        // addValueEventListener
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        String uid = user.getUid();
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        DatabaseReference fileUrl = mDatabase.child(uid);
                        StorageReference storageRef = storage.getReference().child("images/"+"users/"+fileUrl);
                        storageRef.putFile(photoURI).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                //Toast.makeText(getApplicationContext(), "????????? ??????",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "????????????????????? ??????????????????", Toast.LENGTH_SHORT).show();
                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (albumFile != null) {
                       // StorageReference storageRef = storage.getReference();
                        albumURI = Uri.fromFile(albumFile);


//                        StorageReference storageRef = storage.getReference();
//                        final StorageReference riverRef = storageRef.child("images/"+albumURI.getLastPathSegment());
//                        UploadTask uploadTask = riverRef.putFile(imageUri);
//
//                        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
//                            @Override
//                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
//                                if(!task.isSuccessful()){
//                                    throw task.getException();
//                                }
//
//                                //???????????? ???????????? url???
//                                return  riverRef.getDownloadUrl();
//                            }
//                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Uri> task) {
//                                if(task.isSuccessful()){
//                                    Toast.makeText(profile_edit.this, "????????? ??????", Toast.LENGTH_SHORT).show();
//                                    @SuppressWarnings("VisibleForTests")
//                                    Uri downloadUrl = task.getResult();
//                                    Intent intent = new Intent(getApplicationContext(),profile_edit.class);
//                                    startActivity(intent);
//                                }
//                                else{
//                                    //?
//                                }
//                            }
//                        });

                    }






                }

                case PICK_FROM_CAMERA: {
                    // ???????????? ????????? ????????? ??????????????? ????????? ????????? ???????????????.
                    // ????????? ????????? ?????? ????????????????????? ???????????? ?????????.

//                    Intent intent = new Intent("com.android.camera.action.CROP");
//                    intent.setDataAndType(mImageCaptureUri, "image/*");
//
//                    intent.putExtra("outputX", 90);
//                    intent.putExtra("outputY", 90);
//                    intent.putExtra("aspectX", 1);
//                    intent.putExtra("aspectY", 1);
//                    intent.putExtra("scale", true);
//                    intent.putExtra("return-data", true);
//                    startActivityForResult(intent, CROP_FROM_CAMERA);
//
//                    break;
                    cropImage();

//


                }
            }
        }
    }




    // uri ???????????? ????????????
    private String getRealPathFromUri(Uri uri)
    {
        String[] proj=  {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(this,uri,proj,null,null,null);
        Cursor cursor = cursorLoader.loadInBackground();

        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(columnIndex);
        cursor.close();
        return  result;
    }

    @Override
    public void onClick(View v)
    {
        DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                doTakePhotoAction();
            }
        };

        DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                doTakeAlbumAction();
            }
        };

        DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        };

        new AlertDialog.Builder(this)
                .setTitle("???????????? ????????? ??????")
                .setPositiveButton("????????????", albumListener)
                .setNegativeButton("??????", cancelListener)
                .show();
    }
}