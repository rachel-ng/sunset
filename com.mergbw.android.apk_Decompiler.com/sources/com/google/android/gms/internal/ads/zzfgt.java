package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.client.zzs;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfgt {
    public final String zzA;
    public final zzcac zzB;
    public final String zzC;
    public final JSONObject zzD;
    public final JSONObject zzE;
    public final String zzF;
    public final String zzG;
    public final String zzH;
    public final String zzI;
    public final String zzJ;
    public final boolean zzK;
    public final boolean zzL;
    public final boolean zzM;
    public final boolean zzN;
    public final boolean zzO;
    public final boolean zzP;
    public final boolean zzQ;
    public final int zzR;
    public final int zzS;
    public final boolean zzT;
    public final boolean zzU;
    public final String zzV;
    public final zzfhr zzW;
    public final boolean zzX;
    public final boolean zzY;
    public final int zzZ;
    public final List zza;
    public final String zzaa;
    public final int zzab;
    public final String zzac;
    public final boolean zzad;
    public final zzbvm zzae;
    public final zzs zzaf;
    public final String zzag;
    public final boolean zzah;
    public final JSONObject zzai;
    public final boolean zzaj;
    public final JSONObject zzak;
    public final boolean zzal;
    public final String zzam;
    public final boolean zzan;
    public final String zzao;
    public final String zzap;
    public final String zzaq;
    public final boolean zzar;
    public final boolean zzas;
    public final int zzat;
    public final String zzau;
    public final List zzav;
    public final boolean zzaw;
    public final Map zzax;
    public final int zzb;
    public final List zzc;
    public final List zzd;
    public final List zze;
    public final int zzf;
    public final List zzg;
    public final List zzh;
    public final List zzi;
    public final List zzj;
    public final String zzk;
    public final String zzl;
    public final zzbyt zzm;
    public final List zzn;
    public final List zzo;
    public final List zzp;
    public final List zzq;
    public final int zzr;
    public final List zzs;
    public final zzfgy zzt;
    public final List zzu;
    public final List zzv;
    public final JSONObject zzw;
    public final String zzx;
    public final String zzy;
    public final String zzz;

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:286:0x077b, code lost:
        r10 = r84;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:337:0x08e6, code lost:
        r10 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:338:0x08e8, code lost:
        r9 = r26;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    zzfgt(android.util.JsonReader r84) throws java.lang.IllegalStateException, java.io.IOException, org.json.JSONException, java.lang.NumberFormatException {
        /*
            r83 = this;
            r0 = r83
            r83.<init>()
            java.util.List r1 = java.util.Collections.emptyList()
            java.util.List r2 = java.util.Collections.emptyList()
            java.util.List r3 = java.util.Collections.emptyList()
            java.util.List r4 = java.util.Collections.emptyList()
            java.util.List r5 = java.util.Collections.emptyList()
            java.util.List r6 = java.util.Collections.emptyList()
            java.util.List r7 = java.util.Collections.emptyList()
            java.util.List r8 = java.util.Collections.emptyList()
            java.util.List r9 = java.util.Collections.emptyList()
            java.util.List r10 = java.util.Collections.emptyList()
            java.util.List r11 = java.util.Collections.emptyList()
            java.util.List r12 = java.util.Collections.emptyList()
            java.util.List r13 = java.util.Collections.emptyList()
            java.util.List r14 = java.util.Collections.emptyList()
            org.json.JSONObject r15 = new org.json.JSONObject
            r15.<init>()
            org.json.JSONObject r16 = new org.json.JSONObject
            r16.<init>()
            org.json.JSONObject r17 = new org.json.JSONObject
            r17.<init>()
            org.json.JSONObject r18 = new org.json.JSONObject
            r18.<init>()
            org.json.JSONObject r19 = new org.json.JSONObject
            r19.<init>()
            org.json.JSONObject r20 = new org.json.JSONObject
            r20.<init>()
            com.google.android.gms.internal.ads.zzgbc r21 = com.google.android.gms.internal.ads.zzgbc.zzm()
            com.google.android.gms.internal.ads.zzgbc r22 = com.google.android.gms.internal.ads.zzgbc.zzm()
            java.util.HashMap r23 = new java.util.HashMap
            r23.<init>()
            r84.beginObject()
            r24 = 0
            java.lang.String r25 = ""
            r26 = 0
            r27 = -1
            r28 = r16
            r29 = r17
            r30 = r18
            r31 = r19
            r32 = r20
            r33 = r21
            r34 = r22
            r35 = r23
            r40 = r24
            r46 = r40
            r47 = r46
            r48 = r47
            r49 = r48
            r50 = r49
            r51 = r50
            r52 = r51
            r54 = r52
            r55 = r54
            r57 = r55
            r58 = r57
            r59 = r58
            r63 = r59
            r65 = r63
            r71 = r65
            r72 = r71
            r73 = r72
            r74 = r73
            r78 = r74
            r79 = r78
            r80 = r79
            r82 = r80
            r41 = r25
            r42 = r41
            r43 = r42
            r44 = r43
            r45 = r44
            r56 = r45
            r60 = r56
            r62 = r60
            r64 = r62
            r66 = r64
            r67 = r66
            r68 = r67
            r69 = r68
            r70 = r69
            r75 = r70
            r76 = r75
            r77 = r76
            r81 = r77
            r19 = r26
            r36 = r19
            r37 = r36
            r38 = r37
            r39 = r38
            r53 = r27
            r61 = r53
            r21 = r11
            r20 = r12
            r18 = r13
            r17 = r14
            r16 = r15
            r13 = r82
            r14 = r13
            r11 = r81
            r12 = r11
            r15 = r39
        L_0x00f5:
            boolean r22 = r84.hasNext()
            if (r22 == 0) goto L_0x08ec
            java.lang.String r22 = r84.nextName()
            if (r22 != 0) goto L_0x0104
            r23 = r25
            goto L_0x0106
        L_0x0104:
            r23 = r22
        L_0x0106:
            int r22 = r23.hashCode()
            switch(r22) {
                case -2138196627: goto L_0x065f;
                case -1980587809: goto L_0x064e;
                case -1965512151: goto L_0x063d;
                case -1871425831: goto L_0x062c;
                case -1843156475: goto L_0x061b;
                case -1812055556: goto L_0x060a;
                case -1785028569: goto L_0x05f8;
                case -1776946669: goto L_0x05e6;
                case -1662989631: goto L_0x05d4;
                case -1620470467: goto L_0x05c2;
                case -1550155393: goto L_0x05b0;
                case -1440104884: goto L_0x059e;
                case -1439500848: goto L_0x058c;
                case -1428969291: goto L_0x057a;
                case -1406227629: goto L_0x0568;
                case -1403779768: goto L_0x0556;
                case -1375413093: goto L_0x0544;
                case -1360811658: goto L_0x0532;
                case -1306015996: goto L_0x0520;
                case -1303332046: goto L_0x050e;
                case -1289032093: goto L_0x04fc;
                case -1240082064: goto L_0x04ea;
                case -1234181075: goto L_0x04d8;
                case -1168140544: goto L_0x04c6;
                case -1152230954: goto L_0x04b5;
                case -1146534047: goto L_0x04a3;
                case -1115838944: goto L_0x0491;
                case -1081936678: goto L_0x047f;
                case -1078050970: goto L_0x046d;
                case -1051269058: goto L_0x045b;
                case -982608540: goto L_0x0449;
                case -972056451: goto L_0x0437;
                case -776859333: goto L_0x0426;
                case -570101180: goto L_0x0414;
                case -544216775: goto L_0x0402;
                case -437057161: goto L_0x03f1;
                case -404433734: goto L_0x03df;
                case -404326515: goto L_0x03cd;
                case -397704715: goto L_0x03bb;
                case -388807511: goto L_0x03a9;
                case -369773488: goto L_0x0397;
                case -213449460: goto L_0x0385;
                case -213424028: goto L_0x0373;
                case -180214626: goto L_0x0361;
                case -154616268: goto L_0x034f;
                case -29338502: goto L_0x033d;
                case 3107: goto L_0x032b;
                case 3355: goto L_0x0319;
                case 3076010: goto L_0x0307;
                case 37109963: goto L_0x02f5;
                case 63195984: goto L_0x02e3;
                case 107433883: goto L_0x02d1;
                case 230323073: goto L_0x02c0;
                case 418392395: goto L_0x02ae;
                case 542250332: goto L_0x029c;
                case 549176928: goto L_0x028a;
                case 597473788: goto L_0x0278;
                case 754887508: goto L_0x0266;
                case 791122864: goto L_0x0255;
                case 805095541: goto L_0x0243;
                case 1010584092: goto L_0x0231;
                case 1100650276: goto L_0x021f;
                case 1141602460: goto L_0x020d;
                case 1186014765: goto L_0x01fb;
                case 1321720943: goto L_0x01e9;
                case 1422388341: goto L_0x01d7;
                case 1437255331: goto L_0x01c5;
                case 1637553475: goto L_0x01b3;
                case 1638957285: goto L_0x01a2;
                case 1686319423: goto L_0x0190;
                case 1688341040: goto L_0x017f;
                case 1799285870: goto L_0x016d;
                case 1839650832: goto L_0x015b;
                case 1875425491: goto L_0x0149;
                case 2068142375: goto L_0x0137;
                case 2072888499: goto L_0x0125;
                case 2075506442: goto L_0x0113;
                default: goto L_0x010d;
            }
        L_0x010d:
            r26 = r9
            r22 = r10
            goto L_0x0670
        L_0x0113:
            r22 = r10
            java.lang.String r10 = "render_serially"
            r26 = r9
            r9 = r23
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 75
            goto L_0x0672
        L_0x0125:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "manual_tracking_urls"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 15
            goto L_0x0672
        L_0x0137:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "rule_line_external_id"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 52
            goto L_0x0672
        L_0x0149:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "is_analytics_logging_enabled"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 42
            goto L_0x0672
        L_0x015b:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "renderers"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = r24
            goto L_0x0672
        L_0x016d:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "use_third_party_container_height"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 48
            goto L_0x0672
        L_0x017f:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "video_reward_urls"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 7
            goto L_0x0672
        L_0x0190:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "ad_network_class_name"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 55
            goto L_0x0672
        L_0x01a2:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "video_start_urls"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 6
            goto L_0x0672
        L_0x01b3:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "bid_response"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 40
            goto L_0x0672
        L_0x01c5:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "ad_source_id"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 58
            goto L_0x0672
        L_0x01d7:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "is_collapsible"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 70
            goto L_0x0672
        L_0x01e9:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "allow_pub_owned_ad_view"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 31
            goto L_0x0672
        L_0x01fb:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "cache_hit_urls"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 66
            goto L_0x0672
        L_0x020d:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "adapter_response_info_key"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 56
            goto L_0x0672
        L_0x021f:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "rewards"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 11
            goto L_0x0672
        L_0x0231:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "transaction_id"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 9
            goto L_0x0672
        L_0x0243:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "analytics_event_name_to_parameters_map"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 76
            goto L_0x0672
        L_0x0255:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "impression_type"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 5
            goto L_0x0672
        L_0x0266:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "container_sizes"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 17
            goto L_0x0672
        L_0x0278:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "debug_dialog_string"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 27
            goto L_0x0672
        L_0x028a:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "presentation_error_timeout_ms"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 16
            goto L_0x0672
        L_0x029c:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "consent_form_action_identifier"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 72
            goto L_0x0672
        L_0x02ae:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "is_closable_area_disabled"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 36
            goto L_0x0672
        L_0x02c0:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "ad_load_urls"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 4
            goto L_0x0672
        L_0x02d1:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "qdata"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 24
            goto L_0x0672
        L_0x02e3:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "render_test_label"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 33
            goto L_0x0672
        L_0x02f5:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "request_id"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 68
            goto L_0x0672
        L_0x0307:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "data"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 22
            goto L_0x0672
        L_0x0319:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "id"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 23
            goto L_0x0672
        L_0x032b:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "ad"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 18
            goto L_0x0672
        L_0x033d:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "allow_custom_click_gesture"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 32
            goto L_0x0672
        L_0x034f:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "is_offline_ad"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 61
            goto L_0x0672
        L_0x0361:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "native_required_asset_viewability"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 63
            goto L_0x0672
        L_0x0373:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "watermark"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 46
            goto L_0x0672
        L_0x0385:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "force_disable_hardware_acceleration"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 65
            goto L_0x0672
        L_0x0397:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "is_close_button_enabled"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 50
            goto L_0x0672
        L_0x03a9:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "content_url"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 64
            goto L_0x0672
        L_0x03bb:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "ad_close_time_ms"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 45
            goto L_0x0672
        L_0x03cd:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "render_timeout_ms"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 38
            goto L_0x0672
        L_0x03df:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "rtb_native_required_assets"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 62
            goto L_0x0672
        L_0x03f1:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "imp_urls"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 3
            goto L_0x0672
        L_0x0402:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "safe_browsing"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 26
            goto L_0x0672
        L_0x0414:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "late_load_urls"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 74
            goto L_0x0672
        L_0x0426:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "click_urls"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 2
            goto L_0x0672
        L_0x0437:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "ad_source_instance_id"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 60
            goto L_0x0672
        L_0x0449:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "valid_from_timestamp"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 10
            goto L_0x0672
        L_0x045b:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "active_view"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 25
            goto L_0x0672
        L_0x046d:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "video_complete_urls"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 8
            goto L_0x0672
        L_0x047f:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "allocation_id"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 21
            goto L_0x0672
        L_0x0491:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "fill_urls"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 12
            goto L_0x0672
        L_0x04a3:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "is_scroll_aware"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 43
            goto L_0x0672
        L_0x04b5:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "ad_type"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 1
            goto L_0x0672
        L_0x04c6:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "presentation_error_urls"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 14
            goto L_0x0672
        L_0x04d8:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "allow_pub_rendered_attribution"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 30
            goto L_0x0672
        L_0x04ea:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "ad_event_value"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 51
            goto L_0x0672
        L_0x04fc:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "extras"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 29
            goto L_0x0672
        L_0x050e:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "test_mode_enabled"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 34
            goto L_0x0672
        L_0x0520:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "adapters"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 20
            goto L_0x0672
        L_0x0532:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "ad_sizes"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 19
            goto L_0x0672
        L_0x0544:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "ad_cover"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 54
            goto L_0x0672
        L_0x0556:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "showable_impression_type"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 44
            goto L_0x0672
        L_0x0568:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "buffer_click_url_as_ready_to_ping"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 67
            goto L_0x0672
        L_0x057a:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "enable_omid"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 39
            goto L_0x0672
        L_0x058c:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "orientation"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 37
            goto L_0x0672
        L_0x059e:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "is_custom_close_blocked"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 35
            goto L_0x0672
        L_0x05b0:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "nofill_urls"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 13
            goto L_0x0672
        L_0x05c2:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "backend_query_id"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 47
            goto L_0x0672
        L_0x05d4:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "is_interscroller"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 53
            goto L_0x0672
        L_0x05e6:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "ad_source_name"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 57
            goto L_0x0672
        L_0x05f8:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "parallel_key"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 73
            goto L_0x0672
        L_0x060a:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "play_prewarm_options"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 49
            goto L_0x0672
        L_0x061b:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "is_consent"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 71
            goto L_0x0672
        L_0x062c:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "recursive_server_response_data"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 69
            goto L_0x0672
        L_0x063d:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "omid_settings"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 41
            goto L_0x0672
        L_0x064e:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "debug_signals"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 28
            goto L_0x0672
        L_0x065f:
            r26 = r9
            r22 = r10
            r9 = r23
            java.lang.String r10 = "ad_source_instance_name"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0670
            r9 = 59
            goto L_0x0672
        L_0x0670:
            r9 = r27
        L_0x0672:
            switch(r9) {
                case 0: goto L_0x08e0;
                case 1: goto L_0x08d5;
                case 2: goto L_0x08ce;
                case 3: goto L_0x08c7;
                case 4: goto L_0x08c0;
                case 5: goto L_0x08b5;
                case 6: goto L_0x08ae;
                case 7: goto L_0x08a7;
                case 8: goto L_0x08a0;
                case 9: goto L_0x0899;
                case 10: goto L_0x0892;
                case 11: goto L_0x0887;
                case 12: goto L_0x087f;
                case 13: goto L_0x0875;
                case 14: goto L_0x086c;
                case 15: goto L_0x0864;
                case 16: goto L_0x085c;
                case 17: goto L_0x0854;
                case 18: goto L_0x0849;
                case 19: goto L_0x0843;
                case 20: goto L_0x083d;
                case 21: goto L_0x0837;
                case 22: goto L_0x0831;
                case 23: goto L_0x082b;
                case 24: goto L_0x0825;
                case 25: goto L_0x081b;
                case 26: goto L_0x0811;
                case 27: goto L_0x080b;
                case 28: goto L_0x0805;
                case 29: goto L_0x07ff;
                case 30: goto L_0x07f9;
                case 31: goto L_0x07f3;
                case 32: goto L_0x07ed;
                case 33: goto L_0x07e7;
                case 34: goto L_0x07e1;
                case 35: goto L_0x07db;
                case 36: goto L_0x07d5;
                case 37: goto L_0x07cb;
                case 38: goto L_0x07c5;
                case 39: goto L_0x07bf;
                case 40: goto L_0x07b9;
                case 41: goto L_0x07b3;
                case 42: goto L_0x07ad;
                case 43: goto L_0x07a7;
                case 44: goto L_0x07a1;
                case 45: goto L_0x079b;
                case 46: goto L_0x0795;
                case 47: goto L_0x078f;
                case 48: goto L_0x0789;
                case 49: goto L_0x077f;
                case 50: goto L_0x0778;
                case 51: goto L_0x076e;
                case 52: goto L_0x0768;
                case 53: goto L_0x0762;
                case 54: goto L_0x075c;
                case 55: goto L_0x0756;
                case 56: goto L_0x0750;
                case 57: goto L_0x0738;
                case 58: goto L_0x0720;
                case 59: goto L_0x0708;
                case 60: goto L_0x06ef;
                case 61: goto L_0x06e9;
                case 62: goto L_0x06e3;
                case 63: goto L_0x06dd;
                case 64: goto L_0x06d7;
                case 65: goto L_0x06d1;
                case 66: goto L_0x06cb;
                case 67: goto L_0x06c5;
                case 68: goto L_0x06bf;
                case 69: goto L_0x06b9;
                case 70: goto L_0x06b3;
                case 71: goto L_0x06ad;
                case 72: goto L_0x06a7;
                case 73: goto L_0x06a1;
                case 74: goto L_0x069b;
                case 75: goto L_0x0695;
                case 76: goto L_0x067c;
                default: goto L_0x0675;
            }
        L_0x0675:
            r10 = r84
            r84.skipValue()
            goto L_0x08e6
        L_0x067c:
            com.google.android.gms.internal.ads.zzbeg r9 = com.google.android.gms.internal.ads.zzbep.zzZ
            java.lang.Object r9 = r9.zzl()
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L_0x0690
            java.util.Map r35 = com.google.android.gms.ads.internal.util.zzbw.zze(r84)
            goto L_0x08e6
        L_0x0690:
            r84.skipValue()
            goto L_0x077b
        L_0x0695:
            boolean r82 = r84.nextBoolean()
            goto L_0x08e6
        L_0x069b:
            java.util.List r34 = com.google.android.gms.ads.internal.util.zzbw.zzd(r84)
            goto L_0x08e6
        L_0x06a1:
            java.lang.String r81 = r84.nextString()
            goto L_0x08e6
        L_0x06a7:
            int r80 = r84.nextInt()
            goto L_0x08e6
        L_0x06ad:
            boolean r79 = r84.nextBoolean()
            goto L_0x08e6
        L_0x06b3:
            boolean r78 = r84.nextBoolean()
            goto L_0x08e6
        L_0x06b9:
            java.lang.String r76 = r84.nextString()
            goto L_0x08e6
        L_0x06bf:
            java.lang.String r75 = r84.nextString()
            goto L_0x08e6
        L_0x06c5:
            boolean r74 = r84.nextBoolean()
            goto L_0x08e6
        L_0x06cb:
            java.util.List r33 = com.google.android.gms.ads.internal.util.zzbw.zzd(r84)
            goto L_0x08e6
        L_0x06d1:
            boolean r73 = r84.nextBoolean()
            goto L_0x08e6
        L_0x06d7:
            java.lang.String r39 = r84.nextString()
            goto L_0x08e6
        L_0x06dd:
            boolean r72 = r84.nextBoolean()
            goto L_0x08e6
        L_0x06e3:
            org.json.JSONObject r32 = com.google.android.gms.ads.internal.util.zzbw.zzi(r84)
            goto L_0x08e6
        L_0x06e9:
            boolean r71 = r84.nextBoolean()
            goto L_0x08e6
        L_0x06ef:
            com.google.android.gms.internal.ads.zzbeg r9 = com.google.android.gms.internal.ads.zzbep.zzgX
            java.lang.Object r9 = r9.zzl()
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L_0x0703
            java.lang.String r70 = r84.nextString()
            goto L_0x08e6
        L_0x0703:
            r84.skipValue()
            goto L_0x077b
        L_0x0708:
            com.google.android.gms.internal.ads.zzbeg r9 = com.google.android.gms.internal.ads.zzbep.zzgX
            java.lang.Object r9 = r9.zzl()
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L_0x071c
            java.lang.String r69 = r84.nextString()
            goto L_0x08e6
        L_0x071c:
            r84.skipValue()
            goto L_0x077b
        L_0x0720:
            com.google.android.gms.internal.ads.zzbeg r9 = com.google.android.gms.internal.ads.zzbep.zzgX
            java.lang.Object r9 = r9.zzl()
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L_0x0734
            java.lang.String r68 = r84.nextString()
            goto L_0x08e6
        L_0x0734:
            r84.skipValue()
            goto L_0x077b
        L_0x0738:
            com.google.android.gms.internal.ads.zzbeg r9 = com.google.android.gms.internal.ads.zzbep.zzgX
            java.lang.Object r9 = r9.zzl()
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L_0x074c
            java.lang.String r67 = r84.nextString()
            goto L_0x08e6
        L_0x074c:
            r84.skipValue()
            goto L_0x077b
        L_0x0750:
            java.lang.String r77 = r84.nextString()
            goto L_0x08e6
        L_0x0756:
            java.lang.String r66 = r84.nextString()
            goto L_0x08e6
        L_0x075c:
            org.json.JSONObject r31 = com.google.android.gms.ads.internal.util.zzbw.zzi(r84)
            goto L_0x08e6
        L_0x0762:
            boolean r65 = r84.nextBoolean()
            goto L_0x08e6
        L_0x0768:
            java.lang.String r64 = r84.nextString()
            goto L_0x08e6
        L_0x076e:
            org.json.JSONObject r9 = com.google.android.gms.ads.internal.util.zzbw.zzi(r84)
            com.google.android.gms.ads.internal.client.zzs r38 = com.google.android.gms.ads.internal.client.zzs.zza(r9)
            goto L_0x08e6
        L_0x0778:
            r84.nextBoolean()
        L_0x077b:
            r10 = r84
            goto L_0x08e6
        L_0x077f:
            org.json.JSONObject r9 = com.google.android.gms.ads.internal.util.zzbw.zzi(r84)
            com.google.android.gms.internal.ads.zzbvm r37 = com.google.android.gms.internal.ads.zzbvm.zza(r9)
            goto L_0x08e6
        L_0x0789:
            boolean r63 = r84.nextBoolean()
            goto L_0x08e6
        L_0x078f:
            java.lang.String r62 = r84.nextString()
            goto L_0x08e6
        L_0x0795:
            java.lang.String r60 = r84.nextString()
            goto L_0x08e6
        L_0x079b:
            int r61 = r84.nextInt()
            goto L_0x08e6
        L_0x07a1:
            int r59 = r84.nextInt()
            goto L_0x08e6
        L_0x07a7:
            boolean r58 = r84.nextBoolean()
            goto L_0x08e6
        L_0x07ad:
            boolean r57 = r84.nextBoolean()
            goto L_0x08e6
        L_0x07b3:
            org.json.JSONObject r30 = com.google.android.gms.ads.internal.util.zzbw.zzi(r84)
            goto L_0x08e6
        L_0x07b9:
            java.lang.String r56 = r84.nextString()
            goto L_0x08e6
        L_0x07bf:
            boolean r55 = r84.nextBoolean()
            goto L_0x08e6
        L_0x07c5:
            int r54 = r84.nextInt()
            goto L_0x08e6
        L_0x07cb:
            java.lang.String r9 = r84.nextString()
            int r53 = zzd(r9)
            goto L_0x08e6
        L_0x07d5:
            boolean r52 = r84.nextBoolean()
            goto L_0x08e6
        L_0x07db:
            boolean r51 = r84.nextBoolean()
            goto L_0x08e6
        L_0x07e1:
            boolean r50 = r84.nextBoolean()
            goto L_0x08e6
        L_0x07e7:
            boolean r49 = r84.nextBoolean()
            goto L_0x08e6
        L_0x07ed:
            boolean r48 = r84.nextBoolean()
            goto L_0x08e6
        L_0x07f3:
            boolean r47 = r84.nextBoolean()
            goto L_0x08e6
        L_0x07f9:
            boolean r46 = r84.nextBoolean()
            goto L_0x08e6
        L_0x07ff:
            org.json.JSONObject r29 = com.google.android.gms.ads.internal.util.zzbw.zzi(r84)
            goto L_0x08e6
        L_0x0805:
            org.json.JSONObject r28 = com.google.android.gms.ads.internal.util.zzbw.zzi(r84)
            goto L_0x08e6
        L_0x080b:
            java.lang.String r45 = r84.nextString()
            goto L_0x08e6
        L_0x0811:
            org.json.JSONObject r9 = com.google.android.gms.ads.internal.util.zzbw.zzi(r84)
            com.google.android.gms.internal.ads.zzcac r36 = com.google.android.gms.internal.ads.zzcac.zza(r9)
            goto L_0x08e6
        L_0x081b:
            org.json.JSONObject r9 = com.google.android.gms.ads.internal.util.zzbw.zzi(r84)
            java.lang.String r44 = r9.toString()
            goto L_0x08e6
        L_0x0825:
            java.lang.String r43 = r84.nextString()
            goto L_0x08e6
        L_0x082b:
            java.lang.String r42 = r84.nextString()
            goto L_0x08e6
        L_0x0831:
            org.json.JSONObject r16 = com.google.android.gms.ads.internal.util.zzbw.zzi(r84)
            goto L_0x08e6
        L_0x0837:
            java.lang.String r41 = r84.nextString()
            goto L_0x08e6
        L_0x083d:
            java.util.List r18 = com.google.android.gms.ads.internal.util.zzbw.zzd(r84)
            goto L_0x08e6
        L_0x0843:
            java.util.List r17 = com.google.android.gms.internal.ads.zzfgu.zza(r84)
            goto L_0x08e6
        L_0x0849:
            com.google.android.gms.internal.ads.zzfgy r9 = new com.google.android.gms.internal.ads.zzfgy
            r10 = r84
            r9.<init>(r10)
            r19 = r9
            goto L_0x08e6
        L_0x0854:
            r10 = r84
            java.util.List r20 = com.google.android.gms.internal.ads.zzfgu.zza(r84)
            goto L_0x08e6
        L_0x085c:
            r10 = r84
            int r40 = r84.nextInt()
            goto L_0x08e6
        L_0x0864:
            r10 = r84
            java.util.List r21 = com.google.android.gms.ads.internal.util.zzbw.zzd(r84)
            goto L_0x08e6
        L_0x086c:
            r10 = r84
            java.util.List r9 = com.google.android.gms.ads.internal.util.zzbw.zzd(r84)
            r10 = r9
            goto L_0x08e8
        L_0x0875:
            r10 = r84
            java.util.List r9 = com.google.android.gms.ads.internal.util.zzbw.zzd(r84)
            r10 = r22
            goto L_0x00f5
        L_0x087f:
            r10 = r84
            java.util.List r8 = com.google.android.gms.ads.internal.util.zzbw.zzd(r84)
            goto L_0x08e6
        L_0x0887:
            r10 = r84
            org.json.JSONArray r9 = com.google.android.gms.ads.internal.util.zzbw.zzf(r84)
            com.google.android.gms.internal.ads.zzbyt r15 = com.google.android.gms.internal.ads.zzbyt.zza(r9)
            goto L_0x08e6
        L_0x0892:
            r10 = r84
            java.lang.String r11 = r84.nextString()
            goto L_0x08e6
        L_0x0899:
            r10 = r84
            java.lang.String r12 = r84.nextString()
            goto L_0x08e6
        L_0x08a0:
            r10 = r84
            java.util.List r7 = com.google.android.gms.ads.internal.util.zzbw.zzd(r84)
            goto L_0x08e6
        L_0x08a7:
            r10 = r84
            java.util.List r6 = com.google.android.gms.ads.internal.util.zzbw.zzd(r84)
            goto L_0x08e6
        L_0x08ae:
            r10 = r84
            java.util.List r5 = com.google.android.gms.ads.internal.util.zzbw.zzd(r84)
            goto L_0x08e6
        L_0x08b5:
            r10 = r84
            int r9 = r84.nextInt()
            int r14 = zzc(r9)
            goto L_0x08e6
        L_0x08c0:
            r10 = r84
            java.util.List r4 = com.google.android.gms.ads.internal.util.zzbw.zzd(r84)
            goto L_0x08e6
        L_0x08c7:
            r10 = r84
            java.util.List r3 = com.google.android.gms.ads.internal.util.zzbw.zzd(r84)
            goto L_0x08e6
        L_0x08ce:
            r10 = r84
            java.util.List r2 = com.google.android.gms.ads.internal.util.zzbw.zzd(r84)
            goto L_0x08e6
        L_0x08d5:
            r10 = r84
            java.lang.String r9 = r84.nextString()
            int r13 = zzb(r9)
            goto L_0x08e6
        L_0x08e0:
            r10 = r84
            java.util.List r1 = com.google.android.gms.ads.internal.util.zzbw.zzd(r84)
        L_0x08e6:
            r10 = r22
        L_0x08e8:
            r9 = r26
            goto L_0x00f5
        L_0x08ec:
            r26 = r9
            r22 = r10
            r10 = r84
            r84.endObject()
            r0.zza = r1
            r0.zzb = r13
            r0.zzc = r2
            r0.zzd = r3
            r0.zzg = r4
            r0.zzf = r14
            r0.zzh = r5
            r0.zzi = r6
            r0.zzj = r7
            r0.zzk = r12
            r0.zzl = r11
            r0.zzm = r15
            r0.zzn = r8
            r0.zzo = r9
            r9 = r22
            r0.zzp = r9
            r11 = r21
            r0.zzq = r11
            r1 = r40
            r0.zzr = r1
            r12 = r20
            r0.zzs = r12
            r9 = r19
            r0.zzt = r9
            r13 = r18
            r0.zzu = r13
            r14 = r17
            r0.zzv = r14
            r1 = r41
            r0.zzx = r1
            r15 = r16
            r0.zzw = r15
            r1 = r42
            r0.zzy = r1
            r1 = r43
            r0.zzz = r1
            r1 = r44
            r0.zzA = r1
            r1 = r36
            r0.zzB = r1
            r1 = r45
            r0.zzC = r1
            r1 = r28
            r0.zzD = r1
            r1 = r29
            r0.zzE = r1
            r1 = r46
            r0.zzK = r1
            r1 = r47
            r0.zzL = r1
            r1 = r48
            r0.zzM = r1
            r1 = r49
            r0.zzN = r1
            r1 = r50
            r0.zzO = r1
            r1 = r51
            r0.zzP = r1
            r1 = r52
            r0.zzQ = r1
            r1 = r53
            r0.zzR = r1
            r1 = r54
            r0.zzS = r1
            r1 = r55
            r0.zzU = r1
            r1 = r56
            r0.zzV = r1
            com.google.android.gms.internal.ads.zzfhr r1 = new com.google.android.gms.internal.ads.zzfhr
            r2 = r30
            r1.<init>(r2)
            r0.zzW = r1
            r1 = r57
            r0.zzX = r1
            r1 = r58
            r0.zzY = r1
            r1 = r59
            r0.zzZ = r1
            r1 = r60
            r0.zzaa = r1
            r1 = r61
            r0.zzab = r1
            r1 = r62
            r0.zzac = r1
            r1 = r63
            r0.zzad = r1
            r1 = r37
            r0.zzae = r1
            r1 = r38
            r0.zzaf = r1
            r1 = r64
            r0.zzag = r1
            r1 = r65
            r0.zzah = r1
            r1 = r31
            r0.zzai = r1
            r1 = r66
            r0.zzF = r1
            r1 = r67
            r0.zzG = r1
            r1 = r68
            r0.zzH = r1
            r1 = r69
            r0.zzI = r1
            r1 = r70
            r0.zzJ = r1
            r1 = r71
            r0.zzaj = r1
            r1 = r32
            r0.zzak = r1
            r1 = r72
            r0.zzal = r1
            r1 = r39
            r0.zzam = r1
            r1 = r73
            r0.zzan = r1
            r1 = r33
            r0.zze = r1
            r1 = r74
            r0.zzT = r1
            r1 = r75
            r0.zzao = r1
            r1 = r76
            r0.zzap = r1
            r1 = r77
            r0.zzaq = r1
            r1 = r78
            r0.zzar = r1
            r1 = r79
            r0.zzas = r1
            r1 = r80
            r0.zzat = r1
            r1 = r34
            r0.zzav = r1
            r1 = r81
            r0.zzau = r1
            r1 = r82
            r0.zzaw = r1
            r1 = r35
            r0.zzax = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfgt.<init>(android.util.JsonReader):void");
    }

    public static String zza(int i) {
        switch (i) {
            case 1:
                return "BANNER";
            case 2:
                return "INTERSTITIAL";
            case 3:
                return "NATIVE_EXPRESS";
            case 4:
                return "NATIVE";
            case 5:
                return "REWARDED";
            case 6:
                return "APP_OPEN_AD";
            case 7:
                return "REWARDED_INTERSTITIAL";
            default:
                return "UNKNOWN";
        }
    }

    private static int zzb(String str) {
        if ("banner".equals(str)) {
            return 1;
        }
        if ("interstitial".equals(str)) {
            return 2;
        }
        if ("native_express".equals(str)) {
            return 3;
        }
        if ("native".equals(str)) {
            return 4;
        }
        if ("rewarded".equals(str)) {
            return 5;
        }
        if ("app_open_ad".equals(str)) {
            return 6;
        }
        return "rewarded_interstitial".equals(str) ? 7 : 0;
    }

    private static int zzc(int i) {
        if (i == 0 || i == 1 || i == 3) {
            return i;
        }
        return 0;
    }

    private static final int zzd(String str) {
        if ("landscape".equalsIgnoreCase(str)) {
            return 6;
        }
        return "portrait".equalsIgnoreCase(str) ? 7 : -1;
    }
}
