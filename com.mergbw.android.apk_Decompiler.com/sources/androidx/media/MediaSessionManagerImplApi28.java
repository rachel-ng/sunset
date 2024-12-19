package androidx.media;

import android.content.Context;
import android.media.session.MediaSessionManager;
import androidx.media.MediaSessionManager;
import androidx.media.MediaSessionManagerImplBase;

class MediaSessionManagerImplApi28 extends MediaSessionManagerImplApi21 {
    MediaSessionManager mObject;

    MediaSessionManagerImplApi28(Context context) {
        super(context);
        this.mObject = (MediaSessionManager) context.getSystemService("media_session");
    }

    public boolean isTrustedForMediaControl(MediaSessionManager.RemoteUserInfoImpl remoteUserInfoImpl) {
        return super.isTrustedForMediaControl(remoteUserInfoImpl);
    }

    static final class RemoteUserInfoImplApi28 extends MediaSessionManagerImplBase.RemoteUserInfoImplBase {
        final MediaSessionManager.RemoteUserInfo mObject;

        RemoteUserInfoImplApi28(String str, int i, int i2) {
            super(str, i, i2);
            this.mObject = AudioFocusRequestCompat$$ExternalSyntheticApiModelOutline0.m(str, i, i2);
        }

        RemoteUserInfoImplApi28(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            super(AudioFocusRequestCompat$$ExternalSyntheticApiModelOutline0.m(remoteUserInfo), AudioFocusRequestCompat$$ExternalSyntheticApiModelOutline0.m(remoteUserInfo), remoteUserInfo.getUid());
            this.mObject = remoteUserInfo;
        }

        static String getPackageName(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            return AudioFocusRequestCompat$$ExternalSyntheticApiModelOutline0.m(remoteUserInfo);
        }
    }
}
