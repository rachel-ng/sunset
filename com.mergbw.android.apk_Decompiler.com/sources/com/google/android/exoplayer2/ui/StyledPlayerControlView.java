package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DeviceInfo;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.Tracks;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.text.CueGroup;
import com.google.android.exoplayer2.trackselection.TrackSelectionOverride;
import com.google.android.exoplayer2.trackselection.TrackSelectionParameters;
import com.google.android.exoplayer2.ui.TimeBar;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.RepeatModeUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoSize;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

public class StyledPlayerControlView extends FrameLayout {
    public static final int DEFAULT_REPEAT_TOGGLE_MODES = 0;
    public static final int DEFAULT_SHOW_TIMEOUT_MS = 5000;
    public static final int DEFAULT_TIME_BAR_MIN_UPDATE_INTERVAL_MS = 200;
    private static final int MAX_UPDATE_INTERVAL_MS = 1000;
    public static final int MAX_WINDOWS_FOR_MULTI_WINDOW_TIME_BAR = 100;
    private static final float[] PLAYBACK_SPEEDS = {0.25f, 0.5f, 0.75f, 1.0f, 1.25f, 1.5f, 2.0f};
    private static final int SETTINGS_AUDIO_TRACK_SELECTION_POSITION = 1;
    private static final int SETTINGS_PLAYBACK_SPEED_POSITION = 0;
    private long[] adGroupTimesMs;
    /* access modifiers changed from: private */
    public final View audioTrackButton;
    /* access modifiers changed from: private */
    public final AudioTrackSelectionAdapter audioTrackSelectionAdapter;
    private final float buttonAlphaDisabled;
    private final float buttonAlphaEnabled;
    private final ComponentListener componentListener;
    /* access modifiers changed from: private */
    public final StyledPlayerControlViewLayoutManager controlViewLayoutManager;
    private long currentWindowOffset;
    private final TextView durationView;
    private long[] extraAdGroupTimesMs;
    private boolean[] extraPlayedAdGroups;
    /* access modifiers changed from: private */
    public final View fastForwardButton;
    private final TextView fastForwardButtonTextView;
    /* access modifiers changed from: private */
    public final StringBuilder formatBuilder;
    /* access modifiers changed from: private */
    public final Formatter formatter;
    private final ImageView fullScreenButton;
    private final String fullScreenEnterContentDescription;
    private final Drawable fullScreenEnterDrawable;
    private final String fullScreenExitContentDescription;
    private final Drawable fullScreenExitDrawable;
    private boolean isAttachedToWindow;
    private boolean isFullScreen;
    private final ImageView minimalFullScreenButton;
    private boolean multiWindowTimeBar;
    /* access modifiers changed from: private */
    public boolean needToHideBars;
    /* access modifiers changed from: private */
    public final View nextButton;
    private OnFullScreenModeChangedListener onFullScreenModeChangedListener;
    private final Timeline.Period period;
    /* access modifiers changed from: private */
    public final View playPauseButton;
    /* access modifiers changed from: private */
    public final PlaybackSpeedAdapter playbackSpeedAdapter;
    /* access modifiers changed from: private */
    public final View playbackSpeedButton;
    private boolean[] playedAdGroups;
    /* access modifiers changed from: private */
    public Player player;
    /* access modifiers changed from: private */
    public final TextView positionView;
    /* access modifiers changed from: private */
    public final View previousButton;
    private ProgressUpdateListener progressUpdateListener;
    private final String repeatAllButtonContentDescription;
    private final Drawable repeatAllButtonDrawable;
    private final String repeatOffButtonContentDescription;
    private final Drawable repeatOffButtonDrawable;
    private final String repeatOneButtonContentDescription;
    private final Drawable repeatOneButtonDrawable;
    /* access modifiers changed from: private */
    public final ImageView repeatToggleButton;
    /* access modifiers changed from: private */
    public int repeatToggleModes;
    private final Resources resources;
    /* access modifiers changed from: private */
    public final View rewindButton;
    private final TextView rewindButtonTextView;
    /* access modifiers changed from: private */
    public boolean scrubbing;
    /* access modifiers changed from: private */
    public final SettingsAdapter settingsAdapter;
    /* access modifiers changed from: private */
    public final View settingsButton;
    private final RecyclerView settingsView;
    /* access modifiers changed from: private */
    public final PopupWindow settingsWindow;
    private final int settingsWindowMargin;
    private boolean showMultiWindowTimeBar;
    private int showTimeoutMs;
    /* access modifiers changed from: private */
    public final ImageView shuffleButton;
    private final Drawable shuffleOffButtonDrawable;
    private final String shuffleOffContentDescription;
    private final Drawable shuffleOnButtonDrawable;
    private final String shuffleOnContentDescription;
    /* access modifiers changed from: private */
    public final ImageView subtitleButton;
    /* access modifiers changed from: private */
    public final Drawable subtitleOffButtonDrawable;
    /* access modifiers changed from: private */
    public final String subtitleOffContentDescription;
    /* access modifiers changed from: private */
    public final Drawable subtitleOnButtonDrawable;
    /* access modifiers changed from: private */
    public final String subtitleOnContentDescription;
    /* access modifiers changed from: private */
    public final TextTrackSelectionAdapter textTrackSelectionAdapter;
    private final TimeBar timeBar;
    private int timeBarMinUpdateIntervalMs;
    private final TrackNameProvider trackNameProvider;
    private final Runnable updateProgressAction;
    private final CopyOnWriteArrayList<VisibilityListener> visibilityListeners;
    private final View vrButton;
    private final Timeline.Window window;

    @Deprecated
    public interface OnFullScreenModeChangedListener {
        void onFullScreenModeChanged(boolean z);
    }

    public interface ProgressUpdateListener {
        void onProgressUpdate(long j, long j2);
    }

    @Deprecated
    public interface VisibilityListener {
        void onVisibilityChange(int i);
    }

    private static boolean isHandledMediaKey(int i) {
        return i == 90 || i == 89 || i == 85 || i == 79 || i == 126 || i == 127 || i == 87 || i == 88;
    }

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.ui");
    }

    public StyledPlayerControlView(Context context) {
        this(context, (AttributeSet) null);
    }

    public StyledPlayerControlView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StyledPlayerControlView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, attributeSet);
    }

    /* JADX INFO: finally extract failed */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StyledPlayerControlView(Context context, AttributeSet attributeSet, int i, AttributeSet attributeSet2) {
        super(context, attributeSet, i);
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        boolean z8;
        boolean z9;
        boolean z10;
        ComponentListener componentListener2;
        TextView textView;
        boolean z11;
        Context context2 = context;
        AttributeSet attributeSet3 = attributeSet2;
        int i2 = R.layout.exo_styled_player_control_view;
        this.showTimeoutMs = 5000;
        this.repeatToggleModes = 0;
        this.timeBarMinUpdateIntervalMs = 200;
        if (attributeSet3 != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet3, R.styleable.StyledPlayerControlView, i, 0);
            try {
                i2 = obtainStyledAttributes.getResourceId(R.styleable.StyledPlayerControlView_controller_layout_id, i2);
                this.showTimeoutMs = obtainStyledAttributes.getInt(R.styleable.StyledPlayerControlView_show_timeout, this.showTimeoutMs);
                this.repeatToggleModes = getRepeatToggleModes(obtainStyledAttributes, this.repeatToggleModes);
                boolean z12 = obtainStyledAttributes.getBoolean(R.styleable.StyledPlayerControlView_show_rewind_button, true);
                boolean z13 = obtainStyledAttributes.getBoolean(R.styleable.StyledPlayerControlView_show_fastforward_button, true);
                boolean z14 = obtainStyledAttributes.getBoolean(R.styleable.StyledPlayerControlView_show_previous_button, true);
                boolean z15 = obtainStyledAttributes.getBoolean(R.styleable.StyledPlayerControlView_show_next_button, true);
                boolean z16 = obtainStyledAttributes.getBoolean(R.styleable.StyledPlayerControlView_show_shuffle_button, false);
                boolean z17 = obtainStyledAttributes.getBoolean(R.styleable.StyledPlayerControlView_show_subtitle_button, false);
                boolean z18 = obtainStyledAttributes.getBoolean(R.styleable.StyledPlayerControlView_show_vr_button, false);
                setTimeBarMinUpdateInterval(obtainStyledAttributes.getInt(R.styleable.StyledPlayerControlView_time_bar_min_update_interval, this.timeBarMinUpdateIntervalMs));
                boolean z19 = obtainStyledAttributes.getBoolean(R.styleable.StyledPlayerControlView_animation_enabled, true);
                obtainStyledAttributes.recycle();
                z = z17;
                z4 = z14;
                z7 = z18;
                z3 = z15;
                z6 = z12;
                boolean z20 = z16;
                z5 = z13;
                z8 = z19;
                z2 = z20;
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                throw th;
            }
        } else {
            z7 = false;
            z2 = false;
            z = false;
            z8 = true;
            z6 = true;
            z5 = true;
            z4 = true;
            z3 = true;
        }
        LayoutInflater.from(context).inflate(i2, this);
        setDescendantFocusability(262144);
        ComponentListener componentListener3 = new ComponentListener();
        this.componentListener = componentListener3;
        this.visibilityListeners = new CopyOnWriteArrayList<>();
        this.period = new Timeline.Period();
        this.window = new Timeline.Window();
        StringBuilder sb = new StringBuilder();
        this.formatBuilder = sb;
        this.formatter = new Formatter(sb, Locale.getDefault());
        this.adGroupTimesMs = new long[0];
        this.playedAdGroups = new boolean[0];
        this.extraAdGroupTimesMs = new long[0];
        this.extraPlayedAdGroups = new boolean[0];
        this.updateProgressAction = new StyledPlayerControlView$$ExternalSyntheticLambda0(this);
        this.durationView = (TextView) findViewById(R.id.exo_duration);
        this.positionView = (TextView) findViewById(R.id.exo_position);
        ImageView imageView = (ImageView) findViewById(R.id.exo_subtitle);
        this.subtitleButton = imageView;
        if (imageView != null) {
            imageView.setOnClickListener(componentListener3);
        }
        ImageView imageView2 = (ImageView) findViewById(R.id.exo_fullscreen);
        this.fullScreenButton = imageView2;
        initializeFullScreenButton(imageView2, new StyledPlayerControlView$$ExternalSyntheticLambda1(this));
        ImageView imageView3 = (ImageView) findViewById(R.id.exo_minimal_fullscreen);
        this.minimalFullScreenButton = imageView3;
        initializeFullScreenButton(imageView3, new StyledPlayerControlView$$ExternalSyntheticLambda1(this));
        View findViewById = findViewById(R.id.exo_settings);
        this.settingsButton = findViewById;
        if (findViewById != null) {
            findViewById.setOnClickListener(componentListener3);
        }
        View findViewById2 = findViewById(R.id.exo_playback_speed);
        this.playbackSpeedButton = findViewById2;
        if (findViewById2 != null) {
            findViewById2.setOnClickListener(componentListener3);
        }
        View findViewById3 = findViewById(R.id.exo_audio_track);
        this.audioTrackButton = findViewById3;
        if (findViewById3 != null) {
            findViewById3.setOnClickListener(componentListener3);
        }
        TimeBar timeBar2 = (TimeBar) findViewById(R.id.exo_progress);
        View findViewById4 = findViewById(R.id.exo_progress_placeholder);
        if (timeBar2 != null) {
            this.timeBar = timeBar2;
            componentListener2 = componentListener3;
            z10 = z8;
            z9 = z7;
            textView = null;
        } else if (findViewById4 != null) {
            DefaultTimeBar defaultTimeBar = r2;
            View view = findViewById4;
            textView = null;
            componentListener2 = componentListener3;
            z10 = z8;
            z9 = z7;
            DefaultTimeBar defaultTimeBar2 = new DefaultTimeBar(context, (AttributeSet) null, 0, attributeSet2, R.style.ExoStyledControls_TimeBar);
            DefaultTimeBar defaultTimeBar3 = defaultTimeBar;
            defaultTimeBar3.setId(R.id.exo_progress);
            defaultTimeBar3.setLayoutParams(view.getLayoutParams());
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            View view2 = view;
            int indexOfChild = viewGroup.indexOfChild(view2);
            viewGroup.removeView(view2);
            viewGroup.addView(defaultTimeBar3, indexOfChild);
            this.timeBar = defaultTimeBar3;
        } else {
            componentListener2 = componentListener3;
            z10 = z8;
            z9 = z7;
            textView = null;
            this.timeBar = null;
        }
        TimeBar timeBar3 = this.timeBar;
        ComponentListener componentListener4 = componentListener2;
        if (timeBar3 != null) {
            timeBar3.addListener(componentListener4);
        }
        View findViewById5 = findViewById(R.id.exo_play_pause);
        this.playPauseButton = findViewById5;
        if (findViewById5 != null) {
            findViewById5.setOnClickListener(componentListener4);
        }
        View findViewById6 = findViewById(R.id.exo_prev);
        this.previousButton = findViewById6;
        if (findViewById6 != null) {
            findViewById6.setOnClickListener(componentListener4);
        }
        View findViewById7 = findViewById(R.id.exo_next);
        this.nextButton = findViewById7;
        if (findViewById7 != null) {
            findViewById7.setOnClickListener(componentListener4);
        }
        Typeface font = ResourcesCompat.getFont(context2, R.font.roboto_medium_numbers);
        View findViewById8 = findViewById(R.id.exo_rew);
        TextView textView2 = findViewById8 == null ? (TextView) findViewById(R.id.exo_rew_with_amount) : textView;
        this.rewindButtonTextView = textView2;
        if (textView2 != null) {
            textView2.setTypeface(font);
        }
        findViewById8 = findViewById8 == null ? textView2 : findViewById8;
        this.rewindButton = findViewById8;
        if (findViewById8 != null) {
            findViewById8.setOnClickListener(componentListener4);
        }
        View findViewById9 = findViewById(R.id.exo_ffwd);
        TextView textView3 = findViewById9 == null ? (TextView) findViewById(R.id.exo_ffwd_with_amount) : null;
        this.fastForwardButtonTextView = textView3;
        if (textView3 != null) {
            textView3.setTypeface(font);
        }
        findViewById9 = findViewById9 == null ? textView3 : findViewById9;
        this.fastForwardButton = findViewById9;
        if (findViewById9 != null) {
            findViewById9.setOnClickListener(componentListener4);
        }
        ImageView imageView4 = (ImageView) findViewById(R.id.exo_repeat_toggle);
        this.repeatToggleButton = imageView4;
        if (imageView4 != null) {
            imageView4.setOnClickListener(componentListener4);
        }
        ImageView imageView5 = (ImageView) findViewById(R.id.exo_shuffle);
        this.shuffleButton = imageView5;
        if (imageView5 != null) {
            imageView5.setOnClickListener(componentListener4);
        }
        ImageView imageView6 = imageView4;
        Resources resources2 = context.getResources();
        this.resources = resources2;
        ImageView imageView7 = imageView;
        this.buttonAlphaEnabled = ((float) resources2.getInteger(R.integer.exo_media_button_opacity_percentage_enabled)) / 100.0f;
        this.buttonAlphaDisabled = ((float) resources2.getInteger(R.integer.exo_media_button_opacity_percentage_disabled)) / 100.0f;
        View findViewById10 = findViewById(R.id.exo_vr);
        this.vrButton = findViewById10;
        boolean z21 = z;
        if (findViewById10 != null) {
            updateButton(false, findViewById10);
        }
        StyledPlayerControlViewLayoutManager styledPlayerControlViewLayoutManager = new StyledPlayerControlViewLayoutManager(this);
        this.controlViewLayoutManager = styledPlayerControlViewLayoutManager;
        View view3 = findViewById10;
        styledPlayerControlViewLayoutManager.setAnimationEnabled(z10);
        ImageView imageView8 = imageView5;
        boolean z22 = z2;
        View view4 = findViewById7;
        SettingsAdapter settingsAdapter2 = new SettingsAdapter(new String[]{resources2.getString(R.string.exo_controls_playback_speed), resources2.getString(R.string.exo_track_selection_title_audio)}, new Drawable[]{Util.getDrawable(context2, resources2, R.drawable.exo_styled_controls_speed), Util.getDrawable(context2, resources2, R.drawable.exo_styled_controls_audiotrack)});
        this.settingsAdapter = settingsAdapter2;
        this.settingsWindowMargin = resources2.getDimensionPixelSize(R.dimen.exo_settings_offset);
        RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(context).inflate(R.layout.exo_styled_settings_list, (ViewGroup) null);
        this.settingsView = recyclerView;
        recyclerView.setAdapter(settingsAdapter2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        PopupWindow popupWindow = new PopupWindow(recyclerView, -2, -2, true);
        this.settingsWindow = popupWindow;
        if (Util.SDK_INT < 23) {
            z11 = false;
            popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        } else {
            z11 = false;
        }
        popupWindow.setOnDismissListener(componentListener4);
        this.needToHideBars = true;
        this.trackNameProvider = new DefaultTrackNameProvider(getResources());
        this.subtitleOnButtonDrawable = Util.getDrawable(context2, resources2, R.drawable.exo_styled_controls_subtitle_on);
        this.subtitleOffButtonDrawable = Util.getDrawable(context2, resources2, R.drawable.exo_styled_controls_subtitle_off);
        this.subtitleOnContentDescription = resources2.getString(R.string.exo_controls_cc_enabled_description);
        this.subtitleOffContentDescription = resources2.getString(R.string.exo_controls_cc_disabled_description);
        this.textTrackSelectionAdapter = new TextTrackSelectionAdapter();
        this.audioTrackSelectionAdapter = new AudioTrackSelectionAdapter();
        this.playbackSpeedAdapter = new PlaybackSpeedAdapter(resources2.getStringArray(R.array.exo_controls_playback_speeds), PLAYBACK_SPEEDS);
        this.fullScreenExitDrawable = Util.getDrawable(context2, resources2, R.drawable.exo_styled_controls_fullscreen_exit);
        this.fullScreenEnterDrawable = Util.getDrawable(context2, resources2, R.drawable.exo_styled_controls_fullscreen_enter);
        this.repeatOffButtonDrawable = Util.getDrawable(context2, resources2, R.drawable.exo_styled_controls_repeat_off);
        this.repeatOneButtonDrawable = Util.getDrawable(context2, resources2, R.drawable.exo_styled_controls_repeat_one);
        this.repeatAllButtonDrawable = Util.getDrawable(context2, resources2, R.drawable.exo_styled_controls_repeat_all);
        this.shuffleOnButtonDrawable = Util.getDrawable(context2, resources2, R.drawable.exo_styled_controls_shuffle_on);
        this.shuffleOffButtonDrawable = Util.getDrawable(context2, resources2, R.drawable.exo_styled_controls_shuffle_off);
        this.fullScreenExitContentDescription = resources2.getString(R.string.exo_controls_fullscreen_exit_description);
        this.fullScreenEnterContentDescription = resources2.getString(R.string.exo_controls_fullscreen_enter_description);
        this.repeatOffButtonContentDescription = resources2.getString(R.string.exo_controls_repeat_off_description);
        this.repeatOneButtonContentDescription = resources2.getString(R.string.exo_controls_repeat_one_description);
        this.repeatAllButtonContentDescription = resources2.getString(R.string.exo_controls_repeat_all_description);
        this.shuffleOnContentDescription = resources2.getString(R.string.exo_controls_shuffle_on_description);
        this.shuffleOffContentDescription = resources2.getString(R.string.exo_controls_shuffle_off_description);
        styledPlayerControlViewLayoutManager.setShowButton((ViewGroup) findViewById(R.id.exo_bottom_bar), true);
        styledPlayerControlViewLayoutManager.setShowButton(findViewById9, z5);
        styledPlayerControlViewLayoutManager.setShowButton(findViewById8, z6);
        styledPlayerControlViewLayoutManager.setShowButton(findViewById6, z4);
        styledPlayerControlViewLayoutManager.setShowButton(view4, z3);
        styledPlayerControlViewLayoutManager.setShowButton(imageView8, z22);
        styledPlayerControlViewLayoutManager.setShowButton(imageView7, z21);
        styledPlayerControlViewLayoutManager.setShowButton(view3, z9);
        styledPlayerControlViewLayoutManager.setShowButton(imageView6, this.repeatToggleModes != 0 ? true : z11);
        addOnLayoutChangeListener(new StyledPlayerControlView$$ExternalSyntheticLambda2(this));
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player2) {
        boolean z = true;
        Assertions.checkState(Looper.myLooper() == Looper.getMainLooper());
        if (!(player2 == null || player2.getApplicationLooper() == Looper.getMainLooper())) {
            z = false;
        }
        Assertions.checkArgument(z);
        Player player3 = this.player;
        if (player3 != player2) {
            if (player3 != null) {
                player3.removeListener(this.componentListener);
            }
            this.player = player2;
            if (player2 != null) {
                player2.addListener(this.componentListener);
            }
            updateAll();
        }
    }

    public void setShowMultiWindowTimeBar(boolean z) {
        this.showMultiWindowTimeBar = z;
        updateTimeline();
    }

    public void setExtraAdGroupMarkers(long[] jArr, boolean[] zArr) {
        boolean z = false;
        if (jArr == null) {
            this.extraAdGroupTimesMs = new long[0];
            this.extraPlayedAdGroups = new boolean[0];
        } else {
            boolean[] zArr2 = (boolean[]) Assertions.checkNotNull(zArr);
            if (jArr.length == zArr2.length) {
                z = true;
            }
            Assertions.checkArgument(z);
            this.extraAdGroupTimesMs = jArr;
            this.extraPlayedAdGroups = zArr2;
        }
        updateTimeline();
    }

    @Deprecated
    public void addVisibilityListener(VisibilityListener visibilityListener) {
        Assertions.checkNotNull(visibilityListener);
        this.visibilityListeners.add(visibilityListener);
    }

    @Deprecated
    public void removeVisibilityListener(VisibilityListener visibilityListener) {
        this.visibilityListeners.remove(visibilityListener);
    }

    public void setProgressUpdateListener(ProgressUpdateListener progressUpdateListener2) {
        this.progressUpdateListener = progressUpdateListener2;
    }

    public void setShowRewindButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.rewindButton, z);
        updateNavigation();
    }

    public void setShowFastForwardButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.fastForwardButton, z);
        updateNavigation();
    }

    public void setShowPreviousButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.previousButton, z);
        updateNavigation();
    }

    public void setShowNextButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.nextButton, z);
        updateNavigation();
    }

    public int getShowTimeoutMs() {
        return this.showTimeoutMs;
    }

    public void setShowTimeoutMs(int i) {
        this.showTimeoutMs = i;
        if (isFullyVisible()) {
            this.controlViewLayoutManager.resetHideCallbacks();
        }
    }

    public int getRepeatToggleModes() {
        return this.repeatToggleModes;
    }

    public void setRepeatToggleModes(int i) {
        this.repeatToggleModes = i;
        Player player2 = this.player;
        boolean z = false;
        if (player2 != null && player2.isCommandAvailable(15)) {
            int repeatMode = this.player.getRepeatMode();
            if (i == 0 && repeatMode != 0) {
                this.player.setRepeatMode(0);
            } else if (i == 1 && repeatMode == 2) {
                this.player.setRepeatMode(1);
            } else if (i == 2 && repeatMode == 1) {
                this.player.setRepeatMode(2);
            }
        }
        StyledPlayerControlViewLayoutManager styledPlayerControlViewLayoutManager = this.controlViewLayoutManager;
        ImageView imageView = this.repeatToggleButton;
        if (i != 0) {
            z = true;
        }
        styledPlayerControlViewLayoutManager.setShowButton(imageView, z);
        updateRepeatModeButton();
    }

    public boolean getShowShuffleButton() {
        return this.controlViewLayoutManager.getShowButton(this.shuffleButton);
    }

    public void setShowShuffleButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.shuffleButton, z);
        updateShuffleButton();
    }

    public boolean getShowSubtitleButton() {
        return this.controlViewLayoutManager.getShowButton(this.subtitleButton);
    }

    public void setShowSubtitleButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.subtitleButton, z);
    }

    public boolean getShowVrButton() {
        return this.controlViewLayoutManager.getShowButton(this.vrButton);
    }

    public void setShowVrButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.vrButton, z);
    }

    public void setVrButtonListener(View.OnClickListener onClickListener) {
        View view = this.vrButton;
        if (view != null) {
            view.setOnClickListener(onClickListener);
            updateButton(onClickListener != null, this.vrButton);
        }
    }

    public void setAnimationEnabled(boolean z) {
        this.controlViewLayoutManager.setAnimationEnabled(z);
    }

    public boolean isAnimationEnabled() {
        return this.controlViewLayoutManager.isAnimationEnabled();
    }

    public void setTimeBarMinUpdateInterval(int i) {
        this.timeBarMinUpdateIntervalMs = Util.constrainValue(i, 16, 1000);
    }

    @Deprecated
    public void setOnFullScreenModeChangedListener(OnFullScreenModeChangedListener onFullScreenModeChangedListener2) {
        this.onFullScreenModeChangedListener = onFullScreenModeChangedListener2;
        boolean z = true;
        updateFullScreenButtonVisibility(this.fullScreenButton, onFullScreenModeChangedListener2 != null);
        ImageView imageView = this.minimalFullScreenButton;
        if (onFullScreenModeChangedListener2 == null) {
            z = false;
        }
        updateFullScreenButtonVisibility(imageView, z);
    }

    public void show() {
        this.controlViewLayoutManager.show();
    }

    public void hide() {
        this.controlViewLayoutManager.hide();
    }

    public void hideImmediately() {
        this.controlViewLayoutManager.hideImmediately();
    }

    public boolean isFullyVisible() {
        return this.controlViewLayoutManager.isFullyVisible();
    }

    public boolean isVisible() {
        return getVisibility() == 0;
    }

    /* access modifiers changed from: package-private */
    public void notifyOnVisibilityChange() {
        Iterator<VisibilityListener> it = this.visibilityListeners.iterator();
        while (it.hasNext()) {
            it.next().onVisibilityChange(getVisibility());
        }
    }

    /* access modifiers changed from: package-private */
    public void updateAll() {
        updatePlayPauseButton();
        updateNavigation();
        updateRepeatModeButton();
        updateShuffleButton();
        updateTrackLists();
        updatePlaybackSpeedList();
        updateTimeline();
    }

    /* access modifiers changed from: private */
    public void updatePlayPauseButton() {
        int i;
        int i2;
        if (isVisible() && this.isAttachedToWindow && this.playPauseButton != null) {
            boolean shouldShowPauseButton = shouldShowPauseButton();
            if (shouldShowPauseButton) {
                i = R.drawable.exo_styled_controls_pause;
            } else {
                i = R.drawable.exo_styled_controls_play;
            }
            if (shouldShowPauseButton) {
                i2 = R.string.exo_controls_pause_description;
            } else {
                i2 = R.string.exo_controls_play_description;
            }
            ((ImageView) this.playPauseButton).setImageDrawable(Util.getDrawable(getContext(), this.resources, i));
            this.playPauseButton.setContentDescription(this.resources.getString(i2));
            updateButton(shouldEnablePlayPauseButton(), this.playPauseButton);
        }
    }

    /* access modifiers changed from: private */
    public void updateNavigation() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        if (isVisible() && this.isAttachedToWindow) {
            Player player2 = this.player;
            if (player2 != null) {
                if (!this.showMultiWindowTimeBar || !canShowMultiWindowTimeBar(player2, this.window)) {
                    z4 = player2.isCommandAvailable(5);
                } else {
                    z4 = player2.isCommandAvailable(10);
                }
                z3 = player2.isCommandAvailable(7);
                z2 = player2.isCommandAvailable(11);
                z = player2.isCommandAvailable(12);
                z5 = player2.isCommandAvailable(9);
            } else {
                z4 = false;
                z5 = false;
                z3 = false;
                z2 = false;
                z = false;
            }
            if (z2) {
                updateRewindButton();
            }
            if (z) {
                updateFastForwardButton();
            }
            updateButton(z3, this.previousButton);
            updateButton(z2, this.rewindButton);
            updateButton(z, this.fastForwardButton);
            updateButton(z5, this.nextButton);
            TimeBar timeBar2 = this.timeBar;
            if (timeBar2 != null) {
                timeBar2.setEnabled(z4);
            }
        }
    }

    private void updateRewindButton() {
        Player player2 = this.player;
        int seekBackIncrement = (int) ((player2 != null ? player2.getSeekBackIncrement() : 5000) / 1000);
        TextView textView = this.rewindButtonTextView;
        if (textView != null) {
            textView.setText(String.valueOf(seekBackIncrement));
        }
        View view = this.rewindButton;
        if (view != null) {
            view.setContentDescription(this.resources.getQuantityString(R.plurals.exo_controls_rewind_by_amount_description, seekBackIncrement, new Object[]{Integer.valueOf(seekBackIncrement)}));
        }
    }

    private void updateFastForwardButton() {
        Player player2 = this.player;
        int seekForwardIncrement = (int) ((player2 != null ? player2.getSeekForwardIncrement() : C.DEFAULT_SEEK_FORWARD_INCREMENT_MS) / 1000);
        TextView textView = this.fastForwardButtonTextView;
        if (textView != null) {
            textView.setText(String.valueOf(seekForwardIncrement));
        }
        View view = this.fastForwardButton;
        if (view != null) {
            view.setContentDescription(this.resources.getQuantityString(R.plurals.exo_controls_fastforward_by_amount_description, seekForwardIncrement, new Object[]{Integer.valueOf(seekForwardIncrement)}));
        }
    }

    /* access modifiers changed from: private */
    public void updateRepeatModeButton() {
        ImageView imageView;
        if (isVisible() && this.isAttachedToWindow && (imageView = this.repeatToggleButton) != null) {
            if (this.repeatToggleModes == 0) {
                updateButton(false, imageView);
                return;
            }
            Player player2 = this.player;
            if (player2 == null || !player2.isCommandAvailable(15)) {
                updateButton(false, this.repeatToggleButton);
                this.repeatToggleButton.setImageDrawable(this.repeatOffButtonDrawable);
                this.repeatToggleButton.setContentDescription(this.repeatOffButtonContentDescription);
                return;
            }
            updateButton(true, this.repeatToggleButton);
            int repeatMode = player2.getRepeatMode();
            if (repeatMode == 0) {
                this.repeatToggleButton.setImageDrawable(this.repeatOffButtonDrawable);
                this.repeatToggleButton.setContentDescription(this.repeatOffButtonContentDescription);
            } else if (repeatMode == 1) {
                this.repeatToggleButton.setImageDrawable(this.repeatOneButtonDrawable);
                this.repeatToggleButton.setContentDescription(this.repeatOneButtonContentDescription);
            } else if (repeatMode == 2) {
                this.repeatToggleButton.setImageDrawable(this.repeatAllButtonDrawable);
                this.repeatToggleButton.setContentDescription(this.repeatAllButtonContentDescription);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateShuffleButton() {
        ImageView imageView;
        String str;
        if (isVisible() && this.isAttachedToWindow && (imageView = this.shuffleButton) != null) {
            Player player2 = this.player;
            if (!this.controlViewLayoutManager.getShowButton(imageView)) {
                updateButton(false, this.shuffleButton);
            } else if (player2 == null || !player2.isCommandAvailable(14)) {
                updateButton(false, this.shuffleButton);
                this.shuffleButton.setImageDrawable(this.shuffleOffButtonDrawable);
                this.shuffleButton.setContentDescription(this.shuffleOffContentDescription);
            } else {
                updateButton(true, this.shuffleButton);
                this.shuffleButton.setImageDrawable(player2.getShuffleModeEnabled() ? this.shuffleOnButtonDrawable : this.shuffleOffButtonDrawable);
                ImageView imageView2 = this.shuffleButton;
                if (player2.getShuffleModeEnabled()) {
                    str = this.shuffleOnContentDescription;
                } else {
                    str = this.shuffleOffContentDescription;
                }
                imageView2.setContentDescription(str);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateTrackLists() {
        initTrackSelectionAdapter();
        updateButton(this.textTrackSelectionAdapter.getItemCount() > 0, this.subtitleButton);
        updateSettingsButton();
    }

    private void initTrackSelectionAdapter() {
        this.textTrackSelectionAdapter.clear();
        this.audioTrackSelectionAdapter.clear();
        Player player2 = this.player;
        if (player2 != null && player2.isCommandAvailable(30) && this.player.isCommandAvailable(29)) {
            Tracks currentTracks = this.player.getCurrentTracks();
            this.audioTrackSelectionAdapter.init(gatherSupportedTrackInfosOfType(currentTracks, 1));
            if (this.controlViewLayoutManager.getShowButton(this.subtitleButton)) {
                this.textTrackSelectionAdapter.init(gatherSupportedTrackInfosOfType(currentTracks, 3));
            } else {
                this.textTrackSelectionAdapter.init(ImmutableList.of());
            }
        }
    }

    private ImmutableList<TrackInformation> gatherSupportedTrackInfosOfType(Tracks tracks, int i) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        ImmutableList<Tracks.Group> groups = tracks.getGroups();
        for (int i2 = 0; i2 < groups.size(); i2++) {
            Tracks.Group group = groups.get(i2);
            if (group.getType() == i) {
                for (int i3 = 0; i3 < group.length; i3++) {
                    if (group.isTrackSupported(i3)) {
                        Format trackFormat = group.getTrackFormat(i3);
                        if ((trackFormat.selectionFlags & 2) == 0) {
                            builder.add((Object) new TrackInformation(tracks, i2, i3, this.trackNameProvider.getTrackName(trackFormat)));
                        }
                    }
                }
            }
        }
        return builder.build();
    }

    /* access modifiers changed from: private */
    public void updateTimeline() {
        Timeline timeline;
        int i;
        long j;
        Player player2 = this.player;
        if (player2 != null) {
            boolean z = true;
            this.multiWindowTimeBar = this.showMultiWindowTimeBar && canShowMultiWindowTimeBar(player2, this.window);
            this.currentWindowOffset = 0;
            if (player2.isCommandAvailable(17)) {
                timeline = player2.getCurrentTimeline();
            } else {
                timeline = Timeline.EMPTY;
            }
            if (!timeline.isEmpty()) {
                int currentMediaItemIndex = player2.getCurrentMediaItemIndex();
                boolean z2 = this.multiWindowTimeBar;
                int i2 = z2 ? 0 : currentMediaItemIndex;
                int windowCount = z2 ? timeline.getWindowCount() - 1 : currentMediaItemIndex;
                long j2 = 0;
                i = 0;
                while (true) {
                    if (i2 > windowCount) {
                        break;
                    }
                    if (i2 == currentMediaItemIndex) {
                        this.currentWindowOffset = Util.usToMs(j2);
                    }
                    timeline.getWindow(i2, this.window);
                    if (this.window.durationUs == C.TIME_UNSET) {
                        Assertions.checkState(this.multiWindowTimeBar ^ z);
                        break;
                    }
                    for (int i3 = this.window.firstPeriodIndex; i3 <= this.window.lastPeriodIndex; i3++) {
                        timeline.getPeriod(i3, this.period);
                        int adGroupCount = this.period.getAdGroupCount();
                        for (int removedAdGroupCount = this.period.getRemovedAdGroupCount(); removedAdGroupCount < adGroupCount; removedAdGroupCount++) {
                            long adGroupTimeUs = this.period.getAdGroupTimeUs(removedAdGroupCount);
                            if (adGroupTimeUs == Long.MIN_VALUE) {
                                if (this.period.durationUs == C.TIME_UNSET) {
                                } else {
                                    adGroupTimeUs = this.period.durationUs;
                                }
                            }
                            long positionInWindowUs = adGroupTimeUs + this.period.getPositionInWindowUs();
                            if (positionInWindowUs >= 0) {
                                long[] jArr = this.adGroupTimesMs;
                                if (i == jArr.length) {
                                    int length = jArr.length == 0 ? 1 : jArr.length * 2;
                                    this.adGroupTimesMs = Arrays.copyOf(jArr, length);
                                    this.playedAdGroups = Arrays.copyOf(this.playedAdGroups, length);
                                }
                                this.adGroupTimesMs[i] = Util.usToMs(j2 + positionInWindowUs);
                                this.playedAdGroups[i] = this.period.hasPlayedAdGroup(removedAdGroupCount);
                                i++;
                            }
                        }
                    }
                    j2 += this.window.durationUs;
                    i2++;
                    z = true;
                }
                j = j2;
            } else {
                if (player2.isCommandAvailable(16)) {
                    long contentDuration = player2.getContentDuration();
                    if (contentDuration != C.TIME_UNSET) {
                        j = Util.msToUs(contentDuration);
                        i = 0;
                    }
                }
                j = 0;
                i = 0;
            }
            long usToMs = Util.usToMs(j);
            TextView textView = this.durationView;
            if (textView != null) {
                textView.setText(Util.getStringForTime(this.formatBuilder, this.formatter, usToMs));
            }
            TimeBar timeBar2 = this.timeBar;
            if (timeBar2 != null) {
                timeBar2.setDuration(usToMs);
                int length2 = this.extraAdGroupTimesMs.length;
                int i4 = i + length2;
                long[] jArr2 = this.adGroupTimesMs;
                if (i4 > jArr2.length) {
                    this.adGroupTimesMs = Arrays.copyOf(jArr2, i4);
                    this.playedAdGroups = Arrays.copyOf(this.playedAdGroups, i4);
                }
                System.arraycopy(this.extraAdGroupTimesMs, 0, this.adGroupTimesMs, i, length2);
                System.arraycopy(this.extraPlayedAdGroups, 0, this.playedAdGroups, i, length2);
                this.timeBar.setAdGroupTimesMs(this.adGroupTimesMs, this.playedAdGroups, i4);
            }
            updateProgress();
        }
    }

    /* access modifiers changed from: private */
    public void updateProgress() {
        long j;
        long j2;
        int i;
        if (isVisible() && this.isAttachedToWindow) {
            Player player2 = this.player;
            if (player2 == null || !player2.isCommandAvailable(16)) {
                j2 = 0;
                j = 0;
            } else {
                j2 = this.currentWindowOffset + player2.getContentPosition();
                j = this.currentWindowOffset + player2.getContentBufferedPosition();
            }
            TextView textView = this.positionView;
            if (textView != null && !this.scrubbing) {
                textView.setText(Util.getStringForTime(this.formatBuilder, this.formatter, j2));
            }
            TimeBar timeBar2 = this.timeBar;
            if (timeBar2 != null) {
                timeBar2.setPosition(j2);
                this.timeBar.setBufferedPosition(j);
            }
            ProgressUpdateListener progressUpdateListener2 = this.progressUpdateListener;
            if (progressUpdateListener2 != null) {
                progressUpdateListener2.onProgressUpdate(j2, j);
            }
            removeCallbacks(this.updateProgressAction);
            if (player2 == null) {
                i = 1;
            } else {
                i = player2.getPlaybackState();
            }
            long j3 = 1000;
            if (player2 != null && player2.isPlaying()) {
                TimeBar timeBar3 = this.timeBar;
                long min = Math.min(timeBar3 != null ? timeBar3.getPreferredUpdateDelay() : 1000, 1000 - (j2 % 1000));
                float f = player2.getPlaybackParameters().speed;
                if (f > 0.0f) {
                    j3 = (long) (((float) min) / f);
                }
                postDelayed(this.updateProgressAction, Util.constrainValue(j3, (long) this.timeBarMinUpdateIntervalMs, 1000));
            } else if (i != 4 && i != 1) {
                postDelayed(this.updateProgressAction, 1000);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updatePlaybackSpeedList() {
        Player player2 = this.player;
        if (player2 != null) {
            this.playbackSpeedAdapter.updateSelectedIndex(player2.getPlaybackParameters().speed);
            this.settingsAdapter.setSubTextAtPosition(0, this.playbackSpeedAdapter.getSelectedText());
            updateSettingsButton();
        }
    }

    private void updateSettingsButton() {
        updateButton(this.settingsAdapter.hasSettingsToShow(), this.settingsButton);
    }

    private void updateSettingsWindowSize() {
        this.settingsView.measure(0, 0);
        this.settingsWindow.setWidth(Math.min(this.settingsView.getMeasuredWidth(), getWidth() - (this.settingsWindowMargin * 2)));
        this.settingsWindow.setHeight(Math.min(getHeight() - (this.settingsWindowMargin * 2), this.settingsView.getMeasuredHeight()));
    }

    /* access modifiers changed from: private */
    public void displaySettingsWindow(RecyclerView.Adapter<?> adapter, View view) {
        this.settingsView.setAdapter(adapter);
        updateSettingsWindowSize();
        this.needToHideBars = false;
        this.settingsWindow.dismiss();
        this.needToHideBars = true;
        this.settingsWindow.showAsDropDown(view, (getWidth() - this.settingsWindow.getWidth()) - this.settingsWindowMargin, (-this.settingsWindow.getHeight()) - this.settingsWindowMargin);
    }

    /* access modifiers changed from: private */
    public void setPlaybackSpeed(float f) {
        Player player2 = this.player;
        if (player2 != null && player2.isCommandAvailable(13)) {
            Player player3 = this.player;
            player3.setPlaybackParameters(player3.getPlaybackParameters().withSpeed(f));
        }
    }

    /* access modifiers changed from: package-private */
    public void requestPlayPauseFocus() {
        View view = this.playPauseButton;
        if (view != null) {
            view.requestFocus();
        }
    }

    private void updateButton(boolean z, View view) {
        if (view != null) {
            view.setEnabled(z);
            view.setAlpha(z ? this.buttonAlphaEnabled : this.buttonAlphaDisabled);
        }
    }

    /* access modifiers changed from: private */
    public void seekToTimeBarPosition(Player player2, long j) {
        if (this.multiWindowTimeBar) {
            if (player2.isCommandAvailable(17) && player2.isCommandAvailable(10)) {
                Timeline currentTimeline = player2.getCurrentTimeline();
                int windowCount = currentTimeline.getWindowCount();
                int i = 0;
                while (true) {
                    long durationMs = currentTimeline.getWindow(i, this.window).getDurationMs();
                    if (j < durationMs) {
                        break;
                    } else if (i == windowCount - 1) {
                        j = durationMs;
                        break;
                    } else {
                        j -= durationMs;
                        i++;
                    }
                }
                player2.seekTo(i, j);
            }
        } else if (player2.isCommandAvailable(5)) {
            player2.seekTo(j);
        }
        updateProgress();
    }

    /* access modifiers changed from: private */
    public void onFullScreenButtonClicked(View view) {
        if (this.onFullScreenModeChangedListener != null) {
            boolean z = !this.isFullScreen;
            this.isFullScreen = z;
            updateFullScreenButtonForState(this.fullScreenButton, z);
            updateFullScreenButtonForState(this.minimalFullScreenButton, this.isFullScreen);
            OnFullScreenModeChangedListener onFullScreenModeChangedListener2 = this.onFullScreenModeChangedListener;
            if (onFullScreenModeChangedListener2 != null) {
                onFullScreenModeChangedListener2.onFullScreenModeChanged(this.isFullScreen);
            }
        }
    }

    private void updateFullScreenButtonForState(ImageView imageView, boolean z) {
        if (imageView != null) {
            if (z) {
                imageView.setImageDrawable(this.fullScreenExitDrawable);
                imageView.setContentDescription(this.fullScreenExitContentDescription);
                return;
            }
            imageView.setImageDrawable(this.fullScreenEnterDrawable);
            imageView.setContentDescription(this.fullScreenEnterContentDescription);
        }
    }

    /* access modifiers changed from: private */
    public void onSettingViewClicked(int i) {
        if (i == 0) {
            displaySettingsWindow(this.playbackSpeedAdapter, (View) Assertions.checkNotNull(this.settingsButton));
        } else if (i == 1) {
            displaySettingsWindow(this.audioTrackSelectionAdapter, (View) Assertions.checkNotNull(this.settingsButton));
        } else {
            this.settingsWindow.dismiss();
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.controlViewLayoutManager.onAttachedToWindow();
        this.isAttachedToWindow = true;
        if (isFullyVisible()) {
            this.controlViewLayoutManager.resetHideCallbacks();
        }
        updateAll();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.controlViewLayoutManager.onDetachedFromWindow();
        this.isAttachedToWindow = false;
        removeCallbacks(this.updateProgressAction);
        this.controlViewLayoutManager.removeHideCallbacks();
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return dispatchMediaKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchMediaKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        Player player2 = this.player;
        if (player2 == null || !isHandledMediaKey(keyCode)) {
            return false;
        }
        if (keyEvent.getAction() != 0) {
            return true;
        }
        if (keyCode == 90) {
            if (player2.getPlaybackState() == 4 || !player2.isCommandAvailable(12)) {
                return true;
            }
            player2.seekForward();
            return true;
        } else if (keyCode == 89 && player2.isCommandAvailable(11)) {
            player2.seekBack();
            return true;
        } else if (keyEvent.getRepeatCount() != 0) {
            return true;
        } else {
            if (keyCode == 79 || keyCode == 85) {
                dispatchPlayPause(player2);
                return true;
            } else if (keyCode != 87) {
                if (keyCode != 88) {
                    if (keyCode == 126) {
                        dispatchPlay(player2);
                        return true;
                    } else if (keyCode != 127) {
                        return true;
                    } else {
                        dispatchPause(player2);
                        return true;
                    }
                } else if (!player2.isCommandAvailable(7)) {
                    return true;
                } else {
                    player2.seekToPrevious();
                    return true;
                }
            } else if (!player2.isCommandAvailable(9)) {
                return true;
            } else {
                player2.seekToNext();
                return true;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.controlViewLayoutManager.onLayout(z, i, i2, i3, i4);
    }

    /* access modifiers changed from: private */
    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = i4 - i2;
        int i10 = i8 - i6;
        if (!(i3 - i == i7 - i5 && i9 == i10) && this.settingsWindow.isShowing()) {
            updateSettingsWindowSize();
            this.settingsWindow.update(view, (getWidth() - this.settingsWindow.getWidth()) - this.settingsWindowMargin, (-this.settingsWindow.getHeight()) - this.settingsWindowMargin, -1, -1);
        }
    }

    private boolean shouldEnablePlayPauseButton() {
        Player player2 = this.player;
        if (player2 == null || !player2.isCommandAvailable(1) || (this.player.isCommandAvailable(17) && this.player.getCurrentTimeline().isEmpty())) {
            return false;
        }
        return true;
    }

    private boolean shouldShowPauseButton() {
        Player player2 = this.player;
        if (player2 == null || player2.getPlaybackState() == 4 || this.player.getPlaybackState() == 1 || !this.player.getPlayWhenReady()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void dispatchPlayPause(Player player2) {
        int playbackState = player2.getPlaybackState();
        if (playbackState == 1 || playbackState == 4 || !player2.getPlayWhenReady()) {
            dispatchPlay(player2);
        } else {
            dispatchPause(player2);
        }
    }

    private void dispatchPlay(Player player2) {
        int playbackState = player2.getPlaybackState();
        if (playbackState == 1 && player2.isCommandAvailable(2)) {
            player2.prepare();
        } else if (playbackState == 4 && player2.isCommandAvailable(4)) {
            player2.seekToDefaultPosition();
        }
        if (player2.isCommandAvailable(1)) {
            player2.play();
        }
    }

    private void dispatchPause(Player player2) {
        if (player2.isCommandAvailable(1)) {
            player2.pause();
        }
    }

    private static boolean canShowMultiWindowTimeBar(Player player2, Timeline.Window window2) {
        Timeline currentTimeline;
        int windowCount;
        if (!player2.isCommandAvailable(17) || (windowCount = currentTimeline.getWindowCount()) <= 1 || windowCount > 100) {
            return false;
        }
        for (int i = 0; i < windowCount; i++) {
            if ((currentTimeline = player2.getCurrentTimeline()).getWindow(i, window2).durationUs == C.TIME_UNSET) {
                return false;
            }
        }
        return true;
    }

    private static void initializeFullScreenButton(View view, View.OnClickListener onClickListener) {
        if (view != null) {
            view.setVisibility(8);
            view.setOnClickListener(onClickListener);
        }
    }

    private static void updateFullScreenButtonVisibility(View view, boolean z) {
        if (view != null) {
            if (z) {
                view.setVisibility(0);
            } else {
                view.setVisibility(8);
            }
        }
    }

    private static int getRepeatToggleModes(TypedArray typedArray, int i) {
        return typedArray.getInt(R.styleable.StyledPlayerControlView_repeat_toggle_modes, i);
    }

    private final class ComponentListener implements Player.Listener, TimeBar.OnScrubListener, View.OnClickListener, PopupWindow.OnDismissListener {
        public /* synthetic */ void onAudioAttributesChanged(AudioAttributes audioAttributes) {
            Player.Listener.CC.$default$onAudioAttributesChanged(this, audioAttributes);
        }

        public /* synthetic */ void onAudioSessionIdChanged(int i) {
            Player.Listener.CC.$default$onAudioSessionIdChanged(this, i);
        }

        public /* synthetic */ void onAvailableCommandsChanged(Player.Commands commands) {
            Player.Listener.CC.$default$onAvailableCommandsChanged(this, commands);
        }

        public /* synthetic */ void onCues(CueGroup cueGroup) {
            Player.Listener.CC.$default$onCues((Player.Listener) this, cueGroup);
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

        public /* synthetic */ void onPlayWhenReadyChanged(boolean z, int i) {
            Player.Listener.CC.$default$onPlayWhenReadyChanged(this, z, i);
        }

        public /* synthetic */ void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            Player.Listener.CC.$default$onPlaybackParametersChanged(this, playbackParameters);
        }

        public /* synthetic */ void onPlaybackStateChanged(int i) {
            Player.Listener.CC.$default$onPlaybackStateChanged(this, i);
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

        public /* synthetic */ void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
            Player.Listener.CC.$default$onPositionDiscontinuity(this, positionInfo, positionInfo2, i);
        }

        public /* synthetic */ void onRenderedFirstFrame() {
            Player.Listener.CC.$default$onRenderedFirstFrame(this);
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

        public /* synthetic */ void onTracksChanged(Tracks tracks) {
            Player.Listener.CC.$default$onTracksChanged(this, tracks);
        }

        public /* synthetic */ void onVideoSizeChanged(VideoSize videoSize) {
            Player.Listener.CC.$default$onVideoSizeChanged(this, videoSize);
        }

        public /* synthetic */ void onVolumeChanged(float f) {
            Player.Listener.CC.$default$onVolumeChanged(this, f);
        }

        private ComponentListener() {
        }

        public void onEvents(Player player, Player.Events events) {
            if (events.containsAny(4, 5, 13)) {
                StyledPlayerControlView.this.updatePlayPauseButton();
            }
            if (events.containsAny(4, 5, 7, 13)) {
                StyledPlayerControlView.this.updateProgress();
            }
            if (events.containsAny(8, 13)) {
                StyledPlayerControlView.this.updateRepeatModeButton();
            }
            if (events.containsAny(9, 13)) {
                StyledPlayerControlView.this.updateShuffleButton();
            }
            if (events.containsAny(8, 9, 11, 0, 16, 17, 13)) {
                StyledPlayerControlView.this.updateNavigation();
            }
            if (events.containsAny(11, 0, 13)) {
                StyledPlayerControlView.this.updateTimeline();
            }
            if (events.containsAny(12, 13)) {
                StyledPlayerControlView.this.updatePlaybackSpeedList();
            }
            if (events.containsAny(2, 13)) {
                StyledPlayerControlView.this.updateTrackLists();
            }
        }

        public void onScrubStart(TimeBar timeBar, long j) {
            boolean unused = StyledPlayerControlView.this.scrubbing = true;
            if (StyledPlayerControlView.this.positionView != null) {
                StyledPlayerControlView.this.positionView.setText(Util.getStringForTime(StyledPlayerControlView.this.formatBuilder, StyledPlayerControlView.this.formatter, j));
            }
            StyledPlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
        }

        public void onScrubMove(TimeBar timeBar, long j) {
            if (StyledPlayerControlView.this.positionView != null) {
                StyledPlayerControlView.this.positionView.setText(Util.getStringForTime(StyledPlayerControlView.this.formatBuilder, StyledPlayerControlView.this.formatter, j));
            }
        }

        public void onScrubStop(TimeBar timeBar, long j, boolean z) {
            boolean unused = StyledPlayerControlView.this.scrubbing = false;
            if (!z && StyledPlayerControlView.this.player != null) {
                StyledPlayerControlView styledPlayerControlView = StyledPlayerControlView.this;
                styledPlayerControlView.seekToTimeBarPosition(styledPlayerControlView.player, j);
            }
            StyledPlayerControlView.this.controlViewLayoutManager.resetHideCallbacks();
        }

        public void onDismiss() {
            if (StyledPlayerControlView.this.needToHideBars) {
                StyledPlayerControlView.this.controlViewLayoutManager.resetHideCallbacks();
            }
        }

        public void onClick(View view) {
            Player access$1600 = StyledPlayerControlView.this.player;
            if (access$1600 != null) {
                StyledPlayerControlView.this.controlViewLayoutManager.resetHideCallbacks();
                if (StyledPlayerControlView.this.nextButton == view) {
                    if (access$1600.isCommandAvailable(9)) {
                        access$1600.seekToNext();
                    }
                } else if (StyledPlayerControlView.this.previousButton == view) {
                    if (access$1600.isCommandAvailable(7)) {
                        access$1600.seekToPrevious();
                    }
                } else if (StyledPlayerControlView.this.fastForwardButton == view) {
                    if (access$1600.getPlaybackState() != 4 && access$1600.isCommandAvailable(12)) {
                        access$1600.seekForward();
                    }
                } else if (StyledPlayerControlView.this.rewindButton == view) {
                    if (access$1600.isCommandAvailable(11)) {
                        access$1600.seekBack();
                    }
                } else if (StyledPlayerControlView.this.playPauseButton == view) {
                    StyledPlayerControlView.this.dispatchPlayPause(access$1600);
                } else if (StyledPlayerControlView.this.repeatToggleButton == view) {
                    if (access$1600.isCommandAvailable(15)) {
                        access$1600.setRepeatMode(RepeatModeUtil.getNextRepeatMode(access$1600.getRepeatMode(), StyledPlayerControlView.this.repeatToggleModes));
                    }
                } else if (StyledPlayerControlView.this.shuffleButton == view) {
                    if (access$1600.isCommandAvailable(14)) {
                        access$1600.setShuffleModeEnabled(!access$1600.getShuffleModeEnabled());
                    }
                } else if (StyledPlayerControlView.this.settingsButton == view) {
                    StyledPlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                    StyledPlayerControlView styledPlayerControlView = StyledPlayerControlView.this;
                    styledPlayerControlView.displaySettingsWindow(styledPlayerControlView.settingsAdapter, StyledPlayerControlView.this.settingsButton);
                } else if (StyledPlayerControlView.this.playbackSpeedButton == view) {
                    StyledPlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                    StyledPlayerControlView styledPlayerControlView2 = StyledPlayerControlView.this;
                    styledPlayerControlView2.displaySettingsWindow(styledPlayerControlView2.playbackSpeedAdapter, StyledPlayerControlView.this.playbackSpeedButton);
                } else if (StyledPlayerControlView.this.audioTrackButton == view) {
                    StyledPlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                    StyledPlayerControlView styledPlayerControlView3 = StyledPlayerControlView.this;
                    styledPlayerControlView3.displaySettingsWindow(styledPlayerControlView3.audioTrackSelectionAdapter, StyledPlayerControlView.this.audioTrackButton);
                } else if (StyledPlayerControlView.this.subtitleButton == view) {
                    StyledPlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                    StyledPlayerControlView styledPlayerControlView4 = StyledPlayerControlView.this;
                    styledPlayerControlView4.displaySettingsWindow(styledPlayerControlView4.textTrackSelectionAdapter, StyledPlayerControlView.this.subtitleButton);
                }
            }
        }
    }

    private class SettingsAdapter extends RecyclerView.Adapter<SettingViewHolder> {
        private final Drawable[] iconIds;
        private final String[] mainTexts;
        private final String[] subTexts;

        public long getItemId(int i) {
            return (long) i;
        }

        public SettingsAdapter(String[] strArr, Drawable[] drawableArr) {
            this.mainTexts = strArr;
            this.subTexts = new String[strArr.length];
            this.iconIds = drawableArr;
        }

        public SettingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SettingViewHolder(LayoutInflater.from(StyledPlayerControlView.this.getContext()).inflate(R.layout.exo_styled_settings_list_item, viewGroup, false));
        }

        public void onBindViewHolder(SettingViewHolder settingViewHolder, int i) {
            if (shouldShowSetting(i)) {
                settingViewHolder.itemView.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            } else {
                settingViewHolder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            }
            settingViewHolder.mainTextView.setText(this.mainTexts[i]);
            if (this.subTexts[i] == null) {
                settingViewHolder.subTextView.setVisibility(8);
            } else {
                settingViewHolder.subTextView.setText(this.subTexts[i]);
            }
            if (this.iconIds[i] == null) {
                settingViewHolder.iconView.setVisibility(8);
            } else {
                settingViewHolder.iconView.setImageDrawable(this.iconIds[i]);
            }
        }

        public int getItemCount() {
            return this.mainTexts.length;
        }

        public void setSubTextAtPosition(int i, String str) {
            this.subTexts[i] = str;
        }

        public boolean hasSettingsToShow() {
            if (shouldShowSetting(1) || shouldShowSetting(0)) {
                return true;
            }
            return false;
        }

        private boolean shouldShowSetting(int i) {
            if (StyledPlayerControlView.this.player == null) {
                return false;
            }
            if (i == 0) {
                return StyledPlayerControlView.this.player.isCommandAvailable(13);
            }
            if (i != 1) {
                return true;
            }
            if (!StyledPlayerControlView.this.player.isCommandAvailable(30) || !StyledPlayerControlView.this.player.isCommandAvailable(29)) {
                return false;
            }
            return true;
        }
    }

    private final class SettingViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public final ImageView iconView;
        /* access modifiers changed from: private */
        public final TextView mainTextView;
        /* access modifiers changed from: private */
        public final TextView subTextView;

        public SettingViewHolder(View view) {
            super(view);
            if (Util.SDK_INT < 26) {
                view.setFocusable(true);
            }
            this.mainTextView = (TextView) view.findViewById(R.id.exo_main_text);
            this.subTextView = (TextView) view.findViewById(R.id.exo_sub_text);
            this.iconView = (ImageView) view.findViewById(R.id.exo_icon);
            view.setOnClickListener(new StyledPlayerControlView$SettingViewHolder$$ExternalSyntheticLambda0(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$new$0$com-google-android-exoplayer2-ui-StyledPlayerControlView$SettingViewHolder  reason: not valid java name */
        public /* synthetic */ void m520lambda$new$0$comgoogleandroidexoplayer2uiStyledPlayerControlView$SettingViewHolder(View view) {
            StyledPlayerControlView.this.onSettingViewClicked(getAdapterPosition());
        }
    }

    private final class PlaybackSpeedAdapter extends RecyclerView.Adapter<SubSettingViewHolder> {
        private final String[] playbackSpeedTexts;
        private final float[] playbackSpeeds;
        private int selectedIndex;

        public PlaybackSpeedAdapter(String[] strArr, float[] fArr) {
            this.playbackSpeedTexts = strArr;
            this.playbackSpeeds = fArr;
        }

        public void updateSelectedIndex(float f) {
            int i = 0;
            float f2 = Float.MAX_VALUE;
            int i2 = 0;
            while (true) {
                float[] fArr = this.playbackSpeeds;
                if (i < fArr.length) {
                    float abs = Math.abs(f - fArr[i]);
                    if (abs < f2) {
                        i2 = i;
                        f2 = abs;
                    }
                    i++;
                } else {
                    this.selectedIndex = i2;
                    return;
                }
            }
        }

        public String getSelectedText() {
            return this.playbackSpeedTexts[this.selectedIndex];
        }

        public SubSettingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SubSettingViewHolder(LayoutInflater.from(StyledPlayerControlView.this.getContext()).inflate(R.layout.exo_styled_sub_settings_list_item, viewGroup, false));
        }

        public void onBindViewHolder(SubSettingViewHolder subSettingViewHolder, int i) {
            if (i < this.playbackSpeedTexts.length) {
                subSettingViewHolder.textView.setText(this.playbackSpeedTexts[i]);
            }
            if (i == this.selectedIndex) {
                subSettingViewHolder.itemView.setSelected(true);
                subSettingViewHolder.checkView.setVisibility(0);
            } else {
                subSettingViewHolder.itemView.setSelected(false);
                subSettingViewHolder.checkView.setVisibility(4);
            }
            subSettingViewHolder.itemView.setOnClickListener(new StyledPlayerControlView$PlaybackSpeedAdapter$$ExternalSyntheticLambda0(this, i));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onBindViewHolder$0$com-google-android-exoplayer2-ui-StyledPlayerControlView$PlaybackSpeedAdapter  reason: not valid java name */
        public /* synthetic */ void m519lambda$onBindViewHolder$0$comgoogleandroidexoplayer2uiStyledPlayerControlView$PlaybackSpeedAdapter(int i, View view) {
            if (i != this.selectedIndex) {
                StyledPlayerControlView.this.setPlaybackSpeed(this.playbackSpeeds[i]);
            }
            StyledPlayerControlView.this.settingsWindow.dismiss();
        }

        public int getItemCount() {
            return this.playbackSpeedTexts.length;
        }
    }

    private static final class TrackInformation {
        public final Tracks.Group trackGroup;
        public final int trackIndex;
        public final String trackName;

        public TrackInformation(Tracks tracks, int i, int i2, String str) {
            this.trackGroup = (Tracks.Group) tracks.getGroups().get(i);
            this.trackIndex = i2;
            this.trackName = str;
        }

        public boolean isSelected() {
            return this.trackGroup.isTrackSelected(this.trackIndex);
        }
    }

    private final class TextTrackSelectionAdapter extends TrackSelectionAdapter {
        public void onTrackSelection(String str) {
        }

        private TextTrackSelectionAdapter() {
            super();
        }

        public void init(List<TrackInformation> list) {
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= list.size()) {
                    break;
                } else if (list.get(i).isSelected()) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            if (StyledPlayerControlView.this.subtitleButton != null) {
                ImageView access$3500 = StyledPlayerControlView.this.subtitleButton;
                StyledPlayerControlView styledPlayerControlView = StyledPlayerControlView.this;
                access$3500.setImageDrawable(z ? styledPlayerControlView.subtitleOnButtonDrawable : styledPlayerControlView.subtitleOffButtonDrawable);
                StyledPlayerControlView.this.subtitleButton.setContentDescription(z ? StyledPlayerControlView.this.subtitleOnContentDescription : StyledPlayerControlView.this.subtitleOffContentDescription);
            }
            this.tracks = list;
        }

        public void onBindViewHolderAtZeroPosition(SubSettingViewHolder subSettingViewHolder) {
            boolean z;
            subSettingViewHolder.textView.setText(R.string.exo_track_selection_none);
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i2 >= this.tracks.size()) {
                    z = true;
                    break;
                } else if (((TrackInformation) this.tracks.get(i2)).isSelected()) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
            View view = subSettingViewHolder.checkView;
            if (!z) {
                i = 4;
            }
            view.setVisibility(i);
            subSettingViewHolder.itemView.setOnClickListener(new StyledPlayerControlView$TextTrackSelectionAdapter$$ExternalSyntheticLambda0(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onBindViewHolderAtZeroPosition$0$com-google-android-exoplayer2-ui-StyledPlayerControlView$TextTrackSelectionAdapter  reason: not valid java name */
        public /* synthetic */ void m521lambda$onBindViewHolderAtZeroPosition$0$comgoogleandroidexoplayer2uiStyledPlayerControlView$TextTrackSelectionAdapter(View view) {
            if (StyledPlayerControlView.this.player != null && StyledPlayerControlView.this.player.isCommandAvailable(29)) {
                StyledPlayerControlView.this.player.setTrackSelectionParameters(StyledPlayerControlView.this.player.getTrackSelectionParameters().buildUpon().clearOverridesOfType(3).setIgnoredTextSelectionFlags(-3).build());
                StyledPlayerControlView.this.settingsWindow.dismiss();
            }
        }

        public void onBindViewHolder(SubSettingViewHolder subSettingViewHolder, int i) {
            super.onBindViewHolder(subSettingViewHolder, i);
            if (i > 0) {
                subSettingViewHolder.checkView.setVisibility(((TrackInformation) this.tracks.get(i + -1)).isSelected() ? 0 : 4);
            }
        }
    }

    private final class AudioTrackSelectionAdapter extends TrackSelectionAdapter {
        private AudioTrackSelectionAdapter() {
            super();
        }

        public void onBindViewHolderAtZeroPosition(SubSettingViewHolder subSettingViewHolder) {
            subSettingViewHolder.textView.setText(R.string.exo_track_selection_auto);
            subSettingViewHolder.checkView.setVisibility(hasSelectionOverride(((Player) Assertions.checkNotNull(StyledPlayerControlView.this.player)).getTrackSelectionParameters()) ? 4 : 0);
            subSettingViewHolder.itemView.setOnClickListener(new StyledPlayerControlView$AudioTrackSelectionAdapter$$ExternalSyntheticLambda0(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onBindViewHolderAtZeroPosition$0$com-google-android-exoplayer2-ui-StyledPlayerControlView$AudioTrackSelectionAdapter  reason: not valid java name */
        public /* synthetic */ void m518lambda$onBindViewHolderAtZeroPosition$0$comgoogleandroidexoplayer2uiStyledPlayerControlView$AudioTrackSelectionAdapter(View view) {
            if (StyledPlayerControlView.this.player != null && StyledPlayerControlView.this.player.isCommandAvailable(29)) {
                ((Player) Util.castNonNull(StyledPlayerControlView.this.player)).setTrackSelectionParameters(StyledPlayerControlView.this.player.getTrackSelectionParameters().buildUpon().clearOverridesOfType(1).setTrackTypeDisabled(1, false).build());
                StyledPlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, StyledPlayerControlView.this.getResources().getString(R.string.exo_track_selection_auto));
                StyledPlayerControlView.this.settingsWindow.dismiss();
            }
        }

        private boolean hasSelectionOverride(TrackSelectionParameters trackSelectionParameters) {
            for (int i = 0; i < this.tracks.size(); i++) {
                if (trackSelectionParameters.overrides.containsKey(((TrackInformation) this.tracks.get(i)).trackGroup.getMediaTrackGroup())) {
                    return true;
                }
            }
            return false;
        }

        public void onTrackSelection(String str) {
            StyledPlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, str);
        }

        public void init(List<TrackInformation> list) {
            this.tracks = list;
            TrackSelectionParameters trackSelectionParameters = ((Player) Assertions.checkNotNull(StyledPlayerControlView.this.player)).getTrackSelectionParameters();
            if (list.isEmpty()) {
                StyledPlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, StyledPlayerControlView.this.getResources().getString(R.string.exo_track_selection_none));
            } else if (!hasSelectionOverride(trackSelectionParameters)) {
                StyledPlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, StyledPlayerControlView.this.getResources().getString(R.string.exo_track_selection_auto));
            } else {
                for (int i = 0; i < list.size(); i++) {
                    TrackInformation trackInformation = list.get(i);
                    if (trackInformation.isSelected()) {
                        StyledPlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, trackInformation.trackName);
                        return;
                    }
                }
            }
        }
    }

    private abstract class TrackSelectionAdapter extends RecyclerView.Adapter<SubSettingViewHolder> {
        protected List<TrackInformation> tracks = new ArrayList();

        public abstract void init(List<TrackInformation> list);

        /* access modifiers changed from: protected */
        public abstract void onBindViewHolderAtZeroPosition(SubSettingViewHolder subSettingViewHolder);

        /* access modifiers changed from: protected */
        public abstract void onTrackSelection(String str);

        protected TrackSelectionAdapter() {
        }

        public SubSettingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SubSettingViewHolder(LayoutInflater.from(StyledPlayerControlView.this.getContext()).inflate(R.layout.exo_styled_sub_settings_list_item, viewGroup, false));
        }

        public void onBindViewHolder(SubSettingViewHolder subSettingViewHolder, int i) {
            Player access$1600 = StyledPlayerControlView.this.player;
            if (access$1600 != null) {
                if (i == 0) {
                    onBindViewHolderAtZeroPosition(subSettingViewHolder);
                    return;
                }
                boolean z = true;
                TrackInformation trackInformation = this.tracks.get(i - 1);
                TrackGroup mediaTrackGroup = trackInformation.trackGroup.getMediaTrackGroup();
                int i2 = 0;
                if (access$1600.getTrackSelectionParameters().overrides.get(mediaTrackGroup) == null || !trackInformation.isSelected()) {
                    z = false;
                }
                subSettingViewHolder.textView.setText(trackInformation.trackName);
                View view = subSettingViewHolder.checkView;
                if (!z) {
                    i2 = 4;
                }
                view.setVisibility(i2);
                subSettingViewHolder.itemView.setOnClickListener(new StyledPlayerControlView$TrackSelectionAdapter$$ExternalSyntheticLambda0(this, access$1600, mediaTrackGroup, trackInformation));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onBindViewHolder$0$com-google-android-exoplayer2-ui-StyledPlayerControlView$TrackSelectionAdapter  reason: not valid java name */
        public /* synthetic */ void m522lambda$onBindViewHolder$0$comgoogleandroidexoplayer2uiStyledPlayerControlView$TrackSelectionAdapter(Player player, TrackGroup trackGroup, TrackInformation trackInformation, View view) {
            if (player.isCommandAvailable(29)) {
                player.setTrackSelectionParameters(player.getTrackSelectionParameters().buildUpon().setOverrideForType(new TrackSelectionOverride(trackGroup, (List<Integer>) ImmutableList.of(Integer.valueOf(trackInformation.trackIndex)))).setTrackTypeDisabled(trackInformation.trackGroup.getType(), false).build());
                onTrackSelection(trackInformation.trackName);
                StyledPlayerControlView.this.settingsWindow.dismiss();
            }
        }

        public int getItemCount() {
            if (this.tracks.isEmpty()) {
                return 0;
            }
            return this.tracks.size() + 1;
        }

        /* access modifiers changed from: protected */
        public void clear() {
            this.tracks = Collections.emptyList();
        }
    }

    private static class SubSettingViewHolder extends RecyclerView.ViewHolder {
        public final View checkView;
        public final TextView textView;

        public SubSettingViewHolder(View view) {
            super(view);
            if (Util.SDK_INT < 26) {
                view.setFocusable(true);
            }
            this.textView = (TextView) view.findViewById(R.id.exo_text);
            this.checkView = view.findViewById(R.id.exo_check);
        }
    }
}
