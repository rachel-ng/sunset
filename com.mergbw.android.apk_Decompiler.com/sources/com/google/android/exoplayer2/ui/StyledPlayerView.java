package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.opengl.GLSurfaceView;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.google.android.exoplayer2.DeviceInfo;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.Tracks;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.CueGroup;
import com.google.android.exoplayer2.trackselection.TrackSelectionParameters;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.StyledPlayerControlView;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ErrorMessageProvider;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoSize;
import com.google.common.collect.ImmutableList;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public class StyledPlayerView extends FrameLayout implements AdViewProvider {
    public static final int SHOW_BUFFERING_ALWAYS = 2;
    public static final int SHOW_BUFFERING_NEVER = 0;
    public static final int SHOW_BUFFERING_WHEN_PLAYING = 1;
    private static final int SURFACE_TYPE_NONE = 0;
    private static final int SURFACE_TYPE_SPHERICAL_GL_SURFACE_VIEW = 3;
    private static final int SURFACE_TYPE_SURFACE_VIEW = 1;
    private static final int SURFACE_TYPE_TEXTURE_VIEW = 2;
    private static final int SURFACE_TYPE_VIDEO_DECODER_GL_SURFACE_VIEW = 4;
    private final FrameLayout adOverlayFrameLayout;
    private final ImageView artworkView;
    private final View bufferingView;
    private final ComponentListener componentListener;
    private final AspectRatioFrameLayout contentFrame;
    private final StyledPlayerControlView controller;
    private boolean controllerAutoShow;
    /* access modifiers changed from: private */
    public boolean controllerHideDuringAds;
    private boolean controllerHideOnTouch;
    private int controllerShowTimeoutMs;
    /* access modifiers changed from: private */
    public ControllerVisibilityListener controllerVisibilityListener;
    private CharSequence customErrorMessage;
    private Drawable defaultArtwork;
    private ErrorMessageProvider<? super PlaybackException> errorMessageProvider;
    private final TextView errorMessageView;
    /* access modifiers changed from: private */
    public FullscreenButtonClickListener fullscreenButtonClickListener;
    private boolean isTouching;
    private boolean keepContentOnPlayerReset;
    private StyledPlayerControlView.VisibilityListener legacyControllerVisibilityListener;
    private final FrameLayout overlayFrameLayout;
    /* access modifiers changed from: private */
    public Player player;
    private int showBuffering;
    /* access modifiers changed from: private */
    public final View shutterView;
    /* access modifiers changed from: private */
    public final SubtitleView subtitleView;
    private final View surfaceView;
    private final boolean surfaceViewIgnoresVideoAspectRatio;
    /* access modifiers changed from: private */
    public int textureViewRotation;
    private boolean useArtwork;
    private boolean useController;

    public interface ControllerVisibilityListener {
        void onVisibilityChanged(int i);
    }

    public interface FullscreenButtonClickListener {
        void onFullscreenButtonClick(boolean z);
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ShowBuffering {
    }

    private boolean isDpadKey(int i) {
        return i == 19 || i == 270 || i == 22 || i == 271 || i == 20 || i == 269 || i == 21 || i == 268 || i == 23;
    }

    public StyledPlayerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public StyledPlayerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: finally extract failed */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StyledPlayerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        boolean z;
        boolean z2;
        int i2;
        boolean z3;
        int i3;
        boolean z4;
        int i4;
        boolean z5;
        boolean z6;
        int i5;
        int i6;
        boolean z7;
        boolean z8;
        boolean z9;
        boolean z10;
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        ComponentListener componentListener2 = new ComponentListener();
        this.componentListener = componentListener2;
        if (isInEditMode()) {
            this.contentFrame = null;
            this.shutterView = null;
            this.surfaceView = null;
            this.surfaceViewIgnoresVideoAspectRatio = false;
            this.artworkView = null;
            this.subtitleView = null;
            this.bufferingView = null;
            this.errorMessageView = null;
            this.controller = null;
            this.adOverlayFrameLayout = null;
            this.overlayFrameLayout = null;
            ImageView imageView = new ImageView(context2);
            if (Util.SDK_INT >= 23) {
                configureEditModeLogoV23(context2, getResources(), imageView);
            } else {
                configureEditModeLogo(context2, getResources(), imageView);
            }
            addView(imageView);
            return;
        }
        int i7 = R.layout.exo_styled_player_view;
        if (attributeSet2 != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet2, R.styleable.StyledPlayerView, i, 0);
            try {
                boolean hasValue = obtainStyledAttributes.hasValue(R.styleable.StyledPlayerView_shutter_background_color);
                int color = obtainStyledAttributes.getColor(R.styleable.StyledPlayerView_shutter_background_color, 0);
                int resourceId = obtainStyledAttributes.getResourceId(R.styleable.StyledPlayerView_player_layout_id, i7);
                boolean z11 = obtainStyledAttributes.getBoolean(R.styleable.StyledPlayerView_use_artwork, true);
                int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.StyledPlayerView_default_artwork, 0);
                boolean z12 = obtainStyledAttributes.getBoolean(R.styleable.StyledPlayerView_use_controller, true);
                int i8 = obtainStyledAttributes.getInt(R.styleable.StyledPlayerView_surface_type, 1);
                int i9 = obtainStyledAttributes.getInt(R.styleable.StyledPlayerView_resize_mode, 0);
                int i10 = obtainStyledAttributes.getInt(R.styleable.StyledPlayerView_show_timeout, 5000);
                boolean z13 = obtainStyledAttributes.getBoolean(R.styleable.StyledPlayerView_hide_on_touch, true);
                int i11 = resourceId;
                boolean z14 = obtainStyledAttributes.getBoolean(R.styleable.StyledPlayerView_auto_show, true);
                i5 = obtainStyledAttributes.getInteger(R.styleable.StyledPlayerView_show_buffering, 0);
                int i12 = i9;
                this.keepContentOnPlayerReset = obtainStyledAttributes.getBoolean(R.styleable.StyledPlayerView_keep_content_on_player_reset, this.keepContentOnPlayerReset);
                boolean z15 = obtainStyledAttributes.getBoolean(R.styleable.StyledPlayerView_hide_during_ads, true);
                obtainStyledAttributes.recycle();
                z5 = z13;
                z7 = z14;
                i6 = i12;
                z2 = z12;
                i2 = resourceId2;
                z3 = z11;
                i3 = color;
                z4 = hasValue;
                i4 = i8;
                boolean z16 = z15;
                i7 = i11;
                z = i10;
                z6 = z16;
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                throw th;
            }
        } else {
            z = true;
            z7 = true;
            i6 = 0;
            i5 = 0;
            z6 = true;
            z5 = true;
            i4 = 1;
            z4 = false;
            i3 = 0;
            z3 = true;
            i2 = 0;
            z2 = true;
        }
        LayoutInflater.from(context).inflate(i7, this);
        setDescendantFocusability(262144);
        AspectRatioFrameLayout aspectRatioFrameLayout = (AspectRatioFrameLayout) findViewById(R.id.exo_content_frame);
        this.contentFrame = aspectRatioFrameLayout;
        if (aspectRatioFrameLayout != null) {
            setResizeModeRaw(aspectRatioFrameLayout, i6);
        }
        View findViewById = findViewById(R.id.exo_shutter);
        this.shutterView = findViewById;
        if (findViewById != null && z4) {
            findViewById.setBackgroundColor(i3);
        }
        if (aspectRatioFrameLayout == null || i4 == 0) {
            this.surfaceView = null;
            z8 = false;
        } else {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
            if (i4 == 2) {
                this.surfaceView = new TextureView(context2);
            } else if (i4 == 3) {
                try {
                    this.surfaceView = (View) Class.forName("com.google.android.exoplayer2.video.spherical.SphericalGLSurfaceView").getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context2});
                    z10 = true;
                    this.surfaceView.setLayoutParams(layoutParams);
                    this.surfaceView.setOnClickListener(componentListener2);
                    this.surfaceView.setClickable(false);
                    aspectRatioFrameLayout.addView(this.surfaceView, 0);
                    z8 = z10;
                } catch (Exception e) {
                    throw new IllegalStateException("spherical_gl_surface_view requires an ExoPlayer dependency", e);
                }
            } else if (i4 != 4) {
                this.surfaceView = new SurfaceView(context2);
            } else {
                try {
                    this.surfaceView = (View) Class.forName("com.google.android.exoplayer2.video.VideoDecoderGLSurfaceView").getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context2});
                } catch (Exception e2) {
                    throw new IllegalStateException("video_decoder_gl_surface_view requires an ExoPlayer dependency", e2);
                }
            }
            z10 = false;
            this.surfaceView.setLayoutParams(layoutParams);
            this.surfaceView.setOnClickListener(componentListener2);
            this.surfaceView.setClickable(false);
            aspectRatioFrameLayout.addView(this.surfaceView, 0);
            z8 = z10;
        }
        this.surfaceViewIgnoresVideoAspectRatio = z8;
        this.adOverlayFrameLayout = (FrameLayout) findViewById(R.id.exo_ad_overlay);
        this.overlayFrameLayout = (FrameLayout) findViewById(R.id.exo_overlay);
        ImageView imageView2 = (ImageView) findViewById(R.id.exo_artwork);
        this.artworkView = imageView2;
        this.useArtwork = z3 && imageView2 != null;
        if (i2 != 0) {
            this.defaultArtwork = ContextCompat.getDrawable(getContext(), i2);
        }
        SubtitleView subtitleView2 = (SubtitleView) findViewById(R.id.exo_subtitles);
        this.subtitleView = subtitleView2;
        if (subtitleView2 != null) {
            subtitleView2.setUserDefaultStyle();
            subtitleView2.setUserDefaultTextSize();
        }
        View findViewById2 = findViewById(R.id.exo_buffering);
        this.bufferingView = findViewById2;
        if (findViewById2 != null) {
            findViewById2.setVisibility(8);
        }
        this.showBuffering = i5;
        TextView textView = (TextView) findViewById(R.id.exo_error_message);
        this.errorMessageView = textView;
        if (textView != null) {
            textView.setVisibility(8);
        }
        StyledPlayerControlView styledPlayerControlView = (StyledPlayerControlView) findViewById(R.id.exo_controller);
        View findViewById3 = findViewById(R.id.exo_controller_placeholder);
        if (styledPlayerControlView != null) {
            this.controller = styledPlayerControlView;
            z9 = false;
        } else if (findViewById3 != null) {
            z9 = false;
            StyledPlayerControlView styledPlayerControlView2 = new StyledPlayerControlView(context2, (AttributeSet) null, 0, attributeSet2);
            this.controller = styledPlayerControlView2;
            styledPlayerControlView2.setId(R.id.exo_controller);
            styledPlayerControlView2.setLayoutParams(findViewById3.getLayoutParams());
            ViewGroup viewGroup = (ViewGroup) findViewById3.getParent();
            int indexOfChild = viewGroup.indexOfChild(findViewById3);
            viewGroup.removeView(findViewById3);
            viewGroup.addView(styledPlayerControlView2, indexOfChild);
        } else {
            z9 = false;
            this.controller = null;
        }
        StyledPlayerControlView styledPlayerControlView3 = this.controller;
        this.controllerShowTimeoutMs = styledPlayerControlView3 != null ? z : z9 ? 1 : 0;
        this.controllerHideOnTouch = z5;
        this.controllerAutoShow = z7;
        this.controllerHideDuringAds = z6;
        this.useController = (!z2 || styledPlayerControlView3 == null) ? z9 : true;
        if (styledPlayerControlView3 != null) {
            styledPlayerControlView3.hideImmediately();
            this.controller.addVisibilityListener(componentListener2);
        }
        if (z2) {
            setClickable(true);
        }
        updateContentDescription();
    }

    public static void switchTargetView(Player player2, StyledPlayerView styledPlayerView, StyledPlayerView styledPlayerView2) {
        if (styledPlayerView != styledPlayerView2) {
            if (styledPlayerView2 != null) {
                styledPlayerView2.setPlayer(player2);
            }
            if (styledPlayerView != null) {
                styledPlayerView.setPlayer((Player) null);
            }
        }
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player2) {
        Assertions.checkState(Looper.myLooper() == Looper.getMainLooper());
        Assertions.checkArgument(player2 == null || player2.getApplicationLooper() == Looper.getMainLooper());
        Player player3 = this.player;
        if (player3 != player2) {
            if (player3 != null) {
                player3.removeListener(this.componentListener);
                if (player3.isCommandAvailable(27)) {
                    View view = this.surfaceView;
                    if (view instanceof TextureView) {
                        player3.clearVideoTextureView((TextureView) view);
                    } else if (view instanceof SurfaceView) {
                        player3.clearVideoSurfaceView((SurfaceView) view);
                    }
                }
            }
            SubtitleView subtitleView2 = this.subtitleView;
            if (subtitleView2 != null) {
                subtitleView2.setCues((List<Cue>) null);
            }
            this.player = player2;
            if (useController()) {
                this.controller.setPlayer(player2);
            }
            updateBuffering();
            updateErrorMessage();
            updateForCurrentTrackSelections(true);
            if (player2 != null) {
                if (player2.isCommandAvailable(27)) {
                    View view2 = this.surfaceView;
                    if (view2 instanceof TextureView) {
                        player2.setVideoTextureView((TextureView) view2);
                    } else if (view2 instanceof SurfaceView) {
                        player2.setVideoSurfaceView((SurfaceView) view2);
                    }
                    updateAspectRatio();
                }
                if (this.subtitleView != null && player2.isCommandAvailable(28)) {
                    this.subtitleView.setCues(player2.getCurrentCues().cues);
                }
                player2.addListener(this.componentListener);
                maybeShowController(false);
                return;
            }
            hideController();
        }
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        View view = this.surfaceView;
        if (view instanceof SurfaceView) {
            view.setVisibility(i);
        }
    }

    public void setResizeMode(int i) {
        Assertions.checkStateNotNull(this.contentFrame);
        this.contentFrame.setResizeMode(i);
    }

    public int getResizeMode() {
        Assertions.checkStateNotNull(this.contentFrame);
        return this.contentFrame.getResizeMode();
    }

    public boolean getUseArtwork() {
        return this.useArtwork;
    }

    public void setUseArtwork(boolean z) {
        Assertions.checkState(!z || this.artworkView != null);
        if (this.useArtwork != z) {
            this.useArtwork = z;
            updateForCurrentTrackSelections(false);
        }
    }

    public Drawable getDefaultArtwork() {
        return this.defaultArtwork;
    }

    public void setDefaultArtwork(Drawable drawable) {
        if (this.defaultArtwork != drawable) {
            this.defaultArtwork = drawable;
            updateForCurrentTrackSelections(false);
        }
    }

    public boolean getUseController() {
        return this.useController;
    }

    public void setUseController(boolean z) {
        boolean z2 = false;
        Assertions.checkState(!z || this.controller != null);
        if (z || hasOnClickListeners()) {
            z2 = true;
        }
        setClickable(z2);
        if (this.useController != z) {
            this.useController = z;
            if (useController()) {
                this.controller.setPlayer(this.player);
            } else {
                StyledPlayerControlView styledPlayerControlView = this.controller;
                if (styledPlayerControlView != null) {
                    styledPlayerControlView.hide();
                    this.controller.setPlayer((Player) null);
                }
            }
            updateContentDescription();
        }
    }

    public void setShutterBackgroundColor(int i) {
        View view = this.shutterView;
        if (view != null) {
            view.setBackgroundColor(i);
        }
    }

    public void setKeepContentOnPlayerReset(boolean z) {
        if (this.keepContentOnPlayerReset != z) {
            this.keepContentOnPlayerReset = z;
            updateForCurrentTrackSelections(false);
        }
    }

    public void setShowBuffering(int i) {
        if (this.showBuffering != i) {
            this.showBuffering = i;
            updateBuffering();
        }
    }

    public void setErrorMessageProvider(ErrorMessageProvider<? super PlaybackException> errorMessageProvider2) {
        if (this.errorMessageProvider != errorMessageProvider2) {
            this.errorMessageProvider = errorMessageProvider2;
            updateErrorMessage();
        }
    }

    public void setCustomErrorMessage(CharSequence charSequence) {
        Assertions.checkState(this.errorMessageView != null);
        this.customErrorMessage = charSequence;
        updateErrorMessage();
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        Player player2 = this.player;
        if (player2 != null && player2.isCommandAvailable(16) && this.player.isPlayingAd()) {
            return super.dispatchKeyEvent(keyEvent);
        }
        boolean isDpadKey = isDpadKey(keyEvent.getKeyCode());
        if (isDpadKey && useController() && !this.controller.isFullyVisible()) {
            maybeShowController(true);
            return true;
        } else if (dispatchMediaKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent)) {
            maybeShowController(true);
            return true;
        } else {
            if (isDpadKey && useController()) {
                maybeShowController(true);
            }
            return false;
        }
    }

    public boolean dispatchMediaKeyEvent(KeyEvent keyEvent) {
        return useController() && this.controller.dispatchMediaKeyEvent(keyEvent);
    }

    public boolean isControllerFullyVisible() {
        StyledPlayerControlView styledPlayerControlView = this.controller;
        return styledPlayerControlView != null && styledPlayerControlView.isFullyVisible();
    }

    public void showController() {
        showController(shouldShowControllerIndefinitely());
    }

    public void hideController() {
        StyledPlayerControlView styledPlayerControlView = this.controller;
        if (styledPlayerControlView != null) {
            styledPlayerControlView.hide();
        }
    }

    public int getControllerShowTimeoutMs() {
        return this.controllerShowTimeoutMs;
    }

    public void setControllerShowTimeoutMs(int i) {
        Assertions.checkStateNotNull(this.controller);
        this.controllerShowTimeoutMs = i;
        if (this.controller.isFullyVisible()) {
            showController();
        }
    }

    public boolean getControllerHideOnTouch() {
        return this.controllerHideOnTouch;
    }

    public void setControllerHideOnTouch(boolean z) {
        Assertions.checkStateNotNull(this.controller);
        this.controllerHideOnTouch = z;
        updateContentDescription();
    }

    public boolean getControllerAutoShow() {
        return this.controllerAutoShow;
    }

    public void setControllerAutoShow(boolean z) {
        this.controllerAutoShow = z;
    }

    public void setControllerHideDuringAds(boolean z) {
        this.controllerHideDuringAds = z;
    }

    public void setControllerVisibilityListener(ControllerVisibilityListener controllerVisibilityListener2) {
        this.controllerVisibilityListener = controllerVisibilityListener2;
        if (controllerVisibilityListener2 != null) {
            StyledPlayerControlView.VisibilityListener visibilityListener = null;
            setControllerVisibilityListener((StyledPlayerControlView.VisibilityListener) null);
        }
    }

    @Deprecated
    public void setControllerVisibilityListener(StyledPlayerControlView.VisibilityListener visibilityListener) {
        Assertions.checkStateNotNull(this.controller);
        StyledPlayerControlView.VisibilityListener visibilityListener2 = this.legacyControllerVisibilityListener;
        if (visibilityListener2 != visibilityListener) {
            if (visibilityListener2 != null) {
                this.controller.removeVisibilityListener(visibilityListener2);
            }
            this.legacyControllerVisibilityListener = visibilityListener;
            if (visibilityListener != null) {
                this.controller.addVisibilityListener(visibilityListener);
                ControllerVisibilityListener controllerVisibilityListener2 = null;
                setControllerVisibilityListener((ControllerVisibilityListener) null);
            }
        }
    }

    public void setFullscreenButtonClickListener(FullscreenButtonClickListener fullscreenButtonClickListener2) {
        Assertions.checkStateNotNull(this.controller);
        this.fullscreenButtonClickListener = fullscreenButtonClickListener2;
        this.controller.setOnFullScreenModeChangedListener(this.componentListener);
    }

    @Deprecated
    public void setControllerOnFullScreenModeChangedListener(StyledPlayerControlView.OnFullScreenModeChangedListener onFullScreenModeChangedListener) {
        Assertions.checkStateNotNull(this.controller);
        this.fullscreenButtonClickListener = null;
        this.controller.setOnFullScreenModeChangedListener(onFullScreenModeChangedListener);
    }

    public void setShowRewindButton(boolean z) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowRewindButton(z);
    }

    public void setShowFastForwardButton(boolean z) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowFastForwardButton(z);
    }

    public void setShowPreviousButton(boolean z) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowPreviousButton(z);
    }

    public void setShowNextButton(boolean z) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowNextButton(z);
    }

    public void setRepeatToggleModes(int i) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setRepeatToggleModes(i);
    }

    public void setShowShuffleButton(boolean z) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowShuffleButton(z);
    }

    public void setShowSubtitleButton(boolean z) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowSubtitleButton(z);
    }

    public void setShowVrButton(boolean z) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowVrButton(z);
    }

    public void setShowMultiWindowTimeBar(boolean z) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowMultiWindowTimeBar(z);
    }

    public void setExtraAdGroupMarkers(long[] jArr, boolean[] zArr) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setExtraAdGroupMarkers(jArr, zArr);
    }

    public void setAspectRatioListener(AspectRatioFrameLayout.AspectRatioListener aspectRatioListener) {
        Assertions.checkStateNotNull(this.contentFrame);
        this.contentFrame.setAspectRatioListener(aspectRatioListener);
    }

    public View getVideoSurfaceView() {
        return this.surfaceView;
    }

    public FrameLayout getOverlayFrameLayout() {
        return this.overlayFrameLayout;
    }

    public SubtitleView getSubtitleView() {
        return this.subtitleView;
    }

    public boolean performClick() {
        toggleControllerVisibility();
        return super.performClick();
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (!useController() || this.player == null) {
            return false;
        }
        maybeShowController(true);
        return true;
    }

    public void onResume() {
        View view = this.surfaceView;
        if (view instanceof GLSurfaceView) {
            ((GLSurfaceView) view).onResume();
        }
    }

    public void onPause() {
        View view = this.surfaceView;
        if (view instanceof GLSurfaceView) {
            ((GLSurfaceView) view).onPause();
        }
    }

    /* access modifiers changed from: protected */
    public void onContentAspectRatioChanged(AspectRatioFrameLayout aspectRatioFrameLayout, float f) {
        if (aspectRatioFrameLayout != null) {
            aspectRatioFrameLayout.setAspectRatio(f);
        }
    }

    public ViewGroup getAdViewGroup() {
        return (ViewGroup) Assertions.checkStateNotNull(this.adOverlayFrameLayout, "exo_ad_overlay must be present for ad playback");
    }

    public List<AdOverlayInfo> getAdOverlayInfos() {
        ArrayList arrayList = new ArrayList();
        if (this.overlayFrameLayout != null) {
            arrayList.add(new AdOverlayInfo(this.overlayFrameLayout, 4, "Transparent overlay does not impact viewability"));
        }
        if (this.controller != null) {
            arrayList.add(new AdOverlayInfo(this.controller, 1));
        }
        return ImmutableList.copyOf(arrayList);
    }

    @EnsuresNonNullIf(expression = {"controller"}, result = true)
    private boolean useController() {
        if (!this.useController) {
            return false;
        }
        Assertions.checkStateNotNull(this.controller);
        return true;
    }

    @EnsuresNonNullIf(expression = {"artworkView"}, result = true)
    private boolean useArtwork() {
        if (!this.useArtwork) {
            return false;
        }
        Assertions.checkStateNotNull(this.artworkView);
        return true;
    }

    /* access modifiers changed from: private */
    public void toggleControllerVisibility() {
        if (useController() && this.player != null) {
            if (!this.controller.isFullyVisible()) {
                maybeShowController(true);
            } else if (this.controllerHideOnTouch) {
                this.controller.hide();
            }
        }
    }

    private void maybeShowController(boolean z) {
        if ((!isPlayingAd() || !this.controllerHideDuringAds) && useController()) {
            boolean z2 = this.controller.isFullyVisible() && this.controller.getShowTimeoutMs() <= 0;
            boolean shouldShowControllerIndefinitely = shouldShowControllerIndefinitely();
            if (z || z2 || shouldShowControllerIndefinitely) {
                showController(shouldShowControllerIndefinitely);
            }
        }
    }

    private boolean shouldShowControllerIndefinitely() {
        Player player2 = this.player;
        if (player2 == null) {
            return true;
        }
        int playbackState = player2.getPlaybackState();
        if (!this.controllerAutoShow || ((this.player.isCommandAvailable(17) && this.player.getCurrentTimeline().isEmpty()) || (playbackState != 1 && playbackState != 4 && ((Player) Assertions.checkNotNull(this.player)).getPlayWhenReady()))) {
            return false;
        }
        return true;
    }

    private void showController(boolean z) {
        if (useController()) {
            this.controller.setShowTimeoutMs(z ? 0 : this.controllerShowTimeoutMs);
            this.controller.show();
        }
    }

    /* access modifiers changed from: private */
    public boolean isPlayingAd() {
        Player player2 = this.player;
        return player2 != null && player2.isCommandAvailable(16) && this.player.isPlayingAd() && this.player.getPlayWhenReady();
    }

    /* access modifiers changed from: private */
    public void updateForCurrentTrackSelections(boolean z) {
        Player player2 = this.player;
        if (player2 != null && player2.isCommandAvailable(30) && !player2.getCurrentTracks().isEmpty()) {
            if (z && !this.keepContentOnPlayerReset) {
                closeShutter();
            }
            if (player2.getCurrentTracks().isTypeSelected(2)) {
                hideArtwork();
                return;
            }
            closeShutter();
            if (!useArtwork() || (!setArtworkFromMediaMetadata(player2) && !setDrawableArtwork(this.defaultArtwork))) {
                hideArtwork();
            }
        } else if (!this.keepContentOnPlayerReset) {
            hideArtwork();
            closeShutter();
        }
    }

    @RequiresNonNull({"artworkView"})
    private boolean setArtworkFromMediaMetadata(Player player2) {
        if (!player2.isCommandAvailable(18)) {
            return false;
        }
        MediaMetadata mediaMetadata = player2.getMediaMetadata();
        if (mediaMetadata.artworkData == null) {
            return false;
        }
        return setDrawableArtwork(new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(mediaMetadata.artworkData, 0, mediaMetadata.artworkData.length)));
    }

    @RequiresNonNull({"artworkView"})
    private boolean setDrawableArtwork(Drawable drawable) {
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicWidth > 0 && intrinsicHeight > 0) {
                onContentAspectRatioChanged(this.contentFrame, ((float) intrinsicWidth) / ((float) intrinsicHeight));
                this.artworkView.setImageDrawable(drawable);
                this.artworkView.setVisibility(0);
                return true;
            }
        }
        return false;
    }

    private void hideArtwork() {
        ImageView imageView = this.artworkView;
        if (imageView != null) {
            imageView.setImageResource(17170445);
            this.artworkView.setVisibility(4);
        }
    }

    private void closeShutter() {
        View view = this.shutterView;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
        if (r4.player.getPlayWhenReady() == false) goto L_0x0020;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateBuffering() {
        /*
            r4 = this;
            android.view.View r0 = r4.bufferingView
            if (r0 == 0) goto L_0x002b
            com.google.android.exoplayer2.Player r0 = r4.player
            r1 = 0
            if (r0 == 0) goto L_0x0020
            int r0 = r0.getPlaybackState()
            r2 = 2
            if (r0 != r2) goto L_0x0020
            int r0 = r4.showBuffering
            r3 = 1
            if (r0 == r2) goto L_0x0021
            if (r0 != r3) goto L_0x0020
            com.google.android.exoplayer2.Player r0 = r4.player
            boolean r0 = r0.getPlayWhenReady()
            if (r0 == 0) goto L_0x0020
            goto L_0x0021
        L_0x0020:
            r3 = r1
        L_0x0021:
            android.view.View r0 = r4.bufferingView
            if (r3 == 0) goto L_0x0026
            goto L_0x0028
        L_0x0026:
            r1 = 8
        L_0x0028:
            r0.setVisibility(r1)
        L_0x002b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.StyledPlayerView.updateBuffering():void");
    }

    /* access modifiers changed from: private */
    public void updateErrorMessage() {
        ErrorMessageProvider<? super PlaybackException> errorMessageProvider2;
        TextView textView = this.errorMessageView;
        if (textView != null) {
            CharSequence charSequence = this.customErrorMessage;
            if (charSequence != null) {
                textView.setText(charSequence);
                this.errorMessageView.setVisibility(0);
                return;
            }
            Player player2 = this.player;
            PlaybackException playerError = player2 != null ? player2.getPlayerError() : null;
            if (playerError == null || (errorMessageProvider2 = this.errorMessageProvider) == null) {
                this.errorMessageView.setVisibility(8);
                return;
            }
            this.errorMessageView.setText((CharSequence) errorMessageProvider2.getErrorMessage(playerError).second);
            this.errorMessageView.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void updateContentDescription() {
        StyledPlayerControlView styledPlayerControlView = this.controller;
        String str = null;
        if (styledPlayerControlView == null || !this.useController) {
            setContentDescription((CharSequence) null);
        } else if (styledPlayerControlView.isFullyVisible()) {
            if (this.controllerHideOnTouch) {
                str = getResources().getString(R.string.exo_controls_hide);
            }
            setContentDescription(str);
        } else {
            setContentDescription(getResources().getString(R.string.exo_controls_show));
        }
    }

    /* access modifiers changed from: private */
    public void updateControllerVisibility() {
        if (!isPlayingAd() || !this.controllerHideDuringAds) {
            maybeShowController(false);
        } else {
            hideController();
        }
    }

    /* access modifiers changed from: private */
    public void updateAspectRatio() {
        Player player2 = this.player;
        VideoSize videoSize = player2 != null ? player2.getVideoSize() : VideoSize.UNKNOWN;
        int i = videoSize.width;
        int i2 = videoSize.height;
        int i3 = videoSize.unappliedRotationDegrees;
        float f = 0.0f;
        float f2 = (i2 == 0 || i == 0) ? 0.0f : (((float) i) * videoSize.pixelWidthHeightRatio) / ((float) i2);
        View view = this.surfaceView;
        if (view instanceof TextureView) {
            if (f2 > 0.0f && (i3 == 90 || i3 == 270)) {
                f2 = 1.0f / f2;
            }
            if (this.textureViewRotation != 0) {
                view.removeOnLayoutChangeListener(this.componentListener);
            }
            this.textureViewRotation = i3;
            if (i3 != 0) {
                this.surfaceView.addOnLayoutChangeListener(this.componentListener);
            }
            applyTextureViewRotation((TextureView) this.surfaceView, this.textureViewRotation);
        }
        AspectRatioFrameLayout aspectRatioFrameLayout = this.contentFrame;
        if (!this.surfaceViewIgnoresVideoAspectRatio) {
            f = f2;
        }
        onContentAspectRatioChanged(aspectRatioFrameLayout, f);
    }

    private static void configureEditModeLogoV23(Context context, Resources resources, ImageView imageView) {
        imageView.setImageDrawable(Util.getDrawable(context, resources, R.drawable.exo_edit_mode_logo));
        imageView.setBackgroundColor(resources.getColor(R.color.exo_edit_mode_background_color, (Resources.Theme) null));
    }

    private static void configureEditModeLogo(Context context, Resources resources, ImageView imageView) {
        imageView.setImageDrawable(Util.getDrawable(context, resources, R.drawable.exo_edit_mode_logo));
        imageView.setBackgroundColor(resources.getColor(R.color.exo_edit_mode_background_color));
    }

    private static void setResizeModeRaw(AspectRatioFrameLayout aspectRatioFrameLayout, int i) {
        aspectRatioFrameLayout.setResizeMode(i);
    }

    /* access modifiers changed from: private */
    public static void applyTextureViewRotation(TextureView textureView, int i) {
        Matrix matrix = new Matrix();
        float width = (float) textureView.getWidth();
        float height = (float) textureView.getHeight();
        if (!(width == 0.0f || height == 0.0f || i == 0)) {
            float f = width / 2.0f;
            float f2 = height / 2.0f;
            matrix.postRotate((float) i, f, f2);
            RectF rectF = new RectF(0.0f, 0.0f, width, height);
            RectF rectF2 = new RectF();
            matrix.mapRect(rectF2, rectF);
            matrix.postScale(width / rectF2.width(), height / rectF2.height(), f, f2);
        }
        textureView.setTransform(matrix);
    }

    private final class ComponentListener implements Player.Listener, View.OnLayoutChangeListener, View.OnClickListener, StyledPlayerControlView.VisibilityListener, StyledPlayerControlView.OnFullScreenModeChangedListener {
        private Object lastPeriodUidWithTracks;
        private final Timeline.Period period = new Timeline.Period();

        public /* synthetic */ void onAudioAttributesChanged(AudioAttributes audioAttributes) {
            Player.Listener.CC.$default$onAudioAttributesChanged(this, audioAttributes);
        }

        public /* synthetic */ void onAudioSessionIdChanged(int i) {
            Player.Listener.CC.$default$onAudioSessionIdChanged(this, i);
        }

        public /* synthetic */ void onAvailableCommandsChanged(Player.Commands commands) {
            Player.Listener.CC.$default$onAvailableCommandsChanged(this, commands);
        }

        public /* synthetic */ void onCues(List list) {
            Player.Listener.CC.$default$onCues((Player.Listener) this, list);
        }

        public /* synthetic */ void onDeviceInfoChanged(DeviceInfo deviceInfo) {
            Player.Listener.CC.$default$onDeviceInfoChanged(this, deviceInfo);
        }

        public /* synthetic */ void onDeviceVolumeChanged(int i, boolean z) {
            Player.Listener.CC.$default$onDeviceVolumeChanged(this, i, z);
        }

        public /* synthetic */ void onEvents(Player player, Player.Events events) {
            Player.Listener.CC.$default$onEvents(this, player, events);
        }

        public /* synthetic */ void onIsLoadingChanged(boolean z) {
            Player.Listener.CC.$default$onIsLoadingChanged(this, z);
        }

        public /* synthetic */ void onIsPlayingChanged(boolean z) {
            Player.Listener.CC.$default$onIsPlayingChanged(this, z);
        }

        public /* synthetic */ void onLoadingChanged(boolean z) {
            Player.Listener.CC.$default$onLoadingChanged(this, z);
        }

        public /* synthetic */ void onMaxSeekToPreviousPositionChanged(long j) {
            Player.Listener.CC.$default$onMaxSeekToPreviousPositionChanged(this, j);
        }

        public /* synthetic */ void onMediaItemTransition(MediaItem mediaItem, int i) {
            Player.Listener.CC.$default$onMediaItemTransition(this, mediaItem, i);
        }

        public /* synthetic */ void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
            Player.Listener.CC.$default$onMediaMetadataChanged(this, mediaMetadata);
        }

        public /* synthetic */ void onMetadata(Metadata metadata) {
            Player.Listener.CC.$default$onMetadata(this, metadata);
        }

        public /* synthetic */ void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            Player.Listener.CC.$default$onPlaybackParametersChanged(this, playbackParameters);
        }

        public /* synthetic */ void onPlaybackSuppressionReasonChanged(int i) {
            Player.Listener.CC.$default$onPlaybackSuppressionReasonChanged(this, i);
        }

        public /* synthetic */ void onPlayerError(PlaybackException playbackException) {
            Player.Listener.CC.$default$onPlayerError(this, playbackException);
        }

        public /* synthetic */ void onPlayerErrorChanged(PlaybackException playbackException) {
            Player.Listener.CC.$default$onPlayerErrorChanged(this, playbackException);
        }

        public /* synthetic */ void onPlayerStateChanged(boolean z, int i) {
            Player.Listener.CC.$default$onPlayerStateChanged(this, z, i);
        }

        public /* synthetic */ void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
            Player.Listener.CC.$default$onPlaylistMetadataChanged(this, mediaMetadata);
        }

        public /* synthetic */ void onPositionDiscontinuity(int i) {
            Player.Listener.CC.$default$onPositionDiscontinuity(this, i);
        }

        public /* synthetic */ void onRepeatModeChanged(int i) {
            Player.Listener.CC.$default$onRepeatModeChanged(this, i);
        }

        public /* synthetic */ void onSeekBackIncrementChanged(long j) {
            Player.Listener.CC.$default$onSeekBackIncrementChanged(this, j);
        }

        public /* synthetic */ void onSeekForwardIncrementChanged(long j) {
            Player.Listener.CC.$default$onSeekForwardIncrementChanged(this, j);
        }

        public /* synthetic */ void onSeekProcessed() {
            Player.Listener.CC.$default$onSeekProcessed(this);
        }

        public /* synthetic */ void onShuffleModeEnabledChanged(boolean z) {
            Player.Listener.CC.$default$onShuffleModeEnabledChanged(this, z);
        }

        public /* synthetic */ void onSkipSilenceEnabledChanged(boolean z) {
            Player.Listener.CC.$default$onSkipSilenceEnabledChanged(this, z);
        }

        public /* synthetic */ void onSurfaceSizeChanged(int i, int i2) {
            Player.Listener.CC.$default$onSurfaceSizeChanged(this, i, i2);
        }

        public /* synthetic */ void onTimelineChanged(Timeline timeline, int i) {
            Player.Listener.CC.$default$onTimelineChanged(this, timeline, i);
        }

        public /* synthetic */ void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
            Player.Listener.CC.$default$onTrackSelectionParametersChanged(this, trackSelectionParameters);
        }

        public /* synthetic */ void onVolumeChanged(float f) {
            Player.Listener.CC.$default$onVolumeChanged(this, f);
        }

        public ComponentListener() {
        }

        public void onCues(CueGroup cueGroup) {
            if (StyledPlayerView.this.subtitleView != null) {
                StyledPlayerView.this.subtitleView.setCues(cueGroup.cues);
            }
        }

        public void onVideoSizeChanged(VideoSize videoSize) {
            StyledPlayerView.this.updateAspectRatio();
        }

        public void onRenderedFirstFrame() {
            if (StyledPlayerView.this.shutterView != null) {
                StyledPlayerView.this.shutterView.setVisibility(4);
            }
        }

        public void onTracksChanged(Tracks tracks) {
            Timeline timeline;
            Player player = (Player) Assertions.checkNotNull(StyledPlayerView.this.player);
            if (player.isCommandAvailable(17)) {
                timeline = player.getCurrentTimeline();
            } else {
                timeline = Timeline.EMPTY;
            }
            if (timeline.isEmpty()) {
                this.lastPeriodUidWithTracks = null;
            } else if (!player.isCommandAvailable(30) || player.getCurrentTracks().isEmpty()) {
                Object obj = this.lastPeriodUidWithTracks;
                if (obj != null) {
                    int indexOfPeriod = timeline.getIndexOfPeriod(obj);
                    if (indexOfPeriod == -1 || player.getCurrentMediaItemIndex() != timeline.getPeriod(indexOfPeriod, this.period).windowIndex) {
                        this.lastPeriodUidWithTracks = null;
                    } else {
                        return;
                    }
                }
            } else {
                this.lastPeriodUidWithTracks = timeline.getPeriod(player.getCurrentPeriodIndex(), this.period, true).uid;
            }
            StyledPlayerView.this.updateForCurrentTrackSelections(false);
        }

        public void onPlaybackStateChanged(int i) {
            StyledPlayerView.this.updateBuffering();
            StyledPlayerView.this.updateErrorMessage();
            StyledPlayerView.this.updateControllerVisibility();
        }

        public void onPlayWhenReadyChanged(boolean z, int i) {
            StyledPlayerView.this.updateBuffering();
            StyledPlayerView.this.updateControllerVisibility();
        }

        public void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
            if (StyledPlayerView.this.isPlayingAd() && StyledPlayerView.this.controllerHideDuringAds) {
                StyledPlayerView.this.hideController();
            }
        }

        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            StyledPlayerView.applyTextureViewRotation((TextureView) view, StyledPlayerView.this.textureViewRotation);
        }

        public void onClick(View view) {
            StyledPlayerView.this.toggleControllerVisibility();
        }

        public void onVisibilityChange(int i) {
            StyledPlayerView.this.updateContentDescription();
            if (StyledPlayerView.this.controllerVisibilityListener != null) {
                StyledPlayerView.this.controllerVisibilityListener.onVisibilityChanged(i);
            }
        }

        public void onFullScreenModeChanged(boolean z) {
            if (StyledPlayerView.this.fullscreenButtonClickListener != null) {
                StyledPlayerView.this.fullscreenButtonClickListener.onFullscreenButtonClick(z);
            }
        }
    }
}
