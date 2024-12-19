package com.mergbw.core.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AssetsCopyer {
    public static void releaseAssets(Context context) {
        releaseUpdateFile(context);
    }

    private static void releaseUpdateFile(Context context) {
        String str = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/firmware";
        AssetManager assets = context.getAssets();
        try {
            for (String str2 : assets.list("firmware")) {
                writeFile(str2, str, assets.open("firmware" + RemoteSettings.FORWARD_SLASH_STRING + str2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean writeFile(String str, String str2, InputStream inputStream) throws IOException {
        try {
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(str2 + RemoteSettings.FORWARD_SLASH_STRING + str);
            if (file2.exists()) {
                file2.delete();
            }
            file2.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file2, true);
            byte[] bArr = new byte[400000];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    inputStream.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
