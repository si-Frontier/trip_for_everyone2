package com.example.trip_for_everyone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class profile_edit extends AppCompatActivity implements View.OnClickListener{

    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;

    private Uri mImageCaptureUri = null;
    Uri albumURI, photoURI = null;
    Uri imageUri;
    Boolean album = false;
    private de.hdodenhof.circleimageview.CircleImageView mPhotoImageView;
    private Button mButton;
    String mCurrentPhotoPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        mButton = (Button) findViewById(R.id.button);
        mPhotoImageView = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image);

        mButton.setOnClickListener(this);

        Bitmap bm = BitmapFactory.decodeFile(mCurrentPhotoPath);
        mPhotoImageView.setImageBitmap(bm);


    }

    /**
     * 카메라에서 이미지 가져오기
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
                // getUriForFile의 두 번째 인자는 Manifest provier의 authorites와 일치해야 함

                photoURI = Uri.fromFile(photoFile);

                // 인텐트에 전달할 때는 FileProvier의 Return값인 content://로만!!, providerURI의 값에 카메라 데이터를 넣어 보냄
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);


                startActivityForResult(takePictureIntent, PICK_FROM_CAMERA);
            }
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        // 임시로 사용할 파일의 경로를 생성
//        String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
//        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));
//
//        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
//        // 특정기기에서 사진을 저장못하는 문제가 있어 다음을 주석처리 합니다.
//        //intent.putExtra("return-data", true);
//        startActivityForResult(intent, PICK_FROM_CAMERA);
        }
    }

    public File createImageFile() throws IOException {
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



        return imageFile;
    }
    /**
     * 앨범에서 이미지 가져오기
     */
    private void doTakeAlbumAction()
    {
        // 앨범 호출
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
//            //해당 경로에 저장
//            intent.putExtra("output", photoURI);
//
//        }else if(album == true){
//
//            //해당 경로에 저장
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
                    // 크롭이 된 이후의 이미지를 넘겨 받습니다.
                    // 이미지뷰에 이미지를 보여준다거나 부가적인 작업 이후에
                    // 파일 갤러리 저장
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
                    }
                    this.sendBroadcast(mediaScanIntent);
////

//                    // 임시 파일 삭제
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
                    // 이후의 처리가 카메라와 같으므로 일단  break없이 진행합니다.
                    // 실제 코드에서는 좀더 합리적인 방법을 선택하시기 바랍니다.
//                   File albumFile = null;
//                   try{
//                       albumFile = createImageFile();
//                   }catch (IOException e){
//                       e.printStackTrace();
//                   }
//                   if(albumFile != null){
//                       mImageCaptureUri = Uri.fromFile(albumFile);
//                   }
//
//                   mImageCaptureUri = data.getData();
                    album = true;
                    File albumFile = null;
                    try {
                        albumFile = createImageFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (albumFile != null) {
                        albumURI = Uri.fromFile(albumFile);
                    }

                    photoURI = data.getData();


                }

                case PICK_FROM_CAMERA: {
                    // 이미지를 가져온 이후의 리사이즈할 이미지 크기를 결정합니다.
                    // 이후에 이미지 크롭 어플리케이션을 호출하게 됩니다.

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
                .setTitle("업로드할 이미지 선택")
                .setPositiveButton("앨범선택", albumListener)
                .setNegativeButton("취소", cancelListener)
                .show();
    }
}