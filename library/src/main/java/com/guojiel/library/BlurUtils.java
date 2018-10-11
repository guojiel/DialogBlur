package com.guojiel.library;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

public final class BlurUtils {

    private static RenderScript renderScript;
    private static ScriptIntrinsicBlur scriptIntrinsicBlur;

    public static Bitmap blur(Context context, Bitmap source, int radius, float scale){
        int width = Math.round(source.getWidth() * scale);
        int height = Math.round(source.getHeight() * scale);
        Bitmap inputBmp = Bitmap.createScaledBitmap(source,width,height,true);
        if(renderScript == null) {
            renderScript = RenderScript.create(context.getApplicationContext());
        }
        // Allocate memory for Renderscript to work with
        final Allocation input = Allocation.createFromBitmap(renderScript,inputBmp);
        final Allocation output = Allocation.createTyped(renderScript,input.getType());
        // Load up an instance of the specific script that we want to use.
        if(scriptIntrinsicBlur == null) {
            scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        }
        scriptIntrinsicBlur.setInput(input);
        // Set the blur radius
        scriptIntrinsicBlur.setRadius(radius);
        // Start the ScriptIntrinisicBlur
        scriptIntrinsicBlur.forEach(output);
        // Copy the output to the blurred bitmap
        output.copyTo(inputBmp);
        renderScript.destroy();
        return inputBmp;
    }

}
