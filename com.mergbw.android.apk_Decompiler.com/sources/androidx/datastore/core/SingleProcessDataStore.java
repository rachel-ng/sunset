package androidx.datastore.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\f\b\u0000\u0018\u0000 F*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0003FGHB\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012?\b\u0002\u0010\b\u001a9\u00125\u00123\b\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00110\n0\t\u0012\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015ø\u0001\u0000¢\u0006\u0002\u0010\u0016J\u001f\u0010+\u001a\u00020\u00102\f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000-H@ø\u0001\u0000¢\u0006\u0002\u0010.J\u001f\u0010/\u001a\u00020\u00102\f\u00100\u001a\b\u0012\u0004\u0012\u00028\u000001H@ø\u0001\u0000¢\u0006\u0002\u00102J\u0011\u00103\u001a\u00020\u0010H@ø\u0001\u0000¢\u0006\u0002\u00104J\u0011\u00105\u001a\u00020\u0010H@ø\u0001\u0000¢\u0006\u0002\u00104J\u0011\u00106\u001a\u00020\u0010H@ø\u0001\u0000¢\u0006\u0002\u00104J\u0011\u00107\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u00104J\u0011\u00108\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u00104JL\u00109\u001a\u00028\u000021\u0010:\u001a-\b\u0001\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(;\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00110\n2\u0006\u0010<\u001a\u00020=H@ø\u0001\u0000¢\u0006\u0002\u0010>JD\u0010?\u001a\u00028\u000021\u0010:\u001a-\b\u0001\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(;\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00110\nH@ø\u0001\u0000¢\u0006\u0002\u0010@J\u001b\u0010A\u001a\u00020\u00102\u0006\u0010B\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0004\bC\u0010DJ\f\u0010E\u001a\u00020\u0010*\u00020\u0005H\u0002R\u000e\u0010\u0017\u001a\u00020\u0018XD¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001b0\u001aX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u001dX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR \u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\"0!X\u0004¢\u0006\b\n\u0000\u0012\u0004\b#\u0010$R\u001b\u0010%\u001a\u00020\u00058BX\u0002¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b&\u0010'RJ\u0010*\u001a;\u00125\u00123\b\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00110\n\u0018\u00010\tX\u000eø\u0001\u0000¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006I"}, d2 = {"Landroidx/datastore/core/SingleProcessDataStore;", "T", "Landroidx/datastore/core/DataStore;", "produceFile", "Lkotlin/Function0;", "Ljava/io/File;", "serializer", "Landroidx/datastore/core/Serializer;", "initTasksList", "", "Lkotlin/Function2;", "Landroidx/datastore/core/InitializerApi;", "Lkotlin/ParameterName;", "name", "api", "Lkotlin/coroutines/Continuation;", "", "", "corruptionHandler", "Landroidx/datastore/core/CorruptionHandler;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Lkotlin/jvm/functions/Function0;Landroidx/datastore/core/Serializer;Ljava/util/List;Landroidx/datastore/core/CorruptionHandler;Lkotlinx/coroutines/CoroutineScope;)V", "SCRATCH_SUFFIX", "", "actor", "Landroidx/datastore/core/SimpleActor;", "Landroidx/datastore/core/SingleProcessDataStore$Message;", "data", "Lkotlinx/coroutines/flow/Flow;", "getData", "()Lkotlinx/coroutines/flow/Flow;", "downstreamFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Landroidx/datastore/core/State;", "getDownstreamFlow$annotations", "()V", "file", "getFile", "()Ljava/io/File;", "file$delegate", "Lkotlin/Lazy;", "initTasks", "handleRead", "read", "Landroidx/datastore/core/SingleProcessDataStore$Message$Read;", "(Landroidx/datastore/core/SingleProcessDataStore$Message$Read;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleUpdate", "update", "Landroidx/datastore/core/SingleProcessDataStore$Message$Update;", "(Landroidx/datastore/core/SingleProcessDataStore$Message$Update;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readAndInit", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readAndInitOrPropagateAndThrowFailure", "readAndInitOrPropagateFailure", "readData", "readDataOrHandleCorruption", "transformAndWrite", "transform", "t", "callerContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateData", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeData", "newData", "writeData$datastore_core", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createParentDirectories", "Companion", "Message", "UncloseableOutputStream", "datastore-core"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* compiled from: SingleProcessDataStore.kt */
public final class SingleProcessDataStore<T> implements DataStore<T> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Set<String> activeFiles = new LinkedHashSet();
    /* access modifiers changed from: private */
    public static final Object activeFilesLock = new Object();
    private final String SCRATCH_SUFFIX;
    /* access modifiers changed from: private */
    public final SimpleActor<Message<T>> actor;
    private final CorruptionHandler<T> corruptionHandler;
    private final Flow<T> data;
    /* access modifiers changed from: private */
    public final MutableStateFlow<State<T>> downstreamFlow;
    private final Lazy file$delegate;
    private List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> initTasks;
    /* access modifiers changed from: private */
    public final Function0<File> produceFile;
    private final CoroutineScope scope;
    private final Serializer<T> serializer;

    private static /* synthetic */ void getDownstreamFlow$annotations() {
    }

    public SingleProcessDataStore(Function0<? extends File> function0, Serializer<T> serializer2, List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> list, CorruptionHandler<T> corruptionHandler2, CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(function0, "produceFile");
        Intrinsics.checkNotNullParameter(serializer2, "serializer");
        Intrinsics.checkNotNullParameter(list, "initTasksList");
        Intrinsics.checkNotNullParameter(corruptionHandler2, "corruptionHandler");
        Intrinsics.checkNotNullParameter(coroutineScope, "scope");
        this.produceFile = function0;
        this.serializer = serializer2;
        this.corruptionHandler = corruptionHandler2;
        this.scope = coroutineScope;
        this.data = FlowKt.flow(new SingleProcessDataStore$data$1(this, (Continuation<? super SingleProcessDataStore$data$1>) null));
        this.SCRATCH_SUFFIX = ".tmp";
        this.file$delegate = LazyKt.lazy(new SingleProcessDataStore$file$2(this));
        this.downstreamFlow = StateFlowKt.MutableStateFlow(UnInitialized.INSTANCE);
        this.initTasks = CollectionsKt.toList(list);
        this.actor = new SimpleActor<>(coroutineScope, new SingleProcessDataStore$actor$1(this), SingleProcessDataStore$actor$2.INSTANCE, new SingleProcessDataStore$actor$3(this, (Continuation<? super SingleProcessDataStore$actor$3>) null));
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ SingleProcessDataStore(kotlin.jvm.functions.Function0 r7, androidx.datastore.core.Serializer r8, java.util.List r9, androidx.datastore.core.CorruptionHandler r10, kotlinx.coroutines.CoroutineScope r11, int r12, kotlin.jvm.internal.DefaultConstructorMarker r13) {
        /*
            r6 = this;
            r13 = r12 & 4
            if (r13 == 0) goto L_0x0008
            java.util.List r9 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0008:
            r3 = r9
            r9 = r12 & 8
            if (r9 == 0) goto L_0x0015
            androidx.datastore.core.handlers.NoOpCorruptionHandler r9 = new androidx.datastore.core.handlers.NoOpCorruptionHandler
            r9.<init>()
            r10 = r9
            androidx.datastore.core.CorruptionHandler r10 = (androidx.datastore.core.CorruptionHandler) r10
        L_0x0015:
            r4 = r10
            r9 = r12 & 16
            if (r9 == 0) goto L_0x0030
            kotlinx.coroutines.Dispatchers r9 = kotlinx.coroutines.Dispatchers.INSTANCE
            kotlinx.coroutines.CoroutineDispatcher r9 = kotlinx.coroutines.Dispatchers.getIO()
            r10 = 1
            r11 = 0
            kotlinx.coroutines.CompletableJob r10 = kotlinx.coroutines.SupervisorKt.SupervisorJob$default((kotlinx.coroutines.Job) r11, (int) r10, (java.lang.Object) r11)
            kotlin.coroutines.CoroutineContext r10 = (kotlin.coroutines.CoroutineContext) r10
            kotlin.coroutines.CoroutineContext r9 = r9.plus(r10)
            kotlinx.coroutines.CoroutineScope r11 = kotlinx.coroutines.CoroutineScopeKt.CoroutineScope(r9)
        L_0x0030:
            r5 = r11
            r0 = r6
            r1 = r7
            r2 = r8
            r0.<init>(r1, r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.SingleProcessDataStore.<init>(kotlin.jvm.functions.Function0, androidx.datastore.core.Serializer, java.util.List, androidx.datastore.core.CorruptionHandler, kotlinx.coroutines.CoroutineScope, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public Flow<T> getData() {
        return this.data;
    }

    public Object updateData(Function2<? super T, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        CompletableDeferred CompletableDeferred$default = CompletableDeferredKt.CompletableDeferred$default((Job) null, 1, (Object) null);
        this.actor.offer(new Message.Update(function2, CompletableDeferred$default, this.downstreamFlow.getValue(), continuation.getContext()));
        return CompletableDeferred$default.await(continuation);
    }

    /* access modifiers changed from: private */
    public final File getFile() {
        return (File) this.file$delegate.getValue();
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002:\u0002\b\tB\u0007\b\u0004¢\u0006\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0001\u0002\n\u000b¨\u0006\f"}, d2 = {"Landroidx/datastore/core/SingleProcessDataStore$Message;", "T", "", "()V", "lastState", "Landroidx/datastore/core/State;", "getLastState", "()Landroidx/datastore/core/State;", "Read", "Update", "Landroidx/datastore/core/SingleProcessDataStore$Message$Read;", "Landroidx/datastore/core/SingleProcessDataStore$Message$Update;", "datastore-core"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* compiled from: SingleProcessDataStore.kt */
    private static abstract class Message<T> {
        public /* synthetic */ Message(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public abstract State<T> getLastState();

        private Message() {
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0002\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\u000e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028\u0002\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005R\u001c\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028\u0002\u0018\u00010\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/datastore/core/SingleProcessDataStore$Message$Read;", "T", "Landroidx/datastore/core/SingleProcessDataStore$Message;", "lastState", "Landroidx/datastore/core/State;", "(Landroidx/datastore/core/State;)V", "getLastState", "()Landroidx/datastore/core/State;", "datastore-core"}, k = 1, mv = {1, 5, 1}, xi = 48)
        /* compiled from: SingleProcessDataStore.kt */
        public static final class Read<T> extends Message<T> {
            private final State<T> lastState;

            public State<T> getLastState() {
                return this.lastState;
            }

            public Read(State<T> state) {
                super((DefaultConstructorMarker) null);
                this.lastState = state;
            }
        }

        @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000*\u0004\b\u0002\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002Ba\u00121\u0010\u0003\u001a-\b\u0001\u0012\u0013\u0012\u00118\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0004\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00020\u000b\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0002\u0018\u00010\r\u0012\u0006\u0010\u000e\u001a\u00020\u000fø\u0001\u0000¢\u0006\u0002\u0010\u0010R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0002\u0018\u00010\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016RA\u0010\u0003\u001a-\b\u0001\u0012\u0013\u0012\u00118\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Landroidx/datastore/core/SingleProcessDataStore$Message$Update;", "T", "Landroidx/datastore/core/SingleProcessDataStore$Message;", "transform", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "t", "Lkotlin/coroutines/Continuation;", "", "ack", "Lkotlinx/coroutines/CompletableDeferred;", "lastState", "Landroidx/datastore/core/State;", "callerContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/jvm/functions/Function2;Lkotlinx/coroutines/CompletableDeferred;Landroidx/datastore/core/State;Lkotlin/coroutines/CoroutineContext;)V", "getAck", "()Lkotlinx/coroutines/CompletableDeferred;", "getCallerContext", "()Lkotlin/coroutines/CoroutineContext;", "getLastState", "()Landroidx/datastore/core/State;", "getTransform", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function2;", "datastore-core"}, k = 1, mv = {1, 5, 1}, xi = 48)
        /* compiled from: SingleProcessDataStore.kt */
        public static final class Update<T> extends Message<T> {
            private final CompletableDeferred<T> ack;
            private final CoroutineContext callerContext;
            private final State<T> lastState;
            private final Function2<T, Continuation<? super T>, Object> transform;

            public final Function2<T, Continuation<? super T>, Object> getTransform() {
                return this.transform;
            }

            public final CompletableDeferred<T> getAck() {
                return this.ack;
            }

            public State<T> getLastState() {
                return this.lastState;
            }

            public final CoroutineContext getCallerContext() {
                return this.callerContext;
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Update(Function2<? super T, ? super Continuation<? super T>, ? extends Object> function2, CompletableDeferred<T> completableDeferred, State<T> state, CoroutineContext coroutineContext) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(function2, "transform");
                Intrinsics.checkNotNullParameter(completableDeferred, "ack");
                Intrinsics.checkNotNullParameter(coroutineContext, "callerContext");
                this.transform = function2;
                this.ack = completableDeferred;
                this.lastState = state;
                this.callerContext = coroutineContext;
            }
        }
    }

    /* access modifiers changed from: private */
    public final Object handleRead(Message.Read<T> read, Continuation<? super Unit> continuation) {
        State<T> value = this.downstreamFlow.getValue();
        if (!(value instanceof Data)) {
            if (value instanceof ReadException) {
                if (value == read.getLastState()) {
                    Object readAndInitOrPropagateFailure = readAndInitOrPropagateFailure(continuation);
                    return readAndInitOrPropagateFailure == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? readAndInitOrPropagateFailure : Unit.INSTANCE;
                }
            } else if (Intrinsics.areEqual((Object) value, (Object) UnInitialized.INSTANCE)) {
                Object readAndInitOrPropagateFailure2 = readAndInitOrPropagateFailure(continuation);
                return readAndInitOrPropagateFailure2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? readAndInitOrPropagateFailure2 : Unit.INSTANCE;
            } else if (value instanceof Final) {
                throw new IllegalStateException("Can't read in final state.".toString());
            }
        }
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00bc A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object handleUpdate(androidx.datastore.core.SingleProcessDataStore.Message.Update<T> r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof androidx.datastore.core.SingleProcessDataStore$handleUpdate$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            androidx.datastore.core.SingleProcessDataStore$handleUpdate$1 r0 = (androidx.datastore.core.SingleProcessDataStore$handleUpdate$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            androidx.datastore.core.SingleProcessDataStore$handleUpdate$1 r0 = new androidx.datastore.core.SingleProcessDataStore$handleUpdate$1
            r0.<init>(r8, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0057
            if (r2 == r5) goto L_0x004b
            if (r2 == r4) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.CompletableDeferred r9 = (kotlinx.coroutines.CompletableDeferred) r9
            goto L_0x004f
        L_0x0031:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0039:
            java.lang.Object r9 = r0.L$2
            kotlinx.coroutines.CompletableDeferred r9 = (kotlinx.coroutines.CompletableDeferred) r9
            java.lang.Object r2 = r0.L$1
            androidx.datastore.core.SingleProcessDataStore r2 = (androidx.datastore.core.SingleProcessDataStore) r2
            java.lang.Object r4 = r0.L$0
            androidx.datastore.core.SingleProcessDataStore$Message$Update r4 = (androidx.datastore.core.SingleProcessDataStore.Message.Update) r4
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0054 }
            r10 = r9
            r9 = r4
            goto L_0x00a5
        L_0x004b:
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.CompletableDeferred r9 = (kotlinx.coroutines.CompletableDeferred) r9
        L_0x004f:
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0054 }
            goto L_0x00bd
        L_0x0054:
            r10 = move-exception
            goto L_0x00de
        L_0x0057:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlinx.coroutines.CompletableDeferred r10 = r9.getAck()
            kotlin.Result$Companion r2 = kotlin.Result.Companion     // Catch:{ all -> 0x00da }
            r2 = r8
            androidx.datastore.core.SingleProcessDataStore r2 = (androidx.datastore.core.SingleProcessDataStore) r2     // Catch:{ all -> 0x00da }
            kotlinx.coroutines.flow.MutableStateFlow<androidx.datastore.core.State<T>> r2 = r8.downstreamFlow     // Catch:{ all -> 0x00da }
            java.lang.Object r2 = r2.getValue()     // Catch:{ all -> 0x00da }
            androidx.datastore.core.State r2 = (androidx.datastore.core.State) r2     // Catch:{ all -> 0x00da }
            boolean r6 = r2 instanceof androidx.datastore.core.Data     // Catch:{ all -> 0x00da }
            if (r6 == 0) goto L_0x0086
            kotlin.jvm.functions.Function2 r2 = r9.getTransform()     // Catch:{ all -> 0x00da }
            kotlin.coroutines.CoroutineContext r9 = r9.getCallerContext()     // Catch:{ all -> 0x00da }
            r0.L$0 = r10     // Catch:{ all -> 0x00da }
            r0.label = r5     // Catch:{ all -> 0x00da }
            java.lang.Object r9 = r8.transformAndWrite(r2, r9, r0)     // Catch:{ all -> 0x00da }
            if (r9 != r1) goto L_0x0082
            return r1
        L_0x0082:
            r7 = r10
            r10 = r9
            r9 = r7
            goto L_0x00bd
        L_0x0086:
            boolean r6 = r2 instanceof androidx.datastore.core.ReadException     // Catch:{ all -> 0x00da }
            if (r6 == 0) goto L_0x008b
            goto L_0x008d
        L_0x008b:
            boolean r5 = r2 instanceof androidx.datastore.core.UnInitialized     // Catch:{ all -> 0x00da }
        L_0x008d:
            if (r5 == 0) goto L_0x00c9
            androidx.datastore.core.State r5 = r9.getLastState()     // Catch:{ all -> 0x00da }
            if (r2 != r5) goto L_0x00c2
            r0.L$0 = r9     // Catch:{ all -> 0x00da }
            r0.L$1 = r8     // Catch:{ all -> 0x00da }
            r0.L$2 = r10     // Catch:{ all -> 0x00da }
            r0.label = r4     // Catch:{ all -> 0x00da }
            java.lang.Object r2 = r8.readAndInitOrPropagateAndThrowFailure(r0)     // Catch:{ all -> 0x00da }
            if (r2 != r1) goto L_0x00a4
            return r1
        L_0x00a4:
            r2 = r8
        L_0x00a5:
            kotlin.jvm.functions.Function2 r4 = r9.getTransform()     // Catch:{ all -> 0x00da }
            kotlin.coroutines.CoroutineContext r9 = r9.getCallerContext()     // Catch:{ all -> 0x00da }
            r0.L$0 = r10     // Catch:{ all -> 0x00da }
            r5 = 0
            r0.L$1 = r5     // Catch:{ all -> 0x00da }
            r0.L$2 = r5     // Catch:{ all -> 0x00da }
            r0.label = r3     // Catch:{ all -> 0x00da }
            java.lang.Object r9 = r2.transformAndWrite(r4, r9, r0)     // Catch:{ all -> 0x00da }
            if (r9 != r1) goto L_0x0082
            return r1
        L_0x00bd:
            java.lang.Object r10 = kotlin.Result.m986constructorimpl(r10)     // Catch:{ all -> 0x0054 }
            goto L_0x00e8
        L_0x00c2:
            androidx.datastore.core.ReadException r2 = (androidx.datastore.core.ReadException) r2     // Catch:{ all -> 0x00da }
            java.lang.Throwable r9 = r2.getReadException()     // Catch:{ all -> 0x00da }
            throw r9     // Catch:{ all -> 0x00da }
        L_0x00c9:
            boolean r9 = r2 instanceof androidx.datastore.core.Final     // Catch:{ all -> 0x00da }
            if (r9 == 0) goto L_0x00d4
            androidx.datastore.core.Final r2 = (androidx.datastore.core.Final) r2     // Catch:{ all -> 0x00da }
            java.lang.Throwable r9 = r2.getFinalException()     // Catch:{ all -> 0x00da }
            throw r9     // Catch:{ all -> 0x00da }
        L_0x00d4:
            kotlin.NoWhenBranchMatchedException r9 = new kotlin.NoWhenBranchMatchedException     // Catch:{ all -> 0x00da }
            r9.<init>()     // Catch:{ all -> 0x00da }
            throw r9     // Catch:{ all -> 0x00da }
        L_0x00da:
            r9 = move-exception
            r7 = r10
            r10 = r9
            r9 = r7
        L_0x00de:
            kotlin.Result$Companion r0 = kotlin.Result.Companion
            java.lang.Object r10 = kotlin.ResultKt.createFailure(r10)
            java.lang.Object r10 = kotlin.Result.m986constructorimpl(r10)
        L_0x00e8:
            kotlinx.coroutines.CompletableDeferredKt.completeWith(r9, r10)
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.SingleProcessDataStore.handleUpdate(androidx.datastore.core.SingleProcessDataStore$Message$Update, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readAndInitOrPropagateAndThrowFailure(kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof androidx.datastore.core.SingleProcessDataStore$readAndInitOrPropagateAndThrowFailure$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            androidx.datastore.core.SingleProcessDataStore$readAndInitOrPropagateAndThrowFailure$1 r0 = (androidx.datastore.core.SingleProcessDataStore$readAndInitOrPropagateAndThrowFailure$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            androidx.datastore.core.SingleProcessDataStore$readAndInitOrPropagateAndThrowFailure$1 r0 = new androidx.datastore.core.SingleProcessDataStore$readAndInitOrPropagateAndThrowFailure$1
            r0.<init>(r4, r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0038
            if (r2 != r3) goto L_0x0030
            java.lang.Object r0 = r0.L$0
            androidx.datastore.core.SingleProcessDataStore r0 = (androidx.datastore.core.SingleProcessDataStore) r0
            kotlin.ResultKt.throwOnFailure(r5)     // Catch:{ all -> 0x002e }
            goto L_0x0046
        L_0x002e:
            r5 = move-exception
            goto L_0x004b
        L_0x0030:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L_0x0038:
            kotlin.ResultKt.throwOnFailure(r5)
            r0.L$0 = r4     // Catch:{ all -> 0x0049 }
            r0.label = r3     // Catch:{ all -> 0x0049 }
            java.lang.Object r5 = r4.readAndInit(r0)     // Catch:{ all -> 0x0049 }
            if (r5 != r1) goto L_0x0046
            return r1
        L_0x0046:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L_0x0049:
            r5 = move-exception
            r0 = r4
        L_0x004b:
            kotlinx.coroutines.flow.MutableStateFlow<androidx.datastore.core.State<T>> r0 = r0.downstreamFlow
            androidx.datastore.core.ReadException r1 = new androidx.datastore.core.ReadException
            r1.<init>(r5)
            r0.setValue(r1)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.SingleProcessDataStore.readAndInitOrPropagateAndThrowFailure(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readAndInitOrPropagateFailure(kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof androidx.datastore.core.SingleProcessDataStore$readAndInitOrPropagateFailure$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            androidx.datastore.core.SingleProcessDataStore$readAndInitOrPropagateFailure$1 r0 = (androidx.datastore.core.SingleProcessDataStore$readAndInitOrPropagateFailure$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            androidx.datastore.core.SingleProcessDataStore$readAndInitOrPropagateFailure$1 r0 = new androidx.datastore.core.SingleProcessDataStore$readAndInitOrPropagateFailure$1
            r0.<init>(r4, r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0038
            if (r2 != r3) goto L_0x0030
            java.lang.Object r0 = r0.L$0
            androidx.datastore.core.SingleProcessDataStore r0 = (androidx.datastore.core.SingleProcessDataStore) r0
            kotlin.ResultKt.throwOnFailure(r5)     // Catch:{ all -> 0x002e }
            goto L_0x0052
        L_0x002e:
            r5 = move-exception
            goto L_0x0048
        L_0x0030:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L_0x0038:
            kotlin.ResultKt.throwOnFailure(r5)
            r0.L$0 = r4     // Catch:{ all -> 0x0046 }
            r0.label = r3     // Catch:{ all -> 0x0046 }
            java.lang.Object r5 = r4.readAndInit(r0)     // Catch:{ all -> 0x0046 }
            if (r5 != r1) goto L_0x0052
            return r1
        L_0x0046:
            r5 = move-exception
            r0 = r4
        L_0x0048:
            kotlinx.coroutines.flow.MutableStateFlow<androidx.datastore.core.State<T>> r0 = r0.downstreamFlow
            androidx.datastore.core.ReadException r1 = new androidx.datastore.core.ReadException
            r1.<init>(r5)
            r0.setValue(r1)
        L_0x0052:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.SingleProcessDataStore.readAndInitOrPropagateFailure(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0115 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0116  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0129  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readAndInit(kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r13 = this;
            boolean r0 = r14 instanceof androidx.datastore.core.SingleProcessDataStore$readAndInit$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            androidx.datastore.core.SingleProcessDataStore$readAndInit$1 r0 = (androidx.datastore.core.SingleProcessDataStore$readAndInit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            androidx.datastore.core.SingleProcessDataStore$readAndInit$1 r0 = new androidx.datastore.core.SingleProcessDataStore$readAndInit$1
            r0.<init>(r13, r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 0
            r6 = 1
            r7 = 0
            if (r2 == 0) goto L_0x007c
            if (r2 == r6) goto L_0x0068
            if (r2 == r4) goto L_0x004b
            if (r2 != r3) goto L_0x0043
            java.lang.Object r1 = r0.L$3
            kotlinx.coroutines.sync.Mutex r1 = (kotlinx.coroutines.sync.Mutex) r1
            java.lang.Object r2 = r0.L$2
            kotlin.jvm.internal.Ref$BooleanRef r2 = (kotlin.jvm.internal.Ref.BooleanRef) r2
            java.lang.Object r3 = r0.L$1
            kotlin.jvm.internal.Ref$ObjectRef r3 = (kotlin.jvm.internal.Ref.ObjectRef) r3
            java.lang.Object r0 = r0.L$0
            androidx.datastore.core.SingleProcessDataStore r0 = (androidx.datastore.core.SingleProcessDataStore) r0
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0118
        L_0x0043:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L_0x004b:
            java.lang.Object r2 = r0.L$5
            java.util.Iterator r2 = (java.util.Iterator) r2
            java.lang.Object r8 = r0.L$4
            androidx.datastore.core.SingleProcessDataStore$readAndInit$api$1 r8 = (androidx.datastore.core.SingleProcessDataStore$readAndInit$api$1) r8
            java.lang.Object r9 = r0.L$3
            kotlin.jvm.internal.Ref$BooleanRef r9 = (kotlin.jvm.internal.Ref.BooleanRef) r9
            java.lang.Object r10 = r0.L$2
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r11 = r0.L$1
            kotlinx.coroutines.sync.Mutex r11 = (kotlinx.coroutines.sync.Mutex) r11
            java.lang.Object r12 = r0.L$0
            androidx.datastore.core.SingleProcessDataStore r12 = (androidx.datastore.core.SingleProcessDataStore) r12
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x00d9
        L_0x0068:
            java.lang.Object r2 = r0.L$3
            kotlin.jvm.internal.Ref$ObjectRef r2 = (kotlin.jvm.internal.Ref.ObjectRef) r2
            java.lang.Object r8 = r0.L$2
            kotlin.jvm.internal.Ref$ObjectRef r8 = (kotlin.jvm.internal.Ref.ObjectRef) r8
            java.lang.Object r9 = r0.L$1
            kotlinx.coroutines.sync.Mutex r9 = (kotlinx.coroutines.sync.Mutex) r9
            java.lang.Object r10 = r0.L$0
            androidx.datastore.core.SingleProcessDataStore r10 = (androidx.datastore.core.SingleProcessDataStore) r10
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x00b9
        L_0x007c:
            kotlin.ResultKt.throwOnFailure(r14)
            kotlinx.coroutines.flow.MutableStateFlow<androidx.datastore.core.State<T>> r14 = r13.downstreamFlow
            java.lang.Object r14 = r14.getValue()
            androidx.datastore.core.UnInitialized r2 = androidx.datastore.core.UnInitialized.INSTANCE
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r2)
            if (r14 != 0) goto L_0x009a
            kotlinx.coroutines.flow.MutableStateFlow<androidx.datastore.core.State<T>> r14 = r13.downstreamFlow
            java.lang.Object r14 = r14.getValue()
            boolean r14 = r14 instanceof androidx.datastore.core.ReadException
            if (r14 == 0) goto L_0x0098
            goto L_0x009a
        L_0x0098:
            r14 = r5
            goto L_0x009b
        L_0x009a:
            r14 = r6
        L_0x009b:
            if (r14 == 0) goto L_0x013b
            kotlinx.coroutines.sync.Mutex r9 = kotlinx.coroutines.sync.MutexKt.Mutex$default(r5, r6, r7)
            kotlin.jvm.internal.Ref$ObjectRef r2 = new kotlin.jvm.internal.Ref$ObjectRef
            r2.<init>()
            r0.L$0 = r13
            r0.L$1 = r9
            r0.L$2 = r2
            r0.L$3 = r2
            r0.label = r6
            java.lang.Object r14 = r13.readDataOrHandleCorruption(r0)
            if (r14 != r1) goto L_0x00b7
            return r1
        L_0x00b7:
            r10 = r13
            r8 = r2
        L_0x00b9:
            r2.element = r14
            kotlin.jvm.internal.Ref$BooleanRef r14 = new kotlin.jvm.internal.Ref$BooleanRef
            r14.<init>()
            androidx.datastore.core.SingleProcessDataStore$readAndInit$api$1 r2 = new androidx.datastore.core.SingleProcessDataStore$readAndInit$api$1
            r2.<init>(r9, r14, r8, r10)
            java.util.List<? extends kotlin.jvm.functions.Function2<? super androidx.datastore.core.InitializerApi<T>, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object>> r11 = r10.initTasks
            if (r11 != 0) goto L_0x00cd
            r2 = r14
            r14 = r0
            r0 = r10
            goto L_0x00ff
        L_0x00cd:
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.util.Iterator r11 = r11.iterator()
            r12 = r10
            r10 = r8
            r8 = r2
            r2 = r11
            r11 = r9
            r9 = r14
        L_0x00d9:
            boolean r14 = r2.hasNext()
            if (r14 == 0) goto L_0x00fa
            java.lang.Object r14 = r2.next()
            kotlin.jvm.functions.Function2 r14 = (kotlin.jvm.functions.Function2) r14
            r0.L$0 = r12
            r0.L$1 = r11
            r0.L$2 = r10
            r0.L$3 = r9
            r0.L$4 = r8
            r0.L$5 = r2
            r0.label = r4
            java.lang.Object r14 = r14.invoke(r8, r0)
            if (r14 != r1) goto L_0x00d9
            return r1
        L_0x00fa:
            r14 = r0
            r2 = r9
            r8 = r10
            r9 = r11
            r0 = r12
        L_0x00ff:
            r0.initTasks = r7
            r14.L$0 = r0
            r14.L$1 = r8
            r14.L$2 = r2
            r14.L$3 = r9
            r14.L$4 = r7
            r14.L$5 = r7
            r14.label = r3
            java.lang.Object r14 = r9.lock(r7, r14)
            if (r14 != r1) goto L_0x0116
            return r1
        L_0x0116:
            r3 = r8
            r1 = r9
        L_0x0118:
            r2.element = r6     // Catch:{ all -> 0x0136 }
            kotlin.Unit r14 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0136 }
            r1.unlock(r7)
            kotlinx.coroutines.flow.MutableStateFlow<androidx.datastore.core.State<T>> r14 = r0.downstreamFlow
            androidx.datastore.core.Data r0 = new androidx.datastore.core.Data
            T r1 = r3.element
            T r2 = r3.element
            if (r2 == 0) goto L_0x012d
            int r5 = r2.hashCode()
        L_0x012d:
            r0.<init>(r1, r5)
            r14.setValue(r0)
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L_0x0136:
            r14 = move-exception
            r1.unlock(r7)
            throw r14
        L_0x013b:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "Check failed."
            java.lang.String r0 = r0.toString()
            r14.<init>(r0)
            java.lang.Throwable r14 = (java.lang.Throwable) r14
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.SingleProcessDataStore.readAndInit(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0086 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readDataOrHandleCorruption(kotlin.coroutines.Continuation<? super T> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof androidx.datastore.core.SingleProcessDataStore$readDataOrHandleCorruption$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            androidx.datastore.core.SingleProcessDataStore$readDataOrHandleCorruption$1 r0 = (androidx.datastore.core.SingleProcessDataStore$readDataOrHandleCorruption$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            androidx.datastore.core.SingleProcessDataStore$readDataOrHandleCorruption$1 r0 = new androidx.datastore.core.SingleProcessDataStore$readDataOrHandleCorruption$1
            r0.<init>(r7, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0056
            if (r2 == r5) goto L_0x004c
            if (r2 == r4) goto L_0x0040
            if (r2 != r3) goto L_0x0038
            java.lang.Object r1 = r0.L$1
            java.lang.Object r0 = r0.L$0
            androidx.datastore.core.CorruptionException r0 = (androidx.datastore.core.CorruptionException) r0
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ IOException -> 0x0036 }
            goto L_0x0088
        L_0x0036:
            r8 = move-exception
            goto L_0x008b
        L_0x0038:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0040:
            java.lang.Object r2 = r0.L$1
            androidx.datastore.core.CorruptionException r2 = (androidx.datastore.core.CorruptionException) r2
            java.lang.Object r4 = r0.L$0
            androidx.datastore.core.SingleProcessDataStore r4 = (androidx.datastore.core.SingleProcessDataStore) r4
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x007a
        L_0x004c:
            java.lang.Object r2 = r0.L$0
            androidx.datastore.core.SingleProcessDataStore r2 = (androidx.datastore.core.SingleProcessDataStore) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ CorruptionException -> 0x0054 }
            goto L_0x0064
        L_0x0054:
            r8 = move-exception
            goto L_0x0067
        L_0x0056:
            kotlin.ResultKt.throwOnFailure(r8)
            r0.L$0 = r7     // Catch:{ CorruptionException -> 0x0065 }
            r0.label = r5     // Catch:{ CorruptionException -> 0x0065 }
            java.lang.Object r8 = r7.readData(r0)     // Catch:{ CorruptionException -> 0x0065 }
            if (r8 != r1) goto L_0x0064
            return r1
        L_0x0064:
            return r8
        L_0x0065:
            r8 = move-exception
            r2 = r7
        L_0x0067:
            androidx.datastore.core.CorruptionHandler<T> r5 = r2.corruptionHandler
            r0.L$0 = r2
            r0.L$1 = r8
            r0.label = r4
            java.lang.Object r4 = r5.handleCorruption(r8, r0)
            if (r4 != r1) goto L_0x0076
            return r1
        L_0x0076:
            r6 = r2
            r2 = r8
            r8 = r4
            r4 = r6
        L_0x007a:
            r0.L$0 = r2     // Catch:{ IOException -> 0x0089 }
            r0.L$1 = r8     // Catch:{ IOException -> 0x0089 }
            r0.label = r3     // Catch:{ IOException -> 0x0089 }
            java.lang.Object r0 = r4.writeData$datastore_core(r8, r0)     // Catch:{ IOException -> 0x0089 }
            if (r0 != r1) goto L_0x0087
            return r1
        L_0x0087:
            r1 = r8
        L_0x0088:
            return r1
        L_0x0089:
            r8 = move-exception
            r0 = r2
        L_0x008b:
            r1 = r0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            kotlin.ExceptionsKt.addSuppressed(r1, r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.SingleProcessDataStore.readDataOrHandleCorruption(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0070, code lost:
        r7 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0075, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r2, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0079, code lost:
        throw r1;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:24:0x006c, B:30:0x0074] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readData(kotlin.coroutines.Continuation<? super T> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof androidx.datastore.core.SingleProcessDataStore$readData$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            androidx.datastore.core.SingleProcessDataStore$readData$1 r0 = (androidx.datastore.core.SingleProcessDataStore$readData$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            androidx.datastore.core.SingleProcessDataStore$readData$1 r0 = new androidx.datastore.core.SingleProcessDataStore$readData$1
            r0.<init>(r6, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0040
            if (r2 != r3) goto L_0x0038
            java.lang.Object r1 = r0.L$2
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            java.lang.Object r2 = r0.L$1
            java.io.Closeable r2 = (java.io.Closeable) r2
            java.lang.Object r0 = r0.L$0
            androidx.datastore.core.SingleProcessDataStore r0 = (androidx.datastore.core.SingleProcessDataStore) r0
            kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ all -> 0x0036 }
            goto L_0x006c
        L_0x0036:
            r7 = move-exception
            goto L_0x0074
        L_0x0038:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x0040:
            kotlin.ResultKt.throwOnFailure(r7)
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x007a }
            java.io.File r2 = r6.getFile()     // Catch:{ FileNotFoundException -> 0x007a }
            r7.<init>(r2)     // Catch:{ FileNotFoundException -> 0x007a }
            r2 = r7
            java.io.Closeable r2 = (java.io.Closeable) r2     // Catch:{ FileNotFoundException -> 0x007a }
            r7 = 0
            r4 = r7
            java.lang.Throwable r4 = (java.lang.Throwable) r4     // Catch:{ FileNotFoundException -> 0x007a }
            r4 = r2
            java.io.FileInputStream r4 = (java.io.FileInputStream) r4     // Catch:{ all -> 0x0072 }
            androidx.datastore.core.Serializer<T> r5 = r6.serializer     // Catch:{ all -> 0x0072 }
            java.io.InputStream r4 = (java.io.InputStream) r4     // Catch:{ all -> 0x0072 }
            r0.L$0 = r6     // Catch:{ all -> 0x0072 }
            r0.L$1 = r2     // Catch:{ all -> 0x0072 }
            r0.L$2 = r7     // Catch:{ all -> 0x0072 }
            r0.label = r3     // Catch:{ all -> 0x0072 }
            java.lang.Object r0 = r5.readFrom(r4, r0)     // Catch:{ all -> 0x0072 }
            if (r0 != r1) goto L_0x0069
            return r1
        L_0x0069:
            r1 = r7
            r7 = r0
            r0 = r6
        L_0x006c:
            kotlin.io.CloseableKt.closeFinally(r2, r1)     // Catch:{ FileNotFoundException -> 0x0070 }
            return r7
        L_0x0070:
            r7 = move-exception
            goto L_0x007c
        L_0x0072:
            r7 = move-exception
            r0 = r6
        L_0x0074:
            throw r7     // Catch:{ all -> 0x0075 }
        L_0x0075:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r2, r7)     // Catch:{ FileNotFoundException -> 0x0070 }
            throw r1     // Catch:{ FileNotFoundException -> 0x0070 }
        L_0x007a:
            r7 = move-exception
            r0 = r6
        L_0x007c:
            java.io.File r1 = r0.getFile()
            boolean r1 = r1.exists()
            if (r1 != 0) goto L_0x008d
            androidx.datastore.core.Serializer<T> r7 = r0.serializer
            java.lang.Object r7 = r7.getDefaultValue()
            return r7
        L_0x008d:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.SingleProcessDataStore.readData(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object transformAndWrite(kotlin.jvm.functions.Function2<? super T, ? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> r8, kotlin.coroutines.CoroutineContext r9, kotlin.coroutines.Continuation<? super T> r10) {
        /*
            r7 = this;
            boolean r0 = r10 instanceof androidx.datastore.core.SingleProcessDataStore$transformAndWrite$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            androidx.datastore.core.SingleProcessDataStore$transformAndWrite$1 r0 = (androidx.datastore.core.SingleProcessDataStore$transformAndWrite$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            androidx.datastore.core.SingleProcessDataStore$transformAndWrite$1 r0 = new androidx.datastore.core.SingleProcessDataStore$transformAndWrite$1
            r0.<init>(r7, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x004a
            if (r2 == r5) goto L_0x003c
            if (r2 != r4) goto L_0x0034
            java.lang.Object r8 = r0.L$1
            java.lang.Object r9 = r0.L$0
            androidx.datastore.core.SingleProcessDataStore r9 = (androidx.datastore.core.SingleProcessDataStore) r9
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0091
        L_0x0034:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x003c:
            java.lang.Object r8 = r0.L$2
            java.lang.Object r9 = r0.L$1
            androidx.datastore.core.Data r9 = (androidx.datastore.core.Data) r9
            java.lang.Object r2 = r0.L$0
            androidx.datastore.core.SingleProcessDataStore r2 = (androidx.datastore.core.SingleProcessDataStore) r2
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0076
        L_0x004a:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlinx.coroutines.flow.MutableStateFlow<androidx.datastore.core.State<T>> r10 = r7.downstreamFlow
            java.lang.Object r10 = r10.getValue()
            androidx.datastore.core.Data r10 = (androidx.datastore.core.Data) r10
            r10.checkHashCode()
            java.lang.Object r2 = r10.getValue()
            androidx.datastore.core.SingleProcessDataStore$transformAndWrite$newData$1 r6 = new androidx.datastore.core.SingleProcessDataStore$transformAndWrite$newData$1
            r6.<init>(r8, r2, r3)
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r0.L$0 = r7
            r0.L$1 = r10
            r0.L$2 = r2
            r0.label = r5
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r9, r6, r0)
            if (r8 != r1) goto L_0x0072
            return r1
        L_0x0072:
            r9 = r10
            r10 = r8
            r8 = r2
            r2 = r7
        L_0x0076:
            r9.checkHashCode()
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r10)
            if (r9 == 0) goto L_0x0080
            goto L_0x00a3
        L_0x0080:
            r0.L$0 = r2
            r0.L$1 = r10
            r0.L$2 = r3
            r0.label = r4
            java.lang.Object r8 = r2.writeData$datastore_core(r10, r0)
            if (r8 != r1) goto L_0x008f
            return r1
        L_0x008f:
            r8 = r10
            r9 = r2
        L_0x0091:
            kotlinx.coroutines.flow.MutableStateFlow<androidx.datastore.core.State<T>> r9 = r9.downstreamFlow
            androidx.datastore.core.Data r10 = new androidx.datastore.core.Data
            if (r8 == 0) goto L_0x009c
            int r0 = r8.hashCode()
            goto L_0x009d
        L_0x009c:
            r0 = 0
        L_0x009d:
            r10.<init>(r8, r0)
            r9.setValue(r10)
        L_0x00a3:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.SingleProcessDataStore.transformAndWrite(kotlin.jvm.functions.Function2, kotlin.coroutines.CoroutineContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c9, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r3, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00cd, code lost:
        throw r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00ce, code lost:
        r10 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00cf, code lost:
        r11 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00d8, code lost:
        r11.delete();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:26:0x009f, B:36:0x00c8] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00af A[SYNTHETIC, Splitter:B:31:0x00af] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00d8  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object writeData$datastore_core(T r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r9 = this;
            java.lang.String r0 = "Unable to rename "
            boolean r1 = r11 instanceof androidx.datastore.core.SingleProcessDataStore$writeData$1
            if (r1 == 0) goto L_0x0016
            r1 = r11
            androidx.datastore.core.SingleProcessDataStore$writeData$1 r1 = (androidx.datastore.core.SingleProcessDataStore$writeData$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r11 = r1.label
            int r11 = r11 - r3
            r1.label = r11
            goto L_0x001b
        L_0x0016:
            androidx.datastore.core.SingleProcessDataStore$writeData$1 r1 = new androidx.datastore.core.SingleProcessDataStore$writeData$1
            r1.<init>(r9, r11)
        L_0x001b:
            java.lang.Object r11 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 1
            if (r3 == 0) goto L_0x004b
            if (r3 != r4) goto L_0x0043
            java.lang.Object r10 = r1.L$4
            java.io.FileOutputStream r10 = (java.io.FileOutputStream) r10
            java.lang.Object r2 = r1.L$3
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r3 = r1.L$2
            java.io.Closeable r3 = (java.io.Closeable) r3
            java.lang.Object r4 = r1.L$1
            java.io.File r4 = (java.io.File) r4
            java.lang.Object r1 = r1.L$0
            androidx.datastore.core.SingleProcessDataStore r1 = (androidx.datastore.core.SingleProcessDataStore) r1
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ all -> 0x0040 }
            goto L_0x0096
        L_0x0040:
            r10 = move-exception
            goto L_0x00c8
        L_0x0043:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x004b:
            kotlin.ResultKt.throwOnFailure(r11)
            java.io.File r11 = r9.getFile()
            r9.createParentDirectories(r11)
            java.io.File r11 = new java.io.File
            java.io.File r3 = r9.getFile()
            java.lang.String r3 = r3.getAbsolutePath()
            java.lang.String r5 = r9.SCRATCH_SUFFIX
            java.lang.String r3 = kotlin.jvm.internal.Intrinsics.stringPlus(r3, r5)
            r11.<init>(r3)
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x00d1 }
            r3.<init>(r11)     // Catch:{ IOException -> 0x00d1 }
            java.io.Closeable r3 = (java.io.Closeable) r3     // Catch:{ IOException -> 0x00d1 }
            r5 = 0
            r6 = r5
            java.lang.Throwable r6 = (java.lang.Throwable) r6     // Catch:{ IOException -> 0x00d1 }
            r6 = r3
            java.io.FileOutputStream r6 = (java.io.FileOutputStream) r6     // Catch:{ all -> 0x00c6 }
            androidx.datastore.core.Serializer<T> r7 = r9.serializer     // Catch:{ all -> 0x00c6 }
            androidx.datastore.core.SingleProcessDataStore$UncloseableOutputStream r8 = new androidx.datastore.core.SingleProcessDataStore$UncloseableOutputStream     // Catch:{ all -> 0x00c6 }
            r8.<init>(r6)     // Catch:{ all -> 0x00c6 }
            java.io.OutputStream r8 = (java.io.OutputStream) r8     // Catch:{ all -> 0x00c6 }
            r1.L$0 = r9     // Catch:{ all -> 0x00c6 }
            r1.L$1 = r11     // Catch:{ all -> 0x00c6 }
            r1.L$2 = r3     // Catch:{ all -> 0x00c6 }
            r1.L$3 = r5     // Catch:{ all -> 0x00c6 }
            r1.L$4 = r6     // Catch:{ all -> 0x00c6 }
            r1.label = r4     // Catch:{ all -> 0x00c6 }
            java.lang.Object r10 = r7.writeTo(r10, r8, r1)     // Catch:{ all -> 0x00c6 }
            if (r10 != r2) goto L_0x0092
            return r2
        L_0x0092:
            r1 = r9
            r4 = r11
            r2 = r5
            r10 = r6
        L_0x0096:
            java.io.FileDescriptor r10 = r10.getFD()     // Catch:{ all -> 0x0040 }
            r10.sync()     // Catch:{ all -> 0x0040 }
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0040 }
            kotlin.io.CloseableKt.closeFinally(r3, r2)     // Catch:{ IOException -> 0x00ce }
            java.io.File r10 = r1.getFile()     // Catch:{ IOException -> 0x00ce }
            boolean r10 = r4.renameTo(r10)     // Catch:{ IOException -> 0x00ce }
            if (r10 == 0) goto L_0x00af
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L_0x00af:
            java.io.IOException r10 = new java.io.IOException     // Catch:{ IOException -> 0x00ce }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00ce }
            r11.<init>(r0)     // Catch:{ IOException -> 0x00ce }
            r11.append(r4)     // Catch:{ IOException -> 0x00ce }
            java.lang.String r0 = ".This likely means that there are multiple instances of DataStore for this file. Ensure that you are only creating a single instance of datastore for this file."
            r11.append(r0)     // Catch:{ IOException -> 0x00ce }
            java.lang.String r11 = r11.toString()     // Catch:{ IOException -> 0x00ce }
            r10.<init>(r11)     // Catch:{ IOException -> 0x00ce }
            throw r10     // Catch:{ IOException -> 0x00ce }
        L_0x00c6:
            r10 = move-exception
            r4 = r11
        L_0x00c8:
            throw r10     // Catch:{ all -> 0x00c9 }
        L_0x00c9:
            r11 = move-exception
            kotlin.io.CloseableKt.closeFinally(r3, r10)     // Catch:{ IOException -> 0x00ce }
            throw r11     // Catch:{ IOException -> 0x00ce }
        L_0x00ce:
            r10 = move-exception
            r11 = r4
            goto L_0x00d2
        L_0x00d1:
            r10 = move-exception
        L_0x00d2:
            boolean r0 = r11.exists()
            if (r0 == 0) goto L_0x00db
            r11.delete()
        L_0x00db:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.SingleProcessDataStore.writeData$datastore_core(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void createParentDirectories(File file) {
        File parentFile = file.getCanonicalFile().getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
            if (!parentFile.isDirectory()) {
                throw new IOException(Intrinsics.stringPlus("Unable to create parent directories of ", file));
            }
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0016J \u0010\n\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u000fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Landroidx/datastore/core/SingleProcessDataStore$UncloseableOutputStream;", "Ljava/io/OutputStream;", "fileOutputStream", "Ljava/io/FileOutputStream;", "(Ljava/io/FileOutputStream;)V", "getFileOutputStream", "()Ljava/io/FileOutputStream;", "close", "", "flush", "write", "b", "", "bytes", "off", "", "len", "datastore-core"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* compiled from: SingleProcessDataStore.kt */
    private static final class UncloseableOutputStream extends OutputStream {
        private final FileOutputStream fileOutputStream;

        public void close() {
        }

        public UncloseableOutputStream(FileOutputStream fileOutputStream2) {
            Intrinsics.checkNotNullParameter(fileOutputStream2, "fileOutputStream");
            this.fileOutputStream = fileOutputStream2;
        }

        public final FileOutputStream getFileOutputStream() {
            return this.fileOutputStream;
        }

        public void write(int i) {
            this.fileOutputStream.write(i);
        }

        public void write(byte[] bArr) {
            Intrinsics.checkNotNullParameter(bArr, "b");
            this.fileOutputStream.write(bArr);
        }

        public void write(byte[] bArr, int i, int i2) {
            Intrinsics.checkNotNullParameter(bArr, "bytes");
            this.fileOutputStream.write(bArr, i, i2);
        }

        public void flush() {
            this.fileOutputStream.flush();
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0000X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/datastore/core/SingleProcessDataStore$Companion;", "", "()V", "activeFiles", "", "", "getActiveFiles$datastore_core", "()Ljava/util/Set;", "activeFilesLock", "getActiveFilesLock$datastore_core", "()Ljava/lang/Object;", "datastore-core"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* compiled from: SingleProcessDataStore.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Set<String> getActiveFiles$datastore_core() {
            return SingleProcessDataStore.activeFiles;
        }

        public final Object getActiveFilesLock$datastore_core() {
            return SingleProcessDataStore.activeFilesLock;
        }
    }
}
