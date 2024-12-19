package com.google.android.exoplayer2;

import android.net.Uri;
import android.os.Bundle;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Util;
import com.google.common.base.Objects;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;

public final class MediaMetadata implements Bundleable {
    public static final Bundleable.Creator<MediaMetadata> CREATOR = new MediaMetadata$$ExternalSyntheticLambda0();
    public static final MediaMetadata EMPTY = new Builder().build();
    private static final String FIELD_ALBUM_ARTIST = Util.intToStringMaxRadix(3);
    private static final String FIELD_ALBUM_TITLE = Util.intToStringMaxRadix(2);
    private static final String FIELD_ARTIST = Util.intToStringMaxRadix(1);
    private static final String FIELD_ARTWORK_DATA = Util.intToStringMaxRadix(10);
    private static final String FIELD_ARTWORK_DATA_TYPE = Util.intToStringMaxRadix(29);
    private static final String FIELD_ARTWORK_URI = Util.intToStringMaxRadix(11);
    private static final String FIELD_COMPILATION = Util.intToStringMaxRadix(28);
    private static final String FIELD_COMPOSER = Util.intToStringMaxRadix(23);
    private static final String FIELD_CONDUCTOR = Util.intToStringMaxRadix(24);
    private static final String FIELD_DESCRIPTION = Util.intToStringMaxRadix(6);
    private static final String FIELD_DISC_NUMBER = Util.intToStringMaxRadix(25);
    private static final String FIELD_DISPLAY_TITLE = Util.intToStringMaxRadix(4);
    private static final String FIELD_EXTRAS = Util.intToStringMaxRadix(1000);
    private static final String FIELD_FOLDER_TYPE = Util.intToStringMaxRadix(14);
    private static final String FIELD_GENRE = Util.intToStringMaxRadix(27);
    private static final String FIELD_IS_BROWSABLE = Util.intToStringMaxRadix(32);
    private static final String FIELD_IS_PLAYABLE = Util.intToStringMaxRadix(15);
    private static final String FIELD_MEDIA_TYPE = Util.intToStringMaxRadix(31);
    private static final String FIELD_OVERALL_RATING = Util.intToStringMaxRadix(9);
    private static final String FIELD_RECORDING_DAY = Util.intToStringMaxRadix(18);
    private static final String FIELD_RECORDING_MONTH = Util.intToStringMaxRadix(17);
    private static final String FIELD_RECORDING_YEAR = Util.intToStringMaxRadix(16);
    private static final String FIELD_RELEASE_DAY = Util.intToStringMaxRadix(21);
    private static final String FIELD_RELEASE_MONTH = Util.intToStringMaxRadix(20);
    private static final String FIELD_RELEASE_YEAR = Util.intToStringMaxRadix(19);
    private static final String FIELD_STATION = Util.intToStringMaxRadix(30);
    private static final String FIELD_SUBTITLE = Util.intToStringMaxRadix(5);
    private static final String FIELD_TITLE = Util.intToStringMaxRadix(0);
    private static final String FIELD_TOTAL_DISC_COUNT = Util.intToStringMaxRadix(26);
    private static final String FIELD_TOTAL_TRACK_COUNT = Util.intToStringMaxRadix(13);
    private static final String FIELD_TRACK_NUMBER = Util.intToStringMaxRadix(12);
    private static final String FIELD_USER_RATING = Util.intToStringMaxRadix(8);
    private static final String FIELD_WRITER = Util.intToStringMaxRadix(22);
    public static final int FOLDER_TYPE_ALBUMS = 2;
    public static final int FOLDER_TYPE_ARTISTS = 3;
    public static final int FOLDER_TYPE_GENRES = 4;
    public static final int FOLDER_TYPE_MIXED = 0;
    public static final int FOLDER_TYPE_NONE = -1;
    public static final int FOLDER_TYPE_PLAYLISTS = 5;
    public static final int FOLDER_TYPE_TITLES = 1;
    public static final int FOLDER_TYPE_YEARS = 6;
    public static final int MEDIA_TYPE_ALBUM = 10;
    public static final int MEDIA_TYPE_ARTIST = 11;
    public static final int MEDIA_TYPE_AUDIO_BOOK = 15;
    public static final int MEDIA_TYPE_AUDIO_BOOK_CHAPTER = 2;
    public static final int MEDIA_TYPE_FOLDER_ALBUMS = 21;
    public static final int MEDIA_TYPE_FOLDER_ARTISTS = 22;
    public static final int MEDIA_TYPE_FOLDER_AUDIO_BOOKS = 26;
    public static final int MEDIA_TYPE_FOLDER_GENRES = 23;
    public static final int MEDIA_TYPE_FOLDER_MIXED = 20;
    public static final int MEDIA_TYPE_FOLDER_MOVIES = 35;
    public static final int MEDIA_TYPE_FOLDER_NEWS = 32;
    public static final int MEDIA_TYPE_FOLDER_PLAYLISTS = 24;
    public static final int MEDIA_TYPE_FOLDER_PODCASTS = 27;
    public static final int MEDIA_TYPE_FOLDER_RADIO_STATIONS = 31;
    public static final int MEDIA_TYPE_FOLDER_TRAILERS = 34;
    public static final int MEDIA_TYPE_FOLDER_TV_CHANNELS = 28;
    public static final int MEDIA_TYPE_FOLDER_TV_SERIES = 29;
    public static final int MEDIA_TYPE_FOLDER_TV_SHOWS = 30;
    public static final int MEDIA_TYPE_FOLDER_VIDEOS = 33;
    public static final int MEDIA_TYPE_FOLDER_YEARS = 25;
    public static final int MEDIA_TYPE_GENRE = 12;
    public static final int MEDIA_TYPE_MIXED = 0;
    public static final int MEDIA_TYPE_MOVIE = 8;
    public static final int MEDIA_TYPE_MUSIC = 1;
    public static final int MEDIA_TYPE_NEWS = 5;
    public static final int MEDIA_TYPE_PLAYLIST = 13;
    public static final int MEDIA_TYPE_PODCAST = 16;
    public static final int MEDIA_TYPE_PODCAST_EPISODE = 3;
    public static final int MEDIA_TYPE_RADIO_STATION = 4;
    public static final int MEDIA_TYPE_TRAILER = 7;
    public static final int MEDIA_TYPE_TV_CHANNEL = 17;
    public static final int MEDIA_TYPE_TV_SEASON = 19;
    public static final int MEDIA_TYPE_TV_SERIES = 18;
    public static final int MEDIA_TYPE_TV_SHOW = 9;
    public static final int MEDIA_TYPE_VIDEO = 6;
    public static final int MEDIA_TYPE_YEAR = 14;
    public static final int PICTURE_TYPE_ARTIST_PERFORMER = 8;
    public static final int PICTURE_TYPE_A_BRIGHT_COLORED_FISH = 17;
    public static final int PICTURE_TYPE_BACK_COVER = 4;
    public static final int PICTURE_TYPE_BAND_ARTIST_LOGO = 19;
    public static final int PICTURE_TYPE_BAND_ORCHESTRA = 10;
    public static final int PICTURE_TYPE_COMPOSER = 11;
    public static final int PICTURE_TYPE_CONDUCTOR = 9;
    public static final int PICTURE_TYPE_DURING_PERFORMANCE = 15;
    public static final int PICTURE_TYPE_DURING_RECORDING = 14;
    public static final int PICTURE_TYPE_FILE_ICON = 1;
    public static final int PICTURE_TYPE_FILE_ICON_OTHER = 2;
    public static final int PICTURE_TYPE_FRONT_COVER = 3;
    public static final int PICTURE_TYPE_ILLUSTRATION = 18;
    public static final int PICTURE_TYPE_LEAD_ARTIST_PERFORMER = 7;
    public static final int PICTURE_TYPE_LEAFLET_PAGE = 5;
    public static final int PICTURE_TYPE_LYRICIST = 12;
    public static final int PICTURE_TYPE_MEDIA = 6;
    public static final int PICTURE_TYPE_MOVIE_VIDEO_SCREEN_CAPTURE = 16;
    public static final int PICTURE_TYPE_OTHER = 0;
    public static final int PICTURE_TYPE_PUBLISHER_STUDIO_LOGO = 20;
    public static final int PICTURE_TYPE_RECORDING_LOCATION = 13;
    public final CharSequence albumArtist;
    public final CharSequence albumTitle;
    public final CharSequence artist;
    public final byte[] artworkData;
    public final Integer artworkDataType;
    public final Uri artworkUri;
    public final CharSequence compilation;
    public final CharSequence composer;
    public final CharSequence conductor;
    public final CharSequence description;
    public final Integer discNumber;
    public final CharSequence displayTitle;
    public final Bundle extras;
    public final Integer folderType;
    public final CharSequence genre;
    public final Boolean isBrowsable;
    public final Boolean isPlayable;
    public final Integer mediaType;
    public final Rating overallRating;
    public final Integer recordingDay;
    public final Integer recordingMonth;
    public final Integer recordingYear;
    public final Integer releaseDay;
    public final Integer releaseMonth;
    public final Integer releaseYear;
    public final CharSequence station;
    public final CharSequence subtitle;
    public final CharSequence title;
    public final Integer totalDiscCount;
    public final Integer totalTrackCount;
    public final Integer trackNumber;
    public final Rating userRating;
    public final CharSequence writer;
    @Deprecated
    public final Integer year;

    @Documented
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FolderType {
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MediaType {
    }

    @Documented
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PictureType {
    }

    private static int getFolderTypeFromMediaType(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
                return 1;
            case 21:
                return 2;
            case 22:
                return 3;
            case 23:
                return 4;
            case 24:
                return 5;
            case 25:
                return 6;
            default:
                return 0;
        }
    }

    private static int getMediaTypeFromFolderType(int i) {
        switch (i) {
            case 1:
                return 0;
            case 2:
                return 21;
            case 3:
                return 22;
            case 4:
                return 23;
            case 5:
                return 24;
            case 6:
                return 25;
            default:
                return 20;
        }
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public CharSequence albumArtist;
        /* access modifiers changed from: private */
        public CharSequence albumTitle;
        /* access modifiers changed from: private */
        public CharSequence artist;
        /* access modifiers changed from: private */
        public byte[] artworkData;
        /* access modifiers changed from: private */
        public Integer artworkDataType;
        /* access modifiers changed from: private */
        public Uri artworkUri;
        /* access modifiers changed from: private */
        public CharSequence compilation;
        /* access modifiers changed from: private */
        public CharSequence composer;
        /* access modifiers changed from: private */
        public CharSequence conductor;
        /* access modifiers changed from: private */
        public CharSequence description;
        /* access modifiers changed from: private */
        public Integer discNumber;
        /* access modifiers changed from: private */
        public CharSequence displayTitle;
        /* access modifiers changed from: private */
        public Bundle extras;
        /* access modifiers changed from: private */
        public Integer folderType;
        /* access modifiers changed from: private */
        public CharSequence genre;
        /* access modifiers changed from: private */
        public Boolean isBrowsable;
        /* access modifiers changed from: private */
        public Boolean isPlayable;
        /* access modifiers changed from: private */
        public Integer mediaType;
        /* access modifiers changed from: private */
        public Rating overallRating;
        /* access modifiers changed from: private */
        public Integer recordingDay;
        /* access modifiers changed from: private */
        public Integer recordingMonth;
        /* access modifiers changed from: private */
        public Integer recordingYear;
        /* access modifiers changed from: private */
        public Integer releaseDay;
        /* access modifiers changed from: private */
        public Integer releaseMonth;
        /* access modifiers changed from: private */
        public Integer releaseYear;
        /* access modifiers changed from: private */
        public CharSequence station;
        /* access modifiers changed from: private */
        public CharSequence subtitle;
        /* access modifiers changed from: private */
        public CharSequence title;
        /* access modifiers changed from: private */
        public Integer totalDiscCount;
        /* access modifiers changed from: private */
        public Integer totalTrackCount;
        /* access modifiers changed from: private */
        public Integer trackNumber;
        /* access modifiers changed from: private */
        public Rating userRating;
        /* access modifiers changed from: private */
        public CharSequence writer;

        public Builder() {
        }

        private Builder(MediaMetadata mediaMetadata) {
            this.title = mediaMetadata.title;
            this.artist = mediaMetadata.artist;
            this.albumTitle = mediaMetadata.albumTitle;
            this.albumArtist = mediaMetadata.albumArtist;
            this.displayTitle = mediaMetadata.displayTitle;
            this.subtitle = mediaMetadata.subtitle;
            this.description = mediaMetadata.description;
            this.userRating = mediaMetadata.userRating;
            this.overallRating = mediaMetadata.overallRating;
            this.artworkData = mediaMetadata.artworkData;
            this.artworkDataType = mediaMetadata.artworkDataType;
            this.artworkUri = mediaMetadata.artworkUri;
            this.trackNumber = mediaMetadata.trackNumber;
            this.totalTrackCount = mediaMetadata.totalTrackCount;
            this.folderType = mediaMetadata.folderType;
            this.isBrowsable = mediaMetadata.isBrowsable;
            this.isPlayable = mediaMetadata.isPlayable;
            this.recordingYear = mediaMetadata.recordingYear;
            this.recordingMonth = mediaMetadata.recordingMonth;
            this.recordingDay = mediaMetadata.recordingDay;
            this.releaseYear = mediaMetadata.releaseYear;
            this.releaseMonth = mediaMetadata.releaseMonth;
            this.releaseDay = mediaMetadata.releaseDay;
            this.writer = mediaMetadata.writer;
            this.composer = mediaMetadata.composer;
            this.conductor = mediaMetadata.conductor;
            this.discNumber = mediaMetadata.discNumber;
            this.totalDiscCount = mediaMetadata.totalDiscCount;
            this.genre = mediaMetadata.genre;
            this.compilation = mediaMetadata.compilation;
            this.station = mediaMetadata.station;
            this.mediaType = mediaMetadata.mediaType;
            this.extras = mediaMetadata.extras;
        }

        public Builder setTitle(CharSequence charSequence) {
            this.title = charSequence;
            return this;
        }

        public Builder setArtist(CharSequence charSequence) {
            this.artist = charSequence;
            return this;
        }

        public Builder setAlbumTitle(CharSequence charSequence) {
            this.albumTitle = charSequence;
            return this;
        }

        public Builder setAlbumArtist(CharSequence charSequence) {
            this.albumArtist = charSequence;
            return this;
        }

        public Builder setDisplayTitle(CharSequence charSequence) {
            this.displayTitle = charSequence;
            return this;
        }

        public Builder setSubtitle(CharSequence charSequence) {
            this.subtitle = charSequence;
            return this;
        }

        public Builder setDescription(CharSequence charSequence) {
            this.description = charSequence;
            return this;
        }

        public Builder setUserRating(Rating rating) {
            this.userRating = rating;
            return this;
        }

        public Builder setOverallRating(Rating rating) {
            this.overallRating = rating;
            return this;
        }

        @Deprecated
        public Builder setArtworkData(byte[] bArr) {
            return setArtworkData(bArr, (Integer) null);
        }

        public Builder setArtworkData(byte[] bArr, Integer num) {
            this.artworkData = bArr == null ? null : (byte[]) bArr.clone();
            this.artworkDataType = num;
            return this;
        }

        public Builder maybeSetArtworkData(byte[] bArr, int i) {
            if (this.artworkData == null || Util.areEqual(Integer.valueOf(i), 3) || !Util.areEqual(this.artworkDataType, 3)) {
                this.artworkData = (byte[]) bArr.clone();
                this.artworkDataType = Integer.valueOf(i);
            }
            return this;
        }

        public Builder setArtworkUri(Uri uri) {
            this.artworkUri = uri;
            return this;
        }

        public Builder setTrackNumber(Integer num) {
            this.trackNumber = num;
            return this;
        }

        public Builder setTotalTrackCount(Integer num) {
            this.totalTrackCount = num;
            return this;
        }

        public Builder setFolderType(Integer num) {
            this.folderType = num;
            return this;
        }

        public Builder setIsBrowsable(Boolean bool) {
            this.isBrowsable = bool;
            return this;
        }

        public Builder setIsPlayable(Boolean bool) {
            this.isPlayable = bool;
            return this;
        }

        @Deprecated
        public Builder setYear(Integer num) {
            return setRecordingYear(num);
        }

        public Builder setRecordingYear(Integer num) {
            this.recordingYear = num;
            return this;
        }

        public Builder setRecordingMonth(Integer num) {
            this.recordingMonth = num;
            return this;
        }

        public Builder setRecordingDay(Integer num) {
            this.recordingDay = num;
            return this;
        }

        public Builder setReleaseYear(Integer num) {
            this.releaseYear = num;
            return this;
        }

        public Builder setReleaseMonth(Integer num) {
            this.releaseMonth = num;
            return this;
        }

        public Builder setReleaseDay(Integer num) {
            this.releaseDay = num;
            return this;
        }

        public Builder setWriter(CharSequence charSequence) {
            this.writer = charSequence;
            return this;
        }

        public Builder setComposer(CharSequence charSequence) {
            this.composer = charSequence;
            return this;
        }

        public Builder setConductor(CharSequence charSequence) {
            this.conductor = charSequence;
            return this;
        }

        public Builder setDiscNumber(Integer num) {
            this.discNumber = num;
            return this;
        }

        public Builder setTotalDiscCount(Integer num) {
            this.totalDiscCount = num;
            return this;
        }

        public Builder setGenre(CharSequence charSequence) {
            this.genre = charSequence;
            return this;
        }

        public Builder setCompilation(CharSequence charSequence) {
            this.compilation = charSequence;
            return this;
        }

        public Builder setStation(CharSequence charSequence) {
            this.station = charSequence;
            return this;
        }

        public Builder setMediaType(Integer num) {
            this.mediaType = num;
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.extras = bundle;
            return this;
        }

        public Builder populateFromMetadata(Metadata metadata) {
            for (int i = 0; i < metadata.length(); i++) {
                metadata.get(i).populateMediaMetadata(this);
            }
            return this;
        }

        public Builder populateFromMetadata(List<Metadata> list) {
            for (int i = 0; i < list.size(); i++) {
                Metadata metadata = list.get(i);
                for (int i2 = 0; i2 < metadata.length(); i2++) {
                    metadata.get(i2).populateMediaMetadata(this);
                }
            }
            return this;
        }

        public Builder populate(MediaMetadata mediaMetadata) {
            if (mediaMetadata == null) {
                return this;
            }
            if (mediaMetadata.title != null) {
                setTitle(mediaMetadata.title);
            }
            if (mediaMetadata.artist != null) {
                setArtist(mediaMetadata.artist);
            }
            if (mediaMetadata.albumTitle != null) {
                setAlbumTitle(mediaMetadata.albumTitle);
            }
            if (mediaMetadata.albumArtist != null) {
                setAlbumArtist(mediaMetadata.albumArtist);
            }
            if (mediaMetadata.displayTitle != null) {
                setDisplayTitle(mediaMetadata.displayTitle);
            }
            if (mediaMetadata.subtitle != null) {
                setSubtitle(mediaMetadata.subtitle);
            }
            if (mediaMetadata.description != null) {
                setDescription(mediaMetadata.description);
            }
            if (mediaMetadata.userRating != null) {
                setUserRating(mediaMetadata.userRating);
            }
            if (mediaMetadata.overallRating != null) {
                setOverallRating(mediaMetadata.overallRating);
            }
            if (mediaMetadata.artworkData != null) {
                setArtworkData(mediaMetadata.artworkData, mediaMetadata.artworkDataType);
            }
            if (mediaMetadata.artworkUri != null) {
                setArtworkUri(mediaMetadata.artworkUri);
            }
            if (mediaMetadata.trackNumber != null) {
                setTrackNumber(mediaMetadata.trackNumber);
            }
            if (mediaMetadata.totalTrackCount != null) {
                setTotalTrackCount(mediaMetadata.totalTrackCount);
            }
            if (mediaMetadata.folderType != null) {
                setFolderType(mediaMetadata.folderType);
            }
            if (mediaMetadata.isBrowsable != null) {
                setIsBrowsable(mediaMetadata.isBrowsable);
            }
            if (mediaMetadata.isPlayable != null) {
                setIsPlayable(mediaMetadata.isPlayable);
            }
            if (mediaMetadata.year != null) {
                setRecordingYear(mediaMetadata.year);
            }
            if (mediaMetadata.recordingYear != null) {
                setRecordingYear(mediaMetadata.recordingYear);
            }
            if (mediaMetadata.recordingMonth != null) {
                setRecordingMonth(mediaMetadata.recordingMonth);
            }
            if (mediaMetadata.recordingDay != null) {
                setRecordingDay(mediaMetadata.recordingDay);
            }
            if (mediaMetadata.releaseYear != null) {
                setReleaseYear(mediaMetadata.releaseYear);
            }
            if (mediaMetadata.releaseMonth != null) {
                setReleaseMonth(mediaMetadata.releaseMonth);
            }
            if (mediaMetadata.releaseDay != null) {
                setReleaseDay(mediaMetadata.releaseDay);
            }
            if (mediaMetadata.writer != null) {
                setWriter(mediaMetadata.writer);
            }
            if (mediaMetadata.composer != null) {
                setComposer(mediaMetadata.composer);
            }
            if (mediaMetadata.conductor != null) {
                setConductor(mediaMetadata.conductor);
            }
            if (mediaMetadata.discNumber != null) {
                setDiscNumber(mediaMetadata.discNumber);
            }
            if (mediaMetadata.totalDiscCount != null) {
                setTotalDiscCount(mediaMetadata.totalDiscCount);
            }
            if (mediaMetadata.genre != null) {
                setGenre(mediaMetadata.genre);
            }
            if (mediaMetadata.compilation != null) {
                setCompilation(mediaMetadata.compilation);
            }
            if (mediaMetadata.station != null) {
                setStation(mediaMetadata.station);
            }
            if (mediaMetadata.mediaType != null) {
                setMediaType(mediaMetadata.mediaType);
            }
            if (mediaMetadata.extras != null) {
                setExtras(mediaMetadata.extras);
            }
            return this;
        }

        public MediaMetadata build() {
            return new MediaMetadata(this);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v15, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v16, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v17, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v19, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private MediaMetadata(com.google.android.exoplayer2.MediaMetadata.Builder r7) {
        /*
            r6 = this;
            r6.<init>()
            java.lang.Boolean r0 = r7.isBrowsable
            java.lang.Integer r1 = r7.folderType
            java.lang.Integer r2 = r7.mediaType
            r3 = 0
            r4 = -1
            if (r0 == 0) goto L_0x0035
            boolean r5 = r0.booleanValue()
            if (r5 != 0) goto L_0x001e
            java.lang.Integer r1 = java.lang.Integer.valueOf(r4)
            goto L_0x0055
        L_0x001e:
            if (r1 == 0) goto L_0x0026
            int r5 = r1.intValue()
            if (r5 != r4) goto L_0x0055
        L_0x0026:
            if (r2 == 0) goto L_0x0030
            int r1 = r2.intValue()
            int r3 = getFolderTypeFromMediaType(r1)
        L_0x0030:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)
            goto L_0x0055
        L_0x0035:
            if (r1 == 0) goto L_0x0055
            int r0 = r1.intValue()
            if (r0 == r4) goto L_0x003e
            r3 = 1
        L_0x003e:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r3)
            r0.getClass()
            if (r3 == 0) goto L_0x0055
            if (r2 != 0) goto L_0x0055
            int r2 = r1.intValue()
            int r2 = getMediaTypeFromFolderType(r2)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
        L_0x0055:
            java.lang.CharSequence r3 = r7.title
            r6.title = r3
            java.lang.CharSequence r3 = r7.artist
            r6.artist = r3
            java.lang.CharSequence r3 = r7.albumTitle
            r6.albumTitle = r3
            java.lang.CharSequence r3 = r7.albumArtist
            r6.albumArtist = r3
            java.lang.CharSequence r3 = r7.displayTitle
            r6.displayTitle = r3
            java.lang.CharSequence r3 = r7.subtitle
            r6.subtitle = r3
            java.lang.CharSequence r3 = r7.description
            r6.description = r3
            com.google.android.exoplayer2.Rating r3 = r7.userRating
            r6.userRating = r3
            com.google.android.exoplayer2.Rating r3 = r7.overallRating
            r6.overallRating = r3
            byte[] r3 = r7.artworkData
            r6.artworkData = r3
            java.lang.Integer r3 = r7.artworkDataType
            r6.artworkDataType = r3
            android.net.Uri r3 = r7.artworkUri
            r6.artworkUri = r3
            java.lang.Integer r3 = r7.trackNumber
            r6.trackNumber = r3
            java.lang.Integer r3 = r7.totalTrackCount
            r6.totalTrackCount = r3
            r6.folderType = r1
            r6.isBrowsable = r0
            java.lang.Boolean r0 = r7.isPlayable
            r6.isPlayable = r0
            java.lang.Integer r0 = r7.recordingYear
            r6.year = r0
            java.lang.Integer r0 = r7.recordingYear
            r6.recordingYear = r0
            java.lang.Integer r0 = r7.recordingMonth
            r6.recordingMonth = r0
            java.lang.Integer r0 = r7.recordingDay
            r6.recordingDay = r0
            java.lang.Integer r0 = r7.releaseYear
            r6.releaseYear = r0
            java.lang.Integer r0 = r7.releaseMonth
            r6.releaseMonth = r0
            java.lang.Integer r0 = r7.releaseDay
            r6.releaseDay = r0
            java.lang.CharSequence r0 = r7.writer
            r6.writer = r0
            java.lang.CharSequence r0 = r7.composer
            r6.composer = r0
            java.lang.CharSequence r0 = r7.conductor
            r6.conductor = r0
            java.lang.Integer r0 = r7.discNumber
            r6.discNumber = r0
            java.lang.Integer r0 = r7.totalDiscCount
            r6.totalDiscCount = r0
            java.lang.CharSequence r0 = r7.genre
            r6.genre = r0
            java.lang.CharSequence r0 = r7.compilation
            r6.compilation = r0
            java.lang.CharSequence r0 = r7.station
            r6.station = r0
            r6.mediaType = r2
            android.os.Bundle r7 = r7.extras
            r6.extras = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.MediaMetadata.<init>(com.google.android.exoplayer2.MediaMetadata$Builder):void");
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MediaMetadata mediaMetadata = (MediaMetadata) obj;
        if (!Util.areEqual(this.title, mediaMetadata.title) || !Util.areEqual(this.artist, mediaMetadata.artist) || !Util.areEqual(this.albumTitle, mediaMetadata.albumTitle) || !Util.areEqual(this.albumArtist, mediaMetadata.albumArtist) || !Util.areEqual(this.displayTitle, mediaMetadata.displayTitle) || !Util.areEqual(this.subtitle, mediaMetadata.subtitle) || !Util.areEqual(this.description, mediaMetadata.description) || !Util.areEqual(this.userRating, mediaMetadata.userRating) || !Util.areEqual(this.overallRating, mediaMetadata.overallRating) || !Arrays.equals(this.artworkData, mediaMetadata.artworkData) || !Util.areEqual(this.artworkDataType, mediaMetadata.artworkDataType) || !Util.areEqual(this.artworkUri, mediaMetadata.artworkUri) || !Util.areEqual(this.trackNumber, mediaMetadata.trackNumber) || !Util.areEqual(this.totalTrackCount, mediaMetadata.totalTrackCount) || !Util.areEqual(this.folderType, mediaMetadata.folderType) || !Util.areEqual(this.isBrowsable, mediaMetadata.isBrowsable) || !Util.areEqual(this.isPlayable, mediaMetadata.isPlayable) || !Util.areEqual(this.recordingYear, mediaMetadata.recordingYear) || !Util.areEqual(this.recordingMonth, mediaMetadata.recordingMonth) || !Util.areEqual(this.recordingDay, mediaMetadata.recordingDay) || !Util.areEqual(this.releaseYear, mediaMetadata.releaseYear) || !Util.areEqual(this.releaseMonth, mediaMetadata.releaseMonth) || !Util.areEqual(this.releaseDay, mediaMetadata.releaseDay) || !Util.areEqual(this.writer, mediaMetadata.writer) || !Util.areEqual(this.composer, mediaMetadata.composer) || !Util.areEqual(this.conductor, mediaMetadata.conductor) || !Util.areEqual(this.discNumber, mediaMetadata.discNumber) || !Util.areEqual(this.totalDiscCount, mediaMetadata.totalDiscCount) || !Util.areEqual(this.genre, mediaMetadata.genre) || !Util.areEqual(this.compilation, mediaMetadata.compilation) || !Util.areEqual(this.station, mediaMetadata.station) || !Util.areEqual(this.mediaType, mediaMetadata.mediaType)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.title, this.artist, this.albumTitle, this.albumArtist, this.displayTitle, this.subtitle, this.description, this.userRating, this.overallRating, Integer.valueOf(Arrays.hashCode(this.artworkData)), this.artworkDataType, this.artworkUri, this.trackNumber, this.totalTrackCount, this.folderType, this.isBrowsable, this.isPlayable, this.recordingYear, this.recordingMonth, this.recordingDay, this.releaseYear, this.releaseMonth, this.releaseDay, this.writer, this.composer, this.conductor, this.discNumber, this.totalDiscCount, this.genre, this.compilation, this.station, this.mediaType);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        CharSequence charSequence = this.title;
        if (charSequence != null) {
            bundle.putCharSequence(FIELD_TITLE, charSequence);
        }
        CharSequence charSequence2 = this.artist;
        if (charSequence2 != null) {
            bundle.putCharSequence(FIELD_ARTIST, charSequence2);
        }
        CharSequence charSequence3 = this.albumTitle;
        if (charSequence3 != null) {
            bundle.putCharSequence(FIELD_ALBUM_TITLE, charSequence3);
        }
        CharSequence charSequence4 = this.albumArtist;
        if (charSequence4 != null) {
            bundle.putCharSequence(FIELD_ALBUM_ARTIST, charSequence4);
        }
        CharSequence charSequence5 = this.displayTitle;
        if (charSequence5 != null) {
            bundle.putCharSequence(FIELD_DISPLAY_TITLE, charSequence5);
        }
        CharSequence charSequence6 = this.subtitle;
        if (charSequence6 != null) {
            bundle.putCharSequence(FIELD_SUBTITLE, charSequence6);
        }
        CharSequence charSequence7 = this.description;
        if (charSequence7 != null) {
            bundle.putCharSequence(FIELD_DESCRIPTION, charSequence7);
        }
        byte[] bArr = this.artworkData;
        if (bArr != null) {
            bundle.putByteArray(FIELD_ARTWORK_DATA, bArr);
        }
        Uri uri = this.artworkUri;
        if (uri != null) {
            bundle.putParcelable(FIELD_ARTWORK_URI, uri);
        }
        CharSequence charSequence8 = this.writer;
        if (charSequence8 != null) {
            bundle.putCharSequence(FIELD_WRITER, charSequence8);
        }
        CharSequence charSequence9 = this.composer;
        if (charSequence9 != null) {
            bundle.putCharSequence(FIELD_COMPOSER, charSequence9);
        }
        CharSequence charSequence10 = this.conductor;
        if (charSequence10 != null) {
            bundle.putCharSequence(FIELD_CONDUCTOR, charSequence10);
        }
        CharSequence charSequence11 = this.genre;
        if (charSequence11 != null) {
            bundle.putCharSequence(FIELD_GENRE, charSequence11);
        }
        CharSequence charSequence12 = this.compilation;
        if (charSequence12 != null) {
            bundle.putCharSequence(FIELD_COMPILATION, charSequence12);
        }
        CharSequence charSequence13 = this.station;
        if (charSequence13 != null) {
            bundle.putCharSequence(FIELD_STATION, charSequence13);
        }
        Rating rating = this.userRating;
        if (rating != null) {
            bundle.putBundle(FIELD_USER_RATING, rating.toBundle());
        }
        Rating rating2 = this.overallRating;
        if (rating2 != null) {
            bundle.putBundle(FIELD_OVERALL_RATING, rating2.toBundle());
        }
        Integer num = this.trackNumber;
        if (num != null) {
            bundle.putInt(FIELD_TRACK_NUMBER, num.intValue());
        }
        Integer num2 = this.totalTrackCount;
        if (num2 != null) {
            bundle.putInt(FIELD_TOTAL_TRACK_COUNT, num2.intValue());
        }
        Integer num3 = this.folderType;
        if (num3 != null) {
            bundle.putInt(FIELD_FOLDER_TYPE, num3.intValue());
        }
        Boolean bool = this.isBrowsable;
        if (bool != null) {
            bundle.putBoolean(FIELD_IS_BROWSABLE, bool.booleanValue());
        }
        Boolean bool2 = this.isPlayable;
        if (bool2 != null) {
            bundle.putBoolean(FIELD_IS_PLAYABLE, bool2.booleanValue());
        }
        Integer num4 = this.recordingYear;
        if (num4 != null) {
            bundle.putInt(FIELD_RECORDING_YEAR, num4.intValue());
        }
        Integer num5 = this.recordingMonth;
        if (num5 != null) {
            bundle.putInt(FIELD_RECORDING_MONTH, num5.intValue());
        }
        Integer num6 = this.recordingDay;
        if (num6 != null) {
            bundle.putInt(FIELD_RECORDING_DAY, num6.intValue());
        }
        Integer num7 = this.releaseYear;
        if (num7 != null) {
            bundle.putInt(FIELD_RELEASE_YEAR, num7.intValue());
        }
        Integer num8 = this.releaseMonth;
        if (num8 != null) {
            bundle.putInt(FIELD_RELEASE_MONTH, num8.intValue());
        }
        Integer num9 = this.releaseDay;
        if (num9 != null) {
            bundle.putInt(FIELD_RELEASE_DAY, num9.intValue());
        }
        Integer num10 = this.discNumber;
        if (num10 != null) {
            bundle.putInt(FIELD_DISC_NUMBER, num10.intValue());
        }
        Integer num11 = this.totalDiscCount;
        if (num11 != null) {
            bundle.putInt(FIELD_TOTAL_DISC_COUNT, num11.intValue());
        }
        Integer num12 = this.artworkDataType;
        if (num12 != null) {
            bundle.putInt(FIELD_ARTWORK_DATA_TYPE, num12.intValue());
        }
        Integer num13 = this.mediaType;
        if (num13 != null) {
            bundle.putInt(FIELD_MEDIA_TYPE, num13.intValue());
        }
        Bundle bundle2 = this.extras;
        if (bundle2 != null) {
            bundle.putBundle(FIELD_EXTRAS, bundle2);
        }
        return bundle;
    }

    /* access modifiers changed from: private */
    public static MediaMetadata fromBundle(Bundle bundle) {
        Bundle bundle2;
        Bundle bundle3;
        Builder builder = new Builder();
        Builder description2 = builder.setTitle(bundle.getCharSequence(FIELD_TITLE)).setArtist(bundle.getCharSequence(FIELD_ARTIST)).setAlbumTitle(bundle.getCharSequence(FIELD_ALBUM_TITLE)).setAlbumArtist(bundle.getCharSequence(FIELD_ALBUM_ARTIST)).setDisplayTitle(bundle.getCharSequence(FIELD_DISPLAY_TITLE)).setSubtitle(bundle.getCharSequence(FIELD_SUBTITLE)).setDescription(bundle.getCharSequence(FIELD_DESCRIPTION));
        byte[] byteArray = bundle.getByteArray(FIELD_ARTWORK_DATA);
        String str = FIELD_ARTWORK_DATA_TYPE;
        description2.setArtworkData(byteArray, bundle.containsKey(str) ? Integer.valueOf(bundle.getInt(str)) : null).setArtworkUri((Uri) bundle.getParcelable(FIELD_ARTWORK_URI)).setWriter(bundle.getCharSequence(FIELD_WRITER)).setComposer(bundle.getCharSequence(FIELD_COMPOSER)).setConductor(bundle.getCharSequence(FIELD_CONDUCTOR)).setGenre(bundle.getCharSequence(FIELD_GENRE)).setCompilation(bundle.getCharSequence(FIELD_COMPILATION)).setStation(bundle.getCharSequence(FIELD_STATION)).setExtras(bundle.getBundle(FIELD_EXTRAS));
        String str2 = FIELD_USER_RATING;
        if (bundle.containsKey(str2) && (bundle3 = bundle.getBundle(str2)) != null) {
            builder.setUserRating(Rating.CREATOR.fromBundle(bundle3));
        }
        String str3 = FIELD_OVERALL_RATING;
        if (bundle.containsKey(str3) && (bundle2 = bundle.getBundle(str3)) != null) {
            builder.setOverallRating(Rating.CREATOR.fromBundle(bundle2));
        }
        String str4 = FIELD_TRACK_NUMBER;
        if (bundle.containsKey(str4)) {
            builder.setTrackNumber(Integer.valueOf(bundle.getInt(str4)));
        }
        String str5 = FIELD_TOTAL_TRACK_COUNT;
        if (bundle.containsKey(str5)) {
            builder.setTotalTrackCount(Integer.valueOf(bundle.getInt(str5)));
        }
        String str6 = FIELD_FOLDER_TYPE;
        if (bundle.containsKey(str6)) {
            builder.setFolderType(Integer.valueOf(bundle.getInt(str6)));
        }
        String str7 = FIELD_IS_BROWSABLE;
        if (bundle.containsKey(str7)) {
            builder.setIsBrowsable(Boolean.valueOf(bundle.getBoolean(str7)));
        }
        String str8 = FIELD_IS_PLAYABLE;
        if (bundle.containsKey(str8)) {
            builder.setIsPlayable(Boolean.valueOf(bundle.getBoolean(str8)));
        }
        String str9 = FIELD_RECORDING_YEAR;
        if (bundle.containsKey(str9)) {
            builder.setRecordingYear(Integer.valueOf(bundle.getInt(str9)));
        }
        String str10 = FIELD_RECORDING_MONTH;
        if (bundle.containsKey(str10)) {
            builder.setRecordingMonth(Integer.valueOf(bundle.getInt(str10)));
        }
        String str11 = FIELD_RECORDING_DAY;
        if (bundle.containsKey(str11)) {
            builder.setRecordingDay(Integer.valueOf(bundle.getInt(str11)));
        }
        String str12 = FIELD_RELEASE_YEAR;
        if (bundle.containsKey(str12)) {
            builder.setReleaseYear(Integer.valueOf(bundle.getInt(str12)));
        }
        String str13 = FIELD_RELEASE_MONTH;
        if (bundle.containsKey(str13)) {
            builder.setReleaseMonth(Integer.valueOf(bundle.getInt(str13)));
        }
        String str14 = FIELD_RELEASE_DAY;
        if (bundle.containsKey(str14)) {
            builder.setReleaseDay(Integer.valueOf(bundle.getInt(str14)));
        }
        String str15 = FIELD_DISC_NUMBER;
        if (bundle.containsKey(str15)) {
            builder.setDiscNumber(Integer.valueOf(bundle.getInt(str15)));
        }
        String str16 = FIELD_TOTAL_DISC_COUNT;
        if (bundle.containsKey(str16)) {
            builder.setTotalDiscCount(Integer.valueOf(bundle.getInt(str16)));
        }
        String str17 = FIELD_MEDIA_TYPE;
        if (bundle.containsKey(str17)) {
            builder.setMediaType(Integer.valueOf(bundle.getInt(str17)));
        }
        return builder.build();
    }
}
