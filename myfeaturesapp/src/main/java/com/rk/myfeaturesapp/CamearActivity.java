package com.rk.myfeaturesapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.rk.myfeaturesapp.base.BaseActivity;
import com.rk.myfeaturesapp.util.CustomTitleBar;
import com.rk.myfeaturesapp.util.getPhotoFromPhotoAlbum;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static android.provider.MediaStore.ACTION_VIDEO_CAPTURE;

public class CamearActivity extends BaseActivity implements View.OnClickListener {
    private static final int REQUEST_TAKE_PHOTO_CODE = 123;
    private Button bucamear;
    private Button open;
    private CustomTitleBar customTitleBar;
    private static final int REQUEST_PERMISSION_CODE = 101;
    private ImageView imageView;
    private Uri mUri;
    private static final int REQUEST_PHOTO_CODE = 200;
    private Button openCamera;
    private String imagePaths;
    private Uri cameraUri;
    private static final int ACTION_VIDEO_CAPTURES = 150;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        imageView = findViewById(R.id.imagecamear);
        customTitleBar = findViewById(R.id.camearcustom);
        openCamera = findViewById(R.id.openCamera);
        open = findViewById(R.id.open);
        customTitleBar.setTitle("拍照");
        customTitleBar.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bucamear = findViewById(R.id.camear);
        open.setOnClickListener(this);
        bucamear.setOnClickListener(this);
        openCamera.setOnClickListener(this);
    }

    @Override
    protected int findView() {
        return R.layout.activity_camear;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.camear:
                if (!checkPermission()) { //没有或没有全部授权
                    requestPermissions(); //请求权限
                }
             else {
                takePhoto();//拍照逻辑
            }
                break;
            case R.id.open:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_PHOTO_CODE);
                break;
            case R.id.openCamera:
                openCamera();
                break;
        }
    }

    private void openCamera() {
        try {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//            intent.setAction("android.media.action.VIDEO_CAPTURE");
            intent.addCategory("android.intent.category.DEFAULT");
            // 保存录像到指定的路径
            imagePaths = Environment.getExternalStorageDirectory().getPath()
                    + "/temp/" + (System.currentTimeMillis() + ".mp4");
            // 必须确保文件夹路径存在，否则拍照后无法完成回调
            File vFile = new File(imagePaths);
            if (!vFile.exists()) {
                File vDirPath = vFile.getParentFile();
                vDirPath.mkdirs();
            } else {
                if (vFile.exists()) {
                    vFile.delete();
                }
            }
            cameraUri = getUriForFile(CamearActivity.this, "com.rk.myfeaturesapp.fileprovider", vFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                List<ResolveInfo> resInfoList = this.getPackageManager()
                        .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo resolveInfo : resInfoList) {
                    String packageName = resolveInfo.activityInfo.packageName;
                    this.grantUriPermission(packageName, cameraUri,
                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
            }
            Log.i("TAG","哦呵呵");
            this.startActivityForResult(intent, ACTION_VIDEO_CAPTURES);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("TAG",e.toString()+"哦呵呵");
        }
    }
    private static Uri getUriForFile(Context context, String name, File vFile) {
        Uri cameraUri;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            cameraUri = Uri.fromFile(vFile);
        } else {
            cameraUri = FileProvider.getUriForFile(context, name, vFile);
        }
        return cameraUri;
    }
    //检查权限
    private boolean checkPermission() {
        //是否有权限
        boolean haveCameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;

        boolean haveWritePermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        return haveCameraPermission && haveWritePermission;

    }

    // 请求所需权限
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestPermissions() {
        requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE:
                boolean allowAllPermission = false;
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {//被拒绝授权
                        allowAllPermission = false;
                        break;
                    }
                    allowAllPermission = true;
                }
                if (allowAllPermission) {
                    takePhoto();//开始拍照或从相册选取照片
                } else {
                    Toast.makeText(this, "该功能需要授权方可使用", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    private void takePhotoOrPickPhoto() {

    }

    private void takePhoto() {
        // 步骤一：创建存储照片的文件
        String path = getFilesDir() + File.separator + "images" + File.separator;
        File file = new File(path, "test.jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //步骤二：Android 7.0及以上获取文件 Uri
            mUri = FileProvider.getUriForFile(CamearActivity.this, "com.rk.myfeaturesapp.fileprovider", file);
        } else {
            //步骤三：获取文件Uri
            mUri = Uri.fromFile(file);
        }
        //步骤四：调取系统拍照
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
        startActivityForResult(intent,REQUEST_TAKE_PHOTO_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_TAKE_PHOTO_CODE) {//获取系统照片上传

            Bitmap bm = null;
            try {
                bm = getBitmapFormUri(mUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            imageView.setImageBitmap(bm);
        }
        if (resultCode == RESULT_OK && requestCode == REQUEST_PHOTO_CODE) {//获取系统照片上传
            String photoPath = getPhotoFromPhotoAlbum.getRealPathFromUri(this, data.getData());
            Log.i("TAG",photoPath+"====");

            Glide.with(this).load(photoPath).into(imageView)
            ;

            //https://blog.csdn.net/bzlj2912009596/article/details/81702367
        }
        if (resultCode == RESULT_OK && requestCode == ACTION_VIDEO_CAPTURES) {//获取系统照片上传
            Uri uri=data.getData();
            Log.i("TAG", "直接返回视频数据"+uri.getPath());
            //https://blog.csdn.net/luanpeng825485697/article/details/78543467

        }
    }

    public Bitmap getBitmapFormUri(Uri uri) throws FileNotFoundException, IOException {
        InputStream input = getContentResolver().openInputStream(uri);

        //这一段代码是不加载文件到内存中也得到bitmap的真是宽高，主要是设置inJustDecodeBounds为true
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;//不加载到内存
        onlyBoundsOptions.inDither = true;//optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.RGB_565;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        int originalWidth = onlyBoundsOptions.outWidth;
        int originalHeight = onlyBoundsOptions.outHeight;
        if ((originalWidth == -1) || (originalHeight == -1))
            return null;

        //图片分辨率以480x800为标准
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比，由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (originalWidth > originalHeight && originalWidth > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (originalWidth / ww);
        } else if (originalWidth < originalHeight && originalHeight > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (originalHeight / hh);
        }
        if (be <= 0)
            be = 1;
        //比例压缩
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = be;//设置缩放比例
        bitmapOptions.inDither = true;
        bitmapOptions.inPreferredConfig = Bitmap.Config.RGB_565;
        input = getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();

        return compressImage(bitmap);//再进行质量压缩
    }

    public Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            //第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差  ，第三个参数：保存压缩后的数据的流
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
            if (options<=0)
                break;
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }
}
