package com.example.edz.myapplication.camera.camera2;

import android.graphics.Rect;

public interface CaptureListener2 {
    void takePictures();

    void recordShort(long time);

    void recordStart();

    void recordEnd(long time);

    void recordZoom(Rect zoom);

    void recordError();
}
