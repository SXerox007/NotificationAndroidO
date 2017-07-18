package com.black_dreams.notification.notificationandroido;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Surface;
import android.view.TextureView;
import android.widget.FrameLayout;

import java.io.IOException;

public class CameraOpenLandscape extends Activity implements TextureView.SurfaceTextureListener {

    private Camera mCamera;
    private TextureView mTextureView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTextureView = new TextureView(this);
        mTextureView.setSurfaceTextureListener(this);

        setContentView(mTextureView);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {

        int cameraId = 0;
        Camera.CameraInfo info = new Camera.CameraInfo();

        for (cameraId = 0; cameraId < Camera.getNumberOfCameras(); cameraId++) {
            Camera.getCameraInfo(1, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT)
                break;
        }

        mCamera = Camera.open(cameraId);
        Matrix transform = new Matrix();

        Camera.Size previewSize = mCamera.getParameters().getPreviewSize();
        int rotation = getWindowManager().getDefaultDisplay()
                .getRotation();

        //  Log.i("onSurfaceTextureAvailable", " CameraOrientation(" + cameraId + ")" + info.orientation + " " + previewSize.width + "x" + previewSize.height + " Rotation=" + rotation);

        switch (rotation) {
            case Surface.ROTATION_0:
                mCamera.setDisplayOrientation(90);
                mTextureView.setLayoutParams(new FrameLayout.LayoutParams(
                        previewSize.height, previewSize.width, Gravity.CENTER));
                transform.setScale(-1, 1, previewSize.height / 2, 0);
                break;

            case Surface.ROTATION_90:
                mCamera.setDisplayOrientation(0);
                mTextureView.setLayoutParams(new FrameLayout.LayoutParams(
                        previewSize.width, previewSize.height, Gravity.CENTER));
                transform.setScale(-1, 1, previewSize.width / 2, 0);
                break;

            case Surface.ROTATION_180:
                mCamera.setDisplayOrientation(270);
                mTextureView.setLayoutParams(new FrameLayout.LayoutParams(
                        previewSize.height, previewSize.width, Gravity.CENTER));
                transform.setScale(-1, 1, previewSize.height / 2, 0);
                break;

            case Surface.ROTATION_270:
                mCamera.setDisplayOrientation(180);
                mTextureView.setLayoutParams(new FrameLayout.LayoutParams(
                        previewSize.width, previewSize.height, Gravity.CENTER));
                transform.setScale(-1, 1, previewSize.width / 2, 0);
                break;
        }


        try {
            mCamera.setPreviewTexture(surface);
        } catch (IOException t) {
        }

        mTextureView.setTransform(transform);
        // Log.i("onSurfaceTextureAvailable", "Transform: " + transform.toString());

        mCamera.startPreview();

    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        // Ignored, the Camera does all the work for us
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        mCamera.stopPreview();
        mCamera.release();
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        // Update your view here!
    }
}