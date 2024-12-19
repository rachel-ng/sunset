package com.google.android.exoplayer2.ui;

import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.PlayerNotificationManager;

public final class DefaultMediaDescriptionAdapter implements PlayerNotificationManager.MediaDescriptionAdapter {
    private final PendingIntent pendingIntent;

    public /* synthetic */ CharSequence getCurrentSubText(Player player) {
        return PlayerNotificationManager.MediaDescriptionAdapter.CC.$default$getCurrentSubText(this, player);
    }

    public DefaultMediaDescriptionAdapter(PendingIntent pendingIntent2) {
        this.pendingIntent = pendingIntent2;
    }

    public CharSequence getCurrentContentTitle(Player player) {
        if (!player.isCommandAvailable(18)) {
            return "";
        }
        CharSequence charSequence = player.getMediaMetadata().displayTitle;
        if (!TextUtils.isEmpty(charSequence)) {
            return charSequence;
        }
        CharSequence charSequence2 = player.getMediaMetadata().title;
        if (charSequence2 != null) {
            return charSequence2;
        }
        return "";
    }

    public PendingIntent createCurrentContentIntent(Player player) {
        return this.pendingIntent;
    }

    public CharSequence getCurrentContentText(Player player) {
        if (!player.isCommandAvailable(18)) {
            return null;
        }
        CharSequence charSequence = player.getMediaMetadata().artist;
        if (!TextUtils.isEmpty(charSequence)) {
            return charSequence;
        }
        return player.getMediaMetadata().albumArtist;
    }

    public Bitmap getCurrentLargeIcon(Player player, PlayerNotificationManager.BitmapCallback bitmapCallback) {
        byte[] bArr;
        if (player.isCommandAvailable(18) && (bArr = player.getMediaMetadata().artworkData) != null) {
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }
        return null;
    }
}
