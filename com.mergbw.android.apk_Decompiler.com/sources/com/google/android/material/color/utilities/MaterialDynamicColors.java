package com.google.android.material.color.utilities;

import java.util.function.Function;

public final class MaterialDynamicColors {
    private static final double CONTAINER_ACCENT_TONE_DELTA = 15.0d;

    public DynamicColor highestSurface(DynamicScheme dynamicScheme) {
        return dynamicScheme.isDark ? surfaceBright() : surfaceDim();
    }

    public DynamicColor primaryPaletteKeyColor() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda65(), new MaterialDynamicColors$$ExternalSyntheticLambda66());
    }

    public DynamicColor secondaryPaletteKeyColor() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda71(), new MaterialDynamicColors$$ExternalSyntheticLambda72());
    }

    public DynamicColor tertiaryPaletteKeyColor() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda81(), new MaterialDynamicColors$$ExternalSyntheticLambda82());
    }

    public DynamicColor neutralPaletteKeyColor() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda21(), new MaterialDynamicColors$$ExternalSyntheticLambda32());
    }

    public DynamicColor neutralVariantPaletteKeyColor() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda132(), new MaterialDynamicColors$$ExternalSyntheticLambda133());
    }

    static /* synthetic */ Double lambda$background$11(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 6.0d : 98.0d);
    }

    public DynamicColor background() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda92(), new MaterialDynamicColors$$ExternalSyntheticLambda93());
    }

    public DynamicColor onBackground() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda96(), new MaterialDynamicColors$$ExternalSyntheticLambda98(), new MaterialDynamicColors$$ExternalSyntheticLambda99(this));
    }

    static /* synthetic */ Double lambda$onBackground$13(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 90.0d : 10.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBackground$14$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m559lambda$onBackground$14$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return background();
    }

    static /* synthetic */ Double lambda$surface$16(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 6.0d : 98.0d);
    }

    public DynamicColor surface() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda64(), new MaterialDynamicColors$$ExternalSyntheticLambda75());
    }

    static /* synthetic */ Double lambda$inverseSurface$18(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 90.0d : 20.0d);
    }

    public DynamicColor inverseSurface() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda35(), new MaterialDynamicColors$$ExternalSyntheticLambda36());
    }

    static /* synthetic */ Double lambda$surfaceBright$20(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 24.0d : 98.0d);
    }

    public DynamicColor surfaceBright() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda102(), new MaterialDynamicColors$$ExternalSyntheticLambda103());
    }

    static /* synthetic */ Double lambda$surfaceDim$22(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 6.0d : 87.0d);
    }

    public DynamicColor surfaceDim() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda43(), new MaterialDynamicColors$$ExternalSyntheticLambda54());
    }

    static /* synthetic */ Double lambda$surfaceContainerLowest$24(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 4.0d : 100.0d);
    }

    public DynamicColor surfaceContainerLowest() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda1(), new MaterialDynamicColors$$ExternalSyntheticLambda2());
    }

    static /* synthetic */ Double lambda$surfaceContainerLow$26(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 10.0d : 96.0d);
    }

    public DynamicColor surfaceContainerLow() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda73(), new MaterialDynamicColors$$ExternalSyntheticLambda74());
    }

    static /* synthetic */ Double lambda$surfaceContainer$28(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 12.0d : 94.0d);
    }

    public DynamicColor surfaceContainer() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda29(), new MaterialDynamicColors$$ExternalSyntheticLambda30());
    }

    static /* synthetic */ Double lambda$surfaceContainerHigh$30(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 17.0d : 92.0d);
    }

    public DynamicColor surfaceContainerHigh() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda94(), new MaterialDynamicColors$$ExternalSyntheticLambda95());
    }

    static /* synthetic */ Double lambda$surfaceContainerHighest$32(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 22.0d : 90.0d);
    }

    public DynamicColor surfaceContainerHighest() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda138(), new MaterialDynamicColors$$ExternalSyntheticLambda139());
    }

    public DynamicColor onSurface() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda141(), new MaterialDynamicColors$$ExternalSyntheticLambda152(), new MaterialDynamicColors$$ExternalSyntheticLambda10(this));
    }

    static /* synthetic */ Double lambda$onSurface$34(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 90.0d : 10.0d);
    }

    public DynamicColor inverseOnSurface() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda11(), new MaterialDynamicColors$$ExternalSyntheticLambda12(), new MaterialDynamicColors$$ExternalSyntheticLambda13(this));
    }

    static /* synthetic */ Double lambda$inverseOnSurface$36(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 20.0d : 95.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$inverseOnSurface$37$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m557lambda$inverseOnSurface$37$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return inverseSurface();
    }

    static /* synthetic */ Double lambda$surfaceVariant$39(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 30.0d : 90.0d);
    }

    public DynamicColor surfaceVariant() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda129(), new MaterialDynamicColors$$ExternalSyntheticLambda131());
    }

    public DynamicColor onSurfaceVariant() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda31(), new MaterialDynamicColors$$ExternalSyntheticLambda33(), new MaterialDynamicColors$$ExternalSyntheticLambda34(this));
    }

    static /* synthetic */ Double lambda$onSurfaceVariant$41(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 80.0d : 30.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSurfaceVariant$42$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m572lambda$onSurfaceVariant$42$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return surfaceVariant();
    }

    public DynamicColor outline() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda3(), new MaterialDynamicColors$$ExternalSyntheticLambda4(), new MaterialDynamicColors$$ExternalSyntheticLambda10(this));
    }

    static /* synthetic */ Double lambda$outline$44(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 60.0d : 50.0d);
    }

    public DynamicColor outlineVariant() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda100(), new MaterialDynamicColors$$ExternalSyntheticLambda101(), new MaterialDynamicColors$$ExternalSyntheticLambda10(this));
    }

    static /* synthetic */ Double lambda$outlineVariant$46(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 30.0d : 80.0d);
    }

    public DynamicColor shadow() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda136(), new MaterialDynamicColors$$ExternalSyntheticLambda137());
    }

    public DynamicColor scrim() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda50(), new MaterialDynamicColors$$ExternalSyntheticLambda51());
    }

    static /* synthetic */ Double lambda$surfaceTint$52(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 80.0d : 40.0d);
    }

    public DynamicColor surfaceTint() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda5(), new MaterialDynamicColors$$ExternalSyntheticLambda6());
    }

    public DynamicColor primaryContainer() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda90(), new MaterialDynamicColors$$ExternalSyntheticLambda91(), new MaterialDynamicColors$$ExternalSyntheticLambda10(this));
    }

    static /* synthetic */ Double lambda$primaryContainer$54(DynamicScheme dynamicScheme) {
        if (isFidelity(dynamicScheme)) {
            return Double.valueOf(performAlbers(dynamicScheme.sourceColorHct, dynamicScheme));
        }
        if (isMonochrome(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.isDark ? 85.0d : 25.0d);
        }
        return Double.valueOf(dynamicScheme.isDark ? 30.0d : 90.0d);
    }

    public DynamicColor onPrimaryContainer() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda124(), new MaterialDynamicColors$$ExternalSyntheticLambda125(this), new MaterialDynamicColors$$ExternalSyntheticLambda126(this), (Function<DynamicScheme, ToneDeltaConstraint>) null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onPrimaryContainer$56$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ Double m563lambda$onPrimaryContainer$56$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        if (isFidelity(dynamicScheme)) {
            return Double.valueOf(DynamicColor.contrastingTone(((Double) primaryContainer().tone.apply(dynamicScheme)).doubleValue(), 4.5d));
        }
        if (isMonochrome(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.isDark ? 0.0d : 100.0d);
        }
        return Double.valueOf(dynamicScheme.isDark ? 90.0d : 10.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onPrimaryContainer$57$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m564lambda$onPrimaryContainer$57$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return primaryContainer();
    }

    public DynamicColor primary() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda47(), new MaterialDynamicColors$$ExternalSyntheticLambda48(), new MaterialDynamicColors$$ExternalSyntheticLambda10(this), new MaterialDynamicColors$$ExternalSyntheticLambda49(this));
    }

    static /* synthetic */ Double lambda$primary$59(DynamicScheme dynamicScheme) {
        if (isMonochrome(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.isDark ? 100.0d : 0.0d);
        }
        return Double.valueOf(dynamicScheme.isDark ? 80.0d : 40.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$primary$60$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ ToneDeltaConstraint m578lambda$primary$60$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return new ToneDeltaConstraint(CONTAINER_ACCENT_TONE_DELTA, primaryContainer(), dynamicScheme.isDark ? TonePolarity.DARKER : TonePolarity.LIGHTER);
    }

    public DynamicColor inversePrimary() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda107(), new MaterialDynamicColors$$ExternalSyntheticLambda109(), new MaterialDynamicColors$$ExternalSyntheticLambda110(this));
    }

    static /* synthetic */ Double lambda$inversePrimary$62(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 40.0d : 80.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$inversePrimary$63$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m558lambda$inversePrimary$63$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return inverseSurface();
    }

    public DynamicColor onPrimary() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda104(), new MaterialDynamicColors$$ExternalSyntheticLambda105(), new MaterialDynamicColors$$ExternalSyntheticLambda106(this));
    }

    static /* synthetic */ Double lambda$onPrimary$65(DynamicScheme dynamicScheme) {
        if (isMonochrome(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.isDark ? 10.0d : 90.0d);
        }
        return Double.valueOf(dynamicScheme.isDark ? 20.0d : 100.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onPrimary$66$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m562lambda$onPrimary$66$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return primary();
    }

    public DynamicColor secondaryContainer() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda76(), new MaterialDynamicColors$$ExternalSyntheticLambda77(), new MaterialDynamicColors$$ExternalSyntheticLambda10(this));
    }

    static /* synthetic */ Double lambda$secondaryContainer$68(DynamicScheme dynamicScheme) {
        double d = 30.0d;
        if (isMonochrome(dynamicScheme)) {
            if (!dynamicScheme.isDark) {
                d = 85.0d;
            }
            return Double.valueOf(d);
        }
        if (!dynamicScheme.isDark) {
            d = 90.0d;
        }
        double d2 = d;
        if (!isFidelity(dynamicScheme)) {
            return Double.valueOf(d2);
        }
        return Double.valueOf(performAlbers(dynamicScheme.secondaryPalette.getHct(findDesiredChromaByTone(dynamicScheme.secondaryPalette.getHue(), dynamicScheme.secondaryPalette.getChroma(), d2, !dynamicScheme.isDark)), dynamicScheme));
    }

    public DynamicColor onSecondaryContainer() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda17(), new MaterialDynamicColors$$ExternalSyntheticLambda18(this), new MaterialDynamicColors$$ExternalSyntheticLambda19(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSecondaryContainer$70$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ Double m568lambda$onSecondaryContainer$70$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        if (isFidelity(dynamicScheme)) {
            return Double.valueOf(DynamicColor.contrastingTone(((Double) secondaryContainer().tone.apply(dynamicScheme)).doubleValue(), 4.5d));
        }
        return Double.valueOf(dynamicScheme.isDark ? 90.0d : 10.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSecondaryContainer$71$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m569lambda$onSecondaryContainer$71$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return secondaryContainer();
    }

    public DynamicColor secondary() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda150(), new MaterialDynamicColors$$ExternalSyntheticLambda151(), new MaterialDynamicColors$$ExternalSyntheticLambda10(this), new MaterialDynamicColors$$ExternalSyntheticLambda0(this));
    }

    static /* synthetic */ Double lambda$secondary$73(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 80.0d : 40.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$secondary$74$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ ToneDeltaConstraint m579lambda$secondary$74$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return new ToneDeltaConstraint(CONTAINER_ACCENT_TONE_DELTA, secondaryContainer(), dynamicScheme.isDark ? TonePolarity.DARKER : TonePolarity.LIGHTER);
    }

    public DynamicColor onSecondary() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda147(), new MaterialDynamicColors$$ExternalSyntheticLambda148(), new MaterialDynamicColors$$ExternalSyntheticLambda149(this));
    }

    static /* synthetic */ Double lambda$onSecondary$76(DynamicScheme dynamicScheme) {
        double d = 100.0d;
        if (isMonochrome(dynamicScheme)) {
            if (dynamicScheme.isDark) {
                d = 10.0d;
            }
            return Double.valueOf(d);
        }
        if (dynamicScheme.isDark) {
            d = 20.0d;
        }
        return Double.valueOf(d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSecondary$77$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m567lambda$onSecondary$77$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return secondary();
    }

    public DynamicColor tertiaryContainer() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda145(), new MaterialDynamicColors$$ExternalSyntheticLambda146(), new MaterialDynamicColors$$ExternalSyntheticLambda10(this));
    }

    static /* synthetic */ Double lambda$tertiaryContainer$79(DynamicScheme dynamicScheme) {
        if (isMonochrome(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.isDark ? 60.0d : 49.0d);
        } else if (!isFidelity(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.isDark ? 30.0d : 90.0d);
        } else {
            return Double.valueOf(DislikeAnalyzer.fixIfDisliked(dynamicScheme.tertiaryPalette.getHct(performAlbers(dynamicScheme.tertiaryPalette.getHct(dynamicScheme.sourceColorHct.getTone()), dynamicScheme))).getTone());
        }
    }

    public DynamicColor onTertiaryContainer() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda14(), new MaterialDynamicColors$$ExternalSyntheticLambda15(this), new MaterialDynamicColors$$ExternalSyntheticLambda16(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onTertiaryContainer$81$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ Double m574lambda$onTertiaryContainer$81$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        if (isMonochrome(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.isDark ? 0.0d : 100.0d);
        } else if (isFidelity(dynamicScheme)) {
            return Double.valueOf(DynamicColor.contrastingTone(((Double) tertiaryContainer().tone.apply(dynamicScheme)).doubleValue(), 4.5d));
        } else {
            return Double.valueOf(dynamicScheme.isDark ? 90.0d : 10.0d);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onTertiaryContainer$82$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m575lambda$onTertiaryContainer$82$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return tertiaryContainer();
    }

    public DynamicColor tertiary() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda56(), new MaterialDynamicColors$$ExternalSyntheticLambda57(), new MaterialDynamicColors$$ExternalSyntheticLambda10(this), new MaterialDynamicColors$$ExternalSyntheticLambda58(this));
    }

    static /* synthetic */ Double lambda$tertiary$84(DynamicScheme dynamicScheme) {
        if (isMonochrome(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.isDark ? 90.0d : 25.0d);
        }
        return Double.valueOf(dynamicScheme.isDark ? 80.0d : 40.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$tertiary$85$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ ToneDeltaConstraint m580lambda$tertiary$85$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return new ToneDeltaConstraint(CONTAINER_ACCENT_TONE_DELTA, tertiaryContainer(), dynamicScheme.isDark ? TonePolarity.DARKER : TonePolarity.LIGHTER);
    }

    public DynamicColor onTertiary() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda108(), new MaterialDynamicColors$$ExternalSyntheticLambda119(), new MaterialDynamicColors$$ExternalSyntheticLambda130(this));
    }

    static /* synthetic */ Double lambda$onTertiary$87(DynamicScheme dynamicScheme) {
        if (isMonochrome(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.isDark ? 10.0d : 90.0d);
        }
        return Double.valueOf(dynamicScheme.isDark ? 20.0d : 100.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onTertiary$88$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m573lambda$onTertiary$88$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return tertiary();
    }

    public DynamicColor errorContainer() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda42(), new MaterialDynamicColors$$ExternalSyntheticLambda44(), new MaterialDynamicColors$$ExternalSyntheticLambda10(this));
    }

    static /* synthetic */ Double lambda$errorContainer$90(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 30.0d : 90.0d);
    }

    public DynamicColor onErrorContainer() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda37(), new MaterialDynamicColors$$ExternalSyntheticLambda38(), new MaterialDynamicColors$$ExternalSyntheticLambda39(this));
    }

    static /* synthetic */ Double lambda$onErrorContainer$92(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 90.0d : 10.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onErrorContainer$93$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m561lambda$onErrorContainer$93$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return errorContainer();
    }

    public DynamicColor error() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda24(), new MaterialDynamicColors$$ExternalSyntheticLambda25(), new MaterialDynamicColors$$ExternalSyntheticLambda10(this), new MaterialDynamicColors$$ExternalSyntheticLambda26(this));
    }

    static /* synthetic */ Double lambda$error$95(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 80.0d : 40.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$error$96$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ ToneDeltaConstraint m556lambda$error$96$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return new ToneDeltaConstraint(CONTAINER_ACCENT_TONE_DELTA, errorContainer(), dynamicScheme.isDark ? TonePolarity.DARKER : TonePolarity.LIGHTER);
    }

    public DynamicColor onError() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda118(), new MaterialDynamicColors$$ExternalSyntheticLambda120(), new MaterialDynamicColors$$ExternalSyntheticLambda121(this));
    }

    static /* synthetic */ Double lambda$onError$98(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 20.0d : 100.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onError$99$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m560lambda$onError$99$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return error();
    }

    public DynamicColor primaryFixed() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda140(), new MaterialDynamicColors$$ExternalSyntheticLambda142(), new MaterialDynamicColors$$ExternalSyntheticLambda10(this));
    }

    static /* synthetic */ Double lambda$primaryFixed$101(DynamicScheme dynamicScheme) {
        if (!isMonochrome(dynamicScheme)) {
            return Double.valueOf(90.0d);
        }
        return Double.valueOf(dynamicScheme.isDark ? 100.0d : 10.0d);
    }

    public DynamicColor primaryFixedDim() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda143(), new MaterialDynamicColors$$ExternalSyntheticLambda144(), new MaterialDynamicColors$$ExternalSyntheticLambda10(this));
    }

    static /* synthetic */ Double lambda$primaryFixedDim$103(DynamicScheme dynamicScheme) {
        if (!isMonochrome(dynamicScheme)) {
            return Double.valueOf(80.0d);
        }
        return Double.valueOf(dynamicScheme.isDark ? 90.0d : 20.0d);
    }

    public DynamicColor onPrimaryFixed() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda20(), new MaterialDynamicColors$$ExternalSyntheticLambda22(), new MaterialDynamicColors$$ExternalSyntheticLambda23(this));
    }

    static /* synthetic */ Double lambda$onPrimaryFixed$105(DynamicScheme dynamicScheme) {
        double d = 10.0d;
        if (!isMonochrome(dynamicScheme)) {
            return Double.valueOf(10.0d);
        }
        if (!dynamicScheme.isDark) {
            d = 90.0d;
        }
        return Double.valueOf(d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onPrimaryFixed$106$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m565lambda$onPrimaryFixed$106$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return primaryFixedDim();
    }

    public DynamicColor onPrimaryFixedVariant() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda113(), new MaterialDynamicColors$$ExternalSyntheticLambda114(), new MaterialDynamicColors$$ExternalSyntheticLambda115(this));
    }

    static /* synthetic */ Double lambda$onPrimaryFixedVariant$108(DynamicScheme dynamicScheme) {
        double d = 30.0d;
        if (!isMonochrome(dynamicScheme)) {
            return Double.valueOf(30.0d);
        }
        if (!dynamicScheme.isDark) {
            d = 70.0d;
        }
        return Double.valueOf(d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onPrimaryFixedVariant$109$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m566lambda$onPrimaryFixedVariant$109$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return primaryFixedDim();
    }

    public DynamicColor secondaryFixed() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda111(), new MaterialDynamicColors$$ExternalSyntheticLambda112(), new MaterialDynamicColors$$ExternalSyntheticLambda10(this));
    }

    static /* synthetic */ Double lambda$secondaryFixed$111(DynamicScheme dynamicScheme) {
        return Double.valueOf(isMonochrome(dynamicScheme) ? 80.0d : 90.0d);
    }

    public DynamicColor secondaryFixedDim() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda127(), new MaterialDynamicColors$$ExternalSyntheticLambda128(), new MaterialDynamicColors$$ExternalSyntheticLambda10(this));
    }

    static /* synthetic */ Double lambda$secondaryFixedDim$113(DynamicScheme dynamicScheme) {
        return Double.valueOf(isMonochrome(dynamicScheme) ? 70.0d : 80.0d);
    }

    public DynamicColor onSecondaryFixed() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda7(), new MaterialDynamicColors$$ExternalSyntheticLambda8(), new MaterialDynamicColors$$ExternalSyntheticLambda9(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSecondaryFixed$116$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m570lambda$onSecondaryFixed$116$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return secondaryFixedDim();
    }

    public DynamicColor onSecondaryFixedVariant() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda52(), new MaterialDynamicColors$$ExternalSyntheticLambda53(), new MaterialDynamicColors$$ExternalSyntheticLambda55(this));
    }

    static /* synthetic */ Double lambda$onSecondaryFixedVariant$118(DynamicScheme dynamicScheme) {
        return Double.valueOf(isMonochrome(dynamicScheme) ? 25.0d : 30.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSecondaryFixedVariant$119$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m571lambda$onSecondaryFixedVariant$119$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return secondaryFixedDim();
    }

    public DynamicColor tertiaryFixed() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda45(), new MaterialDynamicColors$$ExternalSyntheticLambda46(), new MaterialDynamicColors$$ExternalSyntheticLambda10(this));
    }

    static /* synthetic */ Double lambda$tertiaryFixed$121(DynamicScheme dynamicScheme) {
        return Double.valueOf(isMonochrome(dynamicScheme) ? 40.0d : 90.0d);
    }

    public DynamicColor tertiaryFixedDim() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda116(), new MaterialDynamicColors$$ExternalSyntheticLambda117(), new MaterialDynamicColors$$ExternalSyntheticLambda10(this));
    }

    static /* synthetic */ Double lambda$tertiaryFixedDim$123(DynamicScheme dynamicScheme) {
        return Double.valueOf(isMonochrome(dynamicScheme) ? 30.0d : 80.0d);
    }

    public DynamicColor onTertiaryFixed() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda78(), new MaterialDynamicColors$$ExternalSyntheticLambda79(), new MaterialDynamicColors$$ExternalSyntheticLambda80(this));
    }

    static /* synthetic */ Double lambda$onTertiaryFixed$125(DynamicScheme dynamicScheme) {
        return Double.valueOf(isMonochrome(dynamicScheme) ? 90.0d : 10.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onTertiaryFixed$126$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m576lambda$onTertiaryFixed$126$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return tertiaryFixedDim();
    }

    public DynamicColor onTertiaryFixedVariant() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda68(), new MaterialDynamicColors$$ExternalSyntheticLambda69(), new MaterialDynamicColors$$ExternalSyntheticLambda70(this));
    }

    static /* synthetic */ Double lambda$onTertiaryFixedVariant$128(DynamicScheme dynamicScheme) {
        return Double.valueOf(isMonochrome(dynamicScheme) ? 70.0d : 30.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onTertiaryFixedVariant$129$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m577lambda$onTertiaryFixedVariant$129$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return tertiaryFixedDim();
    }

    static /* synthetic */ Double lambda$controlActivated$131(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 30.0d : 90.0d);
    }

    public DynamicColor controlActivated() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda62(), new MaterialDynamicColors$$ExternalSyntheticLambda63(), (Function<DynamicScheme, DynamicColor>) null);
    }

    static /* synthetic */ Double lambda$controlNormal$133(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 80.0d : 30.0d);
    }

    public DynamicColor controlNormal() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda60(), new MaterialDynamicColors$$ExternalSyntheticLambda61());
    }

    public DynamicColor controlHighlight() {
        return new DynamicColor(new MaterialDynamicColors$$ExternalSyntheticLambda83(), new MaterialDynamicColors$$ExternalSyntheticLambda84(), new MaterialDynamicColors$$ExternalSyntheticLambda85(), new MaterialDynamicColors$$ExternalSyntheticLambda87(), (Function<DynamicScheme, DynamicColor>) null, new MaterialDynamicColors$$ExternalSyntheticLambda88(), new MaterialDynamicColors$$ExternalSyntheticLambda89(), (Function<DynamicScheme, ToneDeltaConstraint>) null);
    }

    static /* synthetic */ Double lambda$controlHighlight$136(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 100.0d : 0.0d);
    }

    static /* synthetic */ Double lambda$controlHighlight$137(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 0.2d : 0.12d);
    }

    static /* synthetic */ Double lambda$controlHighlight$138(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 100.0d : 0.0d);
    }

    static /* synthetic */ Double lambda$controlHighlight$140(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 100.0d : 0.0d);
    }

    static /* synthetic */ Double lambda$textPrimaryInverse$143(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 10.0d : 90.0d);
    }

    public DynamicColor textPrimaryInverse() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda27(), new MaterialDynamicColors$$ExternalSyntheticLambda28());
    }

    static /* synthetic */ Double lambda$textSecondaryAndTertiaryInverse$145(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 30.0d : 80.0d);
    }

    public DynamicColor textSecondaryAndTertiaryInverse() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda40(), new MaterialDynamicColors$$ExternalSyntheticLambda41());
    }

    static /* synthetic */ Double lambda$textPrimaryInverseDisableOnly$147(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 10.0d : 90.0d);
    }

    public DynamicColor textPrimaryInverseDisableOnly() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda122(), new MaterialDynamicColors$$ExternalSyntheticLambda123());
    }

    static /* synthetic */ Double lambda$textSecondaryAndTertiaryInverseDisabled$149(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 10.0d : 90.0d);
    }

    public DynamicColor textSecondaryAndTertiaryInverseDisabled() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda86(), new MaterialDynamicColors$$ExternalSyntheticLambda97());
    }

    static /* synthetic */ Double lambda$textHintInverse$151(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 10.0d : 90.0d);
    }

    public DynamicColor textHintInverse() {
        return DynamicColor.fromPalette(new MaterialDynamicColors$$ExternalSyntheticLambda134(), new MaterialDynamicColors$$ExternalSyntheticLambda135());
    }

    private static ViewingConditions viewingConditionsForAlbers(DynamicScheme dynamicScheme) {
        return ViewingConditions.defaultWithBackgroundLstar(dynamicScheme.isDark ? 30.0d : 80.0d);
    }

    private static boolean isFidelity(DynamicScheme dynamicScheme) {
        return dynamicScheme.variant == Variant.FIDELITY || dynamicScheme.variant == Variant.CONTENT;
    }

    private static boolean isMonochrome(DynamicScheme dynamicScheme) {
        return dynamicScheme.variant == Variant.MONOCHROME;
    }

    static double findDesiredChromaByTone(double d, double d2, double d3, boolean z) {
        Hct from = Hct.from(d, d2, d3);
        if (from.getChroma() >= d2) {
            return d3;
        }
        Hct hct = from;
        double chroma = from.getChroma();
        double d4 = d3;
        while (hct.getChroma() < d2) {
            double d5 = d4 + (z ? -1.0d : 1.0d);
            Hct from2 = Hct.from(d, d2, d5);
            if (chroma > from2.getChroma() || Math.abs(from2.getChroma() - d2) < 0.4d) {
                return d5;
            }
            if (Math.abs(from2.getChroma() - d2) < Math.abs(hct.getChroma() - d2)) {
                hct = from2;
            }
            chroma = Math.max(chroma, from2.getChroma());
            d4 = d5;
        }
        return d4;
    }

    static double performAlbers(Hct hct, DynamicScheme dynamicScheme) {
        Hct inViewingConditions = hct.inViewingConditions(viewingConditionsForAlbers(dynamicScheme));
        if (!DynamicColor.tonePrefersLightForeground(hct.getTone()) || DynamicColor.toneAllowsLightForeground(inViewingConditions.getTone())) {
            return DynamicColor.enableLightForeground(inViewingConditions.getTone());
        }
        return DynamicColor.enableLightForeground(hct.getTone());
    }
}
