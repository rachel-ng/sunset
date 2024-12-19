package androidx.datastore.preferences;

import androidx.datastore.preferences.protobuf.AbstractMessageLite;
import androidx.datastore.preferences.protobuf.ByteString;
import androidx.datastore.preferences.protobuf.CodedInputStream;
import androidx.datastore.preferences.protobuf.ExtensionRegistryLite;
import androidx.datastore.preferences.protobuf.GeneratedMessageLite;
import androidx.datastore.preferences.protobuf.Internal;
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException;
import androidx.datastore.preferences.protobuf.MapEntryLite;
import androidx.datastore.preferences.protobuf.MapFieldLite;
import androidx.datastore.preferences.protobuf.MessageLiteOrBuilder;
import androidx.datastore.preferences.protobuf.Parser;
import androidx.datastore.preferences.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class PreferencesProto {

    public interface PreferenceMapOrBuilder extends MessageLiteOrBuilder {
        boolean containsPreferences(String str);

        @Deprecated
        Map<String, Value> getPreferences();

        int getPreferencesCount();

        Map<String, Value> getPreferencesMap();

        Value getPreferencesOrDefault(String str, Value value);

        Value getPreferencesOrThrow(String str);
    }

    public interface StringSetOrBuilder extends MessageLiteOrBuilder {
        String getStrings(int i);

        ByteString getStringsBytes(int i);

        int getStringsCount();

        List<String> getStringsList();
    }

    public interface ValueOrBuilder extends MessageLiteOrBuilder {
        boolean getBoolean();

        double getDouble();

        float getFloat();

        int getInteger();

        long getLong();

        String getString();

        ByteString getStringBytes();

        StringSet getStringSet();

        Value.ValueCase getValueCase();

        boolean hasBoolean();

        boolean hasDouble();

        boolean hasFloat();

        boolean hasInteger();

        boolean hasLong();

        boolean hasString();

        boolean hasStringSet();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private PreferencesProto() {
    }

    public static final class PreferenceMap extends GeneratedMessageLite<PreferenceMap, Builder> implements PreferenceMapOrBuilder {
        /* access modifiers changed from: private */
        public static final PreferenceMap DEFAULT_INSTANCE;
        private static volatile Parser<PreferenceMap> PARSER = null;
        public static final int PREFERENCES_FIELD_NUMBER = 1;
        private MapFieldLite<String, Value> preferences_ = MapFieldLite.emptyMapField();

        private PreferenceMap() {
        }

        private static final class PreferencesDefaultEntryHolder {
            static final MapEntryLite<String, Value> defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, Value.getDefaultInstance());

            private PreferencesDefaultEntryHolder() {
            }
        }

        private MapFieldLite<String, Value> internalGetPreferences() {
            return this.preferences_;
        }

        private MapFieldLite<String, Value> internalGetMutablePreferences() {
            if (!this.preferences_.isMutable()) {
                this.preferences_ = this.preferences_.mutableCopy();
            }
            return this.preferences_;
        }

        public int getPreferencesCount() {
            return internalGetPreferences().size();
        }

        public boolean containsPreferences(String str) {
            str.getClass();
            return internalGetPreferences().containsKey(str);
        }

        @Deprecated
        public Map<String, Value> getPreferences() {
            return getPreferencesMap();
        }

        public Map<String, Value> getPreferencesMap() {
            return Collections.unmodifiableMap(internalGetPreferences());
        }

        public Value getPreferencesOrDefault(String str, Value value) {
            str.getClass();
            MapFieldLite<String, Value> internalGetPreferences = internalGetPreferences();
            return internalGetPreferences.containsKey(str) ? internalGetPreferences.get(str) : value;
        }

        public Value getPreferencesOrThrow(String str) {
            str.getClass();
            MapFieldLite<String, Value> internalGetPreferences = internalGetPreferences();
            if (internalGetPreferences.containsKey(str)) {
                return internalGetPreferences.get(str);
            }
            throw new IllegalArgumentException();
        }

        /* access modifiers changed from: private */
        public Map<String, Value> getMutablePreferencesMap() {
            return internalGetMutablePreferences();
        }

        public static PreferenceMap parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (PreferenceMap) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static PreferenceMap parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PreferenceMap) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static PreferenceMap parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (PreferenceMap) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static PreferenceMap parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PreferenceMap) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static PreferenceMap parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (PreferenceMap) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static PreferenceMap parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PreferenceMap) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static PreferenceMap parseFrom(InputStream inputStream) throws IOException {
            return (PreferenceMap) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PreferenceMap parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PreferenceMap) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static PreferenceMap parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PreferenceMap) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PreferenceMap parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PreferenceMap) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static PreferenceMap parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PreferenceMap) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static PreferenceMap parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PreferenceMap) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(PreferenceMap preferenceMap) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(preferenceMap);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PreferenceMap, Builder> implements PreferenceMapOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(PreferenceMap.DEFAULT_INSTANCE);
            }

            public int getPreferencesCount() {
                return ((PreferenceMap) this.instance).getPreferencesMap().size();
            }

            public boolean containsPreferences(String str) {
                str.getClass();
                return ((PreferenceMap) this.instance).getPreferencesMap().containsKey(str);
            }

            public Builder clearPreferences() {
                copyOnWrite();
                ((PreferenceMap) this.instance).getMutablePreferencesMap().clear();
                return this;
            }

            public Builder removePreferences(String str) {
                str.getClass();
                copyOnWrite();
                ((PreferenceMap) this.instance).getMutablePreferencesMap().remove(str);
                return this;
            }

            @Deprecated
            public Map<String, Value> getPreferences() {
                return getPreferencesMap();
            }

            public Map<String, Value> getPreferencesMap() {
                return Collections.unmodifiableMap(((PreferenceMap) this.instance).getPreferencesMap());
            }

            public Value getPreferencesOrDefault(String str, Value value) {
                str.getClass();
                Map<String, Value> preferencesMap = ((PreferenceMap) this.instance).getPreferencesMap();
                return preferencesMap.containsKey(str) ? preferencesMap.get(str) : value;
            }

            public Value getPreferencesOrThrow(String str) {
                str.getClass();
                Map<String, Value> preferencesMap = ((PreferenceMap) this.instance).getPreferencesMap();
                if (preferencesMap.containsKey(str)) {
                    return preferencesMap.get(str);
                }
                throw new IllegalArgumentException();
            }

            public Builder putPreferences(String str, Value value) {
                str.getClass();
                value.getClass();
                copyOnWrite();
                ((PreferenceMap) this.instance).getMutablePreferencesMap().put(str, value);
                return this;
            }

            public Builder putAllPreferences(Map<String, Value> map) {
                copyOnWrite();
                ((PreferenceMap) this.instance).getMutablePreferencesMap().putAll(map);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new PreferenceMap();
                case 2:
                    return new Builder((AnonymousClass1) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", new Object[]{"preferences_", PreferencesDefaultEntryHolder.defaultEntry});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<PreferenceMap> parser = PARSER;
                    if (parser == null) {
                        synchronized (PreferenceMap.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            PreferenceMap preferenceMap = new PreferenceMap();
            DEFAULT_INSTANCE = preferenceMap;
            GeneratedMessageLite.registerDefaultInstance(PreferenceMap.class, preferenceMap);
        }

        public static PreferenceMap getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PreferenceMap> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: androidx.datastore.preferences.PreferencesProto$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                androidx.datastore.preferences.protobuf.GeneratedMessageLite$MethodToInvoke[] r0 = androidx.datastore.preferences.protobuf.GeneratedMessageLite.MethodToInvoke.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = r0
                androidx.datastore.preferences.protobuf.GeneratedMessageLite$MethodToInvoke r1 = androidx.datastore.preferences.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.datastore.preferences.protobuf.GeneratedMessageLite$MethodToInvoke r1 = androidx.datastore.preferences.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0028 }
                androidx.datastore.preferences.protobuf.GeneratedMessageLite$MethodToInvoke r1 = androidx.datastore.preferences.protobuf.GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0033 }
                androidx.datastore.preferences.protobuf.GeneratedMessageLite$MethodToInvoke r1 = androidx.datastore.preferences.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x003e }
                androidx.datastore.preferences.protobuf.GeneratedMessageLite$MethodToInvoke r1 = androidx.datastore.preferences.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0049 }
                androidx.datastore.preferences.protobuf.GeneratedMessageLite$MethodToInvoke r1 = androidx.datastore.preferences.protobuf.GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0054 }
                androidx.datastore.preferences.protobuf.GeneratedMessageLite$MethodToInvoke r1 = androidx.datastore.preferences.protobuf.GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.PreferencesProto.AnonymousClass1.<clinit>():void");
        }
    }

    public static final class Value extends GeneratedMessageLite<Value, Builder> implements ValueOrBuilder {
        public static final int BOOLEAN_FIELD_NUMBER = 1;
        /* access modifiers changed from: private */
        public static final Value DEFAULT_INSTANCE;
        public static final int DOUBLE_FIELD_NUMBER = 7;
        public static final int FLOAT_FIELD_NUMBER = 2;
        public static final int INTEGER_FIELD_NUMBER = 3;
        public static final int LONG_FIELD_NUMBER = 4;
        private static volatile Parser<Value> PARSER = null;
        public static final int STRING_FIELD_NUMBER = 5;
        public static final int STRING_SET_FIELD_NUMBER = 6;
        private int bitField0_;
        private int valueCase_ = 0;
        private Object value_;

        private Value() {
        }

        public enum ValueCase {
            BOOLEAN(1),
            FLOAT(2),
            INTEGER(3),
            LONG(4),
            STRING(5),
            STRING_SET(6),
            DOUBLE(7),
            VALUE_NOT_SET(0);
            
            private final int value;

            private ValueCase(int i) {
                this.value = i;
            }

            @Deprecated
            public static ValueCase valueOf(int i) {
                return forNumber(i);
            }

            public static ValueCase forNumber(int i) {
                switch (i) {
                    case 0:
                        return VALUE_NOT_SET;
                    case 1:
                        return BOOLEAN;
                    case 2:
                        return FLOAT;
                    case 3:
                        return INTEGER;
                    case 4:
                        return LONG;
                    case 5:
                        return STRING;
                    case 6:
                        return STRING_SET;
                    case 7:
                        return DOUBLE;
                    default:
                        return null;
                }
            }

            public int getNumber() {
                return this.value;
            }
        }

        public ValueCase getValueCase() {
            return ValueCase.forNumber(this.valueCase_);
        }

        /* access modifiers changed from: private */
        public void clearValue() {
            this.valueCase_ = 0;
            this.value_ = null;
        }

        public boolean hasBoolean() {
            return this.valueCase_ == 1;
        }

        public boolean getBoolean() {
            if (this.valueCase_ == 1) {
                return ((Boolean) this.value_).booleanValue();
            }
            return false;
        }

        /* access modifiers changed from: private */
        public void setBoolean(boolean z) {
            this.valueCase_ = 1;
            this.value_ = Boolean.valueOf(z);
        }

        /* access modifiers changed from: private */
        public void clearBoolean() {
            if (this.valueCase_ == 1) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
        }

        public boolean hasFloat() {
            return this.valueCase_ == 2;
        }

        public float getFloat() {
            if (this.valueCase_ == 2) {
                return ((Float) this.value_).floatValue();
            }
            return 0.0f;
        }

        /* access modifiers changed from: private */
        public void setFloat(float f) {
            this.valueCase_ = 2;
            this.value_ = Float.valueOf(f);
        }

        /* access modifiers changed from: private */
        public void clearFloat() {
            if (this.valueCase_ == 2) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
        }

        public boolean hasInteger() {
            return this.valueCase_ == 3;
        }

        public int getInteger() {
            if (this.valueCase_ == 3) {
                return ((Integer) this.value_).intValue();
            }
            return 0;
        }

        /* access modifiers changed from: private */
        public void setInteger(int i) {
            this.valueCase_ = 3;
            this.value_ = Integer.valueOf(i);
        }

        /* access modifiers changed from: private */
        public void clearInteger() {
            if (this.valueCase_ == 3) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
        }

        public boolean hasLong() {
            return this.valueCase_ == 4;
        }

        public long getLong() {
            if (this.valueCase_ == 4) {
                return ((Long) this.value_).longValue();
            }
            return 0;
        }

        /* access modifiers changed from: private */
        public void setLong(long j) {
            this.valueCase_ = 4;
            this.value_ = Long.valueOf(j);
        }

        /* access modifiers changed from: private */
        public void clearLong() {
            if (this.valueCase_ == 4) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
        }

        public boolean hasString() {
            return this.valueCase_ == 5;
        }

        public String getString() {
            if (this.valueCase_ == 5) {
                return (String) this.value_;
            }
            return "";
        }

        public ByteString getStringBytes() {
            String str;
            if (this.valueCase_ == 5) {
                str = (String) this.value_;
            } else {
                str = "";
            }
            return ByteString.copyFromUtf8(str);
        }

        /* access modifiers changed from: private */
        public void setString(String str) {
            str.getClass();
            this.valueCase_ = 5;
            this.value_ = str;
        }

        /* access modifiers changed from: private */
        public void clearString() {
            if (this.valueCase_ == 5) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
        }

        /* access modifiers changed from: private */
        public void setStringBytes(ByteString byteString) {
            byteString.getClass();
            this.valueCase_ = 5;
            this.value_ = byteString.toStringUtf8();
        }

        public boolean hasStringSet() {
            return this.valueCase_ == 6;
        }

        public StringSet getStringSet() {
            if (this.valueCase_ == 6) {
                return (StringSet) this.value_;
            }
            return StringSet.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setStringSet(StringSet stringSet) {
            stringSet.getClass();
            this.value_ = stringSet;
            this.valueCase_ = 6;
        }

        /* access modifiers changed from: private */
        public void setStringSet(StringSet.Builder builder) {
            this.value_ = builder.build();
            this.valueCase_ = 6;
        }

        /* access modifiers changed from: private */
        public void mergeStringSet(StringSet stringSet) {
            stringSet.getClass();
            if (this.valueCase_ != 6 || this.value_ == StringSet.getDefaultInstance()) {
                this.value_ = stringSet;
            } else {
                this.value_ = ((StringSet.Builder) StringSet.newBuilder((StringSet) this.value_).mergeFrom(stringSet)).buildPartial();
            }
            this.valueCase_ = 6;
        }

        /* access modifiers changed from: private */
        public void clearStringSet() {
            if (this.valueCase_ == 6) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
        }

        public boolean hasDouble() {
            return this.valueCase_ == 7;
        }

        public double getDouble() {
            if (this.valueCase_ == 7) {
                return ((Double) this.value_).doubleValue();
            }
            return 0.0d;
        }

        /* access modifiers changed from: private */
        public void setDouble(double d) {
            this.valueCase_ = 7;
            this.value_ = Double.valueOf(d);
        }

        /* access modifiers changed from: private */
        public void clearDouble() {
            if (this.valueCase_ == 7) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
        }

        public static Value parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Value parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Value parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Value parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Value parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Value parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Value parseFrom(InputStream inputStream) throws IOException {
            return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Value parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Value parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Value) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Value parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Value) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Value parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Value parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Value value) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(value);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Value, Builder> implements ValueOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(Value.DEFAULT_INSTANCE);
            }

            public ValueCase getValueCase() {
                return ((Value) this.instance).getValueCase();
            }

            public Builder clearValue() {
                copyOnWrite();
                ((Value) this.instance).clearValue();
                return this;
            }

            public boolean hasBoolean() {
                return ((Value) this.instance).hasBoolean();
            }

            public boolean getBoolean() {
                return ((Value) this.instance).getBoolean();
            }

            public Builder setBoolean(boolean z) {
                copyOnWrite();
                ((Value) this.instance).setBoolean(z);
                return this;
            }

            public Builder clearBoolean() {
                copyOnWrite();
                ((Value) this.instance).clearBoolean();
                return this;
            }

            public boolean hasFloat() {
                return ((Value) this.instance).hasFloat();
            }

            public float getFloat() {
                return ((Value) this.instance).getFloat();
            }

            public Builder setFloat(float f) {
                copyOnWrite();
                ((Value) this.instance).setFloat(f);
                return this;
            }

            public Builder clearFloat() {
                copyOnWrite();
                ((Value) this.instance).clearFloat();
                return this;
            }

            public boolean hasInteger() {
                return ((Value) this.instance).hasInteger();
            }

            public int getInteger() {
                return ((Value) this.instance).getInteger();
            }

            public Builder setInteger(int i) {
                copyOnWrite();
                ((Value) this.instance).setInteger(i);
                return this;
            }

            public Builder clearInteger() {
                copyOnWrite();
                ((Value) this.instance).clearInteger();
                return this;
            }

            public boolean hasLong() {
                return ((Value) this.instance).hasLong();
            }

            public long getLong() {
                return ((Value) this.instance).getLong();
            }

            public Builder setLong(long j) {
                copyOnWrite();
                ((Value) this.instance).setLong(j);
                return this;
            }

            public Builder clearLong() {
                copyOnWrite();
                ((Value) this.instance).clearLong();
                return this;
            }

            public boolean hasString() {
                return ((Value) this.instance).hasString();
            }

            public String getString() {
                return ((Value) this.instance).getString();
            }

            public ByteString getStringBytes() {
                return ((Value) this.instance).getStringBytes();
            }

            public Builder setString(String str) {
                copyOnWrite();
                ((Value) this.instance).setString(str);
                return this;
            }

            public Builder clearString() {
                copyOnWrite();
                ((Value) this.instance).clearString();
                return this;
            }

            public Builder setStringBytes(ByteString byteString) {
                copyOnWrite();
                ((Value) this.instance).setStringBytes(byteString);
                return this;
            }

            public boolean hasStringSet() {
                return ((Value) this.instance).hasStringSet();
            }

            public StringSet getStringSet() {
                return ((Value) this.instance).getStringSet();
            }

            public Builder setStringSet(StringSet stringSet) {
                copyOnWrite();
                ((Value) this.instance).setStringSet(stringSet);
                return this;
            }

            public Builder setStringSet(StringSet.Builder builder) {
                copyOnWrite();
                ((Value) this.instance).setStringSet(builder);
                return this;
            }

            public Builder mergeStringSet(StringSet stringSet) {
                copyOnWrite();
                ((Value) this.instance).mergeStringSet(stringSet);
                return this;
            }

            public Builder clearStringSet() {
                copyOnWrite();
                ((Value) this.instance).clearStringSet();
                return this;
            }

            public boolean hasDouble() {
                return ((Value) this.instance).hasDouble();
            }

            public double getDouble() {
                return ((Value) this.instance).getDouble();
            }

            public Builder setDouble(double d) {
                copyOnWrite();
                ((Value) this.instance).setDouble(d);
                return this;
            }

            public Builder clearDouble() {
                copyOnWrite();
                ((Value) this.instance).clearDouble();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new Value();
                case 2:
                    return new Builder((AnonymousClass1) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0007\u0001\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001:\u0000\u00024\u0000\u00037\u0000\u00045\u0000\u0005;\u0000\u0006<\u0000\u00073\u0000", new Object[]{"value_", "valueCase_", "bitField0_", StringSet.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Value> parser = PARSER;
                    if (parser == null) {
                        synchronized (Value.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            Value value = new Value();
            DEFAULT_INSTANCE = value;
            GeneratedMessageLite.registerDefaultInstance(Value.class, value);
        }

        public static Value getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Value> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class StringSet extends GeneratedMessageLite<StringSet, Builder> implements StringSetOrBuilder {
        /* access modifiers changed from: private */
        public static final StringSet DEFAULT_INSTANCE;
        private static volatile Parser<StringSet> PARSER = null;
        public static final int STRINGS_FIELD_NUMBER = 1;
        private Internal.ProtobufList<String> strings_ = GeneratedMessageLite.emptyProtobufList();

        private StringSet() {
        }

        public List<String> getStringsList() {
            return this.strings_;
        }

        public int getStringsCount() {
            return this.strings_.size();
        }

        public String getStrings(int i) {
            return (String) this.strings_.get(i);
        }

        public ByteString getStringsBytes(int i) {
            return ByteString.copyFromUtf8((String) this.strings_.get(i));
        }

        private void ensureStringsIsMutable() {
            if (!this.strings_.isModifiable()) {
                this.strings_ = GeneratedMessageLite.mutableCopy(this.strings_);
            }
        }

        /* access modifiers changed from: private */
        public void setStrings(int i, String str) {
            str.getClass();
            ensureStringsIsMutable();
            this.strings_.set(i, str);
        }

        /* access modifiers changed from: private */
        public void addStrings(String str) {
            str.getClass();
            ensureStringsIsMutable();
            this.strings_.add(str);
        }

        /* access modifiers changed from: private */
        public void addAllStrings(Iterable<String> iterable) {
            ensureStringsIsMutable();
            AbstractMessageLite.addAll(iterable, this.strings_);
        }

        /* access modifiers changed from: private */
        public void clearStrings() {
            this.strings_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void addStringsBytes(ByteString byteString) {
            byteString.getClass();
            ensureStringsIsMutable();
            this.strings_.add(byteString.toStringUtf8());
        }

        public static StringSet parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (StringSet) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static StringSet parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StringSet) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static StringSet parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (StringSet) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static StringSet parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StringSet) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static StringSet parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (StringSet) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static StringSet parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StringSet) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static StringSet parseFrom(InputStream inputStream) throws IOException {
            return (StringSet) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static StringSet parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StringSet) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static StringSet parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (StringSet) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static StringSet parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StringSet) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static StringSet parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (StringSet) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static StringSet parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StringSet) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(StringSet stringSet) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(stringSet);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<StringSet, Builder> implements StringSetOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(StringSet.DEFAULT_INSTANCE);
            }

            public List<String> getStringsList() {
                return Collections.unmodifiableList(((StringSet) this.instance).getStringsList());
            }

            public int getStringsCount() {
                return ((StringSet) this.instance).getStringsCount();
            }

            public String getStrings(int i) {
                return ((StringSet) this.instance).getStrings(i);
            }

            public ByteString getStringsBytes(int i) {
                return ((StringSet) this.instance).getStringsBytes(i);
            }

            public Builder setStrings(int i, String str) {
                copyOnWrite();
                ((StringSet) this.instance).setStrings(i, str);
                return this;
            }

            public Builder addStrings(String str) {
                copyOnWrite();
                ((StringSet) this.instance).addStrings(str);
                return this;
            }

            public Builder addAllStrings(Iterable<String> iterable) {
                copyOnWrite();
                ((StringSet) this.instance).addAllStrings(iterable);
                return this;
            }

            public Builder clearStrings() {
                copyOnWrite();
                ((StringSet) this.instance).clearStrings();
                return this;
            }

            public Builder addStringsBytes(ByteString byteString) {
                copyOnWrite();
                ((StringSet) this.instance).addStringsBytes(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new StringSet();
                case 2:
                    return new Builder((AnonymousClass1) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", new Object[]{"strings_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<StringSet> parser = PARSER;
                    if (parser == null) {
                        synchronized (StringSet.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            StringSet stringSet = new StringSet();
            DEFAULT_INSTANCE = stringSet;
            GeneratedMessageLite.registerDefaultInstance(StringSet.class, stringSet);
        }

        public static StringSet getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<StringSet> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
