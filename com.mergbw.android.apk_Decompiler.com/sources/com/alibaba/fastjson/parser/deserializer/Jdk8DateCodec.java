package com.alibaba.fastjson.parser.deserializer;

import androidx.work.Constraints$Builder$$ExternalSyntheticApiModelOutline0;
import androidx.work.WorkRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.ContextObjectSerializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bumptech.glide.disklrucache.DiskLruCache$$ExternalSyntheticApiModelOutline0;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;
import java.util.TimeZone;
import okhttp3.internal.connection.RealConnection;

public class Jdk8DateCodec extends ContextObjectDeserializer implements ObjectSerializer, ContextObjectSerializer, ObjectDeserializer {
    private static final DateTimeFormatter ISO_FIXED_FORMAT = DateTimeFormatter.ofPattern(defaultPatttern).withZone(DiskLruCache$$ExternalSyntheticApiModelOutline0.m());
    private static final DateTimeFormatter defaultFormatter = DateTimeFormatter.ofPattern(defaultPatttern);
    private static final DateTimeFormatter defaultFormatter_23 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private static final String defaultPatttern = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter formatter_d10_cn = DateTimeFormatter.ofPattern("yyyy年M月d日");
    private static final DateTimeFormatter formatter_d10_de = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter formatter_d10_eur = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter formatter_d10_in = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter formatter_d10_kr = DateTimeFormatter.ofPattern("yyyy년M월d일");
    private static final DateTimeFormatter formatter_d10_tw = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private static final DateTimeFormatter formatter_d10_us = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final DateTimeFormatter formatter_d8 = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter formatter_dt19_cn = DateTimeFormatter.ofPattern("yyyy年M月d日 HH:mm:ss");
    private static final DateTimeFormatter formatter_dt19_cn_1 = DateTimeFormatter.ofPattern("yyyy年M月d日 H时m分s秒");
    private static final DateTimeFormatter formatter_dt19_de = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    private static final DateTimeFormatter formatter_dt19_eur = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final DateTimeFormatter formatter_dt19_in = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private static final DateTimeFormatter formatter_dt19_kr = DateTimeFormatter.ofPattern("yyyy년M월d일 HH:mm:ss");
    private static final DateTimeFormatter formatter_dt19_tw = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static final DateTimeFormatter formatter_dt19_us = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
    private static final DateTimeFormatter formatter_iso8601 = DateTimeFormatter.ofPattern(formatter_iso8601_pattern);
    private static final String formatter_iso8601_pattern = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String formatter_iso8601_pattern_23 = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    private static final String formatter_iso8601_pattern_29 = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS";
    public static final Jdk8DateCodec instance = new Jdk8DateCodec();

    public int getFastMatchToken() {
        return 4;
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj, String str, int i) {
        DateTimeFormatter dateTimeFormatter;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 8) {
            jSONLexer.nextToken();
            return null;
        } else if (jSONLexer.token() == 4) {
            String stringVal = jSONLexer.stringVal();
            jSONLexer.nextToken();
            if (str != null) {
                dateTimeFormatter = defaultPatttern.equals(str) ? defaultFormatter : DateTimeFormatter.ofPattern(str);
            } else {
                dateTimeFormatter = null;
            }
            if ("".equals(stringVal)) {
                return null;
            }
            if (type == DiskLruCache$$ExternalSyntheticApiModelOutline0.m()) {
                if (stringVal.length() == 10 || stringVal.length() == 8) {
                    return LocalDateTime.of(parseLocalDate(stringVal, str, dateTimeFormatter), Constraints$Builder$$ExternalSyntheticApiModelOutline0.m());
                }
                return parseDateTime(stringVal, dateTimeFormatter);
            } else if (type != Constraints$Builder$$ExternalSyntheticApiModelOutline0.m$8()) {
                int i2 = 0;
                if (type == Constraints$Builder$$ExternalSyntheticApiModelOutline0.m()) {
                    if (stringVal.length() == 23) {
                        LocalDateTime m = Constraints$Builder$$ExternalSyntheticApiModelOutline0.m((CharSequence) stringVal);
                        return LocalTime.of(Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(m), m.getMinute(), m.getSecond(), m.getNano());
                    }
                    while (true) {
                        if (i2 < stringVal.length()) {
                            char charAt = stringVal.charAt(i2);
                            if (charAt < '0' || charAt > '9') {
                                break;
                            }
                            i2++;
                        } else if (stringVal.length() > 8 && stringVal.length() < 19) {
                            return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(Long.parseLong(stringVal)), JSON.defaultTimeZone.toZoneId()));
                        }
                    }
                    return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m((CharSequence) stringVal);
                } else if (type == Constraints$Builder$$ExternalSyntheticApiModelOutline0.m$1()) {
                    if (dateTimeFormatter == defaultFormatter) {
                        dateTimeFormatter = ISO_FIXED_FORMAT;
                    }
                    if (dateTimeFormatter == null && stringVal.length() <= 19) {
                        JSONScanner jSONScanner = new JSONScanner(stringVal);
                        TimeZone timeZone = defaultJSONParser.lexer.getTimeZone();
                        jSONScanner.setTimeZone(timeZone);
                        if (jSONScanner.scanISO8601DateIfMatch(false)) {
                            return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(jSONScanner.getCalendar().getTime().toInstant(), timeZone.toZoneId());
                        }
                    }
                    return parseZonedDateTime(stringVal, dateTimeFormatter);
                } else if (type == Constraints$Builder$$ExternalSyntheticApiModelOutline0.m$2()) {
                    return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m((CharSequence) stringVal);
                } else {
                    if (type == Constraints$Builder$$ExternalSyntheticApiModelOutline0.m$3()) {
                        return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m((CharSequence) stringVal);
                    }
                    if (type == Constraints$Builder$$ExternalSyntheticApiModelOutline0.m$4()) {
                        return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(stringVal);
                    }
                    if (type == Constraints$Builder$$ExternalSyntheticApiModelOutline0.m$5()) {
                        return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m((CharSequence) stringVal);
                    }
                    if (type == Constraints$Builder$$ExternalSyntheticApiModelOutline0.m$6()) {
                        return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m((CharSequence) stringVal);
                    }
                    if (type != Constraints$Builder$$ExternalSyntheticApiModelOutline0.m$7()) {
                        return null;
                    }
                    while (true) {
                        if (i2 < stringVal.length()) {
                            char charAt2 = stringVal.charAt(i2);
                            if (charAt2 < '0' || charAt2 > '9') {
                                break;
                            }
                            i2++;
                        } else if (stringVal.length() > 8 && stringVal.length() < 19) {
                            return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(Long.parseLong(stringVal));
                        }
                    }
                    return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m((CharSequence) stringVal);
                }
            } else if (stringVal.length() != 23) {
                return parseLocalDate(stringVal, str, dateTimeFormatter);
            } else {
                LocalDateTime m2 = Constraints$Builder$$ExternalSyntheticApiModelOutline0.m((CharSequence) stringVal);
                return LocalDate.of(m2.getYear(), m2.getMonthValue(), m2.getDayOfMonth());
            }
        } else if (jSONLexer.token() == 2) {
            long longValue = jSONLexer.longValue();
            jSONLexer.nextToken();
            if ("unixtime".equals(str)) {
                longValue *= 1000;
            } else if ("yyyyMMddHHmmss".equals(str)) {
                int i3 = (int) (longValue / RealConnection.IDLE_CONNECTION_HEALTHY_NS);
                int i4 = (int) ((longValue / 100000000) % 100);
                int i5 = (int) ((longValue / 1000000) % 100);
                int i6 = (int) ((longValue / WorkRequest.MIN_BACKOFF_MILLIS) % 100);
                int i7 = (int) ((longValue / 100) % 100);
                int i8 = (int) (longValue % 100);
                if (type == DiskLruCache$$ExternalSyntheticApiModelOutline0.m()) {
                    return LocalDateTime.of(i3, i4, i5, i6, i7, i8);
                }
            }
            if (type == DiskLruCache$$ExternalSyntheticApiModelOutline0.m()) {
                return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(longValue), JSON.defaultTimeZone.toZoneId());
            }
            if (type == Constraints$Builder$$ExternalSyntheticApiModelOutline0.m$8()) {
                return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(longValue), JSON.defaultTimeZone.toZoneId()));
            }
            if (type == Constraints$Builder$$ExternalSyntheticApiModelOutline0.m()) {
                return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(longValue), JSON.defaultTimeZone.toZoneId()));
            }
            if (type == Constraints$Builder$$ExternalSyntheticApiModelOutline0.m$1()) {
                return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(longValue), JSON.defaultTimeZone.toZoneId());
            }
            if (type == Constraints$Builder$$ExternalSyntheticApiModelOutline0.m$7()) {
                return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(longValue);
            }
            throw new UnsupportedOperationException();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00f4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.time.LocalDateTime parseDateTime(java.lang.String r17, java.time.format.DateTimeFormatter r18) {
        /*
            r16 = this;
            r0 = r17
            r1 = 0
            r2 = 19
            r3 = 48
            if (r18 != 0) goto L_0x0117
            int r4 = r17.length()
            r5 = 32
            r6 = 16
            r7 = 13
            r8 = 7
            r9 = 46
            r10 = 1
            r11 = 4
            r12 = 58
            r13 = 10
            r14 = 45
            if (r4 != r2) goto L_0x00bb
            char r4 = r0.charAt(r11)
            char r8 = r0.charAt(r8)
            char r15 = r0.charAt(r13)
            char r7 = r0.charAt(r7)
            char r6 = r0.charAt(r6)
            if (r7 != r12) goto L_0x00ea
            if (r6 != r12) goto L_0x00ea
            if (r4 != r14) goto L_0x004c
            if (r8 != r14) goto L_0x004c
            r4 = 84
            if (r15 != r4) goto L_0x0046
            java.time.format.DateTimeFormatter r4 = com.bumptech.glide.disklrucache.DiskLruCache$$ExternalSyntheticApiModelOutline0.m()
            goto L_0x00ec
        L_0x0046:
            if (r15 != r5) goto L_0x00ea
            java.time.format.DateTimeFormatter r4 = defaultFormatter
            goto L_0x00ec
        L_0x004c:
            r5 = 47
            if (r4 != r5) goto L_0x0056
            if (r8 != r5) goto L_0x0056
            java.time.format.DateTimeFormatter r4 = formatter_dt19_tw
            goto L_0x00ec
        L_0x0056:
            char r6 = r0.charAt(r1)
            char r7 = r0.charAt(r10)
            r8 = 2
            char r8 = r0.charAt(r8)
            r12 = 3
            char r12 = r0.charAt(r12)
            r15 = 5
            char r15 = r0.charAt(r15)
            if (r8 != r5) goto L_0x00ad
            if (r15 != r5) goto L_0x00ad
            int r6 = r6 - r3
            int r6 = r6 * r13
            int r7 = r7 - r3
            int r6 = r6 + r7
            int r12 = r12 - r3
            int r12 = r12 * r13
            int r4 = r4 - r3
            int r12 = r12 + r4
            r4 = 12
            if (r6 <= r4) goto L_0x0081
            java.time.format.DateTimeFormatter r4 = formatter_dt19_eur
            goto L_0x00ec
        L_0x0081:
            if (r12 <= r4) goto L_0x0087
            java.time.format.DateTimeFormatter r4 = formatter_dt19_us
            goto L_0x00ec
        L_0x0087:
            java.util.Locale r4 = java.util.Locale.getDefault()
            java.lang.String r4 = r4.getCountry()
            java.lang.String r5 = "US"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x009a
            java.time.format.DateTimeFormatter r4 = formatter_dt19_us
            goto L_0x00ec
        L_0x009a:
            java.lang.String r5 = "BR"
            boolean r5 = r4.equals(r5)
            if (r5 != 0) goto L_0x00aa
            java.lang.String r5 = "AU"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x00ea
        L_0x00aa:
            java.time.format.DateTimeFormatter r4 = formatter_dt19_eur
            goto L_0x00ec
        L_0x00ad:
            if (r8 != r9) goto L_0x00b4
            if (r15 != r9) goto L_0x00b4
            java.time.format.DateTimeFormatter r4 = formatter_dt19_de
            goto L_0x00ec
        L_0x00b4:
            if (r8 != r14) goto L_0x00ea
            if (r15 != r14) goto L_0x00ea
            java.time.format.DateTimeFormatter r4 = formatter_dt19_in
            goto L_0x00ec
        L_0x00bb:
            int r4 = r17.length()
            r15 = 23
            if (r4 != r15) goto L_0x00ea
            char r4 = r0.charAt(r11)
            char r8 = r0.charAt(r8)
            char r13 = r0.charAt(r13)
            char r7 = r0.charAt(r7)
            char r6 = r0.charAt(r6)
            char r15 = r0.charAt(r2)
            if (r7 != r12) goto L_0x00ea
            if (r6 != r12) goto L_0x00ea
            if (r4 != r14) goto L_0x00ea
            if (r8 != r14) goto L_0x00ea
            if (r13 != r5) goto L_0x00ea
            if (r15 != r9) goto L_0x00ea
            java.time.format.DateTimeFormatter r4 = defaultFormatter_23
            goto L_0x00ec
        L_0x00ea:
            r4 = r18
        L_0x00ec:
            int r5 = r17.length()
            r6 = 17
            if (r5 < r6) goto L_0x0119
            char r5 = r0.charAt(r11)
            r6 = 24180(0x5e74, float:3.3883E-41)
            if (r5 != r6) goto L_0x010f
            int r4 = r17.length()
            int r4 = r4 - r10
            char r4 = r0.charAt(r4)
            r5 = 31186(0x79d2, float:4.3701E-41)
            if (r4 != r5) goto L_0x010c
            java.time.format.DateTimeFormatter r4 = formatter_dt19_cn_1
            goto L_0x0119
        L_0x010c:
            java.time.format.DateTimeFormatter r4 = formatter_dt19_cn
            goto L_0x0119
        L_0x010f:
            r6 = 45380(0xb144, float:6.3591E-41)
            if (r5 != r6) goto L_0x0119
            java.time.format.DateTimeFormatter r4 = formatter_dt19_kr
            goto L_0x0119
        L_0x0117:
            r4 = r18
        L_0x0119:
            if (r4 != 0) goto L_0x016c
            com.alibaba.fastjson.parser.JSONScanner r5 = new com.alibaba.fastjson.parser.JSONScanner
            r5.<init>(r0)
            boolean r6 = r5.scanISO8601DateIfMatch(r1)
            if (r6 == 0) goto L_0x0137
            java.util.Calendar r0 = r5.getCalendar()
            java.time.Instant r0 = r0.toInstant()
            java.time.ZoneId r1 = com.bumptech.glide.disklrucache.DiskLruCache$$ExternalSyntheticApiModelOutline0.m()
            java.time.LocalDateTime r0 = androidx.work.Constraints$Builder$$ExternalSyntheticApiModelOutline0.m((java.time.Instant) r0, (java.time.ZoneId) r1)
            return r0
        L_0x0137:
            int r5 = r17.length()
            if (r1 >= r5) goto L_0x014b
            char r5 = r0.charAt(r1)
            if (r5 < r3) goto L_0x016c
            r6 = 57
            if (r5 <= r6) goto L_0x0148
            goto L_0x016c
        L_0x0148:
            int r1 = r1 + 1
            goto L_0x0137
        L_0x014b:
            int r1 = r17.length()
            r3 = 8
            if (r1 <= r3) goto L_0x016c
            int r1 = r17.length()
            if (r1 >= r2) goto L_0x016c
            long r0 = java.lang.Long.parseLong(r17)
            java.time.Instant r0 = androidx.work.Constraints$Builder$$ExternalSyntheticApiModelOutline0.m((long) r0)
            java.util.TimeZone r1 = com.alibaba.fastjson.JSON.defaultTimeZone
            java.time.ZoneId r1 = r1.toZoneId()
            java.time.LocalDateTime r0 = androidx.work.Constraints$Builder$$ExternalSyntheticApiModelOutline0.m((java.time.Instant) r0, (java.time.ZoneId) r1)
            return r0
        L_0x016c:
            if (r4 != 0) goto L_0x0173
            java.time.LocalDateTime r0 = androidx.work.Constraints$Builder$$ExternalSyntheticApiModelOutline0.m((java.lang.CharSequence) r17)
            goto L_0x0177
        L_0x0173:
            java.time.LocalDateTime r0 = androidx.work.Constraints$Builder$$ExternalSyntheticApiModelOutline0.m((java.lang.CharSequence) r0, (java.time.format.DateTimeFormatter) r4)
        L_0x0177:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.Jdk8DateCodec.parseDateTime(java.lang.String, java.time.format.DateTimeFormatter):java.time.LocalDateTime");
    }

    /* access modifiers changed from: protected */
    public LocalDate parseLocalDate(String str, String str2, DateTimeFormatter dateTimeFormatter) {
        DateTimeFormatter dateTimeFormatter2;
        DateTimeFormatter dateTimeFormatter3;
        if (dateTimeFormatter == null) {
            if (str.length() == 8) {
                dateTimeFormatter = formatter_d8;
            }
            int i = 0;
            if (str.length() == 10) {
                char charAt = str.charAt(4);
                char charAt2 = str.charAt(7);
                if (charAt == '/' && charAt2 == '/') {
                    dateTimeFormatter = formatter_d10_tw;
                }
                char charAt3 = str.charAt(0);
                char charAt4 = str.charAt(1);
                char charAt5 = str.charAt(2);
                char charAt6 = str.charAt(3);
                char charAt7 = str.charAt(5);
                if (charAt5 == '/' && charAt7 == '/') {
                    int i2 = ((charAt6 - '0') * 10) + (charAt - '0');
                    if (((charAt3 - '0') * 10) + (charAt4 - '0') > 12) {
                        dateTimeFormatter3 = formatter_d10_eur;
                    } else if (i2 > 12) {
                        dateTimeFormatter3 = formatter_d10_us;
                    } else {
                        String country = Locale.getDefault().getCountry();
                        if (country.equals("US")) {
                            dateTimeFormatter3 = formatter_d10_us;
                        } else if (country.equals("BR") || country.equals("AU")) {
                            dateTimeFormatter3 = formatter_d10_eur;
                        }
                    }
                    dateTimeFormatter = dateTimeFormatter3;
                } else if (charAt5 == '.' && charAt7 == '.') {
                    dateTimeFormatter = formatter_d10_de;
                } else if (charAt5 == '-' && charAt7 == '-') {
                    dateTimeFormatter = formatter_d10_in;
                }
            }
            if (str.length() >= 9) {
                char charAt8 = str.charAt(4);
                if (charAt8 == 24180) {
                    dateTimeFormatter2 = formatter_d10_cn;
                } else if (charAt8 == 45380) {
                    dateTimeFormatter2 = formatter_d10_kr;
                }
                dateTimeFormatter = dateTimeFormatter2;
            }
            while (true) {
                if (i < str.length()) {
                    char charAt9 = str.charAt(i);
                    if (charAt9 < '0' || charAt9 > '9') {
                        break;
                    }
                    i++;
                } else if (str.length() > 8 && str.length() < 19) {
                    return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(Long.parseLong(str)), JSON.defaultTimeZone.toZoneId()));
                }
            }
        }
        if (dateTimeFormatter == null) {
            return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m((CharSequence) str);
        }
        return LocalDate.parse(str, dateTimeFormatter);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00f9 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.time.ZonedDateTime parseZonedDateTime(java.lang.String r16, java.time.format.DateTimeFormatter r17) {
        /*
            r15 = this;
            r0 = r16
            if (r17 != 0) goto L_0x011a
            int r1 = r16.length()
            r2 = 0
            r3 = 4
            r4 = 19
            r5 = 1
            r6 = 48
            if (r1 != r4) goto L_0x00b9
            char r1 = r0.charAt(r3)
            r7 = 7
            char r7 = r0.charAt(r7)
            r8 = 10
            char r9 = r0.charAt(r8)
            r10 = 13
            char r10 = r0.charAt(r10)
            r11 = 16
            char r11 = r0.charAt(r11)
            r12 = 58
            if (r10 != r12) goto L_0x00b9
            if (r11 != r12) goto L_0x00b9
            r10 = 45
            if (r1 != r10) goto L_0x004a
            if (r7 != r10) goto L_0x004a
            r1 = 84
            if (r9 != r1) goto L_0x0042
            java.time.format.DateTimeFormatter r1 = com.bumptech.glide.disklrucache.DiskLruCache$$ExternalSyntheticApiModelOutline0.m()
            goto L_0x00bb
        L_0x0042:
            r1 = 32
            if (r9 != r1) goto L_0x00b9
            java.time.format.DateTimeFormatter r1 = defaultFormatter
            goto L_0x00bb
        L_0x004a:
            r9 = 47
            if (r1 != r9) goto L_0x0054
            if (r7 != r9) goto L_0x0054
            java.time.format.DateTimeFormatter r1 = formatter_dt19_tw
            goto L_0x00bb
        L_0x0054:
            char r7 = r0.charAt(r2)
            char r11 = r0.charAt(r5)
            r12 = 2
            char r12 = r0.charAt(r12)
            r13 = 3
            char r13 = r0.charAt(r13)
            r14 = 5
            char r14 = r0.charAt(r14)
            if (r12 != r9) goto L_0x00a9
            if (r14 != r9) goto L_0x00a9
            int r7 = r7 - r6
            int r7 = r7 * r8
            int r11 = r11 - r6
            int r7 = r7 + r11
            int r13 = r13 - r6
            int r13 = r13 * r8
            int r1 = r1 - r6
            int r13 = r13 + r1
            r1 = 12
            if (r7 <= r1) goto L_0x007e
            java.time.format.DateTimeFormatter r1 = formatter_dt19_eur
            goto L_0x00bb
        L_0x007e:
            if (r13 <= r1) goto L_0x0083
            java.time.format.DateTimeFormatter r1 = formatter_dt19_us
            goto L_0x00bb
        L_0x0083:
            java.util.Locale r1 = java.util.Locale.getDefault()
            java.lang.String r1 = r1.getCountry()
            java.lang.String r7 = "US"
            boolean r7 = r1.equals(r7)
            if (r7 == 0) goto L_0x0096
            java.time.format.DateTimeFormatter r1 = formatter_dt19_us
            goto L_0x00bb
        L_0x0096:
            java.lang.String r7 = "BR"
            boolean r7 = r1.equals(r7)
            if (r7 != 0) goto L_0x00a6
            java.lang.String r7 = "AU"
            boolean r1 = r1.equals(r7)
            if (r1 == 0) goto L_0x00b9
        L_0x00a6:
            java.time.format.DateTimeFormatter r1 = formatter_dt19_eur
            goto L_0x00bb
        L_0x00a9:
            r1 = 46
            if (r12 != r1) goto L_0x00b2
            if (r14 != r1) goto L_0x00b2
            java.time.format.DateTimeFormatter r1 = formatter_dt19_de
            goto L_0x00bb
        L_0x00b2:
            if (r12 != r10) goto L_0x00b9
            if (r14 != r10) goto L_0x00b9
            java.time.format.DateTimeFormatter r1 = formatter_dt19_in
            goto L_0x00bb
        L_0x00b9:
            r1 = r17
        L_0x00bb:
            int r7 = r16.length()
            r8 = 17
            if (r7 < r8) goto L_0x00e5
            char r3 = r0.charAt(r3)
            r7 = 24180(0x5e74, float:3.3883E-41)
            if (r3 != r7) goto L_0x00de
            int r1 = r16.length()
            int r1 = r1 - r5
            char r1 = r0.charAt(r1)
            r3 = 31186(0x79d2, float:4.3701E-41)
            if (r1 != r3) goto L_0x00db
            java.time.format.DateTimeFormatter r1 = formatter_dt19_cn_1
            goto L_0x00e5
        L_0x00db:
            java.time.format.DateTimeFormatter r1 = formatter_dt19_cn
            goto L_0x00e5
        L_0x00de:
            r5 = 45380(0xb144, float:6.3591E-41)
            if (r3 != r5) goto L_0x00e5
            java.time.format.DateTimeFormatter r1 = formatter_dt19_kr
        L_0x00e5:
            int r3 = r16.length()
            if (r2 >= r3) goto L_0x00f9
            char r3 = r0.charAt(r2)
            if (r3 < r6) goto L_0x011c
            r5 = 57
            if (r3 <= r5) goto L_0x00f6
            goto L_0x011c
        L_0x00f6:
            int r2 = r2 + 1
            goto L_0x00e5
        L_0x00f9:
            int r2 = r16.length()
            r3 = 8
            if (r2 <= r3) goto L_0x011c
            int r2 = r16.length()
            if (r2 >= r4) goto L_0x011c
            long r0 = java.lang.Long.parseLong(r16)
            java.time.Instant r0 = androidx.work.Constraints$Builder$$ExternalSyntheticApiModelOutline0.m((long) r0)
            java.util.TimeZone r1 = com.alibaba.fastjson.JSON.defaultTimeZone
            java.time.ZoneId r1 = r1.toZoneId()
            java.time.ZonedDateTime r0 = androidx.work.Constraints$Builder$$ExternalSyntheticApiModelOutline0.m((java.time.Instant) r0, (java.time.ZoneId) r1)
            return r0
        L_0x011a:
            r1 = r17
        L_0x011c:
            if (r1 != 0) goto L_0x0123
            java.time.ZonedDateTime r0 = androidx.work.Constraints$Builder$$ExternalSyntheticApiModelOutline0.m((java.lang.CharSequence) r16)
            goto L_0x0127
        L_0x0123:
            java.time.ZonedDateTime r0 = androidx.work.Constraints$Builder$$ExternalSyntheticApiModelOutline0.m((java.lang.CharSequence) r0, (java.time.format.DateTimeFormatter) r1)
        L_0x0127:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.Jdk8DateCodec.parseZonedDateTime(java.lang.String, java.time.format.DateTimeFormatter):java.time.ZonedDateTime");
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        int m$3;
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull();
            return;
        }
        if (type == null) {
            type = obj.getClass();
        }
        if (type == DiskLruCache$$ExternalSyntheticApiModelOutline0.m()) {
            int mask = SerializerFeature.UseISO8601DateFormat.getMask();
            LocalDateTime m = DiskLruCache$$ExternalSyntheticApiModelOutline0.m(obj);
            String dateFormatPattern = jSONSerializer.getDateFormatPattern();
            if (dateFormatPattern == null) {
                int i2 = mask & i;
                dateFormatPattern = formatter_iso8601_pattern;
                if (i2 == 0 && !jSONSerializer.isEnabled(SerializerFeature.UseISO8601DateFormat) && (m$3 = m.getNano()) != 0) {
                    dateFormatPattern = m$3 % 1000000 == 0 ? formatter_iso8601_pattern_23 : formatter_iso8601_pattern_29;
                }
            }
            if (dateFormatPattern != null) {
                write(serializeWriter, (TemporalAccessor) m, dateFormatPattern);
            } else if (serializeWriter.isEnabled(SerializerFeature.WriteDateUseDateFormat)) {
                write(serializeWriter, (TemporalAccessor) m, JSON.DEFFAULT_DATE_FORMAT);
            } else {
                serializeWriter.writeLong(m.atZone(JSON.defaultTimeZone.toZoneId()).toInstant().toEpochMilli());
            }
        } else {
            serializeWriter.writeString(obj.toString());
        }
    }

    public void write(JSONSerializer jSONSerializer, Object obj, BeanContext beanContext) throws IOException {
        write(jSONSerializer.out, DiskLruCache$$ExternalSyntheticApiModelOutline0.m(obj), beanContext.getFormat());
    }

    private void write(SerializeWriter serializeWriter, TemporalAccessor temporalAccessor, String str) {
        DateTimeFormatter dateTimeFormatter;
        Instant instant;
        if ("unixtime".equals(str)) {
            if (Constraints$Builder$$ExternalSyntheticApiModelOutline0.m((Object) temporalAccessor)) {
                serializeWriter.writeInt((int) Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(Constraints$Builder$$ExternalSyntheticApiModelOutline0.m((Object) temporalAccessor)));
                return;
            } else if (Constraints$Builder$$ExternalSyntheticApiModelOutline0.m$1((Object) temporalAccessor)) {
                serializeWriter.writeInt((int) DiskLruCache$$ExternalSyntheticApiModelOutline0.m((Object) temporalAccessor).atZone(JSON.defaultTimeZone.toZoneId()).toEpochSecond());
                return;
            }
        }
        if ("millis".equals(str)) {
            if (Constraints$Builder$$ExternalSyntheticApiModelOutline0.m((Object) temporalAccessor)) {
                instant = Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(Constraints$Builder$$ExternalSyntheticApiModelOutline0.m((Object) temporalAccessor));
            } else {
                instant = Constraints$Builder$$ExternalSyntheticApiModelOutline0.m$1((Object) temporalAccessor) ? DiskLruCache$$ExternalSyntheticApiModelOutline0.m((Object) temporalAccessor).atZone(JSON.defaultTimeZone.toZoneId()).toInstant() : null;
            }
            if (instant != null) {
                serializeWriter.writeLong(instant.toEpochMilli());
                return;
            }
        }
        if (str == formatter_iso8601_pattern) {
            dateTimeFormatter = formatter_iso8601;
        } else {
            dateTimeFormatter = DateTimeFormatter.ofPattern(str);
        }
        serializeWriter.writeString(dateTimeFormatter.format(temporalAccessor));
    }
}
